package edu.mit.jwi.test;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class JWITestXX
{
	@Test public void mainTestXX() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		boolean result = JWI.run(wnHome, "spread");
		assertTrue(result);
	}
}