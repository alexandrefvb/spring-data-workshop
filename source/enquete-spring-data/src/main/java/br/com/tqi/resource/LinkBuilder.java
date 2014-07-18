package br.com.tqi.resource;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class LinkBuilder {

    private static final String PREVIOUS = "previous";

    private static final String NEXT = "next";

    private static final String SELF = "self";

    public static String PAGE_PARAM_NAME = "page";

    private UriComponentsBuilder uriComponentsBuilder;

    private UriComponentsBuilder currentRequestUriComponentsBuilder;

    private String rel = SELF;

    private Method method = Method.GET;

    private void addPathSegment(Object pathSegment) {
	String s = (pathSegment instanceof String) ? (String) pathSegment
		: pathSegment.toString();
	this.uriComponentsBuilder.pathSegment(s);
    }

    public LinkBuilder() {
	this.uriComponentsBuilder = ServletUriComponentsBuilder
		.fromCurrentServletMapping();
	this.currentRequestUriComponentsBuilder = ServletUriComponentsBuilder
		.fromCurrentRequest();
    }

    public LinkBuilder withMethod(Method method) {
	this.method = method;
	return this;
    }

    public LinkBuilder withRel(String rel) {
	this.rel = rel;
	return this;
    }

    public LinkBuilder withPathSegments(Object... pathSegments) {
	this.uriComponentsBuilder.replacePath(null);
	addPathSegments(pathSegments);
	return this;
    }

    public LinkBuilder addPathSegments(Object... pathSegments) {
	for (Object pathSegment : pathSegments) {
	    addPathSegment(pathSegment);
	}
	return this;
    }

    public Link buildSelfFromCurrentRequest() {
	return new Link(SELF, currentRequestUriComponentsBuilder.build()
		.toString(), this.method, null, null);
    }

    public Link build() {
	String href = this.uriComponentsBuilder.build()
		.toString();
	return new Link(this.rel, href, this.method, null, null);
    }

    public Link buildNextFromCurrentRequest() {
	UriComponents currentRequestUriComponents = this.currentRequestUriComponentsBuilder
		.build();
	String page = currentRequestUriComponents.getQueryParams()
		.getFirst(PAGE_PARAM_NAME);
	if (page == null) {
	    this.currentRequestUriComponentsBuilder.replaceQueryParam(
		    PAGE_PARAM_NAME, 1);
	} else {
	    this.currentRequestUriComponentsBuilder.replaceQueryParam(
		    PAGE_PARAM_NAME,
		    Integer.parseInt(page) + 1);
	}
	return new Link(NEXT, currentRequestUriComponentsBuilder.build()
		.toString(),
		this.method, null, null);
    }

    public Link buildPreviousFromCurrentRequest() {
	UriComponents currentRequestUriComponents = this.currentRequestUriComponentsBuilder
		.build();
	String page = currentRequestUriComponents.getQueryParams()
		.getFirst(PAGE_PARAM_NAME);
	if (page != null) {
	    int newPage = Integer.parseInt(page) - 1;
	    if (newPage != 0) {
		this.currentRequestUriComponentsBuilder.replaceQueryParam(
			PAGE_PARAM_NAME,
			newPage);
	    } else {
		this.currentRequestUriComponentsBuilder.replaceQueryParam(
			PAGE_PARAM_NAME);
	    }
	}
	return new Link(PREVIOUS, currentRequestUriComponentsBuilder.build()
		.toString(),
		this.method, null, null);
    }
}