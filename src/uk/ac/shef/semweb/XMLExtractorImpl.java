package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.util.List;

public class XMLExtractorImpl {

    public List<String> getAllUrls() throws FileNotFoundException {
	UrlFileReader reader = new UrlFileReader();
	return reader.readFile(UrlFileReader.INPUT_PATH);
    }

    
}
