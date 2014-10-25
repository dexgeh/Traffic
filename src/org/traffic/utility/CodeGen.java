package org.traffic.utility;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.traffic.handler.Handler0;
import org.traffic.handler.Handler0Multi;
import org.traffic.handler.Handler1;
import org.traffic.handler.Handler1Multi;
import org.traffic.handler.Handler2;
import org.traffic.handler.Handler2Multi;
import org.traffic.handler.Handler3;
import org.traffic.handler.Handler3Multi;
import org.traffic.handler.Handler4;
import org.traffic.handler.Handler4Multi;
import org.traffic.handler.Handler5;
import org.traffic.handler.Handler5Multi;

public class CodeGen {
	private static String readResourceAsUTF8String(String fileName) throws Exception {
		InputStream in = CodeGen.class.getClassLoader().getResourceAsStream("org/traffic/utility/" + fileName);
		byte[] buffer = new byte[4096];
		int len;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		return new String(out.toByteArray(), Charset.forName("UTF-8"));
	}
	private static String template(String template, HashMap<String, String> params) {
		String output = template;
		for (String key : params.keySet()) {
			output = output.replaceAll("\\{" + key + "\\}", params.get(key));
		}
		return output;
	}
	private static Class<?>[] handlerClasses = new Class<?>[] {
		Handler0.class, Handler0Multi.class,
		Handler1.class, Handler1Multi.class,
		Handler2.class, Handler2Multi.class,
		Handler3.class, Handler3Multi.class,
		Handler4.class, Handler4Multi.class,
		Handler5.class, Handler5Multi.class,};

	private static String[] httpMethods = new String[] {"GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "TRACE", "CONNECT"};

	public static void main(String[] args) throws Exception {
		String add = readResourceAsUTF8String("add.template");
		String addMulti = readResourceAsUTF8String("addMulti.template");
		String method = readResourceAsUTF8String("method.template");
		String methodMulti = readResourceAsUTF8String("methodMulti.template");
		String page = readResourceAsUTF8String("page.template");
		for (Class<?> c : handlerClasses) {
			boolean multi = c.getName().endsWith("Multi");
			HashMap<String, String> params = new HashMap<>();
			params.put("className", c.getName());
			System.out.println(template(multi ? addMulti : add, params));
			for (String methodUpcase : httpMethods) {
				String methodLowcase = methodUpcase.toLowerCase();
				params.put("methodUpcase", methodUpcase);
				params.put("methodLowcase", methodLowcase);
				System.out.println(template(multi ? methodMulti : method, params));
			}
		}
		for (String methodUpcase : httpMethods) {
			String methodLowcase = methodUpcase.toLowerCase();
			HashMap<String, String> params = new HashMap<>();
			params.put("methodUpcase", methodUpcase);
			params.put("methodLowcase", methodLowcase);
			System.out.println(template(page, params));
		}
	}
}
