/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Vinayak
 */
public class vowels {
	private static Scanner input;

	public static void main(String[] args) 
    {
		System.out.print("Enter a filename: ");
                Scanner input = new Scanner( System.in );
		String fileName=input.next();
		
       try{

          FileReader inputFile = new FileReader(fileName);

                    try (BufferedReader bufferReader = new BufferedReader(inputFile)) {
                        String content="";
                        String text="";
                        while ((content = bufferReader.readLine()) != null)   {
                            text = text + content;
                        }
                        
                        char characters[] = text.toCharArray();
                        
                        HashSet<String> h = new HashSet<>();
                        h.add("A");
                        h.add("E");
                        h.add("I");
                        h.add("O");
                        h.add("U");
                        h.add("a");
                        h.add("e");
                        h.add("i");
                        h.add("o");
                        h.add("u");
                        
                        int vowelCount = 0;
                        
                        for (char chr : characters) {
                            if(h.contains(chr+""))
                            {
                                vowelCount++;
                            }                            
                        }                        
                        System.out.println("\nThe number of vowels is: "+vowelCount);
                    }
       }catch(Exception e){
          System.out.println("Error while reading the file " + e.getMessage());                      
       }

     }
}
