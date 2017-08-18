<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<TABLE id="embedded">
    <c:if test="${requestScope.name ne null}">
    <c:set var="ro" value="readonly" scope="page"/>
    </c:if>
    <TR>
        <TD>Name<TD><INPUT type="text" id="fname" name="name" value="${requestScope.name}" ${ro}>
    <TR>
        <TD>Surname<TD><INPUT type="text" id="fsurname" name="surname" value="${requestScope.surname}" ${ro}>
    <TR>
        <TD>Age<TD>
    <c:if test="${ro ne null}">
        <INPUT type="text" id="fage" name="age" value="${requestScope.age}" ${ro} width="5">
    </c:if>
    <c:if test="${ro eq null}">
    <SELECT type="select" id="fage" name="age" ${ro}>
    <c:forEach var="iterator" begin="5" end="65">
        <c:if test="${iterator eq requestScope.age}">
            <c:set var="selFlag" value="selected"/>
        </c:if>
        <c:if test="${iterator ne requestScope.age}">
            <c:set var="selFlag" value=""/>
        </c:if>
        <OPTION value="${iterator}" ${selFlag} >${iterator}
    </c:forEach>

    </SELECT>
    </c:if>
</TABLE>