package org.application.list.swagger.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.application.list.swagger.entity.User;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@Component
public class UserService {

	private static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>());

	private static Logger logger = Logger.getLogger(UserService.class);

	static {
		userMap.put(100L, new User(100L, "Fred", 35, "100108"));
		userMap.put(200L, new User(200L, "Hockor", 36, "100109"));
		userMap.put(300L, new User(300L, "Sengar", 32, "100110"));
		userMap.put(400L, new User(400L, "Whiter", 36, "100111"));
	}

	@HystrixCommand(fallbackMethod = "getUserListFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, //
			threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
	public Collection<User> getUserList() {
		return userMap.values();
	}

	public Collection<User> getUserListFallback() {
		logger.debug(">>getUserListFallback");
		return Collections.emptyList();
	}

	@HystrixCommand(fallbackMethod = "getCreateUserFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, //
			threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
	public void createUser(User user) {
		userMap.put(user.getId(), user);
	}

	public void getCreateUserFallback(User user) {
		logger.debug(">>getCreateUserFallback");
		logger.debug("user: " + user);
	}

	@HystrixCommand(fallbackMethod = "getUserByIdFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, //
			threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
	public User getUserById(Long id) {
		return userMap.get(id);
	}

	public User getUserByIdFallback(Long id) {
		logger.debug(">>getUserByIdFallback");
		logger.debug("id: " + id);
		User u = new User();
		u.setId(id);
		return u;
	}

	@HystrixCommand(fallbackMethod = "getModifyUserFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, //
			threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
	public void modifyUser(Long id, User user) {
		User u = userMap.get(id);
		u.setUsername(user.getUsername());
		u.setAge(user.getAge());
		u.setPhone(user.getPhone());
		userMap.put(id, u);
	}

	public void getModifyUserFallback(Long id, User user) {
		logger.debug(">>getModifyUserFallback");
		logger.debug("id: " + id);
		logger.debug("user: " + user);
	}

	@HystrixCommand(fallbackMethod = "getRemoveUserFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, //
			threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
	public void removeUser(Long id) {
		userMap.remove(id);
	}
	
	public void getRemoveUserFallback(Long id) {
		logger.debug(">>getModifyUserFallback");
		logger.debug("id: " + id);
	}

}
