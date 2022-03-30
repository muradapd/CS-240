import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SequenceAnalyzerTest {

  private static int countMatches(Region r1, Region r2) {
    int length = (r1.getEncoding().length() > r2.getEncoding().length() ? r2.getEncoding().length()
        : r1.getEncoding().length());
    int count = 0;
    for (int i = 0; i < length; i++) {
      if (r1.getEncoding().charAt(i) == r2.getEncoding().charAt(i)) {
        count++;
      }
    }
    return count;
  }

  // ===============================================================
  // Tests for part 2 : 16 points total
  // ===============================================================

  @Test
  public void testAliceAndBobShort() {
    Person alice = new Person("Alice");
    Person bob = new Person("Bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(0, 30);
    assertEquals(1, longest.length, "Alice and Bob should have only one match in first 30");
    assertEquals("TGT", longest[0], "Alice and Bob have a mismatch");
  }

  @Test
  public void testAliceAndLowerCaseBobShort() {
    Person alice = new Person("Alice");
    Person bob = new Person("bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(0, 30);
    assertEquals(1, longest.length, "Alice and bob should have only one match in first 30");
    assertEquals("AG", longest[0], "Alice and bob have a mismatch");
  }

  @Test
  public void testAliceAndBobTrickyCase() {
    Person alice = new Person("Alice");
    Person bob = new Person("Bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(100, 120);
    assertEquals(2, longest.length, "Alice and Bob should have two between 100 and 120");
    String[] correct = { "AUG", "UAG" };
    for (int i = 0; i < longest.length; i++) {
      assertEquals(correct[i], longest[i], "Alice and Bob tricky case mismatch");
    }
  }

  @Test
  public void testAliceAndBob1000() {
    Person alice = new Person("Alice");
    Person bob = new Person("Bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(0, 1000);
    assertEquals(2, longest.length, "Alice and Bob should have two between 0 and 1000");
    String[] correct = { "GCTGAGUAG", "UAGGATAUG" };
    for (int i = 0; i < longest.length; i++) {
      assertEquals(correct[i], longest[i], "Alice and Bob have a mismatch");
    }
  }

  @Test
  public void testAliceAndBob10000() {
    Person alice = new Person("Alice");
    Person bob = new Person("Bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(0, 10000);
    assertEquals(1, longest.length, "Alice and Bob should have only one match in first 10000");
    assertEquals("ACGGCACUAGTGT", longest[0], "Alice and Bob have a mismatch");
  }

  @Test
  public void testAliceBob120K120M() {
    Person alice = new Person("Alice");
    Person bob = new Person("Bob");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);
    String[] longest = analyzer.longestSharedSequence(120000, 120000000);
    assertEquals(1, longest.length, "Alice and Bob should have only one match 120K-120M range");
    assertEquals("UAGAUGTAATGACTGCGGTATTGTT", longest[0], "Alice and bob have a mismatch");
  }

  // ===============================================================
  // Tests for part 4 : 8 points total
  // ===============================================================

  @Test
  public void testCarolCandidatesNoneFound() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(0, 120, true,
        r -> r.getEncoding().length() >= 40 && r.getEncoding().length() <= 45);
    assertEquals(0, regions.size(), "Carol has none of 40-45 length at beginning");
    assertTrue(regions.isEmpty(), "Carol region list should be empty");
  }

  @Test
  public void testCarolCandidatesOneFound() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(0, 120, true,
        r -> r.getEncoding().length() >= 15);
    assertEquals(1, regions.size(), "Carol has one over length 15 at beginning");
    assertFalse(regions.isEmpty(), "Carol region list should not be empty");
    assertEquals("39:CAGCTGCAGCTGTATTGT",
        regions.retrieve().getLocation() + ":" + regions.retrieve().getEncoding(),
        "Carol first over 15 is mismatch");
  }

  @Test
  public void testCarolCandidatesTwoFound() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(0, 120, true,
        r -> r.getEncoding().length() >= 10);
    assertEquals(2, regions.size(), "Carol has one over length 15 at beginning");
    assertFalse(regions.isEmpty(), "Carol region list should not be empty");
    assertEquals("39:CAGCTGCAGCTGTATTGT",
        regions.retrieve().getLocation() + ":" + regions.retrieve().getEncoding(),
        "Carol first over 15 is mismatch");
  }

  @Test
  public void testCarolCandidatesTwoFoundStartAt30() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(30, 120, true,
        r -> r.getEncoding().length() >= 10);
    assertEquals(2, regions.size(), "Carol has one over length 15 at beginning");
    assertFalse(regions.isEmpty(), "Carol region list should not be empty");
    assertEquals("39:CAGCTGCAGCTGTATTGT",
        regions.retrieve().getLocation() + ":" + regions.retrieve().getEncoding(),
        "Carol first over 15 is mismatch");
  }

  @Test
  public void testCarolDaveFindCandidateFalse() {
    Person carol = new Person("Carol");
    Person dave = new Person("Dave");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol, dave);
    WeightedList<Region> regions = analyzer.findCandidates(0, 240, false,
        r -> r.getEncoding().length() >= 40 && r.getEncoding().length() <= 45);
    assertEquals("195:TGTTCTGCCCAAAGCACCCGTCAGTATTGTCATCTTGTCGCC",
        regions.retrieve().getLocation() + ":" + regions.retrieve().getEncoding(),
        "Should select Dave instead of Carol");
  }

  @Test
  public void testCarolCandidatesFoundAcceptNoOrder() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(0, 1200, true,
        r -> r.getEncoding().length() >= 10);
    ArrayList<String> list = new ArrayList<>();
    list.add("243:GCTGAGAGCACCCACCTCGTGGCGATCAGCATTAGTGTTGCTTATTGTTAATGA");
    list.add("663:TATTGTATTAGTTGTTCTCAACTACAACTAGCAGATAGTACTATAAGATATTGT");
    list.add("1023:CTTCGTGCCCAACGTCAGATAAGAGAAGTATGTTCTCAACTATACTGCCGCATA");
    list.add("966:GAGGTGTCGTACTGCTCCCTCCGCACGATCAGCACCTCGTACTGTTCTAGT");
    list.add("432:ATAAGACACCTCTCATATCGCATAATTAGTATAAGAAGTACTTAC");
    list.add("552:TGATCAGCTGAGACCTAACAACTATCGTACCATCTTTGATCAAGG");
    list.add("195:TACTGCAGAACACGTCAGTGTTCTCGTCAGTGGTCGCTACGA");
    list.add("915:CATCTTGTGGCGCGTCAGCTCCGCTGCTCCATCAGCGTAGCA");
    list.add("621:AGGACGAGTACTGTTGCTCATCTTCGCATAGAGGTG");
    list.add("504:TATTGTGCTGAGCTTCGTTACTGCTCATATCTA");
    list.add("327:CGGCACAGCACCCGGCACGTAGCAGTCGCC");
    list.add("750:CGCATATGGTCGACAATTTGTTCTCGA");
    list.add("1110:CAACTAATTAGTCGCATAGTAGCAAGG");
    list.add("1170:TGGTCGTACTGCGCTGAGCGCATACTC");
    list.add("39:CAGCTGCAGCTGTATTGT");
    list.add("819:CGGCACCTTCGTCGTCAG");
    list.add("162:TATTGTGATGTTGTA");
    list.add("402:TGCTCCTAATGACTG");
    list.add("105:CTACGATCGTAC");
    list.add("885:ACGATCGTTGCT");
    assertEquals(20, list.size(), "Carol should have 20 candidates before removal");
    regions.accept((Region r) -> {
      list.remove(r.getLocation() + ":" + r.getEncoding());
    });
    assertEquals(0, list.size(), "Carol candidates should have been all removed");
  }

  @Test
  public void testCarolCandidatesFoundAcceptOrder() {
    Person carol = new Person("Carol");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(carol);
    WeightedList<Region> regions = analyzer.findCandidates(0, 1200, true,
        r -> r.getEncoding().length() >= 10);
    ArrayList<String> list = new ArrayList<>();
    list.add("243:GCTGAGAGCACCCACCTCGTGGCGATCAGCATTAGTGTTGCTTATTGTTAATGA");
    list.add("663:TATTGTATTAGTTGTTCTCAACTACAACTAGCAGATAGTACTATAAGATATTGT");
    list.add("1023:CTTCGTGCCCAACGTCAGATAAGAGAAGTATGTTCTCAACTATACTGCCGCATA");
    list.add("966:GAGGTGTCGTACTGCTCCCTCCGCACGATCAGCACCTCGTACTGTTCTAGT");
    list.add("432:ATAAGACACCTCTCATATCGCATAATTAGTATAAGAAGTACTTAC");
    list.add("552:TGATCAGCTGAGACCTAACAACTATCGTACCATCTTTGATCAAGG");
    list.add("195:TACTGCAGAACACGTCAGTGTTCTCGTCAGTGGTCGCTACGA");
    list.add("915:CATCTTGTGGCGCGTCAGCTCCGCTGCTCCATCAGCGTAGCA");
    list.add("621:AGGACGAGTACTGTTGCTCATCTTCGCATAGAGGTG");
    list.add("504:TATTGTGCTGAGCTTCGTTACTGCTCATATCTA");
    list.add("327:CGGCACAGCACCCGGCACGTAGCAGTCGCC");
    list.add("750:CGCATATGGTCGACAATTTGTTCTCGA");
    list.add("1110:CAACTAATTAGTCGCATAGTAGCAAGG");
    list.add("1170:TGGTCGTACTGCGCTGAGCGCATACTC");
    list.add("39:CAGCTGCAGCTGTATTGT");
    list.add("819:CGGCACCTTCGTCGTCAG");
    list.add("162:TATTGTGATGTTGTA");
    list.add("402:TGCTCCTAATGACTG");
    list.add("105:CTACGATCGTAC");
    list.add("885:ACGATCGTTGCT");
    assertEquals(20, list.size(), "Carol should have 20 candidates before removal");
    regions.accept((Region r) -> {
      assertEquals(list.get(0), (r.getLocation() + ":" + r.getEncoding()),
          "Candidates are not stored in the expected order in the list");
      list.remove(0);
    });
  }

  // ===============================================================
  // Tests for part 5 : 8 points total
  // ===============================================================

  @Test
  public void testDaveEveFirst120MatchOver15() {
    Person dave = new Person("Dave");
    Person eve = new Person("Eve");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(dave, eve);
    WeightedList<Pair<Region, Region>> pairs = analyzer.match(0, 120,
        (reg1, reg2) -> countMatches(reg1, reg2) > 15);
    assertEquals(0, pairs.size(), "Dave and Eve have no matches over 15 at beginning");
    assertTrue(pairs.isEmpty(), "Region list should be empty");
  }

  @Test
  public void testDaveEveFirst120MatchOver5() {
    Person dave = new Person("Dave");
    Person eve = new Person("Eve");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(dave, eve);
    WeightedList<Pair<Region, Region>> pairs = analyzer.match(0, 120,
        (reg1, reg2) -> countMatches(reg1, reg2) > 5);
    assertEquals(1, pairs.size(), "Dave and Eve have one match over 5 at beginning");
    assertFalse(pairs.isEmpty(), "Region list should not be empty");
    assertEquals("39:GAAGTAGCTGAGGTCGCC",
        pairs.retrieve().getFirst().getLocation() + ":" + pairs.retrieve().getFirst().getEncoding(),
        "Dave first over 5 is mismatch");
  }

  @Test
  public void testDaveEveFirst1200MatchOver10NoOrder() {
    Person dave = new Person("Dave");
    Person eve = new Person("Eve");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(dave, eve);
    WeightedList<Pair<Region, Region>> pairs = analyzer.match(0, 1200,
        (reg1, reg2) -> countMatches(reg1, reg2) > 10);
    ArrayList<String> list = new ArrayList<>();
    list.add("243:ACGATCTACTGCCGCATATAATGACTACGACGTCAGAGAACATGCTCCGCTGAG");
    list.add("663:ACAATTGTCGCCTACTGCTGGTCGTGATCAGACGTCCGGCACGAAGTAACGATC");
    list.add("1023:TGCTCCAGGACGAGCACCTCATATCAGCTGTATTGTGTCGCCGTGGCGTCATAT");
    list.add("966:CTACGACTTCGTACAATTATTAGTGCTGAGACCTAATGTTCTGAAGTATCA");
    list.add("552:TCCGAACTACGAGCCCAACGACATCGCATAGACGTCTCCGAACTT");
    list.add("915:GCGGACTCATATAGGACGAGCACCGTAGCAGCTGAGTGTTCT");
    assertEquals(6, pairs.size(), "Dave and Eve have 6 matches over 10 at beginning");
    assertFalse(pairs.isEmpty(), "Region list should not be empty");
    pairs.accept((Pair<Region, Region> p) -> {
      list.remove(p.getFirst().getLocation() + ":" + p.getFirst().getEncoding());
    });
    assertEquals(0, list.size(), "Dave and Eve matches should have been all removed");
  }

  @Test
  public void testDaveEveFirst1200MatchOver10Ordered() {
    Person dave = new Person("Dave");
    Person eve = new Person("Eve");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(dave, eve);
    WeightedList<Pair<Region, Region>> pairs = analyzer.match(0, 1200,
        (reg1, reg2) -> countMatches(reg1, reg2) > 10);
    ArrayList<String> list = new ArrayList<>();
    list.add("243:ACGATCTACTGCCGCATATAATGACTACGACGTCAGAGAACATGCTCCGCTGAG");
    list.add("663:ACAATTGTCGCCTACTGCTGGTCGTGATCAGACGTCCGGCACGAAGTAACGATC");
    list.add("1023:TGCTCCAGGACGAGCACCTCATATCAGCTGTATTGTGTCGCCGTGGCGTCATAT");
    list.add("966:CTACGACTTCGTACAATTATTAGTGCTGAGACCTAATGTTCTGAAGTATCA");
    list.add("552:TCCGAACTACGAGCCCAACGACATCGCATAGACGTCTCCGAACTT");
    list.add("915:GCGGACTCATATAGGACGAGCACCGTAGCAGCTGAGTGTTCT");
    assertEquals(6, pairs.size(), "Dave and Eve have 6 matches over 10 at beginning");
    assertFalse(pairs.isEmpty(), "Region list should not be empty");
    pairs.accept((Pair<Region, Region> p) -> {
      assertEquals(list.get(0), p.getFirst().getLocation() + ":" + p.getFirst().getEncoding(),
          "Matches are not stored in the expected order in the list");
      list.remove(0);
    });
    assertEquals(0, list.size(), "Dave and Eve matches should have been all removed");
  }

  @Test
  public void testDaveEveOver50PercentMatch() {
    Person dave = new Person("Dave");
    Person eve = new Person("Eve");
    SequenceAnalyzer analyzer = new SequenceAnalyzer(dave, eve);
    WeightedList<Pair<Region, Region>> pairs = analyzer.match(0, 120000, (Region reg1,
        Region reg2) -> countMatches(reg1, reg2) / (1.0 * reg1.getEncoding().length()) > 0.5);
    ArrayList<String> list = new ArrayList<>();
    list.add("95409:GAAGTAGCGGACGACGTCCGGCACGTTGCTCAGCTGCTACGAAGCACC");
    list.add("75255:GCTGAGGTTGCTCGACATCGCATACGACATCGGCACATCAGC");
    list.add("61461:GCCCAATGCTCCGCGGACGACGTCCAGCTGCATCTT");
    list.add("58944:CGACATCGCATACGGCACGACGTCATAAGATGC");
    list.add("35013:ACCTAATGATCAGAGGTGCAACTA");
    list.add("16416:ACAATTACCTAAAGGACGATA");
    list.add("46296:CATCTTCTACGAGCAGATTGT");
    list.add("63216:AGAACAATTAGTAGTACTGCA");
    list.add("79236:TCGTACGACGTCGCCCAACTC");
    list.add("81036:ATCAGCTCATATACGATCTGC");
    list.add("117936:GAAGTACGTCAGCGTCAGAGG");
    list.add("19719:CAACTACTCCGCCTCCGC");
    list.add("27819:CATCTTGTAGCATCCGAA");
    list.add("41799:GACGTCTAATGAATAAGA");
    list.add("68979:GTCGCCGTGGCGGATGTT");
    list.add("21882:CATCTTTCTTGGGCA");
    list.add("78522:TCTTGGTGCTCCTGA");
    list.add("8925:ACGATCGAAGTA");
    list.add("10245:CGACATGCCCAA");
    list.add("17145:TCCGAAACAATT");
    list.add("18165:GCAGATTCCGAA");
    list.add("41145:ATCAGCTATTGT");
    list.add("56085:CGACATGTTGCT");
    list.add("76005:TATTGTTATTGT");
    list.add("77145:CACCTCTAATGA");
    list.add("86985:GAAGTATGCTCC");
    list.add("4068:ACAATTCGG");
    list.add("49668:CATCTTTCC");
    list.add("69588:TATTGTACT");
    list.add("72348:GTAGCACGA");
    list.add("74028:TCATATATT");
    list.add("76248:TATTGTGTG");
    list.add("94248:GCAGATCTT");
    list.add("100128:CGTCAGTAT");
    list.add("4611:GTGGCG");
    list.add("12051:TGATCA");
    list.add("36471:GACGTC");
    list.add("36951:GACGTC");
    list.add("49311:ATCAGC");
    list.add("50151:AGCACC");
    list.add("72291:AGCACC");
    list.add("79911:TAATGA");
    list.add("86151:GAAGTA");
    list.add("88131:AGTACT");
    list.add("101511:GTCGCC");
    list.add("103971:TCTTGG");
    list.add("116511:ATCAGC");

    assertEquals(47, pairs.size(), "Dave and Eve have 47 matches over 50 percent");
    assertFalse(pairs.isEmpty(), "Region list should not be empty");
    pairs.accept((Pair<Region, Region> p) -> {
      assertEquals(list.get(0), p.getFirst().getLocation() + ":" + p.getFirst().getEncoding(),
          "Matches are not stored in the expected order in the list");
      list.remove(0);
    });
    assertEquals(0, list.size(), "Dave and Eve matches should have been all removed");
  }

}
