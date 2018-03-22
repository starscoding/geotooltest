package action;


import java.util.List;

import entity.GisBaseStation;
import entity.GisGsmBqzInfo;
import entity.GisWeakCover;

public class GetDataMain {

	public static void main(String[] args) {
		VipDataServices vipService = new VipDataServices();
		String latAndlon = "121.599534655030828-31.304044194480299," + "121.498785589101842-31.204312071815596," + "121.501246226956865-31.211192758375833," + "121.501995292885851-31.210924881040536";
		// 基站
		List<GisBaseStation> result1 = vipService.getBaseInfo(latAndlon);
		// 弱覆盖
		//List<GisWeakCover> result2 = vipService.searchWeakCoverByArea(latAndlon);
		// 退服站
		//List<GisGsmBqzInfo> result3 = vipService.serchTfStation(latAndlon);
		
	}

}
