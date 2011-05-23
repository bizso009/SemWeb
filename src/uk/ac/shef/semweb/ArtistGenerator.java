package uk.ac.shef.semweb;

// Add necessary imports.
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
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class generates the artist pages.
 * @author Team BDM
 *
 */
public class ArtistGenerator extends HtmlGenerator 
{

	/**
	 * Class constructor.
	 * @param model Takes in a Jena model.
	 */
    public ArtistGenerator(Model model) 
    {
    	// Call the super constructor.
        super(model);
        // Set the type and the template path.
        templatePath = "input/artist.xhtml";
        type = "Artist";

    }

    /**
     * This function gets the resources of type Artist from the ontology and then sends these details to the HTML output.
     * @param resources A list of resources.
     */
    @Override
    public void setResource(ResIterator resources) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException 
    {

        super.setResource(resources);
        setAlbums();

        setHomeTown();

        setTweets();

        setGigs();

        System.out.println("Writing artist: " + name);
        write();

    }

    /**
     * This function goes through the list of album properties in an ontology and then writes this to the artist template.
     */
    private void setAlbums() 
    {
        StmtIterator albums = res.listProperties(properties.albumProp);
        while (albums.hasNext()) 
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(StringEscapeUtils.unescapeHtml(albums.next().getResource().getProperty(properties.titleProp).getString()));
            getElementById(template, "albums").appendChild(listItem);
        }
    }

    /**
     * This function goes through the list of gig properties in an ontology and then writes this to the artist template.
     */
    private void setGigs() 
    {
        ResIterator gigs = model.listResourcesWithProperty(properties.artistProp, res);
        while (gigs.hasNext()) 
        {
            Resource gig = gigs.next();
            ResIterator venuesAndVoteEvents = model.listResourcesWithProperty(properties.gigProp, gig);
            while (venuesAndVoteEvents.hasNext()) 
            {
                Resource maybeVenue = venuesAndVoteEvents.next();
                if (maybeVenue.hasProperty(RDF.type, properties.venueClas)) 
                {
                    Element ul = getElementById(template, "locations");
                    Element listItem = template.createElement("li");
                    ul.appendChild(listItem);
                    Element spanItem1 = template.createElement("span");
                    Element spanItem2 = template.createElement("span");
                    listItem.appendChild(spanItem1);
                    listItem.appendChild(spanItem2);

                    if (maybeVenue.getProperty(properties.geoLatProp) != null)
                    {
                        spanItem1.setTextContent(maybeVenue.getProperty(properties.geoLatProp).getString());
                        spanItem2.setTextContent(maybeVenue.getProperty(properties.geoLonProp).getString());
                    }
                }
            }
        }
    }

    /**
     * This gets the homeTown property of the artist from the ontology and then writes this to the artist template.
     */
    private void setHomeTown() 
    {
        StmtIterator homeTowns = res.listProperties(properties.homeTownProp);
        while (homeTowns.hasNext()) 
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(homeTowns.next().getLiteral().getString());
            getElementById(template, "homeTowns").appendChild(listItem);
        }
    }

    /**
     * This function goes through the tweets about an artist that are present in the ontology and then writes this to
     * the artist template.
     */
    private void setTweets() 
    {
        ResIterator users = model.listResourcesWithProperty(RDF.type, properties.userClas);
        while (users.hasNext()) 
        {
            Resource user = users.next();
            StmtIterator tweets = user.listProperties(properties.tweetProp);
            while (tweets.hasNext()) 
            {
                String tweet = tweets.next().getString();
                if (tweet.toLowerCase().contains(name.toLowerCase())) 
                {
                    Element listItem = template.createElement("li");
                    listItem.setTextContent(tweet);
                    getElementById(template, "tweets").appendChild(listItem);
                }
            }
        }
    }

    /**
     * This function gets the artist description from an ontology and then writes this to the artist template.
     */
    @Override
    protected void setDescription()
    {
        getElementById(template, "description").appendChild(template.createTextNode(res.getProperty(properties.biographyProp).getString()));

    }
}
