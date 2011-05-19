package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import sun.security.krb5.internal.Ticket;

import com.hp.hpl.jena.rdf.model.Model;

public class ArtistExtractor extends FileExtractor 
{
	
    public ArtistExtractor(Model ontology, Document xml, String url) 
    {
    	super(ontology, xml, url);
    }

    @Override
    public void extract() 
    {
    	
    }
    
    public String getArtistName() throws XPathExpressionException
    {
    	String nameQuery = "//title";
		NodeList nodes = this.query(nameQuery);
		String title = "";
		for(int x=0; x<nodes.getLength();x++)
		{
			title = nodes.item(x).toString();
		}
		return title;
    }

}
