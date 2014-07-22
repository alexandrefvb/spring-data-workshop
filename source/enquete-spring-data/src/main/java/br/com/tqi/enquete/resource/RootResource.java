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
				.addPathSegments(EnqueteResource.URI).build());
		add(linkBuilder.withRel("nova-enquete").withMethod(Method.POST).build());
		add(new LinkBuilder().withRel("enquetes-ativas").withMethod(Method.GET)
				.addPathSegments(EnqueteResource.URI, "ativas").build());
		add(new LinkBuilder().withRel("enquetes-finalizadas")
				.addPathSegments(EnqueteResource.URI, "finalizadas").build());
		add(new LinkBuilder().withRel("enquetes-nao-iniciadas")
				.addPathSegments(EnqueteResource.URI, "nao-iniciadas").build());
	}
}
