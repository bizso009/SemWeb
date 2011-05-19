package uk.ac.shef.semweb;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.hp.hpl.jena.rdf.model.Model;

public abstract class FileExtractor
{
    protected final String BASE = "http://poplar.dcs.shef.ac.uk";
    protected Model ontology;
    protected Document xml;
    protected String   url;

    public Model getOntology()
    {
        return ontology;
    }

    public Document getXml()
    {
        return xml;
    }

    public String getUrl()
    {
        return url;
    }


    public FileExtractor(Model ontology, Document xml, String url)
    {
        this.ontology = ontology;
        this.xml = xml;
        this.url = url;
    }
//FIXME validate rdf!!!!
    public abstract void extract() throws XPathExpressionException;

    public String getUri(){
        return this.url.substring(0,this.url.lastIndexOf("/xml"));
    }
    public NodeList query(String xPath) throws XPathExpressionException
    {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(xPath);


        Object result = expr.evaluate(this.xml, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes;
    }
}
