package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.traffic.HandlerResult;

@FunctionalInterface
public interface Handler2Multi extends HandlerBase {
	public HandlerResult handle(
		HttpServletRequest request,
		HttpServletResponse response,
		String param1,
		String param2)
		throws Exception;
}
