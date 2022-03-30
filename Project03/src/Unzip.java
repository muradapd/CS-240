import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Comparator;

/**
 * Simple class to uncompress a file in a manner similar to the unzip program.
 *
 * <p>After reading in the compressed file, rebuild the Huffman tree based on the
 * frequency information. Using this information, decode the compressed data,
 * writing the result to the output file.
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
public class Unzip {

  private static Heap<HuffTreeNode> maxHeap;
  private static HuffTree tree;
  private static FileInputStream inFile;
  private static ObjectInputStream inObj;
  private static FileOutputStream outFile;
  private static HuffmanSave zipFile;

  /**
   * Main method. Runner for un-ziping files. Takes in two args: the path of the
   * file to un-zip and the location to save the recreated file.
   * 
   * @param args are the command line arguments taken by this method.
   */
  public static void main(String[] args) {

    try {
      inFile = new FileInputStream(args[0]);
      inObj = new ObjectInputStream(inFile);
      outFile = new FileOutputStream(args[1]);

      buildHeap(); // build the heap that will be used to rebuild the huffman coding tree
      saveFile(); // use the bitSeq from the .jmz and HuffTree to rebuild and save the orig file

    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Error: Incorrect number of arguments.");
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found.");
    } catch (IOException e) {
      System.out.println("Error: File could not be opened.");
    } catch (ClassNotFoundException e) {
      System.out.println("Error: File could not be opened.");
    }
  }

  /**
   * Builds the original heap from the HuffmanSave object.
   * 
   * @throws ClassNotFoundException if the file is not a HuffSave object.
   * @throws IOException if the file cannot be opened.
   */
  private static void buildHeap() throws ClassNotFoundException, IOException {
    zipFile = (HuffmanSave) inObj.readObject();
    HuffmanSave debug = zipFile;

    int nodesSize = zipFile.getFrequencies().size();
    HuffTreeNode[] orderedNodes = new HuffTreeNode[256];
    int count = 0;

    // create an array of HuffTreeNodes in the correct order but un-trimmed
    for (Byte key : zipFile.getFrequencies().keySet()) {
      int freq = zipFile.getFrequencies().get(key);
      HuffTreeNode node = new HuffTreeNode(null, key, freq);
      orderedNodes[Byte.toUnsignedInt(key)] = node;
      count++;
    }
    
    HuffTreeNode[] trimmedNodes = new HuffTreeNode[count];
    int i = 0;
    
    // trim the orderedNodes array for use by heap
    for (HuffTreeNode node : orderedNodes) {
      if (node != null) {
        trimmedNodes[i] = node;
        i++;
      }
    }
    
    maxHeap = new Heap<HuffTreeNode>(trimmedNodes, trimmedNodes.length, buildComp().reversed());
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
   * Uses the recreated tree to reconstruct the original file and save it.
   * 
   * @throws IOException if the file cannot be opened.
   */
  private static void saveFile() throws IOException {
    tree = new HuffTree(maxHeap);
    HuffTree debug = tree;
    HuffmanSave dbug = zipFile;
    String lookup = zipFile.encoding().toString();
    
    while (lookup.length() > 0) {
      Pair<Byte, String> ret = tree.lookup(lookup);
      outFile.write(ret.getFirst());
      lookup = ret.getSecond();
    }

    outFile.close();
    inObj.close();
    inFile.close();
  }
}
