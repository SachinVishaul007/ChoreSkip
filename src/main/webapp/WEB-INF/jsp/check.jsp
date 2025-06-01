<%@page import="com.northeastern.choreless.controller.DumpController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>

<html>
<head>
    <title>Dumpster</title>
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

        #skip {
            color: orange;
        }

        container {
            max-width: 90%;
            background-color: #fff;
            padding: 30px;
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
            line-height: 0.1;
            color: #333;
        }

        /* Form styles */
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
        }

        #skipButtonContainer {
            display: flex;
            width: 100%;
            justify-content: space-between;
        }

        #skipbutton {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box; /* Ensure padding and border don't affect the width */
        }

        #skipDropdown {
            width: 18%; /* Adjust this value as needed */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<container>
    <form action="dump" method="post">
        <c:set var="index" value="${applicationScope.index}" />
        <c:set var="roommates" value="${applicationScope.roommates}" />
        <c:set var="debtorsInLine" value="${applicationScope.debtors_in_line}" />
        <h3> Today's Trash Thrower: ${not empty debtorsInLine ? roommates[debtorsInLine[0]] : roommates[index]}</h3>
        <h5>${requestScope.error}</h5>

        <div id="dropdownContainer"></div>
        <p id="skip">${requestScope.skip}</p>
        <div id="skipButtonContainer">
            <input type="submit" value="SUBMIT" id="skipbutton"/>
            <select id="skipDropdown" name="skipValue">
                <c:forEach var="i" begin="1" end="40">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </div>
    </form>
    <form action="skip" method="post">
    </form>
    <p>Last Submission Time: <%= application.getAttribute("lastSubmissionTime")%></p>

    <h4>Skipping Points:</h4>
    <table border="1">
        <thead>
        <tr>
            <th>Roommates</th>
            <th>Points</th>
        </tr>
        </thead>
        
    </table>

</container>

<script>
    var debtorsInLine = [];

    <c:forEach items="${debtorsInLine}" var="debtors">
        debtorsInLine.push("${debtors}");
    </c:forEach>

    var index = ${index};

    if (debtorsInLine) {
        index = debtorsInLine[0];
    }


    var roommates = [];

    <c:forEach items="${roommates}" var="item">
        roommates.push("${item}");
    </c:forEach>

    document.addEventListener("DOMContentLoaded", function () {
        var sacrificeButton = document.getElementById("sacrificeButton");
        var dropdownContainer = document.getElementById("dropdownContainer");

        sacrificeButton.addEventListener("click", function () {
            var newDropdown = document.createElement("select");

            newDropdown.name = "sacrificer";

            for (var i = 0; i < roommates.length; i++) {
                if (i !== index) {
                    var option = document.createElement("option");
                    option.value = roommates[i];
                    option.text = roommates[i];
                    newDropdown.appendChild(option);
                }
            }

            dropdownContainer.innerHTML = 'Choose the Sacrificer: ';
            dropdownContainer.appendChild(newDropdown);
        });
    });
</script>

<footer style="position: fixed; bottom: 0; width: 100%; text-align: center; background-color: #f8f8f8; padding: 0.1px; line-height: 0.1;">
    <p>a Sachin Vishaul Baskar production</p>
    <p>ChoreLess © 2023</p>
</footer>
</body>
</html>
