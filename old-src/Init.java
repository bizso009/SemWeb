package uk.ac.shef.semweb;

import semweb.builder.DirStructBuilder;
import semweb.twitter.TweetExtractorImpl;

/**
 *
 * @author esh
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DirStructBuilder().doBuild();
        new TweetExtractorImpl().getTweetsAboutArtist(null);
    }
}
