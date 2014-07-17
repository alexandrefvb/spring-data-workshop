package br.com.tqi.resource;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public abstract class ResourceList<R extends Resource, T> extends Resource {

    private List<R> resourceList;

    private String uriTemplate;

    private Page<T> page;

    protected ResourceList(Page<T> entityPage, String uriTemplate) {
	this.resourceList = new ArrayList<R>(entityPage.getContent().size());
	this.uriTemplate = uriTemplate;
	this.page = entityPage;
	populateResourceList(entityPage);
	addSelfLink();
	addNavigationLinks();
    }

    private void addSelfLink() {
	add(new Link(uriFor(this.page.getNumber())));
    }

    private String uriFor(int pageNumber) {
	return String.format(uriTemplate, pageNumber,
		this.page.getSize());
    }

    private void addNavigationLinks() {
	if (this.page.hasNext()) {
	    add(new Link("next", uriFor(this.page.getNumber() + 1)));
	}
	if (this.page.hasPrevious()) {
	    add(new Link("previous", uriFor(this.page.getNumber() - 1)));
	}
    }

    protected List<R> resourceList() {
	return this.resourceList;
    }

    @SuppressWarnings("unchecked")
    private Class<R> resourceType() {
	return (Class<R>) ((ParameterizedType) getClass()
		.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private void populateResourceList(Page<T> entityPage) {
	try {
	    if (!entityPage.getContent().isEmpty()) {
		Constructor<R> constructor = resourceType().getConstructor(
			entityPage.getContent().get(0).getClass());
		for (T model : entityPage) {
		    this.resourceList.add(constructor.newInstance(model));
		}
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
