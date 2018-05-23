package Netfreaks;

import Netfreaks.Account.Account;
import Netfreaks.Account.BasicClass;
import Netfreaks.Account.Profile.ProfileType;
import Netfreaks.Account.Tags.Basic;
import Netfreaks.Account.Tags.Premium;
import Netfreaks.Account.Tags.Standard;
import Netfreaks.Product.Product;

import java.util.AbstractCollection;
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
        Account account = accounts.get(currentAccount);
        accounts.remove(currentAccount);
        ProfileType type = ProfileType.valueOf(planName.toUpperCase());
        switch(type){
            case BASIC:
                Basic bAcc = (Basic) account;
                accounts.put(currentAccount,(Account)bAcc);
                break;
            case STANDARD:
                Standard sAcc = (Standard) account;
                accounts.put(currentAccount,(Account)sAcc);
                break;
            case PREMIUM:
                Premium pAcc = (Premium) account;
                accounts.put(currentAccount,(Account)pAcc);
        }
    }

    @Override
    public void profile(String profileName, boolean type, int ageRestriction) {
            accounts.get(currentAccount).addProfile(profileName,type,ageRestriction);
    }

    @Override
    public void select(String profileName) {
        accounts.get(currentAccount).changeProfileTo(profileName);
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

    private ProfileType getPlan(Account account){
        if(account == null)
            return null;
        if(account instanceof Basic)
            return ProfileType.BASIC;
        if(account instanceof Standard)
            return ProfileType.STANDARD;

        return ProfileType.BASIC;
    }
    @Override
    public ProfileType getActiveProfilePlan() {
        return getPlan(accounts.get(currentAccount));
    }

    @Override
    public boolean SameMembership(String membershipName) {
        return getPlan(accounts.get(currentAccount)).equals(ProfileType.valueOf(membershipName.toUpperCase()));
    }

    @Override
    public boolean isDowngradePossible(String membershipName) {
        int nDevices = accounts.get(currentAccount).getNDevices();
        if(ProfileType.valueOf(membershipName.toUpperCase()).equals(ProfileType.BASIC))
            return nDevices <= ProfileType.BASIC.getMaxNProfiles();
        else
            return nDevices <= ProfileType.PREMIUM.getMaxNProfiles();
    }

    @Override
    public boolean isItDowngrade(String membershipName) {
        ProfileType type = ProfileType.valueOf(membershipName.toUpperCase());
        if(type.equals(ProfileType.BASIC))
            return true;
        if(type.equals(ProfileType.PREMIUM))
            return false;
        return !getPlan(accounts.get(currentAccount)).equals(ProfileType.BASIC);
    }

    @Override
    public boolean isSameProfile(String profileName) {
        return accounts.get(currentAccount).hasProfile(profileName);
    }

    @Override
    public boolean profileNumberExceeded() {
        Account account = accounts.get(currentAccount);
        switch(getPlan(account)){
             case BASIC:
                 return account.getNProfiles() == ProfileType.BASIC.getMaxNProfiles();

             default:
                return account.getNProfiles() == ProfileType.PREMIUM.getMaxNProfiles();
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
