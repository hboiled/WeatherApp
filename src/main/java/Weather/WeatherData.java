/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weather;

/**
 *
 * @author 61406
 */
public class WeatherData {

    private int time;

    // store hourly data for temp
    private int temp;
    // store hourly data for rainfall
    private int precip;

    public int getTime() {
        return time;
    }

    public int getTemp() {
        return temp;
    }

    public int getPrecip() {
        return precip;
    }

    public WeatherData(int time, int temp, int precip) {
        this.time = time;
        this.temp = temp;
        this.precip = precip;
    }

}
