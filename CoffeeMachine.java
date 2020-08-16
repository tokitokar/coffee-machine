package machine;
import java.util.Scanner;

import static machine.MachineAction.*;

enum MachineAction{
    BUY(0,0, 0, 0, 0),
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1),
    BACK(0,0, 0, 0, 0),
    FILL(0,0, 0, 0, 0),
    FILL_MILK(0,0, 0, 0, 0),
    FILL_WATER(0,0, 0, 0, 0),
    FILL_COFFEE_BEANS(0,0, 0, 0, 0),
    FILL_DISPOSABLE_CUPS(0,0, 0, 0, 0),
    TAKE(0,0, 0, 0, 0),
    REMAINING(0,0, 0, 0, 0),
    EXIT(0,0, 0, 0, 0);

    int water;
    int milk;
    int coffeeBeans;
    int cost;
    int disposableCups;

     MachineAction (int water, int milk, int coffeeBeans, int cost, int disposableCups) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
        this.disposableCups = disposableCups;
    }
    public void BuyCoffee () {
        if (this.water > CoffeeMachine.waterInMachine || this.milk > CoffeeMachine.milkInMachine
                || this.coffeeBeans > CoffeeMachine.coffeeBeansInMachine ||
                CoffeeMachine.disposableCupsInMachine == 0) {
            System.out.println("Sorry, I can't make cofee,  there are not enough resources in the coffee machine");
            System.out.println();
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println();
            CoffeeMachine.waterInMachine = CoffeeMachine.waterInMachine - this.water;
            CoffeeMachine.milkInMachine = CoffeeMachine.milkInMachine - this.milk;
            CoffeeMachine.coffeeBeansInMachine = CoffeeMachine.coffeeBeansInMachine - this.coffeeBeans;
            CoffeeMachine.moneyInMachine = CoffeeMachine.moneyInMachine + this.cost;
            CoffeeMachine.disposableCupsInMachine = CoffeeMachine.disposableCupsInMachine - this.disposableCups;
        }
    }
}
public class CoffeeMachine {
    static int waterInMachine = 400;
    static int milkInMachine = 540;
    static int coffeeBeansInMachine = 120;
    static int disposableCupsInMachine = 9;
    static int moneyInMachine = 550;
    static MachineAction machineState;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while (machineState != MachineAction.EXIT ) {
            CoffeeMachineDriver(scanner.nextLine());
        }
    }

    public static void CoffeeMachineDriver (String action) {
        switch (action) {
            case "buy":
                machineState = BUY;
                break;
            case "1":
                machineState = ESPRESSO;
                break;
            case "2":
                machineState = LATTE;
                break;
            case "3":
                machineState = CAPPUCCINO;
                break;
            case "back":
                machineState = BACK;
            case "fill":
                machineState = FILL;
                break;
            case "take":
                machineState = TAKE;
                break;
            case "remaining":
                machineState = REMAINING;
                break;
            case "exit":
                machineState = EXIT;
                break;
        }
        switch (machineState) {
            case BUY:
                System.out.println("> buy");
                System.out.println();
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                        "3 - cappuccino, back - to main menu:");
                break;
            case ESPRESSO:
                System.out.println("> 1");
                ESPRESSO.BuyCoffee();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case CAPPUCCINO:
                System.out.println("> 3");
                CAPPUCCINO.BuyCoffee();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case LATTE:
                System.out.println("> 2");
                LATTE.BuyCoffee();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case BACK:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case FILL:
                System.out.println("> fill");
                System.out.println();
                machineState = FILL_WATER;
                break;
            case FILL_WATER:
                System.out.println("Write how many ml of water do you want to add:");
                System.out.println("> " + action);
                waterInMachine = waterInMachine + Integer.parseInt(action);
                machineState = FILL_MILK;
                break;
            case FILL_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                System.out.println("> " + action);
                milkInMachine = milkInMachine + Integer.parseInt(action);
                machineState = FILL_COFFEE_BEANS;
                break;
            case FILL_COFFEE_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                System.out.println("> " + action);
                coffeeBeansInMachine = coffeeBeansInMachine + Integer.parseInt(action);
                machineState = FILL_DISPOSABLE_CUPS;
                break;
            case FILL_DISPOSABLE_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                System.out.println("> " + action);
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                disposableCupsInMachine = disposableCupsInMachine + Integer.parseInt(action);
                break;
            case TAKE:
                System.out.println("> take");
                System.out.println();
                System.out.println("I gave you $" + moneyInMachine);
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                moneyInMachine = 0;
                break;
            case REMAINING:
                System.out.println("> remaining");
                System.out.println();
                System.out.println("The coffee machine has:");
                System.out.println(waterInMachine + " of water");
                System.out.println(milkInMachine + " of milk");
                System.out.println(coffeeBeansInMachine + " of coffee beans");
                System.out.println(disposableCupsInMachine + " disposable cups");
                System.out.println(moneyInMachine + " of money");
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case EXIT:
                System.out.println("> exit");
                break;
        }
    }
}
