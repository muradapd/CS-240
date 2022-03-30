public class BacktrackingQueens {

  private int[] queens;
  private int rows;

  /**
   * Create an array to keep track of the positions of each queen on a chess board
   * of the given size. For example, if queens[3] is set to 2, then the queen in
   * the fourth row (row index 3) is in the third column (column index 2), as
   * shown below:
   *
   * 0 1 2 3 4 5 6 7 +---+---+---+---+---+---+---+---+ 0 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 1 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 2 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 3 | | | Q | | | | | |
   * +---+---+---+---+---+---+---+---+ 4 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 5 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 6 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+ 7 | | | | | | | | |
   * +---+---+---+---+---+---+---+---+
   *
   * @param size The number of rows (and columns) on the board.
   */
  public BacktrackingQueens(int size) {
    queens = new int[size];
    rows = size;

    /* Mark all rows as unknown positions */
    for (int i = 0; i < size; i++) {
      queens[i] = -1;
    }
  }

  /**
   * Getter for the queens array. Used for testing purposes.
   *
   * @return The array of queens placed.
   */
  public int[] getQueens() {
    return queens;
  }

  /**
   * Gauss-Laquiere backtracking algorithm for solving the N queens problem. Given
   * the row argument, determine a partial solution with that many rows skipped.
   * That is, if row is 0, it will find a full solution. If row is 1, it assumes
   * there is one row already completed and finds a partial solution for the N-1
   * remaining rows.
   *
   * If row is equal to the number of rows on the board, we are done and we have a
   * solution. Otherwise, loop through each column within a row (note that the
   * number of rows and columns are the same). If it is legal to place a queen at
   * that column, do so by setting an entry in the queens array. That is,
   * queens[3] = 5 means that there is a queen in column 5 of row 3.
   *
   * Once the queen is placed, make a recursive call. If it succeeds, we have a
   * solution. If not, then remove the queen (by setting the value to -1) and move
   * on to the next possible column. If you get to the end of a row without
   * placing a queen, return false to indicate there is no solution based on the
   * previous rows. This causes the previous recursive call to backtrack and try a
   * new position.
   *
   * @param row The first row to complete.
   */
  public boolean placeQueens(int row) {
    /* Base case: partial solution is complete */
    if (row == rows) {
      return true;
    } else {
      for (int j = 0; j < rows; j++) {

        /*
         * If it is legal to place a queen in column j of row r, do so and recur. If the
         * recursion is not successful, remove the queen just placed and go to the next
         * legal column. If the recursion was successful, return true.
         */
        if (isLegal(row, j)) {
          queens[row] = j;
          if (!placeQueens(row + 1)) {
            queens[row] = -1;
          } else {
            return true;
          }
        }
      }

      /* No solution was possible */
      return false;
    }
  }

  /**
   * Helper routine to determine if placing a queen in column j is legal for the
   * given row in the current board. This check is the heart of the Gauss-Laquiere
   * algorithm. DO NOT MODIFY.
   *
   * @param row    The current row where a queen is being placed.
   * @param column The current column being tried within that row.
   *
   * @return Whether or not placing that queen is legal, given the placement of
   *         other queens within the board.
   */
  private boolean isLegal(int row, int column) {
    boolean legal = true;
    /*
     * Check each previous row to determine if that row's queen is reachable from
     * the current row/column position.
     */
    for (int i = 0; i < row; i++) {
      if (queens[i] >= 0) {
        if ((queens[i] == column) || (queens[i] == column + row - i)
            || (queens[i] == column - row + i)) {
          legal = false;
        }
      }
    }

    return legal;
  }

  /**
   * Create a string representation of the board.
   *
   * @return The board's string representation.
   */
  @Override
  public String toString() {
    String s = "";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < rows; j++) {
        s += "+---";
      }
      s += "+\n";
      for (int j = 0; j < rows; j++) {
        s += "| ";
        if (queens[i] == j) {
          s += "Q ";
        } else {
          s += "  ";
        }
      }
      s += "|\n";
    }
    for (int j = 0; j < rows; j++) {
      s += "+---";
    }
    s += "+\n";
    return s;
  }

}
