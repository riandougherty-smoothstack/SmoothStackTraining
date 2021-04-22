package com.ss.riandougherty.eval.week_two.entity;

import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;
import com.ss.riandougherty.eval.week_two.util.StringUtil;

public abstract class BaseEntity {
	public abstract List<NameValuePair<? extends Object>> getNameValuePairs();
	
	public String getEasyString(final String describer, final String delim) {
		return StringUtil.getFormattedStringFromNVPs(this.getNameValuePairs(), delim, describer);
	}
	
	@Override
	public String toString() {
		return this.getEasyString(StringUtil.COLON_DESCRIBER, StringUtil.COMMA_DELIM);
	}
}
