package refugeoly;

import java.util.Random;
import java.util.Scanner;

public class RollDiceAction extends Action {

    private int dice;

    Random rnd = new Random();

    @Override
    void act(Refugee refugee) {
        Square square = refugee.getSquare();
        if (refugee.hasToStay() > 0) {
            square.addAction(refugee.getName() + " couldn't roll");
        } else {
            Scanner input = new Scanner(System.in);
            String str;
            do {
                System.out.print("Player " + refugee.getName() + " type \"Roll\" to roll the dice: ");
                str = input.nextLine();
            } while (!str.equalsIgnoreCase("roll"));
            this.dice = rnd.nextInt(6) + 1;
            square.addAction(refugee.getName() + " rolled " + this.dice);
        }
    }

    public int getDice() {
        return this.dice;
    }
}
