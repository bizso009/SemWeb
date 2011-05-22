package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

import uk.ac.shef.semweb.RdfBuilder.IntelWebProperties;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public abstract class HtmlGenerator {

    protected Model              model;
    protected IntelWebProperties properties;
    protected Document           template;
    protected String             templatePath;
    protected String             name;
    public static final String   DIR = "output/website/";
    protected Resource           res;
    protected String             type;

    public HtmlGenerator(Model model) {
        this.model = model;
        properties = new IntelWebProperties(model);
    }

    public void generate() throws TransformerException, FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        // TODO extract methods
        ResIterator resources = model.listResourcesWithProperty(RDF.type, model.getResource(RdfBuilder.RDF_BASE + type));
        while (resources.hasNext()) {
            setResource(resources);

        }
    }

    protected void setResource(ResIterator resources) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException {
        res = resources.next();
        name = StringEscapeUtils.unescapeHtml(res.getProperty(properties.nameProp).getString());

        template = new Extractor().readXml(new FileInputStream(templatePath));
        setImage();

        setDescription();

        setWikiPages();
    }

    protected String encode(String fileName) {
        return DIR + fileName.replaceAll("[^A-Za-z]+", "_") + ".html";
    }

    protected void write() throws TransformerException {
        String fileName = encode(name);
        Source source = new DOMSource(template);
        File file = new File(fileName);
        Result result = new StreamResult(file);
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);

    }

    // workaround method
    public Element getElementById(Document doc, String id) {

        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//*[@id = '" + id + "']");
            return (Element) ((NodeList) expr.evaluate(doc, XPathConstants.NODESET)).item(0);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void setImage() {
        getElementById(template, "image").setAttribute("src", res.getProperty(properties.imageProp).getString());
    }

    protected void setDescription() {
        // override
    }

    protected void setWikiPages() throws DOMException, UnsupportedEncodingException {
        StmtIterator wikiPages = res.listProperties(properties.wikiPageProp);
        while (wikiPages.hasNext()) {
            Element span = template.createElement("span");
            Element a = template.createElement("a");
            span.appendChild(a);
            String link = wikiPages.next().getLiteral().toString();
            a.setAttribute("href", link);
            a.setTextContent(URLDecoder.decode(link.substring(link.lastIndexOf("/") + 1).replaceAll("_", " "),"UTF-8"));
            getElementById(template, "wikiPages").appendChild(span);
        }
    }
}
