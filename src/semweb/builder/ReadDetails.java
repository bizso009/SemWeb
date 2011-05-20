package semweb.builder;

import java.util.ArrayList;


/**
 *  Abstract class to read details from the ontology
 * @author Kushal D'Souza
 *
 */
public abstract class ReadDetails 
{
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
}
