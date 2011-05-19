package uk.ac.shef.semweb;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;

import junit.framework.TestCase;


public class ArtistExtractorTest extends TestCase
{
	private ArtistExtractor artistExtractor;
	
	@Override
	@Before
	public void setUp()
	{
		String ontologyUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf";
		String xmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/471/xml";
		XMLExtractorImpl xmlExtractorImpl = new XMLExtractorImpl();
		try 
		{
			Model model = xmlExtractorImpl.parseRdf(ontologyUrl);
			try 
			{
				Document document = xmlExtractorImpl.loadXml(xmlExtractorImpl.openUrl(xmlUrl).getContent());
				artistExtractor = new ArtistExtractor(model, document, xmlUrl);		
			} catch (SAXException e) 
			{
				e.printStackTrace();
			} catch (ParserConfigurationException e) 
			{
				e.printStackTrace();
			}
		} catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void testGetArtistName() throws XPathExpressionException
	{
		String name;
		name = artistExtractor.getArtistName();
		assertEquals(" Franz Ferdinand", name);
	}
	
	public void testGetAlbums() throws XPathExpressionException
	{
		String[] expectedAlbums = {"Franz Ferdinand ", "You Could Have It So Much Better"};
		String[] albums = artistExtractor.getAlbums();
		assertEquals(albums[0], expectedAlbums[0]);
		assertEquals(albums[1], expectedAlbums[1]);
		
	}
	
}
