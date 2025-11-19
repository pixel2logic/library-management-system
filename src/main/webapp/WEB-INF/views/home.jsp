<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Library Management System</h1>
            <p class="subtitle">Manage Your Books and Authors</p>
            <nav>
                <a href="<c:url value='/authors'/>" class="nav-link">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link">Books</a>
                <a href="<c:url value='/books/with-authors'/>" class="nav-link">Books with Authors</a>
            </nav>
        </header>


        <main class="home-main">
            <div class="welcome-section">
                <h2>Welcome to Library Management System</h2>
                <p>This application allows you to manage books and authors in a library. You can perform the following operations:</p>
            </div>

            <div class="features">
                <div class="feature-card">
                    <h3>Manage Authors</h3>
                    <ul>
                        <li>Create new authors</li>
                        <li>View all authors</li>
                        <li>Update author information</li>
                        <li>View author details</li>
                    </ul>
                    <a href="<c:url value='/authors'/>" class="btn btn-primary">Go to Authors</a>
                </div>

                <div class="feature-card">
                    <h3>Manage Books</h3>
                    <ul>
                        <li>Add new books</li>
                        <li>Browse all books</li>
                        <li>Update book details</li>
                        <li>View book information</li>
                    </ul>
                    <a href="<c:url value='/books'/>" class="btn btn-primary">Go to Books</a>
                </div>

                <div class="feature-card">
                    <h3>View Combined Data</h3>
                    <ul>
                        <li>View books with their authors</li>
                        <li>See complete information</li>
                        <li>Comprehensive view</li>
                    </ul>
                    <a href="<c:url value='/books/with-authors'/>" class="btn btn-primary">View Combined</a>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Library Management System - BDS Assignment 2</p>
        </footer>
    </div>
</body>
</html>
