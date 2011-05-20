package uk.ac.shef.semweb;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class FileExtractorTest extends TestCase
{

    private FileExtractor extractor;

    public void testQuery() throws XPathExpressionException, IllegalStateException, ClientProtocolException, SAXException, IOException,
            ParserConfigurationException
    {
        XMLExtractorImpl impl = new XMLExtractorImpl();
        Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
        Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
        this.extractor = new UserExtractor(model, doc, isXmlUrl);
        assertEquals("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10", this.extractor.getUri());
        assertNotNull(this.extractor.query("//voteEvent"));
    }
    public void testAlbum() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{
        XMLExtractorImpl impl = new XMLExtractorImpl();
        Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());
        
        this.extractor = new AlbumExtractor(model,doc,testUrl);
        this.extractor.extract();
        
        Resource res = model.getResource(testUri);
        assertNotNull(res);
       
        assertEquals("Yesterdays", res.getProperty(this.extractor.titleProp).getString());
        assertEquals("Rock", res.getProperty(this.extractor.genreProp).getString());
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/yesterdays.jpg", res.getProperty(this.extractor.imageProp).getString());
        assertTrue(res.hasProperty(this.extractor.trackProp));
    }
    
    public void testUser() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{
        XMLExtractorImpl impl = new XMLExtractorImpl();
        Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9/xml";
        Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
        this.extractor = new UserExtractor(model,doc,isXmlUrl);
        this.extractor.extract();
        Resource res = model.getResource("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9");
        assertNotNull(res);
        Property p = model.getProperty("#hasVoteEvent");
        res.getProperty(p).getResource();
    }

    
    public void testVenue() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{
        XMLExtractorImpl impl = new XMLExtractorImpl();
        Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328/xml";
        Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
        this.extractor = new VenueExtractor(model,doc,isXmlUrl);
        this.extractor.extract();
        Resource res = model.getResource("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328");
        assertNotNull(res);
        Property p = model.getProperty("#hasGig");
        res.getProperty(p).getResource();
    }
}
