package uk.ac.shef.semweb;

public interface DBpediaExtractor extends XMLExtractor {
	
	// Get DBpedia information from URL
	public void getDBpediaInfo(String URL);
}
