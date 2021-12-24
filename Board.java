import java.util.Map;
import java.util.HashMap;

public class Board 
{
  private Map<Character, Character> state = new HashMap<Character, Character>();
  
  /* 
   * Places the tokens at the start of the game
  */
  public Board() 
  {
    placeBlack(4, 4);
    placeBlack(3, 3);
    placeWhite(3, 4);
    placeWhite(4, 3);
  }

  /*
   * Fills up the board with random characters so that they can be replaced
  */
  public String render() 
  {
    String board = 
      "╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n" +
      "║ # │ $ │ % │ & │ ' │ ( │ ) │ * ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ + │ , │ - │ . │ / │ 0 │ 1 │ 2 ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ : ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ ; │ < │ = │ > │ ? │ @ │ A │ B ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ C │ D │ E │ F │ G │ H │ I │ J ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ K │ L │ M │ N │ O │ P │ Q │ R ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ S │ T │ U │ V │ W │ X │ Y │ Z ║\n" +
      "╟───┼───┼───┼───┼───┼───┼───┼───╢\n" +
      "║ [ │ ~ │ ] │ ^ │ _ │ ` │ a │ b ║\n" +
      "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
    char[] boardArray = board.toCharArray();
    String string = "";
    for (int i = 0; i < boardArray.length; i++) 
    {
      if (boardArray[i] > 32 && boardArray[i] < 127) 
      {
        boardArray[i] = state.getOrDefault(boardArray[i], ' ');
      }
      string += boardArray[i];
    }
    return string;
  }
  
  /*
   * Places the black and white tokens that the user inputs, but does not show it yet
  */
  public void placeBlack(int x, int y) 
  {
    char position = (char) (y * 8 + x + 35);
    state.put(position, '○');
  }
  public void placeWhite(int x, int y) 
  {
    char position = (char) (y * 8 + x + 35);
    state.put(position, '●');
  }

  /* 
   * Checks whether the position of the input is valid, if it is, it places the tokens
  */
  public boolean checkBlack(int x, int y) 
  {
    char position = (char) (y * 8 + x + 35);
    if (!state.containsKey(position)) 
    {
      if (y > 1) 
      {
        char checkPos = (char) ((y - 1) * 8 + x + 35);
        if (state.getOrDefault(checkPos, ' ') == '●') 
        {
          for (int i = y - 2; i >= 0; i--) 
          {
            char scanPos = (char) (i * 8 + x + 35);
            if (state.getOrDefault(scanPos, ' ') == '●') continue;
            if (state.getOrDefault(scanPos, ' ') == '○') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (y < 6) 
      {
        char checkPos = (char) ((y + 1) * 8 + x + 35);
        if (state.getOrDefault(checkPos, ' ') == '●') 
        {
          for (int i = y + 2; i <= 7; i++) 
          {
            char scanPos = (char) (i * 8 + x + 35);
            if (state.getOrDefault(scanPos, ' ') == '●') continue;
            if (state.getOrDefault(scanPos, ' ') == '○') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (x > 1) 
      {
        char checkPos = (char) (y * 8 + (x - 1) + 35);
        if (state.getOrDefault(checkPos, ' ') == '●') 
        {
          for (int i = x - 2 ; i >= 0 ; i--) 
          {
            char scanPos = (char) (y * 8 + i + 35);
            if (state.getOrDefault(scanPos, ' ') == '●') continue;
            if (state.getOrDefault(scanPos, ' ') == '○') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (x < 6) 
      {
        char checkPos = (char) (y * 8 + (x + 1) + 35);
        if (state.getOrDefault(checkPos, ' ') == '●') 
        {
          for (int i = x - 2; i <= 7; i++) 
          {
            char scanPos = (char) (y * 8 + i + 35);
            if (state.getOrDefault(scanPos, ' ') == '●') continue;
            if (state.getOrDefault(scanPos, ' ') == '○') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
    }
    return false;
  }

  public boolean checkWhite(int x, int y) 
  {
    char position = (char) (y * 8 + x + 35);
    if (!state.containsKey(position)) 
    {
      if (y > 1) {
        char checkPos = (char) ((y - 1) * 8 + x + 35);
        if (state.getOrDefault(checkPos, ' ') == '○') 
        {
          for (int i = y - 2; i >= 0; i--) {
            char scanPos = (char) (i * 8 + x + 35);
            if (state.getOrDefault(scanPos, ' ') == '○') continue;
            if (state.getOrDefault(scanPos, ' ') == '●') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (y < 6) 
      {
        char checkPos = (char) ((y + 1) * 8 + x + 35);
        if (state.getOrDefault(checkPos, ' ') == '○') 
        {
          for (int i = y + 2; i <= 7; i++) 
          {
            char scanPos = (char) (i * 8 + x + 35);
            if (state.getOrDefault(scanPos, ' ') == '○') continue;
            if (state.getOrDefault(scanPos, ' ') == '●') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (x > 1) 
      {
        char checkPos = (char) (y * 8 + (x - 1) + 35);
        if (state.getOrDefault(checkPos, ' ') == '○') 
        {
          for (int i = x - 2; i >= 0; i--) {
            char scanPos = (char) (y * 8 + i + 35);
            if (state.getOrDefault(scanPos, ' ') == '○') continue;
            if (state.getOrDefault(scanPos, ' ') == '●') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
      if (x < 6) 
      {
        char checkPos = (char) (y * 8 + (x + 1) + 35);
        if (state.getOrDefault(checkPos, ' ') == '○') 
        {
          for (int i = x + 2; i <= 7; i++) {
            char scanPos = (char) (y * 8 + i + 35);
            if (state.getOrDefault(scanPos, ' ') == '○') continue;
            if (state.getOrDefault(scanPos, ' ') == '●') return true;
            if (state.getOrDefault(scanPos, ' ') == ' ') return false;
          }
        }
      }
    }
    return false;
  }
}