package uk.ac.shef.semweb;

// Add necessary imports.
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * This class generates the Venue HTML page.
 * @author Team BDM.
 *
 */
public class VenueGenerator extends HtmlGenerator 
{

	/**
	 * Class constructor.
	 * @param model Takes a Jena Model.
	 */
    public VenueGenerator(Model model) 
    {
    	// Call to super constructor.
        super(model);
        // Set the required template path.
        templatePath = "input/venue.xhtml";
        type = "Venue";

    }

    /**
     * This function gets the resources of type Venue from the ontology and then sends these details to the HTML output.
     * @param resources A list of resources.
     */
    @Override
    public void setResource(ResIterator resources) throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException 
    {

        super.setResource(resources);

        setCategories();

        setLocations();
        System.out.println("Writing venue: " + name);

        write();

    }

    /**
     * This function gets a category property from the ontology and then writes this to the Venue HTML template.
     */
    private void setCategories() 
    {
        StmtIterator categories = res.listProperties(properties.categoryProp);
        while (categories.hasNext()) 
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(categories.next().getLiteral().toString());
            getElementById(template, "categories").appendChild(listItem);
        }

    }

    /**
     * This function writes a list of locations to the Venue HTML template.
     */
    private void setLocations() {

        Element ul = getElementById(template, "locations");
        Element listItem = template.createElement("li");
        ul.appendChild(listItem);
        Element spanItem1 = template.createElement("span");
        Element spanItem2 = template.createElement("span");
        listItem.appendChild(spanItem1);
        listItem.appendChild(spanItem2);

        if (res.getProperty(properties.geoLatProp) != null) 
        {
            spanItem1.setTextContent(res.getProperty(properties.geoLatProp).getString());
            spanItem2.setTextContent(res.getProperty(properties.geoLonProp).getString());
        }

    }

    /**
     * This function gets the Venue description from the ontology and then writes this to the Venue HTML template.
     */
    @Override
    protected void setDescription() 
    {
        getElementById(template, "description").appendChild(template.createTextNode(res.getProperty(properties.descriptionProp).getString()));

    }

}
