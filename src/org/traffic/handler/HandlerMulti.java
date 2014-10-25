package org.traffic.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface HandlerMulti extends HandlerBase {
	public int handle(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception;
}
