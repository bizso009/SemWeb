package uk.ac.shef.semweb;


// Add necessary packages.
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;


/**
 * This class is used to extract properties of a user from an XML file.
 * These properties will be then used to populate an ontology.
 * @author Team BDM
 *
 */
public class UserBuilder extends RdfBuilder
{

	/**
	 * This resource will be added to the ontology with all the user details.
	 */
    private Resource userRes;
    
    /**
     * This string holds the url for the twitter xml content.
     */
    private String   twitterXml = "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
   
    
    /**
	 * Contructor for the class
	 * @param ontology This argument is a Jena Model.
	 * @param xml This argument takes in an xml document.
	 * @param url This argument takes in the url of the xml file.
	 * @param withWebServices This argument determines whether dbPedia and twitter information is extracted.
	 */
    public UserBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
    	// Call to super constructor.
        super(ontology, xml, url, withWebServices);
    }

    /**
     * This function extracts information from an xml file and used this information to 
     * populate the ontology.
     */
    @Override
    public void extractXml()
    {

    	// Create new resource using the uri of the xml file.
    	// Add this resource to the ontology.
        this.userRes = this.ontology.createResource(getUri());
        // Add type property to the current resource.
        this.userRes.addProperty(RDF.type, this.properties.userClas);

        // Add remaining properties to the current resource.
        String username = getSingleProp(this.nodes.usernameNode);
        this.twitterXml += username;
        this.userRes.addProperty(this.properties.usernameProp, username);

        //add vote events
        for (int i = 0; i < this.nodes.voteEventNodes.getLength(); i++ )
        {
            Node voteEventNode = this.nodes.voteEventNodes.item(i);

            Resource voteEventRes = this.ontology.createResource(getUri() + "#VoteEvent" + i);
            voteEventRes.addProperty(RDF.type, this.properties.voteEventClas);
            this.userRes.addProperty(this.properties.voteEventProp, voteEventRes);

            NodeList voteEventChildren = voteEventNode.getChildNodes();

            short gigNodeIdx = 1;
            Node gigNode = voteEventChildren.item(gigNodeIdx);
            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.properties.gigClas);
            gigRes.addProperty(this.properties.titleProp, getSingleProp(gigNode));
            voteEventRes.addProperty(this.properties.gigProp, gigRes);

            short voteNodeIdx = 3;
            Node voteNode = voteEventChildren.item(voteNodeIdx);
            voteEventRes.addProperty(this.properties.voteProp, getSingleProp(voteNode));

        }
    }

    /**
     * This function is used obtain any information from dbPedia and twitter if the information is
     * present.
     */
    @Override
    public void extractWebServices()
    {
        try
        {
            Extractor ex = new Extractor();
            this.xml = ex.readXml(ex.openUrl(this.twitterXml).getContent());
            this.userRes.addProperty(this.properties.imageProp, getSingleProp(queryTag("profile_image_url").item(0)));
            NodeList tweetNodes = queryTag("text");
            for (int i=0; i<tweetNodes.getLength(); i++)
            {
                this.userRes.addProperty(this.properties.tweetProp, StringEscapeUtils.unescapeHtml(getSingleProp(tweetNodes.item(i))));
            }   
            
        }
        catch (Exception e)
        {
            System.out.println("Error connecting to Twitter");
            e.printStackTrace();
            
        }
    }

}
