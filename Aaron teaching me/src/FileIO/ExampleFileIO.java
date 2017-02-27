package FileIO;

/**
 * Created by ruski on 2/27/2017.
 */
public class ExampleFileIO {
    public static int count(String word) {

        //defines and sets the counter to 0
        int count = 0;
        //iterates wordlength times, increases by 1 each time
        for (int i = 0; i < word.length(); ++i) {
            //defines character c as the character at index i in the string
            char c = word.charAt(i);
            //lowercase characters are in order, so we check if it is between a and z lowercase
            if (c >= 'a' && c <= 'z') {
                count++;
            }
        }
        return count;
    }
}
