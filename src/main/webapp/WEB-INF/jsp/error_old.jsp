<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - choreLess</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f8f9fa;
        }
        .error-card {
            max-width: 500px;
            text-align: center;
            padding: 2rem;
        }
        .error-icon {
            font-size: 4rem;
            color: #dc3545;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="card error-card shadow">
        <div class="card-body">
            <div class="error-icon">⚠️</div>
            <h2 class="card-title text-danger mb-3">Oops! Something went wrong</h2>
            <p class="card-text text-muted mb-4">
                We're sorry, but an unexpected error occurred. Our team has been notified and is working to fix this issue.
            </p>
            <a href="/home" class="btn btn-primary me-2">Go to Home</a>
            <a href="/login" class="btn btn-outline-secondary">Login Again</a>
        </div>
    </div>
</div>
</body>
</html>