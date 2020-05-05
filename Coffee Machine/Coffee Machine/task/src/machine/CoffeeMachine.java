package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int dispoCups = 9;
    private static int money = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

            if (action.equals("buy")) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                String input = scanner.next();
                if (input.equals("back")) {
                    continue;
                } else {
                    try {
                        int coffeeKind = Integer.parseInt(input);
                        buy(coffeeKind);
                    } catch (Exception e) {
                        throw(e);
                    }
                }

            } else if (action.equals("fill")) {
                System.out.println("Write how many ml of water do you want to add:");
                int water = scanner.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                int milk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add:");
                int beans = scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                int dispoCups = scanner.nextInt();
                fill(water, milk, beans, dispoCups);

            } else if (action.equals("take")) {
                take();
            } else if (action.equals("remaining")) {
                getMachineState();
            } else if (action.equals("exit")) {
                break;
            }
        }

    }

    private static void buy(int coffeeKind) {

        switch(coffeeKind) {
            case 1:
                if(CoffeeMachine.water < 250) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (CoffeeMachine.milk < 16) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else if (CoffeeMachine.dispoCups < 1) {
                    System.out.println("Sorry, not enough cup!");
                    break;
                }

                CoffeeMachine.water -= 250;
                CoffeeMachine.beans -= 16;
                CoffeeMachine.money += 4;
                CoffeeMachine.dispoCups -= 1;
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case 2:
                if(CoffeeMachine.water < 350) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (CoffeeMachine.milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else if (CoffeeMachine.beans < 20) {
                    System.out.println("Sorry, not enough beans!");
                    break;
                } else if (CoffeeMachine.dispoCups < 1) {
                    System.out.println("Sorry, not enough cup!");
                    break;
                }
                CoffeeMachine.water -= 350;
                CoffeeMachine.milk -= 75;
                CoffeeMachine.beans -= 20;
                CoffeeMachine.money += 7;
                CoffeeMachine.dispoCups -= 1;
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case 3:
                if(CoffeeMachine.water < 200) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (CoffeeMachine.milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else if (CoffeeMachine.beans < 12) {
                    System.out.println("Sorry, not enough beans!");
                    break;
                } else if (CoffeeMachine.dispoCups < 1) {
                    System.out.println("Sorry, not enough cup!");
                    break;
                }
                CoffeeMachine.water -= 200;
                CoffeeMachine.milk -= 100;
                CoffeeMachine.beans -= 12;
                CoffeeMachine.money += 6;
                CoffeeMachine.dispoCups -= 1;
                System.out.println("I have enough resources, making you a coffee!");
            break;
        }
        System.out.println("");
    }

    private static void fill(int water, int milk, int beans, int dispoCups) {
        CoffeeMachine.water += water;
        CoffeeMachine.milk += milk;
        CoffeeMachine.beans += beans;
        CoffeeMachine.dispoCups += dispoCups;
    }

    private static void take() {
        System.out.println("I gave you $" + CoffeeMachine.money);
        CoffeeMachine.money = 0;
        System.out.println("");
    }

    private static void getMachineState() {

        System.out.println("");
        System.out.println("The coffee machine has:");
        System.out.println(CoffeeMachine.water + " of water");
        System.out.println(CoffeeMachine.milk + " of milk");
        System.out.println(CoffeeMachine.beans + " of coffee beans");
        System.out.println(CoffeeMachine.dispoCups + " of disposable cups");
        System.out.println("$" + CoffeeMachine.money + " of money");
        System.out.println("");

    }
}

