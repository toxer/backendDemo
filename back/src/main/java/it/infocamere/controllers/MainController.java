package it.infocamere.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import it.infocamere.entity.Ente;
import it.infocamere.entity.Report;
import it.infocamere.jsonview.Views;
import it.infocamere.springrepository.CustomSpecificationRepository;
import it.infocamere.springrepository.EnteRepository;
import it.infocamere.springrepository.ReportRepository;

@Controller
public class MainController {
	@Autowired
	private EnteRepository enteRepository;
	@Autowired
	private ReportRepository reportRepository;


	@RequestMapping(value = "/enteTest", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			model.addAttribute("ente", enteRepository.findOne("000000"));

			return "test";
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "";
	}


	@RequestMapping(value = "/enteTestLike/{pageNumber}", method = RequestMethod.GET)
	public String enteTestLike(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable("pageNumber")Integer pageNumber) {
		try {
			Pageable pageSpecification = new PageRequest(pageNumber,1);
			Page<Ente> enti = enteRepository.findAll(CustomSpecificationRepository.likeSpecification("%Test%"),pageSpecification);			
			
			model.addAttribute("enti", enti.getContent());

			return "test2";
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping(value = "/ente/{id}", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	@JsonView(Views.completeEnteView.class)
	public @ResponseBody Ente getEnte(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "id") String enteId) {
		try {
			Ente ente = enteRepository.findOne(enteId);
			
			return ente;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	
	@RequestMapping(value = "/report/{id}", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	@JsonView(Views.completeReportView.class)
	public @ResponseBody Report getReport(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "id") Integer reportId) {
		try {
			Report report = reportRepository.findOne(reportId);
			
			return report;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
}
