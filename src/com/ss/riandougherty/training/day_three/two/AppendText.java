package com.ss.riandougherty.training.day_three.two;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class AppendText {
	private AppendText() {}

	/**
	 * Appends text to file.
	 * 
	 * 1st arg = destination filename
	 * 
	 * 2nd arg = text to append
	 */
	public static void main(final String[] args) {
		FileOutputStream fos;
		
		fos = null;
		
		try {
			File f = new File(args[0]);
			
			fos = new FileOutputStream(f);
			
			fos.write(args[1].getBytes());
		} catch(final Exception e) {
			System.err.println("Error occcurred in main.");
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
