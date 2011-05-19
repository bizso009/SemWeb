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


public class UserExtractorTest extends TestCase {

    private UserExtractor extractor;
    
   public void testExtract() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{
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
    
    
}