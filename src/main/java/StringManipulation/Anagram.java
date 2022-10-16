package StringManipulation;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

    public static void main(String[] args) {
        String s = "Java";

        String s2 = "abJv";
/**
 * Java 8 ---------------
 *
 * s1.chars()
 .filter(ch -> ch != ' ')
 .map(ch -> Character.toLowerCase((char) ch))
 .forEach(ch -> {
 if (s1chars2count.containsKey(ch)) s1chars2count.put((char) ch, s1chars2count.get(ch) + 1);
 else s1chars2count.put((char) ch, 1);
 });

 s2.chars()
 .filter(ch -> ch != ' ')
 .map(ch -> Character.toLowerCase((char) ch))
 .forEach(ch -> {
 if (s2chars2count.containsKey(ch)) s2chars2count.put((char) ch, s2chars2count.get(ch) + 1);
 else s2chars2count.put((char) ch, 1);
 });
 */
        Map<Character,Integer> hashMapS1 = new HashMap<Character, Integer>();

        Map<Character,Integer> hashMapS2 = new HashMap<Character, Integer>();

        for(Character s1 : s.toCharArray()){

            hashMapS1.put(s1,hashMapS1.getOrDefault(s1,0)+1);
        }

        for(Character s1 : s2.toCharArray()){

            hashMapS2.put(s1,hashMapS1.getOrDefault(s1,0)+1);
        }

         boolean ss =  hashMapS1.size() == hashMapS2.size()
                 && hashMapS1.entrySet().stream()
                .filter(e -> !hashMapS2.containsKey(e.getKey()) || hashMapS2.get(e.getKey()) != e.getValue())
                .noneMatch(sq -> true);

        System.out.println("=="+ss);

    }


}
