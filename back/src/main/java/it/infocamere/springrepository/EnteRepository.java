package it.infocamere.springrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.infocamere.entity.Ente;

public interface EnteRepository extends CrudRepository<Ente, String>,JpaSpecificationExecutor<Ente> {

	//si fa l'override di findAll se serve inserire anche il pageSpecification
	Page<Ente>findAll(Specification<Ente>specification,Pageable pageSpecification);
}
