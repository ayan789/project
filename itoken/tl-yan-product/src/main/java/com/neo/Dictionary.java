package com.neo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Created by zhangjunwen on 2017/10/20
 *
 * @author zhangjunwen
 * @date 2017/10/20
 */
@Slf4j
public class Dictionary {

	private static Map<String, String> companyJsonPath = new HashMap<>();
	private static Map<String, Map<String, DItem>> companyDict = new HashMap<>();
	private static Map<String, Map<String, DItem>> companyTypeDict = new HashMap<>();
	private static Map<String, List<String>> typeListMap = new HashMap<>();
	private static Map<String, Map<String, DItem>> cacheDict = new HashMap<>();
	private static boolean isInit = false;

	// init
	static {
		log.info("init Dictionary");
		if (!isInit) {
			init();
//            filter();
			isInit = true;
		}
		log.info("init Dictionary end");
	}

	private static DItem filter(DItem dItem, List<String> typeList) throws CloneNotSupportedException {
		if (null != dItem) {
			if (null != typeList && typeList.contains(dItem.getType())) {
				DItem clone = (DItem) dItem.clone();
				if (null != clone.getItems()) {
					List<DItem> list = new ArrayList<>();
					for (DItem d :
							clone.getItems()) {
						DItem d1 = (DItem) d.clone();
						d1.setItems(null);
						list.add(d1);
					}
					clone.setItems(list);
					return clone;
				}

			}
			return dItem;
		}
		return null;

	}


	private static void init() {

		if (initCompanyJsonPath()) {

			for (Map.Entry<String, String> entry :
					companyJsonPath.entrySet()) {

				builtDItemByPath(entry.getKey(), entry.getValue());
			}

//            log.info("companyDict-" + JSON.toJSONString(companyDict));
		}


	}

	private static void builtDItemByPath(String companyId, String jsonPath) {
		log.info("builtDItemByPath-" + companyId + "-" + jsonPath);
		ClassPathResource resource = new ClassPathResource(jsonPath);
		log.info(resource.getDescription());
		Map<String, DItem> dict = null;

		try {
			URL url = Dictionary.class.getClassLoader().getResource(jsonPath);

			if (url.toString().contains("!/")) {
				log.info("url" + url.toString());
				String jarPath = url.toString().substring(0, url.toString().lastIndexOf("!/") + 2);

				log.info("jarPath" + jarPath);
				URL jarURL = new URL(jarPath);
				JarURLConnection jarCon = (JarURLConnection) jarURL.openConnection();
				JarFile jarFile = jarCon.getJarFile();
				Enumeration<JarEntry> jarEntrys = jarFile.entries();
				while (jarEntrys.hasMoreElements()) {
					JarEntry entry = jarEntrys.nextElement();
					String name = entry.getName();
					log.info("JarEntryName-" + name);
					if (name.startsWith(jsonPath) && !entry.isDirectory()) {
						InputStream fileStream = Dictionary.class.getClassLoader().getResourceAsStream(name);
						if (dict == null) {
							dict = new HashMap<>(20);
						}
						putDictByStream(companyId, dict, fileStream);
						try {
							fileStream.close();
						} catch (Exception e) {
							log.error(e.getMessage(), e);
						}

					}
				}
			} else {

				File file = new File(url.toURI());
				log.info(url.toString() + "-" + file.getAbsolutePath());
				List<File> jsonFiles = getJsonFiles(file);
				dict = parseJsonFile(jsonFiles);
			}


//            log.info("dict-" + JSON.toJSONString(dict));
			companyDict.put(companyId, dict);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private static void putDictByStream(String companyId, Map<String, DItem> dict, InputStream fileStream) throws IOException {

		String jsonStr = IOUtils.toString(fileStream, "utf-8");
		DItem dItem = JSON.parseObject(jsonStr, DItem.class);
		putToTypeList(companyId, dItem);
		putToDict(dict, dItem);
	}

	private static void putToTypeList(String companyId, DItem dItem) {
		List<String> list = typeListMap.get(companyId);
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(dItem.getType());
		typeListMap.put(companyId, list);

		Map<String, DItem> companyMap = companyTypeDict.get(companyId);
		if (companyMap == null) {
			companyMap = new HashMap<>(20);
		}

		companyMap.put(dItem.getType(), dItem);
		companyTypeDict.put(companyId, companyMap);

	}

	private static boolean initCompanyJsonPath() {

		DictionaryEnum[] values = DictionaryEnum.values();
		if (null != values && values.length > 0) {
			for (DictionaryEnum ce :
					values) {
				companyJsonPath.put(ce.getCompanyId(), ce.getPath());
			}
			log.info("companyJsonPath:" + JSON.toJSONString(companyJsonPath));
			return true;
		} else {

			return false;
		}


	}

	private static Map<String, DItem> parseJsonFile(List<File> jsonFiles) throws IOException {
		if (null == jsonFiles || jsonFiles.isEmpty()) {

			return null;
		}
		Map<String, DItem> dict = new HashMap<>(20);
		for (File jsonFile : jsonFiles) {

			String jsonStr = FileUtils.readFileToString(jsonFile, "utf-8");
			DItem dItem = JSON.parseObject(jsonStr, DItem.class);
			putToDict(dict, dItem);
		}

		return dict;

	}

	private static void putToDict(Map<String, DItem> dict, DItem dItem) {
		if (null != dItem) {
			String key = dItem.getKey();
			if (dict.get(key) != null) {
				log.error("key is exist");
			}
			dict.put(key, dItem);
		}

		List<DItem> items = dItem.getItems();
		if (items != null && !items.isEmpty()) {
			for (DItem d :
					items) {
				putToDict(dict, d);
			}


		}

	}


	private static List<File> getJsonFiles(File file) {

		return getJsonFiles(null, file);
	}

	private static List<File> getJsonFiles(List fileList, File file) {
		if (file.isDirectory()) {

			File[] files = file.listFiles();
			for (File f : files) {

				fileList = getJsonFiles(fileList, f);
			}

		} else if (file.getName().endsWith(".json")) {
			if (null == fileList) {
				fileList = new ArrayList();
			}

			fileList.add(file);
		}


		return fileList;
	}

	public static DItem getDictByKey(String companyId, String key) {
		Map<String, DItem> stringDItemMap = companyDict.get(companyId);
		if (null != stringDItemMap && !stringDItemMap.isEmpty()) {

			try {

				return filter(stringDItemMap.get(key), getFilterType());
			} catch (Exception e) {
				return null;
			}
		}

		return null;

	}

	public static List<String> getFilterType() {
		List<String> list = new ArrayList<>();
		list.add("bankDirectDebitBankAccount");
		list.add("bankDirectDebitCreditCard");
		list.add("bankTransferToCustomer");
		return list;
	}

	public static Map<String, DItem> getDictByCompanyId(String companyId) {
		return companyTypeDict.get(companyId);
	}

}
