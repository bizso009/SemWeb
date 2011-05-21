package uk.ac.shef.semweb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class VenueGenerator extends HtmlGenerator
{
    private Resource           venue;

    public VenueGenerator(Model model)
    {
        super(model);
        templatePath = "input/venue.xhtml";

    }

    @Override
    public void generate() throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException
    {
        ResIterator venues = this.model.listResourcesWithProperty(RDF.type, this.model.getResource(RdfBuilder.RDF_BASE + "Venue"));
        while (venues.hasNext())
        {
            //TODO extract methods
            venue = venues.next();
            name = venue.getProperty(properties.nameProp).getString();

            System.out.println("Writing venue: " + name);

            template = new Extractor().readXml(new FileInputStream(templatePath));

            setImage();

            setDescription();

            setWikiPages();

            setCategories();

            setLocations();

            write();

        }
    }

    private void setCategories()
    {
        StmtIterator categories = venue.listProperties(properties.categoryProp);
        while (categories.hasNext())
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(categories.next().getLiteral().toString());
            getElementById(template, "categories").appendChild(listItem);
        }

    }

    private void setImage()
    {
        getElementById(template, "image").setAttribute("src", venue.getProperty(properties.imageProp).getString());
    }

    private void setDescription()
    {
        getElementById(template, "description").setTextContent(venue.getProperty(properties.descriptionProp).getString());
    }

    private void setWikiPages()
    {
        StmtIterator wikiPages = venue.listProperties(properties.wikiPageProp);
        while (wikiPages.hasNext())
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(wikiPages.next().getLiteral().toString());
            getElementById(template, "wikiPages").appendChild(listItem);
        }
    }

    private void setLocations()
    {

        Element ul = getElementById(template, "locations");
        Element listItem = template.createElement("li");
        ul.appendChild(listItem);
        Element spanItem1 = template.createElement("span");
        Element spanItem2 = template.createElement("span");
        listItem.appendChild(spanItem1);
        listItem.appendChild(spanItem2);

        if (venue.getProperty(properties.geoLatProp) != null)
        {
            spanItem1.setTextContent(venue.getProperty(properties.geoLatProp).getString());
            spanItem2.setTextContent(venue.getProperty(properties.geoLonProp).getString());
        }

    }

}
