package edu.mit.jwi.test;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class JWITest
{
	@Test public void doneTest()
	{
		assertTrue(true);
		System.out.println("Done");
	}

	@Test public void mainTest30() throws IOException
	{
		String wnHome = System.getenv("WNHOME30" /* + File.separator + "dict" */);
		boolean result = JWI.run(wnHome);
		assertTrue(result);
	}

	@Test public void mainTest31() throws IOException
	{
		String wnHome = System.getenv("WNHOME31" /* + File.separator + "dict" */);
		boolean result = JWI.run(wnHome);
		assertTrue(result);
	}

	@Test public void mainTestXX() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		boolean result = JWI.run(wnHome);
		assertTrue(result);
	}
}