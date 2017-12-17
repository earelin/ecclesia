package org.earelin.ecclesia.web.admin;

import java.util.List;
import java.util.Map;
import org.dozer.Mapper;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.dto.UserDto;
import org.earelin.ecclesia.web.form.admin.AdminEditUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User administration controller
 */
@Controller
public class UserAdminController {
    
    private final UserService service;
    private final Mapper mapper;

    @Autowired
    public UserAdminController(UserService service, Mapper mapper) {
        this.service = service;
	this.mapper = mapper;
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
	
	List<UserDto> users = service.findAll(null, new OrderingCriteria("username"), 0, 0);
	model.put("users", users);
	
        return "admin/users";
    }
    
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
    public String add(@PathVariable int userId, Map<String, Object> model) {
	
	AdminEditUserForm userForm = new AdminEditUserForm();
	model.put("users", userForm);
	
        return "admin/edit-user";
    }
    
    @RequestMapping(value = "/admin/user/{userId}", method = RequestMethod.GET)
    public String edit(@PathVariable int userId, Map<String, Object> model) {
	
	UserDto user = service.get(userId);
	AdminEditUserForm userForm = mapper.map(user, AdminEditUserForm.class);
	model.put("users", userForm);
	
        return "admin/edit-user";
    }
    
}
