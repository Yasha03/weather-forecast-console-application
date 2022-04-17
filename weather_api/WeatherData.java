package weather_api;

import src.App;
import user_interface.UserInteractorPrintException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherData {
    private String cityKey;

    private String weatherCondition;
    private boolean isDayTime;
    private double temperatureValue;

    public WeatherData(String cityKey) throws IOException, UserInteractorPrintException {
        this.cityKey = cityKey;
        setWeatherInfo();
    }

    private void setWeatherInfo() throws IOException {
        URL url = new URL("http://dataservice.accuweather.com/currentconditions/v1/" + cityKey + "?apikey=" + App.API_KEY);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)
        );
        parseWeatherJson(in.readLine());
    }

    private void parseWeatherJson(String json){
        Pattern pattern = Pattern.compile("\"WeatherText\":\"(.+?)\".+\"IsDayTime\":(true|false).+\"Metric\":\\{\"Value\":(.+?),");
        Matcher match = pattern.matcher(json);

        while(match.find()) {
            for (int i = 1; i <= match.groupCount(); i++) {
                switch (i){
                    case 1:
                        this.weatherCondition = match.group(i);
                        break;
                    case 2:
                        this.isDayTime = Boolean.parseBoolean(match.group(i));
                        break;
                    case 3:
                        this.temperatureValue = Double.parseDouble(match.group(i));
                }
            }
        }
    }

    public void printWeatherInfo() throws UserInteractorPrintException {
        App.terminal.print("Weather condition: " + this.weatherCondition + ".");
        App.terminal.print(this.isDayTime ? "It's daylight now." : "It's night time now.");
        App.terminal.print("Temperature: " + this.temperatureValue + "℃.");
    }


}
