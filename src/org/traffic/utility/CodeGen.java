package org.traffic.utility;

import org.traffic.handler.Handler;
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
import org.traffic.handler.HandlerMulti;

public class CodeGen {
	public static void main(String[] args) {
		for (Class<?> c : new Class<?>[] {
			Handler.class, HandlerMulti.class,
			Handler1.class, Handler1Multi.class,
			Handler2.class, Handler2Multi.class,
			Handler3.class, Handler3Multi.class,
			Handler4.class, Handler4Multi.class,
			Handler5.class, Handler5Multi.class,}) {
			boolean multi = c.getName().endsWith("Multi");
			System.out.println("\tpublic TrafficRouter add(String method, String urlPattern, "+c.getName()+ (multi?"...":"")+" handler"+(multi?"s":"")+") {");
			System.out.println("\t\treturn internalAdd(method, urlPattern, handler"+(multi?"s":"")+");");
			System.out.println("\t}");
			for (String method : new String[]{"GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "TRACE", "CONNECT"}) {
				System.out.println("\tpublic TrafficRouter "+method.toLowerCase()+"(String urlPattern, "+c.getName()+ (multi?"...":"")+" handler"+(multi?"s":"")+") {");
				System.out.println("\t\treturn internalAdd(\""+method+"\", urlPattern, handler"+(multi?"s":"")+");");
				System.out.println("\t}");
			}
		}
	}
}
