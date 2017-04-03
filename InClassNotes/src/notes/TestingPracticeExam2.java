package notes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ruski on 4/1/2017.
 */
public class TestingPracticeExam2 {


    private static double average(double[] array) {
        double count = 0;
        for (int i = 0; i < array.length; i++) {
            count += array[i];
        }
        return (count / array.length);
    }

    /*
		public static void main(String[] args){
			System.out.println(replace("Hello, World"));
			System.out.println(interest(.005, 100));
			ArrayList<Integer> l1 = new ArrayList<>;
			l1 =
			System.out.println(increasing({}))

		}
		*/
    public static String replace(String s1) {
        String s = s1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i)) == false) {
                s = s.replace(s.charAt(i), '#');
            }
        }
        return s;
    }

    private static int interest(double rate, double init) {
        double money = init;
        int months = 0;
        while (money < 2 * init) {
            money += rate * money;
            months++;
        }
        return months;
    }

    public static boolean increasing(ArrayList<Integer> list) {
        for (int i = 1; i <= list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    ///#2
    public static String removeComments(String input) {
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int index = line.indexOf("//");
            if (index >= 0) {
                lines[i] = line.substring(index);
            }
        }

        String[] ex = {
                "Apple",
                "Pie",
                "Swiss cheese"
        };

        assert String.join("++", ex) == "Apple++Pie++Swiss cheese";

        String result = String.join("\n", lines);
        return result;
    }

    public static void removeCommentsFromFile(String filename) throws IOException {
        File f = new File(filename);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String line;
        ArrayList<String> lines = new ArrayList<>();
        while (s.hasNextLine()) {
            line = s.nextLine();
            int index = line.indexOf("//");
            if (index >= 0) {
                lines.add(line.substring(index));
            } else {
                lines.add(line);
            }
        }

        s.close();

        String newFilename = filename.replaceAll(".java$", ".out");
        FileWriter w = null;
        w = new FileWriter(newFilename);
        for (line : lines) {
            w.write(line + "\n");
        }
        w.close();
    }
}
