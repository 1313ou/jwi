package edu.mit.jwi.data.compare;

public class Comparators
{
	/**
	 * Case-sensitive index processing.
	 */
	public static class CaseSensitiveIndexLineComparator extends IndexLineComparator
	{
		private static final CaseSensitiveIndexLineComparator INSTANCE = new CaseSensitiveIndexLineComparator();

		public static CaseSensitiveIndexLineComparator getInstance()
		{
			return INSTANCE;
		}

		protected CaseSensitiveIndexLineComparator()
		{
			super(CommentComparator.getInstance());
		}

		@Override protected int compareLemmas(String lemma1, String lemma2)
		{
			return lemma1.compareTo(lemma2);
		}
	}

	/**
	 * Like ignore case, but in case of ignore-case equals, further case-sensitive processing
	 * comparison is attempted.
	 */
	public static class LexicographicOrderSenseKeyLineComparator extends SenseKeyLineComparator
	{
		private static final LexicographicOrderSenseKeyLineComparator INSTANCE = new LexicographicOrderSenseKeyLineComparator();

		public static LexicographicOrderSenseKeyLineComparator getInstance()
		{
			return INSTANCE;
		}

		protected LexicographicOrderSenseKeyLineComparator()
		{
			super();
		}

		@Override protected int compareSenseKeys(String senseKey1, String senseKey2)
		{
			int c = senseKey1.compareToIgnoreCase(senseKey2);
			if (c != 0)
				return c;
			return -senseKey1.compareTo(senseKey2);
		}
	}
}
