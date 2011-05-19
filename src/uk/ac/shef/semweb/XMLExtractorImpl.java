package uk.ac.shef.semweb;

import java.io.FileNotFoundException;
import java.util.List;

public class XMLExtractorImpl implements XMLExtractor{

    public List<String> getAllUrls() throws FileNotFoundException {
	UrlFileReader reader = new UrlFileReader();
	return reader.readFile(UrlFileReader.INPUT_PATH);
    }

	@Override
	public String getRdfTriples(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAllTriples(String URLListFileName, String OutputFileName) {
		// TODO Auto-generated method stub
		
	}

    
}
