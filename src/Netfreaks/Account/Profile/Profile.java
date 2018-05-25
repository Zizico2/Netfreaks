package Netfreaks.Account.Profile;

import Netfreaks.Product.Product;

public interface Profile {
    int MAX_CAPACITY = 10;

    void watch(String productName);

    void rate(Product product);

    String toString();
}
