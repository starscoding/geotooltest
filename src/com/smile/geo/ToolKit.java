package com.smile.geo;

/**
 * 处理字符串的小工具
 * 
 * @author huangcong
 * 
 */
public class ToolKit {

	/**
	 * 检查一个字符串经过trim()后是否为空白字符串
	 * 
	 * @param s
	 *            字符串
	 * @return 结果
	 */
	public static boolean isBlank(String s) {

		if (s.trim().equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 检查一个字符串是否为null或者经过trim()后是否为空白字符串
	 * 
	 * @param s
	 *            字符串对象
	 * @return 结果
	 */
	public static boolean isNullOrBlank(String s) {

		if (s == null)
			return true;
		if (isBlank(s))
			return true;

		return false;
	}

	/**
	 * 把long类型的timemillis格式化
	 * 
	 * @param millis
	 * @return
	 */
	public static String formatTimeMillis(long millis) {

		String str;

		long tmp = 0;
		if ((tmp = millis / (long) 1000) > 0)
			str = "" + tmp + "." + (millis % (long) 1000) + "秒";
		else
			str = "0." + millis + "秒";

		return str;
	}

	/**
	 * 把long类型的timemillis格式化.单位为秒，但是无单位名称字符。
	 * 
	 * @param millis
	 * @return
	 */
	public static String formatTimeMillisNoUnit(long millis) {

		String str;

		long tmp = 0;
		if ((tmp = millis / (long) 1000) > 0)
			str = "" + tmp + "." + (millis % (long) 1000);
		else
			str = "0." + millis;

		return str;
	}

	/**
	 * 去掉文件名中的后缀
	 * 
	 * @param filename
	 * @return
	 */
	public static String trimPostfixOfFilename(String filename) {

		int index = filename.lastIndexOf(".");
		if (index > -1)
			return filename.substring(0, index);

		return filename;
	}

	/**
	 * 打印二维字符串数组的内容
	 * 
	 * @param arr
	 */
	public static void printArray(String[][] arr) {

		if (arr == null)
			return;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				System.out.print(arr[i][j] + "\t");
			System.out.println("");
		}
	}
}
