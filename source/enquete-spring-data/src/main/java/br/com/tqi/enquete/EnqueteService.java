package br.com.tqi.enquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tqi.enquete.exception.EnqueteAtivaException;
import br.com.tqi.enquete.exception.EnqueteFinalizadaException;
import br.com.tqi.enquete.exception.EnqueteInativaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.EnqueteNaoEncontradaException;
import br.com.tqi.enquete.exception.OpcaoNaoEncontradaException;

@Service
@Transactional(readOnly = true)
public class EnqueteService {

    @Autowired
    private EnqueteRepository repository;

    public Page<Enquete> find(String pergunta, Pageable pageable) {
	if (pergunta == null) {
	    return this.repository.findAll(pageable);
	}
	return this.repository.findByPerguntaContaining(pergunta, pageable);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { EnqueteInvalidaException.class })
    public Enquete update(Long id, Enquete data)
	    throws EnqueteNaoEncontradaException, EnqueteAtivaException,
	    EnqueteFinalizadaException, EnqueteInvalidaException {
	Enquete e = load(id);
	if (e.isActive()) {
	    throw new EnqueteAtivaException();
	}
	if (e.isFinished()) {
	    throw new EnqueteFinalizadaException();
	}
	if (data.getPergunta() != null) {
	    e.setPergunta(data.getPergunta());
	}
	if (data.getInicio() != null) {
	    e.setInicio(data.getInicio());
	}
	if (data.getFim() != null) {
	    e.setFim(data.getFim());
	}
	if (data.getOpcoes() != null) {
	    e.getOpcoes().clear();
	    e.getOpcoes().addAll(data.getOpcoes());
	}
	e.validate();
	// ao sair do método transacionado as alterações serão persistidas
	return e;
    }

    public Enquete load(Long id) throws EnqueteNaoEncontradaException {
	Enquete enquete = this.repository.findOne(id);
	if (enquete == null) {
	    throw new EnqueteNaoEncontradaException();
	}
	return enquete;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Enquete create(Enquete enquete) throws EnqueteInvalidaException {
	enquete.validate();
	return this.repository.save(enquete);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id) throws EnqueteNaoEncontradaException,
	    EnqueteAtivaException {
	Enquete e = load(id);
	if (e.isActive()) {
	    throw new EnqueteAtivaException();
	}
	this.repository.delete(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int vote(Long enqueteId, String textoOpcao)
	    throws EnqueteNaoEncontradaException, OpcaoNaoEncontradaException,
	    EnqueteInativaException {
	Enquete e = load(enqueteId);
	Opcao o = e.findOpcaoToVote(textoOpcao);
	return this.repository.vote(e.getId(), o.getTexto());
    }

    public Page<Enquete> findActive(String pergunta, Pageable pageable) {
	if (pergunta == null) {
	    return this.repository.findActive(pageable);
	}
	return this.repository.findActiveByPerguntaContaining(pergunta,
		pageable);
    }

    public Page<Enquete> findFinished(String pergunta, Pageable pageable) {
	if (pergunta == null) {
	    return this.repository.findFinished(pageable);
	}
	return this.repository.findFinishedByPerguntaContaining(pergunta,
		pageable);
    }

}
