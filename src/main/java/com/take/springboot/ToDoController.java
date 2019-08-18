package com.take.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.take.springboot.repositories.ToDoDataRepository;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

@Controller
public class ToDoController {
	
	@PostConstruct
	public void init() {
		ToDoData d1 = new ToDoData();
		d1.setToDoName("挨拶");
		repository.saveAndFlush(d1);
	}
	
	@Autowired
	ToDoDataRepository repository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") ToDoData tododata, 
			ModelAndView mav) {
		mav.addObject("msg", "新しいToDoを作成する");
		mav.addObject("formModel",tododata);
		mav.setViewName("index");
		Iterable<ToDoData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel") @Validated ToDoData tododata,
			BindingResult result, ModelAndView mav) {
		ModelAndView res = null;
		if (!result.hasErrors()) {
			repository.saveAndFlush(tododata);
			res = new ModelAndView("redirect:/");
		}else {
			mav.setViewName("index");
			mav.addObject("msg", "エラーです");
			Iterable<ToDoData> list = repository.findAll();
			mav.addObject("datalist", list);
			res = mav;
		}
		return res;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(
			@ModelAttribute ToDoData tododata, @PathVariable int id,
			ModelAndView mav) {
		mav.addObject("msg", "ToDoの内容を変更します");
		mav.setViewName("edit");
		Optional<ToDoData> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(
			@ModelAttribute ToDoData tododata,
			ModelAndView mav) {
		repository.saveAndFlush(tododata);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleate(@PathVariable int id,
			ModelAndView mav) {
		mav.addObject("msg", "ToDoを削除します");
		mav.setViewName("delete");
		Optional<ToDoData> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
}
