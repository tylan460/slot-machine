import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String[] symbols = { "🔔", "🍒", "🍋", "⭐️", "🍉" };

        int bet = 0;
        int balance = 100;
        boolean play = true;
        int bellCount = 0;
        int starCount = 0;
        int cherryCount = 0;
        int lemonCount = 0;
        int watermelonCount = 0;
        String decision = "";

        draw_stars(1);
        System.out.println("    Welcome to Java Slots");
        System.out.printf("Symbols: %s%n", Arrays.toString(symbols));
        draw_stars(1);

        while (play) {

            if (balance == 0) {
                System.out.println("Insufficient funds, cannot continue\nGOODBYE!");
                play = false;
                break;

            }
            System.out.printf("Current balance: %d\n", balance);

            while (bet <= 0) {

                System.out.print("Place your bet amount: ");
                bet = scanner.nextInt();
                scanner.nextLine();
                if (bet <= 0) {
                    System.out.println("Your Bet cannot be 0 or less");
                }
            }

            System.out.println("Spinning...");

            balance -= bet;

            String[] randomSymbols = new String[3];

            for (int i = 0; i < randomSymbols.length; i++) {
                randomSymbols[i] = symbols[random.nextInt(symbols.length)];
            }
            System.out.println(Arrays.toString(randomSymbols));

            for (String symbol : randomSymbols) {
                switch (symbol) {
                    case "🔔" -> {
                        bellCount += 1;
                        switch (bellCount) {
                            case 3 -> bet *= 8;
                            case 2 -> bet *= 5;
                        }
                    }
                    case "⭐️" -> {
                        starCount += 1;
                        switch (starCount) {
                            case 3 -> bet *= 9;
                            case 2 -> bet *= 6;

                        }
                    }
                    case "🍒" -> {
                        cherryCount += 1;
                        switch (cherryCount) {
                            case 3 -> bet *= 5;
                            case 2 -> bet *= 3;

                        }
                    }
                    case "🍋" -> {
                        lemonCount += 1;
                        switch (lemonCount) {

                            case 3 -> bet *= 5;
                            case 2 -> bet *= 4;
                        }
                    }
                    case "🍉" -> {
                        watermelonCount += 1;
                        switch (watermelonCount) {
                            case 3 -> bet *= 6;
                            case 2 -> bet *= 4;
                        }
                    }
                }
            }

            int[] symbolCounts = { bellCount, starCount, watermelonCount, cherryCount, lemonCount };
            int lossScore = 0;

            for (int symbolCount : symbolCounts) {
                if (symbolCount >= 2) {
                    System.out.printf("You won $%d\n", bet);
                    balance += bet;
                    break;
                } else {
                    if (symbolCount == 0) {
                        continue;
                    } else {
                        lossScore += 1;
                    }
                }
            }
            if (lossScore == 3) {
                System.out.println("Sorry you lost this round");
            }

            do {
                System.out.println("Do you want to play again? (Y/N): ");
                decision = scanner.next().toLowerCase();

                switch (decision) {
                    case "y" -> {
                        bet = 0;
                        bellCount = 0;
                        starCount = 0;
                        watermelonCount = 0;
                        cherryCount = 0;
                        lemonCount = 0;

                        System.out.println();
                    }
                    case "n" -> {
                        System.out.printf("GAME OVER! Your final balance is %d", balance);
                        play = false;
                    }
                    default -> {
                        System.out.println("Invalid Entry!");
                    }
                }
            } while (!decision.equals("y") && !decision.equals("n"));

        }

        scanner.close();

    }

    static void draw_stars(int number) {

        int amount = (number == 1) ? 30 : 8;
        for (int i = 0; i < amount; i++) {
            System.out.print("*");
        }
        System.out.println();

    }

}