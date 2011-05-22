package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AlbumBuilder extends RdfBuilder
{

    public AlbumBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
        super(ontology, xml, url, withWebServices);

    }

    @Override
    public void extractXml()
    {
        //TODO declare resource as field
        Resource albumRes = this.ontology.createResource(getUri());
        albumRes.addProperty(RDF.type, this.properties.albumClas);

        albumRes.addProperty(this.properties.titleProp, getSingleProp(this.nodes.titleNode));
        albumRes.addProperty(this.properties.genreProp, getSingleProp(this.nodes.genreNode));
        albumRes.addProperty(this.properties.imageProp, getSingleProp(this.nodes.imageNode));

        for (int i = 0; i < this.nodes.trackNodes.getLength(); i++ )
        {
            Node trackNode = this.nodes.trackNodes.item(i);
            albumRes.addProperty(this.properties.trackProp, getSingleProp(trackNode));
        }
    }

    @Override
    public void extractWebServices()
    {
        // nothing to extract
        
    }
}
