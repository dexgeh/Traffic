package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler3 extends HandlerBase {
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response,
		String param1,
		String param2,
		String param3)
		throws Exception;
}
