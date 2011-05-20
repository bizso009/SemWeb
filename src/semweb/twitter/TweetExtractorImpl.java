/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semweb.twitter;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author esh
 */
public class TweetExtractorImpl implements TweetExtractor{

    @Override
    public TweetClass[] getTweetsAboutArtist(String artistName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //TODO Add user as variable
            URL url = new URL("http://api.twitter.com/1/statuses/user_timeline.xml?screen_name=intelliUser9&count=200");
            InputStream inputStream = url.openStream();
            Document document = db.parse(inputStream);
            inputStream.close();
            System.out.println(document.getFirstChild().getNodeName());
        } catch (Exception ex) {
            Logger.getLogger(TweetExtractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
