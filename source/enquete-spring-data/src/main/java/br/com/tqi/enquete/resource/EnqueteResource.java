package br.com.tqi.enquete.resource;

import java.util.Date;
import java.util.List;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.enquete.Opcao;
import br.com.tqi.resource.Link;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.Resource;

/**
 * Representação de um resource Enquete
 */
public class EnqueteResource extends Resource {

    public static final String URI = "/enquetes";

    private Enquete enquete;

    public EnqueteResource(Enquete enquete) {
	this.enquete = enquete;
	String href = URI + "/" + enquete.getId();
	add(new Link(href));
	if (enquete.isActive()) {
	    add(new Link("votar", href + "/voto", Method.POST));
	} else {
	    add(new Link("apagar", href, Method.DELETE));
	    if (!enquete.isFinished()) {
		add(new Link("atualizar", href, Method.PUT));
	    }
	}
    }

    public Long getId() {
	return enquete.getId();
    }

    public String getPergunta() {
	return enquete.getPergunta();
    }

    public List<Opcao> getOpcoes() {
	return enquete.getOpcoes();
    }

    public Date getInicio() {
	return enquete.getInicio();
    }

    public Date getFim() {
	return enquete.getFim();
    }

}
