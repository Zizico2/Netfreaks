package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;
import Netfreaks.Product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static Netfreaks.Account.PlanType.BASIC;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 *
 * Representa um Conta.
 */
public class AccountClass implements Account {

    // Variaveis.
    private final SortedMap<String,Profile> profiles;
    private final String name;
    private String currentProfile;
    private final String password;
    private final List<String> devices;
    private String currentDevice;
    private PlanType type;

    // Construtor.
    public AccountClass(String name, String password, String device) {
        profiles = new TreeMap<>();
        this.name = name;
        this.password = password;
        devices = new ArrayList<>();
        devices.add(device);
        currentDevice = device;
        setPlanType(BASIC);
        currentProfile = "";
    }

    @Override
    public void watch(String productName){
       profiles.get(currentProfile).watch(productName);
    }

    @Override
    public void rate(Product product){
        profiles.get(currentProfile).rate(product);
    }

    @Override
    public void disconnect(){
        devices.remove(currentDevice);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void login(String device){
        currentDevice = device;
    }

    @Override
    public boolean needToRegisterDevice(String device) {
        return !devices.contains(device);
    }

    @Override
    public void registerDevice(String device) {
        devices.add(device);
    }

    @Override
    public void logout(){
        currentDevice = "";
        currentProfile = "";
    }

    @Override
    public String getCurrentProfile(){
        return currentProfile;
    }

    @Override
    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean isDeviceListFull(){
        return !(devices.size() < type.getMaxNDevices());
    }

    @Override
    public String getActiveDevice() {
        return currentDevice;
    }

    @Override
    public int getNDevices(){
        return devices.size();
    }

    @Override
    public List<String> getDevices() {
        return devices;
    }

    @Override
    public void addProfile(String profileName, int ageRestriction) {
        profiles.put(profileName,new ProfileClass(profileName, ageRestriction));
    }

    public void addProfile(String profileName) {
        profiles.put(profileName,new ProfileClass(profileName));
    }

    @Override
    public boolean hasProfile(String profileName) {
        return profiles.containsKey(profileName);
    }

    @Override
    public int getNProfiles() {
        return profiles.size();
    }


    @Override
    public void selectProfile(String profileName) {
        currentProfile = profileName;
    }

    @Override
    public void setPlanType(PlanType type){
       this.type = type;
    }

    @Override
    public PlanType getPlanType(){
        return type;
    }

    @Override
    public SortedMap<String, Profile> infoAccount(){
        return profiles;
    }

    @Override
    public int getCurrentProfileAge() {
        return profiles.get(currentProfile).getAge();
    }

    @Override
    public boolean isInHistory(String productName) {
        return profiles.get(currentProfile).isInHistory(productName);
    }
}
