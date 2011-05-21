package uk.ac.shef.semweb;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserBuilder extends RdfBuilder
{

    private Resource userRes;
    private String   twitterXml = "http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=";
     
    public UserBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
        super(ontology, xml, url, withWebServices);
    }

    @Override
    public void extractXml()
    {

        this.userRes = this.ontology.createResource(getUri());
        this.userRes.addProperty(RDF.type, this.userClas);

        String username = getSingleProp(this.usernameNode);
        this.twitterXml += username;
        this.userRes.addProperty(this.usernameProp, username);

        //add vote events
        for (int i = 0; i < this.voteEventNodes.getLength(); i++ )
        {
            Node voteEventNode = this.voteEventNodes.item(i);

            Resource voteEventRes = this.ontology.createResource(getUri() + "#VoteEvent" + i);
            voteEventRes.addProperty(RDF.type, this.voteEventClas);
            this.userRes.addProperty(this.voteEventProp, voteEventRes);

            NodeList voteEventChildren = voteEventNode.getChildNodes();

            short gigNodeIdx = 1;
            Node gigNode = voteEventChildren.item(gigNodeIdx);
            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.gigClas);
            gigRes.addProperty(this.titleProp, getSingleProp(gigNode));
            voteEventRes.addProperty(this.gigProp, gigRes);

            short voteNodeIdx = 3;
            Node voteNode = voteEventChildren.item(voteNodeIdx);
            voteEventRes.addProperty(this.voteProp, getSingleProp(voteNode));

        }
        // TODO get from twitter
        //getImage 
    }

    @Override
    public void extractWebServices()
    {
        try
        {
            Extractor ex = new Extractor();
            this.xml = ex.loadXml(ex.openUrl(this.twitterXml).getContent());
            this.userRes.addProperty(this.imageProp, getSingleProp(queryTag("profile_image_url").item(0)));
            NodeList tweetNodes = queryTag("text");
            for (int i=0; i<tweetNodes.getLength(); i++){
                this.userRes.addProperty(this.tweetProp, getSingleProp(tweetNodes.item(i)));
            }   
            
        }
        catch (Exception e)
        {
            System.out.println("Error connecting to Twitter");
            e.printStackTrace();
            
        }
    }

}
