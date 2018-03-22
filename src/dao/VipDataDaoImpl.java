package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import com.eastcomsw.um.udq.db.base.DBConnectionProvider;
import com.eastcomsw.um.udq.db.base.DividePageInfo;
import com.eastcomsw.um.udq.db.base.PagingSqlQueryExecutor;
import com.eastcomsw.um.udq.db.base.ResultInfo;
import com.eastcomsw.util.ToolKit;
import com.sybase.jdbc3.jdbc.SybConnectionPoolDataSource;

import entity.DoublePoint;
import entity.GisBaseStation;
import entity.GisGsmBqzInfo;
import entity.GisHelper;
import entity.GisHelper.Point;
import entity.GisWeakCover;
import entity.SqlEntity;
import entity.UdqUtils;
import entity.WirelessEntity;
import entity.WlQueryParamEntity;

public class VipDataDaoImpl {
	
	private static DBConnectionProvider connProvider = getConnProvider();
	private static Logger logger = Logger.getLogger(VipDataDaoImpl.class);
	
	public static DBConnectionProvider getConnProvider(){
		SybConnectionPoolDataSource ds = new SybConnectionPoolDataSource();
//		ds.setServerName("10.221.32.124");
		ds.setServerName("192.168.1.209");
		ds.setDatabaseName("ecis");
		ds.setNetworkProtocol("Tds");
		ds.setUser("ecis");
		ds.setPassword("ecis1234");
//		ds.setPassword("ecis!23$");
		ds.setPortNumber(4100);
		ds.setCHARSET("cp936");
		ds.setDYNAMIC_PREPARE("true");
		ds.setENABLE_BULK_LOAD("true");
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:sybase:Tds:192.168.1.209:4100/ecis?charset=cp936");
		dataSource.setDriverClassName("com.sybase.jdbc3.jdbc.SybDriver");
		dataSource.setUsername("ecis");
		dataSource.setPassword("ecis1234");

		connProvider = new DBConnectionProvider();
		connProvider.setDataSource(dataSource);
		
		DBConnectionProvider connProvider = new DBConnectionProvider();
//		connProvider.setPoolDataSource(ds);
		connProvider.setDataSource(dataSource);
		return connProvider;
	}

	public List<GisBaseStation> getBaseInfo(String bbox, String netType,List<Point> pointList) {
		List<GisBaseStation> gis = null;
		double lb_x = 0;
		double lb_y = 0;
		double rt_x = 0;
		double rt_y = 0;
		if (bbox != null && !"".equals(bbox)) {
			String[] _bbox = bbox.split(",");
			lb_x = Double.parseDouble(_bbox[0]);
			lb_y = Double.parseDouble(_bbox[1]);
			rt_x = Double.parseDouble(_bbox[2]);
			rt_y = Double.parseDouble(_bbox[3]);
		}
		List<GisBaseStation> gis2 = new ArrayList<GisBaseStation>();
		logger.info("步骤1  得到打点区域范围内的所有室外站(宏站、街道站)");
		long lStart = Calendar.getInstance().getTimeInMillis();
		gis = getBaseStations(new DoublePoint(lb_x, lb_y), new DoublePoint(rt_x, rt_y), netType);
		long lEnd = Calendar.getInstance().getTimeInMillis();
		int interval = (int) (lEnd - lStart) / 1000;
		logger.info("步骤1  得到打点区域范围内的所有室外站(宏站、街道站)用时:" + interval + "秒 ");
		if (gis != null && !gis.isEmpty()) {
			logger.info("去重之前的list长度：" + gis.size());
			// 去除重复基站
			for (int i = 0; i < gis.size(); i++) {
				for (int j = gis.size() - 1; j > i; j--) {
					if (gis.get(i).getBts_name().equals(gis.get(j).getBts_name())) {
						gis.remove(j);
					}
				}
			}
			logger.info("去重之后的list长度：" + gis.size());

			logger.info("步骤2  开始判断基站是否在多变边型内");
			long lStart2 = Calendar.getInstance().getTimeInMillis();
			for (GisBaseStation g : gis) {
				String m = g.getBts_name();
				if (!ToolKit.isNullOrBlank(m) && g != null) {
					logger.info(g.getBts_name() + "，是否在多边形内");
					if (GisHelper.parsePointData(g.getLongitude(), g.getLatitude(), pointList)) {
						logger.info(g.getBts_name() + "，在多边形内.....");
						gis2.add(g);
					}
				}
			}
			long lEnd2 = Calendar.getInstance().getTimeInMillis();
			int interval2 = (int) (lEnd2 - lStart2)/1000;
			logger.info("步骤2  判断基站是否在多变边型内用时:"+interval2+"秒 ");
			return gis2;
		}
		return null;
	}
	
	
	public List<GisWeakCover> searchWeakCoverByArea(DoublePoint dp_lb, DoublePoint dp_rt) {
		// TODO Auto-generated method stub
		List<GisWeakCover> result = new ArrayList<GisWeakCover>();
		if (dp_lb == null || dp_rt == null)
			return result;
		
		String sql = "select gw.ZONE_,gw.ADDRESS_,gw.AREA,gw.LATITUDE_,gw.LONGITUDE_,gw.ID_," +
				"gw.ENSURE_TIME,gw.PROJECT_TYPE,gw.PROJECT_PROGRESS,gw.PROJECT_OVERTIME,gw.ENSURE_TIME_TAB," +
				"gw.PROJECT_TYPE_TAB,gw.PROJECT_PROGRESS_TAB,gw.PROJECT_OVERTIME_TAB " +
				"from ecis.dbo.GIS_WEAKCOVER_INFO gw where gw.PROJECT_PROGRESS !='方案已完成' " + 
				"and gw.LONGITUDE_ > "+dp_lb.x+" and gw.LONGITUDE_ < "+dp_rt.x+" and gw.LATITUDE_ > "+dp_lb.y+" and gw.LATITUDE_ < "+dp_rt.y+" ";

		logger.info("searchWeakCoverByArea(关联弱覆盖)-SQL:" + sql);
		PagingSqlQueryExecutor sqlExecutor = new PagingSqlQueryExecutor();
		ResultInfo ri = sqlExecutor.executeQuery(connProvider, sql);
		if (ri.getResultCode() != ResultInfo.RESULT_CODE_SUC) {
			logger.error(ri.getDesc());
			return result;
		}

		if (ri.getData().length <= 1) {
			return result;
		}
		List<GisBaseStation> stations = new ArrayList<GisBaseStation>();
		String[][] data = ri.getData();

//		Query query = this.getEm().createNativeQuery(sql.toString());
//		query.setParameter(1, );
//		query.setParameter(2, );
//		query.setParameter(3, );
//		query.setParameter(4, );
//		List list = query.getResultList();
		
		for (int i = 1; i < data.length; i++) {
			String[] o = data[i];
			GisWeakCover weak = new GisWeakCover();
			weak.setZone(o[0] == null ? "" : o[0].toString());
			weak.setAddress(o[1] == null ? "" : o[1].toString());
			weak.setArea(o[2] == null ? "" : o[2].toString());
			weak.setLatitude(Double.parseDouble(o[3] == null ? "" : o[3].toString()));
			weak.setLongitude(Double.parseDouble(o[4] == null ? "" : o[4].toString()));
			weak.setId(o[5] == null ? "" : o[5].toString());
			weak.setEnsure_time(o[6] == null ? "" : o[6].toString());
			weak.setProjectType(o[7] == null ? "" : o[7].toString());
			weak.setProjectProgress(o[8] == null ? "" : o[8].toString());
			weak.setProjectOvertime(o[9] == null ? "" : o[9].toString());
			weak.setEnsure_time_tab(o[10] == null ? "" : o[10].toString());
			weak.setProjectType_tab(o[11] == null ? "" : o[11].toString());
			weak.setProjectProgress_tab(o[12] == null ? "" : o[12].toString());
			weak.setProjectOvertime_tab(o[13] == null ? "" : o[13].toString());
			result.add(weak);
		}
		return result;
	}
	
	
	public List<GisGsmBqzInfo> get(GisGsmBqzInfo gis,String netType) {
		// TODO Auto-generated method stub
		String tableName = "GIS_GSM_BQZ_INFO";
		if("4G".equals(netType)){
			tableName = "GIS_TUIFU_BQZ_INFO";
		}
		List<GisGsmBqzInfo> result = new ArrayList<GisGsmBqzInfo>();
		StringBuffer sql = new StringBuffer("select g.GHZ_TYPE,g.PROGRESS_STATION,g.PROGRESS_TYPE,g.PROGRESS_SUBTYPE,g.ZONE_,g.BASE_NAME,g.CLOSE_TIME,g.CLOSE_REASON,g.EXPECT_NTIME,g.DISTRICT,g.LOOP_LOCATION,g.STATION_TYPE,g.LATITUDE_,g.LONGITUDE_,g.UPDATE_TIME from "+tableName+" g where 1=1 ");
		if (gis.getMax_lng() != 0.0) {
			sql.append(" and g.LONGITUDE_>=" + gis.getMin_lng() + " and g.LONGITUDE_<=" + gis.getMax_lng() + " and g.LATITUDE_>=" + gis.getMin_lat() + " and g.LATITUDE_<=" + gis.getMax_lat());
		}
		if (!ToolKit.isNullOrBlank(gis.getBlockId())) {
			sql.append(" and g.BLOCK_ID in(" + gis.getBlockId().trim() + ")");
		}
		if (!ToolKit.isNullOrBlank(gis.getGhzType())) {
			sql.append(" and g.GHZ_TYPE='" + gis.getGhzType().trim() + "'");
		}
		if (!ToolKit.isNullOrBlank(gis.getIsNotNetwork())) {
			if (gis.getIsNotNetwork().equals("0")) // 未入网
				sql.append(" and ((g.PROGRESS_TYPE is null or g.PROGRESS_TYPE!='4') and (g.PROGRESS12_NTIME2 is null or g.PROGRESS12_NTIME2!='4') )");
			else if (gis.getIsNotNetwork().equals("1")) // 入网
				sql.append(" and (g.PROGRESS_TYPE='4' or g.PROGRESS12_NTIME2='4')");
		}
		
		logger.info("get(退服站关联SQL)-SQL：" + sql.toString());
		
		PagingSqlQueryExecutor sqlExecutor = new PagingSqlQueryExecutor();
		ResultInfo ri = sqlExecutor.executeQuery(connProvider, sql.toString());
		if (ri.getResultCode() != ResultInfo.RESULT_CODE_SUC) {
			logger.error(ri.getDesc());
			return result;
		}

		if (ri.getData().length <= 1) {
			return result;
		}
		//List<GisBaseStation> stations = new ArrayList<GisBaseStation>();
		String[][] data = ri.getData();
		
		for (int i = 1; i < data.length; i++) {
			GisGsmBqzInfo weak = new GisGsmBqzInfo();
			String[] o = data[i];
			weak.setGhzType((o[0] == null ? "" : o[0].toString()).equals("0") ? netType+"临时搬迁站" : netType+"永久搬迁站");
			weak.setProgressStation(this.progressStationType(o[1] == null ? "" : o[1].toString()));
			weak.setProgressType(this.judeProgressType(o[2] == null ? "" : o[2].toString()));
			weak.setProgressSubtype(this.judeProgressSub(o[3] == null ? "" : o[3].toString()));
			weak.setZone(o[4] == null ? "" : o[4].toString());
			weak.setBaseName(o[5] == null ? "" : o[5].toString());
			weak.setCloseTime(o[6] == null ? "" : o[6].toString());
			weak.setCloseReason(o[7] == null ? "" : o[7].toString());
			weak.setExpectNtime(o[8] == null ? "" : o[8].toString());
			weak.setDistrict(o[9] == null ? "" : o[9].toString());
			weak.setLoopLocation(o[10] == null ? "" : o[10].toString());
			weak.setStationType(o[11] == null ? "" : o[11].toString());
			weak.setLatitude(Double.parseDouble(o[12] == null ? "" : o[12].toString()));
			weak.setLongitude(Double.parseDouble(o[13] == null ? "" : o[13].toString()));
			weak.setUpdateTime(o[14] == null ? "" : o[14].toString());
			result.add(weak);
		
		}
//		Query query = this.getEm().createNativeQuery(sql.toString());
//		List list = query.getResultList();
//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				GisGsmBqzInfo weak = new GisGsmBqzInfo();
//				Object o[] = (Object[]) list.get(i);
//				weak.setGhzType((o[0] == null ? "" : o[0].toString()).equals("0") ? "规划站" : "搬迁站");
//				weak.setProgressStation(this.progressStationType(o[1] == null ? "" : o[1].toString()));
//				weak.setProgressType(this.judeProgressType(o[2] == null ? "" : o[2].toString()));
//				weak.setProgressSubtype(this.judeProgressSub(o[3] == null ? "" : o[3].toString()));
//				weak.setZone(o[4] == null ? "" : o[4].toString());
//				weak.setBaseName(o[5] == null ? "" : o[5].toString());
//				weak.setCloseTime(o[6] == null ? "" : o[6].toString());
//				weak.setCloseReason(o[7] == null ? "" : o[7].toString());
//				weak.setExpectNtime(o[8] == null ? "" : o[8].toString());
//				weak.setDistrict(o[9] == null ? "" : o[9].toString());
//				weak.setLoopLocation(o[10] == null ? "" : o[10].toString());
//				weak.setStationType(o[11] == null ? "" : o[11].toString());
//				weak.setLatitude(Double.parseDouble(o[12] == null ? "" : o[12].toString()));
//				weak.setLongitude(Double.parseDouble(o[13] == null ? "" : o[13].toString()));
//				weak.setUpdateTime(o[14] == null ? "" : o[14].toString());
//				result.add(weak);
//			}
//		}
		return result;
	}
	
	public String progressStationType(String type) {
		if (ToolKit.isNullOrBlank(type))
			return type;
		if ("0".equals(type)) {
			return "未提交审核 ";
		} else if ("1".equals(type)) {
			return "待审核";
		} else if ("2".equals(type)) {
			return "已审核";
		} else {
			return type;
		}
	}

	public String judeProgressType(String type) {
		if (ToolKit.isNullOrBlank(type))
			return type;
		if ("1".equals(type)) {
			return "购租中 ";
		} else if ("2".equals(type)) {
			return "工程建设中";
		} else if ("3".equals(type)) {
			return "受阻";
		} else if ("4".equals(type)) {
			return "已入网";
		} else {
			return type;
		}
	}

	public String judeProgressSub(String type) {
		if (ToolKit.isNullOrBlank(type))
			return type;
		if ("1".equals(type)) {
			return "站点购租 ";
		} else if ("2".equals(type)) {
			return "合同签订";
		} else if ("3".equals(type)) {
			return "机房土建";
		} else if ("4".equals(type)) {
			return "铁塔土建";
		} else if ("5".equals(type)) {
			return "配套设施安装";
		} else if ("6".equals(type)) {
			return "主设备安装";
		} else if ("7".equals(type)) {
			return "传输管道";
		} else if ("8".equals(type)) {
			return "传输光缆";
		} else if ("9".equals(type)) {
			return "传输资源申请";
		} else if ("10".equals(type)) {
			return "频率规划申请";
		} else if ("11".equals(type)) {
			return "受阻";
		} else if ("12".equals(type)) {
			return "已入网";
		} else {
			return type;
		}
	}
	
	
	
	
	
	public List<GisBaseStation> getBaseStations(DoublePoint dp_lb, DoublePoint dp_rt, String stationType) {
		WlQueryParamEntity wlqParamEntity = new WlQueryParamEntity();
		wlqParamEntity.setWlType(WirelessEntity.WL_TYPE_BASESTATION);
		wlqParamEntity.setStationCategory("宏站,街道站");
		if ("3G".equals(stationType) || "3".equals(stationType)) {
			wlqParamEntity.setBtsType(3);
		} else if ("4G".equals(stationType) || "4".equals(stationType))
			wlqParamEntity.setBtsType(4);
		else if ("2G".equals(stationType) || "2".equals(stationType))
			wlqParamEntity.setBtsType(2);
		else
			return null;
		System.out.println("查询地图范围的(宏站、街道站),参数：(" + dp_lb.getX() + " , " + dp_lb.getY() + " , " + dp_rt.getX() + " , " + dp_rt.getY() + ")");
		return getBaseStationsByMap(dp_lb, dp_rt, wlqParamEntity);
	}
	
	public List<GisBaseStation> getBaseStationsByMap(DoublePoint dp_lb, DoublePoint dp_rt, WlQueryParamEntity wlqParamEntity) {
		int networkType = 0;
		int wlType = wlqParamEntity.getWlType();
		if (wlType == WirelessEntity.WL_TYPE_BASESTATION) {
			networkType = wlqParamEntity.getBtsType();
			switch (networkType) {
			case 2:
				wlqParamEntity.setBtsType(WirelessEntity.WL_TYPE_BS_BTS);
				break;
			case 3:
				wlqParamEntity.setBtsType(WirelessEntity.WL_TYPE_BS_NODEB);
				break;
			case 4:
				wlqParamEntity.setBtsType(WirelessEntity.WL_TYPE_BS_ENODEB);
				break;
			default:
				break;
			}
		} else if (wlType == WirelessEntity.WL_TYPE_CELL) {
			networkType = wlqParamEntity.getCellType();
			switch (networkType) {
			case 2:
				wlqParamEntity.setCellType(WirelessEntity.WL_TYPE_CELL_CELL);
				break;
			case 3:
				wlqParamEntity.setCellType(WirelessEntity.WL_TYPE_CELL_UTRANCELL);
				break;
			case 4:
				wlqParamEntity.setCellType(WirelessEntity.WL_TYPE_CELL_EUTRANCELL);
				break;
			default:
				break;
			}
		}
		wlqParamEntity.setLeftDownPoint(dp_lb);
		wlqParamEntity.setRightUpPoint(dp_rt);
		wlqParamEntity.setPageSize(2000);
		if (ToolKit.isNullOrBlank(wlqParamEntity.getStationCategory()))
			wlqParamEntity.setStationCategory("宏站,街道站");

		return queryZongzWlData(wlqParamEntity);
	}
	
	// 查询所有基站
		public List<GisBaseStation> queryZongzWlData(WlQueryParamEntity wlqParamEntity) {
			String sqlId = null;
			int queryType = -1;
			switch (wlqParamEntity.getWlType()) {
			case WirelessEntity.WL_TYPE_BASESTATION:
				queryType = wlqParamEntity.getBtsType();
				switch (wlqParamEntity.getBtsType()) {
				case WirelessEntity.WL_TYPE_BS_BTS:
					sqlId = "Q_WL_ZONGZ_BTS";
					break;
				case WirelessEntity.WL_TYPE_BS_NODEB:
					sqlId = "Q_WL_ZONGZ_NODEB";
					break;
				case WirelessEntity.WL_TYPE_BS_ENODEB:
					sqlId = "Q_WL_ZONGZ_ENODEB";
					break;
				default:
					break;
				}

				break;
			case WirelessEntity.WL_TYPE_CELL:
				queryType = wlqParamEntity.getCellType();
				switch (wlqParamEntity.getCellType()) {
				case WirelessEntity.WL_TYPE_CELL_CELL:
					sqlId = "Q_WL_ZONGZ_CELL";
					break;
				case WirelessEntity.WL_TYPE_CELL_UTRANCELL:
					sqlId = "Q_WL_ZONGZ_UTRANCELL";
					break;
				case WirelessEntity.WL_TYPE_CELL_EUTRANCELL:
					sqlId = "Q_WL_ZONGZ_EUTRANCELL";
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}

			if (sqlId == null) {
				logger.error("没有找到ID为:" + sqlId + "的SQL语句定义.");
				return null;
			}

			// 查询条件集合
			Map<String, String> conditions = new HashMap<String, String>();
			if (queryType == WirelessEntity.WL_TYPE_CELL_EUTRANCELL) {// 4G
				String stationCategory = wlqParamEntity.getStationCategory();
				if (stationCategory != null) {
					wlqParamEntity.setStationCategory(wlqParamEntity.getStationCategory().replaceAll("宏站", "1").replaceAll("街道站", "3").replaceAll("室内覆盖", "2").replaceAll("小区覆盖", "4").replaceAll("应急车", "5"));
				}
			}
			conditions.put("stationCategory", wlqParamEntity.getStationCategory());
			conditions.put("cityName", wlqParamEntity.getCityName());
			conditions.put("name", wlqParamEntity.getName());
			conditions.put("netId", wlqParamEntity.getNetId());
			conditions.put("coverage", wlqParamEntity.getCoverage());
			conditions = guanLianAddress(wlqParamEntity, conditions);// 对关联地址处理
			String status = wlqParamEntity.getStatus();
			if (ToolKit.isNullOrBlank(status))
				status = "在网";
			conditions.put("status", status);
			System.out.println("入网状态status:" + status);
			if (wlqParamEntity.getLeftDownPoint() != null && wlqParamEntity.getRightUpPoint() != null) {
				conditions.put("minLongitude", "" + wlqParamEntity.getLeftDownPoint().getX());
				conditions.put("maxLongitude", "" + wlqParamEntity.getRightUpPoint().getX());
				conditions.put("minLatitude", "" + wlqParamEntity.getLeftDownPoint().getY());
				conditions.put("maxLatitude", "" + wlqParamEntity.getRightUpPoint().getY());
			}
			String sql = new SqlEntity().getSqlEntity(sqlId);
			List<String> paramObjList = new ArrayList<String>();
			sql = UdqUtils.buildSql(sql, conditions, paramObjList);
			logger.info("sql:" + sql);
			PagingSqlQueryExecutor sqlExecutor = new PagingSqlQueryExecutor();
			try {
				DividePageInfo dpi = new DividePageInfo();
				dpi.setStartRow(wlqParamEntity.getStart());
				dpi.setPageSize(wlqParamEntity.getPageSize());
				dpi.setCurrentPage(dpi.getStartRow() / dpi.getPageSize() + 1);
				sqlExecutor.setPageInfo(dpi);
			} catch (Exception e) {
				e.printStackTrace();
			}

			ResultInfo ri = sqlExecutor.executeQuery(connProvider, sql, paramObjList.toArray());
			if (ri.getResultCode() != ResultInfo.RESULT_CODE_SUC) {
				logger.error(ri.getDesc());
				return null;
			}

			if (ri.getData().length <= 1) {
				return new ArrayList<GisBaseStation>(0);
			}

			List<GisBaseStation> stations = new ArrayList<GisBaseStation>();

			String[][] data = ri.getData();
			for (int i = 1; i < data.length; i++) {
				String[] row = data[i];

				GisBaseStation station = composeResult(queryType, row);
				stations.add(station);
			}

			return stations;
		}
		
		private Map<String, String> guanLianAddress(WlQueryParamEntity wlqParamEntity, Map<String, String> conditions) {
			if (!ToolKit.isNullOrBlank(wlqParamEntity.getRemoteuinitAddress())) {// 需要关联拉远点
				String rAddress = wlqParamEntity.getRemoteuinitAddress().trim();// 关联地址
				if (rAddress.endsWith("号%")) {
					conditions.put("remoteuinitAddressHao", rAddress);
					conditions.put("remoteuinitAddressNong", rAddress.substring(0, rAddress.length() - 2) + "弄%");
				} else if (rAddress.endsWith("弄%")) {
					conditions.put("remoteuinitAddressHao", rAddress.substring(0, rAddress.length() - 2) + "号%");
					conditions.put("remoteuinitAddressNong", rAddress);
				} else if (rAddress.endsWith("号")) {
					conditions.put("remoteuinitAddressHao", rAddress);
					conditions.put("remoteuinitAddressNong", rAddress.substring(0, rAddress.length() - 1) + "弄");
				} else if (rAddress.endsWith("弄")) {
					conditions.put("remoteuinitAddressHao", rAddress.substring(0, rAddress.length() - 1) + "号");
					conditions.put("remoteuinitAddressNong", rAddress);
				} else {
					conditions.put("remoteuinitAddress", rAddress);
				}
			} else if (!ToolKit.isNullOrBlank(wlqParamEntity.getHostAddress())) {
				String hostAddress = wlqParamEntity.getHostAddress().trim();// 关联地址
				if (hostAddress.endsWith("号%")) {
					conditions.put("hostAddressHao", hostAddress);
					conditions.put("hostAddressNong", hostAddress.substring(0, hostAddress.length() - 2) + "弄%");
				} else if (hostAddress.endsWith("弄%")) {
					conditions.put("hostAddressHao", hostAddress.substring(0, hostAddress.length() - 2) + "号%");
					conditions.put("hostAddressNong", hostAddress);
				} else if (hostAddress.endsWith("号")) {
					conditions.put("hostAddressHao", hostAddress);
					conditions.put("hostAddressNong", hostAddress.substring(0, hostAddress.length() - 1) + "弄");
				} else if (hostAddress.endsWith("弄")) {
					conditions.put("hostAddressHao", hostAddress.substring(0, hostAddress.length() - 1) + "号");
					conditions.put("hostAddressNong", hostAddress);
				} else {
					conditions.put("hostAddress", hostAddress);
				}
			}
			return conditions;
		}
		
		private GisBaseStation composeResult(int type, String[] row) {
			GisBaseStation station = new GisBaseStation();
			if (type == WirelessEntity.WL_TYPE_BS_BTS) {
				station.setBts_id(row[0]);
				station.setBts_name(row[1]);// DESCRIPTION
				station.setCity_country(row[2]);
				station.setNet_flight(row[2]);
				station.setArea(row[3]);
				station.setVendor(row[4]);
				station.setTypeFlag(row[5]);
				station.setBts_state(row[6]);
				station.setAddress(row[7]);
				station.setBts_type(row[8]);
				station.setBatch_num(row[12]);
				station.setRpttime(row[12]);
				station.setBsc(row[13]);// bsc/BSC_ID/RNC_ID/ENODEBID
				try {
					station.setLongitude(Double.parseDouble(row[14]));
					station.setLatitude(Double.parseDouble(row[15]));
				} catch (Exception e) {
				}
				station.setNetType(row[16]);
				station.setVip_type(row[18]);// vip级别
				station.setShalianame(row[21]);// 别名 SHALIASNAME
				station.setProvince_id(row[24]);// 所属省
				station.setCity_id(row[25]);// 所属城市
				station.setCounty_id(row[26]);// 所属区县
				station.setRoom_id(row[27]);// 所属机房/资源点id
				station.setLac(row[28]);
				station.setCi(row[29]);
				station.setDependency(row[30]);
			} else if (type == WirelessEntity.WL_TYPE_BS_NODEB) {
				station.setBts_id(row[0]);
				station.setBts_name(row[1]);// DESCRIPTION
				station.setCity_country(row[2]);
				station.setArea(row[3]);// 覆盖类型coverage
				station.setVendor(row[4]);// vender
				station.setTypeFlag(row[5]);// 使用频段
				station.setBts_state(row[6]);// status
				station.setAddress(row[7]); // hostaddress
				station.setBts_type(row[8]);// stationCatalog 基站属性
				station.setBatch_num(row[9]);
				station.setRpttime(row[9]);
				station.setBsc(row[10]);// bsc/BSC_ID/RNC_ID/ENODEBID
				try {
					station.setLongitude(Double.parseDouble(row[11]));
					station.setLatitude(Double.parseDouble(row[12]));
				} catch (Exception e) {

				}
				station.setNetType(row[13]);
				station.setVip_type(row[15]);// vip级别
				station.setShalianame(row[18]);// 别名 SHALIASNAME
				station.setProvince_id(row[21]);// 所属省
				station.setCity_id(row[22]);// 所属城市
				station.setCounty_id(row[23]);// 所属区县
				station.setRoom_id(row[24]);// 所属机房/资源点id
				station.setLac(row[25]);
				station.setCi(row[26]);
				station.setDependency(row[27]);// 所属管理部门
			} else if (type == WirelessEntity.WL_TYPE_BS_ENODEB) {
				station.setBts_id(row[0]);
				station.setBts_name(row[1]);// DESCRIPTION
				station.setCity_country(row[2]);
				station.setArea(row[3]);// 覆盖类型coverage
				station.setVendor(row[4]);// vender
				station.setTypeFlag(row[5]);// 使用频段
				station.setBts_state(row[6]);// status
				station.setAddress(row[7]); // hostaddress
				station.setBts_type(row[8]);// stationCatalog 基站属性
				try {
					station.setLongitude(Double.parseDouble(row[9]));
					station.setLatitude(Double.parseDouble(row[10]));
				} catch (Exception e) {

				}
				station.setNetType(row[11]);
				station.setVip_type(row[13]);// vip级别
				station.setShalianame(row[16]);// 别名 SHALIASNAME
				station.setBsc(row[17]);// bsc/BSC_ID/RNC_ID/ENODEBID
				station.setCell_name(row[18]);
				station.setProvince_id(row[19]);// 所属省
				station.setCity_id(row[20]);// 所属城市
				station.setCounty_id(row[21]);// 所属区县
				station.setRoom_id(row[22]);// 所属机房/资源点id
				station.setOmc_name(row[23]);// 所属OMC,OMC_NAME
				station.setEcgi(row[24]);// ecgi
				station.setBatch_num(row[25]);
				station.setRpttime(row[25]);// row[25]
				station.setDependency(row[26]);// 所属管理部门
			} else if (type == WirelessEntity.WL_TYPE_CELL_CELL || type == WirelessEntity.WL_TYPE_CELL_UTRANCELL || type == WirelessEntity.WL_TYPE_CELL_EUTRANCELL) {

				station.setCell_name(row[0]);
				// station.setBts_id(row[0]);
				// station.setBts_name(row[1]);
				station.setCity_country(row[1]);
				station.setCi(row[2]);// 小区码CI
				station.setLac(row[3]);// 位置区码LAC
				station.setArea(row[4]);// //覆盖类型coverage
				if (ToolKit.isNullOrBlank(row[5]) || row[5].equals("null")) {
					station.setLongitude(0.0);// 天线经度
				} else {
					station.setLongitude(Double.parseDouble(row[5]));// 天线经度
				}
				if (ToolKit.isNullOrBlank(row[6]) || row[6].equals("null")) {
					station.setLatitude(0.0);// 天线经度
				} else {
					station.setLatitude(Double.parseDouble(row[6]));// 天线纬度
				}

				station.setCover_universit(row[7]);// 覆盖场景 COVER_UNIVERSIT
				station.setTypeFlag(row[8]);// 使用频段 USE_FRE_BAND_ID USE_FRE_BAND
				station.setBts_state(row[9]);// status

				if (type == WirelessEntity.WL_TYPE_CELL_EUTRANCELL) {
					String temp = row[11];
					if (temp != null) {
						if (temp.equals("1")) {
							station.setBts_type("宏站");
						} else if (temp.equals("2")) {
							station.setBts_type("室内覆盖");
						} else if (temp.equals("3")) {
							station.setBts_type("街道站");
						} else if (temp.equals("4")) {
							station.setBts_type("小区覆盖");
						} else if (temp.equals("5")) {
							station.setBts_type("应急车");
						} else {
							station.setBts_type(row[11]);// 基站属性 stationCatalog
						}
					} else {

						station.setBts_type(row[11]);// 基站属性 stationCatalog
					}
				} else {
					station.setBts_type(row[11]);// 基站属性 stationCatalog
				}
				station.setBts_id(row[12]);
				station.setBts_name(row[13]);
				station.setAddress(row[14]);// 设备归属机房地址 DEVICENORMALADDRESS
				station.setHuawu_name(row[15]);
				station.setBatch_num(row[18]);
				if (!ToolKit.isNullOrBlank(row[20]) && !row[20].equals("null")) {
					double ss = Double.parseDouble(row[20]);
					int ii = (int) ss;
					station.setAntenna_height(ii);// 天线挂高
				} else {
					station.setAntenna_height(0);// 天线经度
				}
				if (!ToolKit.isNullOrBlank(row[21]) && !row[21].equals("null")) {
					double ss = Double.parseDouble(row[21]);
					int ii = (int) ss;
					station.setAntenna_direction_angle(ii);// 方位角
				} else {
					station.setAntenna_direction_angle(0);// 天线经度
				}
				station.setProvince_id(row[22]);// 所属省
				station.setCity_id(row[23]);// 所属城市
				station.setCounty_id(row[24]);// 所属区县
				station.setRoom_id(row[25]);// 所属机房/资源点id
				station.setAntenna_type(row[26]);// 天线类型/TYPE
				station.setTotal_angel(row[27]);// 总俯仰角/total_angel
				station.setElec_down_angel(row[28]);// 电下倾角/elec_down_angel
				station.setMachine_down_angel(row[29]);// 机械下倾角/MACHINE_DOWN_ANGLE
				station.setBts_real_id(row[30]);// 所属BTS/ENODEB_ID NODEB_ID BTS_ID
				station.setAntenna_name(row[31]);// 天线名称
				// station.setBatch_num(row[15]);
				// station.setAntenna_height(Integer.parseInt(row[17]));
				// station.setAntenna_direction_angle(Integer.parseInt(row[18]));
			}
			if (!ToolKit.isNullOrBlank(station.getRpttime()) && !"null".equals(station.getRpttime())) {
				SimpleDateFormat simf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				try {
					String rptTime = sim.format(simf.parse(station.getRpttime()));
					station.setRpttime(rptTime);
				} catch (Exception e) {
					logger.info("更新时间Rpttime>>" + station.getRpttime() + "转换格式出错!");
				}
			}

			return station;
		}
}
