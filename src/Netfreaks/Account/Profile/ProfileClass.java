package Netfreaks.Account.Profile;

import Netfreaks.Product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class ProfileClass implements Profile {

    private String name;
    private int age;
    private List<String> history;
    private List<Product> ratedProducts;
    public ProfileClass(String name, int age) {
        this.age = age;
        this.name = name;
        history = new ArrayList<>(MAX_CAPACITY);
        ratedProducts =  new ArrayList<>();
    }

    public ProfileClass(String name) {
        age = 18;
        this.name = name;
    }

    public void watch(String productName){
        if(!(history.size() < 10))
            history = history.subList(1,MAX_CAPACITY);
        history.add(productName);
    }

    public String getName(){
        return name;
    }

    @Override
    public void rate(Product product) {
        ratedProducts.add(product);
    }

    public String toString(){
        String msg = name + "\n";
        if(age != 18)
            msg = msg.replace("\n"," (" + age + ")\n");

        if(history.isEmpty())
            msg += "Empty list of recently seen shows.\n";
        else{
            List<String> tempHistory = new ArrayList<>(history);
            Collections.reverse(tempHistory);
            for(String productName: tempHistory)
                msg += productName + "; ";
            msg = msg.substring(0,msg.lastIndexOf("; ")) + ".\n";
            if(!ratedProducts.isEmpty()) {
                for (Product product : ratedProducts)
                    msg += product.getTitle() + " (" + product.getRate(name) + "); ";
                msg = msg.substring(0,msg.lastIndexOf("; ")) + ".\n";
            }
        }
        return msg;
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
