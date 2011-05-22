package uk.ac.shef.semweb;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class ArtistBuilder extends RdfBuilder 
{
	private Resource artistRes;
	private ResultSet rs;
	
    public ArtistBuilder(Model ontology, Document xml, String url, boolean withWebServices) 
    {
    	super(ontology, xml, url, withWebServices);
    }

    @Override
    public void extractXml() 
    {
        this.artistRes = this.ontology.createResource(getUri());
        this.artistRes.addProperty(RDF.type, this.properties.artistClas);

        this.artistRes.addProperty(this.properties.nameProp, getSingleProp(this.nodes.titleNode));
        this.artistRes.addProperty(this.properties.biographyProp, getSingleProp(this.nodes.biographyNode));
        this.artistRes.addProperty(this.properties.imageProp, getSingleProp(this.nodes.imageNode));
        this.artistRes.addProperty(this.properties.websiteProp, getSingleProp(this.nodes.websiteNode));

        for (int i=0; i<this.nodes.albumNodes.getLength(); i++){
            Node albumNode = this.nodes.albumNodes.item(i);
            Resource albumRes = this.ontology.createResource(getUrlAttr(albumNode));
            albumRes.addProperty(RDF.type, this.properties.albumClas);
            this.artistRes.addProperty(this.properties.albumProp, albumRes);
            
            albumRes.addProperty(this.properties.titleProp, getSingleProp(albumNode));
        }
        
        //TODO dbpedia
        //genre, associatedband, wikipage,hometown
    }

    @Override
    public void extractWebServices()
    {
        dbpediaLink = getSingleProp(this.nodes.dbpediaNode);
        
        setGenre();
        setAssociatedBand();
        
        setWikiPage();
        
        setHomeTown();
                
    }

    private void setHomeTown()
    {
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaHomeTown);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.homeTownProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }

    private void setWikiPage()
    {
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaWikiPage);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.wikiPageProp, res.toString());
        }
        Extractor.delay();
    }

    private void setAssociatedBand()
    {
        //DONE workaround framwork bug WstxEOFException for http://poplar.dcs.shef.ac.uk/~u0082/intelweb2/%3fq=artist/663/xml
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaAssociatedBand);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.associatedBandProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }

    private void setGenre()
    {
        rs = this.queryDBpedia(dbpediaLink, this.dbpediaGenre);
        while (rs.hasNext()){
            Resource res = rs.next().get(this.dbpediaVAR).asResource();
            this.artistRes.addProperty(this.properties.genreProp, dbpediaDescription(res));
        }
        Extractor.delay();
    }
}
