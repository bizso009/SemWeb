package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;


public class UrlFileReaderTest extends TestCase {

    private UrlFileReader urlFileReader;
    
    @Override
    @Before
    public void setUp(){
	this.urlFileReader = new UrlFileReader();
    }
    public void testReadFile() throws FileNotFoundException{
	List<String> urls = this.urlFileReader.readFile(UrlFileReader.INPUT_PATH);
	assertFalse(urls.isEmpty());
	assertFalse(urls.get(0).isEmpty());
	
    }
}
