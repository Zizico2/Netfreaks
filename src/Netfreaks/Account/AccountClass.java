package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;
import Netfreaks.Product.Product;

import static Netfreaks.Account.PlanType.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class AccountClass implements Account {

    private SortedMap<String,Profile> profiles;
    private String name;
    private String currentProfile;
    private String password;
    private List<String> devices;
    private String currentDevice;
    private PlanType type;

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

    public void watch(String productName){
       profiles.get(currentProfile).watch(productName);
    }

    @Override
    public void rate(Product product){
        profiles.get(currentProfile).rate(product);
    }

    public void disconnect(){
        devices.remove(currentDevice);
    }

    public String getName(){
        return name;
    }

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

    public void logout(){
        currentDevice = "";
        currentProfile = "";
    }

    public String getCurrentProfile(){
        return currentProfile;
    }

    @Override
    public String getPassword() {
        return password;
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
    public void addProfile(String profileName, boolean type, int ageRestriction) {
        Profile profile;
        if(type == NORMAL) {
            profile = new ProfileClass(profileName);
            int age  = profile.getAge();
            assert age != ageRestriction;
        }
        else {
            profile = new ProfileClass(profileName, ageRestriction);
        }
        profiles.put(profileName,profile);
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

    public void setPlanType(PlanType type){
       this.type = type;
    }

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
