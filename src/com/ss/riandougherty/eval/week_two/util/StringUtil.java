package com.ss.riandougherty.eval.week_two.util;

import java.util.Collection;

public final class StringUtil {
	public static final String COMMA_DELIM = ", ";
	public static final String COLON_DESCRIBER = ": ";
	
	private StringUtil() {}
	
	public static String getFormattedStringFromMapper(final Collection<String> fields, SimpleMapper<String> mapper, final String delim) {
		final StringBuilder sb = new StringBuilder();
		
		if(mapper == null) {
			mapper = s -> {
				return s;
			};
		}
		
		boolean isFirst = true;
		
		for(final String field : fields) {
			if(isFirst) {
				isFirst = false;
			} else {
				sb.append(delim);
			}
			
			sb.append(mapper.map(field));
		}
		
		return sb.toString();
	}
	
	public static String getFormattedStringFromMapper(final Collection<String> fields, SimpleMapper<String> mapper) {
		return getFormattedStringFromMapper(fields, mapper, COMMA_DELIM);
	}
	
	public static String getFormattedStringFromNVPs(final Collection<NameValuePair<? extends Object>> nvps, final String delim, final String describer) {
		final StringBuffer sb = new StringBuffer();
		
		boolean isFirst;
		
		isFirst = true;
		
		for(final NameValuePair<?> nvp : nvps) {
			if(isFirst) {
				isFirst = false;
			} else {
				sb.append(delim);
			}
			
			sb.append(nvp.getName());
			
			final Object value = nvp.getValue();
			if(value != null) {
				sb.append(describer);
				sb.append(value.toString());
			}
		}
		
		return sb.toString();
	}
	
	public static String getFormattedStringFromNVPS(final Collection<NameValuePair<? extends Object>> nvps) {
		return getFormattedStringFromNVPs(nvps, COMMA_DELIM, COLON_DESCRIBER);
	}
}
