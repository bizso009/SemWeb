package uk.ac.shef.semweb;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.rdf.model.Model;


public class DBPediaFileReaderTest {
	private DBPediaFileReader dbPediaFileReader;
	
	@Before
    public void setUp()
    {
    	this.dbPediaFileReader = new DBPediaFileReader();
    }
	
	public void testModelCreator() throws IllegalStateException, ClientProtocolException, IOException
	{
		Graph graph = (Graph) this.dbPediaFileReader.getModel();
		
		
	}
    
    
}
