package com.ss.riandougherty.training.day_one.one;

/**
 * Class for simple horizontal formatting
 *
 */
public class TextFormatter {
	private final int maxWidth;
	
	private Alignment alignment;
	
	public TextFormatter(final int maxWidth, final Alignment alignment) {
		this.maxWidth = maxWidth;
		this.alignment = alignment;
	}
	
	/**
	 * Returns a formatted string based on the formatter's properties.
	 * 
	 * @param input - input string
	 * @return A formatted string
	 */
	public String formatLine(final String input) {
		if(input.length() > maxWidth) {
			throw new IndexOutOfBoundsException("The input string must be smaller than maxWidth");
		}
		
		// no need to do anything else since appending on the left of the line should add no indentation.
		if(alignment == Alignment.LEFT) {
			return input;
		}
		
		int freeSpaceRemaining, charsToAppend;
		
		freeSpaceRemaining = maxWidth - input.length();
		
		if(alignment == Alignment.CENTER) {
			charsToAppend = freeSpaceRemaining >> 1; // div by 2
		} else { // alignment is RIGHT
			charsToAppend = freeSpaceRemaining;
		}
		
		final StringBuilder result = new StringBuilder(input.length() + charsToAppend);
		
		int i = charsToAppend;
		
		while(i != 0) {
			result.append(' ');
			
			i--;
		}
		
		result.append(input);
		
		return result.toString();
	}
	
	/**
	 * Prints a formatted line
	 * 
	 * @param input - the String to format
	 */
	public void printLine(final String input) {
		System.out.println(formatLine(input));
	}
	
	/**
	 * Prints a string of repeated characters of length N.
	 * 
	 * @param c - source character to repeat
	 * @param n - number of times to repeat character
	 */
	public void printNCharString(final char c, final int n) {
		printLine(Util.nCharString(c, n));
	}
	
	/**
	 * Prints (n_times) lines, where the number of characters repeated in the first line is start. Each new line is inc shorter/longer.
	 * 
	 * 
	 * @param source - source character to repeat
	 * @param start - number of characters the first line has
	 * @param inc - the amount to decrease/increase the string each line
	 * @param n_times - number of times to do this
	 */
	public void printCharInc(final char source, final int start, final int inc, final int n_times)
	{		
		final StringBuilder sb = new StringBuilder();
		Util.appendNChars(sb, '*', start);
		
		int a;
		
		a = 0;
		for(;;) {
			printLine(sb.toString());
			
			if(++a == n_times) {
				break;
			}
			
			Util.appendNChars(sb, '*', inc);
		}
	}
	
	/**
	 * @return The current Alignment setting
	 */
	public Alignment getAlignment() {
		return alignment;
	}
	
	/**
	 * 
	 * @param newAlignment new Alignment setting
	 */
	public void setAlignment(final Alignment newAlignment) {
		alignment = newAlignment;
	}
}
