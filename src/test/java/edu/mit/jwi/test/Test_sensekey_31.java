package edu.mit.jwi.test;

import edu.mit.jwi.item.Word;
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
		Word.setCheckLexicalId(true);
		jwi = new JWI(wnHome);
	}

	@Test public void sensekey()
	{
		String skStr = "galore%5:00:00:many:00";
		TestLib.sensekey(jwi, skStr);
	}

	@Test public void sensekeys()
	{
		TestLib.sensekeys(jwi);
	}
}