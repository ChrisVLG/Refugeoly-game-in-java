package refugeoly;

public class NoMoneyException extends Exception{

    public NoMoneyException() {
        
    }

    public NoMoneyException(String msg) {
        super(msg);
    }
}
