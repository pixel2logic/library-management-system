<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books with Authors</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Books with Authors (Inner Join)</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link">Books</a>
            </nav>
        </header>

        <main>
            <div class="page-header">
                <h2>Combined View - Books and Authors</h2>
                <p class="description">This view demonstrates the custom inner join query between Book and Author entities</p>
            </div>

            <!-- Books with Authors Table -->
            <div class="table-container">
                <c:choose>
                    <c:when test="${empty booksWithAuthors}">
                        <div class="empty-state">
                            <p>No data found. Please add some books and authors first.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>Book ID</th>
                                    <th>Book Title</th>
                                    <th>ISBN</th>
                                    <th>Genre</th>
                                    <th>Price</th>
                                    <th>Author ID</th>
                                    <th>Author Name</th>
                                    <th>Author Email</th>
                                    <th>Author Country</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="bookAuthor" items="${booksWithAuthors}">
                                    <tr>
                                        <td>${bookAuthor.bookId}</td>
                                        <td>${bookAuthor.bookTitle}</td>
                                        <td>${bookAuthor.isbn}</td>
                                        <td>${bookAuthor.genre}</td>
                                        <td>$${bookAuthor.price}</td>
                                        <td>${bookAuthor.authorId}</td>
                                        <td>${bookAuthor.authorName}</td>
                                        <td>${bookAuthor.authorEmail}</td>
                                        <td>${bookAuthor.authorCountry}</td>
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
