package com.smile.geo;

import java.util.Map;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.dbcp.BasicDataSource;

import com.eastcomsw.um.udq.db.base.BaseSqlExecutor;
import com.eastcomsw.um.udq.db.base.DBConnectionProvider;
import com.eastcomsw.util.ToolKit;

/**
 * 中文转换拼音功能类
 * 
 * @author huangc
 * 
 */
public class Pinyin {
	/**
	 * 将汉字转换为全拼
	 * 
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		if (ToolKit.isNullOrBlank(src))
			return "";
		src = src.replaceAll("\r\n", "");
		char[] t1 = null;
		t1 = src.toCharArray();
		// System.out.println(t1.length);
		String[] t2 = new String[t1.length];
		// System.out.println(t2.length);
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		String tmp = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断能否为汉字字符
				// System.out.println(t1[i]);
				String t = Character.toString(t1[i]);
				if (t.matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
					t4 += " " + t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else if (t.matches("[a-zA-Z0-9\\s]{1,}")) {
					if (tmp.matches("[\\u4E00-\\u9FA5]+"))
						t4 += " " + t;
					else
						t4 += t;
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					t4 += " " + t;
				}
				tmp = t;
			}

			t4 = t4.trim();
			String sta = Character.toString(t1[0]);
			if (!sta.matches("[a-zA-Z0-9\\s]{1,}"))
				t4 = " " + t4;
			String end = Character.toString(t1[t0 - 1]);
			if (!end.matches("[a-zA-Z0-9\\s]{1,}"))
				t4 = t4 + " ";
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t4;

	}
	
	/**
	 * 将汉字转换为全拼 去掉空格的,但每个字母中间加空格
	 * @param src
	 * @return
	 */
	public static String getPinYinNoNull_n(String src){
		String pinyin=getPinYinNoNull(src);
		if(pinyin==null || pinyin==""){
			return null;
		}
		String _l = " "+pinyin.charAt(0);
		for (int i = 1; i < pinyin.length(); i++) {
			_l += " " + pinyin.charAt(i);
		}
		
		return _l;		
	}

	/**
	 * 将汉字转换为全拼 去掉空格的
	 * 
	 * @param src
	 * @return String
	 */
	public static String getPinYinNoNull(String src) {
		if (ToolKit.isNullOrBlank(src))
			return "";
		src = src.replaceAll("\r\n", "");
		char[] t1 = null;
		t1 = src.toCharArray();
		// System.out.println(t1.length);
		String[] t2 = new String[t1.length];
		// System.out.println(t2.length);
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		String tmp = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断能否为汉字字符
				// System.out.println(t1[i]);
				String t = Character.toString(t1[i]);
				if (t.matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
					if(t2==null)
						return null;
					t4 += "" + t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else if (t.matches("[a-zA-Z0-9\\s]{1,}")) {
					if (tmp.matches("[\\u4E00-\\u9FA5]+"))
						t4 += "" + t;
					else
						t4 += t;
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					t4 += "" + t;
				}
				tmp = t;
			}

			t4 = t4.trim();
			String sta = Character.toString(t1[0]);
			if (!sta.matches("[a-zA-Z0-9\\s]{1,}"))
				t4 = "" + t4;
			String end = Character.toString(t1[t0 - 1]);
			if (!end.matches("[a-zA-Z0-9\\s]{1,}"))
				t4 = t4 + "";
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t4;

	}

	/**
	 * 提取每个汉字的首字母
	 * 
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str) {
		if (ToolKit.isNullOrBlank(str))
			return "";
		String convert = "";
		try {
			for (int j = 0; j < str.length(); j++) {
				char word = str.charAt(j);
				// 提取汉字的首字母
				String[] pinyinArray = PinyinHelper
						.toHanyuPinyinStringArray(word);
				if (pinyinArray != null) {
					convert += pinyinArray[0].charAt(0)+" ";
				} else {
					convert += word;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convert;
	}
	
	public static String getPinYinHeadChar_null(String str){
		return getPinYinHeadChar(str).replace(" ", "");
	}

	/**
	 * 将字符串转换成ASCII码
	 * 
	 * @param cnStr
	 * @return String
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		// 将字符串转换成字节序列
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i] & 0xff));
			// 将每个字符转换成ASCII码
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	public static void main(String[] args) {
		// String cnStr =
		// "111三屯伦品ZGA43456涌周围一带、景223洋酒店,和谊便fad利店,惠万佳连锁超市,昌隆百货店,盛利发便利店,天津狗不理专卖(三屯店),正宗天津狗不理专卖(三屯分店),金松百货,美宜佳,名流百货,兴达百货商场,湖南便利店,正宗天津狗不理专卖店驻厚街分店,正宗天津灌汤包,宜兴便利店,满意100百货,名流百货,金松百货,兴达百货商场,中国电信刘宣电话便利店,湖南便利店,宜兴便利店,满意100百货连锁,伦品涌市场,上好便利,中国电信华记公话超市,广东通讯话吧,中国电信成记公话超市,中国网通话吧,华记公话超市,中国网通广东通信话吧,中国网通公话超市,中国网  ";
		// String cnStr = "三屯%伦品12";
		// String cnStr1 = getPinYin(cnStr);
		// System.out.println(cnStr1 + ";");
		// if (cnStr1.contains(" an li "))
		// System.out.println("**********");
		// System.out.println(getPinYinHeadChar(cnStr));
		// System.out.println(getCnASCII(cnStr));
//		String src = "和谊便利店 ";
//		System.out.println(getPinYin(src));
//		System.out.println(getPinYinNoNull(src));
//		System.out.println(getPinYinHeadChar_null(src));
		
//		 BasicDataSource dataSource = null;
//		 DBConnectionProvider connProvider;
//		dataSource = new BasicDataSource();
//		dataSource
//				.setUrl("jdbc:sybase:Tds:10.221.32.110:4100/ecis?charset=eucgb");
//		dataSource.setDriverClassName("com.sybase.jdbc3.jdbc.SybDriver");
//		dataSource.setUsername("ecis");
//		dataSource.setPassword("ecis1234");
//
//		connProvider = new DBConnectionProvider();
//		connProvider.setDataSource(dataSource);
//
//		
//		String sql = "insert into gis_tips_info values(?,?,?,?,?,?,?)";
//		
		for(int i=1;i<=13;i++){
			String objlocation = i+"号线弄号";
			if (objlocation != null && !("".equals(objlocation.trim()))) {
				Map<String, String> map = CnChar2PinyinUtil
						.getPinyinMap(objlocation);
				// 简化全拼0x04
				String loc_pinyin = map.get(CnChar2PinyinUtil.F0X04);
				// 简化声母0x01
				String loc_pinyin_short = map.get(CnChar2PinyinUtil.F0X01);

				String pinyinnonull=Pinyin.getPinYinNoNull_n(objlocation);
				System.out.println(loc_pinyin+","+loc_pinyin_short+","+pinyinnonull);
		
//				Object[] p = new Object[7];
//				p[0] = objlocation;
//				p[1] = 1;
//				p[2] = loc_pinyin;
//				p[3] = loc_pinyin_short;
//				p[4] = "上海市";
//				p[5] = "";
//				p[6] = pinyinnonull;
//
//				BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
//				 int rtn = sqlExecutor.executeUpdate(connProvider, sql, p);
			}
			

		}
//		String s="8号";
//		if (s.endsWith("号")||s.endsWith("弄")) {
//			s = s.substring(0, s.length() - 1);
//			if (Pattern.matches("[a-zA-Z0-9\\s]{1,}", s)) {
//				s = "";
//			}
//		}
//		
//		System.out.println(s);
		

	}
}
