<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="j" value="-1" />
					<c:forEach items="${list }" var="vo">
						<c:set var="j" value="${j+1 }" />
						<c:set var="i" value="${m.startnum -j}" />
						<tr>
							<td>${i }</td>
							<td style="text-align: left; padding-left:${(vo.depth-1)*20 }px">
								<c:choose>
									<c:when test="${vo.depth > 1}">
										<img
											src="${pageContext.servletContext.contextPath }/assets/images/reply.png"
											height="100%">
									</c:when>
								</c:choose> <a
								href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate }</td>
							<!-- 글 쓴 사람이 아니면 삭제 못 하게 -->
							<c:choose>
								<c:when test="${authUser.no eq vo.userNo}">
									<td><a
										href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}"
										class="del"
										style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
								</c:when>
								<c:otherwise>
									<c:set var="href"
										value="${pageContext.servletContext.contextPath }/board" />
								</c:otherwise>
							</c:choose>

						</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${m.prePage > 1}">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${m.prePage }">◀</a></li>
							</c:when>
						</c:choose>
						<c:forEach var="num" begin="${m.startPage }" end="${m.endPage }">
							<c:choose>
								<c:when test="${num == m.currentPage }">
									<c:set var="select" value='class="selected"'></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="select" value=''></c:set>
								</c:otherwise>
							</c:choose>
							
							
							<c:choose>
								<c:when test="${m.serach == 1}">
									<c:set var="search" value="&kwd2=${kwd }" />
								</c:when>
								<c:otherwise>
									<c:set var="search" value="" />
								</c:otherwise>
							</c:choose>
							<li ${select }><a
								href="${pageContext.servletContext.contextPath }/board?page=${num }${search}">${num }</a></li>
						</c:forEach>
						<c:choose>
							<c:when test="${m.totalPage != m.endPage}">
								<li><a
									href="${pageContext.servletContext.contextPath }/board?page=${m.nextPage }">▶</a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath }/board?a=writeform"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>