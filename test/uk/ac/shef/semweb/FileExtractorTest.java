package uk.ac.shef.semweb;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;


public class FileExtractorTest extends TestCase {

    private FileExtractor extractor;
    
   public void testQuery() throws XPathExpressionException, IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException{
	   XMLExtractorImpl impl = new XMLExtractorImpl();
       Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
       String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
       String url = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
       Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
<<<<<<< HEAD
       this.extractor = new UserExtractor(model,doc, isXmlUrl);
       assertEquals("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10",this.extractor.getUri());
=======
       this.extractor = new UserExtractor(model,doc, url);
>>>>>>> fe92b61c59acd8efa9d62558ba2d9d8bacf9f1c5
       assertNotNull(this.extractor.query("//voteEvent"));
   }
    
    
}
