package uk.ac.shef.semweb;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(HtmlGeneratorTest.class);
        suite.addTestSuite(RdfBuilderTest.class);
        suite.addTestSuite(ExtractorTest.class);
        //$JUnit-END$
        return suite;
    }

}
