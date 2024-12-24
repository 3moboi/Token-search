package org.example.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TextProcessing {

    final static int MINIMAL_WORD_LENGTH = 3;

    //Сафонов оформи!
    //Чек лист для доделывания
    //ключевые названия компаний или документов и абвиатур;
    //если строка пустая

    public static List<String> split(String input){

        //разбиение текста на слова;
        List<String> splittingTextIntoTokens = splitIntoTokens(input);

        List<String> words = new ArrayList<>();

        for(String word : splittingTextIntoTokens){
            //проверка слов на
            if(isAbbreviation(word)){
                words.add(word);
                continue;
            }

            //грубая очистка от стоп-слов
            if(isStopWord(word)) continue;

            //удаление окончания слов
            String wordWithoutEnding = morphemeСonversion(word.toLowerCase());

            //сохранение
            if(wordWithoutEnding.length() >= MINIMAL_WORD_LENGTH) words.add(wordWithoutEnding);

        }
        return words;
    }

    public static boolean isAbbreviation(String word){
        for (char c : word.toCharArray()) {
            if (!Character.isUpperCase(c)) { // Проверка на заглавную букву
                return false; // Если хотя бы одна буква не заглавная - возвращаем false
            }
        }
        return true;
    }

    //      разбиение текста на слова;
    public static List<String> splitIntoTokens(String input){
        String regex = "[a-zA-ZА-Яа-яЁё]+";

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        List<String> splittingTextIntoTokens = new ArrayList<>();

        while (matcher.find()) {
            //перевод всех слов в один регистр для уменьшения количества одинаковых слов;
            splittingTextIntoTokens.add(matcher.group());
        }

        return splittingTextIntoTokens;
    }


    //      Это стоп-слово?
    public static boolean isStopWord(String word){
        return word.length() < MINIMAL_WORD_LENGTH;
    }


    //      проведение стемминга, удаление окончания
    public static String morphemeСonversion(String word){

        HashSet<String> lexicon = new HashSet<>(Arrays.asList("ами","ями","ыми","ими","ого","его","ому","ием","ией","ему","еть","ыть","уть","ать",
                "ять","оть","ить","ов","ев","ам","ям","ах","ях","ые","ие","ых","их","ым","ая","яя","ой","ей","ую",
                "юю","ия","ии","ию","ое","ее","ым","им","ом","ем","ый","ий","а","я","ы","и","е","у","ю","о","ь"));

        for(int morphemeLength = 3; morphemeLength > 0; morphemeLength-- ){
            int beginIndexEnding = word.length() - morphemeLength;

            String ending = word.substring(beginIndexEnding);

            if(lexicon.contains(ending)){
                return word.substring(0,beginIndexEnding);
            }
        }

        return word;
    }

    //очень грубый поиск однокоренных слов
    public static ArrayList <String> findSingleRootWords(String word){
        ArrayList <String> singleRootwords = new ArrayList<>();
        for(int len = word.length();len >= MINIMAL_WORD_LENGTH && len > word.length()/2; len-- ){
            singleRootwords.add(word.substring(0,len));
        }
        return singleRootwords;
    }

}
