Traffic
=======

Traffic is an http router for java web applications built around the new java 8 ```@FunctionalInterface```.

It is inspired by express' router for node.js.

Currently support GET/POST/PUT/DELETE for building restful API's, and a generic method to add any method.

Usage example
-------------

A ```TestFilter``` class:

```Java
package org.traffic.test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.traffic.TrafficRouter;
import static org.traffic.handler.HandlerBase.CONTINUE;
import static org.traffic.handler.HandlerBase.SKIP_NEXT;

public class TestFilter implements Filter {
	private HashMap<Long, String> users = new HashMap<>();
	private long seq = 1;
	private TrafficRouter router = new TrafficRouter()
		// simplest route
		.get("/", (req, res) -> {
			res.getWriter().println("index!");
			return CONTINUE;
		})
		// example of positional parameters
		.get("/users", (req, res) -> {
			res.getWriter().println(users);
			return CONTINUE;
		})
		.put("/users/new/:name", (req, res, name) -> {
			users.put(seq++, name);
			res.getWriter().println(users);
			return CONTINUE;
		})
		.post("/users/edit/:id/:name", (req, res, id, name) -> {
			if (users.containsKey(Long.parseLong(id))) {
				users.put(Long.parseLong(id), name);
				res.getWriter().println(users);
			} else {
				res.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
			return CONTINUE;
		})
		.delete("/users/:id", (req, res, id) -> {
			users.remove(Long.parseLong(id));
			res.getWriter().println(users);
			return CONTINUE;
		})
		// multiple handlers for same route
		.get("/bookmarks/:id", (req, res, id) -> {
			if (req.getSession().getAttribute("loggedUser") == null) {
				return SKIP_NEXT;
			}
			return CONTINUE;
		}, (req, res, id) -> {
			// if the previous handler does not throw an exception
			Bookmark bookmark = db.fetch((User) req.getAttribute("loggedUser"), id);
			req.setAttribute("bookmark", bookmark);
			req.getRequestDispatcher("/pages/bookmark.jsp").forward(req, res);
			return CONTINUE;
		})
	;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		try {
			if (router.dispatch((HttpServletRequest) request, (HttpServletResponse) response)) {
				return;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		chain.doFilter(request, response);
	}
	@Override public void init(FilterConfig config) throws ServletException {}
	@Override public void destroy() {}
}
```

And a ```web.xml```:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
  <display-name>TrafficTest</display-name>
  <filter>
  	<filter-name>TrafficTest</filter-name>
  	<filter-class>org.traffic.test.TestFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>TrafficTest</filter-name>
  	<url-pattern>*</url-pattern>
  </filter-mapping>
</web-app>
```

A test session (interactive unix shell and curl):

```
$ curl http://localhost:8080/TrafficTest/
index!
$ curl http://localhost:8080/TrafficTest/users
{}
$ curl -X PUT http://localhost:8080/TrafficTest/users/new/dexgeh
{1=dexgeh}
$ curl -X POST http://localhost:8080/TrafficTest/users/edit/1/blah
{1=blah}
$ curl -X DELETE http://localhost:8080/TrafficTest/users/1
{}
```
