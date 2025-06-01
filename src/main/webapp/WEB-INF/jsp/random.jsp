<%@page import="com.northeastern.choreless.controller.DumpController" %>
    <%@page import="com.northeastern.choreless.model.Roommate" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <%@ page import="java.util.List" %>
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
                                    background-color: #f2f5fc;
                                    display: flex;
                                    flex-direction: column; /* CHANGED: Set flex-direction to column for header inclusion */
                                    align-items: center;
                                    min-height: 100vh; /* CHANGED: Use min-height instead of fixed height */
                                }
                                
                                /* CHANGED: Added header styling for modern navigation */
                                header {
                                    width: 100%;
                                    padding: 20px;
                                    background-color: #fff;
                                    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                                    display: flex;
                                    justify-content: flex-start;
                                }
                                header a {
                                    text-decoration: none;
                                    color: #007bff;
                                    margin-left: 20px;
                                    font-weight: bold;
                                    transition: color 0.3s ease;
                                }
                                header a:hover {
                                    color: #0056b3;
                                }
                        
                                /* CHANGED: Replaced custom <container> with a .container class */
                                    .container {
                                        text-align: center;
                                        max-width: 400px;
                                        padding: 20px;
                                        background-color: #ffffff;
                                        border-radius: 10px;
                                        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                                        margin-top: 80px; /* so content doesn't hide behind header */
                                    }
                        
                                h1, h2, h3 {
                                    font-family: 'Roboto', sans-serif; /* CHANGED: Use Google Font */
                                    font-weight: bold;
                                    color: #007bff;
                                }
                        
                                h5 {
                                    color: #f44336;
                                    font-weight: bold;
                                }
                        
                                p {
                                    font-size: 16px;
                                    line-height: 1.5;
                                    color: #333;
                                }
                        
                                /* CHANGED: Updated form styles for modern design */
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
                                    box-sizing: border-box;
                                }
                        
                                /* CHANGED: Updated button styles for a modern look */
                                input[type="submit"], input[type="button"] {
                                    background-color: #007bff;
                                    color: #fff;
                                    border: none;
                                    padding: 10px 20px;
                                    border-radius: 5px;
                                    cursor: pointer;
                                    transition: background-color 0.3s ease;
                                    margin: 5px;
                                }
                                input[type="submit"]:hover, input[type="button"]:hover {
                                    background-color: #0056b3;
                                }
                        
                                /* CHANGED: Updated #skipbutton styling for a modern appearance */
                                /* Make the skip button look special with a gold gradient and some depth */
#skipbutton {
    width: 80%;
    padding: 10px 20px;
    cursor: pointer;
    margin-right: auto;

    /* Gold gradient background */
    background: linear-gradient(135deg, #f0c14b 0%, #d69f2b 100%);

    /* Subtle border and rounded corners */
    border: 1px solid #a88734;
    border-radius: 5px;

    /* Text styling */
    color: #111;
    font-weight: bold;
    text-transform: uppercase;

    /* Slight box shadow for depth */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);

    /* Smooth transition on hover */
    transition: background 0.3s ease, box-shadow 0.3s ease;
}

#skipbutton:hover {
    /* Lighter gradient on hover */
    background: linear-gradient(135deg, #f7dfa5 0%, #d69f2b 100%);

    /* Slightly bigger shadow on hover */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.25);
}
                        
                                #skipCount {
                                    width: 20%;
                                    padding: 10px;
                                    border: 1px solid #ccc;
                                    border-radius: 5px;
                                    box-sizing: border-box;
                                    min-width: 60px;
                                }
                        
                                #flexing {
                                    display: flex;
                                    flex-direction: row;
                                    padding: 10px;
                                    border: 1px solid #ccc;
                                    border-radius: 5px;
                                    box-sizing: border-box;
                                    width: 200px;
                                    margin-top: 10px;
                                }
                        
                                /* Add this style for sliding animation */
                                h3 {
                                    opacity: 0;
                                    transform: translateY(20px);
                                    transition: opacity 0.5s ease, transform 0.5s ease;
                                }
                        
                                h3.show {
                                    opacity: 1;
                                    transform: translateY(0);
                                }
                                @keyframes spin {
                                    0% { transform: rotate(0deg); }
                                    100% { transform: rotate(360deg); }
                                }
                                table {
                                    width: 100%;                /* Let it fill its container */
                                    max-width: 600px;           /* Optional: limit max width */
                                    margin: 20px auto;          /* Center the table */
                                    border-collapse: collapse;  /* Merge borders for a clean look */
                                    border-radius: 8px;         /* Rounded corners */
                                    overflow: hidden;           /* Ensure corners stay rounded if table extends */
                                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
                                }
                                
                                /* Table header: blue background, white text */
                                thead {
                                    background-color: #007bff;
                                    color: #fff;
                                }
                                
                                th, td {
                                    padding: 12px 16px;
                                    border-bottom: 1px solid #ddd;
                                    text-align: left;
                                    font-weight: normal;
                                }
                                
                                /* Zebra-striping: lighter background on even rows */
                                tbody tr:nth-child(even) {
                                    background-color: #f9f9f9;
                                }
                                
                                /* Hover highlight on table rows */
                                tbody tr:hover {
                                    background-color: #f1f1f1;
                                }

                            </style>
                            <script src="https://code.jquery.com/jquery-3.6.4.min.js">

                            </script>
                            <script>
                                document.addEventListener("DOMContentLoaded", function () {
                                    var nextChoreDoer = document.getElementById("nextChoreDoer");

                                    // Function to add the 'show' class and trigger the slide-in animation
                                    function showNextChoreDoer() {
                                        nextChoreDoer.classList.add("show");
                                    }
                                    var isErrorEmpty = ${empty requestScope.error};
                                    // Check if requestScope.error is empty
                                    if (isErrorEmpty) {
                                    // Show the element initially
                                    showNextChoreDoer();
                                    
                                    }
                                    else {
                                        nextChoreDoer.style.transition = "none";
                                        showNextChoreDoer();
                                    }
                                });
                            </script>
                        </head>

                        <body>
                            <header>
                                <a href="<c:url value='/home'/>">HOME</a>
                                <a href="<c:url value='/logout' />">LOGOUT</a>
                            </header>
                            <div id="loading-container" style="display: none; justify-content: center; align-items: center; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(255, 255, 255, 0.8);">
                                <div id="loading" style="border: 8px solid #f3f3f3; border-top: 8px solid #3498db; border-radius: 50%; width: 50px; height: 50px; animation: spin 3s linear infinite;"></div>
                            </div>                            
                            <div class ="container">
                                <form id="submitForm" method="post">
                                    <input type="hidden" name="id" value="${applicationScope.groupId}" />
                                    <c:set var="index" value="${applicationScope.index}" />
                                    <c:set var="roommates" value="${applicationScope.roommates}" />
                                    <c:set var="debtorsInLine" value="${applicationScope.debtors_in_line}" />
                                    <c:set var="roommate_ids" value="${applicationScope.roommate_ids}" />
                                    <c:set var="sacrifice_arr" value="${applicationScope.sacrifice}" />
                                    <h2>${chore_name}</h2>
                                    <h3 id="nextChoreDoer"> Next Chore Doer: ${not empty debtorsInLine ?
                                        roommates[debtorsInLine[0]] :
                                        roommates[index]}</h3>
                                    <h5>${requestScope.error}</h5>

                                    <div id="dropdownContainer"></div>
                                    <p id="skip">${applicationScope.skip}</p>
                                    <input type="submit" value="MARK COMPLETE" /> &nbsp; <input type="button"
                                        id="sacrificeButton" value="TAKE OVER" />

                                </form>
                                <form id="skipForm" method="post">
                                    <input type="hidden" name="id" value="${applicationScope.groupId}" />
                                    <div id="flexing">
                                        <input type="submit" id="skipbutton" value="SKIP" />
                                        <select id="skipCount" name="skipCount" name="skipValue">
                                            <c:forEach var="i" begin="1" end="${skipCountMax}">
                                                <option value="${i}">${i}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </form>
                                <p>Most recent completion: <%= application.getAttribute("lastSubmissionTime")%>
                                </p>

                                <input type="hidden" name="group_hidden" value="${groupId}">
                                <h4>Skipping Points:</h4>
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>Roommates</th>
                                            <th>Points</th>
                                            <th>Debtors</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="ind" begin="0" end="${fn:length(roommates)-1}" step="1">
                                            <tr>
                                                <td>${roommates[ind]}</td>
                                                <td>${sacrifice[ind]}</td>
                                                <td><a href="javascript:void(0);"
                                                        onclick="displayAlert(${ind})"><i>view</i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>


                            <script>

                                <c:set var="groupId" value="${applicationScope.groupId}" />
                                var id = ${ groupId };

                                // Get the form element
                                var form = document.getElementById('submitForm');

                                // Set the action attribute with the variable value
                                // form.action = 'http://localhost:8080/dump/' + id;
                                var baseUrl = window.location.protocol + '//' + window.location.host;
                                // form.action = baseUrl + '/dump/' + id;
                                form.action = baseUrl + '/dump';
                                // href="<c:url value='/logout' />"

                                var skipform = document.getElementById('skipForm');

                                // Set the action attribute with the variable value
                                // skipform.action = 'http://localhost:8080/skip/' + id;
                                baseUrl = window.location.protocol + '//' + window.location.host;
                                // skipForm.action = baseUrl + '/skip/' + id;
                                skipForm.action = baseUrl + '/skip';


                                var debtorsInLine = [];

                                <c:forEach items="${debtorsInLine}" var="debtors">
                                    debtorsInLine.push("${debtors}");
                                </c:forEach>


                                var index = ${ index };

                                // var sacrifice_arr =[];

                                // <c:forEach items="${sacrifice_arr}" var="s">
                                //     sacrifice_arr.push("${sacrifice}");
                                // </c:forEach>



                                if (debtorsInLine.length > 0) {
                                    index = parseInt(debtorsInLine[0], 10);
                                }

                                // console.log(debtorsInLine, index);

                                var roommates = [];

                                <c:forEach items="${roommates}" var="item">
                                    roommates.push("${item}");
                                </c:forEach>

                                var roommate_ids = [];

                                <c:forEach items="${roommate_ids}" var="item">
                                    roommate_ids.push("${item}");
                                </c:forEach>

                                var condition = (parseInt("${sacrifice_arr[not empty debtorsInLine ?debtorsInLine[0]:index]}") > 0)                 /////////////////////////////////////////////

                                if (!condition) {
                                    document.getElementById("skipCount").disabled = true;
                                }


                                document.addEventListener("DOMContentLoaded", function () {
                                    var sacrificeButton = document.getElementById("sacrificeButton");
                                    var dropdownContainer = document.getElementById("dropdownContainer");

                                    sacrificeButton.addEventListener("click", function () {
                                        if (dropdownContainer.innerHTML.trim() !== "") {
                                            dropdownContainer.innerHTML = "";
                                            return;
                                        }
                                        var newDropdown = document.createElement("select");

                                        newDropdown.name = "sacrificer";

                                        for (var i = 0; i < roommates.length; i++) {
                                            if (i !== index) {
                                                var option = document.createElement("option");
                                                option.value = roommate_ids[i];
                                                option.text = roommates[i];
                                                newDropdown.appendChild(option);
                                            }
                                        }

                                        dropdownContainer.innerHTML = 'Who is taking over for ' + roommates[index] + '? ';
                                        dropdownContainer.appendChild(newDropdown);
                                    });
                                });

                                function displayAlert(index) {
                                    var groupId = document.querySelector("input[name='group_hidden']").value;
                                    $.ajax({
                                        type: 'GET',
                                        url: '/getDebtors/' + groupId,  // Replace with the actual URL to fetch debtors data
                                        data: { index: index },
                                        success: function (debtors) {
                                            // Construct the alert message
                                            var alertMessage = "Debtors who owe you (in chronological order):\n";
                                            for (var i = 0; i < debtors.length; i++) {
                                                alertMessage += roommates[debtors[i]] + "\n";
                                            }

                                            // Display the alert
                                            alert(alertMessage);
                                        },
                                        error: function () {
                                            alert('Error fetching debtors');
                                        }
                                    });
                                }
                                document.addEventListener("DOMContentLoaded", function () {
    var form = document.getElementById('submitForm'); // Adjust the ID as per your form
    var skipForm = document.getElementById('skipForm');


    skipForm.onsubmit = function() {
        // Show loading animation
        document.getElementById('loading-container').style.display = 'flex';
    }
    form.onsubmit = function() {
        // Show loading animation
        document.getElementById('loading-container').style.display = 'flex';

        // Here you would typically have an AJAX call or some asynchronous operation
        // For demonstration, using setTimeout to simulate async operation
        
    };
});



                            </script>
                            <!-- mask the detail URL so only "/" shows in the address bar -->
                            <script>
                                document.addEventListener("DOMContentLoaded", function() {
                                    window.history.replaceState({}, document.title, '/');
                                });
                            </script>
                            
                        </body>

                        </html>