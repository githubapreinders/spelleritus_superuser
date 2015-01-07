package afr.spelleritustest.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Tools
{

//just for running this file separately
	public static void main(String args[])
    {
//        Scanner cs = new Scanner(System.in);
//        System.out.println("A string please\n");
//        System.out.println();
//        System.out.println(makeWordList(cs.nextLine()));
    }
	
	
	
//removes extra spaces ,comma's and \n characters and returns a string with words separated with one space
	public static String makeWordList(String textboxstring)
	{
		String[] string = textboxstring.split("[\\s+,\\s+]");
		ArrayList<String> helper = new ArrayList<String>();
		for (int i = 0; i < string.length; i++)
		{
			string[i].trim();
			if (!(string[i].equals("")))
			{
				helper.add(string[i].toLowerCase());
			}
		}
		String returnstring = "";
		for (String s : helper)
		{
			returnstring += s + " ";
		}
		returnstring = returnstring.replaceAll("\\r\\n|\\r|\\n", " ");
		return returnstring;

	}
	
	//returns the same string but with a capital letter for each word
	public static String correctName(String name)
	{
		ArrayList<Integer> capitalindexes = new ArrayList<Integer>();
		capitalindexes.add(0);
		name.trim();
		StringBuilder str = new StringBuilder(name);
		for (int i = 0; i < str.length() - 1; i++)
		{
			if (str.charAt(i) == ' ')
			{
				capitalindexes.add(i + 1);
			} else
				str.setCharAt(i, Character.toLowerCase(str.charAt(i)));
		}
		for (int j : capitalindexes)

			str.setCharAt(j, Character.toUpperCase(str.charAt(j)));
		return str.toString();
	}
	
	public static double round(double value, int places)
	{
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}
