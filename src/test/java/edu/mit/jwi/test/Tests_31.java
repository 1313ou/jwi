package edu.mit.jwi.test;

import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class Tests_31
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOME31" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		Word.setCheckLexicalId(true);
		jwi = new JWI(wnHome);
	}

	@Test public void allSenses()
	{
		jwi.forAllSenses(null);
	}

	@Test public void allSensesNonNull()
	{
		jwi.forAllSenses((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSynsets()
	{
		jwi.forAllSynsets(null);
	}

	@Test public void allSynsetsNonNull()
	{
		jwi.forAllSynsets((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSenseEntries()
	{
		jwi.forAllSenseEntries(null);
	}

	@Test public void allSenseEntriesNonNull()
	{
		jwi.forAllSenseEntries((s) -> {
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
}