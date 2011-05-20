package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserBuilder extends RdfBuilder
{

    public UserBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
        super(ontology, xml, url, withWebServices);
    }

    @Override
    public void extractXml()
    {

        Resource userRes = this.ontology.createResource(getUri());
        userRes.addProperty(RDF.type, this.userClas);

        userRes.addProperty(this.usernameProp, getSingleProp(this.usernameNode));

        //add vote events
        for (int i = 0; i < this.voteEventNodes.getLength(); i++ )
        {
            Node voteEventNode = this.voteEventNodes.item(i);

            Resource voteEventRes = this.ontology.createResource(getUri() + "#VoteEvent" + i);
            voteEventRes.addProperty(RDF.type, this.voteEventClas);
            userRes.addProperty(this.voteEventProp, voteEventRes);

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
        // TODO Auto-generated method stub
        
    }

}
