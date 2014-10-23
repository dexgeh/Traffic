package org.traffic.routes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.traffic.handler.HandlerBase;

public class Route {
	public final String method;
	public final Pattern urlPatternRgx;
	public final HandlerBase[] handlers;

	public Route(String method, String urlPattern, HandlerBase... handlers) {
		validateUrlPattern(urlPattern);
		this.method = method;
		this.urlPatternRgx = Pattern.compile("^" + urlPattern.replaceAll("/:[a-zA-Z][a-zA-Z0-9]+", "/([^/]+)") + "$");
		this.handlers = handlers;
	}

	private static Pattern urlPatternFormat = Pattern.compile("^(/|(/[^/]+)+)$");

	private void validateUrlPattern(String urlPattern) {
		if (!urlPatternFormat.matcher(urlPattern).find()) {
			throw new IllegalArgumentException("Invalid url pattern " + urlPattern);
		}
	}

	public List<String> match(HttpServletRequest request) {
		if (!request.getMethod().equals(method)) {
			return null;
		}
		String path = request.getPathInfo();
		if (path == null) {
			path = request.getRequestURI().substring(request.getContextPath().length());
		}
		if (path.indexOf('?') != -1) {
			path = path.substring(0, path.indexOf('?'));
		}
		Matcher matcher = urlPatternRgx.matcher(path);
		if (matcher.find()) {
			List<String> params = new ArrayList<String>();
			for (int i = 1; i <= matcher.groupCount(); i++) {
				params.add(matcher.group(i));
			}
			return params;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Route { method=" + method + " urlPatternRgx=" + urlPatternRgx + " }";
	}
}
