package uk.ac.shef.semweb;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class FileExtractor
{
    protected static final String BASE = "http://poplar.dcs.shef.ac.uk";
    protected Model ontology;
    protected Document xml;
    protected String   url;

    public final Property titleProp = this.ontology.getProperty("#hasTitle");
    public final Property genreProp = this.ontology.getProperty("#hasGenre");
    public final Property imageProp = this.ontology.getProperty("#hasImage");
    public final Property trackProp = this.ontology.getProperty("#hasTrack");
    public final Property usernameProp = this.ontology.getProperty("#hasUsername");
    public final Property gigProp = this.ontology.getProperty("#hasGig");
    public final Property websiteProp = this.ontology.getProperty("#hasWebsite");
    public final Property nameProp = this.ontology.getProperty("#hasName");
    public final Property descriptionProp = this.ontology.getProperty("#hasDesription");
    public final Property voteProp = this.ontology.getProperty("#hasVote");
    public final Property biographyProp = this.ontology.getProperty("#hasBiography");
    public final Property artistProp = this.ontology.getProperty("#hasArtist");
    public final Property dateProp = this.ontology.getProperty("#hasDate");
    
    public final Resource albumClas = this.ontology.getResource("#Album");
    public final Resource voteEventClas = this.ontology.getResource("#VoteEvent");
    public final Resource userClas = this.ontology.getResource("#User");
    public final Resource artistClas = this.ontology.getResource("#Artist");
    public final Resource gigClas = this.ontology.getResource("#Gig");
    public final Resource venueClas = this.ontology.getResource("#Venue");
    
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
    
    public FileExtractor(Model ontology, Document xml, String url) throws XPathExpressionException
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
        
        this.titleNode = query("//title").item(0);
        this.genreNode = query("//genre").item(0);
        this.imageNode = query("//image").item(0);
        this.websiteNode = query("//website").item(0);
        this.usernameNode = query("//twitterID").item(0);
        this.nameNode = query("//name").item(0);
        this.biographyNode = query("//biography").item(0);
        this.descriptionNode = query("//description").item(0);
        this.artistNode = query("//artist").item(0);
        this.dateNode = query("//date").item(0);                
        
        this.trackNodes = query("//track"); 
        this.gigNodes = query("//gig"); 
        this.voteEventNodes = query("//voteEvent"); 
        this.albumNodes = query("//album"); 
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
            return node.getTextContent();
        }
        return "";
    }
    protected String getUrlAttr(Node node){
        if (node != null){
            return BASE + node.getAttributes().getNamedItem("url").getTextContent();
        }
        return "";
    }

   
    
//FIXME validate rdf!!!!
    public abstract void extract() throws XPathExpressionException;

    public String getUri(){
        return this.url.substring(0,this.url.lastIndexOf("/xml"));
    }
    public NodeList query(String xPath) throws XPathExpressionException
    {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(xPath);


        Object result = expr.evaluate(this.xml, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes;
    }
}
