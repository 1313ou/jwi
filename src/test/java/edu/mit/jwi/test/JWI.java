package edu.mit.jwi.test;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * JWI
 *
 * @author Bernard Bou
 */
public class JWI
{
	private final IDictionary dict;

	/**
	 * Main
	 *
	 * @param args arguments
	 * @throws IOException io exception
	 */
	public static void main(final String[] args) throws IOException
	{
		final String wnHome = args[0];
		final String lemma = args[1];
		new JWI(wnHome).walk(lemma);
	}

	public JWI(final String wnHome) throws IOException
	{
		// construct the URL to the WordNet dictionary directory
		URL url = new File(wnHome).toURI().toURL();

		// construct the dictionary object and open it
		this.dict = new Dictionary(url);
		this.dict.setCharset(StandardCharsets.UTF_8);
		this.dict.open();
	}

	public IDictionary getDict()
	{
		return dict;
	}

	// M A I N   I T E R A T I O N S

	public <R> void forAllSenses(final Function<IWord, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<IIndexWord> it = this.dict.getIndexWordIterator(pos);
			while (it.hasNext())
			{
				IIndexWord idx = it.next();
				final List<IWordID> senseids = idx.getWordIDs();
				for (final IWordID senseid : senseids) // synset id, sense number, and lemma
				{
					IWord sense = this.dict.getWord(senseid);
					if (sense == null)
					{
						System.err.println("⚠ senseid = " + senseid.toString() + " ➜ null sense");
						//IWord sense2 = this.dict.getWord(senseid);
						continue;
					}
					if (f != null)
					{
						/*R r =*/
						f.apply(sense);
					}
				}
			}
		}
	}

	public <R> void tryForAllSenses(final Function<IWord, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<IIndexWord> it = this.dict.getIndexWordIterator(pos);
			while (it.hasNext())
			{
				try
				{
					IIndexWord idx = it.next();
					final List<IWordID> senseids = idx.getWordIDs();
					for (final IWordID senseid : senseids) // synset id, sense number, and lemma
					{
						IWord sense = this.dict.getWord(senseid);
						if (sense == null)
						{
							System.err.println("⚠ senseid = " + senseid.toString() + " ➜ null sense");
							//IWord sense2 = this.dict.getWord(senseid);
							continue;
						}
						if (f != null)
						{
							/*R r =*/
							f.apply(sense);
						}
					}
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}

			}
		}
	}

	public <R> void forAllSynsets(final Function<ISynset, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<ISynset> it = this.dict.getSynsetIterator(pos);
			while (it.hasNext())
			{
				ISynset synset = it.next();
				if (f != null)
				{
					/*R r =*/
					f.apply(synset);
				}
			}
		}
	}

	public <R> void tryForAllSynsets(final Function<ISynset, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<ISynset> it = this.dict.getSynsetIterator(pos);
			while (it.hasNext())
			{
				try
				{
					ISynset synset = it.next();
					if (f != null)
					{
						/*R r =*/
						f.apply(synset);
					}
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public <R> void forAllSenseEntries(final Function<ISenseEntry, R> f)
	{
		Iterator<ISenseEntry> it = this.dict.getSenseEntryIterator();
		while (it.hasNext())
		{
			ISenseEntry entry = it.next();
			if (f != null)
			{
				/*R r =*/
				f.apply(entry);
			}
		}
	}

	public <R> void tryForAllSenseEntries(final Function<ISenseEntry, R> f)
	{
		Iterator<ISenseEntry> it = this.dict.getSenseEntryIterator();
		while (it.hasNext())
		{
			try
			{
				ISenseEntry entry = it.next();
				if (f != null)
				{
					/*R r =*/
					f.apply(entry);
				}
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
	}

	// S P E C I F I C   I T E R A T I O N S

	public <R> void forAllLemmas(final Function<String, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<IIndexWord> it = this.dict.getIndexWordIterator(pos);
			while (it.hasNext())
			{
				IIndexWord idx = it.next();
				final List<IWordID> senseids = idx.getWordIDs();
				for (final IWordID senseid : senseids) // synset id, sense number, and lemma
				{
					IWord sense = this.dict.getWord(senseid);
					if (sense == null)
					{
						System.err.println("⚠ senseid = " + senseid.toString() + " ➜ null sense");
						// IWord sense2 = this.dict.getWord(senseid);
						continue;
					}
					String lemma = sense.getLemma();
					if (f != null)
					{
						/* R r =*/
						f.apply(lemma);
					}
				}
			}
		}
	}

	public <R> void forAllSensekeys(final Function<ISenseKey, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<IIndexWord> it = this.dict.getIndexWordIterator(pos);
			while (it.hasNext())
			{
				IIndexWord idx = it.next();
				final List<IWordID> senseids = idx.getWordIDs();
				for (final IWordID senseid : senseids) // synset id, sense number, and lemma
				{
					IWord sense = this.dict.getWord(senseid);
					if (sense == null)
					{
						System.err.println("⚠ senseid = " + senseid.toString() + " ➜ null sense");
						//IWord sense2 = this.dict.getWord(senseid);
						continue;
					}
					ISenseKey sensekey = sense.getSenseKey();
					if (f != null)
					{
						/*R r =*/
						f.apply(sensekey);
					}
				}
			}
		}
	}

	public <R> void forAllSynsetRelations(final Function<ISynset, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<ISynset> it = this.dict.getSynsetIterator(pos);
			while (it.hasNext())
			{
				ISynset synset = it.next();
				List<ISynsetID> relatedIds = synset.getRelatedSynsets();
				for (ISynsetID relatedId : relatedIds)
				{
					ISynset related = this.dict.getSynset(relatedId);
					if (f != null)
					{
						/*R r =*/
						f.apply(related);
					}
				}
			}
		}
	}

	public <R> void forAllSenseRelations(final Function<IWord, R> f)
	{
		for (final POS pos : POS.values())
		{
			Iterator<IIndexWord> it = this.dict.getIndexWordIterator(pos);
			while (it.hasNext())
			{
				IIndexWord idx = it.next();
				final List<IWordID> senseids = idx.getWordIDs();
				for (final IWordID senseid : senseids) // synset id, sense number, and lemma
				{
					IWord sense = this.dict.getWord(senseid);
					if (sense == null)
					{
						System.err.println("⚠ senseid = " + senseid.toString() + " ➜ null sense");
						//IWord sense2 = this.dict.getWord(senseid);
						continue;
					}
					List<IWordID> relatedIds = sense.getRelatedWords();
					for (IWordID relatedId : relatedIds)
					{
						IWord related = this.dict.getWord(relatedId);
						if (f != null)
						{
							/*R r =*/
							f.apply(related);
						}
					}
				}
			}
		}
	}

	// T R E E   E X P L O R A T I O N S

	public void walk(final String lemma)
	{
		for (final POS pos : POS.values())
		{
			walk(lemma, pos);
		}
	}

	public void walk(final String lemma, final POS pos)
	{
		// a line in an index file
		final IIndexWord idx = this.dict.getIndexWord(lemma, pos);
		if (idx != null)
		{
			// index
			System.out.println();
			System.out.println("================================================================================");
			System.out.println("■ pos = " + pos.name());
			// System.out.println("lemma = " + idx.getLemma());
			walk(idx);
		}
	}

	public void walk(final IIndexWord idx)
	{
		Set<IPointer> pointers = idx.getPointers();
		for (IPointer ptr : pointers)
		{
			System.out.println("has relation = " + ptr.toString());
		}

		// senseid=(lemma, synsetid, sensenum)
		final List<IWordID> senseids = idx.getWordIDs();
		for (final IWordID senseid : senseids) // synset id, sense number, and lemma
		{
			walk(senseid);
		}
	}

	public void walk(final IWordID senseid)
	{
		System.out.println("--------------------------------------------------------------------------------");
		//System.out.println("senseid = " + senseid.toString());

		// sense=(senseid, lexid, sensekey, synset)
		IWord sense = this.dict.getWord(senseid);
		walk(sense);

		// synset
		final ISynsetID synsetid = senseid.getSynsetID();
		final ISynset synset = this.dict.getSynset(synsetid);
		System.out.println("● synset = " + toString(synset));

		walk(synset, 1);
	}

	public void walk(final IWord sense)
	{
		System.out.println("● sense = " + sense.toString() + " lexid=" + sense.getLexicalID() + " sensekey=" + sense.getSenseKey());

		// adj marker
		AdjMarker marker = sense.getAdjectiveMarker();
		if (marker != null)
			System.out.println("  marker = " + marker);

		// sensekey
		ISenseKey senseKey = sense.getSenseKey();
		ISenseEntry senseEntry = this.dict.getSenseEntry(senseKey);
		if (senseEntry == null)
			throw new IllegalArgumentException(senseKey.toString() + " at offset " + sense.getSynset().getOffset() + " with pos " + sense.getPOS().toString());

		// lexical relations
		Map<IPointer, List<IWordID>> relatedMap = sense.getRelatedMap();
		walk(relatedMap);

		// verb frames
		List<IVerbFrame> verbFrames = sense.getVerbFrames();
		walk(verbFrames, sense.getLemma());

		System.out.println("  sensenum = " + senseEntry.getSenseNumber() + " tag cnt=" + senseEntry.getTagCount());
	}

	public void walk(final Map<IPointer, List<IWordID>> relatedMap)
	{
		if (relatedMap != null)
		{
			for (Map.Entry<IPointer, List<IWordID>> entry : relatedMap.entrySet())
			{
				IPointer pointer = entry.getKey();
				for (IWordID relatedId : entry.getValue())
				{
					IWord related = this.dict.getWord(relatedId);
					System.out.println("  related " + pointer + " = " + related.getLemma() + " synset=" + related.getSynset().toString());
				}
			}
		}
	}

	public void walk(final List<IVerbFrame> verbFrames, final String lemma)
	{
		if (verbFrames != null)
		{
			for (IVerbFrame verbFrame : verbFrames)
				System.out.println("  verb frame = " + verbFrame.getTemplate() + " : " + verbFrame.instantiateTemplate(lemma));
		}
	}

	public void walk(final ISynset synset, final int level)
	{
		final String indentSpace = new String(new char[level]).replace('\0', '\t');
		final Map<IPointer, List<ISynsetID>> links = synset.getRelatedMap();
		for (final IPointer p : links.keySet())
		{
			System.out.println(indentSpace + "🡆 " + p.getName());
			final List<ISynsetID> relations2 = links.get(p);
			walk(relations2, p, level);
		}
	}

	public void walk(final List<ISynsetID> relations2, final IPointer p, final int level)
	{
		final String indentSpace = new String(new char[level]).replace('\0', '\t');
		for (final ISynsetID synsetid2 : relations2)
		{
			final ISynset synset2 = this.dict.getSynset(synsetid2);
			System.out.println(indentSpace + toString(synset2));

			walk(synset2, p, level + 1);
		}
	}

	public void walk(final ISynset synset, final IPointer p, final int level)
	{
		final String indentSpace = new String(new char[level]).replace('\0', '\t');
		final List<ISynsetID> relations2 = synset.getRelatedSynsets(p);
		for (final ISynsetID synsetid2 : relations2)
		{
			final ISynset synset2 = this.dict.getSynset(synsetid2);
			System.out.println(indentSpace + toString(synset2));
			if (canRecurse(p))
				walk(synset2, p, level + 1);
		}
	}

	// H E L P E R S

	public static String toString(final ISynset synset)
	{
		return getMembers(synset) + synset.getGloss();
	}

	public static String getMembers(final ISynset synset)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append('{');
		boolean first = true;
		for (final IWord sense : synset.getWords())
		{
			if (first)
			{
				first = false;
			}
			else
			{
				sb.append(' ');
			}
			sb.append(sense.getLemma());
		}
		sb.append('}');
		sb.append(' ');
		return sb.toString();
	}

	private static boolean canRecurse(IPointer p)
	{
		String symbol = p.getSymbol();
		switch (symbol)
		{
			case "@": // hypernym
			case "~": // hyponym
			case "%p": // part holonym
			case "#p": // part meronym
			case "%m": // member holonym
			case "#m": // member meronym
			case "%s": // substance holonym
			case "#s": // substance meronym
			case "*": // entail
			case ">": // cause
				return true;
		}
		return false;
	}
}
