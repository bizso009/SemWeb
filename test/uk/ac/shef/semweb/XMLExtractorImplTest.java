package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;


public class XMLExtractorImplTest extends TestCase {

    private XMLExtractorImpl extractor;
    
    @Override
    @Before
    public void setUp(){
	this.extractor = new XMLExtractorImpl();
    }
    
    public void testGetAllUrls() throws FileNotFoundException{
	List<String> urls = this.extractor.getAllUrls();
	assertFalse(urls.isEmpty());
    }
    
}
