import java.io.Serializable;
import java.util.HashMap;

/**
 * Simple container class that stores a bit sequence representing a Huffman
 * encoded file along with frequency information that may be used to reconstruct
 * the Huffman tree used for the encoding. 
 * 
 * <p>The frequency HashMap maps from bytes to the number of
 * occurrences of those bytes in the source document.
 * 
 * @author Nathan Sprague
 * @version 11/21/16
 *
 */
public class HuffmanSave implements Serializable {

  private static final long serialVersionUID = 1L;

  private BitSequence encoding;
  private HashMap<Byte, Integer> frequencies;

  /**
   * Create a HuffmanSave object from a bit sequence and a frequency HashMap.
   *
   * @param encoding The BitSequence of the encoded file.
   * @param frequencies The map of frequencies for all bytes.
   */
  public HuffmanSave(BitSequence encoding, HashMap<Byte, Integer> frequencies) {
    this.encoding = encoding;
    this.frequencies = frequencies;
  }

  /**
   * Return the bit sequence.
   *
   * @return The encoded BitSequence.
   */
  public BitSequence encoding() {
    return encoding;
  }

  /**
   * Return the frequency map.
   *
   * @return The map of frequencies for each byte.
   */
  public HashMap<Byte, Integer> getFrequencies() {
    return frequencies;
  }

}
