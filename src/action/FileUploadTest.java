package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class FileUploadTest {
	
	public void fileupload(){
		File file = new File("F:\\testdd.xls");
		StringBuffer sb = new StringBuffer();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = workbook.getSheetAt(0);
			//int rows = sheet.getPhysicalNumberOfRows();//获取所有的行
			
			for(int i = 1 ;;i++){
				HSSFRow row = sheet.getRow(i);
				if(row == null)
					break;
				StringBuffer sql = new StringBuffer("insert into meta_shmsc_info values('").append(i).append("',");
				
				row.getCell((short)0).setCellType(1);
				sql.append("'").append(row.getCell((short)0)).append("',");
//				System.out.print(row.getCell((short)0)+"	");
				row.getCell((short)4).setCellType(1);
				sql.append("'").append(row.getCell((short)4)).append("',");
				//System.out.print(row.getCell((short)4)+"	");
				row.getCell((short)5).setCellType(1);
				sql.append("'").append(row.getCell((short)5)).append("',");
				//System.out.print(row.getCell((short)5)+"	");
				row.getCell((short)15).setCellType(1);
				sql.append("'").append(row.getCell((short)15)).append("')");
				//System.out.println(row.getCell((short)15));
				
				
				System.out.println(sql.toString());
//				System.out.println(row.getCell((short)2).getStringCellValue());
//				if(row.getCell((short)3) == null || "".equals(row.getCell((short)3).getStringCellValue())){
//					System.out.println(1);
//				}
//				sb.append(row.getCell((short)1).getStringCellValue());
//				if(i>1 && i<20){
//					sb.append(",");
//				}
				
				/*StringBuffer sb = new StringBuffer("insert into META_IP_DIALUP values('");
				
				//System.out.println(row);
				sb.append(row.getCell(0)+"','").append(row.getCell(1)+"','").append(row.getCell(2)+"',")
				.append(row.getCell(3)+"").append(row.getCell(4)+"')");
				System.out.println(sb.toString());*/
			}
			
			//System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件读取异常");
			e.printStackTrace();
		}
		
	}
	
	public  Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
          
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
  
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
          
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;
          
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;
          
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
          
        return map;
    }
	
	public double getDistatce(double lon1, double lat1, double lon2,
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
	
	 public double GetDistance(double lon1,double lat1,double lon2, double lat2)  
	    {  
	       double radLat1 = rad(lat1);  
	       double radLat2 = rad(lat2);  
	       double a = radLat1 - radLat2;  
	       double b = rad(lon1) - rad(lon2);  
	       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	       //s = s * EARTH_RADIUS;  
	       s = Math.round(s * 10000) / 10000;
	       return s;  
	    }

	 private double rad(double d)  
	    {  
	       return d * Math.PI / 180.0;  
	    }  

}
