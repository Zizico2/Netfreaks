package Netfreaks.Account.Profile;

import Netfreaks.Product.Product;

import java.util.List;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public interface Profile {

    int MAX_CAPACITY = 10;

    void watch(String productName);

    void rate(Product product);

    int getAge();

    String getName();

    boolean isInHistory(String productName);

    List<String> getHistory();

    List<Product> getRatedProducst();
}
