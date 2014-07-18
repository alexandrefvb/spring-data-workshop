package br.com.tqi.enquete.resource;

import java.util.Date;
import java.util.List;

import br.com.tqi.enquete.Enquete;
import br.com.tqi.enquete.Opcao;
import br.com.tqi.resource.LinkBuilder;
import br.com.tqi.resource.Method;
import br.com.tqi.resource.Resource;

/**
 * Representação de um resource Enquete
 */
public class EnqueteResource extends Resource {

    public static final String URI = "enquetes";

    private Enquete enquete;

    public EnqueteResource(Enquete enquete) {
	this.enquete = enquete;
	// Constrói um linkbuilder com /enquetes/{id}
	LinkBuilder linkBuilder = new LinkBuilder().addPathSegments(URI,
		enquete.getId());
	// Cria o link self
	add(linkBuilder.build());
	if (enquete.isActive()) {
	    // Cria o link votar
	    add(new LinkBuilder().addPathSegments(URI,
		    enquete.getId().toString(), "voto").withRel("votar")
		    .withMethod(Method.POST).build());
	} else {
	    // Cria o link apagar
	    add(linkBuilder.withRel("apagar").withMethod(Method.DELETE).build());
	    if (!enquete.isFinished()) {
		// Cria o link atualizar
		add(linkBuilder.withRel("atualizar").withMethod(Method.PUT)
			.build());
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
