package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AlbumExtractor extends FileExtractor
{

    public AlbumExtractor(Model ontology, Document xml, String url) throws XPathExpressionException
    {
        super(ontology, xml, url);

    }

    @Override
    public void extract()
    {
        Resource albumRes = this.ontology.createResource(getUri());
        albumRes.addProperty(RDF.type, this.albumClas);

        albumRes.addProperty(this.titleProp, getSingleProp(this.titleNode));
        albumRes.addProperty(this.genreProp, getSingleProp(this.genreNode));
        albumRes.addProperty(this.imageProp, getSingleProp(this.imageNode));

        for (int i = 0; i < this.trackNodes.getLength(); i++ )
        {
            Node trackNode = this.trackNodes.item(i);
            albumRes.addProperty(this.trackProp, getSingleProp(trackNode));
        }
    }
}
