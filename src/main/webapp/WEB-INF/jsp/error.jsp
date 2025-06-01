<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - choreLess</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #f6f9fc 0%, #f1f5f9 100%);
            padding: 20px;
            margin: 0;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
        }
        
        .error-card {
            max-width: 500px;
            width: 100%;
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.06);
            padding: 2.5rem;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        
        .error-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        }
        
        .error-icon {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 80px;
            height: 80px;
            margin: 0 auto 1.5rem;
            border-radius: 50%;
            background: #fff5f5;
            color: #ef4444;
            position: relative;
        }
        
        .error-icon:before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            border: 2px solid #fecaca;
            border-radius: 50%;
            animation: pulse 2s infinite;
        }
        
        @keyframes pulse {
            0% { transform: scale(1); opacity: 0.8; }
            70% { transform: scale(1.3); opacity: 0; }
            100% { transform: scale(1); opacity: 0; }
        }
        
        .error-title {
            color: #1e293b;
            font-weight: 700;
            font-size: 1.75rem;
            margin-bottom: 1rem;
        }
        
        .error-message {
            color: #64748b;
            font-size: 1.1rem;
            line-height: 1.6;
            margin-bottom: 1.5rem;
        }
        
        .btn-custom {
            min-width: 140px;
            padding: 0.75rem 1.5rem;
            font-weight: 500;
            border-radius: 6px;
            transition: all 0.2s;
        }
    </style>
</head>
<body>
    <div class="error-card">
        <!-- Animated Alert Icon -->
        <div class="error-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="12" y1="8" x2="12" y2="12"></line>
                <line x1="12" y1="16" x2="12.01" y2="16"></line>
            </svg>
        </div>
        
        <!-- Error Message -->
        <h1 class="error-title">Oops! Something went wrong</h1>
        <p class="error-message">
            We're sorry, but an unexpected error occurred. Our team has been notified and is working to fix this issue.
        </p>
        
        <!-- Action Buttons -->
        <div class="d-flex justify-content-center gap-3">
            <a href="/home" class="btn btn-primary btn-custom">Go to Home</a>
            <a href="/login" class="btn btn-outline-secondary btn-custom">Login Again</a>
        </div>
    </div>
</body>
</html>
