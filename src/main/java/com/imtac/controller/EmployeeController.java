package com.imtac.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imtac.service.Employee;
import com.imtac.service.EmployeeDAO;

@Controller
public class EmployeeController
{
	@Autowired
	private EmployeeDAO employeeDAO;

	@RequestMapping(value = "/employee",method=RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		try
		{
			if(employeeDAO.getEmployeeById(employee.getId()) != null);
			employeeDAO.updateEmployee(employee);
		}
		catch(EmptyResultDataAccessException e)
		{
			System.out.println("inside catch");
			employeeDAO.saveEmployee(employee);
		}
		return new ModelAndView("redirect:/employees");
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("id") int id)
	{
		ModelAndView model = new ModelAndView("employees");

		employee = employeeDAO.getEmployeeById(id);
		List employeeList = employeeDAO.getAllEmployees();

		model.addObject("employee",employee);        
		model.addObject("employeeList",employeeList);

		return model;
	}

	@RequestMapping(value = "/delete/{id}")
	public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("id") int id)
	{
		employeeDAO.deleteEmployee(id);

		return new ModelAndView("redirect:/employees");
	}

	@RequestMapping(value = "/employees")
	public ModelAndView listEmployees(@ModelAttribute("employee") Employee employee,
			HttpSession session)
	{
		if(session.getAttribute("username") == null){
			ModelAndView model = new ModelAndView("login"); 
			model.addObject("msg", "Session Timeout.. Please login..");
			return model;
		}
		ModelAndView model = new ModelAndView("employees");

		List employeeList = employeeDAO.getAllEmployees();
		System.out.println(employeeList);
		model.addObject("employeeList", employeeList);

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password,
			HttpSession session)
	{
		try{
			if(employeeDAO.getUser(username, password) != null){
				session.setAttribute("username", "root");
				session.setMaxInactiveInterval(10 * 60);
				return new ModelAndView("redirect:/employees");
			} else {
				ModelAndView model = new ModelAndView("login");
				model.addObject("msg", "Invalid username/password");
				return model;
			}
		}catch(EmptyResultDataAccessException e){
			ModelAndView model = new ModelAndView("login");
			model.addObject("msg", "Invalid username/password");
			return model;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login()
	{
		ModelAndView model = new ModelAndView("login");
		return model;
	}
}