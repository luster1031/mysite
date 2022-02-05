


# el 태그

1. <%=request.getContextPath()%>

```
${pageContext.request.contextPath }
```
<br>

2. <%=request.getParameter("no") %>
+ ${parma.(요청파라미터이름)}

```
	${parma.no}
```
<br>

3. replace

```
	<%pageContext.setAttribute("newline", "\n");%>
	${fn:replace(vo.message ,newline ,"<br/>")}
```
<br>


4. [리스트 사이즈]list.size()

```
${fn:length(list)}
```
<br>

5. [리스트 for문] for(Vo vo : list)

```
<c:forEach items="${list }" var = "vo">
```
<br>


6. 증감 연산
+ el은 증감 연산이 없다. (구글에 쳐봄)

```
<c:set var="index" value="${index + 1}"/>
```
<br>


