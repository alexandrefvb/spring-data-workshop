package br.com.tqi.enquete.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tqi.enquete.resource.RootResource;

@RestController
public class RootEndpoint {

	@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public RootResource get() {
		return new RootResource();
	}

}
