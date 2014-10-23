package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler1 extends HandlerBase {
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response,
		String param1)
		throws Exception;
}
