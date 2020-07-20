package sun.compiler.util;

import com.alibaba.fastjson.JSONObject;
import sun.compiler.constants.Constants;

import java.io.*;

public class CompilerUtil {
	
	public static String compile(String path) {
	
		Process pro = null;
		String msg = null;
		
		StringBuilder response = new StringBuilder("\n");
		
		String control;
		String[] split_array;
		
		try {
			
			pro = Runtime.getRuntime().exec("python " + path);
			
			InputStream is = pro.getInputStream();
			InputStream err = pro.getErrorStream();
			
			SequenceInputStream sis = new SequenceInputStream(is, err);   // 整合错误流和输出流
	
			InputStreamReader isr = new InputStreamReader(sis, "GBK");
			
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder sb = new StringBuilder();
			
			while ((msg = br.readLine()) != null) {
				
				sb.append(msg).append("\n");
				
			}
			
			control = sb.toString();
			
			split_array = control.split("\n");

			for (String s : split_array) {

				response.append(s).append("<br>");

			}

			return response.toString();
			
		} catch (Exception e) {
			
			e.printStackTrace();

		}

		return null;

	}


	public static void main(String[] args) {

		String path = "E:\\tempFile\\world.py";

		String cc = CompilerUtil.compile(path);

		System.out.println(cc);


	}

}
