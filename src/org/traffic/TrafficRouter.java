package org.traffic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.traffic.handler.Handler;
import org.traffic.handler.Handler1;
import org.traffic.handler.Handler2;
import org.traffic.handler.Handler3;
import org.traffic.handler.Handler4;
import org.traffic.handler.Handler5;
import org.traffic.handler.HandlerBase;
import org.traffic.routes.Route;

public class TrafficRouter {
	private List<Route> routes = new ArrayList<>();

	@Override
	public String toString() {
		return routes.toString();
	}

	public boolean dispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		for (Route route : routes) {
			List<String> params = null;
			if ((params = route.matchGetParams(request)) != null) {
				for (HandlerBase handler : route.handlers) {
					switch (params.size()) {
					case 0:
						((Handler) handler).handle(request, response);
						break;
					case 1:
						((Handler1) handler).handle(request, response, params.get(0));
						break;
					case 2:
						((Handler2) handler).handle(request, response, params.get(0), params.get(1));
						break;
					case 3:
						((Handler3) handler).handle(request, response, params.get(0), params.get(1), params.get(2));
						break;
					case 4:
						((Handler4) handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3));
						break;
					case 5:
						((Handler5) handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
						break;
					}
				}
				return true;
			}
		}
		return false;
	}
	public TrafficRouter add(String method, String urlPattern, Handler...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler... handlers) {
		return add("DELETE", urlPattern, handlers);
	}

	public TrafficRouter add(String method, String urlPattern, Handler1...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler1... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler1... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler1... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler1... handlers) {
		return add("DELETE", urlPattern, handlers);
	}

	public TrafficRouter add(String method, String urlPattern, Handler2...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler2... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler2... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler2... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler2... handlers) {
		return add("DELETE", urlPattern, handlers);
	}

	public TrafficRouter add(String method, String urlPattern, Handler3...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler3... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler3... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler3... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler3... handlers) {
		return add("DELETE", urlPattern, handlers);
	}

	public TrafficRouter add(String method, String urlPattern, Handler4...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler4... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler4... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler4... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler4... handlers) {
		return add("DELETE", urlPattern, handlers);
	}

	public TrafficRouter add(String method, String urlPattern, Handler5...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}
	public TrafficRouter get(String urlPattern, Handler5... handlers) {
		return add("GET", urlPattern, handlers);
	}

	public TrafficRouter post(String urlPattern, Handler5... handlers) {
		return add("POST", urlPattern, handlers);
	}

	public TrafficRouter put(String urlPattern, Handler5... handlers) {
		return add("PUT", urlPattern, handlers);
	}

	public TrafficRouter delete(String urlPattern, Handler5... handlers) {
		return add("DELETE", urlPattern, handlers);
	}
}
