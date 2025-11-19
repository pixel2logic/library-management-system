<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Book</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Book Details</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link active">Books</a>
            </nav>
        </header>

        <main>
            <div class="detail-container">
                <h2>${book.title}</h2>

                <div class="detail-section">
                    <div class="detail-row">
                        <span class="detail-label">ID:</span>
                        <span class="detail-value">${book.id}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Title:</span>
                        <span class="detail-value">${book.title}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">ISBN:</span>
                        <span class="detail-value">${book.isbn}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Author:</span>
                        <span class="detail-value">
                            <a href="<c:url value='/authors/view/${book.author.id}'/>">${book.author.name}</a>
                        </span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Genre:</span>
                        <span class="detail-value">${book.genre}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Published Date:</span>
                        <span class="detail-value">${book.publishedDate}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Price:</span>
                        <span class="detail-value">$${book.price}</span>
                    </div>

                    <div class="detail-row">
                        <span class="detail-label">Pages:</span>
                        <span class="detail-value">${book.pages}</span>
                    </div>

                    <div class="detail-row full-width">
                        <span class="detail-label">Description:</span>
                        <p class="detail-value">${book.description}</p>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="<c:url value='/books/edit/${book.id}'/>" class="btn btn-primary">Edit</a>
                    <a href="<c:url value='/books'/>" class="btn btn-secondary">Back to List</a>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Library Management System</p>
        </footer>
    </div>
</body>
</html>
