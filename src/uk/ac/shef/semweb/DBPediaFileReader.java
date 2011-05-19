package uk.ac.shef.semweb;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.hp.hpl.jena.rdf.model.Model;
import com.sun.corba.se.impl.orbutil.graph.Graph;

public class DBPediaFileReader
{
	public DBPediaFileReader()
	{
		
	}
	
	public Graph getModel() throws IllegalStateException, ClientProtocolException, IOException
	{
		UrlFileReader urlFileReader = new UrlFileReader();
		Model ontologyModel = urlFileReader.parseRdf("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf");
		Graph graph = (Graph) ontologyModel.getGraph();
		return graph;
		
	}
	
}
