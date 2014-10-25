package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler2Multi extends HandlerBase {
	public int handle(
		HttpServletRequest request,
		HttpServletResponse response,
		String param1,
		String param2)
		throws Exception;
}
