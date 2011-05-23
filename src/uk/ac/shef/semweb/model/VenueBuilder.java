package uk.ac.shef.semweb.model;

// Add necessary imports.
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import uk.ac.shef.semweb.controller.Extractor;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class is used to extract properties of a Venue from an xml file.
 * These properties will be then used to populate an ontology.
 * @author Team BDM
 *
 */
public class VenueBuilder extends RdfBuilder
{

	/**
	 * This resource will be added to the ontology with all the Venu details.
	 */
    private Resource venueRes;
    
    /**
     * The ResultSet will hold the dbPedia results for a Venue.
     */
    private ResultSet rs;
    
    /**
	 * Constructor for the class.
	 * @param ontology This argument is a Jena Model.
	 * @param xml This argument takes in an xml document.
	 * @param url This argument takes in the url of the xml file.
	 * @param withWebServices This argument determines whether dbPedia and twitter information is extracted.
	 */
    public VenueBuilder(Model ontology, Document xml, String url, boolean withWebSerives)
    {
    	// Call to super constructor.
        super(ontology, xml, url, withWebSerives);
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
        this.venueRes = this.ontology.createResource(getUri());
        // Add type property to the current resource.
        this.venueRes.addProperty(RDF.type, this.properties.venueClas);

        // Add remaining properties to the current resource.
        this.venueRes.addProperty(this.properties.websiteProp, getSingleProp(this.nodes.websiteNode));
        this.venueRes.addProperty(this.properties.nameProp, getSingleProp(this.nodes.nameNode));
        this.venueRes.addProperty(this.properties.descriptionProp, getSingleProp(this.nodes.descriptionNode));
        this.venueRes.addProperty(this.properties.imageProp, getSingleProp(this.nodes.imageNode));

        //add gigs
        for (int i = 0; i < this.nodes.gigNodes.getLength(); i++ )
        {
            Node gigNode = this.nodes.gigNodes.item(i);

            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.properties.gigClas);
            gigRes.addProperty(this.properties.titleProp, getSingleProp(gigNode));

            this.venueRes.addProperty(this.properties.gigProp, gigRes);

        }
    }

    /**
     * This function is used obtain any information from dbPedia and twitter if the information is
     * present.
     */
    @Override
    public void extractWebServices()
    {
        dbpediaLink = getSingleProp(this.nodes.dbpediaNode);

        setCategory();


        setWikiPage();

        setGeoLat();
        
        setGeoLon();
    }

    /**
     * This function sets the GeoLon Property to the current resource.
     */
    private void setGeoLon()
    {
    	// Query dbPedia for GeoLon.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaGeoLon);
        while (rs.hasNext())
        {
        	// Get resulting resource.
            Literal res = rs.next().get(this.dbpediaVAR).asLiteral();
            this.venueRes.addProperty(this.properties.geoLonProp, new Float(res.getFloat()).toString());
        }
        Extractor.delay();
    }

    /**
     * This function sets the GeoLat property to the current resource.
     */
    private void setGeoLat()
    {
    	// Query dbPedia for GeoLat.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaGeoLat);
        while (rs.hasNext())
        {
        	// Get resulting resource.
            Literal res = rs.next().get(this.dbpediaVAR).asLiteral();
            this.venueRes.addProperty(this.properties.geoLatProp, new Float(res.getFloat()).toString());
        }
        Extractor.delay();
    }

    /**
     * This function sets the wikipedia page property for the current resource.
     */
    private void setWikiPage()
    {
    	// Query dbPedia for wiki page.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaWikiPage);
        while (rs.hasNext())
        {
        	// Get resulting resource.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.venueRes.addProperty(this.properties.wikiPageProp, res.toString());
        }
        Extractor.delay();
    }

    /**
     * This function sets the category property to the current resource.
     */
    private void setCategory()
    {
    	// Query dbPedia for the category.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaCategory);
        while (rs.hasNext())
        {
        	// Get resultant category.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.venueRes.addProperty(this.properties.categoryProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }
}
