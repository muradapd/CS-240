import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Runner class for the JMU Movie Database (JMDb).
 * 
 * @author Patrick Muradaz
 * @param <E> the generic type for this class.
 * 
 *            JMU Honor Code Statement: I have neither given nor received
 *            unauthorized or undocumented help on this assignment.
 */
public class JMDb {

  private static BinTree<Movie> idTree;
  private static BinTree<Movie> titleTree;
  private static BinTree<Movie> ratingTree;
  private static BinTree<Movie> runTimeTree;
  private static BinTree<Movie> yearTree;
  private static BinTree<Movie> tree;

  /**
   * The main entry point to the program. The first argument is the name of the
   * CSV file to parse. The second argument is which sorting algorithm to use.
   * 
   * @param args The command-line arguments.
   * @throws IOException if there is a null line in the file.
   */
  public static void main(String[] args) {
    try { // try catch for runner class

      run(args);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method used to run the entire program.
   * 
   * @param args is the command line arguments passed from main.
   * @throws IOException if there is a null line in the file.
   */
  public static List<Movie> run(String[] args) throws IOException {
    /** ========================== Set everything up ========================== */
    if (args.length == 0) { // if no args are passed
      System.out.println("Error: No args given");
      return null;
    }
    File inFile = new File(args[0]); // create the csv file to get movies from

    if (!inFile.canRead()) { // check that we have a good file location
      return null;
    }

    /** =================== Create variables and comparators ===================. */
    List<Movie> movieList = CSVParse.buildMovieList(args[0]); // create list
    Sorter<Movie> sorter;
    SortFactory<Movie> sortFac = new SortFactory<Movie>();
    final Comparator<Movie> id = (mov1, mov2) -> mov1.getIdentifier()
        .compareTo(mov2.getIdentifier());
    final Comparator<Movie> title = (mov1, mov2) -> mov1.getTitle().compareTo(mov2.getTitle());
    final Comparator<Movie> rating = (mov1, mov2) -> { // longer than others because of double type
      if (mov1.getImdbRating() < mov2.getImdbRating()) {
        return -1;
      } else if (mov1.getImdbRating() > mov2.getImdbRating()) {
        return 1;
      } else {
        return 0;
      }
    };
    final Comparator<Movie> runTime = (mov1, mov2) -> mov1.getRuntime() - mov2.getRuntime();
    final Comparator<Movie> year = (mov1, mov2) -> mov1.getYear() - mov2.getYear();

    /** =========== Sort and build trees with specified sort method =========== */
    sorter = sortFac.buildSorter(args[1]);
    BinTreeBuilder<Movie> builtTree = new BinTreeBuilder<Movie>();
    tree = builtTree.build(movieList);
    sorter.sort(movieList, id);
    idTree = builtTree.build(movieList);
    sorter.sort(movieList, title);
    titleTree = builtTree.build(movieList);
    sorter.sort(movieList, rating);
    ratingTree = builtTree.build(movieList);
    sorter.sort(movieList, runTime);
    runTimeTree = builtTree.build(movieList);
    sorter.sort(movieList, year);
    yearTree = builtTree.build(movieList);

    /** ========================== Search binary trees ========================== */
    return search(args[2]);
  }

  /**
   * Helper method used to decide which binary tree search to enter.
   * 
   * @param arg is the argument to build from.
   */
  private static List<Movie> search(String arg) {
    List<String> criteria = buildCriteria(arg);
    List<String> genres = new ArrayList<String>();
    List<Movie> printList = new ArrayList<Movie>();
    List<Movie> addList = new ArrayList<Movie>();
    List<Movie> removeList = new ArrayList<Movie>();
    String compare;
    int i1;
    int i2;
    boolean and = false;

    for (int i = 0; i < criteria.size(); i++) {
      String criterion = criteria.get(i);

      switch (criterion) {
        case "identifier":
          i++; // account for unique identifier
          compare = criteria.get(i);
          addList = idTree.search(idFunction(compare));
          printList = buildList(printList, addList, removeList, and);
          break;

        case "title":
          i++; // account for title
          compare = criteria.get(i).substring(1, criteria.get(i).length() - 1);
          addList = titleTree.search(titleFunction(compare));
          printList = buildList(printList, addList, removeList, and);
          break;

        case "rating":
          i += 2; // account for min and max values
          double d1 = Double.parseDouble(criteria.get(i - 1));
          double d2 = Double.parseDouble(criteria.get(i));
          addList = ratingTree.search(ratingFunction(d1, d2));
          printList = buildList(printList, addList, removeList, and);
          break;

        case "runtime":
          i += 2; // account for min and max values
          i1 = Integer.parseInt(criteria.get(i - 1));
          i2 = Integer.parseInt(criteria.get(i));
          addList = runTimeTree.search(runtimeFunction(i1, i2));
          printList = buildList(printList, addList, removeList, and);
          break;

        case "year":
          i += 2; // account for min and max values
          i1 = Integer.parseInt(criteria.get(i - 1));
          i2 = Integer.parseInt(criteria.get(i));
          addList = yearTree.search(yearFunction(i1, i2));
          printList = buildList(printList, addList, removeList, and);
          break;

        case "genre":
          while (i < criteria.size() - 1 && !criteria.get(i + 1).equals("AND")) {
            i++;
            genres.add(criteria.get(i));
          }
          addList = tree.search(genreFunction(genres), "genre");
          printList = buildList(printList, addList, removeList, and);
          break;

        case "AND":
          and = true;
          break;

        default:
          System.out.println("Bad input");
          break;
      }
    }

    printList(printList);
    return printList;
  }

  /**
   * Helper method, builds function lambda for ID search.
   * 
   * @param compare is the ID being searched for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> idFunction(String compare) {
    Function<Movie, Integer> function = (Movie m) -> {
      if (m.getIdentifier().compareTo(compare) < 0) {
        return -1;
      } else if (m.getIdentifier().compareTo(compare) > 0) {
        return 1;
      } else {
        return 0;
      }
    };
    return function;
  }

  /**
   * Helper method, builds function lambda for title search.
   * 
   * @param compare is the title being searched for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> titleFunction(String compare) {
    Function<Movie, Integer> function = (Movie m) -> {
      if (m.getTitle().compareTo(compare) < 0) {
        return -1;
      } else if (m.getTitle().compareTo(compare) > 0) {
        return 1;
      } else {
        return 0;
      }
    };
    return function;
  }

  /**
   * Helper method, builds function lambda for imdbRating search.
   * 
   * @param i1 is the lower rating bound to search for.
   * @param i2 is the upper rating bound to search for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> ratingFunction(Double i1, Double i2) {
    Function<Movie, Integer> function = (Movie m) -> {
      if (m.getImdbRating() < i1) {
        return -1;
      } else if (m.getImdbRating() > i2) {
        return 1;
      } else {
        return 0;
      }
    };
    return function;
  }

  /**
   * Helper method, builds function lambda for runtime search.
   * 
   * @param i1 is the lower runtime bound to search for.
   * @param i2 is the upper runtime bound to search for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> runtimeFunction(int i1, int i2) {
    Function<Movie, Integer> function = (Movie m) -> {
      if (m.getRuntime() < i1) {
        return -1;
      } else if (m.getRuntime() > i2) {
        return 1;
      } else {
        return 0;
      }
    };
    return function;
  }

  /**
   * Helper method, builds function lambda for year search.
   * 
   * @param i1 is the lower year bound to search for.
   * @param i2 is the upper year bound to search for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> yearFunction(int i1, int i2) {
    Function<Movie, Integer> function = (Movie m) -> {
      if (m.getYear() < i1) {
        return -1;
      } else if (m.getYear() > i2) {
        return 1;
      } else {
        return 0;
      }
    };
    return function;
  }

  /**
   * Helper method, builds function lambda for genre search.
   * 
   * @param genres is the list of genres to search for.
   * @return the function lambda.
   */
  private static Function<Movie, Integer> genreFunction(List<String> genres) {
    Function<Movie, Integer> function = (Movie m) -> {
      int contains = -1;
      for (String genre : genres) {
        if (m.getGenres().contains(genre)) {
          contains = 0;
        }
      }
      return contains;
    };
    return function;
  }

  /**
   * Helper method that builds the output list from the search method. This method
   * adds items to the output list if there is only one search parameter and
   * merges search lists if there are multiple search parameters.
   * 
   * @param printList  is the final list to output at the end of the search.
   * @param addList    is the list of items to add to printList.
   * @param removeList is the list of items to remove from printList (items are
   *                   removed if they are in printList but not in addList).
   * @param and        is a boolean that is true if there is more than one search
   *                   criteria.
   * @return the list to output.
   */
  private static List<Movie> buildList(List<Movie> printList, List<Movie> addList,
      List<Movie> removeList, boolean and) {
    if (!and) {
      printList.addAll(addList);
    } else { // and is true
      for (Movie movie : printList) {
        if (!addList.contains(movie)) {
          removeList.add(movie);
        }
      }
      printList.removeAll(removeList);
    }
    return printList;
  }

  /**
   * Helper method to build search criteria.
   * 
   * @param arg is the argument to build criteria from.
   * @return a list of search criteria.
   */
  private static List<String> buildCriteria(String arg) {
    List<String> criteria = new ArrayList<String>();
    int start = 0;
    boolean inQuotes = false;

    for (int i = 0; i < arg.length(); i++) {
      if (arg.charAt(i) == '\"') {
        inQuotes = !inQuotes; // toggle state
      }
      if (i == arg.length() - 1) { // at the end of row
        criteria.add(arg.substring(start));
      } else if (arg.charAt(i) == ' ' && !inQuotes) {
        criteria.add(arg.substring(start, i));
        start = i + 1;
      }
    }

    return criteria;
  }

  /**
   * Helper method to print out the contents of a list on new lines.
   * 
   * @param list is the list to print out.
   */
  private static void printList(List<Movie> list) {
    for (Movie movie : list) { // for every movie in the list print it and the genres
      System.out.println(movie.toString());
    }
  }
}
