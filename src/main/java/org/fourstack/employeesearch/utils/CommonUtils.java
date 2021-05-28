package org.fourstack.employeesearch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 * Utility Method to read the content of a file under resource folder using
	 * ResourceStream. Method requires ClassLoader instance of the class from where
	 * the method is called.
	 * 
	 * @param fileNamePath File path with sub folder name (under resources)
	 * @param classLoader  ClassLoader Object
	 * @return Content of File in String format.
	 */
	public static String getFileContentUsingResourceStream(String fileNamePath, ClassLoader classLoader) {
		log.info("CommonUtils:getFileContentUsingResourceStream() - Start >> {}", fileNamePath);
		StringBuffer content = new StringBuffer();

		try (InputStream inputStream = classLoader.getResourceAsStream(fileNamePath);
				InputStreamReader streamreader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamreader)) {
			String line;
			while ((line = reader.readLine()) != null)
				content.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("CommonUtils:getFileContentUsingResourceStream() - End >> {}", fileNamePath);
		return content.toString();
	}

	public static <T> T deserializeJsonStringToResource(String content, Class<T> valueType) {
		try {
			return (T) objectMapper.readValue(content, valueType);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
