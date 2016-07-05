/**   
 * 类名：Main
 *
 */
package com.cybbj;

import com.cybbj.server.HttpServer;

/** 
 * Main: 主入口
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-7-4 v1.0 15989 新建 
 */
public class Main {
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		try {
			httpServer.start(8886);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
