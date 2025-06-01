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
    </style>
</head>
<body>
<container>

    <h1>Reset Password</h1> 

    <form action="ForgotPasswordController" method="post">
    <label for="email">Enter your email:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <input type="submit" value="Submit">
    </form>

</container>
</body>
</html>