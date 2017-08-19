<%@ page import="com.company.VoteService" %>
<%@ page import="com.company.credentials.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="/check.js"></SCRIPT>
<%VoteService vs = VoteService.getInstance();
  String user = (String)session.getAttribute("user_login");


  String voted = vs.isUserVoted(user)?"1":"0";
%>
  <head>
    <title>Some questions
    </title>
    <style>
      .main {border-top:solid 1px black}
    </style>
  </head>

<body>
<H2>Questionnaire for <%=user%></H2>
<TABLE><TR><TD class="main">
  <form method="POST" action="/vote">
    <jsp:include page="/JSTLForm.jsp"/>
  <%if (voted.equals("0")){%>
    <TR><td class="main">
    1. Question 1<br>
    <INPUT id="q1" type="radio" name="question1" value="1">1<br>
    <INPUT type="radio" name="question1" value="2">2<br>
    <INPUT type="radio" name="question1" value="3">3<br>
    2. Question 2<br>
    <INPUT id="q2" type="radio" name="question2" value="1">1<br>
    <INPUT type="radio" name="question2" value="2">2<br>
    <INPUT type="radio" name="question2" value="3">3<br>
    3. Question 3<br>
    <INPUT id="q3" type="radio" name="question3" value="1">1<br>
    <INPUT type="radio" name="question3" value="2">2<br>
    <INPUT type="radio" name="question3" value="3">3<br>
    </br>
    <INPUT type="SUBMIT" onclick="if (!checkAll()) return false;">
  <%}%>
  </form>
    <%if (voted.equals("1")){%>
    <TR><TD class="main">Your answers, dear <%=user%><BR>
              .<%=vs.getUserStat(user)%>
    <TR><TD class="main">Total Statistic:<BR>
               <%=vs.getTotalStat()%>
             <% }    %>
  <TR><TD class="main">
  <a href="/trylogin?relogin=1">Relogin</a>
</TABLE>
  </body>
</html>
