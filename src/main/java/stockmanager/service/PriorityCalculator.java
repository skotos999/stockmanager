package stockmanager.service;

import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PriorityCalculator {

    public PriorityCalculator() {

    }

    public void calculate(Product[] products, Integer[] approx) {

        int[] prioritiesAfterFirstCalculation = new int[products.length];
        for (int i = 0; i < products.length; i++) {
            prioritiesAfterFirstCalculation[i] = approx[i] * products[i].getSalePrice();
        }

        if (new HashSet<>(Arrays.asList(prioritiesAfterFirstCalculation)).size() != 3) {
            //return new Integer[]{1, 2, 3};
        }

        Integer[] priorities = new Integer[]{2, 2, 2};
        List list = Arrays.asList(prioritiesAfterFirstCalculation);
//        priorities[list.indexOf(Collections.max(list))] = 1;
//        priorities[list.indexOf(Collections.min(list))] = 3;

        products[list.indexOf(Collections.max(list))].setPriority(1);
        products[list.indexOf(Collections.min(list))].setPriority(3);
//        Cow.INSTANCE.setPriority(priorities[0]);
//        return priorities;
    }
}
