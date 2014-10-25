package org.traffic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.traffic.handler.Handler0;
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
import org.traffic.handler.Handler0Multi;
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
					HandlerResult retval = HandlerResult.UNDEFINED;
					if (route.handlers.length == 1) {
						if (params.size() == 0) {
							((Handler0)handler).handle(request, response);
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
							retval = ((Handler0Multi)handler).handle(request, response);
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
					if (retval == HandlerResult.SKIP) {
						break;
					}
				}
				return true;
			}
		}
		return false;
	}

	private TrafficRouter internalAddPage(String method, String urlPattern, String dispatchTarget) {
		List<String> paramNames = Route.getParamNames(urlPattern);
		if (paramNames.size() == 0) {
			internalAdd(method, urlPattern, (Handler0) (req, res) -> {
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		} else if (paramNames.size() == 1) {
			internalAdd(method, urlPattern, (Handler1) (req, res, param0) -> {
				req.setAttribute(paramNames.get(0), param0);
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		} else if (paramNames.size() == 2) {
			internalAdd(method, urlPattern, (Handler2) (req, res, param0, param1) -> {
				req.setAttribute(paramNames.get(0), param0);
				req.setAttribute(paramNames.get(1), param1);
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		} else if (paramNames.size() == 3) {
			internalAdd(method, urlPattern, (Handler3) (req, res, param0, param1, param2) -> {
				req.setAttribute(paramNames.get(0), param0);
				req.setAttribute(paramNames.get(1), param1);
				req.setAttribute(paramNames.get(2), param2);
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		} else if (paramNames.size() == 4) {
			internalAdd(method, urlPattern, (Handler4) (req, res, param0, param1, param2, param3) -> {
				req.setAttribute(paramNames.get(0), param0);
				req.setAttribute(paramNames.get(1), param1);
				req.setAttribute(paramNames.get(2), param2);
				req.setAttribute(paramNames.get(3), param3);
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		} else if (paramNames.size() == 5) {
			internalAdd(method, urlPattern, (Handler5) (req, res, param0, param1, param2, param3, param4) -> {
				req.setAttribute(paramNames.get(0), param0);
				req.setAttribute(paramNames.get(1), param1);
				req.setAttribute(paramNames.get(2), param2);
				req.setAttribute(paramNames.get(3), param3);
				req.setAttribute(paramNames.get(4), param4);
				req.getRequestDispatcher(dispatchTarget).forward(req, res);
			});
		}
		return this;
	}

	private TrafficRouter internalAdd(String method, String urlPattern, HandlerBase...handlers) {
		routes.add(new Route(method, urlPattern, handlers));
		return this;
	}

	// method below copypasted from codegen output: do not add code manually under this comment

	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd(method, urlPattern, handler);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("GET", urlPattern, handler);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("POST", urlPattern, handler);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("PUT", urlPattern, handler);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("DELETE", urlPattern, handler);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("HEAD", urlPattern, handler);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("OPTIONS", urlPattern, handler);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("TRACE", urlPattern, handler);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler0 handler) {
		return internalAdd("CONNECT", urlPattern, handler);
	}
	public TrafficRouter add(String method, String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd(method, urlPattern, handlers);
	}
	public TrafficRouter get(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("GET", urlPattern, handlers);
	}
	public TrafficRouter post(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("POST", urlPattern, handlers);
	}
	public TrafficRouter put(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("PUT", urlPattern, handlers);
	}
	public TrafficRouter delete(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("DELETE", urlPattern, handlers);
	}
	public TrafficRouter head(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("HEAD", urlPattern, handlers);
	}
	public TrafficRouter options(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("OPTIONS", urlPattern, handlers);
	}
	public TrafficRouter trace(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
		return internalAdd("TRACE", urlPattern, handlers);
	}
	public TrafficRouter connect(String urlPattern, org.traffic.handler.Handler0Multi... handlers) {
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
	public TrafficRouter get(String urlPattern, String target) {
		return internalAddPage("GET", urlPattern, target);
	}
	public TrafficRouter post(String urlPattern, String target) {
		return internalAddPage("POST", urlPattern, target);
	}
	public TrafficRouter put(String urlPattern, String target) {
		return internalAddPage("PUT", urlPattern, target);
	}
	public TrafficRouter delete(String urlPattern, String target) {
		return internalAddPage("DELETE", urlPattern, target);
	}
	public TrafficRouter head(String urlPattern, String target) {
		return internalAddPage("HEAD", urlPattern, target);
	}
	public TrafficRouter options(String urlPattern, String target) {
		return internalAddPage("OPTIONS", urlPattern, target);
	}
	public TrafficRouter trace(String urlPattern, String target) {
		return internalAddPage("TRACE", urlPattern, target);
	}
	public TrafficRouter connect(String urlPattern, String target) {
		return internalAddPage("CONNECT", urlPattern, target);
	}

}
