<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <title>Photo List</title>
    <script src="/static/check.js"></script>
    </head>
<body>
<form method="POST" action="/deletelist">
    <div class="btn-group" role="group" aria-label="Button group">
    <input type="button" value="Delete selected"  class="btn btn-secondary" onclick="if (checkIfChecked()){submit();}"/>
    <input type="button" value="Upload New" onclick="window.location='/';"  class="btn btn-secondary" />
    </div>
    <TABLE class="table table-hover" width="600px">
    <TR><TD colspan="2">
    <c:forEach var="id" items="${photos}">
        <TR>
            <TD width="50px"><input type="checkbox" value="${id}" id="${id}" name="checked_photo"/>
            <TD onclick="/*alert($('#${id}'));*/$('#${id}').prop('checked',!$('#${id}')[0].checked)">
                <div class="card" style="width: 200px;cursor:pointer">
                    <img class="card-img-top" src="/photo/${id}" alt="Card image cap" onclick="window.location='/photo/${id}'">
                    <div class="card-body">
                        <p class="card-text">Photo Id -> ${id}</p>
                    </div>
                </div>
    </c:forEach>
    <TR><TD colspan="2">
    </TABLE>
    <input type="button" value="Delete selected" class="btn btn-secondary" onclick="if (checkIfChecked()){submit();}"/>
</form>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>