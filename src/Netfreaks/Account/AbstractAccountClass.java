package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;
import Netfreaks.Account.Profile.ProfileClass;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

abstract class AbstractAccountClass implements Account {
    private SortedMap<String,Profile> profiles;
    private String activeProfile;
    private String email;
    private String password;
    private List<String> devices;
    private String currentDevice;

    AbstractAccountClass(String email, String name, String password, String device) {
        profiles = new TreeMap<>();
        profiles.put(name, new ProfileClass(name));
        activeProfile = name;
        this.email = email;
        this.password = password;
        devices = new ArrayList<>();
        devices.add(device);
        currentDevice = device;
    }

    public void disconnect(){
        devices.remove(currentDevice);
    }

    public void login(String device){
        currentDevice = device;
    }

    public void logout(){
        currentDevice = "";
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

    int getNDevices(){
        return devices.size();
    }

}
