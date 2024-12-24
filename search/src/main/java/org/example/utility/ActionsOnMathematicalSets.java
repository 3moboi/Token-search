package org.example.utility;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;

public class ActionsOnMathematicalSets {


    public static ArrayList<Integer> logicalAnd(ArrayList<Integer> a, ArrayList<Integer> b) {
        HashSet<Integer> set = new HashSet<>(b); // Создаем HashSet из списка b
        ArrayList<Integer> result = new ArrayList<>();

        // Проходим по списку a и проверяем наличие каждого элемента в set
        for (Integer element : a) {
            if (set.contains(element) && !result.contains(element)) {
                result.add(element); // Добавляем элемент в результат, если он есть в b
            }
        }

        return result;
    }


    public static ArrayList<Integer> logicalAnd(ArrayList <ArrayList<Integer>> mathematicalSets, String combination){

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < combination.length(); i++) {
            if (combination.charAt(i) == '1') {
                if (result.isEmpty()) {
                    result = mathematicalSets.get(i);
                } else {
                    result = logicalAnd(result, mathematicalSets.get(i));
                    if (result.isEmpty()) return result;
                }
            }
        }
        return result;
    }

    public static ArrayList<Integer> logicalOr(ArrayList<Integer> a, ArrayList<Integer> b) {
        AbstractCollection<Integer> result = new HashSet<>();

        result.addAll(a);
        result.addAll(b);

        return new ArrayList<>(result);
    }

    public static ArrayList<Integer> сombineSetsWithSamePriority(ArrayList <ArrayList<Integer>> mathematicalSets, ArrayList<String> combinations){
        ArrayList<Integer> result = new ArrayList<>();
        for(String combination : combinations){
            ArrayList<Integer> resultLogicalAnd = logicalAnd(mathematicalSets,combination);
            result = logicalOr(result,resultLogicalAnd);
        }
//        System.out.println("итоговые индексы предложений"+result);
        return result;
    }


}
