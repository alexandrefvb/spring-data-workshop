package br.com.tqi.enquete.resource;

import br.com.tqi.resource.Link;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.Resource;

/**
 * Resource raiz. Mostra as operações disponíveis.
 */
public class RootResource extends Resource {

	public static final String URI = "/";

	public RootResource() {
		add(new Link(URI));
		add(new Link("enquetes", EnqueteResource.URI));
		add(new Link("nova-enquete", EnqueteResource.URI, Method.POST));
	}
}
