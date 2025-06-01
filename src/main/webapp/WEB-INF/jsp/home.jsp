<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>home</title>
            <style>
                /* Update body to use flexbox properly */
                body {
                    font-family: 'Roboto', sans-serif;
                    background-color: #f2f2f2;
                    margin: 0;
                    padding: 0;
                    min-height: 100vh; /* Ensure full viewport height */
                    display: flex;
                    flex-direction: column; /* Stack elements vertically */
                }


                /* Make the navbar thinner by reducing padding */
                .navbar {
                    padding: 0.25rem 0.5rem !important; /* top/bottom, left/right */
                    margin: 0 !important;              /* override any margin */
                    min-height: auto !important;       /* prevent forced min-height */
                    height: auto !important;
                }
                
                /* Slightly smaller font size for brand to reduce height */
                .navbar-brand {
                    font-size: 1rem !important;   
                    margin: 0 !important;
                    padding: 0 !important;
                    line-height: 1 !important;
                    display: inline-block !important;
                }
                
                /* Shrink the button further if needed */
                .navbar .btn {
                    padding: 0.25rem 0.5rem !important;
                    line-height: 1 !important;
                    margin: 0 !important;
                }

                .container {
                    max-width: 800px;
                    flex: 1;
                    margin: 30px auto;
                }

                .card {
                    border: none;
                    border-radius: 10px;
                    margin-bottom: 20px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    overflow: hidden;
                    transition: transform 0.3s ease-in-out;
                }

                .card:hover {
                    transform: scale(1.05);
                }

                .card-body {
                    text-align: center;
                }

                .card-title {
                    font-size: 1.5rem;
                    font-weight: bold;
                    color: #007bff;
                    margin-bottom: 10px;
                }

                .card-text {
                    font-size: 1rem;
                    color: #555;
                    line-height: 1.4;
                    margin-bottom: 8px;
                }


                .footer {
                    background-color: #f8f8f8;
                    padding: 10px;
                    text-align: center;
                    margin-top: auto; /* This pushes footer to bottom */
                    width: 100%;
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
                    0% {
                        transform: rotate(0deg);
                    }

                    100% {
                        transform: rotate(360deg);
                    }
                }

                .bg-warning {
                    background-color: #ffc107 !important;
                    /* A shade of yellow */
                    color: black;
                    /* Ensuring text is readable on the yellow background */
                }

                .card.bg-warning:hover {
                    transform: none;
                    /* Optional: remove the scaling effect on hover for warning cards */
                }
            </style>
        </head>

        <body>
            <div id="loading-container">
                <div id="loading"></div>
            </div>
            <nav class="navbar navbar-light bg-light d-flex justify-content-between">
                <span class="navbar-brand">ChoreSkip</span>
                <a href="<c:url value='/logout'/>" class="btn btn-danger btn-sm">
                    LOGOUT
                </a>
            </nav>

            <div class="container mt-4">
                <h1 class="text-center mb-4">${welcome}</h1>

                <c:forEach var="chore" items="${chores}">
                    <div class="card ${chore.status == 'INCOMPLETE' ? 'bg-warning' : ''}">
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="<c:url value='/choreDetails'><c:param name='id' value='${chore.choreId}'/></c:url>">${chore.choreName}</a>
                                <c:if test="${not empty chore.skipHistory}">
                                    &nbsp;<a href="#" title="${chore.skipHistory}" style="font-size:0.8rem; color:#007bff; text-decoration:underline;">
                                        Skipping in Progress
                                    </a>
                                </c:if>
                            </h5>
                            
                            <c:choose>
                                <c:when test="${not empty chore.debtors_next_in_line}">
                                    <p class="card-text">${roommates[chore.debtors_next_in_line[0]].name}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text">${roommates[chore.currIndex].name}</p>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${chore.day == null}">
                                <p class="card-text">${chore.choreType}</p>
                            </c:if>
                            <c:if test="${chore.day != null}">
                                <p class="card-text">${chore.day}</p>
                            </c:if>
                            <p class="card-text">
                                <span
                                    class="badge badge-${chore.status == 'DONE' ? 'success' : 'danger'}">${chore.status}</span>
                            </p>
                        </div>
                    </div>
                </c:forEach>

      
            </div>

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            <script>
                // once the page has loaded, remove everything after the domain
                window.history.replaceState(
                    {},                                // empty state object
                    document.title,                    // keep the same title
                    '/'                                // new “fake” URL in the address bar
                );
            </script>

        </body>

        </html>
        <jsp:include page="footer.jsp" />