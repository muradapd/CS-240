import java.util.List;

/**
 * Class for an immutable movie object. Each instance of this class corresponds
 * to one line of a provided comma-separated value file.
 */
public class Movie {

  private String identifier;
  private String title;
  private double imdbRating;
  private int runtime;
  private int year;
  private List<String> genres;

  /**
   * Build a movie based on the fields in the CSV file.
   *
   * @param identifier An internal IMDb string identifier.
   * @param title      The movie title.
   * @param imdbRating The average user rating (out of 10).
   * @param runtime    The length of the movie in minutes.
   * @param year       The year the movie was released.
   * @param genres     One or more genres to which the movie belongs.
   */
  public Movie(String identifier, String title, double imdbRating, int runtime, int year,
      List<String> genres) {
    this.identifier = identifier;
    this.title = title;
    this.imdbRating = imdbRating;
    this.runtime = runtime;
    this.year = year;
    this.genres = genres;
  }

  /**
   * Get the identifier.
   *
   * @return The movie identifier.
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   * Get the title.
   *
   * @return The movie title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Get the IMDb rating.
   *
   * @return The movie's rating on IMDb.
   */
  public double getImdbRating() {
    return imdbRating;
  }

  /**
   * Get the runtime.
   *
   * @return The movie's runtime in minutes.
   */
  public int getRuntime() {
    return runtime;
  }

  /**
   * Get the year released.
   *
   * @return The movie's release year.
   */
  public int getYear() {
    return year;
  }

  /**
   * Get the genres.
   *
   * @return One or more genres for the movie.
   */
  public List<String> getGenres() {
    return genres;
  }

  /**
   * Convert the movie to a string format.
   *
   * @return The movie formatted as a string.
   */
  @Override
  public String toString() {
    return getTitle() + " [" + getYear() + ", " + getRuntime() + " minutes, " + getImdbRating()
        + "/10.0]";
  }

}
