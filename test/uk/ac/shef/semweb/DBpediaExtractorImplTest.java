package uk.ac.shef.semweb;

import org.junit.Before;

import com.hp.hpl.jena.query.ResultSet;

import junit.framework.TestCase;


public class DBpediaExtractorImplTest extends TestCase 
{
	private DBpediaExtractorImpl dbpediaExtractorImpl;
	
	@Override
	@Before
	public void setUp()
	{
		this.dbpediaExtractorImpl = new DBpediaExtractorImpl();
	}
	
	public void testRunQuery()
	{
		String queryString = "SELECT ?person WHERE { ?person <http://dbpedia.org/ontology/birthPlace> <http://dbpedia.org/resource/Chennai>}";
		ResultSet results = dbpediaExtractorImpl.runQuery(queryString);
		assertNotNull("No values returned", results);
	}
}
