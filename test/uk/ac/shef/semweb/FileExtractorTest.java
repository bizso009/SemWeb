package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;


public class FileExtractorTest extends TestCase {

    private FileExtractor extractor;
    
   public void testQuery() throws XPathExpressionException, IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException{
	   XMLExtractorImpl impl = new XMLExtractorImpl();
       Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
       String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
       String url = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
       Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
       this.extractor = new UserExtractor(model,doc, url);
       assertNotNull(this.extractor.query("//voteEvent"));
   }
    
    
}
