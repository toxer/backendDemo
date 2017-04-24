package it.infocamere.springrepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.infocamere.entity.Ente;

public class CustomSpecificationRepository {

	public static Specification<Ente> likeSpecification(String likeField) {
		
		Specification<Ente> specification=new Specification<Ente>(){

			@Override
			public Predicate toPredicate(Root<Ente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.<String>get("dsEnte"),likeField);
			}
			
		};
		return specification;
	}
}
