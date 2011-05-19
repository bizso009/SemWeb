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


public class VenueExtractorTest extends TestCase {

    private AlbumExtractor extractor;
    
   public void testExtract() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{
       XMLExtractorImpl impl = new XMLExtractorImpl();
       Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
       String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
       Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
       this.extractor = new AlbumExtractor(model,doc,isXmlUrl);
       this.extractor.extract();
       Resource res = model.getResource("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323");
       assertNotNull(res);
       Property p = model.getProperty("#hasGenre");
       assertEquals("Rock", res.getProperty(p).getString());
   }
    
    
}
