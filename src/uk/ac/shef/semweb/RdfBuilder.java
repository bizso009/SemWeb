package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public abstract class RdfBuilder
{
    protected static final String URI_BASE              = "http://poplar.dcs.shef.ac.uk";
    public static final String    RDF_BASE              = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#";
    public static final String    DBPEDIA_SERVICE       = "http://dbpedia.org/sparql";

    protected Model               ontology;
    protected Document            xml;
    protected String              url;
    protected boolean             withWebServices;

    //TODO push fields down to subclasses
    protected final Property      titleProp;
    protected final Property      genreProp;
    protected final Property      imageProp;
    protected final Property      trackProp;
    protected final Property      usernameProp;
    protected final Property      gigProp;
    protected final Property      websiteProp;
    protected final Property      nameProp;
    protected final Property      descriptionProp;
    protected final Property      voteProp;
    protected final Property      biographyProp;
    protected final Property      artistProp;
    protected final Property      dateProp;
    protected final Property      voteEventProp;
    protected final Property      albumProp;
    protected final Property      associatedBandProp;
    protected final Property      wikiPageProp;
    protected final Property      homeTownProp;
    protected final Property      categoryProp;
    protected final Property      geoLatProp;
    protected final Property      geoLonProp;

    protected final Resource      albumClas;
    protected final Resource      voteEventClas;
    protected final Resource      userClas;
    protected final Resource      artistClas;
    protected final Resource      gigClas;
    protected final Resource      venueClas;

    protected final Node          titleNode;
    protected final Node          genreNode;
    protected final Node          imageNode;
    protected final Node          usernameNode;
    protected final Node          websiteNode;
    protected final Node          nameNode;
    protected final Node          descriptionNode;
    protected final Node          biographyNode;
    protected final Node          artistNode;
    protected final Node          dateNode;
    protected final Node          dbpediaNode;

    protected final NodeList      trackNodes;
    protected final NodeList      gigNodes;
    protected final NodeList      voteEventNodes;
    protected final NodeList      albumNodes;

    protected final String        dbpediaGenre          = "http://dbpedia.org/ontology/genre";
    protected final String        dbpediaAssociatedBand = "http://dbpedia.org/ontology/associatedBand";
    protected final String        dbpediaWikiPage       = "http://dbpedia.org/ontology/wikiPageExternalLink";
    protected final String        dbpediaHomeTown       = "http://dbpedia.org/ontology/hometown";
    protected final String        dbpediaCategory       = "http://purl.org/dc/terms/subject";
    protected final String        dbpediaGeoLat         = "http://www.w3.org/2003/01/geo/wgs84_pos#lat";
    protected final String        dbpediaGeoLon         = "http://www.w3.org/2003/01/geo/wgs84_pos#long";
    protected final String        dbpediaVAR            = "arg";

    public RdfBuilder(Model ontology, Document xml, String url, boolean withWebServices)
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
        this.withWebServices = withWebServices;

        this.titleProp = this.ontology.getProperty(RDF_BASE + "hasTitle");
        this.genreProp = this.ontology.getProperty(RDF_BASE + "hasGenre");
        this.imageProp = this.ontology.getProperty(RDF_BASE + "hasImage");
        this.trackProp = this.ontology.getProperty(RDF_BASE + "hasTrack");
        this.usernameProp = this.ontology.getProperty(RDF_BASE + "hasUsername");
        this.gigProp = this.ontology.getProperty(RDF_BASE + "hasGig");
        this.websiteProp = this.ontology.getProperty(RDF_BASE + "hasWebsite");
        this.nameProp = this.ontology.getProperty(RDF_BASE + "hasName");
        this.descriptionProp = this.ontology.getProperty(RDF_BASE + "hasDesription");
        this.voteProp = this.ontology.getProperty(RDF_BASE + "hasVote");
        this.biographyProp = this.ontology.getProperty(RDF_BASE + "hasBiography");
        this.artistProp = this.ontology.getProperty(RDF_BASE + "hasArtist");
        this.dateProp = this.ontology.getProperty(RDF_BASE + "hasDate");
        this.voteEventProp = this.ontology.getProperty(RDF_BASE + "hasVoteEvent");
        this.albumProp = this.ontology.getProperty(RDF_BASE + "hasAlbum");
        this.associatedBandProp = this.ontology.getProperty(RDF_BASE + "hasAssociatedBand");
        this.wikiPageProp = this.ontology.getProperty(RDF_BASE + "hasWikiPage");
        this.homeTownProp = this.ontology.getProperty(RDF_BASE + "hasHomeTown");
        this.categoryProp = this.ontology.getProperty(RDF_BASE + "hasCategory");
        this.geoLatProp = this.ontology.getProperty(RDF_BASE + "hasGeoLat");
        this.geoLonProp = this.ontology.getProperty(RDF_BASE + "hasGeoLon");

        this.albumClas = this.ontology.getResource(RDF_BASE + "Album");
        this.voteEventClas = this.ontology.getResource(RDF_BASE + "VoteEvent");
        this.userClas = this.ontology.getResource(RDF_BASE + "User");
        this.artistClas = this.ontology.getResource(RDF_BASE + "Artist");
        this.gigClas = this.ontology.getResource(RDF_BASE + "Gig");
        this.venueClas = this.ontology.getResource(RDF_BASE + "Venue");

        this.titleNode = queryTag("title").item(0);
        this.genreNode = queryTag("genre").item(0);
        this.imageNode = queryTag("image").item(0);
        this.websiteNode = queryTag("website").item(0);
        this.usernameNode = queryTag("twitterID").item(0);
        this.nameNode = queryTag("name").item(0);
        this.biographyNode = queryTag("biography").item(0);
        this.descriptionNode = queryTag("description").item(0);
        this.artistNode = queryTag("artist").item(0);
        this.dateNode = queryTag("date").item(0);
        this.dbpediaNode = queryTag("dbpedia").item(0);

        this.trackNodes = queryTag("track");
        this.gigNodes = queryTag("gig");
        this.voteEventNodes = queryTag("voteEvent");
        this.albumNodes = queryTag("album");
    }

    public Model getOntology()
    {
        return this.ontology;
    }

    public Document getXml()
    {
        return this.xml;
    }

    public String getUrl()
    {
        return this.url;
    }

    protected String getSingleProp(Node node)
    {
        if (node != null)
        {
            return node.getTextContent().trim();
        }
        return "";
    }

    protected String getUrlAttr(Node node)
    {
        if (node != null)
        {
            return URI_BASE + node.getAttributes().getNamedItem("url").getTextContent().trim();
        }
        return "";
    }

    public ResultSet queryDBpedia(String res, String dbpediaProp)
    {
        String query = "SELECT ?"+this.dbpediaVAR+" WHERE { <" + res + "> <" + dbpediaProp + "> ?"+this.dbpediaVAR+" . }";
        return runQuery(query);
    }
    
    public String dbpediaDescription(Resource res){
        ResultSet rs = queryDBpedia(res.getURI(),RDFS.label.getURI());
        if (rs.hasNext()){
            return rs.next().getLiteral(this.dbpediaVAR).getString();
        }
        return "";
    }
    
    protected ResultSet runQuery(String queryString) throws QueryExceptionHTTP
    {
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(DBPEDIA_SERVICE, query);
        ResultSet results = qexec.execSelect();
        qexec.close();
        return results;
    }
//TODO implement interfaces
    //FIXME validate rdf!!!!
    public abstract void extractXml();

    public abstract void extractWebServices();

    public String getUri()
    {
        return this.url.substring(0, this.url.lastIndexOf("/xml"));
    }

    public NodeList queryTag(String nodeName)
    {
        return this.xml.getElementsByTagName(nodeName);
        /*
           XPathFactory factory = XPathFactory.newInstance();
           XPath xpath = factory.newXPath();
           XPathExpression expr = xpath.compile(xPath);


           Object result = expr.evaluate(this.xml, XPathConstants.NODESET);
           NodeList nodes = (NodeList) result;
           return nodes;
           */
    }

    public final void extract()
    {
        extractXml();
        if (this.withWebServices)
        {
            extractWebServices();
        }

    }
}
