package uk.ac.shef.semweb;

import org.w3c.dom.Document;

import com.hp.hpl.jena.rdf.model.Model;

public class UserExtractor extends FileExtractor {

    public UserExtractor(Model ontology, Document xml) {
	super(ontology, xml);
    }

    @Override
    public void extract() {
	// TODO Auto-generated method stub
	
    }

}