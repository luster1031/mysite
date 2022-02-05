# 구조
```
src
	|--- main
		|--- java
			|--- com.poscoict.web.mvc
				|--- ActionFactory
				|--- Action		
			|--- com.poscoict.web.util
				|--- MvcUtil
			
			|--- com.poscoict.mysite.contorller
				|--- MainController
				|--- UserController : 객체 지향 
				|--- GuestbookController : 방명록Controller
			|--- com.poscoict.mysite.dao
				|--- GuestbookDao.java
				|--- UserDao.java
			|--- com.poscoict.mysite.vo
				|--- GuestbookVo.java
				|--- UserVo.java
				
			|--- com.poscoict.mysite.mvc.main
				|--- MainActionFactory.java
				|--- MainAction.java
			|--- com.poscoict.mysite.mvc.user
				|--- JoinAction.java
				|--- JoinFormAction.java
				|--- JoinSuccessAction.java
				|--- LoginAction.java
				|--- LoginFormAction.java
				|--- LoginOutAction.java
				|--- UpdateAction.java
				|--- UpdateFormAction.java
				|--- updateSuccessAction.java
				|--- UserActionFactory.java
			|--- com.poscoict.mysite.mvc.guestbook
				|--- AddAction.java
				|--- DeleteAction.java
				|--- DeleteFormAction.java
				|--- GuestbookActionFactory.java
				|--- IndexAction.java
					
			|--- com.poscoict.web.mvc
				|--- ActionFactory.java
				|--- Action.java
			|--- com.poscoict.web.filter
				|--- readme.md
				|--- EncodingFilter.java
			|--- com.poscoict.web.util
				|--- MvcUtil.java
					
		|--- resources
		|--- webapp
			|--- assets
				|--- css
				|--- images
				|--- js
			|--- WEB-INE
				|--- views 
					|--- board
					|--- uestbook
					|--- main 
					|--- user
					|--- includes
					|--- web.xml


```
---------------------------------------------
#### 요청이 4개 부분으로 나뉨 -> 요청 잘 분리하는게 좋음

+ UserController
	+ UserDao
		+ user테이블
		
+ GuestbookController
	+ GuestbookDao
		+ CRUD

+ boardController
	+ boardDao

+ mainController
---------------------------------------------

#### 단문으로 기능 정의해라
+ 사용자가 △을 한다.
---------------------------------------------

#### bean이란
+ object라고 해도 큰 무리는 없다. 

-----------------------------------------------------
+ <url-pattern></url-pattern>라고 해놓으면 맵핑명 안 작성해도 됨

+ 값들도 객체로 보는 것
	+ Integer
		+ Integer.garseInt("1") : 새로운 값 1 리턴함
			+ 외부 데이터 값 건들지 않음
			+ 유틸리티 함수
	+ Byte
	+ long
	
------------------------------------------------
객체 지향
+ 수정 없이 확장 가능
+ SOCID (객체지향 설계 원칙)

+ 프레임워크와 라이브러리의 차이점
	+ 프레임워크
		- 전체 다를 프레임워크 라이브러리로 만들어서 필요한 부분을 클래스로 만드는 것
		- 자체적으로 구현함
	+ 라이브러리 
		- 어플리케이션을 만드는 데 필요한 부분을 라이브러리로 불러서 씀
		
	
# 공부해야할 것	
+ 객체 지향 개념
	+ 은닉, 캡슐, 다형성, 상속
+ 객체 지향 프로그래밍
	+ 언어별로 다름
+ 설계 

+ 왜 상속, 오버라이드를 쓰는 지
	+ 인터페이스
		+ 기능 목록
		+ 클래스간 거리 넓히기
		