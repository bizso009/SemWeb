package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class GigExtractor extends FileExtractor 
{

    public GigExtractor(Model ontology, Document xml, String url) 
    {
    	super(ontology, xml, url);
    }

    @Override
    public void extract() 
    {
        Resource gigRes = this.ontology.createResource(getUri());
        gigRes.addProperty(RDF.type, this.gigClas);

        gigRes.addProperty(this.dateProp, getSingleProp(this.dateNode));
        gigRes.addProperty(this.titleProp, getSingleProp(this.titleNode));
        
        Resource artistRes = this.ontology.createResource(getUrlAttr(this.artistNode));
        artistRes.addProperty(this.nameProp, getSingleProp(this.artistNode));
        
        gigRes.addProperty(this.artistProp, artistRes);

    }

}
