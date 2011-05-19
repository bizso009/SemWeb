package uk.ac.shef.semweb;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.hp.hpl.jena.rdf.model.Model;

public class FileExtractorTest extends TestCase
{

    private FileExtractor extractor;

    public void testQuery() throws XPathExpressionException, IllegalStateException, ClientProtocolException, SAXException, IOException,
            ParserConfigurationException
    {
        XMLExtractorImpl impl = new XMLExtractorImpl();
        Model model = impl.parseRdf(XMLExtractorImpl.ONTOLOGY_URL);
        String isXmlUrl = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10/xml";
        Document doc = impl.loadXml(impl.openUrl(isXmlUrl).getContent());
        this.extractor = new UserExtractor(model, doc, isXmlUrl);
        assertEquals("http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=users/user10", this.extractor.getUri());
        assertNotNull(this.extractor.query("//voteEvent"));
    }

}
