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
    
    public FileExtractor(Model ontology, Document xml, String url) throws XPathExpressionException
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
        
        this.titleProp = this.ontology.getProperty("#hasTitle");
        this.genreProp = this.ontology.getProperty("#hasGenre");
        this.imageProp = this.ontology.getProperty("#hasImage");
        this.trackProp = this.ontology.getProperty("#hasTrack");
        this.usernameProp = this.ontology.getProperty("#hasUsername");
        this.gigProp = this.ontology.getProperty("#hasGig");
        this.websiteProp = this.ontology.getProperty("#hasWebsite");
        this.nameProp = this.ontology.getProperty("#hasName");
        this.descriptionProp = this.ontology.getProperty("#hasDesription");
        this.voteProp = this.ontology.getProperty("#hasVote");
        this.biographyProp = this.ontology.getProperty("#hasBiography");
        this.artistProp = this.ontology.getProperty("#hasArtist");
        this.dateProp = this.ontology.getProperty("#hasDate");
        
        this.albumClas = this.ontology.getResource("#Album");
        this.voteEventClas = this.ontology.getResource("#VoteEvent");
        this.userClas = this.ontology.getResource("#User");
        this.artistClas = this.ontology.getResource("#Artist");
        this.gigClas = this.ontology.getResource("#Gig");
        this.venueClas = this.ontology.getResource("#Venue");
        
        
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
            return node.getTextContent().trim();
        }
        return "";
    }
    protected String getUrlAttr(Node node){
        if (node != null){
            return BASE + node.getAttributes().getNamedItem("url").getTextContent().trim();
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
