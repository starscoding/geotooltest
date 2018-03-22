package com.smile.geo;

import action.VipDataServices;

import com.eastcomsw.util.UUIDUtils;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

import dao.Crud_Dao;
import dao.RoadMngDaoImpl;

import entity.GisBaseStation;
import entity.GisGsmBqzInfo;
import entity.GisWeakCover;
import entity.GridInfo;
import entity.Model;
import entity.Point;
import entity.RoadInfo;
import entity.RoadRelGrid;

import net.miginfocom.layout.Grid;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by smile on 2018/1/9.
 */
public class FetchRoad {
	
	
//	public static double X_EACH_WIDTH_1000 = 0.01042792345332; // 经度1000m每格长度
//	public static double Y_EACH_WIDTH_1000 = 0.00898316074327; // 纬度1000m每格长度

	public static double X_EACH_WIDTH_1000 = 0.0105245; // 经度1000m每格长度
	public static double Y_EACH_WIDTH_1000 = 0.0089935; // 纬度1000m每格长度
//	public static double X_EACH_WIDTH_1000 = 0.011412555; // 经度1000m每格长度
//	public static double Y_EACH_WIDTH_1000 = 0.089932161; // 纬度1000m每格长度
//	X_EACH_WIDTH_1000 = 0.0011412555;
//	Y_EACH_WIDTH_1000 = 0.0089932161;
	
	public static double FIXED_DISTANCE = 400;

	private static RoadMngDaoImpl dao = new RoadMngDaoImpl();
	static VipDataServices vipService = new VipDataServices();
    public static void main(String[] args) {
//    	double a = 121.76276841250814;
//    	double b = 121.76307006181369;
//    	System.out.println(a>b);
//    	Point a  = new Point(121.81463406032988,31.107250434027776);
//    	Point b  = new Point(121.81443305121527,31.107182888454865);
//    	System.out.println(a.getLat()>b.getLat()&&a.getLon()>b.getLon());
        fetchRoad();
    }
    @SuppressWarnings("unchecked")
	public static void fetchRoad(){
        Map<String, Object> connect = new HashMap<String, Object>();
        DataStore ds = null;
        File fObj = null;
        BufferedWriter bw = null;

        String shpPath = "G:\\work\\地图\\上海2017\\上海2017\\快速路.shp";

        File shpfile = new File(shpPath);
        try {

            connect.put("url", shpfile.toURI().toURL());
            ds = DataStoreFinder.getDataStore(connect);
            List<RoadInfo> roads = new ArrayList<RoadInfo>();
            
            List<RoadInfo> allRoad = new ArrayList<RoadInfo>();
            
            FeatureSource fs = ds.getFeatureSource("快速路");
            int total = fs.getCount(Query.ALL);
            System.out.println("Number of features = " + total);

            FeatureCollection features = fs.getFeatures();
            FeatureIterator fi = features.features();

            SimpleFeature f;
            while (fi.hasNext()){
            	RoadInfo road = new RoadInfo();
                 f = (SimpleFeature) fi.next();
                
                road.setLonlats(toGBK(f.getAttribute(0)));
                road.setId(toGBK(f.getAttribute(1)));
                road.setMesh(toGBK(f.getAttribute(2)));
                road.setNameOrg(toGBK(f.getAttribute(3)));
                road.setName(toGBK(f.getAttribute(3)).replace("出口", "").replace("入口", ""));
                road.setNamePY(toGBK(f.getAttribute(4)));
                road.setType(toGBK(f.getAttribute(5)));
                road.setWidth(toGBK(f.getAttribute(7)));
                road.setRouteNo(toGBK(f.getAttribute(10)));
                double length = 0;
//                System.out.println(road);
                
                Geometry geo = (Geometry) f.getAttribute(0);
                Coordinate[] coors = geo.getCoordinates();
                for (int i = 0; i < coors.length; i++) {
//					System.out.println(coors[i].x);
//					System.out.println(coors[i].y);
					if(i!=coors.length-1){
//						System.out.println(coors[i].distsance(coors[i+1])*1000*100);
						length =length + getDistatce(coors[i].x, coors[i].y, coors[i+1].x, coors[i+1].y);
						RoadInfo temp = new RoadInfo();
						temp.setId(road.getId());
						temp.setNameOrg(road.getNameOrg());
						temp.setName(road.getName());
						temp.setNamePY(road.getNamePY());
						temp.setRouteNo(road.getRouteNo());
						temp.setType(road.getType());
						temp.setLonStart(coors[i].x+"");
						temp.setLatStart(coors[i].y+"");
						temp.setLonEnd(coors[i+1].x+"");
						temp.setLatEnd(coors[i+1].y+"");
						temp.setLength(length+"");
						temp.setWidth(road.getWidth());
						
						temp.setxStart(lonToKmX(coors[i].x));
						temp.setyStart(latToKmY(coors[i].y));
						temp.setxEnd(lonToKmX(coors[i+1].x));
						temp.setyEnd(latToKmY(coors[i+1].y));
						
						allRoad.add(temp);
//						System.out.println(getDistatce(coors[i].x, coors[i].y, coors[i+1].x, coors[i+1].y));
//						System.out.println(getDistatce(coors[i].y, coors[i].x, coors[i+1].y, coors[i+1].x));
					}
				}
                road.setLength(length+"");
                boolean addFlag = true;
                if(roads!=null&&roads.size()>0){
                	for (int i = 0; i < roads.size(); i++) {
						if(road.getName().equals(roads.get(i).getName())){
							roads.get(i).setLength(roads.get(i).getLength()+road.getLength());
							addFlag = false;
							break;
						}
					}
                }
                if(addFlag){
                	roads.add(road);
                }
//                System.out.println(road.getName()+":"+length+"m");
            }
//            System.out.println("总共记录数(不分段)："+roads.size());
//            System.out.println("总共记录数(分段)："+allRoad.size());
//            for (int i = 0; i < roads.size(); i++) {
//            	System.out.println(roads.get(i).getName()+":"+roads.get(i).getLength()+"m");
//			}
            
            //保存所有路的线段
            dao.saveRoadsToDB(allRoad);
            List<RoadInfo> newRoads = new ArrayList<RoadInfo>();
            for (int i = 0; i < allRoad.size(); i++) {
            	RoadInfo r = caculate(allRoad.get(i));
            	if(r!=null)
            		newRoads.add(r);
            }
            
            for (int i = 0; i < newRoads.size(); i++) {
            	
            	System.out.println("关联第"+i+"条路.......");
            	
            	RoadInfo r = newRoads.get(i);
            	List<Point> ps = r.getPoints();
            	String latAndlon = ps.get(0).getLon()+"-"+ps.get(0).getLat()+",";
            	latAndlon += ps.get(1).getLon()+"-"+ps.get(1).getLat()+",";
            	latAndlon += ps.get(2).getLon()+"-"+ps.get(2).getLat()+",";
            	latAndlon += ps.get(3).getLon()+"-"+ps.get(3).getLat();
            	System.out.println(latAndlon);
            	//基站
//            	List<GisBaseStation> result1 = vipService.getBaseInfo(latAndlon);
        		// 弱覆盖
        		List<GisWeakCover> result2 = vipService.searchWeakCoverByArea(latAndlon);
        		// 退服站
        		List<GisGsmBqzInfo> result3 = vipService.serchTfStation(latAndlon);
        		
//        		System.out.println(result1.size());
        		System.out.println(result2.size());
        		System.out.println(result3.size());
        		
        		//保存矩形关联的基站
//        		if(result1!=null&&result1.size()>0)
//        			dao.saveStation(result1, r.getId(), r.getName());
        		//保存矩形关联的弱覆盖
        		if(result2!=null&&result2.size()>0)
        			dao.saveWeakcover(result2, r.getId(), r.getName());
        		//保存矩形关联的退服站
        		if(result3!=null&&result3.size()>0)
        			dao.saveTfStation(result3, r.getId(), r.getName());
				
//				if(i%50==0){
//					try {
//						Thread.sleep(10*1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}
            
            //保存线段关联的矩形
            //dao.saveRectangle(newRoads);
            
            //保存栅格
            //List<GridInfo> grids = PaintGrid.paintGridBylon();
            //dao.saveGridInfo(grids);
            
            //矩形关联的栅格
            //List<RoadRelGrid> roadRelGrids = roadRelGird(newRoads, grids);
            //dao.saveRoadRelGrid(roadRelGrids);
            
//            caculate(allRoad.get(0));
            System.out.println("处理完成，处理记录"+allRoad.size()+"条！");
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static String toGBK(Object obj){
    	String result = "";
    	if (obj == null) {
    		result = "无";
        } else {
        	result = obj.toString().trim();
            if (!("".equals(obj))) {
            	try {
					result = new String(result.getBytes("ISO-8859-1"), "GBK");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
            }
//            if(i == 0){
//            	roadName = roadName.replaceAll("MULTILINESTRING", "").replaceAll("[((]", "").replaceAll("[))]", "");
//        	}
        }
    	return result;
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
		return distance;
	}
	
	public static RoadInfo caculate(RoadInfo road){
		List<Point> points = new ArrayList<Point>();
		System.out.println("线段长度："+getDistatce(Double.parseDouble(road.getLonStart()),
				Double.parseDouble(road.getLatStart()), Double.parseDouble(road.getLonEnd()), 
				Double.parseDouble(road.getLatEnd())));
		double length = getDistatce(Double.parseDouble(road.getLonStart()),
				Double.parseDouble(road.getLatStart()), Double.parseDouble(road.getLonEnd()), 
				Double.parseDouble(road.getLatEnd()));
		if(length<0.004){
			return null;
		}
		double pi = Math.PI;
		Point b = new Point(Double.parseDouble(road.getLonStart()),Double.parseDouble(road.getLatStart()));
		Point a = new Point(Double.parseDouble(road.getLonEnd()),Double.parseDouble(road.getLatEnd()));
		
		Point c = new Point( b.getLon(),a.getLat());
		
//		Point c = null;
//		
//		if((a.getLon()<b.getLon()&&a.getLat()<b.getLat())||(a.getLon()>b.getLon()&&a.getLat()<b.getLat())){
//			c = new Point( b.getLon(),a.getLat());
//		}else{
//			c = new Point( a.getLon(),b.getLat());
//		}
		
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		System.out.println("c:"+c);
		
//		double ab = getDistatce(a.getLon(), a.getLat(), b.getLon(), b.getLat());
//		double ac = getDistatce(a.getLon(), a.getLat(), c.getLon(), c.getLat());
//		double bc = getDistatce(b.getLon(), b.getLat(), c.getLon(), c.getLat());
		
		double ac = Math.abs(a.getLon()-b.getLon())*X_EACH_WIDTH_1000;
		double bc = Math.abs(b.getLat()-c.getLat())*Y_EACH_WIDTH_1000;
		double ab = Math.sqrt(ac*ac+bc*bc);
//		System.out.println( Math.abs(a.getLon()-b.getLon()));
//		System.out.println(Math.abs(b.getLat()-c.getLat()));
//		System.out.println("ab length : "+ab);
//		System.out.println("ac length : "+ac);
//		System.out.println("bc length : "+bc);
		
		double m = FIXED_DISTANCE*bc/ab;
		double n = FIXED_DISTANCE*ac/ab;
		
//		System.out.println("m,n:"+m+","+n);
		double ex = 0;
		double ey = 0;
		double fx = 0;
		double fy = 0;
		double gx = 0;
		double gy = 0;
		double hx = 0;
		double hy = 0;
		if((a.getLat()>b.getLat()&&a.getLon()>b.getLon())||(a.getLat()<b.getLat()&&a.getLon()<b.getLon())){
//		if(true){
			ex = b.getLon()+m*X_EACH_WIDTH_1000/1000;
			ey = b.getLat()-n*Y_EACH_WIDTH_1000/1000;
			System.out.println("e point : "+ex+","+ey);
			
			fx = a.getLon()+m*X_EACH_WIDTH_1000/1000;
			fy = a.getLat()-n*Y_EACH_WIDTH_1000/1000;
			System.out.println("f point : "+fx +","+fy);
			
			gx = a.getLon()-m*X_EACH_WIDTH_1000/1000;
			gy = a.getLat()+n*Y_EACH_WIDTH_1000/1000;
			System.out.println("g point : "+gx +","+gy);
			
			hx = b.getLon()-m*X_EACH_WIDTH_1000/1000;
			hy = b.getLat()+n*Y_EACH_WIDTH_1000/1000;
			System.out.println("h point : "+hx +","+hy);
		} else{
			ex = b.getLon()-m*X_EACH_WIDTH_1000/1000;
			ey = b.getLat()-n*Y_EACH_WIDTH_1000/1000;
			System.out.println("e point : "+ex+","+ey);
			
			fx = a.getLon()-m*X_EACH_WIDTH_1000/1000;
			fy = a.getLat()-n*Y_EACH_WIDTH_1000/1000;
			System.out.println("f point : "+fx +","+fy);
			
			gx = a.getLon()+m*X_EACH_WIDTH_1000/1000;
			gy = a.getLat()+n*Y_EACH_WIDTH_1000/1000;
			System.out.println("g point : "+gx +","+gy);
			
			hx = b.getLon()+m*X_EACH_WIDTH_1000/1000;
			hy = b.getLat()+n*Y_EACH_WIDTH_1000/1000;
			System.out.println("h point : "+hx +","+hy);
		}
		
		
//		double ex = b.getLon()-m*X_EACH_WIDTH_1000/1000-n*X_EACH_WIDTH_1000/1000;
//		double ey = b.getLat()-n*Y_EACH_WIDTH_1000/1000+m*Y_EACH_WIDTH_1000/1000;
//		System.out.println("e point : "+ex+","+ey);
//		
//		double fx = a.getLon()-m*X_EACH_WIDTH_1000/1000+n*X_EACH_WIDTH_1000/1000;
//		double fy = a.getLat()-n*Y_EACH_WIDTH_1000/1000-m*Y_EACH_WIDTH_1000/1000;
//		System.out.println("f point : "+fx +","+fy);
//		
//		double gx = a.getLon()+m*X_EACH_WIDTH_1000/1000+n*X_EACH_WIDTH_1000/1000;
//		double gy = a.getLat()+n*Y_EACH_WIDTH_1000/1000-m*Y_EACH_WIDTH_1000/1000;
//		System.out.println("g point : "+gx +","+gy);
//		
//		double hx = b.getLon()+m*X_EACH_WIDTH_1000/1000-n*X_EACH_WIDTH_1000/1000;
//		double hy = b.getLat()+n*Y_EACH_WIDTH_1000/1000+m*Y_EACH_WIDTH_1000/1000;
//		System.out.println("h point : "+hx +","+hy);
		
		points.add(new Point(ex, ey));
		points.add(new Point(fx, fy));
		points.add(new Point(gx, gy));
		points.add(new Point(hx, hy));
		
		road.setPoints(points);
		return road;
	}
	
	public static List<RoadRelGrid> roadRelGird(List<RoadInfo> roads,List<GridInfo> grids){
		List<RoadRelGrid> result = new ArrayList<RoadRelGrid>();
		for (int i = 0; i < roads.size(); i++) {
			System.out.println("处理"+i+"条！");
			RoadInfo road = roads.get(i);
			List<Point> points = road.getPoints();
			List<com.smile.geo.GisHelper.Point> pointList = new ArrayList<com.smile.geo.GisHelper.Point>();
			com.smile.geo.GisHelper.Point e = new com.smile.geo.GisHelper.Point(points.get(0).getLon(), points.get(0).getLat());
			com.smile.geo.GisHelper.Point f = new com.smile.geo.GisHelper.Point(points.get(0).getLon(), points.get(0).getLat());
			com.smile.geo.GisHelper.Point g = new com.smile.geo.GisHelper.Point(points.get(0).getLon(), points.get(0).getLat());
			com.smile.geo.GisHelper.Point h = new com.smile.geo.GisHelper.Point(points.get(0).getLon(), points.get(0).getLat());
			pointList.add(e);
			pointList.add(f);
			pointList.add(g);
			pointList.add(h);
			for (int j = 0; j < grids.size(); j++) {
				GridInfo grid = grids.get(j);
				com.smile.geo.GisHelper.Point a= new com.smile.geo.GisHelper.Point(grid.getMinLon(), grid.getMinLat());
				com.smile.geo.GisHelper.Point c= new com.smile.geo.GisHelper.Point(grid.getMinLon(), grid.getMaxLat());
				com.smile.geo.GisHelper.Point b= new com.smile.geo.GisHelper.Point(grid.getMaxLon(), grid.getMaxLat());
				com.smile.geo.GisHelper.Point d= new com.smile.geo.GisHelper.Point(grid.getMaxLon(), grid.getMinLat());
				List<com.smile.geo.GisHelper.Point> pointList2 = new ArrayList<com.smile.geo.GisHelper.Point>();
				pointList2.add(a);
				pointList2.add(b);
				pointList2.add(c);
				pointList2.add(d);
				if(GisHelper.parsePointData(a, pointList)||GisHelper.parsePointData(b, pointList)
						||GisHelper.parsePointData(c, pointList)||GisHelper.parsePointData(d, pointList)){
					RoadRelGrid temp = new RoadRelGrid();
					temp.setGridId(grid.getId());
					temp.setRoadId(road.getId());
					temp.setUuid(UUIDUtils.generate32UUID()	);
					result.add(temp);
				}else if(GisHelper.parsePointData(e, pointList2)||GisHelper.parsePointData(f, pointList2)
						||GisHelper.parsePointData(g, pointList2)||GisHelper.parsePointData(h, pointList2)){
					RoadRelGrid temp = new RoadRelGrid();
					temp.setGridId(grid.getId());
					temp.setRoadId(road.getId());
					temp.setUuid(UUIDUtils.generate32UUID()	);
					result.add(temp);
				}
				
			}
		}
		return result;
	}
	
	
	public static double lonToKmX(double lon){
		return (lon/X_EACH_WIDTH_1000)*1000;
	}
	
	public static double latToKmY(double lat){
		return (lat/Y_EACH_WIDTH_1000)*1000;
	}
	
	public static double kmXtoLon(double kmX){
		return (kmX/1000)*X_EACH_WIDTH_1000;
	}
	
	public static double kmYtoLat(double kmY){
		return (kmY/1000)*Y_EACH_WIDTH_1000;
	}
	
}
