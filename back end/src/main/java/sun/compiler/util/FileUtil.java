package sun.compiler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
	
	/**
	 * @discription ��ȡһ��ʱ�䴮
	 * @return String
	 */
	public static String getStringID() {
		
		String id = null;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		
		id = sdf.format(date);
		
		return id;
		
	}
	
	

}
