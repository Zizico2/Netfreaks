package Comparators;

import Netfreaks.Product.Product;

import java.util.Comparator;

public class ChronoComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getYearOfRelease() == o2.getYearOfRelease())
            return o1.getTitle().compareTo(o2.getTitle());
        else
            return Integer.compare(o1.getYearOfRelease(),o2.getYearOfRelease());
    }
}
