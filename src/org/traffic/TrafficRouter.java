package org.traffic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.traffic.handler.Handler;
import org.traffic.handler.Handler1;
import org.traffic.handler.Handler1Multi;
import org.traffic.handler.Handler2;
import org.traffic.handler.Handler2Multi;
import org.traffic.handler.Handler3;
import org.traffic.handler.Handler3Multi;
import org.traffic.handler.Handler4;
import org.traffic.handler.Handler4Multi;
import org.traffic.handler.Handler5;
import org.traffic.handler.Handler5Multi;
import org.traffic.handler.HandlerBase;
import org.traffic.handler.HandlerMulti;
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
					int retval = HandlerBase.END;
					if (route.handlers.length == 1) {
						if (params.size() == 0) {
							((Handler)handler).handle(request, response);
						} else if (params.size() == 1) {
							((Handler1)handler).handle(request, response, params.get(0));
						} else if (params.size() == 2) {
							((Handler2)handler).handle(request, response, params.get(0), params.get(1));
						} else if (params.size() == 3) {
							((Handler3)handler).handle(request, response, params.get(0), params.get(1), params.get(2));
						} else if (params.size() == 4) {
							((Handler4)handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3));
						} else if (params.size() == 5) {
							((Handler5)handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
						}
					} else {
						if (params.size() == 0) {
							retval = ((HandlerMulti)handler).handle(request, response);
						} else if (params.size() == 1) {
							retval = ((Handler1Multi)handler).handle(request, response, params.get(0));
						} else if (params.size() == 2) {
							retval = ((Handler2Multi)handler).handle(request, response, params.get(0), params.get(1));
						} else if (params.size() == 3) {
							retval = ((Handler3Multi)handler).handle(request, response, params.get(0), params.get(1), params.get(2));
						} else if (params.size() == 4) {
							retval = ((Handler4Multi)handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3));
						} else if (params.size() == 5) {
							retval = ((Handler5Multi)handler).handle(request, response, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
						}
					}
					if (retval == HandlerBase.SKIP) {
						break;
					}
				}
				return true;
			}
		}
		return false;
	}

	private TrafficRouter internalAdd(String method, String urlPattern, HandlerBase...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}

	// method below copypasted from codegen output: do not add code manually under this comment

	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.HandlerMulti... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler1 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler1Multi... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler2 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler2Multi... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler3 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler3Multi... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler4 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler4Multi... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler5 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler5Multi... handlers) {
		return internalAdd("CONNECT", urlPattern, handlers);
	}

}
