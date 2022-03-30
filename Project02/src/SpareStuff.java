
public @interface SpareStuff {

  /**
   * TODO:
   * 
   * Fix quick sort?
   * 
   * Ask: Does
   */

  /** ================= Build other variables as needed ================= */
//List<Movie> starterList;
//List<Movie> sortedStarterList;
//List<Movie> mediumList;
//List<Movie> sortedMediumList;
//List<Movie> imdbList;
//List<Movie> sortedImdbList;
//private Sorter<Movie> sorter;
//private SortFactory<Movie> sortFac = new SortFactory<Movie>();
//private Comparator<Movie> comp = (mov1, mov2) -> mov1.getRuntime() - mov2.getRuntime();
//
//private void buildLists() throws IOException {
//  starterList = CSVParse.buildMovieList(starterFile); // create list
//  sortedStarterList = CSVParse.buildMovieList(starterFile); // create sorted list
//  mediumList = CSVParse.buildMovieList(mediumFile); // create list
//  sortedMediumList = CSVParse.buildMovieList(mediumFile); // create sorted list
//  imdbList = CSVParse.buildMovieList(imdbFile); // create list
//  sortedImdbList = CSVParse.buildMovieList(imdbFile); // create sorted list
//
//  sorter = sortFac.buildSorter("bubble");
//  sorter.sort(sortedStarterList, comp);
//  sorter.sort(sortedMediumList, comp);
//  sorter.sort(sortedImdbList, comp);
//}

  /** ==================== Print original unsorted list ==================== */
//System.out.println("Unsorted Output:");
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//movieList = CSVParse.buildMovieList(inFile); // scramble list
//
  /** ======================= Print MergeSorted list ======================= */
//System.out.println("\n\nMergeSort Output:");
//sorter = sortFac.buildSorter("merge");
//sorter.sort(movieList, comp);
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//movieList = CSVParse.buildMovieList(inFile); // scramble list

  /** ======================= Print QuickSorted list ======================= */
//System.out.println("\n\nQuickSort Output:");
//sorter = sortFac.buildSorter("quick");
//sorter.sort(movieList, comp);
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//System.out.println("Working!");
//movieList = CSVParse.buildMovieList(inFile); // scramble list

  /** ===================== Print InsertionSorted list ===================== */
//System.out.println("\n\nInsertionSort Output:");
//sorter = sortFac.buildSorter("insertion");
//sorter.sort(movieList, comp);
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//System.out.println("Working!");
//movieList = CSVParse.buildMovieList(inFile); // scramble list

  /** ===================== Print SelectionSorted list ===================== */
//System.out.println("\n\nSelectionSort Output:");
//sorter = sortFac.buildSorter("selection");
//sorter.sort(movieList, comp);
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//System.out.println("Working!");
//movieList = CSVParse.buildMovieList(inFile); // scramble list

  /** ======================= Print BubbleSorted list ======================= */
//System.out.println("\n\nBubbleSort Output:");
//sorter = sortFac.buildSorter("bubble");
//sorter.sort(movieList, comp);
//for (Movie mov : movieList) {
//  System.out.println(mov.toString());
//}
//System.out.println("Working!");
//movieList = CSVParse.buildMovieList(inFile); // scramble list

  /** ======================== Test sort times =========================== */
  // Sorter<Movie> sorter;
//SortFactory<Movie> sortFac = new SortFactory<Movie>();
//String inFile = "src\\3-imdb.csv";
//final Comparator<Movie> comp = (mov1, mov2) -> mov1.getYear() - mov2.getYear();
//List<Movie> movieList = CSVParse.buildMovieList(inFile); // scramble list
//List<Movie> movieList2 = new ArrayList<Movie>();
//for (Movie movie : movieList) {
//  movieList2.add(movie);
//}
//
//sorter = sortFac.buildSorter("quick");
//long start = System.currentTimeMillis();
//sorter.sort(movieList, comp);
//long end = System.currentTimeMillis();
//System.out.println("Quicksort time: " + (end - start));
//
//sorter = sortFac.buildSorter("insertion");
//start = System.currentTimeMillis();
//sorter.sort(movieList2, comp);
//end = System.currentTimeMillis();
//System.out.println("Insertion sort time: " + (end - start));

}
