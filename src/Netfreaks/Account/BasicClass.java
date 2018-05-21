package Netfreaks.Account;

public class BasicClass extends AbstractAccountClass implements Netfreaks.Account.Tags.Basic{

    public BasicClass(String email, String name, String password, String device) {
        super(email, name, password, device);
    }

    @Override
    public boolean isDeviceListFull() {
        return getNDevices() >= MAXIMUM_DEVICES;
    }

}
