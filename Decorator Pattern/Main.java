interface Coffee {
    String getDescription();
    double getCost();
}
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    @Override
    public double getCost() {
        return 5.0;
    }
}
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Milk";
    }
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.5;
    }
}
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Sugar";
    }
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
