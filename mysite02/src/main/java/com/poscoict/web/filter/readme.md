# filter 만들기

+ filter에 reqeust처리를 해줌으로서, 
UserController, GuestbookController, Main Controller에 ```request.setCharacterEncoding("utf-8");```을 없애준다. 

<br>

+ 설정을 바깥으로 빼는 것 

> web.xml

```
<filter>
		<filter-name>EncodingFilter</filter-name>
		<filter-class>com.poscoict.web.filter.EncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
```

> EncodingFilter.java

```
private String encoding;

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
```
   
<br>

---------

+ default encoding 
	+ web.xml에 init-param없으면
	
> EncodingFilter.java
	
	```
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null) {	//	default encoding
			encoding = "UTF-8";
		}
	}
	```

------

+ servlet 지역변수
	+private 지역변수랑 유사
	

> web.xml

```
<init-param>
			<param-name>config</param-name>
			<param-value>/WEB-INF/servlet-context.xml</param-value>
		</init-param>
```
