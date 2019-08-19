package com.neo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * Created by zhangjunwen on 2018/7/27
 *
 * @author zhangjunwen
 * @date 2018/7/27
 */
@Slf4j
public class FrontEndDictionary {


	private static boolean isInit = false;
	private static String front_end_path = "static/dict/front_end/";
	private static Map<String, JSONArray> resultDict = new HashMap<>();

	// init
	static {
		log.info("init FrontEndDictionary");
		if (!isInit) {
			init();
//            filter();
			isInit = true;
		}
		log.info("init FrontEndDictionary end");
	}


	private static void init() {

		builtDItemByPath(front_end_path);

	}

	private static void builtDItemByPath(String jsonPath) {
		log.info("buildPath--" + jsonPath);
		ClassPathResource resource = new ClassPathResource(jsonPath);
		log.info(resource.getDescription());

		try {
			URL url = FrontEndDictionary.class.getClassLoader().getResource(jsonPath);

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
						InputStream fileStream = FrontEndDictionary.class.getClassLoader().getResourceAsStream(name);
						String fileName = name.substring(name.lastIndexOf("/") + 1);
						putDictByStream(fileName, fileStream);
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
				parseJsonFile(jsonFiles);
			}


//            log.info("dict-" + JSON.toJSONString(dict));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private static void putDictByStream(String dictName, InputStream fileStream) throws IOException {

		String jsonStr = IOUtils.toString(fileStream, "utf-8");
		resultDict.put(dictName, JSON.parseArray(jsonStr));

	}


	private static void parseJsonFile(List<File> jsonFiles) throws IOException {
		if (null == jsonFiles || jsonFiles.isEmpty()) {

			return;
		}
		for (File jsonFile : jsonFiles) {

			String jsonStr = FileUtils.readFileToString(jsonFile, "utf-8");
			String name = jsonFile.getName();
			resultDict.put(name, JSON.parseArray(jsonStr));
		}


	}


	private static List<File> getJsonFiles(File file) {

		return getJsonFiles(null, file);
	}

	@SuppressWarnings("Duplicates")
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

	public static Map<String, JSONArray> getResultDict() {
		return resultDict;
	}


//	public static void main(String[] args) {
//
//		Map<String, JSONArray> resultDict = FrontEndDictionary.getResultDict();
//
//	}

}
