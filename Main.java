import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Main 
{
  public static void main(String[] args) 
  {
    System.out.println("Make sure to have your caps lock on :P");

    Scanner input = new Scanner(System.in);
    
    GameLogic board = new GameLogic();

    /*
     * Sees whether someone has won or if the game is tied
    */
    while (true) 
    {
      System.out.println(board);

      while (true) 
      {
        if (board.validMove(Token.BLACK) == false) 
        {
          input.close();
          System.out.println("Black has " + board.countTokens(Token.BLACK) + " tokens");
          
          if (board.countTokens(Token.BLACK) > board.countTokens(Token.WHITE)) 
          {
            System.out.println("Player black has won!");
            return;
          }

          if (board.countTokens(Token.BLACK) < board.countTokens(Token.WHITE)) 
          {
            System.out.println("Player white has won!");
            return;
          }

          if (board.countTokens(Token.BLACK) == board.countTokens(Token.WHITE)) 
          {
            System.out.println("The players are tied!");
            return;
          }
        }
        System.out.println("Pick a move (black):");
        String line = input.nextLine();
        
        /*
         * Checks whether player black has resigned
        */
        if (line.toLowerCase().equals("resign")) 
        {
          input.close();
          System.out.println("Black has " + board.countTokens(Token.BLACK) + " tokens");
          System.out.println("White has " + board.countTokens(Token.WHITE) + " tokens");
          System.out.println("Player black has resigned");
          return;
        }

        Matcher yMatcher = Pattern.compile("\\d").matcher(line);
        Matcher xMatcher = Pattern.compile("[A-Z]").matcher(line);
        xMatcher.find();
        yMatcher.find();

        /*
         * Finds the positions of the tokens and sees whether it is valid or not
        */
        int x = xMatcher.group(0).charAt(0) - 65;
        int y = yMatcher.group(0).charAt(0) - 49;

        if (board.checkValid(x, y, Token.BLACK)) 
        {
          board.placeToken(x, y, Token.BLACK);
          board.flip(x, y, Token.BLACK);
          break;
        }
        else 
        {
          System.out.println("This move is invalid.");
        }
      }

      System.out.println(board);

      while (true) 
      {
        if (board.validMove(Token.WHITE) == false) 
        {
          input.close();
          System.out.println("White has " + board.countTokens(Token.WHITE) + " tokens");

          if (board.countTokens(Token.BLACK) > board.countTokens(Token.WHITE)) 
          {
            System.out.println("Player black has won!");
            return;
          }

          if (board.countTokens(Token.BLACK) < board.countTokens(Token.WHITE)) 
          {
            System.out.println("Player white has won!");
            return;
          }

          if (board.countTokens(Token.BLACK) == board.countTokens(Token.WHITE)) 
          {
            System.out.println("The players are tied!");
            return;
          }
        }
        
        /* 
         * Sees whether player white has resigned
        */
        System.out.println("Pick a move (white):");
        String line = input.nextLine();

        if (line.toLowerCase().equals("resign")) 
        {
          input.close();
          System.out.println("Black has " + board.countTokens(Token.BLACK) + " tokens");
          System.out.println("White has " + board.countTokens(Token.WHITE) + " tokens");
          System.out.println("Player white has resigned");
          return;
        }

        /*
         * Finds the positions of the tokens and sees whether it is valid or not
        */
        Matcher yMatcher = Pattern.compile("\\d").matcher(line);
        Matcher xMatcher = Pattern.compile("[A-Z]").matcher(line);
        xMatcher.find();
        yMatcher.find();

        int x = xMatcher.group(0).charAt(0) - 65;
        int y = yMatcher.group(0).charAt(0) - 49;

        if (board.checkValid(x, y, Token.WHITE)) 
        {
          board.placeToken(x, y, Token.WHITE);
          board.flip(x, y, Token.WHITE);
          break;
        }
        else 
        {
          System.out.println("This move is invalid.");
        }
      }
    }
  }
}