package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneContoller {

	// 필드
	@Autowired
	private PhoneDao phoneDao;
	// 생성자
	// 메소드 게터 세터
	// 메소드 일반

	// ****메소드 일반*****
	// test

	// 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform() {

		System.out.println("writeForm");

		return "write";

	}

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");

		//Dao를 통해 리스트를 받아온다 
		
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println("personList.toString");

		//model--> data 보내는 방법 -> 모델(박스)에 담아 놓으면 된다 
		model.addAttribute("pList", personList);
		
		System.out.println(personList.toString());

		return "list";
	}

	// 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write (@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam ("company") String company) {
	System.out.println("write");
		
	//묶어주기
	PersonVo personVo =  new PersonVo(name, hp, company);
	System.out.println(personVo.toString());
	

	phoneDao.personInsert(personVo);
	
	return "redirect:phone/list";
	}
	
	// 수정폼-> modifyForm
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyform(@RequestParam("id")int id, Model model) {

		System.out.println("modifyForm");
		System.out.println(id);

	
		PersonVo personVo = phoneDao.getPerson(id);
		
		model.addAttribute("personVo", personVo);
		System.out.println(personVo.toString());

		
		return "modifyForm";
	}
	
	// 수정->modify-->@RequestParam
	@RequestMapping(value= "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("modify");

	System.out.println(personVo.toString());
	

	phoneDao.personUpdate(personVo);
	
	return "redirect:/phone/list";
	}
	
	//수정 --> modify2 --> @RequestParam
		@RequestMapping(value = "/modify2", method = {RequestMethod.GET, RequestMethod.POST})
		public String modify2(@RequestParam("personId") int id, 
							 @RequestParam("name") String name, 
							 @RequestParam("hp") String hp,
							 @RequestParam("company") String company) {
			System.out.println("modify2");
			
			PersonVo personVo = new PersonVo(id, name, hp, company);
			System.out.println(personVo.toString());
			
			
			phoneDao.personUpdate(personVo);
			
			
			return "redirect:/phone/list";
		}
	
	// 삭제->delete--> 약식
	@RequestMapping(value = "/delete/{}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete (@RequestParam("personId") int id) {
	System.out.println("delete");
	System.out.println("personId");
	
	
	
	phoneDao.personDelete(id);
	
	return "redirect:phone/list";
	}
	
	//삭제 --> delete --> @PathVariable
		@RequestMapping(value = "/delete2/{personId}", method = {RequestMethod.GET, RequestMethod.POST})
		public String delete2(@PathVariable("personId") int id) {
			System.out.println("delete2");
			System.out.println(id);
			
			
			phoneDao.personDelete(id);
			
			return "redirect:/phone/list";
		}
	
	// 메소드 단위로 기능을 정의

	// doGet() 메소드 1개에 파라미터로 구분
}
