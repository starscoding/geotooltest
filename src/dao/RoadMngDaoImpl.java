package dao;

import java.util.ArrayList;
import java.util.List;

import com.eastcomsw.um.udq.db.base.BaseSqlExecutor;
import com.eastcomsw.um.udq.db.base.DBConnectionProvider;
import com.eastcomsw.um.udq.db.base.DataMeta;
import com.eastcomsw.um.udq.db.base.dm.DoubleDataMeta;
import com.eastcomsw.um.udq.db.base.dm.IntDataMeta;
import com.eastcomsw.um.udq.db.base.dm.StringDataMeta;
import com.eastcomsw.util.UUIDUtils;
import com.smile.geo.FetchRoad;
import com.sybase.jdbc3.jdbc.SybConnectionPoolDataSource;

import entity.GisBaseStation;
import entity.GisGsmBqzInfo;
import entity.GisWeakCover;
import entity.GridInfo;
import entity.Point;
import entity.RoadInfo;
import entity.RoadRelGrid;

public class RoadMngDaoImpl {
 
	public DBConnectionProvider getConnProvider(){
		SybConnectionPoolDataSource ds = new SybConnectionPoolDataSource();
		ds.setServerName("192.168.1.209");
//		ds.setServerName("10.221.32.124");
		ds.setDatabaseName("ecis");
		ds.setNetworkProtocol("Tds");
		ds.setUser("ecis");
		ds.setPassword("ecis1234");
		ds.setPortNumber(4100);
		ds.setCHARSET("cp936");
		ds.setDYNAMIC_PREPARE("true");
		ds.setENABLE_BULK_LOAD("true");
		
		DBConnectionProvider connProvider = new DBConnectionProvider();
		connProvider.setPoolDataSource(ds);
		return connProvider;
	}
	
	public void saveRoadsToDB(List<RoadInfo> roads){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		
		String sql = "insert into GIS_FASTWAY_INFO(UUID,ROADID,NAME_ORG,NAME_,NAME_PY,ROUTE_NO,TYPE_,LON_START,LAT_START,LON_END,LAT_END,WIDTH_,LENGTH_)";
	    sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    
	    String[][] datas= new String[roads.size()][13];
	    for (int i = 0; i < roads.size(); i++) {
	    	RoadInfo temp = roads.get(i);
	    	datas[i][0] = UUIDUtils.generate32UUID();
			datas[i][1] = temp.getId();
			datas[i][2] = temp.getNameOrg();
			datas[i][3] = temp.getName();
			datas[i][4] = temp.getNamePY();
			datas[i][5] = temp.getRouteNo();
			datas[i][6] = temp.getType();
			datas[i][7] = temp.getLonStart();
			datas[i][8] = temp.getLatStart();
			datas[i][9] = temp.getLonEnd();
			datas[i][10] = temp.getLatEnd();
			datas[i][11] = temp.getWidth();
			datas[i][12] = temp.getLength();
		}
	    
	    DataMeta[] dms = new DataMeta[13];
	    dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new StringDataMeta(2, true);
		dms[3] = new StringDataMeta(3, true);
		dms[4] = new StringDataMeta(4, true);
		dms[5] = new StringDataMeta(5, true);
		dms[6] = new StringDataMeta(6, true);
		dms[7] = new DoubleDataMeta(7, true);
		dms[8] = new DoubleDataMeta(8, true);
		dms[9] = new DoubleDataMeta(9, true);
		dms[10] = new DoubleDataMeta(10, true);
		dms[11] = new DoubleDataMeta(11, true);
		dms[12] = new DoubleDataMeta(12, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	public void saveRectangle(List<RoadInfo> roads){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_ROAD_RECTANGLE_INFO(UUID,ROADID,LON_A,LAT_A,LON_B,LAT_B,LON_C,LAT_C,LON_D,LAT_D,LENGTH_)";
	    sql += " values(?,?,?,?,?,?,?,?,?,?,?)";
	    
	    String[][] datas= new String[roads.size()][11];
	    for (int i = 0; i < roads.size(); i++) {
	    	RoadInfo temp = roads.get(i);
	    	List<Point> points = temp.getPoints();
	    	datas[i][0] = UUIDUtils.generate32UUID();
			datas[i][1] = temp.getId();
			datas[i][2] = points.get(0).getLon()+"";
			datas[i][3] = points.get(0).getLat()+"";
			datas[i][4] = points.get(1).getLon()+"";
			datas[i][5] = points.get(1).getLat()+"";
			datas[i][6] = points.get(2).getLon()+"";
			datas[i][7] = points.get(2).getLat()+"";
			datas[i][8] = points.get(3).getLon()+"";
			datas[i][9] = points.get(3).getLat()+"";
			datas[i][10] = FetchRoad.FIXED_DISTANCE+"";
		}
	    
	    DataMeta[] dms = new DataMeta[11];
	    dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new DoubleDataMeta(2, true);
		dms[3] = new DoubleDataMeta(3, true);
		dms[4] = new DoubleDataMeta(4, true);
		dms[5] = new DoubleDataMeta(5, true);
		dms[6] = new DoubleDataMeta(6, true);
		dms[7] = new DoubleDataMeta(7, true);
		dms[8] = new DoubleDataMeta(8, true);
		dms[9] = new DoubleDataMeta(9, true);
		dms[10] = new DoubleDataMeta(10, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	public void saveRoadRelGrid(List<RoadRelGrid> info){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_ROAD_REL_GRID(UUID,ROADID,GRIDID)";
		sql += " values(?,?,?)";
		
		String[][] datas= new String[info.size()][3];
		for (int i = 0; i < info.size(); i++) {
			RoadRelGrid temp = info.get(i);
			datas[i][0] = temp.getUuid();
			datas[i][1] = temp.getRoadId();
			datas[i][2] = temp.getGridId();
		}
		
		DataMeta[] dms = new DataMeta[3];
		dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new StringDataMeta(2, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	public void saveGridInfo(List<GridInfo>  grids){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_NEWGRID_INFO(ID_,DISTRICT_,LONGITUDE_,LATITUDE_,LONGITUDE_MIN,LATITUDE_MIN,LONGITUDE_MAX,LATITUDE_MAX,X_WIDTH,Y_WIDTH,GRID_POSITION,COL_NAME,ROW_NAME,DISTRICT_B,VENDOR_LIST)";
		sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		String[][] datas= new String[grids.size()][15];
		for (int i = 0; i < grids.size(); i++) {
			GridInfo temp = grids.get(i);
			datas[i][0] = temp.getId();
			datas[i][1] = temp.getDistrictList();
			datas[i][2] = temp.getLon()+"";
			datas[i][3] = temp.getLat()+"";
			datas[i][4] = temp.getMinLon()+"";
			datas[i][5] = temp.getMinLat()+"";
			datas[i][6] = temp.getMaxLon()+"";
			datas[i][7] = temp.getMaxLat()+"";
			datas[i][8] = temp.getXwidth()+"";
			datas[i][9] = temp.getYwidth()+"";
			datas[i][10] = temp.getPosition()+"";
			datas[i][11] = temp.getColName();
			datas[i][12] = temp.getRowName()+"";
			datas[i][13] = temp.getDistrict();
			datas[i][14] = "";
		}
		
		DataMeta[] dms = new DataMeta[15];
		dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new DoubleDataMeta(2, true);
		dms[3] = new DoubleDataMeta(3, true);
		dms[4] = new DoubleDataMeta(4, true);
		dms[5] = new DoubleDataMeta(5, true);
		dms[6] = new DoubleDataMeta(6, true);
		dms[7] = new DoubleDataMeta(7, true);
		dms[8] = new IntDataMeta(8, true);
		dms[9] = new IntDataMeta(9, true);
		dms[10] = new IntDataMeta(10, true);
		dms[11] = new StringDataMeta(11, true);
		dms[12] = new StringDataMeta(12, true);
		dms[13] = new StringDataMeta(13, true);
		dms[14] = new StringDataMeta(14, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	public void saveStation(List<GisBaseStation> roads,String roadId,String roadName){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_STATION_REL_RECTANGLE(UUID,ROAD_ID,ROAD_NAME,STATION_ID,STATION_NAME,NET_TYPE,STATION_TYPE,LON_,LAT_,ADDRESS,DEPENDENCY,DISTRICT,STATION_STATE,AREA,BSC_RNC)";
	    sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    
	    String[][] datas= new String[roads.size()][15];
	    for (int i = 0; i < roads.size(); i++) {
	    	GisBaseStation temp = roads.get(i);
	    	datas[i][0] = UUIDUtils.generate32UUID();
			datas[i][1] = roadId;
			datas[i][2] = roadName;
			datas[i][3] = temp.getBts_id();
			datas[i][4] = temp.getBts_name();
			datas[i][5] = temp.getNetType();
			datas[i][6] = temp.getBts_type();
			datas[i][7] = temp.getLongitude()+"";
			datas[i][8] = temp.getLatitude()+"";
			datas[i][9] = temp.getAddress();
			datas[i][10] = temp.getDependency();
			datas[i][11] = temp.getCity_country();
			datas[i][12] = temp.getBts_state();
			datas[i][13] = temp.getArea();
			datas[i][14] = temp.getBsc();
		}
	    
	    DataMeta[] dms = new DataMeta[15];
	    dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new StringDataMeta(2, true);
		dms[3] = new StringDataMeta(3, true);
		dms[4] = new StringDataMeta(4, true);
		dms[5] = new StringDataMeta(5, true);
		dms[6] = new StringDataMeta(6, true);
		dms[7] = new DoubleDataMeta(7, true);
		dms[8] = new DoubleDataMeta(8, true);
		dms[9] = new StringDataMeta(9, true);
		dms[10] = new StringDataMeta(10, true);
		dms[11] = new StringDataMeta(11, true);
		dms[12] = new StringDataMeta(12, true);
		dms[13] = new StringDataMeta(13, true);
		dms[14] = new StringDataMeta(14, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	public void saveWeakcover(List<GisWeakCover> roads,String roadId,String roadName){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_WEAKCOVER_REL_RECTANGLE(UUID,ROAD_ID,ROAD_NAME,DISTRICT,ADDRESS,CELL_NAME,IF_COLSED,LON_,LAT_,WEAKCOVER_ID)";
	    sql += " values(?,?,?,?,?,?,?,?,?,?)";
	    
	    String[][] datas= new String[roads.size()][10];
	    for (int i = 0; i < roads.size(); i++) {
	    	GisWeakCover temp = roads.get(i);
	    	datas[i][0] = UUIDUtils.generate32UUID();
			datas[i][1] = roadId;
			datas[i][2] = roadName;
			datas[i][3] = temp.getZone();
			datas[i][4] = temp.getAddress();
			datas[i][5] = temp.getArea();
			datas[i][6] = temp.getIfClosed();
			datas[i][7] = temp.getLongitude()+"";
			datas[i][8] = temp.getLatitude()+"";
			datas[i][9] = temp.getId();
		}
	    
	    DataMeta[] dms = new DataMeta[10];
	    dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new StringDataMeta(2, true);
		dms[3] = new StringDataMeta(3, true);
		dms[4] = new StringDataMeta(4, true);
		dms[5] = new StringDataMeta(5, true);
		dms[6] = new StringDataMeta(6, true);
		dms[7] = new DoubleDataMeta(7, true);
		dms[8] = new DoubleDataMeta(8, true);
		dms[9] = new StringDataMeta(9, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	public void saveTfStation(List<GisGsmBqzInfo> roads,String roadId,String roadName){
		DBConnectionProvider connProvider = this.getConnProvider();
		BaseSqlExecutor sqlExecutor = new BaseSqlExecutor();
		String sql = "insert into GIS_TFSTATION_REL_RECTANGLE(UUID,ROAD_ID,ROAD_NAME,TFSTATION_TYPE,PROGRESS_STATION,IF_COLSED,PROGRESS_TYPE,PROGRESS_SUBTYPE,DISTRICT,BASE_NAME,CLOSE_TIME,CLOSE_REASON,EXCEPT_TIME,DEPENDENCY,LOOP_LOCATION,STATION_TYPE,LON_,LAT_)";
	    sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    
	    String[][] datas= new String[roads.size()][18];
	    for (int i = 0; i < roads.size(); i++) {
	    	GisGsmBqzInfo temp = roads.get(i);
	    	datas[i][0] = UUIDUtils.generate32UUID();
			datas[i][1] = roadId;
			datas[i][2] = roadName;
			datas[i][3] = temp.getGhzType();
			datas[i][4] = temp.getProgressStation();
			datas[i][5] = temp.getIfClosed();
			datas[i][6] = temp.getProgressType();
			datas[i][7] = temp.getProgressSubtype();
			datas[i][8] = temp.getZone();
			datas[i][9] = temp.getBaseName();
			datas[i][10] = temp.getCloseTime();
			datas[i][11] = temp.getCloseReason();
			datas[i][12] = temp.getExpectNtime();
			datas[i][13] = temp.getDistrict();
			datas[i][14] = temp.getLoopLocation();
			datas[i][15] = temp.getStationType();
			datas[i][16] = temp.getLongitude()+"";
			datas[i][17] = temp.getLatitude()+"";
		}
	    
	    DataMeta[] dms = new DataMeta[18];
	    dms[0] = new StringDataMeta(0, true);
		dms[1] = new StringDataMeta(1, true);
		dms[2] = new StringDataMeta(2, true);
		dms[3] = new StringDataMeta(3, true);
		dms[4] = new StringDataMeta(4, true);
		dms[5] = new StringDataMeta(5, true);
		dms[6] = new StringDataMeta(6, true);
		dms[7] = new StringDataMeta(7, true);
		dms[8] = new StringDataMeta(8, true);
		dms[9] = new StringDataMeta(9, true);
		dms[10] = new StringDataMeta(9, true);
		dms[11] = new StringDataMeta(9, true);
		dms[12] = new StringDataMeta(9, true);
		dms[13] = new StringDataMeta(9, true);
		dms[14] = new StringDataMeta(9, true);
		dms[15] = new StringDataMeta(9, true);
		dms[16] = new DoubleDataMeta(9, true);
		dms[17] = new DoubleDataMeta(9, true);
		int rtn = sqlExecutor.executeUpdate(connProvider, sql, datas, dms);
		System.out.println("保存数据库成功，记录"+rtn+"条");
	}
	
	
}
