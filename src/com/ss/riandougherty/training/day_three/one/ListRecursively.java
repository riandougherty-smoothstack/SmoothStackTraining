package com.ss.riandougherty.training.day_three.one;

import java.io.File;

public final class ListRecursively {
	private ListRecursively() {}
	
	private static void listRecursively(final File file) {
		for(final File f : file.listFiles()) {
			System.out.println(f.getPath());
			
			if(f.isDirectory()) {
				listRecursively(file);
			}
		}
	}
	
	/**
	 * Takes directory path as argument
	 */
	public static void main(final String[] args) {
		File tld = new File(args[0]);
		
		listRecursively(tld);
	}
}
