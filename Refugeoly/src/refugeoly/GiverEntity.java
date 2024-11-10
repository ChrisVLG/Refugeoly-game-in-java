package refugeoly;

public class GiverEntity implements MoneyGiver {
    private String name;
    private int money;
    
    public GiverEntity(String name, int money) {
       this.name = name;
       this.money = money;
    }
    
    @Override
    public void giveMoney(int amount) throws NoMoneyException {
        if(this.money - amount < 0) {
            throw new NoMoneyException("Den dinw alla lefta");
        } else {
            this.money -= amount;
        }
    }
    
    public int getMoney() {
        return this.money;
    }
}