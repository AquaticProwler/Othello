public class GameLogic 
{
  /* 
   * Makes a table for the board
  */
  private Token[][] state = new Token[8][8];

  /*
   * Position of the 4 tokens placed at the start of the game
  */
  public GameLogic() 
  {
    for (int i = 0; i < 8; i++) 
    {
      for (int j = 0; j < 8; j++) 
      {
        state[i][j] = Token.NONE;
      }
    }
    state[3][3] = Token.WHITE;
    state[4][4] = Token.WHITE;
    state[3][4] = Token.BLACK;
    state[4][3] = Token.BLACK;
  }

  /*
   * Creates a map of the board so that we can insert the tokens
  */
  public String toString() 
  {
    return (
      "\u001b[1J\u001b[H" +
      "    A   B   C   D   E   F   G   H  \n" +
      "  ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n" +
      "1 ║ " + state[0][0] + " │ " + state[0][1] + " │ " + state[0][2] + " │ " + state[0][3] + " │ " + state[0][4] + " │ " + state[0][5] + " │ " + state[0][6] + " │ " + state[0][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "2 ║ " + state[1][0] + " │ " + state[1][1] + " │ " + state[1][2] + " │ " + state[1][3] + " │ " + state[1][4] + " │ " + state[1][5] + " │ " + state[1][6] + " │ " + state[1][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "3 ║ " + state[2][0] + " │ " + state[2][1] + " │ " + state[2][2] + " │ " + state[2][3] + " │ " + state[2][4] + " │ " + state[2][5] + " │ " + state[2][6] + " │ " + state[2][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "4 ║ " + state[3][0] + " │ " + state[3][1] + " │ " + state[3][2] + " │ " + state[3][3] + " │ " + state[3][4] + " │ " + state[3][5] + " │ " + state[3][6] + " │ " + state[3][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "5 ║ " + state[4][0] + " │ " + state[4][1] + " │ " + state[4][2] + " │ " + state[4][3] + " │ " + state[4][4] + " │ " + state[4][5] + " │ " + state[4][6] + " │ " + state[4][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "6 ║ " + state[5][0] + " │ " + state[5][1] + " │ " + state[5][2] + " │ " + state[5][3] + " │ " + state[5][4] + " │ " + state[5][5] + " │ " + state[5][6] + " │ " + state[5][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "7 ║ " + state[6][0] + " │ " + state[6][1] + " │ " + state[6][2] + " │ " + state[6][3] + " │ " + state[6][4] + " │ " + state[6][5] + " │ " + state[6][6] + " │ " + state[6][7] + " ║\n" +
      "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "8 ║ " + state[7][0] + " │ " + state[7][1] + " │ " + state[7][2] + " │ " + state[7][3] + " │ " + state[7][4] + " │ " + state[7][5] + " │ " + state[7][6] + " │ " + state[7][7] + " ║\n" +
      "  ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝"
    );
  }

  /*
   * Sees if the tokens are being placed in the correct position
  */
  public boolean validMove(Token piece) 
  {
    for (int i = 0; i < 8; i++) 
    {
      for (int j = 0; j < 8; j++) 
      {
        if (checkValid(i, j, piece)) 
        {
          return true;
        }
      }
    }
    return false;
  }

  /* 
   * Counts the number of tokens
  */
  public int countTokens(Token piece) 
  {
    int tokenNum = 0;
    for (int i = 0; i < 8; i++) 
    {
      for (int j = 0; j < 8; j++) 
      {
        if (state[i][j] == piece) 
        {
          tokenNum += 1;
        }
      }
    }
    return tokenNum;
  }

  /*
   * Main method that holds the calculations to see whether the placements are valid or not
  */
  public boolean checkValid(int x, int y, Token piece) 
  {
    // check if a piece is already in the selected point 
    if (state[y][x] != Token.NONE) 
    {
      return false;
    }
    // check for an adjacent piece of the opposite color
    boolean adjacentOpponent = false;
    if (x > 0) {
      if (state[y][x - 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (x < 7) 
    {
      if (state[y][x + 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (y > 0) 
    {
      if (state[y - 1][x] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (y < 7) 
    {
      if (state[y + 1][x] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (x > 0 && y > 0) 
    {
      if (state[y - 1][x - 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (x > 0 && y < 7) 
    {
      if (state[y + 1][x - 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (x < 7 && y > 0) 
    {
      if (state[y - 1][x + 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }

    if (x < 7 && y < 7) 
    {
      if (state[y + 1][x + 1] == piece.opponent()) 
      {
        adjacentOpponent = true;
      }
    }
    // check for a piece of the same color in the row, column, or diagonal
    if (adjacentOpponent) 
    {
      for (int i = x + 2; i < 8; i++) 
      {
        if (state[y][i] == piece) return true;
        if (state[y][i] == Token.NONE) break;
      }

      for (int i = x - 2; i >= 0; i--) 
      {
        if (state[y][i] == piece) return true;
        if (state[y][i] == Token.NONE) break;
      }

      for (int i = y + 2; i < 8; i++) 
      {
        if (state[i][x] == piece) return true;
        if (state[i][x] == Token.NONE) break;
      }

      for (int i = y - 2; i >= 0; i--) 
      {
        if (state[i][x] == piece) return true;
        if (state[i][x] == Token.NONE) break;
      }
      
      for (int i = 2; x + i < 8 && y + i < 8; i++) 
      {
        if (state[y + i][x + i] == piece) return true;
        if (state[y + i][x + i] == Token.NONE) break;
      }

      for (int i = 2; x - i >= 0 && y + i < 8; i++) 
      {
        if (state[y + i][x - i] == piece) return true;
        if (state[y + i][x - i] == Token.NONE) break;
      }

      for (int i = 2; x + i < 8 && y - i >= 0; i++) 
      {
        if (state[y - i][x + i] == piece) return true;
        if (state[y - i][x + i] == Token.NONE) break;
      }

      for (int i = 2; x - i >= 0 && y - i >= 0; i++) 
      {
        if (state[y - i][x - i] == piece) return true;
        if (state[y - i][x - i] == Token.NONE) break;
      }
    }
    return false;
  }

  public boolean findSimilar(int row, int cell, int dy, int dx, Token piece) 
  {
    for (int i = 0; row + dy * i < 8 && cell + dx * i < 8; i++) 
    {
      if (state[row + dy * i][cell + dx * i] == Token.NONE) return false;
      if (state[row + dy * i][cell + dx * i] == piece) return true; 
    }

    return false;
  }

  public void placeToken(int x, int y, Token piece) 
  {
    state[y][x] = piece;
  }

  public void flip(int x, int y, Token piece) 
  {
    boolean captureE = false;
    boolean captureW = false;
    boolean captureN = false;
    boolean captureS = false;
    boolean captureNE = false;
    boolean captureSE = false;
    boolean captureSW = false;
    boolean captureNW = false;

    for (int i = 1; x + i < 8; i++) 
    {
      if (state[y][x + i] == piece) captureE = true;
      if (state[y][x + i] == Token.NONE) break;
    }

    for (int i = 1; x - i >= 0; i++) 
    {
      if (state[y][x - i] == piece) captureW = true;
      if (state[y][x - i] == Token.NONE) break;
    }

    for (int i = 1; y + i < 8; i++) 
    {
      if (state[y + i][x] == piece) captureS = true;
      if (state[y + i][x] == Token.NONE) break;
    }

    for (int i = 1; y - i >= 0; i++) 
    {
      if (state[y - i][x] == piece) captureN = true;
      if (state[y - i][x] == Token.NONE) break;
    }

    for (int i = 1; x + i < 8 && y - i >= 0; i++) 
    {
      if (state[y - i][x + i] == piece) captureNE = true;
      if (state[y - i][x + i] == Token.NONE) break;
    }

    for (int i = 1; x + i < 8 && y + i < 8; i++) 
    {
      if (state[y + i][x + i] == piece) captureSE = true;
      if (state[y + i][x + i] == Token.NONE) break;
    }

    for (int i = 1; x - i >= 0 && y + i < 8; i++) 
    {
      if (state[y + i][x - i] == piece) captureSW = true;
      if (state[y + i][x - i] == Token.NONE) break;
    }

    for (int i = 1; x - i >= 0 && y - i >= 0; i++) 
    {
      if (state[y - i][x - i] == piece) captureNW = true;
      if (state[y - i][x - i] == Token.NONE) break;
    }

    if (captureE) 
    {
      for (int i = 1; x + i < 8 && state[y][x + i] == piece.opponent(); i++) 
      {
        state[y][x + i] = piece;
      }
    }

    if (captureW) 
    {
      for (int i = 1; x - i >= 0 && state[y][x - i] == piece.opponent(); i++) 
      {
        state[y][x - i] = piece;
      }
    }

    if (captureS) 
    {
      for (int i = 1; y + i >= 0 && state[y + i][x] == piece.opponent(); i++) 
      {
        state[y + i][x] = piece;
      }
    }

    if (captureN) 
    {
      for (int i = 1; y - i >= 0 && state[y - i][x] == piece.opponent(); i++) 
      {
        state[y - i][x] = piece;
      }
    }

    if (captureNE) 
    {
      for (int i = 1; y - i >= 0 && x + i < 8 && state[y - i][x + i] == piece.opponent(); i++) 
      {
        state[y - i][x + i] = piece;
      }
    }

    if (captureSE) 
    {
      for (int i = 1; y + i < 8 && x + i < 8 && state[y + i][x + i] == piece.opponent(); i++) 
      {
        state[y + i][x + i] = piece;
      }
    }

    if (captureSW) 
    {
      for (int i = 1; y + i < 8 && x - i >= 0 && state[y + i][x - i] == piece.opponent(); i++) 
      {
        state[y + i][x - i] = piece;
      }
    }

    if (captureNW) 
    {
      for (int i = 1; y - i >= 0 && x - i >= 0 && state[y - i][x - i] == piece.opponent(); i++) 
      {
        state[y - i][x - i] = piece;
      }
    }
  }
}