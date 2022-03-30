import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * JUNit tests for the CS240 Huffman Coding Project.
 * 
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version 11/2018
 *
 */
public class ZipTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private static PrintStream originalOut;
  private static PrintStream originalErr;

  // Keep a list of all the files that are created so that they can be deleted.
  private static String[] createdFiles =
      {"empty.dat", "empty.jmz",
          "one_byte.dat", "one_byte.jmz",
          "mary.txt", "mary.jmz",
          "fibonacci.dat", "fibonacci.jmz",
          "bytes.dat", "bytes.jmz", "bytes_restored.dat",
          "flubber.jmz"};


  /**
   * Create several files that can be used to test encoding and decoding.
   * 
   * Reset System.out and System.err so they can be checked in tests.
   * 
   * @throws IOException
   */
  @BeforeAll
  public static void setUp() throws IOException {
    originalOut = System.out;
    originalErr = System.err;
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

    // Create an empty file.
    PrintWriter pw = new PrintWriter(new File("empty.dat"));
    pw.close();

    // Create a file containing a single byte.
    FileOutputStream fo = new FileOutputStream(new File("one_byte.dat"));
    fo.write(42);
    fo.close();

    // Create a short text file.
    pw = new PrintWriter(new File("mary.txt"));
    pw.println("Mary had a little lamb.  It's fleece was white as snow.");
    pw.close();

    // Create a short binary file containing truncated elements from the
    // Fibonacci sequence.
    fo = new FileOutputStream(new File("fibonacci.dat"));

    int prev1 = 1;
    int prev2 = 1;
    for (int i = 0; i < 1000; i++) {
      int next = prev1 + prev2;
      fo.write(next);
      prev1 = prev2;
      prev2 = next;
    }
    fo.close();

    // Create a file containing known counts of many different bytes.
    fo = new FileOutputStream(new File("bytes.dat"));

    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < i + 10; j++) {
        fo.write(i);
      }
    }
    fo.close();
  }

  /**
   * Restore System.out and System.err, also delete all files that were created
   * in setUp.
   */
  @AfterAll
  public static void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);

    for (String fileName : createdFiles) {
      if (!fileName.equals("one_byte.dat") && !fileName.equals("bytes_restored.dat")) {
        File file = new File(fileName);
        file.delete();
      }
    }
  }

  // -----------------------------------------------
  // TESTS FOR Zip COMMAND LINE ARGUMENT ERROR HANDLING
  //
  // NOTE: These tests only confirm that *some* error output is produced. It is
  // your responsibility to make sure that the output is appropriate.
  // -----------------------------------------------


  /**
   * Helper method to confirm that SOME output is generated. 
   */
  private void assertProducesErrorMessage() {
    String resultOut = outContent.toString();
    String resultErr = errContent.toString();
    assertTrue(resultOut.length() != 0 || resultErr.length() != 0);
  }

  @Test
  public void testZipHandlesZeroArguments() {
    Zip.main(new String[] {});
    assertProducesErrorMessage();
  }

  @Test
  public void testZipHandlesOneMissingArgument() {
    Zip.main(new String[] {"file.txt"});
    assertProducesErrorMessage();
  }

  @Test
  public void testZipHandlesUnreadableFile() {
    Zip.main(new String[] {"BLAfjdlSFl.txt", "bytes.jmz"});
    assertProducesErrorMessage();
  }

  // -----------------------------------------------
  // TESTS FOR Unzip COMMAND LINE ARGUMENT ERROR HANDLING
  // -----------------------------------------------
  @Test
  public void testUnzipHandlesZeroArguments() {
    Unzip.main(new String[] {});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesOneMissingArgument() {
    Unzip.main(new String[] {"file.txt"});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesUnreadableFile() {
    Unzip.main(new String[] {"BLAfjdlSFl.txt", "out.jmz"});
    assertProducesErrorMessage();
  }

  @Test
  public void testUnzipHandlesWrongFileFormat() {
    Unzip.main(new String[] {"mary.txt", "blah.txt"});
    assertProducesErrorMessage();
  }

  // -----------------------------------------------
  // TESTS FOR FILE CREATION
  // -----------------------------------------------

  @Test
  public void testZipCreatesFile() throws IOException {
    Zip.main(new String[] {"mary.txt", "flubber.jmz"});

    File file = new File("flubber.jmz");
    assertTrue(file.exists());
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT FREQUENCIES
  // -----------------------------------------------

  private HuffmanSave loadSaved(String name) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(new File(name));
    ObjectInputStream in;
    in = new ObjectInputStream(fileIn);
    HuffmanSave result = (HuffmanSave) in.readObject();
    in.close();
    fileIn.close();
    return result;
  }

  @Test
  public void testOneByteFileCorrectFrequencies() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"one_byte.dat", "one_byte.jmz"});

    HuffmanSave result = loadSaved("one_byte.jmz");
    assertEquals(1, result.getFrequencies().size());
    assertEquals(1, (int) result.getFrequencies().get((byte) 42));
  }

  @Test
  public void testMultiByteFileCorrectFrequencies() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"bytes.dat", "bytes.jmz"});

    HuffmanSave result = loadSaved("bytes.jmz");
    assertEquals(100, result.getFrequencies().size());
    for (int i = 0; i < 100; i++) {
      assertEquals(i + 10, (int) result.getFrequencies().get((byte) i));
    }
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT ENCODING LENGTH
  // -----------------------------------------------

  @Test
  public void testEmptyFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"empty.dat", "empty.jmz"});

    HuffmanSave result = loadSaved("empty.jmz");
    assertEquals(0, result.encoding().length());
  }

  @Test
  public void testOneByteFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"one_byte.dat", "one_byte.jmz"});

    HuffmanSave result = loadSaved("one_byte.jmz");
    assertEquals(1, result.encoding().length());
  }

  @Test
  public void testTextFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"mary.txt", "mary.jmz"});

    HuffmanSave result = loadSaved("mary.jmz");
    assertEquals(227, result.encoding().length()); // is this wrong?
  }

  @Test
  public void testFibonacciFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"fibonacci.dat", "fibonacci.jmz"});

    HuffmanSave result = loadSaved("fibonacci.jmz");
    assertEquals(7147, result.encoding().length());
  }

  @Test
  public void testByteFileCorrectEncodingLength() throws ClassNotFoundException, IOException {
    Zip.main(new String[] {"bytes.dat", "bytes.jmz"});

    HuffmanSave result = loadSaved("bytes.jmz");
    assertEquals(38557, result.encoding().length());
  }

  // -----------------------------------------------
  // TESTS FOR CORRECT RECONSTRUCTION
  // -----------------------------------------------

  private void checkReconstruction(String name) throws IOException {
    Zip.main(new String[] {name, "bytes.jmz"});
    Unzip.main(new String[] {"bytes.jmz", "bytes_restored.dat"});
    Path path1 = FileSystems.getDefault().getPath(name);
    Path path2 = FileSystems.getDefault().getPath("bytes_restored.dat");
    byte[] bytes1 = Files.readAllBytes(path1);
    byte[] bytes2 = Files.readAllBytes(path2);
    org.junit.Assert.assertArrayEquals(bytes1, bytes2);
  }

  @Test
  public void testEmptyFileCorrectlyRestored() throws IOException {
    checkReconstruction("empty.dat");
  }

  @Test
  public void testOneByteFileCorrectlyRestored() throws IOException {
    checkReconstruction("one_byte.dat");
  }

  @Test
  public void testTextFileCorrectlyRestored() throws IOException {
    checkReconstruction("mary.txt");
  }

  @Test
  public void testFibonacciFileCorrectlyRestored() throws IOException {
    checkReconstruction("fibonacci.dat");
  }

  @Test
  public void testByteFileCorrectlyRestored() throws IOException {
    checkReconstruction("bytes.dat");
  }
}
