Step 1: 以-Dspring.profiles.active=primary (and secondary, and tertiary)为启动参数分别启动Eureka Server
Args: [
	java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=primary 
	java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=secondary 
	java -jar target/demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=tertiary 
]

Step 2: 修改所有Eureka Client的eureka.client.serviceUrl.defaultZone值为
http://eureka-primary:8011/eureka/,http://eureka-secondary:8012/eureka/,http://eureka-tertiary:8013/eureka/