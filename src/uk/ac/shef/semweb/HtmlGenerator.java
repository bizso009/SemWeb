package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.ac.shef.semweb.RdfBuilder.IntelWebProperties;
import com.hp.hpl.jena.rdf.model.Model;

public abstract class HtmlGenerator
{

    protected Model              model;
    protected IntelWebProperties properties;
    protected Document           template;
    protected String             templatePath;
    protected String             name;
    public static final String   DIR = "output/website/";

    public HtmlGenerator(Model model)
    {
        this.model = model;
        this.properties = new IntelWebProperties(model);
    }

    public abstract void generate() throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException;

    protected String encode(String fileName)
    {
        return DIR + (fileName.replaceAll("[^A-Za-z]+", "_")) + ".html";
    }

    protected void write() throws TransformerException
    {
        String fileName = encode(name);
        Source source = new DOMSource(template);
        File file = new File(fileName);
        Result result = new StreamResult(file);
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);

    }

    //workaround method
    public Element getElementById(Document doc, String id)
    {

        try
        {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//*[@id = '" + id + "']");
            return (Element)((NodeList)expr.evaluate(doc, XPathConstants.NODESET)).item(0);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
