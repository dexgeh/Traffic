Traffic
=======

Traffic is an http router for java web applications built around the new java 8 ```@FunctionalInterface```.

It is inspired by express' router for node.js.

Currently supported http method are:

- GET
- POST
- PUT
- DELETE
- HEAD
- OPTIONS
- TRACE
- CONNECT

A ```TrafficRouter``` has methods for adding routes, named after lowercase HTTP methods, and a dispatch method to execute the routing; it will return a ```true``` value if a route matching the request was found and ```false``` otherwise.

It also has generic ```add``` method for  non-standard HTTP methods.

A route is defined using the method matching the HTTP method, and a path with the syntax show below:

```
.get("/path/:variable", (req, res, variable) -> {
	// your code here
})
```

Valid path are ```/``` or multiple path element separated with ```/```.

A path element can be a fixed string or a variable, prefixed with ```:```.

Variable names are matched against a regex ```[a-zA-Z][a-zA-Z0-9]+```, and are captured in the order that they have in the url and given to the handler.

It is mandatory to have matching url and signatures.

Handlers actually support from 0 to 5 variables, and it is possible to add more than one handler to a single route; they are processed in in the order specified, and they have to signal the router about the behavior processing: continue processing other handlers or skip them.

It is mandatory for multiple handlers to have matching url and signature. 

Usage example
-------------

A ```SampleFilter``` class show how to use the router:

```Java
package org.traffic.test;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;

import org.traffic.TrafficRouter;
import static org.traffic.HandlerResult.SKIP;
import static org.traffic.HandlerResult.CONTINUE;

public class SampleFilter implements Filter {
	private HashMap<Long, String> users = new HashMap<>();
	private long seq = 1;
	private TrafficRouter router = new TrafficRouter()
		// simplest route
		.get("/", (req, res) -> {
			res.getWriter().println("index!");
		})
		// example of positional parameters
		.get("/users", (req, res) -> {
			res.getWriter().println(users);
		})
		.put("/users/new/:name", (req, res, name) -> {
			users.put(seq++, name);
			res.getWriter().println(users);
		})
		.post("/users/edit/:id/:name", (req, res, id, name) -> {
			if (users.containsKey(Long.parseLong(id))) {
				users.put(Long.parseLong(id), name);
				res.getWriter().println(users);
			} else {
				res.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		})
		.delete("/users/:id", (req, res, id) -> {
			users.remove(Long.parseLong(id));
			res.getWriter().println(users);
		})
		// multiple handlers for same route
		.get("/bookmarks/:id", (req, res, id) -> {
			if (req.getSession().getAttribute("loggedUser") == null) {
				return SKIP;
			}
			return CONTINUE;
		}, (req, res, id) -> {
			// if the previous handler return CONTINUE
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

A ```web.xml``` file to map the filter to any request to the webapp:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
  <display-name>TrafficTest</display-name>
  <filter>
  	<filter-name>SampleFilter</filter-name>
  	<filter-class>org.traffic.test.SampleFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>SampleFilter</filter-name>
  	<url-pattern>*</url-pattern>
  </filter-mapping>
</web-app>
```

A test session (interactive unix shell and curl) showing some requests:

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
