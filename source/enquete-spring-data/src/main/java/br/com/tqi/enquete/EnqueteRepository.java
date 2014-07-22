package br.com.tqi.enquete;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqueteRepository extends
		PagingAndSortingRepository<Enquete, Long> {

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE CURRENT_TIMESTAMP BETWEEN e.inicio and e.fim", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE CURRENT_TIMESTAMP BETWEEN e.inicio and e.fim")
	Page<Enquete> findActive(Pageable pageable);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE CURRENT_TIMESTAMP BETWEEN e.inicio AND e.fim AND e.pergunta LIKE %?1%", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE CURRENT_TIMESTAMP BETWEEN e.inicio AND e.fim AND e.pergunta LIKE %?1%")
	Page<Enquete> findActiveByPerguntaContaining(String text, Pageable pageable);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE CURRENT_TIMESTAMP > e.fim", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE CURRENT_TIMESTAMP > e.fim")
	Page<Enquete> findFinished(Pageable pageable);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE CURRENT_TIMESTAMP > e.fim AND e.pergunta LIKE %?1%", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE CURRENT_TIMESTAMP > e.fim AND e.pergunta LIKE %?1%")
	Page<Enquete> findFinishedByPerguntaContaining(String text,
			Pageable pageable);

	@Modifying
	@Query(value = "UPDATE Opcao o SET o.votos = o.votos + 1 WHERE o.texto = ?2 AND o.enquete.id = ?1")
	int vote(Long id, String text);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes", countQuery = "SELECT COUNT(e) FROM Enquete e")
	Page<Enquete> findAll(Pageable pageable);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE e.pergunta LIKE %?1%", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE e.pergunta LIKE %?1%")
	Page<Enquete> findByPerguntaContaining(String text, Pageable pageable);

	@Query("SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE e.id = ?1")
	Enquete findOne(Long id);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE CURRENT_TIMESTAMP < e.inicio", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE CURRENT_TIMESTAMP < e.inicio")
	Page<Enquete> findNotInitialized(Pageable pageable);

	@Query(value = "SELECT e FROM Enquete e JOIN FETCH e.opcoes WHERE e.pergunta LIKE %?1% AND CURRENT_TIMESTAMP < e.inicio", countQuery = "SELECT COUNT(e) FROM Enquete e WHERE e.pergunta LIKE %?1% AND CURRENT_TIMESTAMP < e.inicio")
	Page<Enquete> findNotInitializedByPerguntaContaining(String text,
			Pageable pageable);

}
