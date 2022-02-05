# listener

> ContextLoadListener.java

```
 public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Application(mysite01) Starts...");
    }
```

>web.xml 추가

```
<listener>
		<listener-class>com.poscoict.web.listener.ContextLoadListener
		</listener-class>
	</listener>
```

-------
+ 전역 파라미터
<context-param>
	+ 전역 모든 서블릿에 사용 가능
		+ Context 
		<br>
			≓ application
		<br>
			≓ servlet context
	
>web.xml


```
	<context-param>
		<param-name>ContextConfigLocation</param-name>
		<param-value>/WEB-INF/applicationContext.xml</param-value>
	</context-param>
```
