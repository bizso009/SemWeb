package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class ArtistBuilder extends RdfBuilder 
{
	private Resource artistRes;
	
    public ArtistBuilder(Model ontology, Document xml, String url, boolean withWebServices) 
    {
    	super(ontology, xml, url, withWebServices);
    }

    @Override
    public void extractXml() 
    {
        this.artistRes = this.ontology.createResource(getUri());
        this.artistRes.addProperty(RDF.type, this.artistClas);

        this.artistRes.addProperty(this.nameProp, getSingleProp(this.titleNode));
        this.artistRes.addProperty(this.biographyProp, getSingleProp(this.biographyNode));
        this.artistRes.addProperty(this.imageProp, getSingleProp(this.imageNode));
        this.artistRes.addProperty(this.websiteProp, getSingleProp(this.websiteNode));

        for (int i=0; i<this.albumNodes.getLength(); i++){
            Node albumNode = this.albumNodes.item(i);
            Resource albumRes = this.ontology.createResource(getUrlAttr(albumNode));
            albumRes.addProperty(RDF.type, this.albumClas);
            this.artistRes.addProperty(this.albumProp, albumRes);
            
            albumRes.addProperty(this.titleProp, getSingleProp(albumNode));
        }
        
        //TODO dbpedia
        //genre, associatedband, wikipage,hometown
    }

    @Override
    public void extractWebServices()
    {
        String dbLink = getSingleProp(this.dbpediaNode);
        
        ResultSet rs = this.queryDBpedia(dbLink, this.dbpediaGenre);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.genreProp, dbpediaDescription(res));
        }
        Extractor.delay();
        //TODO workaround framwork bug WstxEOFException for http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/663/xml
        rs = this.queryDBpedia(dbLink, this.dbpediaAssociatedBand);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.associatedBandProp, dbpediaDescription(res));
        }
        Extractor.delay();
        
        rs = this.queryDBpedia(dbLink, this.dbpediaWikiPage);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.wikiPageProp, res.toString());
        }
        Extractor.delay();
        
        rs = this.queryDBpedia(dbLink, this.dbpediaHomeTown);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.homeTownProp, dbpediaDescription(res));
        }
        Extractor.delay();
                
    }
}
