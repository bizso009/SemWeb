package uk.ac.shef.semweb;

// Add necessary imports
import org.w3c.dom.Document;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class is used to extract properties of a Gig from an xml file.
 * These properties are then used to populate the ontology.
 * @author Team BDM
 *
 */
public class GigBuilder extends RdfBuilder 
{

	/**
	 * Contructor for the class
	 * @param ontology This argument is a Jena Model.
	 * @param xml This argument takes in an xml document.
	 * @param url This argument takes in the url of the xml file.
	 * @param withWebServices This argument determines whether dbPedia and twitter information is extracted.
	 */
    public GigBuilder(Model ontology, Document xml, String url, boolean withDBpedia) 
    {
        super(ontology, xml, url, withDBpedia);
    }

    /**
     * This function extracts information from an xml file and used this information to 
     * populate the ontology.
     */
    @Override
    public void extractXml() 
    {
    	// Create new resource using the uri of the xml file.
    	// Add this resource to the ontology.
        Resource gigRes = ontology.createResource(getUri());
        // Add type property to the current resource.
        gigRes.addProperty(RDF.type, properties.gigClas);

        // Add remaining properties to the current resource.
        gigRes.addProperty(properties.dateProp, getSingleProp(nodes.dateNode));
        gigRes.addProperty(properties.titleProp, getSingleProp(nodes.titleNode));

        Resource artistRes = ontology.createResource(getUrlAttr(nodes.artistNode));
        artistRes.addProperty(properties.nameProp, getSingleProp(nodes.artistNode));

        gigRes.addProperty(properties.artistProp, artistRes);

    }

    /**
     * This function can be coded to obtain any information from dbPedia and twitter if the information is
     * present.
     */
    @Override
    public void extractWebServices() 
    {
        // nothing to extract

    }

}
