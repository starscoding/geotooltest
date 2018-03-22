package com.smile.geo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.lang.StringUtils;

import dao.RoadMngDaoImpl;

import entity.GridInfo;
import entity.Point;

public class PaintGrid {

//	public static double X_EACH_WIDTH_1000 = 0.0105240; // 经度1000m每格长度
//	public static double Y_EACH_WIDTH_1000 = 0.0089935; // 纬度1000m每格长度
	public static double X_EACH_WIDTH_1000 = 0.01042792345332; // 经度1000m每格长度
	public static double Y_EACH_WIDTH_1000 = 0.00898316074327; // 纬度1000m每格长度
	private static RoadMngDaoImpl dao = new RoadMngDaoImpl();
	
	public static double startLon = 120.844171;
	public static double startLat = 31.9035835;
	public static double endLon = 122.064979;
	public static double endLat = 30.6805005;
	
	public static int haveDistrictCount = 0;
	
	public static int lonNum=0;
	
	public static int gridNum = 1;
	
	
	public static double FIXED_DISTANCE = 1;//单位km
	
	static Map<String, List<GisHelper.Point>> boundary = getDistrictBoundary();
	
	public static void main(String[] args) {
		dao.saveGridInfo(paintGridBylon());
	}
	
	public static String getSerialNo(int i){
		int alphbetNum = 26;
		int num = 65;
		int multipie = i/alphbetNum;
		int remainder = i%alphbetNum;
		int perasci = multipie+num;
		char percode = (char) perasci;
		int asci = remainder+num;
		char code = (char) asci;
		System.out.println(percode+""+code+"");
		return percode+""+code+"";
	}
	
	public static void paintOriginPoint(){
		Point origin = new Point(120.844171, 31.9035835);
		Point sPoint = new Point(origin.getLon()-(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()-(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
		Point ePoint = new Point(origin.getLon()+(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()+(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
		
		System.out.println(origin);
		System.out.println(sPoint.toString());
		System.out.println(ePoint.toString());
		
		Point origin2 = new Point(120.844171, 31.9035835-FIXED_DISTANCE*Y_EACH_WIDTH_1000);
		Point sPoint2 = new Point(origin.getLon()-(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()-(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
		Point ePoint2 = new Point(origin.getLon()+(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()+(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
		
		System.out.println(origin2);
		System.out.println(sPoint2.toString());
		System.out.println(ePoint2.toString());
	}
	
	public static List<GridInfo> paintGridByLat(double sLon){
		List<GridInfo> result = new ArrayList<GridInfo>();
		double sLat = startLat;
		int count = 0;
		
		while(sLat>=endLat){
			Point origin = new Point(sLon, sLat);
			Point sPoint = new Point(origin.getLon()-(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()-(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
			Point ePoint = new Point(origin.getLon()+(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()+(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
			
			Point sPoint2 = new Point(origin.getLon()-(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()+(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
			Point ePoint2 = new Point(origin.getLon()+(FIXED_DISTANCE*X_EACH_WIDTH_1000/2), origin.getLat()-(FIXED_DISTANCE*Y_EACH_WIDTH_1000/2));
			
			//判断该点是在哪个行政区
			String district = judgeDistrict(sLon, sLat, boundary);
			if(StringUtils.isNotBlank(district))
				haveDistrictCount++;
			
			//获取行政区集合
			String districts = "";
			String a = judgeDistrict(sPoint.getLon(), sPoint.getLat(), boundary);
			String b = judgeDistrict(ePoint.getLon(), ePoint.getLat(), boundary);
			String c = judgeDistrict(sPoint2.getLon(), sPoint2.getLat(), boundary);
			String d = judgeDistrict(ePoint2.getLon(), ePoint2.getLat(), boundary);
			if(StringUtils.isNotBlank(a)&&!districts.contains(a)){
				if(StringUtils.isBlank(districts))
					districts = a;
				else
					districts = districts+","+a;
			}
			if(StringUtils.isNotBlank(b)&&!districts.contains(b)){
				if(StringUtils.isBlank(districts))
					districts = b;
				else
					districts = districts+","+b;
			}
			if(StringUtils.isNotBlank(c)&&!districts.contains(c)){
				if(StringUtils.isBlank(districts))
					districts = c;
				else
					districts = districts+","+c;
			}
			if(StringUtils.isNotBlank(d)&&!districts.contains(d)){
				if(StringUtils.isBlank(districts))
					districts = d;
				else
					districts = districts+","+d;
			}
			System.out.println(districts);
			//得到编号
			String colName = getSerialNo(lonNum);
			String id = colName+(count+1);
			System.out.println(id);
			GridInfo grid = new GridInfo();
			grid.setId(id);
			grid.setDistrictList(districts);
			grid.setLon(origin.getLon());
			grid.setLat(origin.getLat());
			grid.setMinLon(sPoint.getLon());
			grid.setMinLat(sPoint.getLat());
			grid.setMaxLon(ePoint.getLon());
			grid.setMaxLat(ePoint.getLat());
			grid.setXwidth((int)FIXED_DISTANCE);
			grid.setYwidth((int)FIXED_DISTANCE);
			grid.setPosition(gridNum);
			grid.setColName(colName);
			grid.setRowName(count+1);
			grid.setDistrict(district);
			result.add(grid);
			
			sLat = sLat-FIXED_DISTANCE*Y_EACH_WIDTH_1000;
			System.out.println(origin);
			System.out.println(sPoint.toString());
			System.out.println(ePoint.toString());
			System.out.println();
			count++;
			gridNum++;
		}
		
		System.out.println("执行完成，记录"+count+"条！");
		return result;
	}
	
	public static List<GridInfo> paintGridBylon(){
		List<GridInfo> result = new ArrayList<GridInfo>();
		double sLon = startLon;
		int count = 0;
		while(sLon<=endLon){
			result.addAll(paintGridByLat(sLon));
			sLon =  sLon+FIXED_DISTANCE*X_EACH_WIDTH_1000;
			count++;
			lonNum++;
		}
		System.out.println("执行完成，记录"+count*136+"条！");
		System.out.println("执行完成，记录"+result.size()+"条！");
		System.out.println("记录"+haveDistrictCount+"条有行政区！");
		return result;
	}
	
	public static Map<String, List<GisHelper.Point>> getDistrictBoundary(){
		Map<String, String> boundarys = Geotools.getCityBoundary("G:\\work\\地图\\地图数据提取\\区县边界.txt");
        Map<String, List<GisHelper.Point>> points = new HashMap<String, List<GisHelper.Point>>();
        for (Entry<String, String> entry : boundarys.entrySet()) {
            List<GisHelper.Point> point = GisHelper.parsePoints(entry.getValue());
            points.put(entry.getKey(), point);
            System.out.println(entry.getKey());
        }
        return points;
	}
	
	public static String judgeDistrict(double lon,double lat,Map<String, List<GisHelper.Point>> boundary){
		String result = "";
		for (Entry<String, List<GisHelper.Point>> entry : boundary.entrySet()) {
            if(GisHelper.parsePointData(lon, lat, entry.getValue())){
            	result = entry.getKey();
            	break;
            }
        }
		return result;
	}
}
