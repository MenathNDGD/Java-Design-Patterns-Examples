# Java Design Patterns Examples 🚀

Welcome to the **Java Design Patterns Examples** repository! This project demonstrates key design patterns in Java through real-world examples. It is ideal for learning Object-Oriented Programming (OOP) design principles.

## Table of Contents 📚

- [Patterns Implemented](#patterns-implemented)
- [Singleton Pattern](#singleton-pattern)
- [Decorator Pattern](#decorator-pattern)
- [Observer Pattern](#observer-pattern)
- [Code Examples and Insights](#code-examples-and-insights)
- [How to Run](#how-to-run)
- [Contributing](#contributing)
- [License](#license)

## <a name="patterns-implemented">Patterns Implemented 🛠️</a>

1. **Singleton Pattern**: Ensures a class has only one instance and provides a global point of access to it.
2. **Decorator Pattern**: Allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class.
3. **Observer Pattern**: Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## <a name="singleton-pattern">Singleton Pattern 🔒</a>

The Singleton pattern is demonstrated through a database connection manager.

- **Class**: [`DatabaseConnection`](Singleton%20Pattern/DatabaseConnection.java)
- **Example Usage**:
  ```java
  DatabaseConnection db1 = DatabaseConnection.getInstance();
  DatabaseConnection db2 = DatabaseConnection.getInstance();
  db1.query("SELECT * FROM users");
  db2.query("INSERT INTO users VALUES (1, 'John')");
  ```

## <a name="decorator-pattern">Decorator Pattern 🎨</a>

The Decorator pattern is demonstrated through a customizable coffee shop.

- **Classes**: [`SimpleCoffee`](Decorator%20Pattern/SimpleCoffee.class), [`MilkDecorator`](Decorator%20Pattern/MilkDecorator.class), [`SugarDecorator`](Decorator%20Pattern/SugarDecorator.class)
- **Example Usage**:
  ```java
  Coffee coffee = new SimpleCoffee();
  coffee = new MilkDecorator(coffee);
  coffee = new SugarDecorator(coffee);
  System.out.println(coffee.getDescription() + " $" + coffee.getCost());
  ```

## <a name="observer-pattern">Observer Pattern 👀</a>

The Observer pattern is demonstrated through a weather monitoring system.

- **Classes**: [`WeatherStation`](Observer%20Pattern/WeatherStation.class), [`CurrentConditionsDisplay`](Observer%20Pattern/CurrentConditionsDisplay.class), [`StatisticsDisplay`](Observer%20Pattern/StatisticsDisplay.class)
- **Example Usage**:
  ```java
  WeatherStation weatherStation = new WeatherStation();
  weatherStation.registerObserver(new CurrentConditionsDisplay());
  weatherStation.registerObserver(new StatisticsDisplay());
  weatherStation.setTemperature(80);
  ```

## <a name="code-examples-and-insights">Code Examples and Insights 🧠</a>

<details>
<summary><code>Singleton Pattern Overview 🔒🌐</code></summary>

### Introduction 🌟🧩

The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance. This pattern is useful when exactly one object is needed to coordinate actions across the system.

### Scenario: Database Connection 💻🔗

In many applications, you need to ensure that only one instance of a database connection is created to prevent resource conflicts and manage resources efficiently. The Singleton pattern ensures that there is only one instance of a class and provides a global point of access to it.

### Code Explanation 📜💻

```java
private static DatabaseConnection instance;
```

- This variable holds the single instance of the class.
- Being `static` means it belongs to the class, not to any specific object of the class.

```java
private DatabaseConnection() {
    System.out.println("Database Connection established");
}
```

- The constructor is private, preventing other classes from instantiating the `DatabaseConnection` class directly.
- It ensures that the only way to get an instance of this class is through the `getInstance` method.

```java
public static DatabaseConnection getInstance() {
    if (instance == null) {
        instance = new DatabaseConnection();
    }
    return instance;
}
```

- This method returns the single instance of the class.
- If the `instance` is `null` (meaning it hasn't been created yet), it creates a new instance.
- Subsequent calls to `getInstance` return the already created instance, ensuring there's only one instance.

```java
public void query(String sql) {
    System.out.println("Executing query: " + sql);
}
```

- This method simulates executing a database query.
- It prints out the SQL query string provided as an argument.

```java
DatabaseConnection db1 = DatabaseConnection.getInstance();
DatabaseConnection db2 = DatabaseConnection.getInstance();
```

- Both `db1` and `db2` are references to the same instance of `DatabaseConnection`.
- The `getInstance` method ensures that the same instance is returned both times.

```java
db1.query("SELECT * FROM users");
db2.query("INSERT INTO users VALUES (1, 'John')");
```

- The `query` method is called on both `db1` and `db2`.
- Since `db1` and `db2` refer to the same instance, these calls operate on the same object.

```java
System.out.println(db1 == db2);
```

- This prints `true` because `db1` and `db2` are references to the same instance.

### Output Explanation 🖥️🔍

When running the `Main` class, the final output will be:

```java
Database Connection established
Executing query: SELECT * FROM users
Executing query: INSERT INTO users VALUES (1, 'John')
true
```

- **Database Connection established** is printed once when the instance is first created by `DatabaseConnection.getInstance()`.
- **Executing query: SELECT \* FROM users** is printed after called the `db1.query("SELECT * FROM users")`.
- **Executing query: INSERT INTO users VALUES (1, 'John')** is printed when `db2.query("INSERT INTO users VALUES (1, 'John')")` is called.
- **true** is printed when `System.out.println(db1 == db2)` is executed, confirming that both references point to the same instance.

</details>

<details>
<summary><code>Decorator Pattern Overview 🎨✨</code></summary>

### Introduction 🌟🧩

The Decorator Pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. This pattern is typically used to extend the functionalities of classes in a flexible and reusable way.

### Scenario: Coffee Shop with Different Add-Ons ☕🍰

In a coffee shop, you can order a basic coffee and add various add-ons like milk or sugar. The Decorator pattern allows you to add behavior to objects dynamically.

### Code Explanation 📜💻

**_`Coffee Interface`_** defines the structure that all coffee types (both base and decorated) must follow.

```java
interface Coffee {
    String getDescription();
    double getCost();
}
```

- The `getDescription()` method returns a description of the coffee.
- It implemented by both base and decorator classes.
- The `getCost()` method returns the cost of the coffee.
- It also implemented by both base and decorator classes.

**_`SimpleCoffee Class`_** represents a basic coffee without any decorations.

```java
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
```

- The `getDescription()` method returns **Simple Coffee**.
- The `getCost()` Method returns a base cost of **5.0**.

**_`CoffeeDecorator Abstract Class`_** implements the `Coffee` interface and serves as the base class for all coffee decorators.

```java
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
```

- The `decoratedCoffee` field holds the reference to the coffee object being decorated.
- The constructor initializes the `decoratedCoffee` with the given `coffee`.
- The `getDescription()` method returns the description of the decorated coffee.
- The `getCost()` method returns the cost of the decorated coffee.

**_`MilkDecorator Class`_** adds milk to the coffee, extending the `CoffeeDecorator`.

```java
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
```

- The constructor passes the coffee to be decorated to the `CoffeeDecorator` constructor.
- The `getDescription()` method appends **+ Milk** to the existing description.
- The `getCost()` method adds **1.5** to the existing cost.

**_`SugarDecorator Class`_** adds sugar to the coffee, extending the `CoffeeDecorator`.

```java
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
```

- The constructor passes the coffee to be decorated to the `CoffeeDecorator` constructor.
- The `getDescription()` method appends **+ Sugar** to the existing description.
- The `getCost()` method adds **0.5** to the existing cost.

**_`Main Class`_** demonstrates the use of the decorators to add features to the base coffee.

```java
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
```

**`Creating a Simple Coffee:`**

```java
   Coffee coffee = new SimpleCoffee();
   System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- This creates a `SimpleCoffee` instance.
- It prints the description and cost: **Simple Coffee $5.0**.

**`Adding Milk to Coffee:`**

```java
   coffee = new MilkDecorator(coffee);
   System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- This decorates the `coffee` with `MilkDecorator`.
- It prints the new description and cost: **Simple Coffee + Milk $6.5**.

**`Adding Sugar to Coffee:`**

```java
coffee = new SugarDecorator(coffee);
System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- This further decorates the `coffee` with `SugarDecorator`.
- It prints the new description and cost: **Simple Coffee + Milk + Sugar $7.0**.

### Output Explanation 🖥️🔍

The Final Output generated as follows:

```java
Simple Coffee $5.0
Simple Coffee + Milk $6.5
Simple Coffee + Milk + Sugar $7.0
```

When the `Main` class is executed, it follows these steps to generate the output:

**`1. Creating a Simple Coffee:`**

```java
Coffee coffee = new SimpleCoffee();
System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- A `SimpleCoffee` instance is created.
- The `getDescription()` method of `SimpleCoffee` returns **Simple Coffee**.
- The `getCost()` method of Simpl`eCoffee returns **5.0**.
- The output is: **Simple Coffee $5.0**.

**`2. Adding Milk to Coffee:`**

```java
coffee = new MilkDecorator(coffee);
System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- The `SimpleCoffee` instance is decorated with `MilkDecorator`.
- The `getDescription()` method of `MilkDecorator` calls the `getDescription()` method of the decorated coffee (which is `SimpleCoffee`) and appends **+ Milk**.
- The` decoratedCoffee.getDescription()` returns **Simple Coffee**.
- Then the final description is **Simple Coffee + Milk**.
- The `getCost()` method of `MilkDecorator` calls the `getCost()` method of the decorated coffee (which is `SimpleCoffee`) and adds **1.5**.
- In this case, the `decoratedCoffee.getCost()` returns **5.0**.
- After that, the final cost is **5.0 + 1.5 = 6.5**.
- Then the output is: **Simple Coffee + Milk $6.5**.

**`3. Adding Sugar to Coffee:`**

```java
coffee = new SugarDecorator(coffee);
System.out.println(coffee.getDescription() + " $" + coffee.getCost());
```

- The `MilkDecorator` instance (which already decorates `SimpleCoffee`) is further decorated with `SugarDecorator`.
- The `getDescription()` method of `SugarDecorator` calls the `getDescription()` method of the decorated coffee (which is `MilkDecorator`) and appends **+ Sugar**.
- In here the `decoratedCoffee.getDescription()` (which is `MilkDecorator.getDescription()`) returns **Simple Coffee + Milk**.
- So the final description is **Simple Coffee + Milk + Sugar**.
- After that, the `getCost()` method of `SugarDecorator` calls the `getCost()` method of the decorated coffee (which is `MilkDecorator`) and adds **0.5**.
- In that case, the `decoratedCoffee.getCost()` (which is `MilkDecorator.getCost()`) returns **6.5**.
- So the, final cost is **6.5 + 0.5 = 7.0**.
- Finally, the output is: **Simple Coffee + Milk + Sugar $7.0**.

</details>

<details>
<summary><code>Observer Pattern Overview 👀📡</code></summary>

### Introduction 🌟🧩

The Observer pattern allows you to define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

### Scenario: A Weather Station that Monitors Temperature 🌤️🌡️

Imagine a weather station that monitors temperature and updates multiple displays whenever the temperature changes.

### Code Explanation 📜💻

**_`WeatherSubject Interface`_** defines the methods that any subject (in this case, a weather station) must implement to allow observers to register, unregister, and be notified of changes.

```java
interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}
```

- The `registerObserver()` method adds an observer to the list of observers.
- The `removeObserver()` method removes an observer from the list of observers.
- The `notifyObservers()` method notifies all registered observers of a change.

**_`WeatherStation Class`_** implements the `WeatherSubject` interface and maintains a list of observers. It also holds the temperature data and notifies observers when the temperature changes.

```java
class WeatherStation implements WeatherSubject {
    private List<WeatherObserver> observers;
    private float temperature;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}
```

- The `observers` field is a list to keep track of registered observers.
- The `temperature` field shows the current temperature.
- The `registerObserver()` method adds an `observer` to the list.
- The `removeObserver()` method removes an `observer` from the list.
- The `notifyObservers()` method calls the `update()` method on each registered `observer`, passing the current `temperature`.
- The `setTemperature()` method sets the temp`erature and calls `notifyObservers()` method to update all observers.

**_`WeatherObserver Interface`_** defines the `update()` method that observers must implement to get updates from the subject.

```java
interface WeatherObserver {
    void update(float temperature);
}
```

- The `update()` method takes the new `temperature` as an argument and updates the observer.

**_`CurrentConditionsDisplay Class`_** implements the `WeatherObserver` interface and displays the current `temperature`.

```java
class CurrentConditionsDisplay implements WeatherObserver {
    @Override
    public void update(float temperature) {
        System.out.println("Current conditions: " + temperature + "F degrees");
    }
}
```

- The `update()` method prints the current `temperature` to the console.

**_`StatisticsDisplay Class`_** implements the `WeatherObserver` interface and maintains statistics about the `temperature` (_*average, maximum, and minimum*_).

```java
class StatisticsDisplay implements WeatherObserver {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;

    @Override
    public void update(float temperature) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
            + "/" + maxTemp + "/" + minTemp);
    }
}
```

- The following is how the fields are shown in this instance:
  - `maxTemp`: Stores the maximum recorded temperature.
  - `minTemp`: Stores the minimum recorded temperature.
  - `tempSum`: Stores the sum of all recorded temperatures.
  - `numReadings`: Counts the number of temperature readings.
- The `update()` method updates the statistics with the new `temperature` and calls display.
- The `display()` method prints the _*average, maximum, and minimum*_ temperatures to the console.

**_`Main Class`_** demonstrates the Observer Pattern by creating a `WeatherStation`, registering observers, and changing the temperature.

```java
public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();

        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);

        weatherStation.setTemperature(80);
        weatherStation.setTemperature(82);
        weatherStation.setTemperature(78);
    }
}
```

- Creating the WeatherStation:

  - This creates an instance of `WeatherStation`.

    ```java
    WeatherStation weatherStation = new WeatherStation();
    ```

- Creating the Observers:

  - This creates instances of `CurrentConditionsDisplay` and `StatisticsDisplay`.

    ```java
    CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
    StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
    ```

- Registering the Observers:

  - This registers the observers with the `WeatherStation`.

    ```java
    weatherStation.registerObserver(currentDisplay);
    weatherStation.registerObserver(statisticsDisplay);
    ```

- Setting the Temperature:

  - This changes the temperature, which triggers the `notifyObservers()` method to update all registered observers.

    ```java
    weatherStation.setTemperature(80);
    weatherStation.setTemperature(82);
    weatherStation.setTemperature(78);
    ```

### Output Explanation 🖥️🔍

The Final output is generated as follows:

```java
Current conditions: 80.0F degrees
Avg/Max/Min temperature = 80.0/80.0/80.0
Current conditions: 82.0F degrees
Avg/Max/Min temperature = 81.0/82.0/80.0
Current conditions: 78.0F degrees
Avg/Max/Min temperature = 80.0/82.0/78.0
```

When the `Main` class is executed, it follows these steps to generate the output:

**`1. Setting the temperature to 80:`**

- The `CurrentConditionsDisplay` prints: **Current conditions: 80.0F degrees**.
- The `StatisticsDisplay` updates its statistics as:
  - Average: _*801=80.0\frac{80}{1} = 80.0180=80.0*_
  - Maximum: _*80.0*_
  - Minimum: _*80.0*_
  - Prints: **Avg/Max/Min temperature = 80.0/80.0/80.0**

**`2. Setting the temperature to 82:`**

- The `CurrentConditionsDisplay` prints: C**urrent conditions: 82.0F degrees**.
- The `StatisticsDisplay` updates its statistics as:
  - Average: _*80+822=81.0\frac{80 + 82}{2} = 81.0280+82=81.0*_
  - Maximum: _*82.0*_
  - Minimum: _*80.0*_
  - Prints: **Avg/Max/Min temperature = 81.0/82.0/80.0**

**`3. Setting the temperature to 78:`**

- The `CurrentConditionsDisplay` prints: **Current conditions: 78.0F degrees**.
- The `StatisticsDisplay` updates its statistics as:
  - Average: _*80+82+783≈80.0\frac{80 + 82 + 78}{3} \approx 80.0380+82+78≈80.0*_
  - Maximum: _*82.0*_
  - Minimum: _*78.0*_
  - Prints: **Avg/Max/Min temperature = 80.0/82.0/78.0**

</details>

## <a name="how-to-run">How to Run 🏃‍♂️</a>

1. Clone the repository:
   ```sh
   git clone https://github.com/MenathNDGD/Java-Design-Patterns-Examples.git
   ```
2. Navigate to the desired pattern directory:
   ```sh
   cd "Singleton Pattern"  # or "Decorator Pattern", "Observer Pattern"
   ```
3. Compile and run the `Main.java` file:
   ```sh
   javac Main.java
   java Main
   ```

## <a name="contributing">Contributing 🤝</a>

Contributions are welcome! Please fork this repository and submit a pull request for any enhancements or bug fixes.

## <a name="license">License 📄</a>

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
