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
	
	public void testGetWebsite() throws XPathExpressionException
	{
		String expectedWebsite = "http://www.franzferdinand.co.uk/";
		String website = artistExtractor.getWebsite();
		assertEquals(expectedWebsite, website);
	}
	
	public void testGetImage() throws XPathExpressionException
	{
		String expectedImage = "http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/franz.jpg";
		String image = artistExtractor.getImage();
		assertEquals(expectedImage,image);
	}
	
	public void testGetBiography() throws XPathExpressionException
	{
		String expectedBiography = "Glasgow's art-damaged rock quartet Franz Ferdinand -- named for the Austro-Hungarian Archduke whose murder sparked World War I -- features bassist Bob Hardy, guitarist Nick McCarthy, drummer Paul Thomson, and singer/guitarist Alex Kapranos. In late 2001, Kapranos and Hardy had begun working on music together when they met McCarthy, a classically trained pianist and double bass player who originally played drums for the group despite no prior experience as a drummer. The trio had been rehearsing at McCarthy's house for a while when they met and started playing with Thomson, a former drummer for the Yummy Fur who felt like playing guitar instead. Eventually, McCarthy and Thomson switched to guitar and drums, and the band switched practice spaces, stumbling upon an abandoned warehouse that they named the Chateau. The Chateau became Franz Ferdinand's headquarters, where they rehearsed and held rave-like events incorporating music and art (Hardy graduated from the Glasgow School of Art, and Thomson also posed as a life model there). The bandmembers needed a new rehearsal space once their illicit art parties were discovered by the police, and they found one in a Victorian courthouse and jail. By summer 2002, they recorded an EP's worth of material that they intended to release themselves, but word of mouth about the band spread and Franz Ferdinand signed to Domino in the summer of 2003. The group's EP Darts of Pleasure, which led some to label Franz Ferdinand \"the Scottish Interpol,\" was released that fall, and the band spent the rest of the year supporting groups such as Hot Hot Heat and Interpol itself. Franz Ferdinand's second single, Take Me Out, arrived in early 2004. The single propelled them to greater popularity in the UK and laid the groundwork for the band's debut album. Franz Ferdinand was released in February 2004 in the UK and a month later Stateside. Franz Ferdinand's success followed them across the pond; \"Take Me Out\" became a sizeable modern rock hit, in part thanks to the song's cutting-edge video, which earned the Breakthrough Video award at that year's MTV Music Video Awards. The group's momentum continued with the release of the Michael single and their Mercury Prize win over such artists as the Streets, Basement Jaxx and Keane. Franz Ferdinand released their second album, You Could Have it So Much Better ... with Franz Ferdinand in fall 2005. ~ Heather Phares, All Music Guide";
		String biography = artistExtractor.getBiography();
		assertEquals(expectedBiography, biography);
	}
}
