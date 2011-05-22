package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class AlbumBuilder extends RdfBuilder {

    public AlbumBuilder(Model ontology, Document xml, String url, boolean withWebServices) {
        super(ontology, xml, url, withWebServices);
    }

    @Override
    public void extractXml() {
        // TODO declare resource as field
        Resource albumRes = ontology.createResource(getUri());
        albumRes.addProperty(RDF.type, properties.albumClas);

        albumRes.addProperty(properties.titleProp, getSingleProp(nodes.titleNode));
        albumRes.addProperty(properties.genreProp, getSingleProp(nodes.genreNode));
        albumRes.addProperty(properties.imageProp, getSingleProp(nodes.imageNode));

        for (int i = 0; i < nodes.trackNodes.getLength(); i++) {
            Node trackNode = nodes.trackNodes.item(i);
            albumRes.addProperty(properties.trackProp, getSingleProp(trackNode));
        }
    }

    @Override
    public void extractWebServices() {
        // nothing to extract

    }
}
