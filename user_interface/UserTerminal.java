package user_interface;

import src.App;
import weather_api.City;
import weather_api.WeatherData;

import java.io.IOException;
import java.util.Scanner;

public class UserTerminal implements UserInteractor{
    private Scanner sc;

    public UserTerminal(){
        this.sc = new Scanner(System.in);
    }

    @Override
    public void print(String value) throws UserInteractorPrintException{
        System.out.println(">> " + value);
    }

    @Override
    public String readCommand() throws UserInteractorReadException, UserInteractorPrintException {
        System.out.print("<< ");
        String command = sc.nextLine();
        print("You entered the command: \u001B[31m" + command + "\u001B[0m");

        return command;
    }

    public int readInt() throws UserInteractorPrintException {
        System.out.print("<< ");
        int command = sc.nextInt();
        App.terminal.getScanner().nextLine();
        print("You entered the number: \u001B[31m" + command + "\u001B[0m");

        return command;
    }

    public String whileRead() throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        App.terminal.print("Enter the name of the city: ");
        String command = readCommand();
        if(command.equals("exit")){
            return "";
        }
        City city = new City(command);
        WeatherData weatherData = new WeatherData(city.getCityKey());
        weatherData.printWeatherInfo();

        Thread.sleep(2000);
        whileRead();


        return command;
    }

    public Scanner getScanner(){
        return this.sc;
    }
}
