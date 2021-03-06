package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler4 extends HandlerBase {
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response,
		String param1,
		String param2,
		String param3,
		String param4)
		throws Exception;
}
