package action;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

public class main_test {

	public static void main(String[] args) {

		
//		  FileUploadTest ff = new FileUploadTest(); 
//		  ff.fileupload();
//		 
		/*
		 * String str = "KS2017-06-07-247 "; String sss = str.substring(16);
		 * String ss = str.replace("\u00A0", "");
		 * 
		 * System.out.println(ss);
		 */
		/*
		 * int[] str ={1,2,3,4,5,6}; for(int i:str){ if(i==1) continue;
		 * System.out.println(i); }
		 */
		/*
		 * Date time = new Date(); Calendar cal = Calendar.getInstance();
		 * cal.setTime(time); cal.add(Calendar.DATE,-1);
		 * 
		 * SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		 * System.out.println(sf.format(new Date()));
		 */

		/*
		 * String regEx = "sosdcf-\\w{1,}"; Pattern p=Pattern.compile(regEx);
		 * Matcher m=p.matcher("1sosdcf-"); //System.out.println(m.find());
		 * //String str =
		 * "【传输网】【维护优化中部】 Huawei/U2000. LW/GPON/蒙自路-OLT01/00-01-03-GPON口. 主干光纤断或OLT检测不到预期的光信号（LOS）【传输:本地汇聚】"
		 * ; String str =
		 * "【传输网】【北区分公司】 Huawei/U2000. YP/龙泽大厦-ONU0001. 网管与设备通信失败【传输:本地接入】";
		 * String parseAlarmTitle = str.substring( str.indexOf(". ") + 2,
		 * str.lastIndexOf("."));
		 */
		/*
		 * String parseAlarmTitle = str.substring(str.indexOf(". . ") + 4,
		 * str.lastIndexOf("."));
		 */
		/*
		 * String str=
		 * "新增收集字段：用户手机品牌：OPPO;用户手机型号：R7;是否开通长期/短期国漫：长期;详细地址：上海;手动选网时间：" +
		 * "2017-08-17 14:16:57;手动选网时间：2017-08-16 14:16:57;联系电话所属区域：国外;"; String
		 * value = str.substring(str.indexOf("手动选网时间：")+7,str.indexOf(";"));
		 */
		// String ss = value.substring(0, value.indexOf(";"));
		// ss.replace(oldChar, newChar)
		// System.out.println(parseAlarmTitle);

		// FileUploadTest ff = new FileUploadTest();
		// ff.fileupload();
		// /* Map map = ff.getAround("31.15247", "121.34679", "1000");

		// Double log1= Double.parseDouble(map.get("minLat").toString());
		// Double lat1= Double.parseDouble(map.get("minLng").toString());
		// Double log1= Double.parseDouble("121.34679");
		// Double lat1= Double.parseDouble("31.15247");
		// Double log2= Double.parseDouble(map.get("maxLat").toString());
		// Double lat2= Double.parseDouble(map.get("maxLng").toString());
		//
		// System.out.println(map.get("maxLng")+"=="+map.get("maxLat").toString());
		//
		// System.out.println(ff.getDistatce(log1,lat1,log2,lat2));*/
		// double lon1 = 121.448084;
		// double lat1 = 31.128705;
		// double lon2 = 121.432532;
		// double lat2 = 31.118119;
		// System.out.println(main_test.getDistatce(lon1, lat1, lon2, lat2));
		// List<String> list = new ArrayList<String>();
		// list.add("a");
		// list.add("b");
		// list.add("c");
		// list.add("d");

		// StringBuffer name = new StringBuffer();
		// for (int i = 0; i < list.size(); i++) {
		// // Object o[] = (Object[]) list.get(i);
		// name.append(list.get(i));
		// if(list.size()>1 && i<list.size()-1){
		// name.append("|");
		// }
		// }
		//
		// System.out.println(list.get(2)==null?"1":"2");

		// System.out.println((int)Double.parseDouble("21.212"));

		// String[][] str = new String[3][1];
		//
		// str[0][0] = "a";
		// //str[0][1] = "b";
		// System.out.println(str.length+"=="+str[0].length);
		//
		// for (int i = 0; i < 10; i++) {
		// new ThreadTest().run();
		// }

		// String aa = "1";
		// String[] ss = aa.split(",");
		// System.out.println(ss.length);
		// String[] no = {};
		// String[] ss = "13916052679、13989882020".split("、");
		// String str = "198";
		// String value = "1520000".substring(3, 7);
		// //System.out.println(value);
//		 if(str.length()<7){
//		 String aa = str.substring(2,str.length()-2);
//		 System.out.println(aa);
//		 }
		//
		// SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// System.out.println(simp.format(new Date()));

		// DecimalFormat dec = new DecimalFormat("###0.0000");
		// double a1 = 0.26;
		// double a2 = 12.5602;
		// double a3 = 2.7777;
		// double a4 = 1.6;
		// System.out.println(dec.format(a1));
		// System.out.println(dec.format(a2));
		// System.out.println(dec.format(a3));
		// System.out.println(dec.format(a4));

//		String str = "love23next234csdn3423javaeye0123456789../,;12";
//		str = str.trim();
//		
//		StringBuffer str2 = new StringBuffer();
//		if (str != null && !"".equals(str)) {
//			for (int i = 0; i < str.length(); i++) {
//				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) 
//					str2.append(str.charAt(i));
//				else
//					str2.append("/");
//			}
//
//		}
//		System.out.println(str2);
		 //String phone = "15001749225，31148567";
//			String regx = "\\d{8,11}";
//			String result = "";
//			Pattern pattern = Pattern.compile(regx);
//			Matcher matcher = pattern.matcher(phone);
//			while(matcher.find()){
//				String vaule = matcher.group();
//				//System.out.println(vaule);
//				String aa = vaule.replace(vaule.substring(3, 7), "****");
//				phone = phone.replace(vaule, aa);
//			}
		// System.out.println(tuomin(phone));
		 
//		 String timeid = "201711302340";
//		 SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmm");
//		 SimpleDateFormat d2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
//		 try {
//			 
//			Date time = d1.parse(timeid);
//			System.out.println(d2.format(time));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String zhuqianyue = "名称TTTAA；状态：已开通[SCP:8613744，SKEY：18，profie：tesy]";
//		int index = zhuqianyue.indexOf("SKEY");
//		String value = zhuqianyue.substring(index);
//		zhuqianyue = value.substring(5,value.indexOf("，"));
//		 System.out.println(zhuqianyue);
//		String text2 = "<p style='font-size:12px;color:#124388;text-indent: 8mm;'>MGSSP:IMSI=460006043182528,LOCAT;" +
//				"<br>&nbsp;&nbsp;&nbsp;&nbsp;MT MOBILE SUBSCRIBER STATE<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;SUBSCRIBER DETAILS<br>&nbsp;&nbsp;&nbsp;&nbsp;IMSI             MSISDN           STATE    " +
//				"RESTR    LAI<br>&nbsp;&nbsp;&nbsp;&nbsp;460006043182528  8613816055492    IDLE              " +
//				"460-00-6209<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;CGI<br>&nbsp;&nbsp;&nbsp;&nbsp;" +
//				"460-00-6209-60017<br>&nbsp;&nbsp;&nbsp;&nbsp;TMSI<br>&nbsp;&nbsp;&nbsp;&nbsp;H'49AC-H'4A1E<br>&nbsp;&nbsp;" +
//				"&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;EQUIPMENT IDENTITY CONTROL DETAILS<br>&nbsp;&nbsp;&nbsp;&nbsp;IMEI" +
//				"             CHKRES   CNT<br>&nbsp;&nbsp;&nbsp;&nbsp;867778031955550  UNSPEC     0<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;GENERAL PACKET RADIO SERVICE INFORMATION<br>&nbsp;&nbsp;&nbsp;&nbsp;GPRSSTATE      " +
//				"SGSN<br>&nbsp;&nbsp;&nbsp;&nbsp;DET<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;EQUIPMENT IDENTITY DETAILS" +
//				"<br>&nbsp;&nbsp;&nbsp;&nbsp;IMEISV<br>&nbsp;&nbsp;&nbsp;&nbsp;8677780319555520<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;REDISTRIBUTION INITIATED<br>&nbsp;&nbsp;&nbsp;&nbsp;NO<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;" +
//				"&nbsp;&nbsp;&nbsp;END<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<</p>";
//		String text = "MGSSP:IMSI=460028212557201,LOCAT;" +
//				"<br>&nbsp;&nbsp;&nbsp;&nbsp;MT MOBILE SUBSCRIBER STATE<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;" +
//				"SUBSCRIBER DETAILS<br>&nbsp;&nbsp;&nbsp;&nbsp;IMSI             MSISDN           STATE    RESTR    LAI<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;460028212557201  8615821270979    IDLE              460-00-6205<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;CGI<br>&nbsp;&nbsp;&nbsp;&nbsp;UNKNOWN<br>&nbsp;" +
//				"&nbsp;&nbsp;&nbsp;TMSI<br>&nbsp;&nbsp;&nbsp;&nbsp;H'71B4-H'2F16<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;" +
//				"&nbsp;&nbsp;GENERAL PACKET RADIO SERVICE INFORMATION<br>&nbsp;&nbsp;&nbsp;&nbsp;GPRSSTATE      SGSN<br>" +
//				"&nbsp;&nbsp;&nbsp;&nbsp;DET<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;EVOLVED PACKET SYSTEM INFORMATION" +
//				"<br>&nbsp;&nbsp;&nbsp;&nbsp;EPSSTATE       MME<br>&nbsp;&nbsp;&nbsp;&nbsp;ATT            " +
//				"MMECDA.MMEGI0262.MME.EPC.MNC000.MCC460.3GPPNETWORK.ORG<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;" +
//				"&nbsp;&nbsp;EQUIPMENT IDENTITY DETAILS<br>&nbsp;&nbsp;&nbsp;&nbsp;IMEISV<br>&nbsp;&nbsp;&nbsp;&nbsp;" +
//				"3567030820869504<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;REDISTRIBUTION INITIATED<br>&nbsp;" +
//				"&nbsp;&nbsp;&nbsp;NO<br>&nbsp;&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;END<br>&nbsp;&nbsp;&nbsp;&nbsp;" +
//				"<br>&nbsp;&nbsp;&nbsp;&nbsp;";
//		String vaule = text.replaceAll("<br>&nbsp;&nbsp;&nbsp;&nbsp;", "\n").trim();
//		
//		String tmpText = vaule.replaceAll("(\r\n|\r|\n|\n\r)", "GROUP_SPLITTER");
//		//System.out.println(tmpText);
//		String[] lines = tmpText.split("GROUP_SPLITTER");
//		if (lines!=null){
//			for(int i=0;i<lines.length;i++){
//				if (lines[i]!=null && lines[i].trim().length()>0){
//					//System.out.println(lines[i].trim());
//					if (lines[i].indexOf("LAI")!=-1){
//						String[] values = lines[i+1].split("\\s+");
//						//m.put("lai", values[values.length-1]);
//						//System.out.println(values[values.length-1]);
//						continue;
//					}
//					if (lines[i].indexOf("CGI")!=-1){
//						String[] values = lines[i+1].split("\\s+");
//						//m.put("lai", values[values.length-1]);
//						//System.out.println(values[0]);
//						continue;
//					}
//				}
//			}
//		}
//		String ttt = "460-00-6205";
//		System.out.println(ttt.substring(ttt.lastIndexOf("-")).substring(1));
//		String zhuqianyue = "名称: TTTAA; 状态: 已开通 [ SCP: 8613740163, SKEY: 18 , Profile: TCSI18-1,</br>网元名称: SHVOLTEAS2（VoLTE SCCAS） ]";
//		String zhuSK = "";
//		if(zhuqianyue.indexOf("SKEY")!=-1){
//			int index = zhuqianyue.indexOf("SKEY");
//			String value = zhuqianyue.substring(index);
//			if(value.indexOf(",")!=-1)
//				zhuSK = value.substring(5,value.indexOf(","));
//			else
//				zhuSK = value;
//		}
//		System.out.println(zhuSK);
		
//		String ss = "国际业务投诉||查HLR中用户IMSI号码：460004370624528，HLR名称：HLR47，VLR号码：8613440468，MSC号码：MSC67，" +
//				"SGSN名称：，GSM漫游权限：国内漫游/国际漫游，主被叫信息：系统级：主叫开通,被叫开通,国际长途开通;，呼叫限制：呼入限制：未开通，呼出限制：  如<font color='red'>未开通</font>，国际长途限制：未开通，请处理。<br>";
//		String subStr = ss.replaceAll("<[^>]*>", "");  
//		System.out.println(subStr);
		
//		long expireTime = 3*365*24*60*60*1000;
//		System.out.println(expireTime);
//		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String fromDate = "2012-05-01 12:00:00";  
//		String toDate = "2017-06-01 12:00:00";  
//		
//		try {
//			long from = simpleFormat.parse(fromDate).getTime();  
//			long to = simpleFormat.parse(toDate).getTime();  
//			int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
//			System.out.println(days);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		//String s = "2018-01-09 00:00:00";
//		String s = "2017-12-03 21:52:21";
//		Long timeL = Long.parseLong(s);
//		System.out.println(timeL);
		
		String ss = "((121.32188422309028 31.162255588107637, 121.32218234592014 31.161808810763887, 121.32220974392362 31.161778428819442, 121.32223253038195 31.161756184895832, 121.32228108723957 31.16171902126736, 121.32240044487847 31.16165608723958))";
		System.out.println(ss.replaceAll("[((]", ""));

	}
	
	public static String tuomin(String mobileNo){
		String phone = mobileNo;
		String regx = "\\d{8,11}";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(phone);
		while(matcher.find()){
			String vaule = matcher.group();
			String result = vaule.replace(vaule.substring(3, 7), "****");
			phone = phone.replace(vaule, result);
		}
		return phone;
	}

	public static double getDistatce(double lon1, double lat1, double lon2, double lat2) {
		double R = 6378.137; // 地球半径
		double distance = 0.0;
		double dLat = (lat2 - lat1) * Math.PI / 180;
		double dLon = (lon2 - lon1) * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
		return distance * 1000;
	}

}
