package Netfreaks.Account.Profile;

public class ProfileClass implements Profile {

    String name;
    int age;


    public ProfileClass(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public ProfileClass(String name) {
        age = 18;
        this.name = name;

    }
}
