package com.foundation.controller;

import java.util.Map;

import org.junit.Test;

import com.foundation.common.http.HttpClientUtils;
import com.google.common.collect.Maps;

public class EvaluateControllerHttpTest {
	String url = "http://localhost:8080/evaluate/3178416421898240";

	@Test
	public void test() throws Exception {
		Map<String, String> params = Maps.newHashMap();
		params.put("systemId", "yujian");
		params.put("nonce", "123456");
		params.put("timestamp", "1478749975421");
		params.put("signature", "98f4ac9e5e898422a5963240f838b132");
		String result = HttpClientUtils.doPost(url, params);
		System.out.println(result);
	}

}
