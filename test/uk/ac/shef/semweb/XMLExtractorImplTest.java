package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;

import com.hp.hpl.jena.rdf.model.Model;


public class XMLExtractorImplTest extends TestCase {

    private XMLExtractorImpl extractor;
    
    @Override
    @Before
    public void setUp(){
	this.extractor = new XMLExtractorImpl();
    }
    
    public void testReadFile() throws FileNotFoundException{
	List<String> urls = this.extractor.readFile(XMLExtractorImpl.INPUT_PATH);
	assertFalse(urls.isEmpty());
	assertFalse(urls.get(0).isEmpty());
	
    }
    public void testOpenUrl() throws ClientProtocolException, IOException{
	String url = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
	HttpEntity entity = this.extractor.openUrl(url);
	assertNotNull(entity);
    }
    public void testIsXML() throws ClientProtocolException, IOException{
	String notXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
	assertFalse(this.extractor.isXML(this.extractor.openUrl(notXmlUrl)));
	
	String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
	assertTrue(this.extractor.isXML(this.extractor.openUrl(isXmlUrl)));
	
    }
    public void testParseRdf() throws IllegalStateException, ClientProtocolException, IOException{
	Model model = this.extractor.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
	model.write(System.out, "N-TRIPLE");
	assertNotNull(model);
	
    }
    
}
