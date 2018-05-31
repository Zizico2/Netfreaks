package Netfreaks.Account.Profile;

import Netfreaks.Product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class ProfileClass implements Profile {

    // Variaveis
    private final String name;
    private final int age;
    private List<String> history;
    private List<Product> ratedProducts;

    // Construtor para Crianca.
    public ProfileClass(String name, int age) {
        this.age = age;
        this.name = name;
        history = new ArrayList<>(MAX_CAPACITY);
        ratedProducts =  new ArrayList<>();
    }

    // Construtor Normal.
    public ProfileClass(String name) {
        age = 18;
        this.name = name;
    }

    @Override
    public void watch(String productName){
        if(!(history.size() < 10))
            history = history.subList(1,MAX_CAPACITY);
        history.add(productName);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void rate(Product product) {
        ratedProducts.add(product);
    }


    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean isInHistory(String productName) {
        return history.contains(productName);
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public List<Product> getRatedProducst() {
        return ratedProducts;
    }
}
