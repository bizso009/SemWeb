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

public class ArtistGenerator extends HtmlGenerator
{
    public static final String ARTIST_TEMPLATE = "input/artist.xhtml";
    private Resource           artist;
    public ArtistGenerator(Model model)
    {
        super(model);

    }

    @Override
    public void generate() throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException
    {
        ResIterator artists = this.model.listResourcesWithProperty(RDF.type, this.model.getResource(RdfBuilder.RDF_BASE + "Artist"));
        while (artists.hasNext())
        {
            //TODO extract methods
            artist = artists.next();
            name = artist.getProperty(properties.nameProp).getString();
            
            System.out.println("Writing artist: " + name);

            template = new Extractor().readXml(new FileInputStream(ARTIST_TEMPLATE));

            setImage();

            setDescription();

            setAlbums();

            setWikiPages();

            setHomeTown();

            setTweets();
            
            setGigs();

            write();

        }
    }

    private void setImage()
    {
        getElementById(template, "image").setAttribute("src", artist.getProperty(properties.imageProp).getString());
    }

    private void setDescription()
    {
        getElementById(template, "description").setTextContent(artist.getProperty(properties.biographyProp).getString());
    }

    private void setAlbums()
    {
        StmtIterator albums = artist.listProperties(properties.albumProp);
        while (albums.hasNext())
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(albums.next().getResource().getProperty(properties.titleProp).getString());
            getElementById(template, "albums").appendChild(listItem);
        }
    }

    private void setWikiPages()
    {
        StmtIterator wikiPages = artist.listProperties(properties.wikiPageProp);
        while (wikiPages.hasNext())
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(wikiPages.next().getLiteral().toString());
            getElementById(template, "wikiPages").appendChild(listItem);
        }
    }

    private void setGigs()
    {
        ResIterator gigs = model.listResourcesWithProperty(properties.artistProp, artist);
        while (gigs.hasNext())
        {
            Resource gig = gigs.next();
            ResIterator venuesAndVoteEvents = model.listResourcesWithProperty(properties.gigProp, gig);
            while (venuesAndVoteEvents.hasNext())
            {
                Resource maybeVenue = venuesAndVoteEvents.next();
                if (maybeVenue.hasProperty(RDF.type, properties.venueClas))
                {
                    Element ul = getElementById(template, "gigs");
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

    private void setHomeTown()
    {
        StmtIterator homeTowns = artist.listProperties(properties.homeTownProp);
        while (homeTowns.hasNext())
        {
            Element listItem = template.createElement("li");
            listItem.setTextContent(homeTowns.next().getLiteral().getString());
            getElementById(template, "homeTowns").appendChild(listItem);
        }
    }

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
}
