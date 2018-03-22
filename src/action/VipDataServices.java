package action;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.eastcomsw.util.ToolKit;

import dao.VipDataDaoImpl;

import entity.DoublePoint;
import entity.GisBaseStation;
import entity.GisGsmBqzInfo;
import entity.GisHelper;
import entity.GisHelper.Point;
import entity.GisWeakCover;

public class VipDataServices {
	
	private static VipDataDaoImpl vipDao = new VipDataDaoImpl();
	//关联基站 
	public List<GisBaseStation> getBaseInfo(String latAndlon){
		long lStart = Calendar.getInstance().getTimeInMillis();
		List<GisBaseStation> result = new ArrayList<GisBaseStation>();
		List<Point> pointList = new ArrayList<Point>();
		String[] array = latAndlon.split(",");
		for (int i = 0; i < array.length; i++) {
			if (!ToolKit.isNullOrBlank(array[i])) {
				Point p = new Point(Double.parseDouble(array[i].split("-")[0]), Double.parseDouble(array[i].split("-")[1]));
				pointList.add(p);
			}
		}
		String bbox = this.lonAdnLatRange(array);
		List<GisBaseStation> result2G = vipDao.getBaseInfo(bbox, "2G", pointList);
		if(result2G!=null){
			System.out.println("关联到2G基站个数："+result2G.size());
			result.addAll(result2G);
		}
		List<GisBaseStation> result4G = vipDao.getBaseInfo(bbox, "4G", pointList);
		if(result4G!=null){
			System.out.println("关联到4G基站个数："+result4G.size());
			result.addAll(result4G);
		}
		long lEnd = Calendar.getInstance().getTimeInMillis();
		int interval = (int) (lEnd - lStart)/1000;
		System.out.println("关联基站用时：" + interval);
		System.out.println("基站关联个数：" + result.size());
		return result;
	}
	
	//关联若覆盖
	public List<GisWeakCover> searchWeakCoverByArea(String latAndlon){
		long lStart = Calendar.getInstance().getTimeInMillis();
		List<GisWeakCover> result = new ArrayList<GisWeakCover>();
		List<Point> pointList = new ArrayList<Point>();
		String[] array = latAndlon.split(",");
		for (int i = 0; i < array.length; i++) {
			if (!ToolKit.isNullOrBlank(array[i])) {
				Point p = new Point(Double.parseDouble(array[i].split("-")[0]), Double.parseDouble(array[i].split("-")[1]));
				pointList.add(p);
			}
		}
		String bbox = this.lonAdnLatRange(array);
		String minLat = bbox.split(",")[1];
		String maxLat = bbox.split(",")[3];
		String minLng = bbox.split(",")[0];
		String maxLng = bbox.split(",")[2];
		
		List<GisWeakCover> query = vipDao.searchWeakCoverByArea(new DoublePoint(Double.parseDouble(minLng), Double.parseDouble(minLat)), new DoublePoint(Double.parseDouble(maxLng), Double.parseDouble(maxLat)));
		System.out.println("关联退服站未判断是否在多边形之前弱覆盖基站个数："+query.size());
		if (query != null) {
			for (GisWeakCover weak : query) {
				System.out.println("判断基站：" + weak.getArea() + ",是否在多边形中");
				if (GisHelper.parsePointData(weak.getLongitude(), weak.getLatitude(), pointList)) {
					System.out.println("基站：" + weak.getArea() + ",在多边形中.....");
					if("0".equals(this.ifClosed(weak, 1))){
						result.add(weak);
					}
				}
			}
		}
		long lEnd = Calendar.getInstance().getTimeInMillis();
		int interval = (int) (lEnd - lStart)/1000;
		System.out.println("关联弱覆盖用时：" + interval);
		System.out.println("弱覆盖关联个数：" + result.size());
		return result;
	}
	
	//关联退服站
	public List<GisGsmBqzInfo> serchTfStation(String latAndlon){
		long lStart = Calendar.getInstance().getTimeInMillis();
		List<GisGsmBqzInfo> gisBqzInfo = new ArrayList<GisGsmBqzInfo>();
		List<Point> pointList = new ArrayList<Point>();
		String[] array = latAndlon.split(",");
		for (int i = 0; i < array.length; i++) {
			if (!ToolKit.isNullOrBlank(array[i])) {
				Point p = new Point(Double.parseDouble(array[i].split("-")[0]), Double.parseDouble(array[i].split("-")[1]));
				pointList.add(p);
			}
		}
		String bbox = this.lonAdnLatRange(array);
		String minLat = bbox.split(",")[1];
		String maxLat = bbox.split(",")[3];
		String minLng = bbox.split(",")[0];
		String maxLng = bbox.split(",")[2];
		
		// 永久退服搬迁站
		List<GisGsmBqzInfo> resultYj2g = vipDao.get(new GisGsmBqzInfo(Double.parseDouble(minLng), Double.parseDouble(minLat), Double.parseDouble(maxLng), Double.parseDouble(maxLat), "", "1", "0"),"2G");
		// 临时退服搬迁站
		List<GisGsmBqzInfo> resultLs2g = vipDao.get(new GisGsmBqzInfo(Double.parseDouble(minLng), Double.parseDouble(minLat), Double.parseDouble(maxLng), Double.parseDouble(maxLat), "", "0", "0"),"2G");
		// 永久退服搬迁站
		List<GisGsmBqzInfo> resultYj4g = vipDao.get(new GisGsmBqzInfo(Double.parseDouble(minLng), Double.parseDouble(minLat), Double.parseDouble(maxLng), Double.parseDouble(maxLat), "", "1", "0"),"4G");
		// 临时退服搬迁站
		List<GisGsmBqzInfo> resultLs4g = vipDao.get(new GisGsmBqzInfo(Double.parseDouble(minLng), Double.parseDouble(minLat), Double.parseDouble(maxLng), Double.parseDouble(maxLat), "", "0", "0"),"4G");
		
		gisBqzInfo.addAll(this.judeTf(resultYj2g, pointList));
		gisBqzInfo.addAll(this.judeTf(resultLs2g, pointList));
		gisBqzInfo.addAll(this.judeTf(resultYj4g, pointList));
		gisBqzInfo.addAll(this.judeTf(resultLs4g, pointList));
		
		long lEnd = Calendar.getInstance().getTimeInMillis();
		int interval = (int) (lEnd - lStart)/1000;
		System.out.println("关联退服站用时：" + interval);
//		log.info("永久退服搬迁站(2g):" + resultYj2g.size());
//		log.info("临时退服搬迁站(2g):" + resultLs2g.size());
//		log.info("永久退服搬迁站(4g):" + resultYj4g.size());
//		log.info("临时退服搬迁站(4g):" + resultLs4g.size());
		//log.info("覆盖范围内的退服站(临时+永久):" + gisBqzInfo.size());
		System.out.println("退服站关联个数：" + gisBqzInfo.size());
		return gisBqzInfo;
	}
	
	
	private List<GisGsmBqzInfo> judeTf(List<GisGsmBqzInfo> result,List<Point> pointList){
		List<GisGsmBqzInfo> newResult = new ArrayList<GisGsmBqzInfo>();
		if(result==null || result.size()<1){
			return newResult;
		}
		for (GisGsmBqzInfo g : result) {
			if (g != null) {
				if (GisHelper.parsePointData(g.getLongitude(), g.getLatitude(), pointList)) {
					if("0".equals(this.ifClosed(g, 10))){
						g.setIfClosed("在用中");// 判断口径应用状态
						newResult.add(g);
					}
				}
			}
		}
		return newResult;
	}
	
	public String judeIfClose(String type) {
		if (type != null) {
			if ("0".equals(type)) {
				return "在用";
			} else if ("1".equals(type)) {
				return "正常关闭";
			} else if ("2".equals(type)) {
				return "过期关闭";
			} else {
				return type;
			}
		} else {
			return type;
		}
	}
	
	private String ifClosed(Object object, int flag) {
		// DateFormat df_d = new SimpleDateFormat("yyyy-MM-dd");
		// Date today = new Date();
		// Date lastMonth = new Date(today.getYear(), today.getMonth() - 1, 16,
		// 0,
		// 0, 0);
		// Date firstDay = new Date(today.getYear(), today.getMonth(), 1, 0, 0,
		// 0);
		/*
		 * if (flag == 0) { GisGhzInfo gisGhzInfo = (GisGhzInfo) object; String
		 * result = GisIfClosedAction.ghzIfClosed( gisGhzInfo.getUpdateTime(),
		 * gisGhzInfo.getExpectNtime(), gisGhzInfo.getProgressType()); return
		 * result; } else
		 */if (flag == 1) {
			GisWeakCover gisWeakCover = (GisWeakCover) object;
			String result = top100IfClosed(gisWeakCover.getEnsure_time(), gisWeakCover.getProjectType(), gisWeakCover.getProjectProgress(), gisWeakCover.getProjectOvertime(), gisWeakCover.getEnsure_time_tab(), gisWeakCover.getProjectType_tab(), gisWeakCover.getProjectProgress_tab(), gisWeakCover.getProjectOvertime_tab());
			return result;
		} else if (flag == 10) {
			GisGsmBqzInfo gisGsmBqz = (GisGsmBqzInfo) object;
			String result = bqzIfClosed(gisGsmBqz.getUpdateTime(), gisGsmBqz.getExpectNtime(), gisGsmBqz.getProgressType(), gisGsmBqz.getGhzType());
			return result;
		}

		return "0";
	}
	
	public static String bqzIfClosed(String updateTime, String expectNtime, String progressType, String ghzType) {
		DateFormat df_d = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date today = new Date();
			Date lastMonth = new Date(today.getYear(), today.getMonth() - 1, 16, 0, 0, 0);
			Date firstDay = new Date(today.getYear(), today.getMonth(), 1, 0, 0, 0);

			if ("1".equals(ghzType) || "2".equals(ghzType)) {// 1：为室外永久退服类型2：室内退服
				if (!"3".equals(progressType)) {
					if (!ToolKit.isNullOrBlank(expectNtime) && df_d.parse(expectNtime).before(df_d.parse(df_d.format(new Date())))) {
						return "2";
					} else if (ToolKit.isNullOrBlank(expectNtime)) {// 预计入网时间为空，表示工程还未开始，作为暂关闭口径
						return "2";
					}
				}
			} else {
				if (!ToolKit.isNullOrBlank(expectNtime) && df_d.parse(expectNtime).before(df_d.parse(df_d.format(new Date())))) {
					return "2";
				} else if (ToolKit.isNullOrBlank(expectNtime)) {// 预计入网时间为空，表示工程还未开始，作为暂关闭口径
					return "2";
				}
			}

			if (today.getDate() <= 15) {
				if (df_d.parse(updateTime).before(lastMonth))
					return "2";
			} else {
				if (df_d.parse(updateTime).before(firstDay))
					return "2";
			}

			return "0";
		} catch (ParseException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	public static String top100IfClosed(String ensure_time, String projectType, String projectProgress, String projectOvertime, String ensure_time_tab, String projectType_tab, String projectProgress_tab, String projectOvertime_tab) {
		if (ToolKit.isNullOrBlank(projectType) || "null".equals(projectType)) {// 新增情况--暂关闭
			if (ToolKit.isNullOrBlank(projectType_tab) || "null".equals(projectType_tab)) {
				return "2";
			} else {
				if (ToolKit.isNullOrBlank(projectProgress_tab) && ToolKit.isNullOrBlank(projectOvertime_tab)) {
					return "2";
				}
			}
		} else {
			if (ToolKit.isNullOrBlank(projectType_tab) || "null".equals(projectType_tab)) {
				if (ToolKit.isNullOrBlank(projectProgress) && ToolKit.isNullOrBlank(projectOvertime)) {
					return "2";
				}
			} else {
				if (ToolKit.isNullOrBlank(projectProgress) && ToolKit.isNullOrBlank(projectOvertime) && ToolKit.isNullOrBlank(projectProgress_tab) && ToolKit.isNullOrBlank(projectOvertime_tab)) {
					return "2";
				}
			}
		}
		if (!ToolKit.isNullOrBlank(ensure_time) && !ToolKit.isNullOrBlank(ensure_time_tab)) {// 两中权限都有
			String result = ifClosed(ensure_time, projectType, projectProgress, projectOvertime);
			String result_tab = ifClosed(ensure_time_tab, projectType_tab, projectProgress_tab, projectOvertime_tab);
			if ("2".equals(result) || "2".equals(result_tab)) {
				return "2";
			} else if ("0".equals(result) || "0".equals(result_tab)) {
				return "0";
			} else {
				return "1";
			}
		} else if (!ToolKit.isNullOrBlank(ensure_time)) {
			return ifClosed(ensure_time, projectType, projectProgress, projectOvertime);
		} else if (!ToolKit.isNullOrBlank(ensure_time_tab)) {
			return ifClosed(ensure_time_tab, projectType_tab, projectProgress_tab, projectOvertime_tab);
		}
		return "0";
	}
	
	// 0:在用中，1：正常关闭，2：暂关闭
		private static String ifClosed(String ensure_time, String projectType, String projectProgress, String projectOvertime) {
			DateFormat df_d = new SimpleDateFormat("yyyy-MM-dd");
			try {
				String time = ensure_time;
				if (time == null || "null".equals(time))
					time = "";
				Date today = new Date();
				Date lastMonth = new Date(today.getYear(), today.getMonth() - 1, 16, 0, 0, 0);
				Date firstDay = new Date(today.getYear(), today.getMonth(), 1, 0, 0, 0);
				if (!ToolKit.isNullOrBlank(projectType) && !"null".equals(projectType)) {
					if ("方案已完成".equals(projectProgress)) {
						return "1";// 正常关闭
					}
					if (!ToolKit.isNullOrBlank(projectProgress)) {
						if (!"暂无法解决".equals(projectProgress)) {
							if (!ToolKit.isNullOrBlank(projectOvertime) && !"null".equals(projectOvertime)) {
								if (df_d.parse(projectOvertime).before(df_d.parse(df_d.format(new Date())))) {
									return "2";
								} else {
									if (today.getDate() <= 15) {
										if (df_d.parse(time).before(lastMonth))
											return "2";
									} else {
										if (df_d.parse(time).before(firstDay))
											return "2";
									}
								}
								return "0";
							} else {
								return "2";
							}
						} else {
							if (today.getDate() <= 15) {
								if (df_d.parse(time).before(lastMonth))
									return "2";
							} else {
								if (df_d.parse(time).before(firstDay))
									return "2";
							}
						}
						return "0";
					} else {
						return "2";
					}
				} else {
					return "1";
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return "0";
			}
		}
	
	public String lonAdnLatRange(String[] values) {

		double minlon = 0.0;
		double minlat = 0.0;
		double maxlon = 0.0;
		double maxlat = 0.0;

		if (values == null || values.length < 0)
			return null;

		for (int i = 0; i < values.length; i++) {
			String str = values[i];
			double lon = Double.parseDouble(str.split("-")[0]);
			double lat = Double.parseDouble(str.split("-")[1]);
			if (i == 0) {
				minlon = lon;
				minlat = lat;
			}
			if (lon < minlon) {
				minlon = lon;
			}
			if (lon > maxlon) {
				maxlon = lon;
			}
			if (lat > maxlat) {
				maxlat = lat;
			}
			if (lat < minlat) {
				minlat = lat;
			}

		}
		String bbox = String.valueOf(minlon) + "," + String.valueOf(minlat) + "," + String.valueOf(maxlon) + "," + String.valueOf(maxlat); // 地图边界范围
		System.out.println("多边形地图边界 ：" + bbox);
		return bbox;
	}
}
