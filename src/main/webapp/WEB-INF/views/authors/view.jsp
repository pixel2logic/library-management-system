<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Author</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Author Details</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link active">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link">Books</a>
            </nav>
        </header>

        <main>
            <div class="detail-container">
                <h2>${author.name}</h2>

                <div class="detail-section">
                    <div class="detail-row">
                        <span class="detail-label">ID:</span>
                        <span class="detail-value">${author.id}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Name:</span>
                        <span class="detail-value">${author.name}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Email:</span>
                        <span class="detail-value">${author.email}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Country:</span>
                        <span class="detail-value">${author.country}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Number of Books:</span>
                        <span class="detail-value">${bookCount}</span>
                    </div>

                    <div class="detail-row full-width">
                        <span class="detail-label">Biography:</span>
                        <p class="detail-value">${author.biography}</p>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="<c:url value='/authors/edit/${author.id}'/>" class="btn btn-primary">Edit</a>
                    <a href="<c:url value='/authors'/>" class="btn btn-secondary">Back to List</a>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Library Management System</p>
        </footer>
    </div>
</body>
</html>
