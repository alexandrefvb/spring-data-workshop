package br.com.tqi.enquete.resource;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.resource.Link;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.ResourceList;

public class EnquetesResource extends ResourceList<EnqueteResource, Enquete> {

	public EnquetesResource(Page<Enquete> page, String uriTemplate) {
		super(page, uriTemplate);
		add(new Link("nova-enquete", EnqueteResource.URI, Method.POST));
	}

	public List<EnqueteResource> getEnquetes() {
		return resourceList();
	}

}
