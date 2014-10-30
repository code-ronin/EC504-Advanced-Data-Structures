package hash;

import java.io.IOException;
import java.util.*;

public class Decode extends hash{
	public static void main(String[] args)
	{
		//Create array for hashes
		String[] hashStrings = {"b*4&r<:b*|(24x26", "66 ,8n<.\" fpfnj>", 
				"<pf\"|r x(\"8f|>ld", "dnf (x:rt8( ,f6t", ":f*n&>~2z 8zl> 6",
				" `<z6\">:tb~vfx*v", "x&*>t:.r<<:<d$2n", "z|j*r4*&xhfpjndb",
				"x&rj&4r~f,*x~xht", "\"~0\"rvx~xjlx ldh"};
		//Use a 52 letter alphabet (26 Uppercase, 26 Lowercase)
		String[] alphabet = {"A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f", "G", 
				"g", "H", "h", "I", "i", "J", "j", "K", "k", "L", "l", "M", "m", "N", "n", 
				"O", "o", "P", "p", "Q", "q", "R", "r", "S", "s", "T", "t", "U", "u", "V", "v",
				"W", "w", "X", "x", "Y", "y", "Z", "z"};
		//Create a hash table to store all possible 3-letter hash combinations
		Hashtable<String, String> values  = new Hashtable<String, String>();
		//Run through and fill hash table with all possible 3-letter hashes
		for (int i = 0; i<52; i++){
			for (int j = 0; j <52; j++){
				for (int k = 0; k<52; k++){
					String str = alphabet[i] + alphabet[j] + alphabet[k];
					String hash = compact(str);
					values.put(hash, str);
				}
			}
		}
		//Check to see if value is contained in hash table and print the hash and 3-letter string
		for (int i= 0; i<hashStrings.length; i++){
			String str3 = values.get(hashStrings[i]);
			System.out.println(hashStrings[i] + "    " + str3);
		}
	}
}
