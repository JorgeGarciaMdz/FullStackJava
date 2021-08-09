<%-- 
    Document   : Index
    Created on : 02/08/2021, 21:09:09
    Author     : jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/stylesheet.css">
        <title>Hotel Mendoza</title>
    </head>
    <body class="body">
        <nav class="navbar nav-color">
            <div class="container-fluid">
                <h2 style="color: #e5f0ed;" >Hotel Mendoza</h2>
                <% if (session.getAttribute("user") == null) { %>

                <form class="row g-3 login-form-needs-validation" method="POST" action="index" novalidate >
                    <div class="col-md-4">
                        <div class="input-group has-validation">
                            <input type="text" class="form-control" name="user" aria-describedby="inputGroupPrepend" required placeholder="user@mail.com">
                            <div class="invalid-feedback">
                                Please choose a username.
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <input type="password" class="form-control" name="password" id="login-pass" required placeholder="password">
                        <div class="invalid-feedback">
                            Please provide a password.
                        </div>
                    </div>

                    <div class="col-md-2">
                        <button class="btn btn-primary" type="submit">Log in</button>
                    </div>
                </form>



                </form>
                <% } else {%>
                <form class="d-flex" name="logout" action="index" method="GET">
                    <span class="navbar-brand"> <%= session.getAttribute("user")%> </span>
                    <button class="btn btn-outline-info" type="submit">Logout</button>
                </form>
                <% }%>
            </div>
        </nav>
        <script src="js/jquery-1.4.4.min.js" ></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.bundle.js"></script>
        <script src="js/form.validate.login.js"></script>
    </body>
</html>
