package io.nakong;

public class Test {
	
	public static void main(String[] args) {
		
		int init = 12;
		String [] values = {
				
				  "shljll ",
				  "lxjsjll",
				  "fdjljll",
				  "fdisjll",
				  "ccdljll",
				  "ccdsjll",
				  "ybljll ",
				  "ybsjll ",
				  "ybbljll",
				  "ybbsjll",
				  "shljll ",
				  "shsjll ",
				  "shbljll",
				  "shbsjll"
		};
		for (int  i = 0 ;i < 4 ;i++){
			
			StringBuffer  bf = new StringBuffer("{");
			for (String value : values) {
				bf.append(value.trim() + ":'"+ i * 30 + init +"',");
			}
			bf.append("\n");
			bf.append("},");
			bf.append("\n");
		}
	}

}
