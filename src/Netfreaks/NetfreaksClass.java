package Netfreaks;

import Netfreaks.Account.AccountClass;
import Netfreaks.Account.Account;
import Netfreaks.Account.PlanType;
import Netfreaks.Product.Product;
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
    public SortedMap<String, Product> upload(Product[] products) {
        for (Product product:products)
            this.products.put(product.getTitle(),product);

        return this.products;
    }


    @Override
    public void register(String name, String email, String password, String device) {
        accounts.put(email, new AccountClass(email, name, password, device));
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
        Product product = products.get(productName);
        product.rate(currentAccount,rate);
        accounts.get(currentAccount).rate(product);
    }

    @Override
    public String infoaccount() {
        return accounts.get(currentAccount).toString();
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
}
