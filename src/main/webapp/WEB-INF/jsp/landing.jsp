<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chore Management App</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f5fc;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            max-width: 400px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }

        .features {
            text-align: left;
            margin-bottom: 20px;
        }

        .feature-item {
            margin-bottom: 10px;
            list-style-type: none;
        }

        .feature-item::before {
            content: '\2022'; /* Bullet point */
            color: #007bff;
            display: inline-block;
            width: 1em;
            margin-left: -1em;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
        }

        .button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .registered-banner {
            font-size: 18px;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }

        .login-btn {
            background-color: #007bff;
            color: #fff;
        }

        .register-btn {
            background-color: #ffffff;
            color: #007bff;
            border: 1px solid #007bff;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    
    <div class="container">
        <div class="registered-banner">
            Registered Users: <span id="userCount">0</span>
        </div>
        <h1>   < ChoreSkip >  </h1>
        <div class="features">
            <ul>
                <li class="feature-item">Fair distribution of chores</li>
                <li class="feature-item">Do extra chores, Earn points</li>
                <li class="feature-item">Skip your turn with earned points</li>
                <li class="feature-item">Admin approval system </li>
                <li class="feature-item">Flexible roster adjustments</li>
            </ul>
        </div>
        <div class="button-container">
            <!-- <a href="http://localhost:8080/login" class="button login-btn">Login</a> -->
            <a href="<c:url value='/login'/>" class="button login-btn">Login</a>
            <a href="<c:url value='/register'/>" class="button register-btn">Register</a>
        </div>
    </div>
    <script>
        (function() {
            let count = 0;
            const target = 106;
            const duration = 2000; // total animation duration in milliseconds
            const incrementTime = Math.floor(duration / target);
            const counterElement = document.getElementById("userCount");

            const timer = setInterval(() => {
                count++;
                counterElement.textContent = count;
                if (count >= target) {
                    clearInterval(timer);
                }
            }, incrementTime);
        })();
    </script>
</body>
</html>
