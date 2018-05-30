package Comparators;

import Netfreaks.Product.Product;

import java.util.Comparator;

public class RatingComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        String o1StringRating = String.valueOf(o1.getAverageRating()).substring(0,3);
        String o2StringRating = String.valueOf(o2.getAverageRating()).substring(0,3);
        float o1Rating = Float.parseFloat(o1StringRating);
        float o2Rating = Float.parseFloat(o2StringRating);

        if(o1Rating == o2Rating)
            return o1.getTitle().compareTo(o2.getTitle());
        else
            return Float.compare(o2Rating,o1Rating);
    }
}
