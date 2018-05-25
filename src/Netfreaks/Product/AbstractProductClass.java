package Netfreaks.Product;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractProductClass implements Product {

    private String title;
    private int yearOfRelease;
    private int ageRestriction;
    private String genre;
    private String[] cast;
    private int nCast;

    Map<String, Integer> rates;

    AbstractProductClass(String title, int yearOfRelease, String ageRestriction, String genre, String[] cast, int nCast) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.ageRestriction = Integer.parseInt(ageRestriction.replace("+",""));
        this.genre = genre;
        this.cast = cast;
        this.nCast = nCast;
        rates = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public String getGenre() {
        return genre;
    }

    public String[] getCast(){
        return cast;
    }

    @Override
    public void rate(String profileName, int rate) {
       rates.put(profileName,rate);
    }

    @Override
    public int getRate(String profileName){
        return rates.get(profileName);
    }


}
