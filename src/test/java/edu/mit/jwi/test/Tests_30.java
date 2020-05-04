package edu.mit.jwi.test;

import edu.mit.jwi.item.POS;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class Tests_30
{
	private static JWI jwi;

	@BeforeClass public static void init() throws IOException
	{
		String wnHome = System.getenv("WNHOMEXX" /* + File.separator + "dict" */);
		System.out.printf("FROM %s%n", wnHome);
		jwi = new JWI(wnHome);
	}

	@Test public void allSenses() throws IOException
	{
		jwi.forallSenses(null);
	}

	@Test public void allSynsets() throws IOException
	{
		jwi.forallSynsets(null);
	}

	@Test public void allSenseEntries() throws IOException
	{
		jwi.forallSenseEntries(null);
	}

	@Test public void allSensesNonNull() throws IOException
	{
		jwi.forallSenses((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSensekeysNonNull() throws IOException
	{
		jwi.forallSensekeys((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allLemmasNonNull() throws IOException
	{
		jwi.forallLemmas((s) -> {
			assertNotNull(s);
			assertFalse(s.isEmpty());
			return null;
		});
	}

	@Test public void allSynsetsNonNull() throws IOException
	{
		jwi.forallSynsets((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSenseEntriesNonNull() throws IOException
	{
		jwi.forallSenseEntries((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSenseRelationsNonNull() throws IOException
	{
		jwi.forallSenseRelations((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	@Test public void allSynsetRelationsNonNull() throws IOException
	{
		jwi.forallSynsetRelations((s) -> {
			assertNotNull(s);
			return null;
		});
	}

	// the test involves new is_caused_by
	@Test public void extraRelations() throws IOException
	{
		jwi.walk("spread");
	}

	// the test involves Young (n) and adj
	@Test public void cased() throws IOException
	{
		jwi.walk("young");
	}

	// the test involves adj
	@Test public void adjSat() throws IOException
	{
		jwi.walk("small");
	}

	// the test involves galore (a)
	@Test public void adjMarker() throws IOException
	{
		jwi.walk("galore");
	}

	// the test involves a frameless entry
	@Test public void frameless() throws IOException
	{
		jwi.getDict().getIndexWord("fangirl", POS.VERB);
		jwi.walk("fangirl", POS.VERB);
	}
}