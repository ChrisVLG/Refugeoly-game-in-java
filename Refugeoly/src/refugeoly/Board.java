package refugeoly;

public class Board {

    private int round;
    private int i = 0;
    private Square[] squares = new Square[40];
    private ReceiverEntity mafia;
    private GiverEntity NGO;

    public Board(int round, ReceiverEntity mafia, GiverEntity NGO) {
        this.round = round;
        this.mafia = mafia;
        this.NGO = NGO;
    }

    public void AddSquare(Square square) {
        squares[(this.i)] = square;
        (this.i)++;
    }

    public Square getSquare(int index) {
        return squares[index];
    }
    
    public String getSquareText(int index) {
        return (squares[index]).getText();
    }
    
    public ReceiverEntity getReceiverEntity() {
        return this.mafia;
    }
    
    public GiverEntity getGiverEntity() {
        return this.NGO;
    }
}
