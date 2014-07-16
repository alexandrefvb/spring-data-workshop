package br.com.tqi.resource;

public class Link {

	private String rel;

	private String href;

	private Method method;

	public Link(String href) {
		this("self", href);
	}

	public Link(String rel, String href) {
		this(rel, href, Method.GET);
	}

	public Link(String rel, String href, Method method) {
		this.rel = rel;
		this.href = href;
		this.method = method;
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}

	public Method getMethod() {
		return method;
	}
}
