package sandbox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Main {

    public static void main (String [] args) {
        //System.out.println(reverseSentence("1 2 3"));
        System.out.println(mostFrequentDigits(new int[]{25, 2, 5, 17, 91}));
    }

    static String reverseSentence(String sentence) {
        StringBuilder reversed = new StringBuilder();
        String[] words = sentence.split(" ");

        for(int i = words.length-1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }

        return reversed.toString().trim();
    }

    static int[] mostFrequentDigits(int[] array) {
        Map<Integer, Integer> digitsMap = new HashMap<>();

        for(int n : array) {
            String str = Integer.toString(n);
            Arrays.stream(str.split(""))
                    .mapToInt(Integer::parseInt)
                    .forEach(o -> {
                        digitsMap.compute(o, (key, val) -> val != null ? val+1 : 1);
                    });
        }

        int max = digitsMap.values().stream().max(Integer::compareTo).get();
        System.out.println(digitsMap);
        int []result = digitsMap.entrySet().stream().filter(entry -> entry.getValue().equals(max))
                .sorted((a,b) -> b.getKey().compareTo(a.getKey()))
                .mapToInt(Map.Entry::getKey)
                .toArray();

        StringBuilder strBuilder = new StringBuilder();
        for (int i : result) {
            strBuilder.append(i).append(",");
        }
        System.out.println(strBuilder.toString());

        return result;
    }


    static int addTwoDigits(int n) {
        String strInt = Integer.toString(n);

        return Arrays.stream(strInt.split(""))
                .mapToInt(Integer::parseInt)
                .reduce((a,b) -> {
                    System.out.println(String.format("a=%s,b=%s",a,b));
                    return a+b;
                }).getAsInt();
    }

}
