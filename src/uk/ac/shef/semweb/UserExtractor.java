package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserExtractor extends FileExtractor
{

    public UserExtractor(Model ontology, Document xml, String url)
    {
        super(ontology, xml, url);
    }

    @Override
    public void extract() throws XPathExpressionException
    {

        //create resource
        Resource userRes = this.ontology.createResource(getUri());
        //add class
        Resource userClas = this.ontology.getResource("#User");
        userRes.addProperty(RDF.type, userClas);

        //add username
        Node userNameNode = query("//twitterID").item(0);
        if (userNameNode != null)
        {
            String userName = userNameNode.getTextContent();
            Property userNameProp = this.ontology.getProperty("#hasUsername");
            userRes.addProperty(userNameProp, userName);
        }
        //add vote events
        NodeList voteEventNodeList = query("//voteEvent");
        for (int i = 0; i < voteEventNodeList.getLength(); i++ )
        {
            Node voteEventNode = voteEventNodeList.item(i);
            if (voteEventNode != null)
            {
                //add vote event resource
                Resource voteEventRes = this.ontology.createResource("#VoteEvent");
                //add class
                Resource voteEventclas = this.ontology.getResource("#User");
                userRes.addProperty(RDF.type, voteEventclas);
                
                NodeList voteEventChildren = voteEventNode.getChildNodes();
                Node gigNode = voteEventChildren.item(0);
                if (gigNode != null)
                {
                    String gigUri = "http://poplar.dcs.shef.ac.uk" + gigNode.getAttributes().getNamedItem("url").getTextContent();
                    Resource gigRes = this.ontology.createResource(gigUri);
                    Resource gigClas = this.ontology.getResource("#Gig");
                    gigRes.addProperty(RDF.type, gigClas);

                    String gigTitle = gigNode.getTextContent();
                    Property gigTitleProp = this.ontology.getProperty("#hasTitle");
                    gigRes.addProperty(gigTitleProp, gigTitle);
                    
                    Property gigProperty = this.ontology.getProperty("#hasGig");
                    voteEventRes.addProperty(gigProperty, gigRes);
                    
                }
                
                Node voteNode = voteEventChildren.item(1);
                if (voteNode !=null){
                    String vote = voteNode.getTextContent();
                    Property voteProp = this.ontology.getProperty("#hasVote");
                    voteEventRes.addProperty(voteProp, vote);            
                }

                String gigUri = voteEventNode.getAttributes().getNamedItem("url").getTextContent();
                Resource gigRes = this.ontology.createResource(gigUri);
                Resource gigClas = this.ontology.getResource("#Gig");
                gigRes.addProperty(RDF.type, gigClas);
            }
        }
        // TODO get from twitter
        //getImage 
    }

}
