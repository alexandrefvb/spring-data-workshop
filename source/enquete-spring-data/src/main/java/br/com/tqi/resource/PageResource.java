package br.com.tqi.resource;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public abstract class PageResource<R extends Resource, T> extends Resource {

    private List<R> content;

    private LinkBuilder linkBuilder;

    private PageInfo pageInfo;

    protected PageResource(Page<T> entityPage) {
	this.content = new ArrayList<R>(entityPage.getContent().size());
	this.pageInfo = new PageInfo(entityPage);
	// Criado aqui pois deve ser criado no contexto da requisição
	this.linkBuilder = new LinkBuilder();
	// Cria o content chamando o construtor do resource R
	populateResourceList(entityPage);
	// Adiciona o link "self" apontando para 
	addSelfLink();
	addNavigationLinks(entityPage);
    }

    private void addSelfLink() {
	add(this.linkBuilder.buildSelfFromCurrentRequest());
    }

    private void addNavigationLinks(Page<T> entityPage) {
	if (entityPage.hasNext()) {
	    add(this.linkBuilder.buildNextFromCurrentRequest());
	}
	if (entityPage.hasPrevious()) {
	    add(this.linkBuilder.buildPreviousFromCurrentRequest());
	}
    }

    public List<R> getContent() {
	return this.content;
    }

    public PageInfo getPageInfo() {
	return pageInfo;
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
		    this.content.add(constructor.newInstance(model));
		}
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
