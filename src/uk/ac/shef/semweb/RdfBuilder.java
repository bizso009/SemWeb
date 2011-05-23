package uk.ac.shef.semweb;

// Add necessary imports.
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * This class is the superclass that handles the population of the ontology.
 * @author Team BDM.
 *
 */
public abstract class RdfBuilder
{
	/**
	 * This class instantiates the nodes that are used in the XML files.
	 * @author Team BDM.
	 *
	 */
    public class IntelWebNodes
    {
    	/**
    	 * Artist Node
    	 */
        protected final Node     artistNode;
        
        /**
         * Biography Node.
         */
        protected final Node     biographyNode;
        
        /**
         * Date Node.
         */
        protected final Node     dateNode;
        
        /**
         * DBPedia Node.
         */
        protected final Node     dbpediaNode;
        
        /**
         * Description Node.
         */
        protected final Node     descriptionNode;
        
        /**
         * Genre Node.
         */
        protected final Node     genreNode;
        
        /**
         * Image Node.
         */
        protected final Node     imageNode;
        
        /**
         * Name Node.
         */
        protected final Node     nameNode;
        
        /**
         * Username Node.
         */
        protected final Node     usernameNode;
        
        /**
         * Website Node.
         */
        protected final Node     websiteNode;
        
        /**
         * Title Node.
         */
        protected final Node     titleNode;

        
        /**
         * Album nodes list.
         */
        protected final NodeList albumNodes;
        
        /**
         * Track nodes list.
         */
        protected final NodeList trackNodes;
        
        /**
         * Gig nodes list.
         */
        protected final NodeList gigNodes;
        
        /**
         * Vote Event nodes list.
         */
        protected final NodeList voteEventNodes;

        /**
         * Class constructor
         */
        public IntelWebNodes()
        {
            titleNode = queryTag("title").item(0);
            genreNode = queryTag("genre").item(0);
            imageNode = queryTag("image").item(0);
            websiteNode = queryTag("website").item(0);
            usernameNode = queryTag("twitterID").item(0);
            nameNode = queryTag("name").item(0);
            biographyNode = queryTag("biography").item(0);
            descriptionNode = queryTag("description").item(0);
            artistNode = queryTag("artist").item(0);
            dateNode = queryTag("date").item(0);
            dbpediaNode = queryTag("dbpedia").item(0);

            trackNodes = queryTag("track");
            gigNodes = queryTag("gig");
            voteEventNodes = queryTag("voteEvent");
            albumNodes = queryTag("album");
        }
    }

    /**
     * This class handles the properties for the ontology.
     * @author Team BDM
     *
     */
    public static class IntelWebProperties
    {

    	/**
    	 * Album class.
    	 */
        public final Resource albumClas;
        
        /**
         * Album Property.
         */
        public final Property albumProp;
        
        /**
         * Artist Class.
         */
        public final Resource artistClas;
        
        /**
         * Artist Property.
         */
        public final Property artistProp;
        
        /**
         * Associated Band Property.
         */
        public final Property associatedBandProp;
        
        /**
         * Biography Property.
         */
        public final Property biographyProp;
        
        /**
         * Category Property.
         */
        public final Property categoryProp;
        
        /**
         * Date Property.
         */
        public final Property dateProp;
        
        /**
         * Description Property.
         */
        public final Property descriptionProp;
        
        /**
         * Genre Property.
         */
        public final Property genreProp;
        
        /**
         * Geo Latitude Property.
         */
        public final Property geoLatProp;
        
        /**
         * Geo Longitude Property.
         */
        public final Property geoLonProp;
        
        /**
         * Gig Class.
         */
        public final Resource gigClas;
        
        /**
         * Gig Property.
         */
        public final Property gigProp;
        
        /**
         * Home Town Property.
         */
        public final Property homeTownProp;
        
        /**
         * Image Property.
         */
        public final Property imageProp;
        
        /**
         * Name property.
         */
        public final Property nameProp;
        
        /**
         * Title property.
         */
        public final Property titleProp;
        
        /**
         * Track Property.
         */
        public final Property trackProp;
        
        /**
         * Tweet Property.
         */
        public final Property tweetProp;
        
        /**
         * User Class.
         */
        public final Resource userClas;
        
        /**
         * User Name Property.
         */
        public final Property usernameProp;

        /**
         * Venue Class.
         */
        public final Resource venueClas;
        
        /**
         * Vote Event Class.
         */
        public final Resource voteEventClas;
        
        /**
         * Vote Event Property.
         */
        public final Property voteEventProp;
        
        /**
         * Vote Property.
         */
        public final Property voteProp;
        
        /**
         * Website Property.
         */
        public final Property websiteProp;
        
        /**
         * Wikipedia Page Property.
         */
        public final Property wikiPageProp;

        /**
         * Class Constructor.
         * @param ontology This takes in a Jena Model.
         */
        public IntelWebProperties(Model ontology)
        {

            titleProp = ontology.getProperty(RDF_BASE + "hasTitle");
            genreProp = ontology.getProperty(RDF_BASE + "hasGenre");
            imageProp = ontology.getProperty(RDF_BASE + "hasImage");
            trackProp = ontology.getProperty(RDF_BASE + "hasTrack");
            usernameProp = ontology.getProperty(RDF_BASE + "hasUsername");
            gigProp = ontology.getProperty(RDF_BASE + "hasGig");
            websiteProp = ontology.getProperty(RDF_BASE + "hasWebsite");
            nameProp = ontology.getProperty(RDF_BASE + "hasName");
            descriptionProp = ontology.getProperty(RDF_BASE + "hasDesription");
            voteProp = ontology.getProperty(RDF_BASE + "hasVote");
            biographyProp = ontology.getProperty(RDF_BASE + "hasBiography");
            artistProp = ontology.getProperty(RDF_BASE + "hasArtist");
            dateProp = ontology.getProperty(RDF_BASE + "hasDate");
            voteEventProp = ontology.getProperty(RDF_BASE + "hasVoteEvent");
            albumProp = ontology.getProperty(RDF_BASE + "hasAlbum");
            associatedBandProp = ontology.getProperty(RDF_BASE + "hasAssociatedBand");
            wikiPageProp = ontology.getProperty(RDF_BASE + "hasWikiPage");
            homeTownProp = ontology.getProperty(RDF_BASE + "hasHomeTown");
            categoryProp = ontology.getProperty(RDF_BASE + "hasCategory");
            geoLatProp = ontology.getProperty(RDF_BASE + "hasGeoLat");
            geoLonProp = ontology.getProperty(RDF_BASE + "hasGeoLon");
            tweetProp = ontology.getProperty(RDF_BASE + "hasTweet");

            albumClas = ontology.getResource(RDF_BASE + "Album");
            voteEventClas = ontology.getResource(RDF_BASE + "VoteEvent");
            userClas = ontology.getResource(RDF_BASE + "User");
            artistClas = ontology.getResource(RDF_BASE + "Artist");
            gigClas = ontology.getResource(RDF_BASE + "Gig");
            venueClas = ontology.getResource(RDF_BASE + "Venue");

        }
    }

    /**
     * String containing the DBPedia service URL.
     */
    public static final String   DBPEDIA_SERVICE       = "http://dbpedia.org/sparql";

    /**
     * String containing the ontology URL.
     */
    public static final String   RDF_BASE              = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#";
    
    /**
     * String containing the URI base.
     */
    public static final String   URI_BASE              = "http://poplar.dcs.shef.ac.uk";
    
    /**
     * String containing the DBPedia associated band URL.
     */
    protected final String       dbpediaAssociatedBand = "http://dbpedia.org/ontology/associatedBand";
    
    /**
     * String containing the DBPedia category URL.
     */
    protected final String       dbpediaCategory       = "http://purl.org/dc/terms/subject";

    /**
     * String containing the DBPedia Genre URL.
     */
    protected final String       dbpediaGenre          = "http://dbpedia.org/ontology/genre";
    
    /**
     * String containing the DBPedia GeoLat URL.
     */
    protected final String       dbpediaGeoLat         = "http://www.w3.org/2003/01/geo/wgs84_pos#lat";

    /**
     * String containing the DBPedia GeoLon URL.
     */
    protected final String       dbpediaGeoLon         = "http://www.w3.org/2003/01/geo/wgs84_pos#long";
    
    /**
     * String containing the DBPedia hometown URL.
     */
    protected final String       dbpediaHomeTown       = "http://dbpedia.org/ontology/hometown";
    
    /**
     * String that holds the DBPedia link.
     */
    protected String             dbpediaLink;
    
    /**
     * String that holds the DBPedia variable name.
     */
    protected final String       dbpediaVAR            = "arg";
    
    /**
     * String that holds the DBPedia wikipedia page.
     */
    protected final String       dbpediaWikiPage       = "http://xmlns.com/foaf/0.1/page";
    
    /**
     * An instance of IntelWebNodes.
     */
    protected IntelWebNodes      nodes;
    
    /**
     * A Jena model that contains an ontology.
     */
    protected Model              ontology;
    
    /**
     * An instance of IntelWebProperties.
     */
    protected IntelWebProperties properties;
    
    /**
     * A string that holds the Url to the xml file.
     */
    protected String             url;

    /**
     * Boolean value determining whether web services are used or not.
     */
    protected boolean            withWebServices;
    
    /**
     * An xml Document.
     */
    protected Document           xml;

    
    /**
     * Class constructor
     * @param ontology Takes a Jena Model.
     * @param xml Takes an xml Document.
     * @param url Takes the url of the xml file.
     * @param withWebServices Boolean value determing whether DBPedia and twitter processing is done or not.
     */
    public RdfBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
        this.withWebServices = withWebServices;

        properties = new IntelWebProperties(ontology);
        nodes = new IntelWebNodes();

        //framework bug workaround
        ARQ.getContext().setTrue(ARQ.useSAX);
    }

    /**
     * This function gets the desciption for the current resource from dbPedia.
     * @param res Takes a Jena resource.
     * @return Returns the description as a String.
     */
    public String dbpediaDescription(Resource res)
    {
        final ResultSet rs = queryDBpedia(res.getURI(), RDFS.label.getURI());
        if (rs.hasNext())
        {
            return rs.next().getLiteral(dbpediaVAR).getString();
        }
        return "";
    }

    /**
     * This function does the xml extraction.
     */
    public final void extract()
    {
        extractXml();
        if (withWebServices)
        {
            extractWebServices();
        }
    }

    /**
     * Abstract method that handles getting of information from dbPedia and twitter.
     */
    public abstract void extractWebServices();

    /**
     * Abstract method that handles the extraction of xml data.
     */
    public abstract void extractXml();

    /**
     * 
     * @return Returns the Jena Model.
     */
    public Model getOntology()
    {
        return ontology;
    }

    /**
     * This function gets the content of a node.
     * @param node Takes in a Node.
     * @return Returns the content of the node as a string.
     */
    protected String getSingleProp(Node node)
    {
        if (node != null)
        {
            return node.getTextContent().trim();
        }
        return "";
    }

    /**
     * This function gets the Uri from the url of the xml file.
     * @return Returns the URI as a string.
     */
    public String getUri()
    {
        return url.substring(0, url.lastIndexOf("/xml"));
    }

    /**
     * This function returns the url of the xml file.
     * @return Returns the URL as a string.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * This function gets the url attributes of a node.
     * @param node Takes in a single Node.
     * @return Returns the url attribute of the node as a string.
     */
    protected String getUrlAttr(Node node)
    {
        if (node != null)
        {
            return URI_BASE + node.getAttributes().getNamedItem("url").getTextContent().trim();
        }
        return "";
    }

    /**
     * 
     * @return Returns the xml file as a Document.
     */
    public Document getXml()
    {
        return xml;
    }

    /**
     * This function queries DBpedia and returns a result set.
     * @param resUri A resource URI.
     * @param dbpediaProp A DBPedia property.
     * @return Returns the result set returned by DBPedia.
     */
    public ResultSet queryDBpedia(String resUri, String dbpediaProp)
    {
        final String query = "SELECT ?" + dbpediaVAR + " WHERE { <" + resUri + "> <" + dbpediaProp + "> ?" + dbpediaVAR + " . }";
        return runQuery(query);
    }

    /**
     * This function returns a list of nodes 
     * @param nodeName A name of a node.
     * @return Returns the list of nodes with the name as provided by the parameter.
     */
    public NodeList queryTag(String nodeName)
    {
        return xml.getElementsByTagName(nodeName);
    }

    /**
     * This function runs a sparcl query.
     * @param queryString Takes in a Query
     * @return Returns the Result set that is returned by the query.
     * @throws QueryExceptionHTTP
     */
    protected ResultSet runQuery(String queryString) throws QueryExceptionHTTP
    {
        final Query query = QueryFactory.create(queryString);
        final QueryExecution qexec = QueryExecutionFactory.sparqlService(DBPEDIA_SERVICE, query);
        final ResultSet results = qexec.execSelect();
        qexec.close();
        return results;
    }
}
