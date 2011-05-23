package uk.ac.shef.semweb;

// Add all the necessary imports
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;


/**
 * This class is used to extract properties of an album from an xml file.
 * These properties are then used to populate the ontology.
 * @author Team BDM
 *
 */
public class AlbumBuilder extends RdfBuilder 
{

	/**
	 * Contructor for the class
	 * @param ontology This argument is a Jena Model.
	 * @param xml This argument takes in an xml document.
	 * @param url This argument takes in the url of the xml file.
	 * @param withWebServices This argument determines whether dbPedia and twitter information is extracted.
	 */
    public AlbumBuilder(Model ontology, Document xml, String url, boolean withWebServices) 
    {
    	// Call the super constructor.
        super(ontology, xml, url, withWebServices);
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
        Resource albumRes = ontology.createResource(getUri());
        // Add type property to the current resource.
        albumRes.addProperty(RDF.type, properties.albumClas);

        // Add remaining properties to the current resource.
        albumRes.addProperty(properties.titleProp, getSingleProp(nodes.titleNode));
        albumRes.addProperty(properties.genreProp, getSingleProp(nodes.genreNode));
        albumRes.addProperty(properties.imageProp, getSingleProp(nodes.imageNode));

        for (int i = 0; i < nodes.trackNodes.getLength(); i++) 
        {
            Node trackNode = nodes.trackNodes.item(i);
            albumRes.addProperty(properties.trackProp, getSingleProp(trackNode));
        }
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
