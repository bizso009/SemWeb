package uk.ac.shef.semweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class XMLExtractorImpl implements XMLExtractor {
	// TODO redundant
	public static final String INPUT_PATH = "input/internalLinks.txt";
	public static final String XML_TYPE = "text/xml";
	public static final String ONTOLOGY_URL = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf";
	public static final String OUTPUT_MODE = "RDF/XML-ABBREV";

	public void extract() {
		// TODO
	}

	/**
	 * Reads a file and returns each line as a String
	 */
	public List<String> readFile(String inputPath) throws FileNotFoundException {
		File inputFile = new File(inputPath);
		Scanner scanner = new Scanner(inputFile);

		List<String> urls = new ArrayList<String>();
		while (scanner.hasNext()) {
			urls.add(scanner.nextLine());
		}
		return urls;
	}

	public Document loadXml(InputStream is) throws SAXException, IOException,
			ParserConfigurationException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(is);
		return doc;
	}

	/**
	 * Is the entity an xml file?
	 */
	public boolean isXML(HttpEntity entity) {
		return entity.getContentType().getValue().equals(XML_TYPE);
	}

	/**
	 * Opens the url
	 */
	public HttpEntity openUrl(String url) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return entity;
	}

	public Model parseRdf(String ontologyUrl) throws IllegalStateException,
			ClientProtocolException, IOException {
		Model model = ModelFactory.createDefaultModel();

		// use the FileManager to find the input file
		InputStream in = openUrl(ontologyUrl).getContent();
		if (in == null) {
			throw new IllegalArgumentException("File: " + ontologyUrl
					+ " not found");
		}

		// read the RDF/XML file
		model.read(in, null);

		return model;
	}

	@Override
	public String getRdfTriples(String url) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAllTriples(String URLListFileName, String OutputFileName) {
		// TODO Auto-generated method stub
	}

	/*
	 * public Set<Resource> getClassResources(Model model) { ResIterator it =
	 * model.listResourcesWithProperty(RDF.type); return it.toSet(); }
	 * 
	 * public boolean matchClassWithUrl(Resource res, String url) { return
	 * url.toLowerCase
	 * ().contains(res.getProperty(RDFS.label).getString().toLowerCase()); }
	 * 
	 * public Set<Resource> getPropertiesByClass( Resource res) { Model model =
	 * res.getModel(); ResIterator it =
	 * model.listResourcesWithProperty(RDFS.domain, res); return it.toSet(); }
	 */

}
