<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f5fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            max-width: 50%;
            background-color: #fff;
            padding: 60px;
            margin: 0 auto;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            border: 2px solid #007bff;
            text-align: center;
        }

        h2,
        h3,
        h4 {
            font-family: 'Roboto', sans-serif;
            font-weight: bold;
            color: #007bff;
        }

        h4 {
            color: red;
            margin-top: 10px;
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
        }

        label {
            margin-top: 10px;
            display: block;
            font-weight: bold;
            color: #007bff;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 50%;
            padding: 10px;
            margin-top: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }
        .room{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
        /* form div {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 10px;
        } */

        /* form div label,
        form div input {
            width: 48%; 
        } */
    </style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <div class="container">
        <h2>Registration</h2>
        <h3>Enter details of your roommates</h3>
        <h4>${error}</h4>
        <form action="createGroup" method="post">
            <label for="groupName">Group Name:</label>
            <input type="text" id="groupName" name="groupName" required>

            <div id="roommates">
                <div class = "room">
                    <label for="roommate1">Roommate 1:</label>
                    <input type="text" id="roommate1" name="roommates[0].name" oninput="updateAdminDropdown()" required>

                    <label for="email1">Email:</label>
                    <input type="email" id="email1" name="roommates[0].email" required>
                </div>
                    <!-- <div>
                    <label for="roommate1">Roommate 1:</label>
                    <input type="text" id="roommate1" name="roommates[0].name" oninput="updateAdminDropdown()" required>
                </div>
                <div>
                    <label for="email1">Email:</label>
                    <input type="email" id="email1" name="roommates[0].email" required>
                </div> -->
            </div>

            <button type="button" onclick="addRoommate()">+ Add Roommate</button>

            <label for="admin">Who's the Admin?</label>
            <select id="admin" name="admin.name" required></select>

            <label for="password">Enter a password for the group:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Submit">
        </form>
    </div>
        <script>
            var roommateCount = 1;

            // Function to add a roommate and update the admin dropdown
            function addRoommate() {
                var roommatesDiv = document.getElementById("roommates");
                var newRoommateDiv = document.createElement("div");

                roommateCount++;
                var currentCount = roommateCount;


                // newRoommateDiv.innerHTML ='<div>' +
                //     '<label for="roommate' + currentCount + '">Roommate ' + currentCount + ':</label>' +
                //     '<input type="text" id="roommate' + currentCount + '" name="roommates[' + (currentCount - 1) + '].name" required oninput="updateAdminDropdown()">' +
                //     '<label for="email' + currentCount + '">Email:</label>' +
                //     '<input type="email" id="email' + currentCount + '" name="roommates[' + (currentCount - 1) + '].email" required>' + '</div>';

                newRoommateDiv.innerHTML = '<div class = "room">' +
        '<label for="roommate' + currentCount + '">Roommate ' + currentCount + ':</label>' +
        '<input type="text" id="roommate' + currentCount + '" name="roommates[' + (currentCount - 1) + '].name" required oninput="updateAdminDropdown()">' +
        '<label for="email' + currentCount + '">Email:</label>' +
        '<input type="email" id="email' + currentCount + '" name="roommates[' + (currentCount - 1) + '].email" required>' +
        '</div>'; // Add a line break after each pair

        //         newRoommateDiv.innerHTML =
        // '<br><div>' +
        //     '<label for="roommate' + currentCount + '">Roommate ' + currentCount + ':</label>' +
        //     '<input type="text" id="roommate' + currentCount + '" name="roommates[' + (currentCount - 1) + '].name" required oninput="updateAdminDropdown()">' +
        // '</div>' +
        // '<div>' +
        //     '<label for="email' + currentCount + '">Email:</label>' +
        //     '<input type="email" id="email' + currentCount + '" name="roommates[' + (currentCount - 1) + '].email" required>' +
        // '</div>';

                roommatesDiv.appendChild(newRoommateDiv);

                // Update the admin dropdown with the new roommate
                updateAdminDropdown();
            }

            // Function to update the admin dropdown with all roommates
            function updateAdminDropdown() {
                var adminDropdown = document.getElementById("admin");

                // Remove existing options
                adminDropdown.innerHTML = '';

                // Add options for all roommates with non-empty names
                for (var i = 1; i <= roommateCount; i++) {
                    var roommateName = document.getElementById("roommate" + i).value.trim();
                    if (roommateName !== '') {
                        var newOption = document.createElement("option");
                        newOption.value = roommateName;
                        newOption.text = roommateName;
                        adminDropdown.add(newOption);
                    }
                }
            }

            // Initialize the admin dropdown with the first roommate
            document.addEventListener("DOMContentLoaded", function () {
                updateAdminDropdown();
            });
        </script>

    </body>

    </html>















<!-- 

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration Confirmation</title>
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
                max-width: 50%;
                background-color: #fff;
                padding: 60px;
                margin: 0 auto;
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                border: 2px solid #007bff;
                text-align: center;
            }

            h1,
            h2,
            h3 {
                font-family: 'Roboto', sans-serif;
                font-weight: bold;
                color: #007bff;
            }
            h4{
                color : red;
            }
            p {
                font-size: 16px;
                line-height: 1.6;
                color: #333;
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


    </head>

    <body>
        
        <container>
            <h2>Registration </h2>
        <h3>Enter details of your roommates</h3>

        <h4>${error}</h4>

            <form action="createGroup" method="post">

                <label for="groupName">Group Name:</label>
                <input type="text" id="groupName" name="groupName" required>

                <div id="roommates">
                    <div>
                        <label for="roommate1">Roommate 1:</label>
                        <input type="text" id="roommate1" name="roommates[0].name"  oninput="updateAdminDropdown()" required>

                        <label for="email1">Email:</label>
                        <input type="email" id="email1" name="roommates[0].email" required>
                    </div>
                </div>

                <button type="button" onclick="addRoommate()">+ Add Roommate</button>

                <br>
                <label for="admin">Who's the Admin?</label>
                <select id="admin" name="admin.name" required>
                    <!-- Options will be dynamically added using JavaScript -->

<!--                     
                </select>
                <br>

                <label for="password">Enter a password for the group:</label>
                <input type="password" id="password" name="password" required>

                <br><br>
                <input type="submit" value="Submit">

            </form>

        </container> -->
