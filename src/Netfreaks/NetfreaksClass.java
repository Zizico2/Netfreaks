package Netfreaks;

import Netfreaks.Product.Film;
import Netfreaks.Product.Product;
import Netfreaks.Product.Series;

import java.util.SortedMap;
import java.util.TreeMap;

public class NetfreaksClass implements Netfreaks {

    private SortedMap<String,Product> products;

    public NetfreaksClass(){
        products = new TreeMap<>();
    }

    @Override
    public String upload(Product[] products) {

        for (Product product:products) {
            this.products.put(product.getTitle(),product);
        }

        String msg = "";
        String separator = "; ";
        for (Product product:this.products.values()) {
            String title = product.getTitle();
            String genre = product.getGenre();
            String[] cast = product.getCast();
            int ageRestriction = product.getAgeRestriction();
            int yearOfRelease = product.getYearOfRelease();
            msg += title + separator ;
            if(product instanceof Film) {
                Film film = (Film) product;
                msg += film.getDirector() + separator +  film.getDuration() + separator;
            }
            else{
                Series series = (Series) product;
                msg += series.getCreatorName() + separator +  series.getNSeasons() + separator + series.getNEpisodesPerSeason() + separator;
            }
            msg += ageRestriction + "+" + separator + yearOfRelease + separator + genre + separator;
            if(cast.length < 4)
            for (String name : cast)
                msg += name + separator;
            else
                for(int i = 0; i < 3; i++)
                    msg += cast[i] + separator;
            msg = msg.substring(0,msg.lastIndexOf(separator)) + ".";
            msg += "\n";
        }
        return msg;
    }

    @Override
    public void register(String[] accountInfo) {

    }

    @Override
    public void login(String email, String password, String device) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void membership(String planName) {

    }

    @Override
    public void profile(String profileName, boolean normal, int ageRestriction) {

    }

    @Override
    public void select(String profileName) {

    }

    @Override
    public void watch(String title) {

    }

    @Override
    public void rate(String title, int rating) {

    }

    @Override
    public String infoaccount() {
        return null;
    }

    @Override
    public String searchByGenre(String genreName) {
        return null;
    }

    @Override
    public String searchByName(String participantsName) {
        return null;
    }

    @Override
    public String searchByRate(int rate) {
        return null;
    }
}
