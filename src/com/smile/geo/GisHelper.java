package com.smile.geo;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GisHelper {

	public final static DecimalFormat LAT_DECF = new DecimalFormat("##.######");
	public final static DecimalFormat LNG_DECF = new DecimalFormat("###.######");
	public static double X_EACH_WIDTH_1000 = 0.01042792345332; // 经度1000m每格长度
	public static double Y_EACH_WIDTH_1000 = 0.00898316074327; // 纬度1000m每格长度
	public static String Bounds = "119.94838327226432,30.516509889084887,123.02656522880216,32.28363652648602";
	/**
	 * 实际上海边界
	 */
	public static String Bounds_sh = "120.838909,30.676004,122.070241,31.90808";
	public static String[] replaceList1 = new String[] { "弄", "号" };
	public static String[] replaceList2 = new String[] { "为", "在", ":", "反映",
			"：", " ", "映", ";", "；", "区", "称", "起", "（", "表示", "县", "上海市", "靠近" };
	public static String[] replaceList3 = new String[] { "浦东", "徐汇", "长宁",
			"闵行", "黄浦", "宝山", "杨浦", "虹口", "静安", "普陀", "闸北", "嘉定", "金山", "松江",
			"青浦", "奉贤", "崇明", "卢湾", "南汇", "上海" };
	public static String[] xzqDataList = new String[] { "青浦区", "松江区", "金山区",
			"嘉定区", "奉贤区", "闵行区", "长宁区", "黄浦区", "静安区", "徐汇区", "闸北区", "普陀区",
			"宝山区", "南汇区", "崇明县", "浦东新区", "虹口区", "杨浦区", "金泽镇", "西岑镇", "商塌镇",
			"枫泾镇", "兴塔镇", "石湖荡镇", "新浜镇", "练塘镇", "青浦镇", "朱家角镇", "杜村镇", "赵屯镇",
			"山塘镇", "干巷镇", "廊下镇", "钱圩镇", "东新镇", "西新镇", "新农镇", "吕巷镇", "朱迳镇",
			"松隐镇", "泖港镇", "小昆山镇", "佘山镇", "赵巷镇", "重固镇", "华新镇", "白鹤镇", "黄渡镇",
			"安亭镇", "外冈镇", "朱家桥镇", "唐行镇", "华亭镇", "娄塘镇", "堡镇", "绿华镇", "金山卫镇",
			"山阳镇", "张堰镇", "朱行镇", "叶榭镇", "马桥镇", "车墩镇", "新桥镇", "七宝镇", "莘庄镇",
			"洞泾镇", "泗泾镇", "九亭镇", "华漕镇", "长征镇", "徐泾镇", "新泾镇", "桃浦镇", "江桥镇",
			"南翔镇", "封浜镇", "马陆镇", "戬浜镇", "曹王镇", "罗泾镇", "罗店镇", "徐行镇", "华亭镇",
			"三星镇", "庙镇", "凤阳镇", "??缺村镇", "柘林镇", "胡桥镇", "漕泾镇", "新寺镇", "江海镇",
			"庄行镇", "南桥镇", "西渡镇", "邬桥镇", "金汇镇", "吴泾镇", "颛桥镇", "华泾镇", "三林镇",
			"梅陇镇", "长征镇", "虹桥镇", "高境镇", "庙行镇", "江湾镇", "大场镇", "彭浦镇", "杨行镇",
			"顾村镇", "凇南镇", "月浦镇", "浜镇", "建设镇", "城桥镇", "港西镇", "奉新镇", "光明镇",
			"塘外镇", "钱桥镇", "梁典镇", "泰日镇", "齐贤镇", "青村镇", "下沙镇", "航头镇", "周浦镇",
			"康桥镇", "浦江镇", "秦家镇", "六里镇", "钦洋镇", "北蔡镇", "花木镇", "张江镇", "五角场镇",
			"东沟镇", "金桥镇", "高行镇", "高东镇", "草镇", "高桥镇", "东兴镇", "堡镇", "新河镇", "竖新镇",
			"朱新镇", "平安镇", "奉城镇", "大团镇", "头桥镇", "四团镇", "洪庙镇", "新场镇", "惠南镇",
			"三灶镇", "宣桥镇", "坦直镇", "瓦屑镇", "六灶镇", "东城镇", "合庆镇", "机场镇", "唐镇",
			"川沙新镇", "孙桥镇", "曹路镇", "向化镇", "港沿镇", "芦潮港镇", "邵厂镇", "彭镇镇", "泥城镇",
			"北窑镇", "书院镇", "万祥镇", "新港镇", "三墩镇", "老港镇", "黄路镇", "祝桥镇", "盐仓镇",
			"东海镇", "陈家镇", "中兴镇", "汇南镇", "横沔镇", "王港镇", "川沙镇", "杨园镇", "龚路镇",
			"纪王镇", "六团镇", "朱泾镇", "侯家镇", "凤溪镇", "茸北镇", "新华镇", "方泰镇", "黄楼镇",
			"杨思镇", "蔡路镇", "余山镇", "叶谢镇" };

	/**
	 * GIS地图外环线点集
	 */
	public static final String waihuanxian = "121.383427,31.120890; 121.404554,31.120521; 121.424602, 31.122549; 121.441848, 31.125498; 121.453058, 31.125867;"
			+ "121.456723, 31.126420; 121.471598, 31.132134; 121.490353, 31.133977; 121.519887, 31.138769; 121.534546, 31.141350;"
			+ "121.556318, 31.146327; 121.573133, 31.147433; 121.586283, 31.149092; 121.594906, 31.150382; 121.606763, 31.153516;"
			+ "121.632416, 31.157018; 121.643195, 31.157470; 121.642979, 31.168263; 121.642764, 31.182653; 121.642225, 31.188741;"
			+ "121.639961, 31.207098; 121.639961, 31.210788; 121.639422, 31.227854; 121.639746, 31.252853; 121.639638, 31.285416;"
			+ "121.639853, 31.312260; 121.639530, 31.314566; 121.638452, 31.316226; 121.637374, 31.317241; 121.637374, 31.317887;"
			+ "121.635650, 31.318994; 121.605361, 31.343900; 121.589625, 31.354970; 121.545109, 31.370967; 121.537564, 31.373544;"
			+ "121.528509, 31.375385; 121.498329, 31.378699; 121.495742, 31.377963; 121.489921, 31.375754; 121.481730, 31.372624;"
			+ "121.479789, 31.372071; 121.450687, 31.363786; 121.433872, 31.358447; 121.430854, 31.357343; 121.413824, 31.353844;"
			+ "121.409297, 31.352187; 121.393128, 31.348137; 121.388601, 31.347401; 121.383427, 31.344455; 121.377823, 31.342061;"
			+ "121.375667, 31.340588; 121.368122, 31.338195; 121.361223, 31.335801; 121.357774, 31.334697; 121.356696, 31.332856;"
			+ "121.356049, 31.330646; 121.355834, 31.328437; 121.357343, 31.326228; 121.359498,31.321993;121.360361,31.318863;"
			+ "121.360145,31.315733; 121.359498,31.310210; 121.357127,31.298427; 121.353893,31.290694;121.354109,31.287012;"
			+ "121.355187,31.278359; 121.355618,31.271178; 121.354971,31.268416; 121.353462,31.264550; 121.348073,31.252767;"
			+ "121.345702,31.248349; 121.344193,31.245219; 121.343330,31.241352; 121.342468,31.237854; 121.343115,31.227912;"
			+ "121.344839,31.221468; 121.348935,31.213367; 121.351307,31.202320; 121.353031,31.188144;"
			+ "121.356696,31.175256; 121.362085,31.159606; 121.377176,31.132174; 121.383265,31.120881";

	/**
	 * GIS地图中环线点集
	 */
	public static final String zhonghuanxian = "121.541249,31.308992;121.540493,31.302161;121.545570,31.292930;121.552051,31.288222;"
			+ "121.555832,31.276222;121.564150,31.271053;121.567066,31.267730;121.569443,31.265053;121.570739,31.263114;"
			+ "121.571927,31.261822;121.573332,31.260622;121.575600,31.259329;121.577328,31.258683;121.579813,31.258037;"
			+ "121.590183,31.248899;121.593099,31.245945;121.600661,31.236068;121.601309,31.234499;121.601309,31.231914;"
			+ "121.601309,31.230622;121.602497,31.226837;121.608870,31.209575;121.609842,31.208375;121.620969,31.198221;"
			+ "121.622697,31.195821;121.626262,31.188528;121.617836,31.184497;121.614163,31.182926;121.610166,31.180523;"
			+ "121.605630,31.178768;121.602281,31.177474;121.599148,31.176365;121.598068,31.175995;121.588346,31.173962;"
			+ "121.586402,31.173408;121.583593,31.172576;121.572251,31.167864;121.565122,31.165738;121.558964,31.165184;"
			+ "121.556696,31.164907;121.551511,31.164168;121.544274,31.162227;121.520509,31.154650;121.510139,31.151416;"
			+ "121.484539,31.149845;121.481190,31.149383;121.458722,31.146241;121.457102,31.145823;121.452889,31.145638;"
			+ "121.450188,31.145454;121.446516,31.144806;121.443923,31.143974;121.441331,31.143142;121.436038,31.140369;"
			+ "121.427936,31.138243;121.425560,31.137688;121.423831,31.136856;121.420051,31.136301;121.417134,31.135007;"
			+ "121.414001,31.133575;121.409465,31.138289;121.407304,31.142450;121.407088,31.142450;121.397798,31.164174;"
			+ "121.393694,31.171201;121.392721,31.174159;121.390345,31.182849;121.387536,31.190244;121.383540,31.196346;"
			+ "121.379975,31.205498;121.373062,31.212062;121.371441,31.214465;121.371441,31.219088;121.371657,31.226853;"
			+ "121.374898,31.233139;121.376842,31.235451;121.377706,31.236837;121.378787,31.238132;121.378787,31.239518;"
			+ "121.384296,31.248948;121.386456,31.253385;121.387320,31.256159;121.388401,31.270950;121.400499,31.289011;"
			+ "121.418538,31.290027;121.432473,31.292150;121.445651,31.294458;121.454725,31.296673;121.456129,31.296673;"
			+ "121.460450,31.295196;121.466391,31.293350;121.472981,31.291319;121.479570,31.292796;121.485187,31.294366;"
			+ "121.489076,31.295750;121.495881,31.297412;121.504739,31.299904;121.511652,31.302397;121.518349,31.304335;"
			+ "121.530393,31.308004;121.533904,31.308973;121.535848,31.309388;121.536929,31.309481;121.539737,31.309296;"
			+ "121.541303,31.309019";

	/**
	 * GIS地图内环线点集
	 */
	public static final String neihuanxian = "121.570415,31.210800;121.567066,31.225120;121.565878,31.229925;121.562313,31.238055;"
			+ "121.560369,31.241935;121.554860,31.247848;121.550647,31.250805;121.546002,31.253576;121.535848,31.261614;"
			+ "121.529691,31.270484;121.526558,31.274826;121.524506,31.276858;121.521373,31.279815;121.517809,31.287206;"
			+ "121.516620,31.288222;121.513704,31.287853;121.511652,31.287206;121.509599,31.286744;121.507763,31.286836;"
			+ "121.505926,31.287391;121.503226,31.288407;121.499985,31.289146;121.497069,31.289516;121.496097,31.289516;"
			+ "121.494962,31.289160;121.493288,31.287913;121.491938,31.287729;121.489831,31.287636;121.488265,31.287729;"
			+ "121.479138,31.287682;121.478435,31.285452;121.478381,31.283236;121.478327,31.282082;121.477733,31.281067;"
			+ "121.477409,31.280605;121.476383,31.280097;121.475249,31.279590;121.474492,31.278943;121.474006,31.278205;"
			+ "121.473736,31.277605;121.473520,31.276820;121.473250,31.276035;121.472980,31.275112;121.472548,31.274327;"
			+ "121.472278,31.273681;121.471792,31.272942;121.470712,31.271419;121.469362,31.270126;121.468605,31.269618;"
			+ "121.467687,31.269064;121.467039,31.268280;121.466661,31.267680;121.465581,31.266941;121.464987,31.266249;"
			+ "121.462988,31.265187;121.461206,31.264356;121.456939,31.262694;121.441924,31.259604;121.438468,31.258403;"
			+ "121.434903,31.257018;121.431770,31.255725;121.431122,31.255263;121.429394,31.253417;121.427990,31.252032;"
			+ "121.425289,31.249538;121.424101,31.248338;121.422049,31.247507;121.419780,31.246214;121.416864,31.244737;"
			+ "121.412435,31.242243;121.410383,31.240304;121.406494,31.235225;121.405198,31.231439;121.406386,31.227653;"
			+ "121.405954,31.219250;121.408222,31.213987;121.408222,31.211863;121.407142,31.207707;121.408546,31.202352;"
			+ "121.411463,31.197735;121.418484,31.188628;121.419888,31.186226;121.421076,31.184840;121.423345,31.183916;"
			+ "121.425721,31.183362;121.429394,31.182530;121.431770,31.181698;121.434147,31.180405;121.436523,31.179943;"
			+ "121.439548,31.182253;121.441924,31.183546;121.444193,31.185671;121.446353,31.187150;121.450134,31.188351;"
			+ "121.452834,31.189090;121.454563,31.189922;121.458235,31.192416;121.461260,31.194541;121.464717,31.195927;"
			+ "121.468605,31.197775;121.473466,31.199346;121.481136,31.202026;121.485349,31.203596;121.487185,31.204890;"
			+ "121.488913,31.205906;121.491398,31.207569;121.494315,31.209510;121.495071,31.209972;121.499175,31.208493;"
			+ "121.506305,31.205629;121.510194,31.204428;121.525856,31.200685;121.530177,31.199762;121.533850,31.199207;"
			+ "121.537414,31.199762;121.542059,31.201332;121.545840,31.203365;121.550377,31.205767;121.559235,31.206045;"
			+ "121.562259,31.206691;121.564609,31.207927;121.566202,31.208689;121.567714,31.209428;121.570415,31.210791";

	public static class Point {
		private Double longitude;
		private Double latitude;

		public Point(Double longitude, Double latitude) {
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
	}

	static class City {
		String id;
		String textLabel;
		String points;

		City(String id, String textLabel, String points) {
			this.id = id;
			this.textLabel = textLabel;
			this.points = points;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTextLabel() {
			return textLabel;
		}

		public void setTextLabel(String textLabel) {
			this.textLabel = textLabel;
		}

		public String getPoints() {
			return points;
		}

		public void setPoints(String points) {
			this.points = points;
		}
	}

	/**
	 * 解析边界点字符串
	 * 
	 * @param point
	 * @return
	 */
	public static List<Point> parsePoints(String point) {
		List<Point> rslt = new ArrayList<Point>();
		if (point == null || point.equals(""))
			return rslt;
		if (point.indexOf(";") > -1) {
			String[] points = point.trim().split(";");
			if (points.length < 4)
				return rslt;
			for (String p : points) {
				if (ToolKit.isNullOrBlank(p))
					continue;
				if (p.indexOf(",") > -1) {
					String[] pp = p.split(",");
					if (pp.length == 2) {
						rslt.add(new Point(Double.parseDouble(pp[0]), Double
								.parseDouble(pp[1])));
					}
				}
			}
		}
		if (rslt == null || rslt.isEmpty() || rslt.size() < 4) {
			return null;
		}
		return rslt;
	}

	/**
	 * 判断点是否在多边形内
	 * 
	 * @param pt
	 * @param point
	 * @return
	 */
	public static boolean parsePointData(Double longitude, Double latitude,
			List<Point> pointList) {

		Point pt = new Point(longitude, latitude);
		boolean rslt = false;
		if (pt.getLatitude() == null || pt.getLongitude() == null
				|| pt.getLatitude() == 0 || pt.getLongitude() == 0)
			return rslt;
		int intSum = 0;
		int count = pointList.size(); // 多边形的顶点数
		double y0, y1, x0, x1;
		double x;
		for (int i = 0; i < count; i++) {
			if (pointList.get(i).getLongitude() == pt.getLongitude()
					&& pointList.get(i).getLatitude() == pt.getLatitude()) // 刚好点是多边形顶点的情况
				return true;
			if (i == count - 1) {
				x0 = pointList.get(count - 1).getLongitude();
				y0 = pointList.get(count - 1).getLatitude();
				// x0=pointList.get(i).getLongitude();
				// y0=pointList.get(i).getLatitude();
				x1 = pointList.get(0).getLongitude();
				y1 = pointList.get(0).getLatitude();
			} else {
				x0 = pointList.get(i).getLongitude();
				y0 = pointList.get(i).getLatitude();
				x1 = pointList.get(i + 1).getLongitude();
				y1 = pointList.get(i + 1).getLatitude();
			}

			if ((pt.getLatitude() > y0 && pt.getLatitude() < y1)
					|| (pt.getLatitude() > y1 && pt.getLatitude() < y0)) {
				// if ((pt.getLatitude() >= y0 && pt.getLatitude() < y1)
				// || (pt.getLatitude() >= y1 && pt.getLatitude() < y0)) {
				if (Math.abs(y0 - y1) > 0) {
					x = x0 - (x0 - x1) * (y0 - pt.getLatitude()) / (y0 - y1);
					if (x < pt.getLongitude())
						intSum++;
				}
			}
		}
		if (intSum % 2 == 1)
			rslt = true; // 总和为奇数

		return rslt;
	}
	public static boolean parsePointData(Point pt,
			List<Point> pointList) {
		
//		Point pt = new Point(longitude, latitude);
		boolean rslt = false;
		if (pt.getLatitude() == null || pt.getLongitude() == null
				|| pt.getLatitude() == 0 || pt.getLongitude() == 0)
			return rslt;
		int intSum = 0;
		int count = pointList.size(); // 多边形的顶点数
		double y0, y1, x0, x1;
		double x;
		for (int i = 0; i < count; i++) {
			if (pointList.get(i).getLongitude() == pt.getLongitude()
					&& pointList.get(i).getLatitude() == pt.getLatitude()) // 刚好点是多边形顶点的情况
				return true;
			if (i == count - 1) {
				x0 = pointList.get(count - 1).getLongitude();
				y0 = pointList.get(count - 1).getLatitude();
				// x0=pointList.get(i).getLongitude();
				// y0=pointList.get(i).getLatitude();
				x1 = pointList.get(0).getLongitude();
				y1 = pointList.get(0).getLatitude();
			} else {
				x0 = pointList.get(i).getLongitude();
				y0 = pointList.get(i).getLatitude();
				x1 = pointList.get(i + 1).getLongitude();
				y1 = pointList.get(i + 1).getLatitude();
			}
			
			if ((pt.getLatitude() > y0 && pt.getLatitude() < y1)
					|| (pt.getLatitude() > y1 && pt.getLatitude() < y0)) {
				// if ((pt.getLatitude() >= y0 && pt.getLatitude() < y1)
				// || (pt.getLatitude() >= y1 && pt.getLatitude() < y0)) {
				if (Math.abs(y0 - y1) > 0) {
					x = x0 - (x0 - x1) * (y0 - pt.getLatitude()) / (y0 - y1);
					if (x < pt.getLongitude())
						intSum++;
				}
			}
		}
		if (intSum % 2 == 1)
			rslt = true; // 总和为奇数
		
		return rslt;
	}

	/**
	 * 2G3G获得基站覆盖半径
	 * 
	 * @param lontitude
	 * @param latitude
	 * @return
	 */
	public static double getRadius(Double lontitude, Double latitude) {
		if (getDistrict(neihuanxian, lontitude, latitude))
			return 256;// 环内
		if (getDistrict(zhonghuanxian, lontitude, latitude))
			return 307;// 环间
		if (getDistrict(waihuanxian, lontitude, latitude))
			return 390;// 环外
		return 0;
	}

	/**
	 * 4G获得基站覆盖半径--zhangM
	 * 
	 * @param status
	 *            入网状态
	 * @param district
	 *            区域(浦东/浦西)
	 * @param pinduan
	 *            频段(D频段/F频段)
	 * @param temp
	 *            是否在内环线
	 * @param temp2
	 *            是否在中环线
	 * @param temp3
	 *            是否在外环线
	 * @return
	 */
	public static int getBanJinBy4G(String status, String district,
			String pinduan, boolean temp, boolean temp2, boolean temp3) {
		int max = 0;
		if ("在网".equals(status)) {
			if ("浦东".equals(district)) {
				if ("D频段".equals(pinduan)) {
					if (temp) {
						max = 170;
					} else {
						if (temp2 || temp3) {
							max = 210;
						} else {
							max = 270;
						}
					}
				} else if ("F频段".equals(pinduan)) {
					if (temp) {
						max = 270;
					} else {
						if (temp2 || temp3) {
							max = 310;
						} else {
							max = 360;
						}
					}
				}
			} else if ("浦西".equals(district)) {
				if ("D频段".equals(pinduan)) {
					if (temp2) {
						max = 170;
					} else {
						if (temp3) {
							max = 210;
						} else {
							max = 270;
						}
					}
				} else if ("F频段".equals(pinduan)) {
					if (temp2) {
						max = 270;
					} else {
						if (temp3) {
							max = 310;
						} else {
							max = 360;
						}
					}
				}
			}
		} else if ("工程".equals(status)) {// 生命周期状态=工程
			max = 170;// 固定D频段-半径固定为170
		}
		return max;
	}

	// 根据得到基站信息，判断此基站是否在环线内
	public static boolean getDistrict(String points, Double lontitude,
			Double latitude) {
		boolean flag = false;
		List<Point> pointList = GisHelper.parsePoints(points);
		if (pointList != null) {
			boolean b = GisHelper
					.parsePointData(lontitude, latitude, pointList);
			if (b) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 估计经纬度计算距离(单位:m)
	 * 
	 * @param lon1_s
	 * @param lat1_s
	 * @param lon2_s
	 * @param lat2_s
	 * @return
	 */
	public static double getDistatce(double lon1, double lat1, double lon2,
			double lat2) {
		double R = 6378.137; // 地球半径
		double distance = 0.0;
		double dLat = (lat2 - lat1) * Math.PI / 180;
		double dLon = (lon2 - lon1) * Math.PI / 180;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(lat1 * Math.PI / 180)
				* Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
		return distance * 1000;
	}



	


	public static String QtoB(String input) {

		char c[] = input.toCharArray();

		for (int i = 0; i < c.length; i++) {

			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}

		return new String(c);

	}



	public static String getAddress(String region_name) {
		if ("浦东新区".equals(region_name) || "浦东".equals(region_name)
				|| "南汇".equals(region_name) || "南汇(原)".equals(region_name)
				|| "南汇（原）".equals(region_name) || "金山".equals(region_name)
				|| "奉贤".equals(region_name) || "崇明".equals(region_name)) {
			return "浦东";
		}
		return "浦西";
	}
	
	public static String filterLocation(String location, int flag) {
		// location="121沪闵路7ASD弄100支弄";
		int t = -1;
		for (int i = 0; i < location.length(); i++) {
			if (!location.substring(i, i + 1).matches("[0-9a-zA-Z\\-]")) {
				t = i;
				break;
			}
		}
		if (t == -1)
			return location;
		int t1 = 0;
		for (int i = t; i < location.length(); i++) {
			if (location.substring(i, i + 1).matches("[0-9a-zA-Z\\-]")) {
				t1 = i;
				break;
			}
		}

		if (t1 > 0) {

			if (flag == 1) {
				String location_t = location.substring(t1);
				int t2 = 0;
				for (int i = 0; i < location_t.length(); i++) {
					if (!location_t.substring(i, i + 1).matches(
							"[0-9a-zA-Z\\-]")) {
						t2 = i;
						break;
					}
				}
				if (t2 == 0)
					t2 = location_t.length();
				if(location.length()>t1+t2)
					location = location.substring(0, t1 + t2+1);
				else
					location = location.substring(0, t1 + t2);
			} else if (flag == 2) {
				location = location.substring(t, t1);
			}

		}
		return location;
	}

	

	public static void main(String[] args) throws Exception {}

}
