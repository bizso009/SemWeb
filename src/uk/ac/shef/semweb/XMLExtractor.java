package uk.ac.shef.semweb;

public interface XMLExtractor 
{
	/** given an URL in the form of a string (e.g. http://www.mysite.org/index.html) it
	extracts the RDF triples relevant to the ontology (see figure 2 for an example).*/
	public String getRdfTriples(String url);
	
	/** it loads a list of URLs from a file (see details below); it applies the method
	getRdfTriples to each URL and saves all the extracted triples to OutputFileName */
	public void saveAllTriples(String URLListFileName, String OutputFileName);

}
