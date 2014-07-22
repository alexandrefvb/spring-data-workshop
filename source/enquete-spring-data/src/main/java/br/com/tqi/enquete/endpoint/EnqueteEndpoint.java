package br.com.tqi.enquete.endpoint;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.enquete.EnqueteService;
import br.com.tqi.enquete.Opcao;
import br.com.tqi.enquete.exception.EnqueteAtivaException;
import br.com.tqi.enquete.exception.EnqueteFinalizadaException;
import br.com.tqi.enquete.exception.EnqueteInativaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.EnqueteNaoEncontradaException;
import br.com.tqi.enquete.exception.OpcaoNaoEncontradaException;
import br.com.tqi.enquete.resource.EnqueteResource;
import br.com.tqi.enquete.resource.EnquetesResource;

@RestController
@RequestMapping(EnqueteResource.URI)
public class EnqueteEndpoint {

	@Autowired
	private EnqueteService service;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnquetesResource find(
			@RequestParam(value = "pergunta", required = false) String pergunta,
			@PageableDefault(page = 0, size = 10) Pageable pageable)
			throws UnsupportedEncodingException {
		return new EnquetesResource(this.service.find(pergunta, pageable));
	}

	@RequestMapping(value = "/ativas", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnquetesResource findActive(
			@RequestParam(value = "pergunta", required = false) String pergunta,
			@PageableDefault(page = 0, size = 10) Pageable pageable)
			throws UnsupportedEncodingException {

		return new EnquetesResource(this.service.findActive(pergunta, pageable));
	}

	@RequestMapping(value = "/finalizadas", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnquetesResource findFinished(
			@RequestParam(value = "pergunta", required = false) String pergunta,
			@PageableDefault(page = 0, size = 10) Pageable pageable)
			throws UnsupportedEncodingException {
		return new EnquetesResource(this.service.findFinished(pergunta,
				pageable));
	}

	@RequestMapping(value = "/nao-iniciadas", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnquetesResource findNotInitialized(
			@RequestParam(value = "pergunta", required = false) String pergunta,
			@PageableDefault(page = 0, size = 10) Pageable pageable)
					throws UnsupportedEncodingException {
		return new EnquetesResource(this.service.findNotInitialized(pergunta,
				pageable));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public EnqueteResource create(@RequestBody Enquete enquete)
			throws EnqueteInvalidaException {
		return new EnqueteResource(this.service.create(enquete));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EnqueteResource get(@PathVariable("id") Long id)
			throws EnqueteNaoEncontradaException {
		return new EnqueteResource(this.service.load(id));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public EnqueteResource put(@PathVariable("id") Long id,
			@RequestBody Enquete data) throws EnqueteNaoEncontradaException,
			EnqueteAtivaException, EnqueteFinalizadaException,
			EnqueteInvalidaException {
		return new EnqueteResource(this.service.update(id, data));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id)
			throws EnqueteNaoEncontradaException, EnqueteAtivaException {
		this.service.delete(id);
	}

	@RequestMapping(value = "{id}/voto", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vote(@PathVariable("id") Long id, @RequestBody Opcao opcao)
			throws EnqueteNaoEncontradaException, EnqueteInativaException,
			OpcaoNaoEncontradaException {
		this.service.vote(id, opcao.getTexto());
	}

	@ExceptionHandler({ EnqueteInvalidaException.class,
			OpcaoNaoEncontradaException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badRequestExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler({ EnqueteNaoEncontradaException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFoundExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler({ EnqueteAtivaException.class,
			EnqueteInativaException.class, EnqueteFinalizadaException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public String conflictExceptionHandler(Exception e) {
		return e.getMessage();
	}
}
