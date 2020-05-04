package edu.mit.jwi.test;

import edu.mit.jwi.data.parse.SenseKeyParser;
import edu.mit.jwi.item.ISenseEntry;
import edu.mit.jwi.item.ISenseKey;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.SynsetID;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TestLib
{
	public static ISynset sensekey(JWI jwi, String skstr) throws IOException
	{
		ISenseKey sk = SenseKeyParser.getInstance().parseLine(skstr);
		assertEquals(sk.toString(), skstr);

		return sensekey(jwi, sk);
	}

	public static ISynset sensekey(JWI jwi, ISenseKey sk) throws IOException
	{
		System.out.println("● sensekey=" + sk);
		ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
		if (senseEntry == null)
			throw new IllegalArgumentException(sk.toString());
		int offset = senseEntry.getOffset();
		SynsetID sid = new SynsetID(offset, sk.getPOS());
		return jwi.getDict().getSynset(sid);
	}

	public static void sensekeys(JWI jwi) throws IOException
	{
		AtomicInteger errorCount = new AtomicInteger();
		jwi.forallSenses((sense) -> {
			ISenseKey sk = sense.getSenseKey();
			ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
			if (senseEntry == null)
			{
				System.err.println("● sense = " + sense.toString() + " sensekey=" + sk + " expected but not found");
				//throw new IllegalArgumentException(sk.toString());
				errorCount.getAndIncrement();
			}
			return null;
		});
		assertEquals(0, errorCount.get());
	}
}
