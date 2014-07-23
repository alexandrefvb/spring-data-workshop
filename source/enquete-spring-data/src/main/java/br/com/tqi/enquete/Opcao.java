package br.com.tqi.enquete;

import static br.com.tqi.enquete.exception.EnqueteInvalidaException.throwIf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.tqi.enquete.exception.EnqueteInvalidaException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Opção de uma enquete.
 */
@Entity
@JsonInclude(Include.NON_NULL)
public class Opcao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String texto;

	private long votos;

	@ManyToOne
	@JoinColumn(name = "enquete_id", insertable = false, updatable = false)
	private Enquete enquete;

	/**
	 * @return Texto da opção.
	 */
	public String getTexto() {
		return this.texto;
	}

	/**
	 * @return Número de votos que a opção possui.
	 */
	public Long getVotos() {
		return (enquete != null && enquete.isFinished()) ? this.votos : null;
	}

	/**
	 * Valida a opção da enquete.
	 * 
	 * @throws EnqueteInvalidaException
	 *             Caso a opção esteja inválida.
	 */
	public void validate() throws EnqueteInvalidaException {
		throwIf(this.texto == null || "".equals(this.texto.trim()),
				"Uma das opções foi informada sem texto ou com o texto em branco.");
		this.texto = this.texto.trim();
		// Zera o número de votos da opção (caso o cliente tenha informado um
		// valor diferente de zero)
		this.votos = 0L;
	}
}
