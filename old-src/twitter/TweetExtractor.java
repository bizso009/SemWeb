<<<<<<< HEAD
package uk.ac.shef.semweb;
=======
package uk.ac.shef.semweb.twitter;
>>>>>>> 70bbf423ca571db117cd609f58da84978c7c4307

/**
 *
 * @author esh
 */
interface TweetExtractor {
    /** given an artist name it returns a list of 5 tweets */
    public TweetClass[] getTweetsAboutArtist(String artistName);
}
