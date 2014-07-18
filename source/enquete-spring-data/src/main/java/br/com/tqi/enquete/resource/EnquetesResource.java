package br.com.tqi.enquete.resource;

import org.springframework.data.domain.Page;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.resource.LinkBuilder;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.PageResource;

public class EnquetesResource extends PageResource<EnqueteResource, Enquete> {

    public EnquetesResource(Page<Enquete> page) {
	super(page);
	add(new LinkBuilder().withRel("nova-enquete").withMethod(Method.POST)
		.addPathSegments(EnqueteResource.URI).build());
    }
}
