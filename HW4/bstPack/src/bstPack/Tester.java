package bstPack;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

import bstPack.BST.Rotation;

public class Tester{

	 public static void main(String[] args) throws IOException
		{
		 	ArrayList<Integer> T1num = new ArrayList<Integer>();
		 	ArrayList<Integer> T2num = new ArrayList<Integer>();
		 	
		 	for (int ii =1; ii<=6;ii++){
		 		T1num.add(ii);
		 		T2num.add(7-ii);
		 	}
		 	BST T1 = new BST(T1num.get(0));
		 	BST T2 = new BST(T2num.get(0));
		 	
		 	for(int ii=1;ii<T1num.size();ii++)
		 	{
		 		T1.insert(T1num.get(ii));
		 		T2.insert(T2num.get(ii));
		 	}
		 	
		 	//TEST
		 	ArrayList<Integer> num1 = new ArrayList<Integer>();
		 	ArrayList<Integer> num2 = new ArrayList<Integer>();
		 	num1.add(1);
		 	num1.add(3);
		 	num1.add(2);
		 	num1.add(4);
		 	num1.add(5);

		 	num2.add(4);
		 	num2.add(2);
		 	num2.add(1);
		 	num2.add(3);
		 	num2.add(5);

		 	BST T11 = new BST(num1.get(0));
		 	BST T22 = new BST(num2.get(0));
		 	
		 	for(int ii=1;ii<num1.size();ii++)
		 	{
		 		T11.insert(num1.get(ii));
		 		T22.insert(num2.get(ii));
		 	}
		 	
	    	String value = T11.print();
	    	System.out.printf("Orig First\n%s\n", value);
	    	String value1 = T22.print();
	    System.out.printf("Orig Second\n%s\n", value1);

	    
	    	ArrayList<Rotation> values = BST.transform(T11, T22);
	    	for(int ii=0;ii<values.size();ii++)
	    	{	
	    		value = values.get(ii).print();
	    		System.out.printf("%s\n",value);}
		}
}