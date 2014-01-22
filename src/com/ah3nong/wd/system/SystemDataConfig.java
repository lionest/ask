package com.ah3nong.wd.system;

public class SystemDataConfig {
	private static String siteUrl;
	private static String casUrl;
	private static String passportUrl;
	private static String regUrl;
	private static String editUserUrl;
	private static String editUserAvatarUrl;
	private static String loginUrl;
	private static String loginOutUrl;
	private static String importExcelUrl;
	
	public static String getLoginUrl() {
		return loginUrl;
	}
	
	public static void setLoginUrl(String loginUrl) {
		SystemDataConfig.loginUrl = loginUrl;
	}
	
	public static String getLoginOutUrl() {
		return loginOutUrl;
	}
	
	public static void setLoginOutUrl(String loginOutUrl) {
		SystemDataConfig.loginOutUrl = loginOutUrl;
	}

	public static String getSiteUrl() {
		return siteUrl;
	}

	public static void setSiteUrl(String siteUrl) {
		SystemDataConfig.siteUrl = siteUrl;
	}

	public static String getCasUrl() {
		return casUrl;
	}

	public static void setCasUrl(String casUrl) {
		SystemDataConfig.casUrl = casUrl;
	}

	public static String getImportExcelUrl() {
		return importExcelUrl;
	}

	public static void setImportExcelUrl(String importExcelUrl) {
		SystemDataConfig.importExcelUrl = importExcelUrl;
	}

	public static String getRegUrl() {
		return regUrl;
	}

	public static void setRegUrl(String regUrl) {
		SystemDataConfig.regUrl = regUrl;
	}

	public static String getPassportUrl() {
		return passportUrl;
	}

	public static void setPassportUrl(String passportUrl) {
		SystemDataConfig.passportUrl = passportUrl;
	}

	public static String getEditUserUrl() {
		return editUserUrl;
	}

	public static void setEditUserUrl(String editUserUrl) {
		SystemDataConfig.editUserUrl = editUserUrl;
	}

	public static String getEditUserAvatarUrl() {
		return editUserAvatarUrl;
	}

	public static void setEditUserAvatarUrl(String editUserAvatarUrl) {
		SystemDataConfig.editUserAvatarUrl = editUserAvatarUrl;
	}
	
}
