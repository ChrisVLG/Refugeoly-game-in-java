package refugeoly;

public class ReceiverEntity implements MoneyReceiver {
    private String name;
    private int money;
    
    public ReceiverEntity(String name, int money) {
       this.name = name;
       this.money = money;
    }
    
    @Override
    public void receiveMoney(int amount) {
        this.money = this.money + amount;
    }
    
    public int getMoney() {
        return this.money;
    }
}   