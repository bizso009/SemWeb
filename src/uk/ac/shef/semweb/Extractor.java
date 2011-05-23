package uk.ac.shef.semweb;

// Add necessary imports.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

/**
 * This class is the main class than handles the processing of an ontology and the writing
 * of the rdf triples to the output. It also handles the creation of HTML pages. 
 * @author Team BDM
 *
 */
public class Extractor 
{
    /**
     * INPUT_PATH stores the links to the various xml files.
     */
    public static final String INPUT_PATH   = "input/internalLinks.txt";
    
    /**
     * XML_TRIPLES_PATH is the path to the output file file containing rdf triples.
     */
    public static final String XML_TRIPLES_PATH  = "output/XMLFileTriples.rdf";
    
    /**
     * DBPEDIA_TRIPLES_PATH is the path to the output file containing the DBPedia triples.
     */
    public static final String DBPEDIA_TRIPLES_PATH  = "output/DBPediaTriples.rdf";
    
    /**
     * XML_TYPE stores the type of the xml documents.
     */
    public static final String XML_TYPE     = "text/xml";
    
    /**
     * ONTOLOGY_URL is the link to the ontology.
     */
    public static final String ONTOLOGY_URL = "http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/intelweb.rdf";
    
    /**
     * OUTPUT_MODE is the format of the rdf triples output file.
     */
    public static final String OUTPUT_MODE  = "RDF/XML-ABBREV";
    
    /**
     * DELAY_MS is the delay between requests to the server.
     */
    public static final long   DELAY_MS     = 200;

    /**
     * This is the main function that runs the whole applications
     * @param args
     */
    public static void main(String[] args)
    {
    	// Create new instance of an extractor and extract data.
        Extractor extractor = new Extractor();
        // Extract data without dbPedia info.
        extractor.extract(false,XML_TRIPLES_PATH);
        // Extract data with dbPedia info.
        extractor.extract(true,DBPEDIA_TRIPLES_PATH);
        
    }
    
    /**
     * This function handles the extraction process from the xml files.
     * @param withDBPedia This determines whether dbPedia information is extracted or not.
     * @param outputPath This is the path to the output file.
     */
    public void extract(boolean withDBPedia, String outputPath)
    {
        try
        {
        	// Read the ontology into memory.
            Model ontology = readRdf(ONTOLOGY_URL);

            // Go through the list of URLs in the input file and process each xml in turn.
            List<String> urls = readFile(INPUT_PATH);
            for (String url : urls)
            {
                System.out.print("Processing: " + url);
                // Open connection to each URL
                HttpEntity entity;
                try
                {
                    entity = openUrl(url);
                }
                catch (IOException e)
                {
                    System.out.println(" -- MALFORMED URL");
                    continue;
                }
                if ( !isXML(entity))
                {
                    System.out.println(" -- NOT XML");
                    continue;
                }
                // Get content of the document.
                Document xml = readXml(entity.getContent());
                // Create an instance of RdfBuilder to extract the data.
                RdfBuilder extractor = createRdfBuilder(ontology, xml, url, withDBPedia);
                // Extract the data.
                extractor.extract();
                System.out.println(" -- DONE");
                delay();
            }
            System.out.println("Writing ontology");
            // Write rdf triples to the output file.
            ontology.write(new FileOutputStream(outputPath), OUTPUT_MODE);
            // Handle processing if dbPedia is set to true.
            if (withDBPedia)
            {
                System.out.println("Generating html");
                // Generate all the necessary HTML files.
                new IndexGenerator(ontology).generate();
                new ArtistGenerator(ontology).generate();
                new VenueGenerator(ontology).generate();
            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
    }
    
    /**
     * This function handles the delay between requests to the server.
     */
    public static void delay()
    {
        try
        {
            Thread.sleep(DELAY_MS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * This function handles the processing of xml files depending on the type of xml file
     * @param ontology Takes in an ontology
     * @param xml Takes in an xml document.
     * @param url Takes in a url of the xml document.
     * @param withWebservices Takes in a boolean value determining whether dbPedia extraction is done.
     * @return returns an RdfBuilder instance.
     */
    public RdfBuilder createRdfBuilder(Model ontology, Document xml, String url, boolean withWebservices)
    {
    	// Create instance of the appropriate superclass.
        if (url.contains("album"))
        {
            return new AlbumBuilder(ontology, xml, url, withWebservices);
        }
        if (url.contains("artist"))
        {
            return new ArtistBuilder(ontology, xml, url, withWebservices);
        }
        if (url.contains("user"))
        {
            return new UserBuilder(ontology, xml, url, withWebservices);
        }
        if (url.contains("gig"))
        {
            return new GigBuilder(ontology, xml, url, withWebservices);
        }
        if (url.contains("venue"))
        {
            return new VenueBuilder(ontology, xml, url, withWebservices);
        }
        throw new IllegalArgumentException("Extractor unavaialble for " + url);
    }

    /**
     * This function reads a file and returns each line as a String
     * @param inputPath Takes in the path to the file.
     * @return Returns a list of URLs from the input file.
     * 
     */
    public List<String> readFile(String inputPath) throws FileNotFoundException
    {
        File inputFile = new File(inputPath);
        Scanner scanner = new Scanner(inputFile);

        List<String> urls = new ArrayList<String>();
        while (scanner.hasNext())
        {
            urls.add(scanner.nextLine());
        }
        return urls;
    }

    /**
     * This function creates a Document in memory for a given xml file.
     * @param is Takes in an input stream for the xml file.
     * @return returns a Document containing the xml file.
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public Document readXml(InputStream is) throws SAXException, IOException, ParserConfigurationException
    {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(is);
        return doc;
    }

    /**
     * This function determins if an entity is an XML file.
     * @param entity Takes in an HttpEntity.
     * @return Returns true if the entity is an xml file.
     */
    public boolean isXML(HttpEntity entity)
    {
        return entity.getContentType().getValue().equals(XML_TYPE);
    }

    /**
     * This function opens a connection the URL.
     * @param url Takes in a URL.
     * @return Returns a connection to the given URL.
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpEntity openUrl(String url) throws ClientProtocolException, IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        return entity;
    }

    /**
     * This function reads in an ontology
     * @param in Takes an InputStream to the ontology.
     * @return Returns a Jena Model containing the ontology that has been read in.
     */
    public Model readRdf(InputStream in){
        Model model = ModelFactory.createDefaultModel();
        
        // read the RDF/XML file
        model.read(in, null);
        model.setNsPrefix("com4280", RdfBuilder.RDF_BASE);
        
        return model;
        
    }
    
    /**
     * This function reads an ontology into memory.
     * @param ontologyUrl Takes in the URL of the ontology.
     * @return Returns a Jena model containing the ontology that has been read in.
     * @throws IllegalStateException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Model readRdf(String ontologyUrl) throws IllegalStateException, ClientProtocolException, IOException
    {
        return readRdf(openUrl(ontologyUrl).getContent());
    }
}
