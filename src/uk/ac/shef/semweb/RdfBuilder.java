package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class RdfBuilder
{
    protected static final String URI_BASE = "http://poplar.dcs.shef.ac.uk";
    public static final String RDF_BASE = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf";
    protected Model ontology;
    protected Document xml;
    protected String   url;
    protected boolean withWebServices;

    
    public final Property titleProp;
    public final Property genreProp;
    public final Property imageProp;
    public final Property trackProp;
    public final Property usernameProp;
    public final Property gigProp;
    public final Property websiteProp;
    public final Property nameProp;
    public final Property descriptionProp;
    public final Property voteProp;
    public final Property biographyProp;
    public final Property artistProp;
    public final Property dateProp;
    public final Property voteEventProp;
    public final Property albumProp;
    
    public final Resource albumClas;
    public final Resource voteEventClas;
    public final Resource userClas;
    public final Resource artistClas;
    public final Resource gigClas;
    public final Resource venueClas;
    
    public final Node titleNode;
    public final Node genreNode;
    public final Node imageNode;
    public final Node usernameNode;
    public final Node websiteNode;
    public final Node nameNode;
    public final Node descriptionNode;
    public final Node biographyNode;
    public final Node artistNode;
    public final Node dateNode;
    
    public final NodeList trackNodes;
    public final NodeList gigNodes;
    public final NodeList voteEventNodes;
    public final NodeList albumNodes;
    
    public RdfBuilder(Model ontology, Document xml, String url, boolean withWebServices) 
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
        this.withWebServices = withWebServices;
        
        this.titleProp = this.ontology.getProperty(RDF_BASE + "#hasTitle");
        this.genreProp = this.ontology.getProperty(RDF_BASE + "#hasGenre");
        this.imageProp = this.ontology.getProperty(RDF_BASE + "#hasImage");
        this.trackProp = this.ontology.getProperty(RDF_BASE + "#hasTrack");
        this.usernameProp = this.ontology.getProperty(RDF_BASE + "#hasUsername");
        this.gigProp = this.ontology.getProperty(RDF_BASE + "#hasGig");
        this.websiteProp = this.ontology.getProperty(RDF_BASE + "#hasWebsite");
        this.nameProp = this.ontology.getProperty(RDF_BASE + "#hasName");
        this.descriptionProp = this.ontology.getProperty(RDF_BASE + "#hasDesription");
        this.voteProp = this.ontology.getProperty(RDF_BASE + "#hasVote");
        this.biographyProp = this.ontology.getProperty(RDF_BASE + "#hasBiography");
        this.artistProp = this.ontology.getProperty(RDF_BASE + "#hasArtist");
        this.dateProp = this.ontology.getProperty(RDF_BASE + "#hasDate");
        this.voteEventProp = this.ontology.getProperty(RDF_BASE + "#hasVoteEvent");
        this.albumProp = this.ontology.getProperty(RDF_BASE + "#hasAlbum");
                
        this.albumClas = this.ontology.getResource(RDF_BASE + "#Album");
        this.voteEventClas = this.ontology.getResource(RDF_BASE + "#VoteEvent");
        this.userClas = this.ontology.getResource(RDF_BASE + "#User");
        this.artistClas = this.ontology.getResource(RDF_BASE + "#Artist");
        this.gigClas = this.ontology.getResource(RDF_BASE + "#Gig");
        this.venueClas = this.ontology.getResource(RDF_BASE + "#Venue");
        
        
        this.titleNode = query("title").item(0);
        this.genreNode = query("genre").item(0);
        this.imageNode = query("image").item(0);
        this.websiteNode = query("website").item(0);
        this.usernameNode = query("twitterID").item(0);
        this.nameNode = query("name").item(0);
        this.biographyNode = query("biography").item(0);
        this.descriptionNode = query("description").item(0);
        this.artistNode = query("artist").item(0);
        this.dateNode = query("date").item(0);                
        
        this.trackNodes = query("track"); 
        this.gigNodes = query("gig"); 
        this.voteEventNodes = query("voteEvent"); 
        this.albumNodes = query("album"); 
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

    protected String getSingleProp(Node node){
        if (node != null){
            return node.getTextContent().trim();
        }
        return "";
    }
    protected String getUrlAttr(Node node){
        if (node != null){
            return URI_BASE + node.getAttributes().getNamedItem("url").getTextContent().trim();
        }
        return "";
    }

   
    
//FIXME validate rdf!!!!
    public abstract void extractXml(); 

    public abstract void extractWebServices();
    
    public String getUri(){
        return this.url.substring(0,this.url.lastIndexOf("/xml"));
    }
    public NodeList query(String nodeName)
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
        if (this.withWebServices){
            extractWebServices();
        }
        
    }
}
