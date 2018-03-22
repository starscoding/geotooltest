package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.eastcomsw.util.UUIDUtils;

import entity.Model;

public class Crud_Dao {
	
	private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public void insert(List<Model> ModeList){
		long start = System.currentTimeMillis();
		Sybase_Jdbc sybase = new Sybase_Jdbc();
		Connection conn =  sybase.getConntion();
		String sql = "insert into GIS_ELEVATED_INFO values(?,?,?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			PreparedStatement pre = conn.prepareStatement(sql);
			for (int i =0;i<ModeList.size();i++) {
				Model model = ModeList.get(i);
				String id = df.format(Calendar.getInstance().getTime())
		                 + UUIDUtils.generateUUID(8);
				pre.setString(1, id);
				pre.setString(2, model.getName());
				pre.setString(3, model.getOrnName());
				pre.setString(4, model.getNumber());
				pre.setString(5, model.getLonAndlat());
				pre.addBatch();
				if(i%100==0)
					pre.executeBatch();
				
			}
			
			pre.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("批量插入需要时间:"+(end - start)/1000+"s");
		
		
	}
}
