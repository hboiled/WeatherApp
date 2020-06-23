
import Weather.WeatherData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 61406
 */
class Scraper {
    public List<WeatherData> scrapeWeather() {
        // placeholder
        List<WeatherData> weatherData = new ArrayList<>();
        
        try {
            // must include user agent for certain sites
            Document doc = Jsoup.connect("https://www.accuweather.com/en/au/perth/26797/hourly-weather-forecast/26797").
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0").get();
            System.out.println(doc.title());

            // get all table rows and append to elements
            Elements rows = doc.getElementsByClass("hourly-forecast-card-header hourly-forecast-header");

            for (Element row : rows) {   
                int time = convertTime24(row.getElementsByClass("date").get(0).child(0).text());
                //String date = row.getElementsByClass("date").get(0).child(1).text();
                
                int temp = getRawNumber(row.getElementsByClass("temp metric").text());
                
                int rainfall = getRawNumber(row.getElementsByClass("precip").text());
                //System.out.println(rainfall);
                
                weatherData.add(new WeatherData(time, temp, rainfall));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }     
        
        return weatherData;
    }
    
    private static int convertTime24(String time) {
        String rawNumbers = time.replaceAll("\\D+", "");
        int time24 = Integer.valueOf(rawNumbers);        
        
        if (time24 != 12 && time.contains("PM")) {
            time24 += 12;
        }

        return time24;
    }
    
    private static int getRawNumber(String str) {
        return Integer.valueOf(str.replaceAll("\\D+", ""));
    }
}
