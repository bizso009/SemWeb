package uk.ac.shef.semweb;

import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class VenueBuilder extends RdfBuilder
{

    public VenueBuilder(Model ontology, Document xml, String url)
    {
        super(ontology, xml, url, false);
    }

    @Override
    public void extractXml()
    {
        Resource venueRes = this.ontology.createResource(getUri());
        venueRes.addProperty(RDF.type, this.venueClas);

        venueRes.addProperty(this.websiteProp, getSingleProp(this.websiteNode));
        venueRes.addProperty(this.nameProp, getSingleProp(this.nameNode));
        venueRes.addProperty(this.descriptionProp, getSingleProp(this.descriptionNode));
        venueRes.addProperty(this.imageProp, getSingleProp(this.imageNode));

        //add gigs
        for (int i = 0; i < this.gigNodes.getLength(); i++ )
        {
            Node gigNode = this.gigNodes.item(i);

            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.gigClas);
            gigRes.addProperty(this.titleProp, getSingleProp(gigNode));

            venueRes.addProperty(this.gigProp, gigRes);

        }

    
        //TODO get from dbpedia
        // has wikiPage, has category, has geoLon, has geoLat
    }

    @Override
    public void extractWebServices()
    {
        // TODO Auto-generated method stub
        
    }
}
