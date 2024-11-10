package refugeoly;

public class Refugee implements MoneyGiver, MoneyReceiver {
    private String name;
    private int money;
    private int expenses;
    private Board board;
    private Square square;
    private int dice;
    private boolean vest;
    private int stay;
    private boolean ended;
    
    public Refugee(String name, int money, Board board, Square square) {
        this.name = name;
        this.money = money;
        this.expenses = 0;
        this.board = board;
        this.square = square;
        this.vest = false;
        this.stay = 0;
        this.ended = false;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public void receiveMoney(int amount) {
        this.money += amount;
    }
    
    @Override
    public void giveMoney(int amount) throws NoMoneyException {
        if(this.money - amount < 0) {
            this.money = 0;
            throw new NoMoneyException("exase");
        } else {
            this.money -= amount;
        }
    }
    
    public void setExpenses(int expenses) {
        this.expenses += expenses;
    }
    
    public double getExpenses() {
        return this.expenses;
    }
    
    public Square getSquare() {
        return this.square;
    }
    
    public void moveTo(Square square) {
        this.square = square;
    }
    
    public void setDice(int number) {
        this.dice = number;
    }
    
    public Board getBoard() {
        return this.board;
    }
    public double getMoney(){
        return this.money;
    
    }
     public void setMoney(int money){
       this.money = money;
    }
    public boolean hasVest(){
        return this.vest;
    }
           
    public void setVest(boolean state){
        this.vest = state;
    }
    
    public int hasToStay(){
        return this.stay;
    }
           
    public void setStay(int state){
        this.stay = state;
    }
  
    public boolean hasEnded(){
        return this.ended;
    }
           
    public void setEnded(){
        this.ended = true;
    }
}
