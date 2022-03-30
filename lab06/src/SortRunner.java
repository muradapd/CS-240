public class SortRunner {

  public static void main(String args[]) {

    // Framework for running bubble sort on strings
    System.out.print("String input values:");
    String stringInput[] = { "how", "about", "dey", "da", "bears" };
    for (int i = 0; i < stringInput.length; i++) {
      System.out.print(" " + stringInput[i]);
    }
    System.out.println("");

    // Comparison is to compare the string lengths
    Sorter<String> stringSorter = new BubbleSorter<>();
    String stringResults[][] = new String[4][5];
    int stringCounts[] = stringSorter.sort(stringInput,
        (s1, s2) -> s1.length() - s2.length(), stringResults);

    // Print the rows of the results array to see the progress
    for (int i = 0; i < stringResults.length; i++) {
      System.out.print("Row " + i + ":   ");
      for (int j = 0; j < stringResults[i].length; j++) {
        System.out.print(" " + stringResults[i][j]);
      }
      System.out.println("");
    }

    // Show the statistics and the sorted (if correct) array
    System.out.println("Comps:    " + stringCounts[0]);
    System.out.println("Swaps:    " + stringCounts[1]);
    System.out.print("Sorted:  ");
    for (int i = 0; i < stringInput.length; i++) {
      System.out.print(" " + stringInput[i]);
    }
    System.out.println("\n");


    // ******************************************************
    
    // Now do the same thing with int values
    System.out.print("Integer input values:");
    Integer intInput[] = { 9, 5, 6, 7, 2, 8 };
    for (int i = 0; i < intInput.length; i++) {
      System.out.print(" " + intInput[i]);
    }
    System.out.println("");

    // Comparison is standard integer comparison
    Sorter<Integer> intSorter = new InsertionSorter<>();
    Integer intResults[][] = new Integer[5][6];
    int intCounts[] = intSorter.sort(intInput,
        (i1, i2) -> i1 - i2, intResults);

    // Print the rows of the results array to see the progress
    for (int i = 0; i < intResults.length; i++) {
      System.out.print("Row " + i + ":   ");
      for (int j = 0; j < intResults[i].length; j++) {
        System.out.print(" " + intResults[i][j]);
      }
      System.out.println("");
    }

    // Show the statistics and the sorted (if correct) array
    System.out.println("Comps:    " + intCounts[0]);
    System.out.println("Swaps:    " + intCounts[1]);
    System.out.print("Sorted:  ");
    for (int i = 0; i < intInput.length; i++) {
      System.out.print(" " + intInput[i]);
    }
    System.out.println("");
  }

}
