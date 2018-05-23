package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

abstract class AbstractAccountClass implements Account {

    private SortedMap<String,Profile> profiles;
    private String name;
    private String activeProfile;
    private String email;
    private String password;
    private List<String> devices;
    private String currentDevice;

    AbstractAccountClass(String email, String name, String password, String device) {
        profiles = new TreeMap<>();
        profiles.put(name, new ProfileClass(name));
        this.name = name;
        this.email = email;
        this.password = password;
        devices = new ArrayList<>();
        devices.add(device);
        currentDevice = device;
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
        activeProfile = "";
    }

    public String getActiveProfile(){
        return activeProfile;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public abstract boolean isDeviceListFull();

    @Override
    public String getActiveDevice() {
        return currentDevice;
    }

    @Override
    public int getNDevices(){
        return devices.size();
    }

    @Override
    public void addProfile(String profileName, boolean type, int ageRestriction) {
        Profile profile;
        if(type == NORMAL)
            profile = new ProfileClass(profileName);
        else
            profile = new ProfileClass(profileName,ageRestriction);

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
    public void changeProfileTo(String profileName) {
        activeProfile = profileName;
    }

}
