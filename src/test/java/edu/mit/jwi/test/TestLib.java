package edu.mit.jwi.test;

import edu.mit.jwi.data.parse.DataLineParser;
import edu.mit.jwi.data.parse.SenseKeyParser;
import edu.mit.jwi.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestLib
{
	public static boolean sensekeyFromStringIsLive(JWI jwi, String skStr)
	{
		ISenseKey sk = SenseKeyParser.getInstance().parseLine(skStr);
		assertEquals(sk.toString(), skStr);
		return sensekeyIsLive(jwi, sk);
	}

	public static boolean sensekeyIsLive(JWI jwi, ISenseKey sk)
	{
		//System.out.println("● sensekey=" + sk);
		ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
		if (senseEntry == null)
			return false;
		int offset = senseEntry.getOffset();
		SynsetID sid = new SynsetID(offset, sk.getPOS());
		return jwi.getDict().getSynset(sid) != null;
	}

	public static void listDeadSensekeys(JWI jwi)
	{
		AtomicInteger errorCount = new AtomicInteger();
		jwi.forAllSenses((sense) -> {
			ISenseKey sk = sense.getSenseKey();
			boolean isLive = sensekeyIsLive(jwi, sk);
			if (!isLive)
			{
				System.err.println("☈ sense = " + sense.toString() + " generated sensekey=" + sk + " not found");
				//throw new IllegalArgumentException(sk.toString());
				errorCount.getAndIncrement();
			}
		});
		assertEquals(0, errorCount.get());
	}

	public static void allSensekeysAreLive(JWI jwi)
	{
		jwi.forAllSensekeys((sk) -> {
			assertNotNull(sk);
			ISenseEntry senseEntry = jwi.getDict().getSenseEntry(sk);
			assertNotNull(senseEntry);
			int offset = senseEntry.getOffset();
			SynsetID sid = new SynsetID(offset, sk.getPOS());
			assertNotNull(sid);
			ISynset synset = jwi.getDict().getSynset(sid);
			assertNotNull(synset);
		});
	}

	public static void allSenseEntriesAreLive(JWI jwi)
	{
		jwi.forAllSenseEntries((se) -> {
			assertNotNull(se);
			int offset = se.getOffset();
			POS pos = se.getPOS();
			ISynsetID sid = new SynsetID(offset, pos);
			assertNotNull(sid);
			ISynset synset = jwi.getDict().getSynset(sid);
			assertNotNull(synset);
		});
	}

	public static List<String> parseDataLineIntoMembers(String line)
	{
		List<String> result = new ArrayList<>();
		DataLineParser parser = DataLineParser.getInstance();
		ISynset synset = parser.parseLine(line);
		for (IWord sense : synset.getWords())
		{
			result.add(String.format("%s %s %d", sense, sense.getLemma(), sense.getLexicalID()));
		}
		return result;
	}
}
