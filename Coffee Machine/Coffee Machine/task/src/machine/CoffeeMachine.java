package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        System.out.print("> ");
        int water = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        System.out.print("> ");
        int milk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        System.out.print("> ");
        int beans = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        System.out.print("> ");
        int coffee = scanner.nextInt();

        int maxWater = water / 200;
        int maxMilk = milk / 50;
        int maxBeans = beans / 15;

        int maxCoffee = Math.min(Math.min(maxWater, maxMilk), maxBeans);
        if ( coffee == maxCoffee) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (maxCoffee > coffee) {
            System.out.println("Yes, I can make that amount of coffee ( and even " + (maxCoffee - coffee) + " more than that)");
        } else {
            System.out.println("No, I can make only " + maxCoffee + " cup(s) of coffee");
        }

    }
}
