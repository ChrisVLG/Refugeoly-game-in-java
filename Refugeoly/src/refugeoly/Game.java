package refugeoly;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    public static int rollDice(Refugee player) {
        RollDiceAction rollDiceAction = new RollDiceAction();
        rollDiceAction.act(player);
        return rollDiceAction.getDice();
    }

    public static void moveSquares(Refugee player, int amount) {
        GoToAction goToAction = new GoToAction();
        Board board = player.getBoard();
        int squareNumber = amount + (player.getSquare()).getSquareNumber();
        if (squareNumber > 39) {
            squareNumber = 39 - (squareNumber - 39);
        }
        Square newSquare = board.getSquare(squareNumber);
        if (player.hasToStay() > 0) {
            goToAction.setSquare(newSquare, true);
        } else {
            goToAction.setSquare(newSquare, false);
        }
        goToAction.act(player);
    }

    public static void payMafia(Refugee payer, int amount) {
        Board board = payer.getBoard();
        ReceiverEntity Mafia = board.getReceiverEntity();
        PayMoneyAction payMoneyAction = new PayMoneyAction();
        payMoneyAction.setAmount(amount);
        payMoneyAction.setReceiver(Mafia);
        payMoneyAction.act(payer);
        payer.setExpenses(amount);
    }

    public static void doSquareAction(Refugee player) throws NoMoneyException {
        Square square = player.getSquare();
        int squareNumber = square.getSquareNumber();
        Board board = player.getBoard();
        if (player.hasToStay() == 0) {
            System.out.println((player.getBoard()).getSquareText(squareNumber));
        }
        if (squareNumber == 1) {
            player.giveMoney(100);
            player.setExpenses(100);

        } else if (squareNumber == 2 || squareNumber == 12 || squareNumber == 17 || squareNumber == 22 || squareNumber == 28 || squareNumber == 33) {  // roll dice actions    
            if (squareNumber == 33) {
                moveSquares(player, -16);
            }

            int dice = rollDice(player);

            if (squareNumber == 22) {
                dice = dice * -1;
            }

            moveSquares(player, dice);
            try {
                doSquareAction(player);
            } catch (NoMoneyException ex) {

            }
        } else if (squareNumber == 3 || squareNumber == 6 || squareNumber == 9 || squareNumber == 13 || squareNumber == 16 || squareNumber == 31 || squareNumber == 37) {  // pay mafia action
            String text = square.getText();
            String numericPart = text.replaceAll("[^0-9]", "");
            int amount = Integer.parseInt(numericPart);
            payMafia(player, amount);
            if (squareNumber == 9 || squareNumber == 16 || squareNumber == 31) { // roll dice action 
                int dice = rollDice(player);
                moveSquares(player, dice);
                try {
                    doSquareAction(player);
                } catch (NoMoneyException ex) {
                }

            }
        } else if (squareNumber == 4 || squareNumber == 5 || squareNumber == 38) {
            moveSquares(player, -squareNumber);
        } else if (squareNumber == 15 || squareNumber == 25 || squareNumber == 35) {
            moveSquares(player, -10);
        } else if (squareNumber == 7) {
            player.setVest(true);

        } else if (squareNumber == 10) {
            if (player.hasVest() == false) {
                endGamePlayer(player, false);
            } else {
                System.out.println("You have a live vest so you survive the sea");
                player.setVest(false);
            }
        } else if (squareNumber == 8 || squareNumber == 11 || squareNumber == 14 || squareNumber == 19 || squareNumber == 24 || squareNumber == 27 || squareNumber == 32 || squareNumber == 34) { // stay turn action
            if (player.hasToStay() > 0) {
                player.setStay(0);
            } else {
                player.setStay(1);
            }
        } else if (squareNumber == 18) {
            moveSquares(player, 4);
        } else if (squareNumber == 23) {
            moveSquares(player, 6);
        } else if (squareNumber == 29) {
            moveSquares(player, 2);
        } else if (squareNumber == 20) {
            GiverEntity NGO = board.getGiverEntity();
            NGO.giveMoney(1000);
            player.receiveMoney(1000);
        } else if (squareNumber == 21) {
            player.setExpenses(1500);
            player.giveMoney(1500);
        } else if (squareNumber == 26) {
            if (player.hasToStay() > 0) {
                player.setStay(player.hasToStay() - 1);
            } else {
                payMafia(player, 1000);
                Scanner input = new Scanner(System.in);
                String option;
                do {
                    System.out.print("Type \"Option A\" or \"Option B\" continue: ");
                    option = input.nextLine();
                } while (!(option.equalsIgnoreCase("Option A") || option.equalsIgnoreCase("Option B")));

                if (option.equalsIgnoreCase("Option A")) {
                    payMafia(player, 1500);
                } else {
                    player.setStay(2);
                }
            }
        } else if (squareNumber == 36 || squareNumber == 39) {
            endGamePlayer(player, true);
        }

    }

    public static void endTurn(Refugee player) {
        Board board = player.getBoard();
        ReceiverEntity Mafia = board.getReceiverEntity();
        GiverEntity NGO = board.getGiverEntity();
        System.out.println(player.getName() + "'s money: $" + player.getMoney() + " / expenses: $" + player.getExpenses());
        System.out.println("Mafia's money : $" + Mafia.getMoney());
        System.out.println("NGO's money: $" + NGO.getMoney());
    }

    public static void checkPlayers(Refugee players[]) {
        boolean flag = false;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getMoney() > 0) {
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("The game is over");
            System.exit(0);
        }

    }

    public static void endGamePlayer(Refugee player, boolean won) {
        if (player.hasEnded() == false) {
            if (won == true) {
                System.out.println(player.getName() + " Won the game ");
            } else {
                System.out.println(player.getName() + " Lost the game");
            }
            player.setMoney(0);
            player.setEnded();
        }
    }

    public static void main(String[] args) {
        GiverEntity NGO = new GiverEntity("NGO Bank", 10000);
        ReceiverEntity Mafia = new ReceiverEntity("Mafia Bank", 0);
        Board board = new Board(0, Mafia, NGO);

        Scanner input = new Scanner(System.in);
        int numplayers;
        do {
            System.out.print("Enter amount of players (1-4): ");
            if (input.hasNextInt()) {
                numplayers = input.nextInt();
                if (numplayers > 0 && numplayers < 5) {
                    break;
                }
            }
        } while (true);

        try (Scanner fileScanners = new Scanner(new FileInputStream("refugeoly-squares.txt"))) {
            for (int i = 0; i < 40; i++) {
                Scanner fileScanner = new Scanner(new FileInputStream("refugeoly-squares.txt"));
                for (int j = 0; j < 159; j++) {
                    if (j == 4 * i + 1) {
                        if (fileScanner.hasNextLine()) {
                            String text = fileScanner.nextLine();
                            Square square = new Square(i, text, 0);
                            board.AddSquare(square);
                        }
                    } else {
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file for reading");
        }

        Refugee players[] = new Refugee[numplayers];
        for (int i = 0; i < numplayers; i++) {
            players[i] = new Refugee("refugee" + (i + 1), 10000, board, board.getSquare(0));
        }

        while (true) {
            for (int i = 0; i < numplayers; i++) {
                if (players[i].hasEnded() == false) {
                    int dice = rollDice(players[i]);
                    moveSquares(players[i], dice);
                    try {
                        doSquareAction(players[i]);
                    } catch (NoMoneyException ex) {

                    }
                    if (players[i].getMoney() == 0) {
                        endGamePlayer(players[i], false);
                    }
                    if (players[i].getMoney() != 0) {
                        endTurn(players[i]);
                    }
                }
                checkPlayers(players);
            }
        }

    }
}