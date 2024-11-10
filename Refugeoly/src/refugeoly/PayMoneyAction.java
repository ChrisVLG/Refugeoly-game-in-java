package refugeoly;

public class PayMoneyAction extends Action {
    private int amount;
    private ReceiverEntity Mafia;

    @Override
    void act(Refugee refugee) {
        Square square = refugee.getSquare();
        try {
            refugee.giveMoney(this.amount);
            (this.Mafia).receiveMoney(this.amount);
        } catch (NoMoneyException ex) {
            
        }
    }
    
    void setAmount(int amount) {
        this.amount = amount;
    }
    
    void setReceiver(ReceiverEntity Mafia) {
        this.Mafia = Mafia;
    }
}
