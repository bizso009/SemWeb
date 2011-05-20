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
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
        Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
        this.builder = new UserBuilder(model, doc, isXmlUrl,true);
        assertEquals("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10", this.builder.getUri());
        assertNotNull(this.builder.queryTag("//voteEvent"));
    }

    public void testDBpedia() throws IllegalStateException, ClientProtocolException, IOException, SAXException, ParserConfigurationException{
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new AlbumBuilder(model, doc, testUrl,true);        
        ResultSet rs = this.builder.queryDBpedia("http://dbpedia.org/resource/Coldplay", this.builder.dbpediaGenre);
        Resource res = rs.next().getResource(this.builder.dbpediaVAR);
        ResultSet rs2 = this.builder.queryDBpedia(res.getURI(),RDFS.label.getURI());
        assertEquals("Alternative rock",rs2.next().getLiteral("arg").getString());
    }
    public void testAlbum() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException
    {
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/323";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new AlbumBuilder(model, doc, testUrl,true);
        this.builder.extractXml();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("Yesterdays", res.getProperty(this.builder.titleProp).getString());
        assertEquals("Rock", res.getProperty(this.builder.genreProp).getString());
        assertEquals("http://ext.dcs.shef.ac.uk/~u0082/intelweb2/sites/default/files/images/yesterdays.jpg", res.getProperty(this.builder.imageProp)
                .getString());
        assertTrue(res.hasProperty(this.builder.trackProp));
    }

    public void testUser() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException    {
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user9";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new UserBuilder(model, doc, testUrl,true);
        this.builder.extractXml();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("intelliUser9", res.getProperty(this.builder.usernameProp).getString());
        assertTrue(res.hasProperty(this.builder.voteEventProp));

        Resource voteEventRes = res.getProperty(this.builder.voteEventProp).getResource();
        assertNotNull(voteEventRes);

        Resource gigRes = voteEventRes.getProperty(this.builder.gigProp).getResource();
        assertNotNull(gigRes);

        assertTrue(voteEventRes.hasProperty(this.builder.voteProp));
        assertTrue(gigRes.hasProperty(this.builder.titleProp));

    }

    public void testVenue() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException, DOMException
    {
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=venue/328";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new VenueBuilder(model, doc, testUrl,true);
        this.builder.extract();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("http://www.sbg.org/", res.getProperty(this.builder.websiteProp).getString());
        assertEquals("Botanical Gardens", res.getProperty(this.builder.nameProp).getString());
        
        Resource gigRes = res.getProperty(this.builder.gigProp).getResource();
        assertNotNull(gigRes);
        assertTrue(gigRes.hasProperty(this.builder.titleProp));
        assertEquals("53.372032", res.getProperty(this.builder.geoLatProp).getString());

    }

    public void testGig() throws IllegalStateException, ClientProtocolException, IOException, SAXException, ParserConfigurationException
    {
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=gig/601/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=gig/601";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new GigBuilder(model, doc, testUrl);
        this.builder.extractXml();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("Friday, 25 May, 2007", res.getProperty(this.builder.dateProp).getString());
        
        Resource artistRes = res.getProperty(this.builder.artistProp).getResource();
        assertNotNull(artistRes);
        assertTrue(artistRes.hasProperty(this.builder.nameProp));
    }

    public void testArtist() throws IllegalStateException, ClientProtocolException, SAXException, IOException, ParserConfigurationException
    {
        Extractor impl = new Extractor();
        Model model = impl.parseRdf(Extractor.ONTOLOGY_URL);
        String testUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/384/xml";
        String testUri = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/384";
        Document doc = impl.loadXml(impl.openUrl(testUrl).getContent());

        this.builder = new ArtistBuilder(model, doc, testUrl, true);
        this.builder.extract();

        Resource res = model.getResource(testUri);
        assertNotNull(res);

        assertEquals("http://www.coldplay.com/", res.getProperty(this.builder.websiteProp).getString());
        
        Resource albumRes = res.getProperty(this.builder.albumProp).getResource();
        assertNotNull(albumRes);
        assertTrue(albumRes.hasProperty(this.builder.titleProp));
        
        assertEquals("Alternative rock",res.getProperty(this.builder.genreProp).getString());
        assertEquals(2,res.listProperties(this.builder.wikiPageProp).toSet().size());
        assertEquals(2,res.listProperties(this.builder.homeTownProp).toSet().size());
    }

}
