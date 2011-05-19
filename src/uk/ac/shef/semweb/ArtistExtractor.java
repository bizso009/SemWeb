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
		return getSingleItem(nameQuery);
    }
    

	public String[] getAlbums() throws XPathExpressionException
    {
    	String albumQuery = "//album";
    	return getListItem(albumQuery);
    }
	
	public String getWebsite() throws XPathExpressionException
	{
		String websiteQuery = "//website";
		return getSingleItem(websiteQuery);
	}
	
	public String getImage() throws XPathExpressionException
	{
		String imageQuery = "//image";
		return getSingleItem(imageQuery);
	}
	
	public String getSingleItem(String query) throws XPathExpressionException
	{
		Node node = this.query(query).item(0);
		String item = "";
		if(node != null)
		{
			item = node.getTextContent();
		}
		return item;
	}
	
	public String[] getListItem(String query) throws XPathExpressionException
	{
		NodeList nodes = this.query(query);
    	ArrayList<String> itemList = new ArrayList<String>();
    	for(int x=0; x<nodes.getLength();x++)
    	{
    		if(nodes.item(x)!=null)
    		{
    			itemList.add(nodes.item(x).getTextContent());
    		}
    	}
    	String[] items = new String[itemList.size()];
    	itemList.toArray(items);
		return items;    	
	}
}
