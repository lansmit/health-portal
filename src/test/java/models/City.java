package models;

public class City {
    private final String name;
    private final String url;

    public City(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static City saintPetersburg() {
        return new City("Санкт-Петербург", "https://spb.docdoc.ru/");
    }

}