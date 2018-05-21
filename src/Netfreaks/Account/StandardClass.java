package Netfreaks.Account;


public class StandardClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Standard {
    public StandardClass(String email, String name, String password, String device) {
        super(email, name, password, device);
    }

    @Override
    public boolean isDeviceListFull() {
        return getNDevices() > MAXIMUM_DEVICES;
    }

}
