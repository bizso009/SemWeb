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
        Resource clas = this.ontology.getResource("#Album");
        res.addProperty(RDF.type, clas);
     
        //get title
        Node titleNode = query("//title").item(0);
        if (titleNode !=null){
            String title = titleNode.getTextContent();
            Property titleProp = this.ontology.getProperty("#hasTitle");
            res.addProperty(titleProp, title);            
        }
        
        Node genreNode = query("//genre").item(0);
        if (genreNode !=null){
            String genre = genreNode.getTextContent();
            Property genreProp = this.ontology.getProperty("#hasGenre");
            res.addProperty(genreProp, genre);            
        }
       
        Node imageNode = query("//image").item(0);
        if (imageNode !=null){
            String image = imageNode.getTextContent();
            Property imageProp = this.ontology.getProperty("#hasImage");
            res.addProperty(imageProp, image);            
        }
        
        NodeList trackNodeList = query("//track");
        for (int i=0; i<trackNodeList.getLength(); i++){
            Node trackNode =  trackNodeList.item(i);
            if (trackNode != null){
                String track = trackNode.getTextContent();
                Property trackProp = this.ontology.getProperty("#hasTrack");
                res.addProperty(trackProp, track);
            }
        }
    }

}
