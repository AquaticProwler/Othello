/*
 * This enum class makes sure that the tokens cannot be changed by any method
*/
public enum Token 
{
  BLACK {
    public String toString() { return "○"; }
    public Token opponent() { return Token.WHITE; }
  }, 
  WHITE {
    public String toString() { return "●"; }
    public Token opponent() { return Token.BLACK; }
  }, 
  NONE {
    public String toString() { return " "; }
    public Token opponent() { return null; }
  };

  public abstract String toString();
  public abstract Token opponent();
}