package com.example.neeraj;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.neeraj.model.Employee;
import com.example.neeraj.service.EmployeeServiceImpl;
import com.example.neeraj.service.IEmployeeService;

import utilservice.CommonServices;

/**
 * Handles requests for the application home page.
 */
@RequestMapping("employee")
@Controller
public class EmployeeController implements ApplicationContextAware, BeanClassLoaderAware {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private IEmployeeService empService;

	@Autowired
	private CommonServices commonService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/addGet", method = RequestMethod.GET)
	public String employeeAdd(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		logger.error(commonService.hashCode() + "");
		String formattedDate = commonService.getCurrentTime();

		logger.error(formattedDate + "");

		model.addAttribute("employeeObj", new Employee());
		List<Employee> list = empService.getEmployees();
		model.addAttribute("employeeList", list);
		/* model.addAttribute("tempEmp", list.get(0)); */

		return "employee";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(Locale locale, @ModelAttribute("employeeObj") Employee employeeObj) {
		try {
			if (employeeObj != null && employeeObj.getId() > 0) {

				empService.updateEmployee(employeeObj);

			} else {
				empService.saveEmployee(employeeObj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:addGet";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployee(Locale locale, Model model) {
		List<Employee> empLIst = null;

		try {
			empLIst = empService.getEmployees();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return empLIst;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEmployee(Locale locale, Model model, HttpServletRequest request) {
		Employee emp = null;
		String id = request.getParameter("id");
		try {
			emp = empService.getEmployee(Integer.valueOf(id));
			model.addAttribute("employeeObj", emp);
			System.out.println(emp);
			List<Employee> list = empService.getEmployees();
			model.addAttribute("employeeList", list);
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return "employee";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(Locale locale, Model model, @PathVariable("id") int id) {

		try {
			empService.deleteEmployee(id);
			System.out.println("Delete successfull");
			List<Employee> list = empService.getEmployees();
			model.addAttribute("employeeList", list);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "redirect:../addGet";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("During Server");
		empService = (EmployeeServiceImpl) applicationContext.getBean("empService");

	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		URL url = classLoader.getResource("log4j.xml");
		System.out.println(url.getPath());
	}

	@RequestMapping(value = "/caching", method = RequestMethod.GET)
	public String cachingDemo(Locale locale, Model model, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		try {
			empService.cachingDemo(Integer.valueOf(id));

		} catch (Exception e) {
			logger.error(e.toString());
		}

		return "redirect:../../addGet";
	}

}
