package refugeoly;

public class GoToAction extends Action {

    private Square square;
    private boolean stayed;

    @Override
    void act(Refugee refugee) {
        Square prevsquare = refugee.getSquare();
        refugee.moveTo(this.square);
        if (stayed == false) {
            prevsquare.addAction(refugee.getName() + " moved to square " + (this.square).getSquareNumber());
        } else {
            prevsquare.addAction(refugee.getName() + " stayed on square " + (this.square).getSquareNumber());
        }
    }

    void setSquare(Square square, boolean stayed) {
        this.square = square;
        this.stayed = stayed;
    }
}
