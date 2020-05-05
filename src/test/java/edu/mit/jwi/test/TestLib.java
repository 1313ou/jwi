package edu.mit.jwi.test;

import edu.mit.jwi.data.parse.SenseKeyParser;
import edu.mit.jwi.item.ISenseEntry;
import edu.mit.jwi.item.ISenseKey;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.SynsetID;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TestLib
{
	public static ISynset sensekey(JWI jwi, String skStr)
	{
		ISenseKey sk = SenseKeyParser.getInstance().parseLine(skStr);
		assertEquals(sk.toString(), skStr);

		return sensekey(jwi, sk);
	}

	public static ISynset sensekey(JWI jwi, ISenseKey sk)
	{
		System.out.println("● sensekey=" + sk);
		ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
		if (senseEntry == null)
			throw new IllegalArgumentException(sk.toString());
		int offset = senseEntry.getOffset();
		SynsetID sid = new SynsetID(offset, sk.getPOS());
		return jwi.getDict().getSynset(sid);
	}

	public static void sensekeys(JWI jwi)
	{
		AtomicInteger errorCount = new AtomicInteger();
		jwi.forAllSenses((sense) -> {
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
