package org.example.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TruthTable {

    private final int SIZE_OF_PERMUTATION ;
    
    private final int THE_LARGEST_NUMBER_iN_TRUE_TABLE;
    private final Map<Integer, ArrayList<String>> combinations = new HashMap<>();

    public TruthTable(int numberOfSets){
        SIZE_OF_PERMUTATION = numberOfSets;
        THE_LARGEST_NUMBER_iN_TRUE_TABLE = (int) (Math.pow(2,SIZE_OF_PERMUTATION) - 1);
        findCombinations();
    }


    //перебор 2^N-1 до 1, N- количество множеств (найденных токенов)
    private void findCombinations(){
        for(int i = THE_LARGEST_NUMBER_iN_TRUE_TABLE; i > 0; i--){

            //Отношение множеств друг к другу, будем представлять в качестве бинарного числа
            String combination = getBinaryString(i);

            //комбинация {1,1,1} = A{}*B{}*C{}
            //комбинации {1, 1, 0} = A{}*B{}
            //комбинации {1, 0, 1} = A{}*С{}
            //комбинации {1, 1, 0} и {1, 0, 1} равнозначные так как не упоминают один токен из всех
            //кол-во нулей = кол-во отсутствующих токенов
            int numberOfZeros = countZeros(combination);

            ArrayList <String> combinationsWithSameNumberOfZeros;

            if(combinations.containsKey(numberOfZeros)){
                combinationsWithSameNumberOfZeros = combinations.get(numberOfZeros);
            }
            else{
                combinationsWithSameNumberOfZeros = new ArrayList<String>();
            }
            //сохраняем комбинации по равнозначимости
            combinationsWithSameNumberOfZeros.add(combination);
            combinations.put(numberOfZeros, combinationsWithSameNumberOfZeros);
        }
    }

    //функция для подсчета нулей в перестановке
    private int countZeros(String str) {
        int count = 0;
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i) == '0') count++;
        }
        return count;
    }


    //сделать бинарное число для обозначения отношений множеств
    private String getBinaryString(int number) {
        // Получаем двоичное представление числа K
        String binary = Integer.toBinaryString(number);

        // Проверяем, нужно ли дополнить строку нулями
        if (binary.length() < SIZE_OF_PERMUTATION) {
            // Дополняем слева нулями до длины N
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < SIZE_OF_PERMUTATION - binary.length(); i++) {
                sb.append('0');
            }
            sb.append(binary);
            return sb.toString();
        }

        return binary;
    }

    public ArrayList<String> getCombinations(int numberOfZeros){
        return combinations.get(numberOfZeros);
    }

}
