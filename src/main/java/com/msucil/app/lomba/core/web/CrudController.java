package com.msucil.app.lomba.core.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msucil.app.lomba.core.persistance.AbstractEntity;
import com.msucil.app.lomba.core.persistance.CrudService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CrudController<E extends AbstractEntity<I>, D extends Dto<I>, I> {

	private static final String INDEX_PAGE_NAME = "index";
	private static final String PAGINATE_PAGE_NAME = "paginate";
	private static final String FORM_PAGE_NAME = "form";
	private static final String DETAIL_PAGE_NAME = "detail";

	protected static final String SUCCESS_KEY = "success";
	protected static final String ERROR_KEY = "error";
	protected static final String WARN_KEY = "warn";

	protected static final String SUCCESS_MSG = "Operation Successful";
	protected static final String ERROR_MESSAGE = "Operation Failed";
	protected static final String VALIDATION_FAILED_MSG = "Validation Failed";
	protected static final String NOT_FOUND_MSG = "Record Not Found";

	private final CrudService<E, I> crudService;
	private final AbstractMapper<E, D, I> mapper;

	private final String baseUrl;

	private final String indexPage;
	private final String paginatePage;
	private final String formPage;
	private final String detailPage;

	protected CrudController(CrudService<E, I> crudService, AbstractMapper<E, D, I> mapper, String baseUrl,
			String templateDir) {
		this.crudService = crudService;
		this.mapper = mapper;
		this.baseUrl = baseUrl;
		this.indexPage = templateDir + INDEX_PAGE_NAME;
		this.paginatePage = templateDir + PAGINATE_PAGE_NAME;
		this.formPage = templateDir + FORM_PAGE_NAME;
		this.detailPage = templateDir + DETAIL_PAGE_NAME;
	}

	@RequestMapping
	public String index(Model model) {

		model.addAttribute("records", crudService.findAll());

		return indexPage;
	}

	@RequestMapping("/paginate")
	public String pageinate(Model model) {

		model.addAttribute("records", crudService.findAll());
		return paginatePage;
	}

	@GetMapping("/create")
	public String create(@ModelAttribute("form") D form) {
		return formPage;
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("form") @Valid D form, BindingResult validation, Model model,
			RedirectAttributes redirect) {

		if (validation.hasErrors()) {

			model.addAttribute(ERROR_KEY, VALIDATION_FAILED_MSG);
			return formPage;
		}

		final E entity = mapper.maptoEntity(form);

		try {

			final E saved = crudService.save(entity);

			redirect.addFlashAttribute(SUCCESS_KEY, SUCCESS_MSG);

			return getRedirectUrl(saved);
		} catch (Exception e) {
			log.error("Error while saving record {}", entity, e);

			model.addAttribute(ERROR_KEY, "Error occurred while saving records (" + e.getLocalizedMessage() + ")");
			return formPage;
		}
	}

	@GetMapping("/{id}/update")
	public String update(@PathVariable("id") I id, @ModelAttribute("form") D form, Model model,
			RedirectAttributes redirect) {

		var record = crudService.findById(id);

		if (record.isEmpty()) {
			redirect.addFlashAttribute(WARN_KEY, NOT_FOUND_MSG);

			return getRedirectUrl(null);
		}

		model.addAttribute("form", mapper.mapToDto(record.get()));

		return formPage;
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") I id, @ModelAttribute @Valid D form, BindingResult validation, Model model,
			RedirectAttributes redirect) {

		if (!crudService.existsById(id)) {
			redirect.addFlashAttribute(WARN_KEY, NOT_FOUND_MSG);

			return getRedirectUrl(null);
		}

		final E entity = mapper.maptoEntity(form);

		try {

			final E saved = crudService.save(entity);

			redirect.addFlashAttribute(SUCCESS_KEY, SUCCESS_MSG);

			return getRedirectUrl(saved);

		} catch (Exception e) {
			log.error("Error while saving record {}", entity, e);

			model.addAttribute(ERROR_KEY, "Error occurred while saving records (" + e.getLocalizedMessage() + ")");
			return formPage;
		}
	}

	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id") I id) {

		crudService.deleteById(id);

		return getRedirectUrl(null);
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable("id") I id, Model model, RedirectAttributes redirect) {

		var record = crudService.findById(id);

		if (record.isEmpty()) {
			redirect.addFlashAttribute(WARN_KEY, NOT_FOUND_MSG);

			return getRedirectUrl(null);
		}

		model.addAttribute("record", record.get());

		return detailPage;
	}

	protected String getRedirectUrl(E entity) {
		return "redirect:" + this.baseUrl + ((null == entity) ? "" : "/" + entity.getId());
	}
}
