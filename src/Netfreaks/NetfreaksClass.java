package Netfreaks;

import Comparators.ChronoComparator;
import Comparators.RatingComparator;
import Netfreaks.Account.AccountClass;
import Netfreaks.Account.Account;
import Netfreaks.Account.PlanType;
import Netfreaks.Product.Product;

import java.util.*;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 *
 * Representa a nossa aplicacao topo.
 */
public class NetfreaksClass implements Netfreaks {

    // Variaveis.
    private SortedMap<String,Product> productsByName;
    private SortedMap<String,Account> accounts;
    private String currentAccount;
    private Map<String,List<String>> productsByGenre;
    private Map<String,SortedSet<Product>> productsByDude;
    private SortedMap<Integer,SortedSet<Product>> productsByRate;

    // Construtor.
    public NetfreaksClass(){
        productsByName = new TreeMap<>();
        accounts = new TreeMap<>();
        productsByGenre = new HashMap<>();
        productsByDude = new HashMap<>();
        productsByRate = new TreeMap<>();
        currentAccount = "";
    }

    @Override
    public SortedMap<String, Product> upload(Product[] products) {
        SortedSet<Product> unratedProducts = new TreeSet<>(new RatingComparator());
        for (Product product:products) {
            String title = product.getTitle();
            String genre = product.getGenre();
            productsByName.put(title, product);
            if(!productsByGenre.containsKey(genre))
                productsByGenre.put(product.getGenre(),new ArrayList<>());
            productsByGenre.get(genre).add(title);

            String masterName = product.getMasterName();
            if(!productsByDude.containsKey(masterName))
                productsByDude.put(masterName, new TreeSet<>(new ChronoComparator()));
            productsByDude.get(masterName).add(product);

            for(String name: product.getCast()) {
                if (!productsByDude.containsKey(name))
                    productsByDude.put(name, new TreeSet<>(new ChronoComparator()));
                productsByDude.get(name).add(product);
            }
            unratedProducts.add(product);
        }
        productsByRate.put(0,unratedProducts);
        return productsByName;
    }


    @Override
    public void register(String name, String email, String password, String device) {
        accounts.put(email, new AccountClass(name, password, device));
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
        accounts.get(currentAccount).setPlanType(PlanType.valueOf(planName.toUpperCase()));
    }

    @Override
    public void profile(String profileName, boolean type, int ageRestriction) {
            accounts.get(currentAccount).addProfile(profileName,type,ageRestriction);
    }

    @Override
    public void select(String profileName) {
        accounts.get(currentAccount).selectProfile(profileName);
    }

    @Override
    public void watch(String productName) {
        accounts.get(currentAccount).watch(productName);
    }

    @Override
    public void rate(String productName, int rate) {
        Product product = productsByName.get(productName);
        int oldRating = (int)Math.floor(product.getAverageRating());
        product.rate(accounts.get(currentAccount).getCurrentProfile(),rate);
        accounts.get(currentAccount).rate(product);
        int newRating = (int) Math.floor(product.getAverageRating());

        if(newRating !=  oldRating){
            SortedSet<Product> Set = productsByRate.get(oldRating);
            Set.remove(product);
            Set = productsByRate.get(newRating);
            if(Set == null)
                Set = new TreeSet<>(new RatingComparator());
            Set.add(product);
            productsByRate.remove(newRating);
            productsByRate.put(newRating,Set);
        }
    }

    @Override
    public Account infoaccount() {
        return accounts.get(currentAccount);
    }

    @Override
    public SortedMap<String, Product> searchByGenre(String genreName) {
        SortedMap<String,Product> result = new TreeMap<>();
        for(String title: productsByGenre.get(genreName))
            result.put(title,productsByName.get(title));
        return result;
    }

    @Override
    public SortedSet<Product> searchByName(String participantsName) {
        return productsByDude.get(participantsName);

    }

    @Override
    public List<SortedSet<Product>> searchByRate(int rate) {
        List<SortedSet<Product>> listOfShowsByRate = new ArrayList<>();
        int PEGI = accounts.get(currentAccount).getCurrentProfileAge();
        for(int i = MAX_RATE; i >= rate; i--) {
            SortedSet<Product> set = new TreeSet<>(new RatingComparator());
            SortedSet<Product> originalSet  = productsByRate.get(i);
            if(originalSet != null) {
                for (Product product : productsByRate.get(i))
                    if (PEGI >= product.getPEGI())
                        set.add(product);
                listOfShowsByRate.add(set);
            }
        }
        return listOfShowsByRate;
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
        return accounts.get(currentAccount).getCurrentProfile();
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
        return accounts.get(email).isCorrectPassword(password);
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

    @Override
    public PlanType getActiveProfilePlan() {
        return accounts.get(currentAccount).getPlanType();
    }

    @Override
    public boolean SameMembership(String membershipName) {
        return accounts.get(currentAccount).getPlanType().equals(PlanType.valueOf(membershipName.toUpperCase()));
    }

    @Override
    public boolean isDowngradePossible(String membershipName) {

        PlanType type = PlanType.valueOf(membershipName.toUpperCase());
        Account account = accounts.get(currentAccount);

        return account.getNDevices() <= type.getMaxNDevices() && account.getNProfiles() <= type.getMaxNProfiles();

    }

    @Override
    public boolean isItDowngrade(String membershipName) {
        PlanType type = PlanType.valueOf(membershipName.toUpperCase());
        if(type.equals(PlanType.BASIC))
            return true;
        if(type.equals(PlanType.PREMIUM))
            return false;
        return !accounts.get(currentAccount).getPlanType().equals(PlanType.BASIC);
    }

    @Override
    public boolean isSameProfile(String profileName) {
        return accounts.get(currentAccount).hasProfile(profileName);
    }

    @Override
    public boolean profileNumberExceeded() {
        Account account = accounts.get(currentAccount);
        switch(account.getPlanType()){
             case BASIC:
                 return account.getNProfiles() == PlanType.BASIC.getMaxNProfiles();

             default:
                return account.getNProfiles() == PlanType.PREMIUM.getMaxNProfiles();
         }
    }

    @Override
    public boolean hasProfile(String profile) {
        return accounts.get(currentAccount).hasProfile(profile);
    }

    @Override
    public String getActiveAccountName() {
        return accounts.get(currentAccount).getName();
    }

    @Override
    public boolean isThereProfileSelected() {
        return !accounts.get(currentAccount).getCurrentProfile().equals("");
    }

    @Override
    public boolean isThereAProductNamed(String productName) {
        return productsByName.containsKey(productName);
    }

    @Override
    public boolean isPEGICompatible(String productName) {
        return accounts.get(currentAccount).getCurrentProfileAge() >= productsByName.get(productName).getPEGI();
    }

    @Override
    public boolean isInRecentHistory(String productName) {
       return accounts.get(currentAccount).isInHistory(productName);
    }

    @Override
    public boolean isProductRated(String productName) {
        return productsByName.get(productName).isRatedBy(accounts.get(currentAccount).getCurrentProfile());
    }

    @Override
    public boolean hasGenre(String genre) {
        return productsByGenre.containsKey(genre);
    }

    @Override
    public boolean hasDude(String name) {
        return productsByDude.containsKey(name);
    }

    @Override
    public boolean hasShowsWithRateHigherThan(int rate) {
        return !searchByRate(rate).isEmpty();
    }


}
