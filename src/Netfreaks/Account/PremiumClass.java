package Netfreaks.Account;

public class PremiumClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Premium {
    public PremiumClass(String email, String name, String password, String device) {
        super(email, name, password, device);
    }

    @Override
    public boolean isDeviceListFull() {
        return getNDevices() >= MAXIMUM_DEVICES;
    }
}
