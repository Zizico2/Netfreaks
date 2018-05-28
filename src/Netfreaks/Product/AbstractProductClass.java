package Netfreaks.Product;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractProductClass implements Product{

    private String title;
    private int yearOfRelease;
    private int PEGI;
    private String genre;
    private String[] cast;
    private String masterName;
    Map<String, Integer> rates;

    AbstractProductClass(String title, int yearOfRelease, String PEGI, String genre, String[] cast) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.PEGI = Integer.parseInt(PEGI.replace("+",""));
        this.genre = genre;
        masterName = cast[0];
        String[] temp = new String[cast.length-1];
        System.arraycopy(cast,1,temp,0,temp.length);
        this.cast = temp;
        rates = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public int getPEGI() {
        return PEGI;
    }

    public String getGenre() {
        return genre;
    }

    public String[] getCast(){
        return cast;
    }

    public String getMasterName(){
        return masterName;
    }

    @Override
    public void rate(String profileName, int rate) {
       rates.put(profileName,rate);
    }

    @Override
    public int getRate(String profileName){
        return rates.get(profileName);
    }

    @Override
    public boolean isRatedBy(String currentProfile) {
        return rates.containsKey(currentProfile);
    }
}
