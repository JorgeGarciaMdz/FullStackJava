<%-- Document : administrator Created on : 02/08/2021, 21:49:13 Author : jorge --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <link rel="stylesheet" href="css/stylesheet.css">
            <title>Administrator</title>
        </head>

        <body class="body-admin">
            <nav class="navbar nav-color">
                <div class="container-fluid">
                    <h2 style="color: #e5f0ed;">Hotel Mendoza</h2>
                    <% if (session.getAttribute("user")==null) { %>
                        <% response.sendRedirect("index.jsp"); %>
                            <% } else {%>
                                <form class="d-flex" name="logout" action="index" method="GET">
                                    <% if( session.getAttribute("type") !=null ) { %>
                                        <span class="navbar-brand">
                                            <bold>Administrador</bold>
                                        </span>
                                        <% } %>
                                            <span class="navbar-brand">
                                                <%= session.getAttribute("user")%>
                                            </span>
                                            <button class="btn btn-outline-info" type="submit">Logout</button>
                                </form>
                                <% }%>
                </div>
                <% if (session.getAttribute("type") !=null) { %>
                    <nav class="nav">
                        <a class="nav-link active" aria-current="page" onclick="showAllReservation()">Consultar
                            Reservas</a>
                        <!--   <a class="nav-link" href="#">Link</a>
                    <a class="nav-link" href="#">Link</a>
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
                    </nav>
                    <% } %>
            </nav>
            <div id="root" class="container"></div>
        </body>
        <% if (session.getAttribute("type") !=null) { %>
            <script src="./js/admin.js"></script>
            <script src="js/jquery-1.4.4.min.js"></script>
            <script src="js/bootstrap.js"></script>
            <script src="js/bootstrap.bundle.js"></script>
            <% } %>

        </html>