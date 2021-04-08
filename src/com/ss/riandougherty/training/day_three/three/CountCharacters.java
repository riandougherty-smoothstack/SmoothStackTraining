package com.ss.riandougherty.training.day_three.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class CountCharacters {
	private CountCharacters() {}
	
	/**
	 * Finds all occurences of character in file.
	 * 
	 * 1st argument is filename.
	 * 
	 * 2nd argument is the character to search for.
	 */
	public static void main(final String[] args) throws Exception {
		
		FileReader fRead = null;
		
		try {
			final File f = new File(args[0]);
			
			fRead = new FileReader(f);
		} catch(final FileNotFoundException e) {
			System.err.println("Invalid filename");
			
			return;
		}
		
		
		char test;
		try {
			test = args[1].charAt(1);
		} catch(final ArrayIndexOutOfBoundsException e) {
			System.err.println("You did not specify a second argument.");
			
			fRead.close();
			return;
		}
		
		try {
			char[] data = new char[1];
			int i;
			
			
			i = 0;
			
			while(fRead.read(data) != -1) {
				if(data[0] == test) {
					System.out.println(i);
				}
				
				i++;
			}
		} finally {
			fRead.close();
		}
	}
}
