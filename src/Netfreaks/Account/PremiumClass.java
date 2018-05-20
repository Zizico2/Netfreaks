package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;

public class PremiumClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Premium {


    PremiumClass(String name, String email, String password) {
        super(name, email, password);
        nDevicesAllowed = 4;
        nProfilesAllowed = 5;
        profiles = new Profile[nProfilesAllowed];
    }
}
