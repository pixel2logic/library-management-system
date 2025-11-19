<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books List</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Books Management</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link active">Books</a>
            </nav>
        </header>

        <main>
            <div class="page-header">
                <h2>All Books</h2>
                <a href="<c:url value='/books/new'/>" class="btn btn-success">Add New Book</a>
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

            <!-- Books Table -->
            <div class="table-container">
                <c:choose>
                    <c:when test="${empty books}">
                        <div class="empty-state">
                            <p>No books found. Click "Add New Book" to create one.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>ISBN</th>
                                    <th>Author</th>
                                    <th>Genre</th>
                                    <th>Price</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${books}">
                                    <tr>
                                        <td>${book.id}</td>
                                        <td>${book.title}</td>
                                        <td>${book.isbn}</td>
                                        <td>${book.author.name}</td>
                                        <td>${book.genre}</td>
                                        <td>$${book.price}</td>
                                        <td class="actions">
                                            <a href="<c:url value='/books/view/${book.id}'/>" class="btn btn-info btn-sm">View</a>
                                            <a href="<c:url value='/books/edit/${book.id}'/>" class="btn btn-primary btn-sm">Edit</a>
                                            <a href="<c:url value='/books/delete/${book.id}'/>"
                                               class="btn btn-danger btn-sm"
                                               onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
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
