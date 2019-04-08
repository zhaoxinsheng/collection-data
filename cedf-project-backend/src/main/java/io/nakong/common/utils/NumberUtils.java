package io.nakong.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {

	private static String numberFormatter  = "#,###.##";
	
	public static String formatNumber (Double d,String formatter) {
		if (d == null) {
			return "";
		}
		NumberFormat nf = new DecimalFormat(formatter);
		return nf.format(d);
	}
	
     public static String formatNumber (Double d) {
    	if (d == null) {
 			return "";
 		} 
    	NumberFormat nf = new DecimalFormat(numberFormatter);
		return nf.format(d);
	}
     
     public static String formatNumber (BigDecimal d) {
     	if (d == null) {
  			return "";
  		} 
     	NumberFormat nf = new DecimalFormat(numberFormatter);
 		return nf.format(d);
 	}
     
    public static boolean isAmount(BigDecimal amount) {
    	  Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
    	  Matcher match = pattern.matcher(String.valueOf(amount));   
    	  return match.matches();
    }
    
    public static void main(String[] args) {
    	BigDecimal amount = new BigDecimal("3.00");
		if (isAmount(amount)) {
			System.out.println(" This is amount ");
		} else {
			System.out.println(" Not a  amount ");
		}
	}
    
}
