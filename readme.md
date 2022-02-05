# [mysite02](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/mysite/mysite01)

+ 게시판만들기
	+ 방명록 보기, 수정, 삭제
	+ 로그인, 로그아웃
	+ 회원가입, 회원정보 수정
	
		# 웹프로그래밍 (JSP & Servlet) 평가과제

		**poscoict 자바 전문가 과정 395기**

		> [강사님 깃허브](https://github.com/kickscar)<br>
		> [POSCOICT-비트아카데미](https://github.com/poscoict-bitacademy)

		# **mysite 프로젝트**
		### 기능
		+ MVC 적용
			+ DAO, VO 사용
		1. 상태에 따른 UI 변화
			+ 로그인 전과 후 네비게이션 탑 상태 변화![UI](https://user-images.githubusercontent.com/61460836/150640512-a6ad1616-a685-4ff7-acae-62dbfcb45bf2.gif)

		<br>

		2. 게시물 입력
		![글쓰기](https://user-images.githubusercontent.com/61460836/150640513-adf827aa-3f3e-4fc4-8fed-c90138fea51f.gif)

		<br>
		3. 게시물 리스트

		<br>
		4. 게시물 삭제

		+ 자신이 등록한 게시물만 삭제 가능
		+ 삭제 시에 '삭제된 글입니다.'로 보임
		+ 삭제하면 타이틀을 눌러도 게시물이 보이지 않는다.

			![삭제](https://user-images.githubusercontent.com/61460836/150640505-cc38880d-7b27-4687-91a1-a2055bd517f9.gif)

		<br>
		5. 게시물 수정

		+ 자신이 작성한 글이라면 수정 가능
			![수정](https://user-images.githubusercontent.com/61460836/150802739-6891ec24-7f85-4e72-b9c8-3d27e1a93350.gif)

		<br>
		6. 조회수

		+ 24시간으로 세션 설정함
		+ 각 ID당 하나의 조회수를 늘릴 수 있다. 
			![조회수](https://user-images.githubusercontent.com/61460836/150640510-e055b9b8-0843-4f2e-9c74-3551c266e9ca.gif)

		<br>
		7. 답글

		![답글](https://user-images.githubusercontent.com/61460836/150640516-2e33c711-4b3b-4bc3-85eb-01f587969a9e.gif)

		+ 리스트에서 게시물 검색 기능
			+ 현재는 제목 검색만 허용한 상태이다.
		![검색1](https://user-images.githubusercontent.com/61460836/150640806-069841a7-e4c9-4caf-acda-2b5f7c4d3b6b.gif)

		<br>
		8. 페이징 처리

		+ 게시물 페이징
		![페이징](https://user-images.githubusercontent.com/61460836/150640807-c7d6644c-743c-4542-8b9e-08363b8467b7.gif)

		+ 검색한 결과에 따른 페이징 가능
		![검색_페이징](https://user-images.githubusercontent.com/61460836/150640804-4bf04728-7701-42bc-b1e7-4c65fb8afeee.gif)
# [mysite03](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/mysite/mysite03)
 
 + 게시판만들기
 	+ repository, service, controller로 만들기
 