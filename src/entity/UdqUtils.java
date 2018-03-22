package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import com.eastcomsw.util.ToolKit;

public class UdqUtils {



	public static Map<String, String> getBeanPropertiesMap(Object bean) {
		Map<String, String> m = new HashMap<String, String>();

		try {
			@SuppressWarnings("rawtypes")
			Map map = BeanUtils.describe(bean);
			@SuppressWarnings("rawtypes")
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) iterator.next();
				String name = (String) entry.getKey();
				String value = BeanUtils.getProperty(bean, name);
				if (!ToolKit.isNullOrBlank(value))
					if (ToolKit.isInteger(value) && Integer.parseInt(value) > 0)
						m.put(name, value);
					else if (!ToolKit.isInteger(value))
						m.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return m;
	}

	public static String buildSql(String sql, Map<String, String> paramMap,
			List<String> paramObjList) {
		return buildSql(sql, paramMap, paramObjList, false);
	}

	/**
	 * @param sql
	 * @param paramMap
	 * @param paramObjList
	 * @param ignoreBlank
	 *            是否忽略值为空的参数
	 * @return
	 */
	public static String buildSql(String sql, Map<String, String> paramMap,
			List<String> paramObjList, boolean ignoreBlank) {

		sql = sql.replaceAll("\\{\\{", "#@@@@#");
		sql = sql.replaceAll("\\}\\}", "@####@");

		StringBuffer sb = new StringBuffer();

		String regex = "\\{[^\\}]+\\}";

		String regex2 = "#[a-zA-Z0-9]+#";
		Pattern p2 = Pattern.compile(regex2);

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			String sParam = m.group();

			StringBuffer buffer = new StringBuffer();
			List<String> m2List = new ArrayList<String>();
			Matcher m2 = p2.matcher(sParam);
			while (m2.find()) {
				String param = m2.group();
				param = param.replaceAll("^#", "");
				param = param.replaceAll("#$", "");
				
				String tmpParam = param;

				boolean isIn = false;
				if (param.startsWith("999")) { // 此为一个in条件
					isIn = true;
					tmpParam = param.replaceFirst("^999", "");
				}

				String pv = paramMap.get(tmpParam);
				if (pv != null) {
					if (!ignoreBlank || !ToolKit.isNullOrBlank(pv)) {
						if (!isIn) {
							m2List.add(pv);
							m2.appendReplacement(buffer, "?");
						} else {
							String sign = null;
							String[] paramArr = pv.split(",");
							for (String pa : paramArr){
								m2List.add(pa);
								if (sign != null)
									sign += ",?";
								else
									sign = "?";
							}
							
							m2.appendReplacement(buffer, sign);
						}
					}
				}
			}
			String s = m2.appendTail(buffer).toString();
			if (s.contains("#"))
				m.appendReplacement(sb, "");
			else {
				s = s.replace("{", "");
				s = s.replace("}", "");
				m.appendReplacement(sb, s);
				paramObjList.addAll(m2List);
			}

		}

		m.appendTail(sb);

		String midStr = sb.toString();
		StringBuffer buffer = new StringBuffer();
		Matcher m2 = p2.matcher(midStr);
		while (m2.find()) {
			String param = m2.group();
			param = param.replaceAll("^#", "");
			param = param.replaceAll("#$", "");

			String pv = paramMap.get(param);
			if (pv != null) {
				if (!ignoreBlank || !ToolKit.isNullOrBlank(pv)) {
					paramObjList.add(pv);
					m2.appendReplacement(buffer, "?");
				}
			}
		}
		String s = m2.appendTail(buffer).toString();

		String regex3 = "@[a-zA-Z0-9]+@";
		Pattern p3 = Pattern.compile(regex3);
		StringBuffer sb3 = new StringBuffer();
		Matcher m3 = p3.matcher(s);
		while (m3.find()) {
			String param = m3.group();
			param = param.replaceAll("^@", "");
			param = param.replaceAll("@$", "");

			String pv = paramMap.get(param);
			if (pv != null) {
				if (!ignoreBlank || !ToolKit.isNullOrBlank(pv)) {
					m3.appendReplacement(sb3, pv);
				}
			}
		}
		s = m3.appendTail(sb3).toString();

		s = Pattern.compile("where\\s+and", Pattern.CASE_INSENSITIVE)
				.matcher(s).replaceAll("WHERE");
		s = Pattern.compile("where\\s+order", Pattern.CASE_INSENSITIVE)
				.matcher(s).replaceAll("ORDER");
		s = Pattern.compile("where\\s*$", Pattern.CASE_INSENSITIVE).matcher(s)
				.replaceAll("");
		s = Pattern.compile("where\\s*\\)", Pattern.CASE_INSENSITIVE)
				.matcher(s).replaceAll(")");

		s = s.replaceAll("#@@@@#", "{");
		s = s.replaceAll("@####@", "}");

		// 去除未有值的@xx@类标记
		s = s.replaceAll("@\\w+@", "");

		return s;

	}



}
