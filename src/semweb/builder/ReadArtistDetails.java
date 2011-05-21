package semweb.builder;

import java.util.ArrayList;
import java.util.HashMap;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Class to read Artist details
 * @author kushal
 *
 */
public class ReadArtistDetails extends ReadDetails {
	
	private ArrayList<ArrayList> artistsDetails;
	private HashMap<String,String> artists;
	
	public ReadArtistDetails()
	{
		super();
	}
	/**
	 * This method reads details of all the artists in the ontology
	 * 
	 */
	@Override
	public void readDetails() {
		getArtistNames();
	}

	private void getArtistNames() {
		//System.out.println(ontology.getGraph());
		Resource artists = ontology.getResource("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/663");
		System.out.println(artists.toString());
		//System.out.println(artists.listProperties().toList().toString());
	}

	/**
	 * This method gets the list of artists from the ontology.
	 * @return Returns an array list of items containing the artists that are present in the ontology.
	 */
	@Override
	public ArrayList getItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
