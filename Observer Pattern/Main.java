import java.util.ArrayList;
import java.util.List;

interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}

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

interface WeatherObserver {
    void update(float temperature);
}

class CurrentConditionsDisplay implements WeatherObserver {
    @Override
    public void update(float temperature) {
        System.out.println("Current conditions: " + temperature + "F degrees");
    }
}

class StatisticsDisplay implements WeatherObserver {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
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
