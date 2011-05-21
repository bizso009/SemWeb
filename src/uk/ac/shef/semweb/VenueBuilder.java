package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class VenueBuilder extends RdfBuilder
{

    private Resource venueRes;
    public VenueBuilder(Model ontology, Document xml, String url, boolean withWebSerives)
    {
        super(ontology, xml, url, withWebSerives);
    }

    @Override
    public void extractXml()
    {
        this.venueRes = this.ontology.createResource(getUri());
        this.venueRes.addProperty(RDF.type, this.properties.venueClas);

        this.venueRes.addProperty(this.properties.websiteProp, getSingleProp(this.websiteNode));
        this.venueRes.addProperty(this.properties.nameProp, getSingleProp(this.nameNode));
        this.venueRes.addProperty(this.properties.descriptionProp, getSingleProp(this.descriptionNode));
        this.venueRes.addProperty(this.properties.imageProp, getSingleProp(this.imageNode));

        //add gigs
        for (int i = 0; i < this.gigNodes.getLength(); i++ )
        {
            Node gigNode = this.gigNodes.item(i);

            Resource gigRes = this.ontology.createResource(getUrlAttr(gigNode));
            gigRes.addProperty(RDF.type, this.properties.gigClas);
            gigRes.addProperty(this.properties.titleProp, getSingleProp(gigNode));

            this.venueRes.addProperty(this.properties.gigProp, gigRes);

        }

        //TODO get from dbpedia
        // has wikiPage, has category, has geoLon, has geoLat
    }

    @Override
    public void extractWebServices()
    {
        String dbLink = getSingleProp(this.dbpediaNode);

        ResultSet rs = this.queryDBpedia(dbLink, this.dbpediaCategory);
        while (rs.hasNext())
        {
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.venueRes.addProperty(this.properties.categoryProp, dbpediaDescription(res));
        }
        Extractor.delay();


        rs = this.queryDBpedia(dbLink, this.dbpediaWikiPage);
        while (rs.hasNext())
        {
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.venueRes.addProperty(this.properties.wikiPageProp, res.toString());
        }
        Extractor.delay();

        rs = this.queryDBpedia(dbLink, this.dbpediaGeoLat);
        while (rs.hasNext())
        {
            Literal res = rs.next().get(this.dbpediaVAR).asLiteral();
            this.venueRes.addProperty(this.properties.geoLatProp, new Float(res.getFloat()).toString());
        }
        Extractor.delay();
        
        rs = this.queryDBpedia(dbLink, this.dbpediaGeoLon);
        while (rs.hasNext())
        {
            Literal res = rs.next().get(this.dbpediaVAR).asLiteral();
            this.venueRes.addProperty(this.properties.geoLonProp, new Float(res.getFloat()).toString());
        }
        Extractor.delay();
    }
}
