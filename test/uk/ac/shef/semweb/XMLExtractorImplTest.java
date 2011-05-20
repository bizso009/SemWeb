package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;


public class XMLExtractorImplTest extends TestCase {

    private XMLExtractorImpl extractor;
    
    @Override
    @Before
    public void setUp(){
    	this.extractor = new XMLExtractorImpl();
    }
    
    public void testReadFile() throws FileNotFoundException
    {
		List<String> urls = this.extractor.readFile(XMLExtractorImpl.INPUT_PATH);
		assertFalse(urls.isEmpty());
		assertFalse(urls.get(0).isEmpty());	
    }
    
    public void testOpenUrl() throws ClientProtocolException, IOException
    {
		String url = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
		HttpEntity entity = this.extractor.openUrl(url);
		assertNotNull(entity);
    }
    
    public void testIsXML() throws ClientProtocolException, IOException
    {
		String notXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
		assertFalse(this.extractor.isXML(this.extractor.openUrl(notXmlUrl)));
		
		String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
		assertTrue(this.extractor.isXML(this.extractor.openUrl(isXmlUrl)));
	
    }
    
    public void testParseRdf() throws IllegalStateException, ClientProtocolException, IOException
    {
		Model model = this.extractor.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
		assertNotNull(model);	
    }
    
    public void testExtract(){
        this.extractor.extract();
        assertTrue(new File(XMLExtractorImpl.OUTPUT_PATH).exists());
    }
    
    /*
    public void testGetClassResources() throws IllegalStateException, ClientProtocolException, IOException
    {
		Model model = this.extractor.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
		assertFalse(this.extractor.getClassResources(model).isEmpty());
    }
    
    public void testMatchClassWithUrl() throws IllegalStateException, ClientProtocolException, IOException
    {
		String artistUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#Artist";
		String shouldMatch = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/415/xml";
		String shouldNotMatch = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/558/xml";
	
		Model model = this.extractor.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
		Resource res = model.getResource(artistUri);
		assertTrue(this.extractor.matchClassWithUrl(res,shouldMatch));
		assertFalse(this.extractor.matchClassWithUrl(res,shouldNotMatch));
    }
    
    public void testGetPropertiesByClass() throws IllegalStateException, ClientProtocolException, IOException
    {
		String artistUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#Artist";
		Model model = this.extractor.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
		Resource res = model.getResource(artistUri);
		assertFalse(this.extractor.getPropertiesByClass(res).isEmpty());
    }*/
    
    public void testLoadXml() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException
    {
		String xmlPath = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/415/xml";
		InputStream is = this.extractor.openUrl(xmlPath).getContent();
		Document doc = this.extractor.loadXml(is);
		assertNotNull(doc);
    }
}
