package StringManipulation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintDuplicateChars {

    public static void main(String[] args) {

        String s ="vipinahuja";

        System.out.println(PrintDuplicateChars.duplicates(s));

    }

    public static  Map<Character,Integer> duplicates(String s ){
        Map<Character,Integer> duplicateCount = new HashMap<>();
        if(s==null){
            return duplicateCount;
        }else
        {
            s.chars().forEach(ch->{
                    duplicateCount.put((char)ch,duplicateCount.getOrDefault((char)ch,0)+1);

            });
        }

        /*return duplicateCount.entrySet().
                stream().filter(e->e.getValue()>1).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));*/
// If need to print count of every word
        return duplicateCount.entrySet().
                stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));


    }
}
