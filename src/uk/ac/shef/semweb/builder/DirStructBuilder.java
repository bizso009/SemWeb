/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.shef.semweb.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esh
 */
public class DirStructBuilder {
    public void doBuild(){
        buildDirStructure();
        copyStaticElements();
    }
    
    private void buildDirStructure(){
        String folders[] = new String[6];
	folders[0] = "Output";
	folders[1] = "Output/Website";
	folders[2] = "Output/Website/static";
	folders[3] = "Output/Website/static/img";
	folders[4] = "Output/Website/artists";
	folders[5] = "Output/Website/venues";
        for(String str : folders){
            File current = new File(str);
            if(!current.exists()) current.mkdirs();
        }
    }
    
    private void copyStaticElements(){
        HashMap files = new HashMap();
        files.put("/semweb/builder/templates/staticfiles/style.css", "Output/Website/static/style.css");
        files.put("/semweb/builder/templates/staticfiles/body_bg.jpg", "Output/Website/static/img/body_bg.jpg");
        files.put("/semweb/builder/templates/staticfiles/header_bg.jpg", "Output/Website/static/img/header_bg.jpg");
        Set set = files.entrySet();

        Iterator i = set.iterator();

        while(i.hasNext()){
          Map.Entry me = (Map.Entry)i.next();
          writeFile((String)me.getKey(), (String)me.getValue());
        }
    }
    
    private void writeFile(String inFile, String outFile){
        try {
            InputStream instream = getClass().getResourceAsStream(inFile);
            OutputStream outstream=new FileOutputStream(new File(outFile));
            byte buf[]=new byte[1024];
            int len;
            while((len=instream.read(buf))>0)
            outstream.write(buf,0,len);
            outstream.close();
            instream.close();
        } catch (IOException ex) {
            Logger.getLogger(DirStructBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
