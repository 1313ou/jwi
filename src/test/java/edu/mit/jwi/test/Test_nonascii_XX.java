package edu.mit.jwi.test;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Test_nonascii_XX
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX_contrib" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		jwi = new JWI(wnHome);
	}

	@Test public void nonascii() throws IOException
	{
		final IIndexWord idx = jwi.getDict().getIndexWord("Wałęsa", POS.NOUN);
		final List<IWordID> senseids = idx.getWordIDs();
		for (final IWordID senseid : senseids) // synset id, sense number, and lemma
		{
			// sense
			final IWord sense = jwi.getDict().getWord(senseid);
			System.out.println("● sense = " + sense.toString() + " sensekey=" + sense.getSenseKey());

			// synset
			final ISynsetID synsetid = senseid.getSynsetID();
			final ISynset synset = jwi.getDict().getSynset(synsetid);
			final String members = JWI.getMembers(synset);
			System.out.println("● synset = " + members + synset.getGloss());
			assertTrue(members.contains("Wałęsa"));
			assertTrue(members.contains("Lech_Wałęsa"));
			assertTrue(members.contains("Walesa"));
			assertTrue(members.contains("Lech_Walesa"));
		}
	}
}