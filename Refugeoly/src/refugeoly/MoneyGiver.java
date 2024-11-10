package refugeoly;

public interface MoneyGiver {
    void giveMoney(int amount) throws NoMoneyException;
}