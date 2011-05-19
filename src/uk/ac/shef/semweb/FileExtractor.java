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

    private Model ontology;
    private Document xml;
    private String url;

    public FileExtractor(Model ontology, Document xml, String url) {
	this.ontology = ontology;
	this.xml = xml;
	this.url = url;
    }

    

    public abstract void extract();
  
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
