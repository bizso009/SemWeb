package uk.ac.shef.semweb;

import org.w3c.dom.Document;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class GigBuilder extends RdfBuilder {

    public GigBuilder(Model ontology, Document xml, String url, boolean withDBpedia) {
        super(ontology, xml, url, withDBpedia);
    }

    @Override
    public void extractXml() {
        Resource gigRes = ontology.createResource(getUri());
        gigRes.addProperty(RDF.type, properties.gigClas);

        gigRes.addProperty(properties.dateProp, getSingleProp(nodes.dateNode));
        gigRes.addProperty(properties.titleProp, getSingleProp(nodes.titleNode));

        Resource artistRes = ontology.createResource(getUrlAttr(nodes.artistNode));
        artistRes.addProperty(properties.nameProp, getSingleProp(nodes.artistNode));

        gigRes.addProperty(properties.artistProp, artistRes);

    }

    @Override
    public void extractWebServices() {
        // nothing to extract

    }

}
