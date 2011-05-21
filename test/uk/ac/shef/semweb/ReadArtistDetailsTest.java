package uk.ac.shef.semweb;


import junit.framework.TestCase;

import org.junit.Before;

import semweb.builder.ReadArtistDetails;

public class ReadArtistDetailsTest extends TestCase {
	
	private ReadArtistDetails readArtistDetails;

	@Override
	@Before
	public void setUp() throws Exception {
		this.readArtistDetails = new ReadArtistDetails();
	}
	
	public void testGetArtistNames()
	{
		this.readArtistDetails.readDetails();
	}

}
