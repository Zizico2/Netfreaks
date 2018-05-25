package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;
import static Netfreaks.Account.PlanType.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class AccountClass implements Account {

    private SortedMap<String,Profile> profiles;
    private String name;
    private String activeProfile;
    private String email;
    private String password;
    private List<String> devices;
    private String currentDevice;

    private int maxProfiles;
    private int maxDevices;

    public AccountClass(String email, String name, String password, String device) {
        profiles = new TreeMap<>();
        this.name = name;
        this.email = email;
        this.password = password;
        devices = new ArrayList<>();
        devices.add(device);
        currentDevice = device;
        setPlanType(BASIC);
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
    public boolean isDeviceListFull(){
        return !(devices.size() < maxDevices);
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
    public void selectProfile(String profileName) {
        activeProfile = profileName;
    }

    public void setPlanType(PlanType type){
        maxProfiles = type.getMaxNProfiles();
        maxDevices = type.getMaxNDevices();
    }

    public PlanType getPlanType(){
        for (PlanType type: PlanType.values())
            if(type.getMaxNDevices() == maxDevices)
                return type;
        return null;
    }
}
