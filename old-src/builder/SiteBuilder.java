package uk.ac.shef.semweb.builder;

import java.io.File;

/**
 *
 * @author esh
 */
public class SiteBuilder {
    public void doBuild(){
        buildSite();
    }
    
    private void buildSite(){
        buildIndex();
        //foreach user
            
        //end foreach
        //foreach artist
            buildArtist();
        //end foreach
        //foreach venue
            buildVenue();
        //end
    }
    
    private void buildIndex(){
        
    }
    
    private void buildArtist(){
        
    }
    
    private void buildVenue(){
        
    }
}