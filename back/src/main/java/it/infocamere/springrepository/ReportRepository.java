package it.infocamere.springrepository;

import org.springframework.data.repository.CrudRepository;

import it.infocamere.entity.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {
	
}
