package io.nakong;

public class StringBufferTest {

	public static void main(String[] args) {
		StringBuffer stringJoiner = new StringBuffer();
		for (int i =1;i< 10 ;i++ ) {
			stringJoiner.append("hello ");
			stringJoiner.append(",");
		}
		if (stringJoiner.length() > 1) {
			stringJoiner.deleteCharAt(stringJoiner.length());
		}
		  System.out.println(stringJoiner);
	}
}
