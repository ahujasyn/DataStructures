package StringManipulation;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(permutations(s));
    }

    public static  List<String> permutations(String s) {
        if (s==null) return null;
        List<String> result = new ArrayList<>();
        if (s.length() < 2){

            result.add(s);
            return result;
        }

        List<String> permutations = permutations(s.substring(1));
        for (String permutation : permutations){
            for (int i=0;i<=permutation.length();i++){
                String newPermutation = permutation.substring(0, i) + s.charAt(0)+ permutation.substring(i);
                result.add(newPermutation);
            }
        }
        return result;
    }


}
