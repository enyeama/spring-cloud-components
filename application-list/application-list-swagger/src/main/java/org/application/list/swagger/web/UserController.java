package org.application.list.swagger.web;

import java.util.Collection;

import org.application.list.swagger.entity.User;
import org.application.list.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	UserService userService;

	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<User> getUserList() {
		Collection<User> userList = userService.getUserList();
		return userList;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createUser(@RequestBody User user) {
		userService.createUser(user);
		return "success";
	}

	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	@ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String modifyUser(@PathVariable("id") Long id, @RequestBody User user) {
		userService.modifyUser(id, user);
		return "success";
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String removeUser(@PathVariable("id") Long id) {
		userService.removeUser(id);
		return "success";
	}
	
	
}
