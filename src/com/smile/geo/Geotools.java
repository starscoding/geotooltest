package com.smile.geo;

import com.eastcomsw.util.UUIDUtils;
import com.vividsolutions.jts.geom.Point;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Geotools {

    private static Log log = LogFactory.getLog(Geotools.class);
    private static String[][] nameandtypes = new String[][]{
            {"xingqudian", "兴趣点"}, {"doorplate2", "门牌号"},
            {"zhenjiecundian", "镇街村点"}, {"quxiandian", "区县点"},
            {"yiliaowensheng", "医疗卫生"}, {"xiuxianyule", "休闲娱乐"},
            {"xinwenmeiti", "新闻媒体"}, {"shehuifuwu", "社会服务"},
            {"shangchanggouwu", "商场购物"}, {"rvyoujingdian", "旅游景点"},
            {"juminxiaoqucunzhuang", "小区村庄"}, {"jiudianbingguan", "酒店宾馆"},
            {"jingrong", "金融"}, {"jiaoyukeyan", "教育科研"},
            {"jiaotong", "交通"}, {"gongchangqiye", "工厂企业"},
            {"danzhengjiguan", "党政机关"}};
    private static String[] nameandtypes_new = new String[]{"省级政府", "区县级政府",
            "行政地名", "保险公司", "餐饮服务", "城市轨道交通", "崇明岛兴趣点", "次要道路", "道路附属设施", "地区市政府", "地市级行政区划", "地铁出入口",
            "地铁面", "地铁站点", "飞机场", "风景名胜", "港口码头", "高等院校", "高速路", "工商税务机构", "公共设施", "公检法机关",
            "公司企业", "公园", "购物服务", "国道", "国省级景点", "火车站", "交通地名", "交通设施服务",
            "金融保险机构", "科教文化服务", "快速路", "楼宇大厦", "面状水系", "摩托车服务", "普通医院",
            "汽车相关服务", "桥", "区县级行政区划", "三甲医院", "商务住宅", "上海政区", "生活服务", "省级行政区划",
            "水系名称", "体育休闲服务", "铁路", "文化场馆服务", "五星级宾馆", "线状水系", "乡镇地名", "小路",
            "医疗相关服务", "银行", "银行相关服务", "证券公司", "政府机关及社会团体", "植被", "中小学校",
            "主要道路", "住宿服务", "住宅小区", "自然地名"};
    public static int dataNum = 0;
    public static Map<String, String> ignoreAddr = new HashMap<String, String>();
    public static Map<String, String> zoneMap = null;
    public final static DecimalFormat LAT_DECF = new DecimalFormat("##.########");
    public final static DecimalFormat LNG_DECF = new DecimalFormat("###.########");
    private static String afterParseFileDir = "D:/Work/mapdata/";
    private static String parseFileDir = "D:/Work/geo/上海2017/上海2017/";
    private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static void main(String[] args) {
//		 combineDatas();//合并门牌数据
//        getMenPaiDatas();//获取门牌数据
		 getXingQuDian();// 获取兴趣点数据
//		 reviseDatas();
//		 System.out.println(filterLocation1("老街中路典当弄22–1号", 1));
    }

    public static void combineDatas() {
        String prefix = "doorplategbk";
        String fileType = ".txt";
        int num = 1;
        File fObj = new File("D:/Work/mapdata/", "test.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fObj));
            for (int i = 1; i <= num; i++) {
//				String fileName = "D:/Work/mapdata/" + prefix + i + fileType;
                String fileName = "D:/Work/mapdata/" + prefix + fileType;
                System.out.println("读取第个" + i + "文件，文件路径为:" + fileName);
                addDatas(fileName, bw);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reviseDatas() {
        String fileName = "test.txt";
        String path = "G:/work/地图/地图数据提取/";
        File fObj = new File("G:/work/地图/地图数据提取/", "promp.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fObj));
            fileName = path + fileName;
            reviseData(fileName, bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pro() {
        String prefix = "menpai1-";
        String fileType = ".txt";
        int num = 15;
        File fObj = new File(parseFileDir, "menpaiall.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fObj));
            for (int i = 1; i <= num; i++) {
                String fileName = parseFileDir + prefix + i + fileType;
                System.out.println("读取第个" + i + "文件，文件路径为:" + fileName);
                addDatas(fileName, bw);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMenPaiDatas() {
        Map<String, String> boundarys = getCityBoundary("G:\\work\\地图\\地图数据提取\\区县边界.txt");
        Map<String, List<GisHelper.Point>> points = new HashMap<String, List<GisHelper.Point>>();
        for (Entry<String, String> entry : boundarys.entrySet()) {
            List<GisHelper.Point> point = GisHelper.parsePoints(entry
                    .getValue());
            points.put(entry.getKey(), point);
        }
        exportMenPai_x(1, points);
    }

    public static void getXingQuDian() {
        try {
            String zhabei = getZhabeiBoundary("D:/Work/geo/闸北边界.txt");
            List<GisHelper.Point> list = GisHelper.parsePoints(zhabei);
            File fObj = new File("D:/Work/mapdata/", "interest2017New.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(fObj));
            for (int i = 0; i < nameandtypes_new.length; i++) {
                exportNewData(i, bw, list);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void exportMenPai_x(int index,
                                      Map<String, List<GisHelper.Point>> points) {
        String[] nameandtype = nameandtypes[index];
        String delimiter = "|";

        Map<String, Object> connect = new HashMap<String, Object>();
        DataStore ds = null;
        File fObj = null;
        BufferedWriter bw = null;
        try {
            File shpfile = new File("D:/Work/geo/上海2017/上海2017/" + nameandtype[1] + ".shp");
            connect.put("url", shpfile.toURI().toURL());
            ds = DataStoreFinder.getDataStore(connect);
            File newDir = new File(afterParseFileDir);
            if (!newDir.exists()) {
                newDir.mkdir();
            }
            fObj = new File(afterParseFileDir, "door2017New.txt");
            bw = new BufferedWriter(new FileWriter(fObj));

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (ds == null) {
            return;
        }

        try {
//            SimpleFeatureSource fs = ds.getFeatureSource(nameandtype[1]);
            FeatureSource fs = ds.getFeatureSource(nameandtype[1]);
            int total = fs.getCount(Query.ALL);
            System.out.println("Number of features = " + total);
            FeatureCollection features = fs.getFeatures();
            FeatureIterator fi = features.features();
            try {
                SimpleFeature f;

                int count = 0;

                while (fi.hasNext()) {
                    count++;
                    f = (SimpleFeature) fi.next();
//					if(count <= 920000){
//						continue;
//					}
                    // 自生成ID，时间+8位随机数
                    String id = df.format(Calendar.getInstance().getTime())
                            + UUIDUtils.generateUUID(8);

                    //640263
//                    if(count!=640266 ){
//                        continue;
//                    }


                    // 地理位置
                    String objlocation = "";
                    String objmingcheng = "";
                    String objroadname = "";
                    String objmenpaiNum = "";

                    // 名称
                    Object fmingchengObj = f.getAttribute(1);
                    if (fmingchengObj == null) {
                        objmingcheng = "";
                    } else {
                        objmingcheng = fmingchengObj.toString().trim();
                        if (!("".equals(objmingcheng))) {
                            objmingcheng = new String(objmingcheng
                                    .getBytes("ISO-8859-1"), "GBK");

                        }
                    }
                    // 道路名
                    Object froadnameObj = f.getAttribute(2);
                    if (froadnameObj == null) {
                        objroadname = "";
                    } else {
                        objroadname = froadnameObj.toString().trim();
                        if (!("".equals(objroadname))) {
                            objroadname = new String(objroadname
                                    .getBytes("ISO-8859-1"), "GBK");
                        }
                    }
                    // 门牌号
                    Object fmenpaiNumObj = f.getAttribute(3);
                    if (fmenpaiNumObj == null) {
                        objmenpaiNum = "";
                    } else {
                        objmenpaiNum = fmenpaiNumObj.toString().trim();
                        if (!("".equals(objmenpaiNum))) {
                            objmenpaiNum = new String(objmenpaiNum
                                    .getBytes("ISO-8859-1"), "GBK");
                        }
                    }

                    objlocation = objmingcheng + objroadname + objmenpaiNum;
                    String address = "";
                    if (StringUtils.isNotBlank(objroadname)) {
                        address = objroadname + objmenpaiNum;
                    } else {
                        address = objlocation;
                    }
                    String stdLocation = "";
                    if (StringUtils.isNotBlank(objroadname)) {
                        stdLocation = objroadname + objmenpaiNum;
                        stdLocation = filterLocation1(stdLocation, 1);
                    } else {
                        stdLocation = filterLocation(objlocation, 1) + "号";
                    }

                    // 将地理位置中的"换成'
                    if (objlocation.contains("\"")) {
                        objlocation = objlocation.replace("\"", "'");
                    }

                    // 拼音地理位置,拼音地理位置缩写

                    String loc_pinyin = "";
                    String loc_pinyin_short = "";
                    String pinyinnonull = "";
                    if (objlocation != null && !("".equals(objlocation.trim()))) {
                        Map<String, String> map = CnChar2PinyinUtil
                                .getPinyinMap(objlocation);
                        // 简化全拼0x04
                        loc_pinyin = map.get(CnChar2PinyinUtil.F0X04);
                        // 简化声母0x01
                        loc_pinyin_short = map.get(CnChar2PinyinUtil.F0X01);
                    }

                    pinyinnonull = Pinyin.getPinYinNoNull_n(objlocation);
                    if (loc_pinyin == null)
                        loc_pinyin = pinyinnonull;
                    if (loc_pinyin_short == null)
                        loc_pinyin_short = pinyinnonull;

                    // 地理位置经度,地理位置维度
                    String lngStr = "";
                    String latStr = "";
                    Point fgeomObj = (Point) f.getAttribute("the_geom");
                    Double lng = null, lat = null;
                    if (fgeomObj != null) {
                        lng = fgeomObj.getX();// 经度
                        lat = fgeomObj.getY();// 维度

                        lngStr = LNG_DECF.format(lng);
                        latStr = LAT_DECF.format(lat);
                    }

                    if (lngStr.equals(""))
                        continue;
                    String objzone = "";
                    for (Entry<String, List<GisHelper.Point>> point : points
                            .entrySet()) {
                        if (GisHelper
                                .parsePointData(lng, lat, point.getValue())) {
                            objzone = point.getKey();
                            break;
                        }
                    }
                    if (StringUtils.isNotBlank(objzone) && "静安".equals(objzone)) {
                        List<GisHelper.Point> list = points.get("闸北");
                        if (GisHelper.parsePointData(lng, lat, list)) {
                            objzone = "闸北";
                        }
                    }
                    String rust = id + delimiter + "60" + delimiter
                            + objmingcheng + delimiter
                            + addQuotes(controSpace(loc_pinyin)) + delimiter
                            + addQuotes(controSpace(loc_pinyin_short))
                            + delimiter + lngStr + delimiter + latStr
                            + delimiter + addQuotes("上海市") + delimiter
                            + addQuotes(objzone) + delimiter + "门牌" + delimiter
                            + addQuotes(Pinyin.getPinYinNoNull("门牌"))
                            + delimiter + addQuotes(null) + delimiter
                            + addQuotes(null) + delimiter + addQuotes(null)
                            + delimiter + addQuotes(null) + delimiter
                            + addQuotes(pinyinnonull) + delimiter
                            + addQuotes(address) + delimiter
                            + addQuotes(null) + delimiter + addQuotes(null)
                            + delimiter + addQuotes(null) + delimiter
                            + objmingcheng + delimiter + objroadname
                            + delimiter + objmenpaiNum + delimiter
                            + objlocation + delimiter + stdLocation;

                    if ("".equals(latStr) || "".equals(lngStr))
                        System.out.println(rust);

                    // 去取数据中的换行、回车特殊字符
                    if (rust.contains("\n"))
                        rust = rust.replace("\n", "");
                    if (rust.contains("\r"))
                        rust = rust.replace("\r", "");

                    if (rust != null && !("".equals(rust.trim()))) {
                        System.out.println(count + ":" + rust);
                        // if("静安".equals(objzone)||"闸北".equals(objzone))
                        bw.write(rust.replaceAll("\"", "").replaceAll("\\(null\\)", "") + "\n");
                    }

                }
            } finally {
                bw.close();
//                 features.close(fi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出兴趣点数据
     *
     * @param index
     */
    @SuppressWarnings("unchecked")
    public static void exportNewData(int index, BufferedWriter bw,
                                     List<GisHelper.Point> list) {
        String nameandtype = nameandtypes_new[index];
        String delimiter = ";";

        Map<String, Object> connect = new HashMap<String, Object>();
        DataStore ds = null;
        int count = 0;
        try {
            File shpfile = new File(parseFileDir + nameandtype + ".shp");
            connect.put("url", shpfile.toURI().toURL());
            ds = DataStoreFinder.getDataStore(connect);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (ds == null) {
            return;
        }

        try {
            FeatureSource fs = ds.getFeatureSource(nameandtype);
            System.out.println(nameandtype + " Number of features = "
                    + fs.getCount(Query.ALL));

            FeatureCollection features = fs.getFeatures();
            FeatureIterator fi = features.features();
            try {
                SimpleFeature f;

                while (fi.hasNext()) {
                    f = (SimpleFeature) fi.next();
                    dataNum++;
                    // 自生成ID，时间+8位随机数
                    String id = df.format(Calendar.getInstance().getTime())
                            + UUIDUtils.generateUUID(8);
                    // 地理位置
                    String objlocation = "";
                    String location = "";
                    Object flocationObj = f.getAttribute("Name");
                    if (flocationObj == null) {
                        objlocation = "";
                    } else {
                        objlocation = flocationObj.toString().trim();
                        if (!("".equals(objlocation))) {
                            objlocation = new String(objlocation
                                    .getBytes("ISO-8859-1"), "GBK");
                        }
                    }

                    objlocation = QtoB(objlocation);

                    location = objlocation;

                    // 地址
                    String addr = f.getAttribute("Address") != null ? f
                            .getAttribute("Address").toString().trim() : "";
                    if (!("".equals(addr))) {
                        addr = new String(addr.getBytes("ISO-8859-1"), "GBK");
                        addr = QtoB(addr);
                        objlocation = objlocation + "-" + addr;
                    }
                    addr = addr.equals("") ? null : addr;

                    // 拼音地理位置,拼音地理位置缩写

                    String loc_pinyin = "";
                    String loc_pinyin_short = "";
                    String pinyinnonull = "";
                    if (objlocation != null && !("".equals(objlocation.trim()))) {
                        // System.out.println("objlocation:" + objlocation);
                        Map<String, String> map = CnChar2PinyinUtil
                                .getPinyinMap(objlocation);
                        if (map == null) {
                            ignoreAddr.put(addr, addr);
                        }
                        // 简化全拼0x04
                        loc_pinyin = map.get(CnChar2PinyinUtil.F0X04);
                        // 简化声母0x01
                        loc_pinyin_short = map.get(CnChar2PinyinUtil.F0X01);
                    }

                    pinyinnonull = Pinyin.getPinYinNoNull_n(objlocation);
                    if (loc_pinyin == null)
                        loc_pinyin = pinyinnonull;
                    if (loc_pinyin_short == null)
                        loc_pinyin_short = pinyinnonull;

                    // 地理位置经度,地理位置维度
                    String lngStr = "";
                    String latStr = "";
                    Point fgeomObj = (Point) f.getAttribute("the_geom");
                    if (fgeomObj == null) {
                        lngStr = "";
                        latStr = "";
                    } else {
                        Double lng = fgeomObj.getX();// 经度
                        Double lat = fgeomObj.getY();// 维度
                        lngStr = String.valueOf(lng);
                        latStr = String.valueOf(lat);
                        // log.info("经度:"+lngStr+" 维度:"+latStr);
                    }

                    // 区县 - citycol
                    String zone = "";
                    Object objzone = f.getAttribute("Code");
                    // System.out.println("objzone" + objzone);
                    if (objzone == null) {
                        zone = "";
                    } else {
                        zone = objzone.toString().trim();
                        if (index == 1) {
                            if (zoneMap == null) {
                                zoneMap = new HashMap<String, String>();
                            }
                            zoneMap.put(zone, objlocation.substring(0, 2));
                        }
                        if (index > 0) {
                            if (!("".equals(zone))) {
                                zone = zoneMap.get(zone) != null ? zoneMap
                                        .get(zone) : "";
                            }
                            if (zone.equals("")) {
                                log.info(objzone.toString().trim());
                            }
                        } else
                            zone = "上海市";

                    }
                    double lng = Double.parseDouble(lngStr);
                    double lat = Double.parseDouble(latStr);

                    lngStr = LNG_DECF.format(lng);
                    latStr = LAT_DECF.format(lat);
                    // System.out.println("zone:" + zone);
                    if (!ToolKit.isNullOrBlank(zone)) {
                        if (zone.equals("静安")) {
                            if (GisHelper.parsePointData(lng, lat, list))
                                zone = "闸北";
                        }
                    }

                    // 区域位置类型，如学校/医院等
                    String loc_type = nameandtype;

                    String telephone = f.getAttribute("Telephone") != null ? f
                            .getAttribute("Telephone").toString().trim() : "";
                    telephone = telephone.equals("") ? addQuotes(null) : "\""
                            + QtoB(telephone) + "\"";
                    String ctype = f.getAttribute("Ctype") != null ? f
                            .getAttribute("Ctype").toString().trim() : "";
                    if (!("".equals(ctype))) {
                        ctype = new String(ctype.getBytes("ISO-8859-1"), "GBK");
                    }
                    ctype = ctype.equals("") ? null : ctype;
                    String ntype = f.getAttribute("Ntype") != null ? f
                            .getAttribute("Ntype").toString().trim() : "";
                    ntype = ntype.equals("") ? null : ntype;

                    String name = f.getAttribute("Name") != null ? f
                            .getAttribute("Name").toString().trim() : "";
                    if (!("".equals(name))) {
                        name = new String(name.getBytes("ISO-8859-1"), "GBK");
                    }

                    String rust = addQuotes(id) + delimiter + (index + 1)
                            + delimiter + addQuotes(location) + delimiter
                            + addQuotes(controSpace(loc_pinyin)) + delimiter
                            + addQuotes(controSpace(loc_pinyin_short))
                            + delimiter + lngStr + delimiter + latStr
                            + delimiter + addQuotes("上海市") + delimiter
                            + addQuotes(zone) + delimiter + addQuotes(loc_type)
                            + delimiter
                            + addQuotes(Pinyin.getPinYinNoNull(loc_type))
                            + delimiter + addQuotes(null) + delimiter
                            + addQuotes(null) + delimiter + addQuotes(null)
                            + delimiter + addQuotes(null) + delimiter
                            + addQuotes(pinyinnonull) + delimiter
                            + addQuotes(addr) + delimiter + telephone
                            + delimiter + addQuotes(ctype) + delimiter + "\""
                            + ntype + "\"" + delimiter + name + delimiter
                            + addQuotes(null) + delimiter + addQuotes(null)
                            + delimiter + addQuotes(addr);
                    if (StringUtils.isNotBlank(addr)) {
                        rust += delimiter
                                + addQuotes(filterLocation1(addr, 1));
                    } else {
                        rust += delimiter + addQuotes(null);
                    }

                    // 去取数据中的换行、回车特殊字符
                    if (rust.contains("\n"))
                        rust = rust.replace("\n", "");
                    if (rust.contains("\r"))
                        rust = rust.replace("\r", "");

                    if (rust != null && !("".equals(rust.trim()))) {
                        System.out.println("count:" + dataNum + ">>" + rust);
                        // if (index > 1)
                        bw.write(rust + "\r\n");
                    }

                }
            } finally {
                ds.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String addQuotes(String src) {
        Pattern pattern = Pattern.compile("[0-9]*.[0-9]{0,25}");
        Pattern pat = Pattern.compile("[0-9]*");

        if (src != null && !("".equals(src))) {
            if (pattern.matcher(src).matches() || pat.matcher(src).matches())
                src = src;
            else
                src = "\"" + src + "\"";
        } else if ("".equals(src))
            src = "\"\"";
        else
            src = "(null)";

        return src;
    }

    public static String controSpace(String cn_loc) {
        if (cn_loc != null) {
            if (!cn_loc.startsWith(" "))
                cn_loc = " " + cn_loc;
            if (cn_loc.endsWith(" "))
                cn_loc = cn_loc.substring(0, cn_loc.indexOf(cn_loc.trim())
                        + cn_loc.trim().length());
        } else
            return cn_loc;
        return cn_loc;
    }

    public static Map<String, String> getCityBoundary(String path) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            String encoding = "UTF-8";
            File file = new File(path);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] data = lineTxt.split(":");
                    // System.out.println(data[0]);
                    String name = data[0];
                    String value = data[1];
                    if (value.indexOf(",") != -1)
                        result.put(name, value);
                    System.out.println(name + ":" + value);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return result;
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

    public static Map<String, String> addDatas(String path, BufferedWriter bw) {
        Map<String, String> result = new HashMap<String, String>();
        int lineNumber = 0;
        int errorNumber = 0;
        try {
            String encoding = "gbk";
            File file = new File(path);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    lineNumber++;
                    if (lineNumber > 1300000)
                        break;
                    String[] tmp = lineTxt.split("\\|");
                    if (tmp.length != 25 || tmp[5].length() > 18 || tmp[6].length() > 18 || tmp[0] == null) {
                        errorNumber++;
                        System.out.println(lineTxt.replaceAll("\"", "").replaceAll("\\(null\\)", "") + "\n");
                        System.out.println("error rows number:" + lineNumber);
                    }
                    try {
                        parseDecimal(tmp[5]);
                        parseDecimal(tmp[6]);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(lineTxt);
                        System.out.println("经纬度错误行数" + lineNumber);
                    }
//					bw.write(lineTxt.replaceAll("\"","").replaceAll("\\(null\\)","")+ "\n");
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        System.out.println("有问题记录数为：" + errorNumber);
        return result;
    }

    public static Map<String, String> reviseData(String path, BufferedWriter bw) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            String encoding = "gbk";
            File file = new File(path);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String address = lineTxt.substring(
                            lineTxt.lastIndexOf(";") + 1, lineTxt.length());
                    System.out.println(address);
                    System.out.println(GisHelper.filterLocation(address, 1));
                    System.out.println(lineTxt.substring(0, lineTxt
                            .lastIndexOf(";")));
                    String afterPro = lineTxt.substring(0, lineTxt
                            .lastIndexOf(";") + 1)
                            + GisHelper.filterLocation(address, 1);
                    bw.write(afterPro + "\n");
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return result;
    }

    public static String getZhabeiBoundary(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            String encoding = "UTF-8";
            File file = new File(path);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt.indexOf(",") != -1)
                        sb.append(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sb.toString();
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
                location = location.substring(0, t1 + t2);
            } else if (flag == 2) {
                location = location.substring(t, t1);
            }

        }
        // log.info(location);
        return location;
    }

    public static String filterLocation1(String location, int flag) {
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
                int t3 = 0;
                for (int i = 0; i < location_t.length(); i++) {
                    t3++;
                    if (!location_t.substring(i, i + 1).matches(
                            "[0-9a-zA-Z\\-]")) {
                        t2 = i;
                        break;
                    }
                }
                if (t2 == 0) {
                    t2 = location_t.length();
                    if (t3 == 0)
                        location = location.substring(0, t1 + t2);
                    else
                        location = location.substring(0, t1 + t2) + "弄";
                } else {
                    if ((location.length() > t1 + t2)) {
                        location = location.substring(0, t1 + t2 + 1);
                        if (!location.endsWith("号") && !location.endsWith("弄"))
                            location = location.substring(0, t1 + t2) + "弄";
                    } else
                        location = location.substring(0, t1 + t2) + "弄";
                }


            } else if (flag == 2) {
                location = location.substring(t, t1);
            }

        }
        //    log.info(location);
        return location;

    }

    public static BigDecimal parseDecimal(String str) {
        BigDecimal dec = new BigDecimal(str);
        return dec;
    }
}
