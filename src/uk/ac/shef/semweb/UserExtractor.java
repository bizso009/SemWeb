package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserExtractor extends FileExtractor
{

    public UserExtractor(Model ontology, Document xml, String url) throws XPathExpressionException
    {
        super(ontology, xml, url);
    }

    @Override
    public void extract()
    {

        Resource userRes = this.ontology.createResource(getUri());
        userRes.addProperty(RDF.type, this.userClas);

        userRes.addProperty(this.usernameProp, getSingleProp(this.usernameNode));

        //add vote events
        for (int i = 0; i < this.voteEventNodes.getLength(); i++ )
        {
            Node voteEventNode = this.voteEventNodes.item(i);

            Resource voteEventRes = this.ontology.createResource(getUri() + "#VoteEvent" + i);
            userRes.addProperty(RDF.type, this.voteEventClas);

            NodeList voteEventChildren = voteEventNode.getChildNodes();

            Node gigNode = voteEventChildren.item(0);
            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.gigClas);
            gigRes.addProperty(this.titleProp, getSingleProp(gigNode));
            voteEventRes.addProperty(this.gigProp, gigRes);

            Node voteNode = voteEventChildren.item(1);
            voteEventRes.addProperty(this.voteProp, getSingleProp(voteNode));

        }
        // TODO get from twitter
        //getImage 
    }

}
