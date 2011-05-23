package uk.ac.shef.semweb;

// Add necessary imports.
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class generates the index HTML page.
 * @author Team BDM
 *
 */
public class IndexGenerator extends HtmlGenerator
{

	/**
	 * Class constructor
	 * @param model Takes in a Jena model.
	 */
    public IndexGenerator(Model model)
    {
    	// Call to the super constructor.
        super(model);
        // Set the required template path.
        templatePath = "input/index.xhtml";
    }

    /**
     * This function generates the index.html page.
     * 
     */
    @Override
    public void generate() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException
    {
        name = "index";
        System.out.println("Writing index");

        template = new Extractor().readXml(new FileInputStream(templatePath));
        
       setArtists();
       setVenues();
       write();
       

    }

    /**
     * This function sets the list of arts to the index.html file.
     */
    private void setArtists()
    {
        ResIterator artists = model.listResourcesWithProperty(RDF.type,properties.artistClas);
           Element artistsElem = getElementById(template, "artists");
           while (artists.hasNext())
           {
               Resource artist = artists.next();
               String disp = StringEscapeUtils.unescapeHtml(artist.getProperty(properties.nameProp).getString());
               String link = disp.replaceAll("[^A-Za-z]+", "_")+".html";
               Element li = template.createElement("li");
               artistsElem.appendChild(li);
               Element a = template.createElement("a");
               a.setAttribute("href", link);
               a.setTextContent(disp);
               li.appendChild(a);
           }
    }

    /**
     * This function sets the list of venues to the index.html file.
     */
    private void setVenues()
    {
        ResIterator venues = model.listResourcesWithProperty(RDF.type,properties.venueClas);
           Element venuesElem = getElementById(template, "venues");
           while (venues.hasNext())
           {
               Resource artist = venues.next();
               String disp = artist.getProperty(properties.nameProp).getString();
               String link = disp.replaceAll("[^A-Za-z]+", "_")+".html";
               Element li = template.createElement("li");
               venuesElem.appendChild(li);
               Element a = template.createElement("a");
               a.setAttribute("href", link);
               a.setTextContent(disp);
               li.appendChild(a);
           }
    }

}
