/**
 * Representation of a gene coding region. At the least, this class should
 * contain the sequence of nucleotides between the start and stop codons, and
 * the index where the sequence starts. You may add other functionality to this
 * class if it will be beneficial for your design.
 *
 * @author Patrick Muradaz
 * @version V1, 9/2019
 */
public class Region {

  private String encoding;
  private long location;
  private int length;

  /**
   * Creates a new Region to represent a gene coding region.
   *
   * @param encoding The string contents of the region.
   * @param location The index where the region starts.
   */
  public Region(String encoding, long location, int length) {
    this.encoding = new String(encoding);
    this.location = location;
    this.length = length;
  }

  /**
   * Creates a new Region from an existing region.
   *
   * @param inRegion The region to copy from.
   */
  public Region(Region inRegion) {
    this.encoding = new String(inRegion.getEncoding());
    this.location = inRegion.getLocation();
    this.length = inRegion.getLength();
  }

  /**
   * Gets the string of the gene coding region.
   *
   * @return The string of nucleotide codes.
   */
  public String getEncoding() {
    return encoding;
  }

  /**
   * Concats a string to the end of this encoding.
   */
  public void addToEncoding(String inCode) {
    encoding = new String(encoding + inCode);
  }

  /**
   * Gets the location of the region in the genome.
   *
   * @return A zero-based index where the region starts.
   */
  public long getLocation() {
    return location;
  }

  /**
   * Gets the length of the region in the genome.
   * 
   * @return The length of the string of codes.
   */
  public int getLength() {
    return length;
  }

  /**
   * Sets the length of the region in the genome.
   */
  public void setLength() {
    length = encoding.length();
  }

  /**
   * Override of standard toString method.
   * 
   * @return The encoding as a string.
   */
  @Override
  public String toString() {
    return encoding.toString();
  }
}
