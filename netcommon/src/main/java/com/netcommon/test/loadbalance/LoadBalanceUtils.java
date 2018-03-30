package com.netcommon.test.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/***
 * 负载均衡相关算法
 * 
 * @author bing
 *
 */
public class LoadBalanceUtils {
	static Map<String, Integer> serverIps;
	static Integer pos = 0;
	static {
		serverIps = new HashMap<String, Integer>();
		serverIps.put("192.168.0.1", 1);
		serverIps.put("192.168.0.2", 3);
		serverIps.put("192.168.0.3", 2);
	}

	/***
	 * 轮询算法
	 * 
	 * @return
	 */
	public static String loadBalance() {
		Set<String> serverIpSet = serverIps.keySet();
		List<String> serverIpList = new ArrayList<String>();
		serverIpList.addAll(serverIpSet);
		synchronized (pos) {

			if (pos >= serverIpList.size()) {
				pos = 0;
			}

			serverIpList.get(pos);
			String ip = serverIpList.get(pos);
			pos++;

			return ip;
		}
	}

	/***
	 * 权重轮询算法
	 * 
	 * @return
	 */

	public static String weightBalance() {
		Set<String> serverIpsSet = serverIps.keySet();
		Iterator<String> weightIp = serverIpsSet.iterator();
		List<String> serverIpList = new ArrayList<String>();
		while (weightIp.hasNext()) {
			String ip = weightIp.next();
			Integer weight = serverIps.get(ip);
			for (int i = 0; i < weight; i++) {
				serverIpList.add(ip);
			}
		}
		synchronized (pos) {
			if (pos >= serverIpList.size()) {
				pos = 0;
			}
			String serverIp = serverIpList.get(pos);
			pos++;
			return serverIp;
		}
	}

	/***
	 * 随机
	 * 
	 * @return
	 */
	public static String random() {
		Set<String> serverIpSet = serverIps.keySet();
		List<String> serverIpList = new ArrayList<String>();
		serverIpList.addAll(serverIpSet);
		Random random = new Random();
		int pos = random.nextInt(serverIpList.size());
		String ip = serverIpList.get(pos);
		return ip;
	}

	/***
	 * ip hash
	 * 
	 * @param client
	 * @return
	 */
	public static String hash(String client) {
		int hash = client.hashCode();
		Set<String> serverIpSet = serverIps.keySet();
		List<String> serverIpList = new ArrayList<String>();
		serverIpList.addAll(serverIpSet);
		int pos = hash % serverIpList.size();
		String ip = serverIpList.get(pos);
		return ip;
	}

	public static void main(String[] args) {
		weightBalance();
		System.out.println(LoadBalanceUtils.hash("127.1.0.3"));
	}
}
