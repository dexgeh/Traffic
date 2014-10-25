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
	public final int paramArity;

	public Route(String method, String urlPattern, HandlerBase... handlers) {
		validateUrlPattern(urlPattern);
		this.method = method;
		this.urlPatternRgx = urlPatternToRegex(urlPattern);
		this.handlers = handlers;
		this.paramArity = matchGetParams(method, urlPattern).size();
		checkHandlersArity();
	}

	private static Pattern urlPatternFormat = Pattern.compile("^(/|(/[^/]+)+)$");

	private static void validateUrlPattern(String urlPattern) {
		if (!urlPatternFormat.matcher(urlPattern).find()) {
			throw new IllegalArgumentException("Invalid url pattern " + urlPattern);
		}
	}

	private void checkHandlersArity() {
		for (HandlerBase handler : handlers) {
			if (handler.getClass().getSimpleName().indexOf(String.valueOf(paramArity)) == -1) {
				throw new IllegalArgumentException("Handler signature does not match the url number of variables");
			}
		}
	}

	private String escapeRegexChars(String urlPattern) {
		String toEscape = ".+*?^$[](){}";
		StringBuilder out = new StringBuilder();
		for (char c : urlPattern.toCharArray()) {
			if (toEscape.indexOf(c) != -1) {
				out.append("\\").append(c);
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

	private Pattern urlPatternToRegex(String urlPattern) {
		return Pattern.compile("^" + escapeRegexChars(urlPattern).replaceAll("/:[a-zA-Z][a-zA-Z0-9]+", "/([^/]+)") + "$");
	}

	public List<String> matchGetParams(HttpServletRequest request) {
		String method = request.getMethod();
		String path = request.getPathInfo();
		if (path == null) {
			path = request.getRequestURI().substring(request.getContextPath().length());
		}
		if (path.indexOf('?') != -1) {
			path = path.substring(0, path.indexOf('?'));
		}
		return matchGetParams(method, path);
	}

	private List<String> matchGetParams(String method, String path) {
		if (!method.equals(this.method)) {
			return null;
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
