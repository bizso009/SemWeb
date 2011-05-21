package uk.ac.shef.semweb;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDFS;

public class RdfBuilderTest extends TestCase
{

    private RdfBuilder builder;


    public void testQuery() throws IllegalStateException, ClientProtocolException, SAXException, IOException,
            ParserConfigurationException
    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
        Document doc = impl.readXml(impl.openUrl(isXmlUrl).getContent());
        this.builder = new UserBuilder(model, doc, isXmlUrl,true);
        assertEquals("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10", this.builder.getUri());
        assertNotNull(this.builder.queryTag("//voteEvent"));
    }

    public void testDBpedia() throws IllegalStateException, ClientProtocolException, IOException, SAXException, ParserConfigurationException{
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new AlbumBuilder(model, doc, testUrl,true);        
        ResultSet rs = this.builder.queryDBpedia("http://dbpedia.org/resource/Coldplay", this.builder.dbpediaGenre);
        Resource res = rs.next().getResource(this.builder.dbpediaVAR);
        ResultSet rs2 = this.builder.queryDBpedia(res.getURI(),RDFS.label.getURI());
        assertEquals("Alternative rock",rs2.next().getLiteral("arg").getString());
    }
    public void testAlbum() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException
    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new AlbumBuilder(model, doc, testUrl,true);
        this.builder.extractXml();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("Yesterdays", res.getProperty(builder.properties.titleProp).getString());
        assertEquals("Rock", res.getProperty(builder.properties.genreProp).getString());
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/yesterdays.jpg", res.getProperty(builder.properties.imageProp)
                .getString());
        assertTrue(res.hasProperty(builder.properties.trackProp));
    }

    public void testUser() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new UserBuilder(model, doc, testUrl,true);
        this.builder.extract();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("intelliUser9", res.getProperty(builder.properties.usernameProp).getString());
        assertTrue(res.hasProperty(builder.properties.voteEventProp));

        Resource voteEventRes = res.getProperty(builder.properties.voteEventProp).getResource();
        assertNotNull(voteEventRes);

        Resource gigRes = voteEventRes.getProperty(builder.properties.gigProp).getResource();
        assertNotNull(gigRes);

        assertTrue(voteEventRes.hasProperty(builder.properties.voteProp));
        assertTrue(gigRes.hasProperty(builder.properties.titleProp));
            
        assertEquals("http://a0.twimg.com/sticky/default_profile_images/default_profile_1_normal.png",res.getProperty(builder.properties.imageProp).getString());
        assertEquals(17,res.listProperties(builder.properties.tweetProp).toSet().size());

    }

    public void testVenue() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException
    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new VenueBuilder(model, doc, testUrl,true);
        this.builder.extract();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("http://www.sbg.org/", res.getProperty(builder.properties.websiteProp).getString());
        assertEquals("Botanical Gardens", res.getProperty(builder.properties.nameProp).getString());
        
        Resource gigRes = res.getProperty(builder.properties.gigProp).getResource();
        assertNotNull(gigRes);
        assertTrue(gigRes.hasProperty(builder.properties.titleProp));
        assertEquals("53.372032", res.getProperty(builder.properties.geoLatProp).getString());

    }

    public void testGig() throws IllegalStateException, ClientProtocolException, IOException, SAXException, ParserConfigurationException
    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=gig/601/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=gig/601";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new GigBuilder(model, doc, testUrl,false);
        this.builder.extractXml();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("Friday, 25 May, 2007", res.getProperty(builder.properties.dateProp).getString());
        
        Resource artistRes = res.getProperty(builder.properties.artistProp).getResource();
        assertNotNull(artistRes);
        assertTrue(artistRes.hasProperty(builder.properties.nameProp));
    }

    public void testArtist() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException
    {
        Extractor impl = new Extractor();
        Model model = impl.readRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/384/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/384";
        Document doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new ArtistBuilder(model, doc, testUrl, true);
        this.builder.extract();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("http://www.coldplay.com/", res.getProperty(builder.properties.websiteProp).getString());
        
        Resource albumRes = res.getProperty(builder.properties.albumProp).getResource();
        assertNotNull(albumRes);
        assertTrue(albumRes.hasProperty(builder.properties.titleProp));
        
        assertEquals("Alternative rock",res.getProperty(builder.properties.genreProp).getString());
        assertEquals(2,res.listProperties(builder.properties.wikiPageProp).toSet().size());
        assertEquals(2,res.listProperties(builder.properties.homeTownProp).toSet().size());
        
        testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/663/xml";
        testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/663";
        doc = impl.readXml(impl.openUrl(testUrl).getContent());

        this.builder = new ArtistBuilder(model, doc, testUrl, true);
        this.builder.extract();

    }

}
