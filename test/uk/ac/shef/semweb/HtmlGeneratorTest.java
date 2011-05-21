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

    @Override
	public void setUp(){
    	this.ex = new Extractor();
    	try
        {
            Model model = this.ex.readRdf(new FileInputStream("output/DBPediaTriples.rdf"));
            this.generator = new ArtistGenerator(model);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void testGenerateArtists() throws SAXException, IOException, ParserConfigurationException, TransformerException{
        this.generator.generate();
        //artist/367
        InputStream artistFile = new FileInputStream("output/website/Skid_Row.html");
        Document artistXml = this.ex.readXml(artistFile);
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/skidrow.jpg",generator.getElementById(artistXml, "image").getAttribute("src"));
        assertTrue(2<=artistXml.getElementsByTagName("span").getLength());
        assertTrue(2<generator.getElementById(artistXml, "tweets").getElementsByTagName("li").getLength());
    }/*
    public void testGenerateVenues() throws SAXException, IOException, ParserConfigurationException, TransformerException{
        this.generator.generateArtists();
        //artist/367
        InputStream artistFile = new FileInputStream("output/website/Skid_Row.html");
        Document artistXml = this.ex.readXml(artistFile);
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/skidrow.jpg",generator.getElementById(artistXml, "image").getAttribute("src"));
    }*/
   
}
