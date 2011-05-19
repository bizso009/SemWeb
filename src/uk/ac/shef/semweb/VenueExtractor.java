package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class VenueExtractor extends FileExtractor 
{

    public VenueExtractor(Model ontology, Document xml, String url) 
    {
    	super(ontology, xml, url);
    }

    @Override
    public void extract() throws XPathExpressionException 
    {
        //create resource
        Resource res = this.ontology.createResource(getUri());
        //add class
        Resource clas = this.ontology.getResource("#Venue");
        res.addProperty(RDF.type, clas);
        
        //add gigs
        NodeList gigNodeList = query("//gig");
        for (int i=0; i<gigNodeList.getLength(); i++){
            Node gigNode =  gigNodeList.item(i);
            if (gigNode != null){
                String gigUri = "http://poplar.dcs.shef.ac.uk" + gigNode.getAttributes().getNamedItem("url").getTextContent();
                Resource gigRes = this.ontology.createResource(gigUri);
                Resource gigClas = this.ontology.getResource("#Gig");
                gigRes.addProperty(RDF.type, gigClas);
                
                String gigTitle = gigNode.getTextContent();
                Property gigTitleProp = this.ontology.getProperty("#hasTitle");
                gigRes.addProperty(gigTitleProp, gigTitle);
                
                Property gigProperty = this.ontology.getProperty("#hasGig");
                res.addProperty(gigProperty, gigRes);
            }
        }
        
        //add website
        Node websiteNode = query("//website").item(0);
        if (websiteNode !=null){
            String website = websiteNode.getTextContent();
            Property websiteProp = this.ontology.getProperty("#hasWebsite");
            res.addProperty(websiteProp, website);            
        }
        
        //add name
        Node nameNode = query("//name").item(0);
        if (nameNode !=null){
            String name = nameNode.getTextContent();
            Property nameProp = this.ontology.getProperty("#hasName");
            res.addProperty(nameProp, name);            
        }
        
        //add description
        Node descriptionNode = query("//description").item(0);
        if (descriptionNode !=null){
            String description = descriptionNode.getTextContent();
            Property descriptionProp = this.ontology.getProperty("#hasDescription");
            res.addProperty(descriptionProp, description);            
        }
        
        //add image
        Node imageNode = query("//image").item(0);
        if (imageNode !=null){
            String image = imageNode.getTextContent();
            Property imageProp = this.ontology.getProperty("#hasImage");
            res.addProperty(imageProp, image);            
        }
        
        //TODO get from dbpedia
        // has wikiPage, has category, has geoLon, has geoLat
    }

}
