package uk.ac.shef.semweb;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
		Node node = this.query(nameQuery).item(0);
		String title = "";
		if(node != null)
		{
			title = node.getTextContent();
		}
		return title;
    }
    

	public String[] getAlbums() throws XPathExpressionException
    {
    	String albumQuery = "//album";
    	NodeList nodes = this.query(albumQuery);
    	ArrayList<String> albumsList = new ArrayList<String>();
    	for(int x=0; x<nodes.getLength();x++)
    	{
    		if(nodes.item(x)!=null)
    		{
    			albumsList.add(nodes.item(x).getTextContent());
    		}
    	}
    	String[] albums = new String[albumsList.size()];
    	albumsList.toArray(albums);
		return albums;    	
    }
}
