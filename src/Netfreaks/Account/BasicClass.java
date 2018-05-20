package Netfreaks.Account;

import Netfreaks.Account.Profile.Profile;

public class BasicClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Basic{


    BasicClass(String name, String email, String password) {
        super(name, email, password);
        nDevicesAllowed = 1;
        nProfilesAllowed = 2;
        profiles = new Profile[nProfilesAllowed];
    }
}
