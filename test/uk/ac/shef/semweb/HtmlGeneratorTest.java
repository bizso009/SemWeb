package uk.ac.shef.semweb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;

public class HtmlGeneratorTest extends TestCase
{

    private HtmlGenerator generator;
    private Extractor ex;
    private Model model;

    @Override
	public void setUp(){
    	this.ex = new Extractor();
    	try
        {
            model = this.ex.readRdf(new FileInputStream("output/DBPediaTriples.rdf"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void testGenerateArtists() throws SAXException, IOException, ParserConfigurationException, TransformerException{
        this.generator = new ArtistGenerator(model);

        this.generator.generate();
        //artist/367
        InputStream artistFile = new FileInputStream("output/website/Skid_Row.html");
        Document artistXml = this.ex.readXml(artistFile);
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/skidrow.jpg",generator.getElementById(artistXml, "image").getAttribute("src"));
        assertTrue(2<=artistXml.getElementsByTagName("span").getLength());
        assertTrue(2<generator.getElementById(artistXml, "tweets").getElementsByTagName("li").getLength());
    }
    public void testGenerateVenues() throws SAXException, IOException, ParserConfigurationException, TransformerException{
        this.generator = new VenueGenerator(model);

        this.generator.generate();
        //venue/328/
        InputStream venueFile = new FileInputStream("output/website/Botanical_Gardens.html");
        Document venueXml = this.ex.readXml(venueFile);
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/lollapaloozacrowd.jpg",generator.getElementById(venueXml, "image").getAttribute("src"));
    }
    public void testGenerateIndex() throws SAXException, IOException, ParserConfigurationException, TransformerException{
        this.generator = new IndexGenerator(model);

        this.generator.generate();
        //venue/328/
        InputStream indexFile = new FileInputStream("output/website/index.html");
        Document indexXml = this.ex.readXml(indexFile);
        assertEquals(13,generator.getElementById(indexXml, "artists").getElementsByTagName("li").getLength());
        assertEquals(8,generator.getElementById(indexXml, "venues").getElementsByTagName("li").getLength());
    }
   
}
