package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler extends HandlerBase {
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception;
}
