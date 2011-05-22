package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;

public class ExtractorTest extends TestCase {

    private Extractor extractor;

    @Override
    @Before
    public void setUp() {
        extractor = new Extractor();
    }

    public void testDBpediaExtract() {
        extractor.extract(true, Extractor.DBPEDIA_TRIPLES_PATH);
        assertTrue(new File(Extractor.DBPEDIA_TRIPLES_PATH).exists());
    }

    public void testIsXML() throws ClientProtocolException, IOException {
        String notXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
        assertFalse(extractor.isXML(extractor.openUrl(notXmlUrl)));

        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
        assertTrue(extractor.isXML(extractor.openUrl(isXmlUrl)));

    }

    public void testLoadXml() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
        String xmlPath = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/415/xml";
        InputStream is = extractor.openUrl(xmlPath).getContent();
        Document doc = extractor.readXml(is);
        assertNotNull(doc);
    }

    public void testOpenUrl() throws ClientProtocolException, IOException {
        String url = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=taxonomy/term/1&amp;page=7";
        HttpEntity entity = extractor.openUrl(url);
        assertNotNull(entity);
    }

    public void testParseRdf() throws IllegalStateException, ClientProtocolException, IOException {
        Model model = extractor.readRdf(Extractor.ONTOLOGY_URL);
        assertNotNull(model);
    }

    public void testReadFile() throws FileNotFoundException {
        List<String> urls = extractor.readFile(Extractor.INPUT_PATH);
        assertFalse(urls.isEmpty());
        assertFalse(urls.get(0).isEmpty());
    }

    /*
     * public void testGetClassResources() throws IllegalStateException,
     * ClientProtocolException, IOException { Model model =
     * this.extractor.parseRdf(Extractor.ONTOLOGY_URL);
     * assertFalse(this.extractor.getClassResources(model).isEmpty()); }
     * 
     * public void testMatchClassWithUrl() throws IllegalStateException,
     * ClientProtocolException, IOException { String artistUri =
     * "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#Artist";
     * String shouldMatch =
     * "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/415/xml";
     * String shouldNotMatch =
     * "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=album/558/xml";
     * 
     * Model model = this.extractor.parseRdf(Extractor.ONTOLOGY_URL); Resource
     * res = model.getResource(artistUri);
     * assertTrue(this.extractor.matchClassWithUrl(res,shouldMatch));
     * assertFalse(this.extractor.matchClassWithUrl(res,shouldNotMatch)); }
     * 
     * public void testGetPropertiesByClass() throws IllegalStateException,
     * ClientProtocolException, IOException { String artistUri =
     * "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf#Artist";
     * Model model = this.extractor.parseRdf(Extractor.ONTOLOGY_URL); Resource
     * res = model.getResource(artistUri);
     * assertFalse(this.extractor.getPropertiesByClass(res).isEmpty()); }
     */

    public void testXmlExtract() {
        extractor.extract(false, Extractor.XML_TRIPLES_PATH);
        assertTrue(new File(Extractor.XML_TRIPLES_PATH).exists());
    }
}
