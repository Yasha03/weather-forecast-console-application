package weather_api;

import src.App;
import user_interface.UserInteractorPrintException;
import user_interface.UserInteractorReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {

    private String cityKey;

    public City(String query) throws IOException, UserInteractorReadException, UserInteractorPrintException, InterruptedException {
        findCity(query.trim());
    }

    private void findCity(String query) throws IOException, UserInteractorReadException, UserInteractorPrintException, InterruptedException {
        URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + App.API_KEY + "&q=" + query);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)
        );

        String json = in.readLine();
        if(json.length() > 2){
            selectCity(json);
        }else{
            App.terminal.print("City not found");
            App.terminal.whileRead();
        }

    }

    private void selectCity(String json) throws UserInteractorPrintException {
        Pattern pattern = Pattern.compile("\"Country\":\\{.+?\"EnglishName\":\"(.+?)\"\\},\"AdministrativeArea\":\\{.+?\"EnglishName\":\"(.+?)\".+?\\}");
        Matcher match = pattern.matcher(json);
        printCityData(match);

        App.terminal.print("Select city number: ");
        int selectNumber = App.terminal.readInt();
        setCityKey(json, selectNumber);
    }

    private void setCityKey(String json, int number) throws UserInteractorPrintException {
        Pattern pattern = Pattern.compile("\"Key\":\"(\\w+)\"");
        Matcher match = pattern.matcher(json);
        int n = 1;
        while (match.find()){
            if(n == number){
                this.cityKey = match.group(1);
            }
            n++;
        }
    }

    public String getCityKey(){
        return this.cityKey;
    }

    private void printCityData(Matcher match) throws UserInteractorPrintException {
        int n = 1;
        while(match.find()) {
            App.terminal.print(n + ".");
            for (int i = 1; i <= match.groupCount(); i++) {
                App.terminal.print("\t" + match.group(i));
            }
            n++;
        }
    }

}
