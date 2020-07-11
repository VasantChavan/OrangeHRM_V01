package com.orangeHrm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	public Properties prop;

	public ConfigDataProvider() {

		try {
			//String configpath = System.getProperty(("user.dir") + "//Config//config.properties");

			File fs = new File(".//Config//config.properties");

			FileInputStream fins = new FileInputStream(fs);

			prop = new Properties();

			prop.load(fins);

		} catch (Exception e) {
			System.out.println("File not found :" + e.getMessage());

		}

	}

	public String keySearch(String key) {
		return prop.getProperty(key);
	}

	public String getUser() {
		return prop.getProperty("username");
	}

	public String getPass() {
		return prop.getProperty("password");
	}

	public String getAppUrl() {
		return prop.getProperty("url");
	}

	public String getBrowser() {
		return prop.getProperty("browser");
	}

	public String getIEPath() {
		return prop.getProperty("iepath");
	}

	public String getFirefoxPath() {
		return prop.getProperty("firefoxpath");
	}

	public String getChromPath() {
		return prop.getProperty("crompath");
	}
}
