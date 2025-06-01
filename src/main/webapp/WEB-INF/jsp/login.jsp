<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>


        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        container {
            max-width: 90%;
            background-color: #fff;
            padding: 60px;
            margin: 0 auto;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            border: 2px solid #007bff;
            text-align: center; /* Center align text */
            position:relative;

        }

        h1, h2, h3 {
            font-family: 'Roboto', sans-serif;
            font-weight: bold;
            color: #007bff;
        }

        h5 {
            color: #f44336;
            font-weight: bold;
        }

        p {
            font-size: 16px;
            line-height: 1.6;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        input[type="submit"], input[type="button"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            background-color: #f8f8f8;
            padding: 10px;
            line-height: 1.6;
        }
        #loading-container {
            display: none;
            justify-content: center;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(255, 255, 255, 0.8);
        }

        #loading {
            border: 8px solid #f3f3f3;
            border-top: 8px solid #3498db;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            animation: spin 3s linear infinite;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }

    </style>
    <link rel="stylesheet" href="/css/choreskip-common.css">

</head>
<body>
<%--<img src="<c:url value='/images/logo.png'/>" alt="ChoreSkip Logo" class="logo" style="max-width: 200px; height: auto;"/>--%>

    <div id="loading-container">
        <div id="loading"></div>
    </div>
<container>


    <h5>${msg}</h5>
    <h5>${error}</h5>

    <h1>Login</h1> <!-- Title added here -->

    <form action="authorize" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" onclick="login()" value="LOGIN" />
        <a href="${pageContext.request.contextPath}/forgotPassword">Forgot Password?</a>
        <label>
            <input type="checkbox" name="rememberMe"> Keep me signed in
        </label>
    </form>

<%--    <footer class="choreskip-footer">--%>
<%--        © 2024 ChoreSkip - Fair Chore Distribution--%>
<%--    </footer>--%>
</container>
<script>
    function login() {
        // Show loading animation
        document.getElementById('loading-container').style.display = 'flex';

        // Simulate login process (replace this with your actual login logic)
        setTimeout(function () {
            // Hide loading animation after a delay (replace this with your actual login logic)
            document.getElementById('loading-container').style.display = 'none';

            // Handle successful login (redirect or show success message)
        }, 2000); // Simulated delay of 2 seconds
    }
</script>
</body>
</html>