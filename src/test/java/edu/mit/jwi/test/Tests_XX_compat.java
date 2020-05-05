package edu.mit.jwi.test;

import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class Tests_XX_compat
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX_compat" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		Word.setCheckLexicalId(false);
		jwi = new JWI(wnHome);
	}

	@Test public void allSenses()
	{
		jwi.tryForAllSenses(null);
	}

	@Test public void allSensesNonNull()
	{
		jwi.tryForAllSenses((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSynsets()
	{
		jwi.tryForAllSynsets(null);
	}

	@Test public void allSynsetsNonNull()
	{
		jwi.tryForAllSynsets((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSenseEntries()
	{
		jwi.tryForAllSenseEntries(null);
	}

	@Test public void allSenseEntriesNonNull()
	{
		jwi.tryForAllSenseEntries((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allLemmasNonNull()
	{
		jwi.forAllLemmas((s) -> {
			assertNotNull(s);
			assertFalse(s.isEmpty());
			return null;
		});
	}

	@Test public void allSensekeysNonNull()
	{
		jwi.forAllSensekeys((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSynsetRelationsNonNull()
	{
		jwi.forAllSynsetRelations((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSenseRelationsNonNull()
	{
		jwi.forAllSenseRelations((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	// the test involves new is_caused_by
	@Test public void extraRelations()
	{
		jwi.walk("spread");
	}

	// the test involves Young (n) and adj
	@Test public void cased()
	{
		jwi.walk("young");
	}

	// the test involves adj
	@Test public void adjSat()
	{
		jwi.walk("small");
	}

	// the test involves galore (a)
	@Test public void adjMarker()
	{
		jwi.walk("galore");
	}

	// the test involves a frameless entry
	@Test public void frameless()
	{
		jwi.getDict().getIndexWord("fangirl", POS.VERB);
		jwi.walk("fangirl", POS.VERB);
	}
}