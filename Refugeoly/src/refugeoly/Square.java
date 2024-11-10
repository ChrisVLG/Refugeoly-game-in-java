package refugeoly;

import java.util.ArrayList;

public class Square {
    private int number;
    private String text;
    private int round;
    private ArrayList<String> actions;
     
    Square(int number, String text, int round) {
        this.number = number;
        this.text = text;
        this.round = round;
        this.actions = new ArrayList<>();
    }
    
    public int getSquareNumber() {
        return this.number;
    }
    
    public void addAction(String action) {
        (this.actions).add(action);
        System.out.println(action);
    }
    
    public String getText() {
        return this.text;
    }
}
