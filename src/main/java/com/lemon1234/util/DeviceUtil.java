package com.lemon1234.util;

/**
 * 判断是pc还是手机端请求
 * 
 * @date 2021年1月16日
 * @author lemon1234.zhihua
 */
public class DeviceUtil {

	/**
	 * @param requestHeader
	 * @return
	 */
	public static boolean isMobileDevice(String requestHeader){
		String[] deviceArray = new String[]{"android", "iphone", "iphone"};
		if(requestHeader == null) {
			return false;
		} else {
			requestHeader = requestHeader.toLowerCase();
			for(int i = 0; i<deviceArray.length; i++){
	            if(requestHeader.indexOf(deviceArray[i])>0) {
	                return true;
	            }
	        }
		}
		return false;
	}
}
