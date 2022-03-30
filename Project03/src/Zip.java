import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Simple class to compress a file in a manner similar to the zip program.
 *
 * <p>After reading in the file, build a Huffman tree based on the byte frequencies
 * of the contents. Use this Huffman tree to create a bit sequence encoding of
 * the contents, writing the result to the output file.
 *
 * @author Patrick Muradaz
 * @version 11/22/2019
 * 
 *          Honor Code Statement: I neither gave nor received unacknowledged or
 *          unauthorized help on this assignment. This work complies with the
 *          JMU honor code.
 * 
 *          Acknowledgments: I offered help on finding bitSequences to Abel and
 *          Carter.
 */
public class Zip {

  private static byte[] bytes;
  private static Heap<HuffTreeNode> maxHeap;
  private static HuffTree tree;
  private static HashMap<Byte, Integer> freqMap;
  private static BitSequence bitSeq;
  private static File inFile;
  private static FileOutputStream outFile;
  private static ObjectOutputStream outObj;

  /**
   * Main method. Runner for zipping files. Takes in two args: the path of the
   * file to zip and the location to save the zipped file.
   * 
   * @param args are the command line arguments taken by this method.
   */
  public static void main(String[] args) {

    try {
      inFile = new File(args[0]);
      outFile = new FileOutputStream(args[1]);
      outObj = new ObjectOutputStream(outFile);

      try {
        buildBytes(); // read the input file and build an array of bytes
        buildHeap(); // build a heap using bytes array
        buildFreqMap(); // build the frequency map
        buildBitSequence(); // build the bitSequence
        saveFile(); // create HuffmanSave object and write it out

      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Error: Incorrect number of arguments.");
      } catch (FileNotFoundException e) {
        System.out.println("Error: File not found.");
      } catch (IOException e) {
        System.out.println("Error: File could not be opened.");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Error: Incorrect number of arguments.");
    } catch (FileNotFoundException e1) {
      System.out.println("Error: File could not be saved.");
    } catch (IOException e) {
      System.out.println("Error: Object could not be saved.");
    }
  }

  /**
   * Reads bytes from the given file and builds an array of bytes.
   * 
   * @throws IOException if the file cannot be read.
   */
  private static void buildBytes() throws IOException {
    FileInputStream in = new FileInputStream(inFile);
    bytes = in.readAllBytes();
    in.close();
  }

  /**
   * Builds a priority queue of HuffTreeNodes using the bytes array. The
   * HuffTreeNodes don't yet have parents but store the current byte and that
   * byte's weight (frequency).
   */
  private static void buildHeap() {
    int[] freqs = new int[256];
    int count = 0;

    // create frequency array for bytes
    for (Byte signed : bytes) {
      int unsigned = Byte.toUnsignedInt(signed); // convert signed java byte to unsigned byte
      if (freqs[unsigned] == 0) {
        count++;
      }
      freqs[unsigned]++;
    }

    HuffTreeNode[] nodes = new HuffTreeNode[count];
    int j = 0;

    // create array of HuffTreeNodes using frequency array of bytes
    for (int i = 0; i < freqs.length; i++) {
      if (freqs[i] != 0) {
        Byte signed = (byte) i; // convert unsigned byte to java signed byte
        HuffTreeNode node = new HuffTreeNode(null, signed, freqs[i]);
        nodes[j] = node;
        j++;
      }
    }
    maxHeap = new Heap<HuffTreeNode>(nodes, nodes.length, buildComp().reversed());
  }

  /**
   * Creates a comparator to be used by the heap.
   * 
   * @return the comparator created in the method.
   */
  private static Comparator<HuffTreeNode> buildComp() {
    Comparator<HuffTreeNode> comp = (o1, o2) -> {
      HuffTreeNode firstNode = o1;
      HuffTreeNode secondNode = o2;

      return firstNode.weight() - secondNode.weight();
    };
    return comp;
  }

  /**
   * Builds the map of frequencies per bytes for use by HuffmanSave.
   */
  private static void buildFreqMap() {
    tree = new HuffTree(maxHeap);
    Iterator<HuffTreeNode> iter = tree.iterator();
    freqMap = new HashMap<>();

    while (iter.hasNext()) {
      HuffTreeNode node = iter.next();
      if (node.isLeaf()) {
        freqMap.put(node.element(), node.weight());
      }
    }
  }

  /**
   * Builds the bitSequence for use by HuffmanSave.
   * 
   * @throws IOException throw the IOException
   */
  private static void buildBitSequence() throws IOException {
    HuffTree debug = tree;
    bitSeq = new BitSequence();

    for (Byte signed : bytes) {
      bitSeq.appendBits(tree.bitSeqFinder(signed)); // append signed bytes to bit sequence
    }
  }

  /**
   * Creates a HuffmanSave object and writes it out as a serialization to the user
   * specified output location.
   * 
   * @throws IOException throw the IOException
   */
  private static void saveFile() throws IOException {
    HuffmanSave zipFile = new HuffmanSave(bitSeq, freqMap);

    // write output file
    outObj.writeObject(zipFile);
    outObj.close();
    outFile.close();
  }
}
