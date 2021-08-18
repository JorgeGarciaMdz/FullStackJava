<%-- 
    Document   : employee
    Created on : 02/08/2021, 21:48:12
    Author     : jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/stylesheet.css">
        <title>Employee</title>
    </head>
    <body class="body-employee">
        <nav class="navbar nav-color">
            <div class="container-fluid">
                <h2 style="color: #e5f0ed;" >Hotel Mendoza</h2>
                <% if (session.getAttribute("user") == null) {
                        response.sendRedirect("index.jsp"); %>
                <% } else {%>
                <form class="d-flex" name="logout" action="index" method="GET">
                    <% if( session.getAttribute("type") != null ){ %>
                    <!-- <span class="navbar-brand"> <%= session.getAttribute("type")%> </span> -->
                    <span class="navbar-brand">Empleado  </span>
                    <% }%>
                    <span class="navbar-brand"> <%= session.getAttribute("user")%> </span>
                    <button class="btn btn-outline-info" type="submit">Logout</button>
                </form>
                <% }%>
            </div>
            <% if (session.getAttribute("user") != null) { %>
            <nav class="nav">
                <a class="nav-link active" aria-current="page" onclick="newReserve()">Nueva Reserva</a>
            <!--   <a class="nav-link" href="#">Link</a>
                <a class="nav-link" href="#">Link</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
            </nav>
            <% } %>
        </nav>
        <% if (session.getAttribute("user") != null) { %>
        <div id="root" class="root"></div>
        <script>
            var employee_id = <%= session.getAttribute("user_id").toString() %>
        </script>
        
        <script src="js/employee.js"></script>
        <script src="js/jquery-1.4.4.min.js" ></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.bundle.js"></script>
        <% }%>
    </body>
</html>
