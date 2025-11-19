<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authors List</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Authors Management</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link active">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link">Books</a>
            </nav>
        </header>

        <main>
            <div class="page-header">
                <h2>All Authors</h2>
                <a href="<c:url value='/authors/new'/>" class="btn btn-success">Add New Author</a>
            </div>

            <!-- Success/Error Messages -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">
                    ${successMessage}
                </div>
            </c:if>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">
                    ${errorMessage}
                </div>
            </c:if>

            <!-- Authors Table -->
            <div class="table-container">
                <c:choose>
                    <c:when test="${empty authors}">
                        <div class="empty-state">
                            <p>No authors found. Click "Add New Author" to create one.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Country</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="author" items="${authors}">
                                    <tr>
                                        <td>${author.id}</td>
                                        <td>${author.name}</td>
                                        <td>${author.email}</td>
                                        <td>${author.country}</td>
                                        <td class="actions">
                                            <a href="<c:url value='/authors/view/${author.id}'/>" class="btn btn-info btn-sm">View</a>
                                            <a href="<c:url value='/authors/edit/${author.id}'/>" class="btn btn-primary btn-sm">Edit</a>
                                            <a href="<c:url value='/authors/delete/${author.id}'/>"
                                               class="btn btn-danger btn-sm"
                                               onclick="return confirm('Are you sure you want to delete this author?')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Library Management System</p>
        </footer>
    </div>
</body>
</html>
