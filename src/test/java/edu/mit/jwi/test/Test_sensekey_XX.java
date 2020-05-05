package edu.mit.jwi.test;

import edu.mit.jwi.data.parse.DataLineParser;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class Test_sensekey_XX
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		jwi = new JWI(wnHome);
	}

	@Test public void sensekey()
	{
		String skStr = "galore%5:00:02:many:00";
		TestLib.sensekey(jwi, skStr);
	}

	//find:   hot%5:00:03:warm:03
	//exists: hot%5:00:19:warm:03				19->3

	@Test public void find_sensekey_non_compat_lexid()
	{
		String skStr = "hot%5:00:19:warm:03";
		ISynset synset = TestLib.sensekey(jwi, skStr);
		for (IWord sense : synset.getWords())
		{
			System.out.printf("%s %s %d%n",sense, sense.getLemma(), sense.getLexicalID());
			//TestLib.sensekey(jwi, sense.getSenseKey());
		}
	}

	@Test public void parse_non_compat_lexid()
	{
		String line = "02504828 00 s 01 hot 13 001 & 02504619 a 0000 | (color) bold and intense; \"hot pink\"";
		              //"02504839 00 s 01 hot 13 001 & 02504630 a 0000 | (color) bold and intense; \"hot pink\"";"
		DataLineParser parser = DataLineParser.getInstance();
		ISynset synset = parser.parseLine(line);
		for (IWord sense : synset.getWords())
		{
			System.out.printf("%s %s %d%n",sense, sense.getLemma(), sense.getLexicalID());
			//TestLib.sensekey(jwi, sense.getSenseKey());
		}
	}

	@Test public void sensekeys()
	{
		TestLib.sensekeys(jwi);
	}
}