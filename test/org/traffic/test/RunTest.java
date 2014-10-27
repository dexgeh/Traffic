package org.traffic.test;

import org.traffic.TrafficRouter;

public class RunTest {
	@FunctionalInterface public static interface I {
		public void m();
	}
	public static void assertException(I i) {
		Exception ex = null;
		try {
			i.m();
		} catch (Exception e) {
			ex = e;
		}
		if (ex == null) {
			throw new RuntimeException();
		}
	}
	public static void assertFalse(boolean b) {
		if (b) throw new RuntimeException();
	}
	public static void assertTrue(boolean b) {
		if (!b) throw new RuntimeException();
	}
	public static void main(String[] args) throws Exception {
		TrafficRouter router = new TrafficRouter();
		router.get("/", (req, res) -> {});
		assertTrue(router.dispatch(new FakeRequest("GET", "/"), null));
		assertFalse(router.dispatch(new FakeRequest("GET", "/x"), null));
		assertFalse(router.dispatch(new FakeRequest("POST", "/"), null));
		assertException(() -> { router.get("/", (req, res, param) -> {}); });
		router.put("/path/:variable", (req, res, param) -> {});
		assertFalse(router.dispatch(new FakeRequest("PUT", "/path/"), null));
		assertTrue(router.dispatch(new FakeRequest("PUT", "/path/var"), null));
		assertFalse(router.dispatch(new FakeRequest("PUT", "/path/var/x"), null));
		assertException(() -> { router.delete("/path/:param", (req, res) -> {}); });
		router.delete("/path/:param", (req, res, param) -> {});
	}
}
