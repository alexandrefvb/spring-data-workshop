package br.com.tqi.enquete.resource;

import br.com.tqi.resource.LinkBuilder;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.Resource;

/**
 * Resource raiz. Mostra as operações disponíveis.
 */
public class RootResource extends Resource {

    public RootResource() {
	LinkBuilder linkBuilder = new LinkBuilder();
	add(linkBuilder.buildSelfFromCurrentRequest());
	add(linkBuilder.withRel("enquetes")
		.withPathSegments(EnqueteResource.URI).build());
	add(linkBuilder.withRel("nova-enquete").withMethod(Method.POST).build());
    }
}
