package edu.mit.jwi.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class Test_sensekey_31
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOME31" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		jwi = new JWI(wnHome);
	}

	@Test public void sensekey() throws IOException
	{
		String skStr = "galore%5:00:00:many:00";
		TestLib.sensekey(jwi, skStr);
	}

	@Test public void sensekeys() throws IOException
	{
		TestLib.sensekeys(jwi);
	}
}