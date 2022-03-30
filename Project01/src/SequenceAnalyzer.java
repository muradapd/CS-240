import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Sequence analyzer for up to two people at a time. If only one person is
 * passed with a constructor, then the only valid method is findCandidates. The
 * longestSharedSequence and match methods should both throw a GenomeException.
 *
 * @author Michael S. Kirkpatrick
 * @version V1, 8/2018
 */
public class SequenceAnalyzer {

  private static final long MAX = 500000000;

  private Person firstPerson;
  private Person secondPerson;
  private Person person;

  /**
   * Create an instance to analyze two people's gene sequences.
   *
   * @param first  The first person in the list of two.
   * @param second The second person in the list of two.
   */
  public SequenceAnalyzer(Person first, Person second) {
    person = first;
    firstPerson = first;
    secondPerson = second;
  }

  /**
   * Create an instance to analyze a single person's gene sequences.
   *
   * @param person The only person to analyze.
   */
  public SequenceAnalyzer(Person person) {
    this.person = person;
    firstPerson = null;
    secondPerson = null;
  }

  /**
   * Find longest nucleotide sequence. This sequence can appear anywhere in the
   * gene snippet, in either a junk or coding region.
   *
   * @param start The index of the first nucleotide to start looking.
   * @param end   The index of the last nucleotide to look.
   *
   * @return An array of all common substrings in the regions.
   */
  public String[] longestSharedSequence(long start, long end) {
    if (firstPerson == null) {
      throw new GenomeException();
    }

    int length = (int) (end - start);
    String[] sharedSeqs = null;

    if (end - start < MAX) {
      sharedSeqs = retSeqs(firstPerson.getSnippet(start, length),
          secondPerson.getSnippet(start, length));
    } else {
      boolean ended = false;
      long newEnd = end;

      while (end - start > MAX || !ended) {
        if (start + MAX < newEnd) {
          end = start + MAX;
        }
        length = (int) (end - start);
        String snippetA = firstPerson.getSnippet(start, length);
        String snippetB = secondPerson.getSnippet(start, length);

        if (snippetA.charAt(length - 1) != snippetB.charAt(length - 1)) {
          sharedSeqs = retSeqs(snippetA, snippetB);
        } else {
          // TODO method accounting for string splits
        }

        if (end == newEnd) {
          ended = true;
        } else {
          start = end + 1;
          end = newEnd;
        }

      }
    }

    return sharedSeqs;
  }

  /**
   * Find matched regions that satisfy some particular criterion. These criteria
   * should compare pairwise regions from two people and test to see if they match
   * (such as having a certain number of common nucleotides). The matched regions
   * are returned in a priority order,
   *
   * @param start     The index of the first nucleotide to start looking.
   * @param end       The index of the last nucleotide to look.
   * @param condition A binary predicate that all regions must match.
   *
   * @return An array of all common substrings in the regions.
   */
  public WeightedList<Pair<Region, Region>> match(long start, long end,
      BiPredicate<Region, Region> condition) {
    if (firstPerson == null) {
      throw new GenomeException();
    }

    WeightedList<Pair<Region, Region>> matches = new WeightedList<Pair<Region, Region>>();
    Comparator<Pair<Region, Region>> comp = (Pair<Region, Region> p1,
        Pair<Region, Region> p2) -> p1.getFirst().getLength() - p2.getFirst().getLength();

    WeightedList<Region> regions1 = findCandidates(start, end, true, p1 -> p1 != null);
    WeightedList<Region> regions2 = findCandidates(start, end, false, p2 -> p2 != null);

    for (Region r1 : regions1) {
      for (Region r2 : regions2) {
        if (condition.test(r1, r2) && r1.getLocation() == r2.getLocation()) {
          Pair<Region, Region> pair = new Pair<Region, Region>(r1, r2);
          matches.add(pair, comp);

        }
      }
    }

    return matches;

  }

  /**
   * Find candidate gene coding regions that satisfy some criterion. Example
   * criteria include having a particular (or minimum) coding length, containing
   * some codon sequence, etc.
   */
  public WeightedList<Region> findCandidates(long start, long end, boolean first,
      Predicate<Region> condition) {
    Person p;
    if (firstPerson == null) {
      p = person;
    } else if (first) {
      p = firstPerson;
    } else {
      p = secondPerson;
    }

    WeightedList<Region> regions = new WeightedList<>();

    if (end - start < MAX) {
      addCandidates(start, p.getSnippet(start, (int) end), regions, condition);

    } else {
      boolean ended = false;
      long newEnd = end;

      while (end - start > MAX || !ended) {
        if (start + MAX < newEnd) {
          end = start + MAX;
        }
        String snippet = p.getSnippet(start, (int) end);

        if (addCandidates(start, p.getSnippet(start, (int) end), regions, condition)) {
          // middle of a
          // sequence
          // TODO method accounting for string splits
        }

        if (end == newEnd) {
          ended = true;
        } else {
          start = end + 1;
          end = newEnd;
        }
      }
    }

    return regions;
  }

  /**
   * Helper method for longestSharedSequence Returns an array of sequences as
   * Strings.
   * 
   * @param snippetA is the first person's genome
   * @param snippetB is the second person's genome
   * @return an array of strings which contains the largest shared sequences
   *         between two people
   */
  private String[] retSeqs(String snippetA, String snippetB) {
    int max = 0;
    int counter = 0;
    int index = 0;
    int[] indices = new int[snippetA.length() / 2];

    for (int i = 0; i < snippetA.length(); i++) {
      if (snippetA.charAt(i) == snippetB.charAt(i)) {

        counter++;

        if (index == 0) {
          index = i;
        }
      }

      if (snippetA.charAt(i) != snippetB.charAt(i) && counter > 0 || i == snippetA.length() - 1) {
        if (counter > max) {

          indices = new int[snippetA.length() / 2];
          indices[0] = index;
          max = counter;
          counter = 0;
          index = 0;

        } else if (counter == max) {
          Boolean added = false;
          int j = 0;

          while (!added) {
            if (indices[j] == 0) {
              indices[j] = index;
              added = true;
            }
            j++;
          }

          counter = 0;
          index = 0;

        } else {

          counter = 0;
          index = 0;
        }
      }
    }

    indices = shrinkIndices(indices);
    String[] sharedSeqs = new String[indices.length];

    for (int i = 0; i < indices.length; i++) {

      sharedSeqs[i] = snippetA.substring(indices[i], indices[i] + max);
    }
    return sharedSeqs;
  }

  /**
   * Helper method for findCandidates. Adds codon sequences to the WeightedList.
   * 
   * @param start     is the start index.
   * @param snippet   is the snippet to find codons in.
   * @param regions   is the list to add to.
   * @param condition is the condition on which to add the snippet to the list.
   * @return
   */
  private boolean addCandidates(long start, String snippet, WeightedList<Region> regions,
      Predicate<Region> condition) {
    boolean inSeq = false;
    int index = 0;
    int length = 0;
    Comparator<Region> comp = (Region r1, Region r2) -> r2.getLength() - r1.getLength();

    for (int i = 1; i < snippet.length() - 1; i++) {
      if (snippet.charAt(i) == 'U' && snippet.charAt(i - 1) == 'A'
          && snippet.charAt(i + 1) == 'G') {
        index = i + 2;
        inSeq = true;
      }

      if (snippet.charAt(i) == 'U' && snippet.charAt(i + 1) == 'A'
          && snippet.charAt(i + 2) == 'G') {
        length = (i) - index;

        Region region = new Region(snippet.substring(index, index + length), start + index, length);

        if (condition.test(region)) {
          regions.add(region, comp);
        }
        inSeq = false;
      }
    }

    return inSeq;
  }

  /**
   * Shrink the indices array to only take up the space it needs.
   * 
   * @param indices is the array to shrink.
   * @return the shrunk array.
   */
  private int[] shrinkIndices(int[] indices) {
    int i = 0;

    while (indices[i] > 0) {
      i++;
    }
    int[] retIndices = new int[i];

    for (int j = 0; j < retIndices.length; j++) {
      retIndices[j] = indices[j];
    }
    return retIndices;
  }
}
