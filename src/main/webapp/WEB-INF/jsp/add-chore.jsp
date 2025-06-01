<%@page import="com.northeastern.choreless.controller.DumpController" %>
    <%@page contentType="text/html;charset=UTF-8" language="java" %>
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
        
            /* form {
                display: flex;
                flex-direction: column;
                align-items: center;
            } */
        
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
        
            /* .room {
                display: flex;
                flex-direction: column;
                align-items: center;
            } */
        
            .dayDropdown {
                display: none; /* Initially hide day dropdown */
                
            }

            .chore{
                display: flex;
                flex-direction: row;
                width: 100%;

            }

            #roommatesContainer {
                display: flex;
                flex-direction: column;
            }

            .chore_row{
                width:30%;
                
            }
        </style>
        
    </head>

    <body>
        <h2>Decide on your chores:</h2>


        <div class="chore">
        
            <label class="chore_row" for="choreName" style="padding: 10px;">Chore Name:</label>
            <input class="chore_row" type="text" id="choreName" name="choreName[]" required style="width: 200px;padding: 10px;">

            <label class="chore_row" for="choreType" style="padding: 10px;">Chore Type:</label>
            <select class="chore_row" id="choreType" name="choreType[]" onchange="showDayDropdown(this);" required style="width: 200px;padding: 10px;">
                <option value="turn-based">Turn-based</option>
                <option value="week-based">Week-based</option>
            </select>
            

            <div id="dayDropdown" class="dayDropdown chore_row" style="width: 200px;padding: 10px;">
                <label for="dayOfWeek" style="padding: 10px;">Day of the Week:</label>
                <select id="dayOfWeek" name="day[]">
                    <option value="monday">Monday</option>
                    <option value="tuesday">Tuesday</option>
                    <option value="wednesday">Wednesday</option>
                    <option value="thursday">Thursday</option>
                    <option value="friday">Friday</option>
                    <option value="saturday">Saturday</option>
                    <option value="sunday">Sunday</option>
                </select>
            </div>

            <br>
        
        </div>

            <div id="roommatesContainer">
                <!-- Dynamic input fields will be added here -->

            </div>

            <br>
            <button type="button" onclick="addChore()" style="display: flex;
            justify-content: center;align-items: center;">Add Chore</button>
            <br>

            <input type="hidden" name="group_hidden" value="${groupId}">
            <input type="submit" value="Submit" onclick="submitChores()">

       

        <script>
            document.addEventListener("DOMContentLoaded", function () {
    var choreTypeDropdowns = document.querySelectorAll("select[name='choreType[]']");
    choreTypeDropdowns.forEach(function (choreTypeDropdown) {
        showDayDropdown(choreTypeDropdown);

        choreTypeDropdown.addEventListener("change", function () {
            showDayDropdown(choreTypeDropdown);
        });
    });
});
            function addChore() {
                var container = document.getElementById("roommatesContainer");

                // Create a new container for each chore
                var choreContainer = document.createElement("div");
                choreContainer.className = "chore";
                
                // add another class name for chore row
                // choreContainer.classList.add("chore_row");

                // Create chore name input and label
                var choreNameLabel = document.createElement("label");
                choreNameLabel.setAttribute("for", "choreName");
                choreNameLabel.innerText = "Chore Name:";
                choreContainer.appendChild(choreNameLabel);

                var choreNameInput = document.createElement("input");
                choreNameInput.type = "text";
                choreNameInput.name = "choreName[]";
                choreNameInput.required = true;
                choreContainer.appendChild(choreNameInput);

                // Create chore type dropdown and label with unique names or IDs
                var choreTypeLabel = document.createElement("label");
                choreTypeLabel.setAttribute("for", "choreType");
                choreTypeLabel.innerText = "Chore Type:";
                choreContainer.appendChild(choreTypeLabel);

                var choreTypeDropdown = document.createElement("select");
                var uniqueDropdownId = "choreType_" + Date.now(); // Unique ID for each dropdown
                choreTypeDropdown.id = uniqueDropdownId;
                choreTypeDropdown.name = "choreType[]";
                choreTypeDropdown.required = true;
                choreTypeDropdown.addEventListener("change", () => showDayDropdown(choreTypeDropdown));
                // Attach the event listener to the new choreTypeDropdown
    choreTypeDropdown.addEventListener("change", function () {
        showDayDropdown(choreTypeDropdown);
    });
                var optionTurnBased = document.createElement("option");
                optionTurnBased.value = "turn-based";
                optionTurnBased.text = "Turn-based";
                choreTypeDropdown.appendChild(optionTurnBased);

                var optionWeekBased = document.createElement("option");
                optionWeekBased.value = "week-based";
                optionWeekBased.text = "Week-based";
                choreTypeDropdown.appendChild(optionWeekBased);

                choreContainer.appendChild(choreTypeDropdown);

                // Create day dropdown and label with unique names or IDs
                var dayDropdown = document.createElement("div");
                dayDropdown.className = "dayDropdown";

                var dayOfWeekLabel = document.createElement("label");
                dayOfWeekLabel.setAttribute("for", "dayOfWeek");
                dayOfWeekLabel.innerText = "Day of the Week:";
                dayDropdown.appendChild(dayOfWeekLabel);

                var dayOfWeekDropdown = document.createElement("select");
                var uniqueDayDropdownId = "dayOfWeek_" + Date.now(); // Unique ID for each dropdown
                dayOfWeekDropdown.id = uniqueDayDropdownId;
                dayOfWeekDropdown.name = "day[]";

                if (choreTypeDropdown.value === "week-based") {
                var daysOfWeek = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
                for (var i = 0; i < daysOfWeek.length; i++) {
                    var option = document.createElement("option");
                    option.value = daysOfWeek[i].toLowerCase();
                    option.text = daysOfWeek[i];
                    dayOfWeekDropdown.appendChild(option);
                }
            }

                dayDropdown.appendChild(dayOfWeekDropdown);

                choreContainer.appendChild(dayDropdown);

                // Add all elements to the main container
                container.appendChild(choreContainer);

                // container.appendChild(document.createElement("br"));
            }

            function submitChores() {
                // Get all chore data from the form
                var choreNameInputs = document.querySelectorAll("input[name='choreName[]']");
                var choreTypeDropdowns = document.querySelectorAll("select[name='choreType[]']");
                var dayOfWeekDropdowns = document.querySelectorAll("select[name='day[]']");

                // Create an array of Chore objects
                var chores = [];
                for (var i = 0; i < choreNameInputs.length; i++) {
                    var chore = {
                        choreName: choreNameInputs[i].value,
                        choreType: choreTypeDropdowns[i].value,
                        day: dayOfWeekDropdowns[i].value,
                    };

                    // Add debtors for turn-based chores (implement logic based on your requirements)
                    if (chore.choreType === "turn-based") {
                        var chore = {
                            choreName: choreNameInputs[i].value,
                            choreType: choreTypeDropdowns[i].value
                        };
                    }

                    chores.push(chore);
                }

                var groupId = document.querySelector("input[name='group_hidden']").value;
                // Send the list of chores to the server using a POST request
                fetch("/addChores/" + groupId, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(chores)
                })
                    .then(function (response) {
                        // Check if the response status is OK (status code 200)
                        if (response.ok) {
                            return response.text(); // Return the resolved value (text)
                        } else {
                            throw new Error("Failed to add chores");
                        }
                    })
                    .then(function (responseText) {
                        // Handle successful response
                        alert(responseText);
                        window.location.href = "/login";
                    })
                    .catch(function (error) {
                        // Handle error
                        console.error(error);
                    });
            }
            function showDayDropdown(choreTypeDropdown) {
    var dayDropdown = choreTypeDropdown.parentNode.querySelector(".dayDropdown");
    dayDropdown.style.display = choreTypeDropdown.value === "week-based" ? "block" : "none";

    // If week-based, populate day dropdown with values
    if (choreTypeDropdown.value === "week-based") {
        var daysOfWeek = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
        var dayOfWeekDropdown = dayDropdown.querySelector("select[name='day[]']");
        
        // Clear existing options
        dayOfWeekDropdown.innerHTML = "";

        for (var i = 0; i < daysOfWeek.length; i++) {
            var option = document.createElement("option");
            option.value = daysOfWeek[i].toLowerCase();
            option.text = daysOfWeek[i];
            dayOfWeekDropdown.appendChild(option);
        }
    }
}
        </script>

    </body>

    </html>