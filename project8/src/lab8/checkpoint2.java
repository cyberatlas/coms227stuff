package lab8;

import java.util.ArrayList;

public class checkpoint2
{
    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Hi");
        list.add("Hi");
        list.add("Hi");
        list.add("Hello");
        //loops through the string list printing the words in it
        for(String word: list)
        {
            System.out.println(word);
        }
        removeDuplicates(list);
        for(String word: list)
        {
            System.out.println(word);
        }
    }
    public static void removeDuplicates(ArrayList<String> words)
    {
    	//adds all the words from the arraylist into a new array list only if they have not been used yet
        ArrayList<String> listCopy = new ArrayList<String>();
		/*for(int i = 0; i < words.size(); ++i)
		{
			String word = (String)words.get(i);
			if(!listCopy.contains(word))
			{
				listCopy.add(word);
			}
		}*/
        for(String word: words)
        {
            if(!listCopy.contains(word))
            {
                listCopy.add(word);
            }
        }
        words.clear();
        //copies the new array into the old one
        words.addAll(listCopy);
    }
}
