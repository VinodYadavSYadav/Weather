package com.proficiency.myapplication.model;

public class Wheatherbean {

    public   String city_Id;
    public   String city_name;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public   String humidity;

    public Wheatherbean(String city_Id, String city_name, String wheatherMain, String temp, String temp_min_max,String humidity) {
        this.city_Id = city_Id;
        this.city_name = city_name;
        this.wheatherMain = wheatherMain;
        this.temp = temp;
        this.temp_min_max = temp_min_max;
        this.humidity = humidity;
    }

    public String getCity_Id() {
        return city_Id;
    }

    public void setCity_Id(String city_Id) {
        this.city_Id = city_Id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getWheatherMain() {
        return wheatherMain;
    }

    public void setWheatherMain(String wheatherMain) {
        this.wheatherMain = wheatherMain;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min_max() {
        return temp_min_max;
    }

    public void setTemp_min_max(String temp_min_max) {
        this.temp_min_max = temp_min_max;
    }

    public  String wheatherMain;
    public  String temp ;
    public  String temp_min_max ;
}
