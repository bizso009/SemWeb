package uk.ac.shef.semweb.view;

// Add necessary imports.
import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uk.ac.shef.semweb.controller.Extractor;
import uk.ac.shef.semweb.model.RdfBuilder;
import uk.ac.shef.semweb.model.RdfBuilder.IntelWebProperties;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * This class is used to generate the HTML for the various files.
 * @author Team BDM
 *
 */
public abstract class HtmlGenerator 
{

	/**
	 * A Jena model containing the ontology.
	 */
    protected Model              model;
    
    /**
     * This variable contains the properties of the ontology.
     */
    protected IntelWebProperties properties;
    
    /**
     * A Document containing the output template.
     */
    protected Document           template;
    
    /**
     * String containing the template path.
     */
    protected String             templatePath;
    
    /**
     * String containing the name of the output.
     */
    protected String             name;
    
    /**
     * String containing the path of the output directory for HTML content.
     */
    public static final String   DIR = "output/website/";
    
    /**
     * A Jena resource.
     */
    protected Resource           res;
    
    /**
     * Type of the Resource.
     */
    protected String             type;

    /**
     * Class constructor
     * @param model Takes in a Jena Model.
     */
    public HtmlGenerator(Model model) 
    {
        this.model = model;
        properties = new IntelWebProperties(model);
    }

    /**
     * This function generates the HTML pages.
     * @throws TransformerException
     * @throws FileNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void generate() throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException 
    {
        ResIterator resources = model.listResourcesWithProperty(RDF.type, model.getResource(RdfBuilder.RDF_BASE + type));
        while (resources.hasNext()) 
        {
            setResource(resources);

        }
    }

    /**
     * This function sets the common properties of artists and venues.
     * @param resources Takes a Jena Resource.
     * @throws FileNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    protected void setResource(ResIterator resources) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException 
    {
        res = resources.next();
        name = StringEscapeUtils.unescapeHtml(res.getProperty(properties.nameProp).getString());

        template = new Extractor().readXml(new FileInputStream(templatePath));
        setImage();

        setDescription();

        setWikiPages();
    }

    /**
     * This function formats the output file name.
     * @param fileName Takes in a String that stores the file Name
     * @return Returns the formatted output file name.
     */
    protected String encode(String fileName) 
    {
        return DIR + fileName.replaceAll("[^A-Za-z]+", "_") + ".html";
    }

    /**
     * This function writes the HTML to an output file.
     * @throws TransformerException
     */
    protected void write() throws TransformerException 
    {
        String fileName = encode(name);
        Source source = new DOMSource(template);
        File file = new File(fileName);
        Result result = new StreamResult(file);
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);

    }
    
    /**
     * This is a workaround function to get elements from the DOM.
     */
    public Element getElementById(Document doc, String id) 
    {

        try 
        {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//*[@id = '" + id + "']");
            return (Element) ((NodeList) expr.evaluate(doc, XPathConstants.NODESET)).item(0);
        } catch (XPathExpressionException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function sets the img in the output HTML file.
     */
    protected void setImage() 
    {
        getElementById(template, "image").setAttribute("src", res.getProperty(properties.imageProp).getString());
    }

    /**
     * This function sets the description property in the output HTML file. It must be overidden to set specific descriptions.
     */
    protected void setDescription() 
    {
        // override
    }

    /**
     * This function sets the information from dbPedia to the output HTML file.
     * @throws DOMException
     */
    protected void setWikiPages() throws DOMException 
    {
        Element span = template.createElement("span");
        Element a = template.createElement("a");
        span.appendChild(a);
        String link = "";
        if (res.getProperty(properties.wikiPageProp)!=null)
        {
            link = res.getProperty(properties.wikiPageProp).getLiteral().toString();
        }
        a.setAttribute("href", link);
        a.setTextContent(name);
        getElementById(template, "wikiPages").appendChild(span);
    }
}
