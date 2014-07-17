package br.com.tqi.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource {

    private List<Link> links = new ArrayList<Link>();

    public List<Link> getLinks() {
	return links;
    }

    protected void add(Link link) {
	this.links.add(link);
    }
}
