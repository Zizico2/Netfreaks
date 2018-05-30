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
    private Map<String, Integer> rates;
    private double averageRating;

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
        averageRating = 0.00f;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getYearOfRelease() {
        return yearOfRelease;
    }

    @Override
    public int getPEGI() {
        return PEGI;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String[] getCast(){
        return cast;
    }

    @Override
    public String getMasterName(){
        return masterName;
    }

    @Override
    public void rate(String profileName, int rate) {
       rates.put(profileName,rate);
       Float acumulator = 0.0f;
       for(int rating : rates.values())
           acumulator += rating;
        float averageTemp = Math.round((acumulator/rates.size() * 10));
        averageRating = averageTemp / 10;
    }

    @Override
    public float getAverageRating(){
        return (float)averageRating;
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
