package uk.ac.shef.semweb;

// Add necessary imports.
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class is used to extract the artist information from xml files and then
 * use this information to populate an ontology.
 * @author Team BDM
 *
 */
public class ArtistBuilder extends RdfBuilder 
{
	// Declare private variables
	/**
	 * This resource will be added to the ontology with all the artist details.
	 */
	private Resource artistRes;
	
	/**
	 * This ResultSet contains the dbPedia results for an artist.
	 */
	private ResultSet rs;
	
	/**
	 * Constructor for the class.
	 * @param ontology This argument is a Jena Model.
	 * @param xml This argument takes in an xml document.
	 * @param url This argument takes in the url of the xml file.
	 * @param withWebServices This argument determines whether dbPedia and twitter information is extracted.
	 */
    public ArtistBuilder(Model ontology, Document xml, String url, boolean withWebServices) 
    {
    	// Call super constructor.
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
        this.artistRes = this.ontology.createResource(getUri());
        // Add type property to the current resource.
        this.artistRes.addProperty(RDF.type, this.properties.artistClas);

        // Add remaining properties to the resource.
        this.artistRes.addProperty(this.properties.nameProp, getSingleProp(this.nodes.titleNode));
        this.artistRes.addProperty(this.properties.biographyProp, getSingleProp(this.nodes.biographyNode));
        this.artistRes.addProperty(this.properties.imageProp, getSingleProp(this.nodes.imageNode));
        this.artistRes.addProperty(this.properties.websiteProp, getSingleProp(this.nodes.websiteNode));

        for (int i=0; i<this.nodes.albumNodes.getLength(); i++)
        {
            Node albumNode = this.nodes.albumNodes.item(i);
            Resource albumRes = this.ontology.createResource(getUrlAttr(albumNode));
            albumRes.addProperty(RDF.type, this.properties.albumClas);
            this.artistRes.addProperty(this.properties.albumProp, albumRes);
            
            albumRes.addProperty(this.properties.titleProp, getSingleProp(albumNode));
        }
    }

    /**
     * This function extracts the dbPedia and twitter services of an artist.
     */
    @Override
    public void extractWebServices()
    {
    	// Get dbPedia link.
        dbpediaLink = getSingleProp(this.nodes.dbpediaNode);
        
        // Set the different properties to the resource.
        setGenre();
        setAssociatedBand();
        
        setWikiPage();
        
        setHomeTown();
                
    }

    /**
     * This function sets the homeTownProp to the current resource.
     */
    private void setHomeTown()
    {
    	// Query dbPedia for hometown.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaHomeTown);
        while (rs.hasNext())
        {
        	// Get resulting resource.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.homeTownProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }

    /**
     * This function sets the dbPediaWikiPage property of the current resource.
     */
    private void setWikiPage()
    {
    	// Query dbPedia for the wiki page.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaWikiPage);
        while (rs.hasNext())
        {
        	// Get the resulting resource.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.wikiPageProp, res.toString());
        }
        Extractor.delay();
    }

    /**
     * This function sets the associatedBand property of the current resource.
     */
    private void setAssociatedBand()
    {
    	// Query dbPedia for the associated band property.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaAssociatedBand);
        while (rs.hasNext())
        {
        	// Get the resulting resource.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.associatedBandProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }

    /**
     * This function sets the genre property of the current resource.
     */
    private void setGenre()
    {
    	// Query dbPedia for the genre property.
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaGenre);
        while (rs.hasNext())
        {
        	// Get the resulting resource.
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.genreProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }
}
