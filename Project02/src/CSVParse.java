import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for creating a list of movies from a comma-separated value (CSV) file.
 * The allowable syntax of CSV files can be somewhat complex. Our simplified
 * format allows anything to be part of any data field, except for quotation
 * marks ("). Quotation marks are reserved for grouping data fields that
 * themselves contain a comma. For instance, consider the following line:
 *
 * <P>hello,"Peter, Paul, and Mary",how,are,you
 *
 * <P>This line contains 5 data fields, where the second is the string "Peter,
 * Paul, and Mary". Note that your String object should not contain the
 * quotation marks.
 *
 * <P>Another simplifying factor is that every line has exactly one data field per
 * member of the Movie class. You do not need to handle ill-formed data files.
 */
public class CSVParse {

  /**
   * Main interface for parsing a CSV file into a movie list.
   *
   * @param inFile The name of the file to parse.
   * @return The list of movies in the same order as the file contents.
   * @throws IOException if there is a null line in the file.
   */
  public static List<Movie> buildMovieList(String inFile) {
    File csvFile = new File(inFile);
    List<Movie> movieList = new ArrayList<Movie>();
    List<String> genres = new ArrayList<String>();
    BufferedReader csvReader = null; // initialized null
    Movie movie = null;
    String row = null;

    try { // try/catch for the BufferedReader

      csvReader = new BufferedReader(new FileReader(csvFile)); // initialize reader

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try { // try/catch for parsing the file

      while ((row = csvReader.readLine()) != null) { // parse the file
        List<String> parseList = new ArrayList<String>();
        int start = 0;
        boolean inQuotes = false;

        for (int i = 0; i < row.length(); i++) { // look at every character split along commas
          // as long as we are not in quotes
          if (row.charAt(i) == '\"') {
            inQuotes = !inQuotes; // toggle state
          }
          if (i == row.length() - 1) { // at the end of row
            parseList.add(row.substring(start));
          } else if (row.charAt(i) == ',' && !inQuotes) {
            parseList.add(row.substring(start, i));
            start = i + 1;
          }
        }
        genres = buildGenreList(parseList); // used to build the list of genres
        String title = parseList.get(1).substring(1, parseList.get(1).length() - 1);
        // remove quotes from title
        movie = new Movie(parseList.get(0), title, Double.parseDouble(parseList.get(2)),
            Integer.parseInt(parseList.get(3)), Integer.parseInt(parseList.get(4)), genres);
        movieList.add(movie);
      }

    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try { // try/catch for close file

      csvReader.close(); // close file

    } catch (IOException e) {
      e.printStackTrace();
    }

    return movieList;
  }

  /**
   * Helper method for buildMovieList, populates a list of genres for each movie
   * object.
   * 
   * @param parseList is the list to get genres from.
   * @return a list of genres to go into the movieList.
   */
  private static List<String> buildGenreList(List<String> parseList) {
    List<String> genres = new ArrayList<String>();
    String genreString = parseList.get(5); // get the string of genres
    genreString = genreString.substring(1, genreString.length() - 1); // get rid of quotes
    String[] genreArray = genreString.split(","); // create genre array

    for (String genre : genreArray) { // create genre list from array
      genres.add(genre);
    }

    return genres;
  }
}
