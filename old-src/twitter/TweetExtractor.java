package uk.ac.shef.semweb;

/**
 *
 * @author esh
 */
interface TweetExtractor {
    /** given an artist name it returns a list of 5 tweets */
    public TweetClass[] getTweetsAboutArtist(String artistName);
}
