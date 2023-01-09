


public class Main {
    public static void main(String[] args) {

        System.out.println(reversWord("word"));

    }
    public static String reversWord(String inputString){
        String result = "";
        char[] word = new char[inputString.length()];
        for(int i =0; i< word.length; i++){
            word[i] = inputString.charAt(i);
        }

        for(int i = word.length-1; i>= 0; i--){
            result += word[i];
        }
        return result;
    }
}
