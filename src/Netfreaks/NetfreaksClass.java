package Netfreaks;

import Netfreaks.Account.Account;
import Netfreaks.Account.BasicClass;
import Netfreaks.Product.Film;
import Netfreaks.Product.Product;
import Netfreaks.Product.Series;

import java.util.SortedMap;
import java.util.TreeMap;

public class NetfreaksClass implements Netfreaks {

    private SortedMap<String,Product> products;
    private SortedMap<String,Account> accounts;
    private String currentAccount;


    public NetfreaksClass(){
        products = new TreeMap<>();
        accounts = new TreeMap<>();
        currentAccount = "";
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
                for(int i = 0; i < 3 && i < cast.length; i++)
                    msg += cast[i] + separator;
            msg = msg.substring(0,msg.lastIndexOf(separator)) + "." + "\n";
        }
        return msg;
    }


    @Override
    public void register(String name, String email, String password, String device) {
        accounts.put(email, new BasicClass(email, name, password, device));
        login(email, device);


    }

    @Override
    public void login(String email, String device) {
        currentAccount = email;
        accounts.get(currentAccount).login(device);

    }

    @Override
    public void disconnect() {
        accounts.get(currentAccount).disconnect();
        logout();
    }

    @Override
    public void logout() {
        accounts.get(currentAccount).logout();
        currentAccount = "";

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

    @Override
    public boolean isAClientLoggedIn() {
        return !isClientLoggedIn("");
    }

    @Override
    public boolean isEmailUsed(String email) {
        return accounts.get(email) != null;
    }

    @Override
    public String getActiveProfile() {
        return accounts.get(currentAccount).getActiveProfile();
    }

    public String getActiveDevice(){
        return accounts.get(currentAccount).getActiveDevice();
    }

    @Override
    public boolean isClientLoggedIn(String email) {
        return currentAccount.equals(email);
    }

    @Override
    public boolean isPasswordRight(String email, String password) {
        return accounts.get(email).getPassword().equals(password);
    }

    @Override
    public boolean deviceNumberExceeded(String email, String device) {
        return accounts.get(email).isDeviceListFull();
    }

    @Override
    public boolean needToRegisterDevice(String email, String device) {
        return accounts.get(email).needToRegisterDevice(device);

    }

    @Override
    public void registerDevice(String email, String device) {
        accounts.get(email).registerDevice(device);
    }
}
