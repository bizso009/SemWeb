package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class GigBuilder extends RdfBuilder 
{

    
    public GigBuilder(Model ontology, Document xml, String url, boolean withDBpedia) 
    {
        super(ontology, xml, url, withDBpedia);
    }

    @Override
    public void extractXml() 
    {
        Resource gigRes = this.ontology.createResource(getUri());
        gigRes.addProperty(RDF.type, this.properties.gigClas);

        gigRes.addProperty(this.properties.dateProp, getSingleProp(this.nodes.dateNode));
        gigRes.addProperty(this.properties.titleProp, getSingleProp(this.nodes.titleNode));
        
        Resource artistRes = this.ontology.createResource(getUrlAttr(this.nodes.artistNode));
        artistRes.addProperty(this.properties.nameProp, getSingleProp(this.nodes.artistNode));
        
        gigRes.addProperty(this.properties.artistProp, artistRes);

    }
    @Override
    public void extractWebServices()
    {
        // nothing to extract
        
    }

}
