package br.com.tqi.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Link {

    private String rel;

    private String href;

    private Method method;

    private String consumes;

    private String produces;

    Link(String rel, String href, Method method, String consumes,
	    String produces) {
	this.rel = rel;
	this.href = href;
	this.method = method;
	this.consumes = consumes;
	this.produces = produces;
    }

    @JsonIgnore
    public String getRel() {
	return rel;
    }

    public String getHref() {
	return href;
    }

    public Method getMethod() {
	return method;
    }

    public String getConsumes() {
	return consumes;
    }

    public String getProduces() {
	return produces;
    }
}
