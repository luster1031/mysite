> applicationContext.xml

```
<context:component-scan
		base-package="com.poscoict.mysite.service, com.poscoict.mysite.repository">
```
+ com.poscoict.mysite.service 추가


###	 DefaultServelt 위임


> spring=servlet.xml

```
	<!-- 서블릿 컨테이너 (tomcat)의 DefaultServlet 위임(delegate) Handler-->
	<mvc:default-servlet-handler/>
```
 

### ViewResolver

```
@RequestMapping({"", "/main"})
	public String index() {
		// 	return "/WEB-INF/views/main/index.jsp"
		return "main/index";
	}
```
+ return "main/index"로만 했는데 <br>
	"/WEB-INF/views/main/index.jsp" 되게 만들기

```
<!-- ViewResolver -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
	</bean>
```


### exception

+ AOP기술
	+ 모든 에러가 여기로 가는 것 
	
> spring-servlet.xml 추가해줘야함

```
<context:component-scan
		base-package="com.poscoict.mysite.controller, com.poscoict.mysite.exception" />
```

> GlobalException.java
```
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		
		//	1. 로깅
		//  errors가 가지고 있는 버퍼 안에 에러 내용 적혀 있음
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); 
		System.out.println(errors.toString());
		
		model.addAttribute("exception", errors.toString());
		
		//	2. 사과 페이지 (HTML 응답, 정상 종료)
		return "error/exception";
		
		
	}
}
```
    
    
# datasource

+ getConnection()을 함수로 안 빼줘도 된다. -> bean으로 처리

> pom.xml

```
<!-- spring jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		
		<!-- Common DBCP -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>

```

> applicationContext.xml

```
	<!-- Connection Pool DataSource -->
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName"
			value="com.mysql.cj.jdbc.Driver" />
		<property name="url"
			value="jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&amp;serverTimezone=UTC" />
		<property name="username" value="webdb" />
		<property name="password" value="webdb" />
	</bean>
```

> UserRepository.java

```
//	 주입
	@Autowired
	private DataSource dataSource;
	
```

```
dataSource.getConnection();
```


# 템플릿


# Mybatis
1. 라이브러리 추가 

```
<!-- MyBatis -->
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis</artifactId>
	<version>3.2.2</version>
</dependency>

<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis-spring</artifactId>
	<version>1.2.0</version>
</dependency>
```

2. Sql Session Factory Bean 설정

```
<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />	<!-- 주입할 bean의 id (ref) -->
		<property name="configLocation"
			value="classpath:mybatis/configuration.xml" />
	</bean>
```

+ <property name="dataSource" ref="dataSource" /> 주입할 bean 의 id를 ref에 넣기

2. resources - mybatis - configuration.xml만들기

3. mybatis에 mybatis sql session Template넣기

```
<!-- MyBatis SqlSessionTemplate -->
	<!-- 생성자 사용하는 DAO -->
	<bean id="sqlSession"
		class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg index="0" ref="sqlSessionFactory" />
	</bean>
```

4. configuration.xml

```
<mapper resource ="mybaits/mappers/user.xml"/>
```

5. user.xml


6. UserRepository.java


7. typeAlias

>user.xml

```
<insert id="insert" parameterType="com.poscoict.mysite.vo.UserVo">
```

```
<insert id="insert" parameterType="uservo">
```
이렇게 바꾸고

> configuration.xml

```
<typeAliases>
		<typeAlias type="com.poscoict.mysite.vo.UserVo" alias="uservo" />
    	</typeAliases>
```

