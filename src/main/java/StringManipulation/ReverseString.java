package StringManipulation;

public class ReverseString {

    public static void main(String[] args) {
        String s = "Shiva";

        reverseIterative(s);
    }

    public static  String reverseIterative(String s ){
        if(s==null){
            return "";

        }else {
            int len = s.length();

            char[] reverse = new char[len];

            for(int i=0;i<=s.length()/2;i++){
  // it will replace the min length
                reverse[i]= s.charAt(len-i-1);
                reverse[len-i-1]= s.charAt(i);

            }
            return new String(reverse);
        }
    }

    public String recursive(String s ){
        if(s==null) return "";
        if(s.length()==1) return s;
        return s.charAt(s.length()-1) +  recursive(s.substring(0,s.length()-1)); // recursive way

    }

}