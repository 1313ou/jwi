package edu.mit.jwi.test;

import edu.mit.jwi.data.parse.SenseKeyParser;
import edu.mit.jwi.item.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JWITestXX_sensekey
{
	@Test public void mainTestXX_sensekey() throws IOException
	{
		String skstr = "galore%5:00:02:many:00";
		ISenseKey sk = SenseKeyParser.getInstance().parseLine(skstr);
		assertEquals(sk.toString(), skstr);
		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		final JWI jwi = new JWI(wnHome);
		System.out.println("● sensekey=" + sk);
		ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
		if (senseEntry == null)
			throw new IllegalArgumentException(sk.toString());
	}

	@Test public void mainTestXX_sensekeys() throws IOException
	{
		String lemma = "galore";
		POS[] poses = POS.values();

		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		final JWI jwi = new JWI(wnHome);
		for (final POS pos : poses)
		{
			// a line in an index file
			final IIndexWord idx = jwi.getDict().getIndexWord(lemma, pos);
			if (idx != null)
			{
				// index
				System.out.println();
				System.out.println("■ pos = " + pos.name());

				// senseid=(lemma, synsetid, sensenum)
				final List<IWordID> senseids = idx.getWordIDs();
				for (final IWordID senseid : senseids) // synset id, sense number, and lemma
				{
					//System.out.println("senseid = " + senseid.toString());

					// sense=(senseid, lexid, sensekey, synset)
					IWord sense = jwi.getDict().getWord(senseid);

					ISenseKey sk = sense.getSenseKey();
					System.out.println("● sense = " + sense.toString() + " lexid=" + sense.getLexicalID() + " sensekey=" + sk);
					ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
					if (senseEntry == null)
						throw new IllegalArgumentException(sk.toString());
				}
			}
		}
	}
}