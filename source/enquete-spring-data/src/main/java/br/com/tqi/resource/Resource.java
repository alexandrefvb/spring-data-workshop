package br.com.tqi.resource;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Resource {

    private Map<String, Link> links = new LinkedHashMap<String, Link>();

    public Map<String, Link> getLinks() {
	return links;
    }

    protected void add(Link link) {
	this.links.put(link.getRel(), link);
    }
}
