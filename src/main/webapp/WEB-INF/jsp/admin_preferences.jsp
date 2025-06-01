<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>admin</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* container {
            max-width: 100%;
            background-color: #fff;
            padding: 60px;
            margin: 0 auto;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            border: 2px solid #007bff;
            text-align: center;} /* Center align text */
            
        container {
            max-width: 100%;
            background-color: #fff;
            padding: 60px;
            margin-top: 20px;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            border: 2px solid #007bff;
            text-align: center;
            margin-bottom: 20px;
        }
        h1, h2, h3 {
            font-family: 'Roboto', sans-serif;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
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
            text-align: left;
    margin-top: 20px;
        }

        label {
            /* display: block; */
            display: inline-block;
            margin-bottom: 10px;
            margin-right: 20px;
            padding:10 px;
            text-align: right;
            font-weight: bold;
        }

        input, select {
            /* width: 100%; */
            width: calc(100% - 250px);
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
            line-height: 1;
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
        .delete-icon {
    text-decoration: none; /* Remove underline from anchor tag */
    color: #f44336; /* Optional: Set a default color for the icon */
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
        .form-section{
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div id="loading-container">
        <div id="loading"></div>
    </div>
<container>
    
    
<html>
<body>
    <h1>Edit Profile:</h1>
    <form:form method="POST" action="/adminpreferences" modelAttribute="group">
                <form:hidden path="groupId"/>
                
                <div class="form-section">
                    <h2>Group:</h2>
                <label for="groupName">Group Name:</label>
                <form:input path="groupName" placeholder="Group Name"/>
                
                <!-- Password -->
                <label for="password">Password:</label>
                <form:input path="password" placeholder="Password"/>
                <form:hidden path="admin"/>
                </div>
                
                <!-- Roommates -->
                <div class="form-section">
                <h2>Roommates:</h2>
                <c:forEach items="${group.roommates}" var="roommate" varStatus="status">
                    <form:hidden path="roommates[${status.index}].id"/>
                    <label for="roommates[${status.index}].name">Roommate Name:</label>
                    <form:input path="roommates[${status.index}].name" placeholder="Roommate Name"/><a href="#" class="delete-icon" onclick="removeRoommate(${status.index})">❌</a>
                    <br>
                    <label for="roommates[${status.index}].email">Roommate Email:</label>
                    <form:input path="roommates[${status.index}].email" placeholder="Roommate Email"/>
                    <!-- <form:hidden path="roommates[${status.index}].choregroup"/> -->
                    <br><br>
                </c:forEach>
                </div>

                <div class="form-section">
                <h2>Chores:</h2>
                <c:forEach items="${group.chores}" var="chore" varStatus="status">
                    <form:hidden path="chores[${status.index}].choreId"/>
                    <!-- <form:hidden path="chores[${status.index}].choregroup"/> -->
                    <form:hidden path="chores[${status.index}].sacrifice"/>
                    <form:hidden path="chores[${status.index}].last_chore_sub_time"/>
                    <label for="chores[${status.index}].choreName">Chore Name:</label>
                    <form:input path="chores[${status.index}].choreName" placeholder="Chore Name"/><a href="#" class="delete-icon" onclick="removeChore(${status.index})">❌</a>
                    <br>
                    <label for="chores[${status.index}].choreType">Chore Type:</label>
                    <form:input path="chores[${status.index}].choreType" placeholder="Chore Type"/>
                    <br>
                    <label for="chores[${status.index}].currIndex">Curr Index:</label>
                    <form:input path="chores[${status.index}].currIndex" placeholder="Curr Index"/>
                    <br>
                    <label for="chores[${status.index}].day">Day:</label>
                    <form:input path="chores[${status.index}].day" placeholder="Day"/>
                    <br><br>
                </c:forEach>
                </div>
                
                <input type="submit" value="Submit"/>
            </form:form>
    
</body>
</html>

    
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
            alert('Login successful!');
        }, 1000); // Simulated delay of 2 seconds
    }
</script>
</body>
</html>