package semweb.builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import uk.ac.shef.semweb.RdfBuilder;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;


/**
 *  Abstract class to read details from the ontology
 * @author Kushal D'Souza
 *
 */
public abstract class ReadDetails 
{
	
	protected Model ontology;
	private String ontologyFile = "output/XMLFileTriples.rdf";
	
	/**
	 * Class constructor initiates reading of details
	 */
	public ReadDetails()
	{
		try {
			populateOntology();
		} catch (Exception exception)
		{
			System.out.println("Error");
			// Handle code in event of the exception
		}
	}
	
	/**
	 * Method that reads all the details of each item.
	 * 
	 */
	public abstract  void readDetails();
	
	
	/**
	 * Method to get a list of items without any other data.
	 * @return
	 */
	public abstract ArrayList getItems();
	
	/**
	 * Reads an rdf triples file into memory
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws IllegalStateException 
	 */
	public void populateOntology() throws IllegalStateException, ClientProtocolException, IOException
	{
		Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
		 InputStream in = FileManager.get().open( ontologyFile );
        if (in == null)
        {
        	System.out.println("Invalid File Name");
        }
        // read the RDF/XML file
        model.read(in, null);
        model.setNsPrefix("com4280", RdfBuilder.RDF_BASE);
        
        this.ontology = model;
	}

	public void setOntology(Model ontology) {
		this.ontology = ontology;
	}

	public Model getOntology() {
		return ontology;
	}
}
