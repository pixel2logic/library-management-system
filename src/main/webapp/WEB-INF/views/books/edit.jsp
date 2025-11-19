<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Edit Book</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link active">Books</a>
            </nav>
        </header>

        <main>
            <div class="form-container">
                <h2>Update Book Information</h2>

                <form:form action="${pageContext.request.contextPath}/books/update/${book.id}" method="post" modelAttribute="book" cssClass="form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="title">Title: <span class="required">*</span></label>
                            <form:input path="title" id="title" cssClass="form-control" placeholder="Enter book title" required="true"/>
                            <form:errors path="title" cssClass="error-message"/>
                        </div>

                        <div class="form-group">
                            <label for="isbn">ISBN: <span class="required">*</span></label>
                            <form:input path="isbn" id="isbn" cssClass="form-control" placeholder="Enter ISBN" required="true"/>
                            <form:errors path="isbn" cssClass="error-message"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="author.id">Author: <span class="required">*</span></label>
                            <form:select path="author.id" id="author.id" cssClass="form-control" required="true">
                                <form:option value="" label="Select an author"/>
                                <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                            </form:select>
                            <form:errors path="author" cssClass="error-message"/>
                        </div>

                        <div class="form-group">
                            <label for="genre">Genre:</label>
                            <form:input path="genre" id="genre" cssClass="form-control" placeholder="Enter genre"/>
                            <form:errors path="genre" cssClass="error-message"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="publishedDate">Published Date:</label>
                            <form:input path="publishedDate" id="publishedDate" type="date" cssClass="form-control"/>
                            <form:errors path="publishedDate" cssClass="error-message"/>
                        </div>

                        <div class="form-group">
                            <label for="price">Price:</label>
                            <form:input path="price" id="price" type="number" step="0.01" cssClass="form-control" placeholder="0.00"/>
                            <form:errors path="price" cssClass="error-message"/>
                        </div>

                        <div class="form-group">
                            <label for="pages">Pages:</label>
                            <form:input path="pages" id="pages" type="number" cssClass="form-control" placeholder="Number of pages"/>
                            <form:errors path="pages" cssClass="error-message"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description">Description:</label>
                        <form:textarea path="description" id="description" cssClass="form-control" rows="5" placeholder="Enter book description"/>
                        <form:errors path="description" cssClass="error-message"/>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Update Book</button>
                        <a href="<c:url value='/books'/>" class="btn btn-secondary">Cancel</a>
                    </div>
                </form:form>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Library Management System</p>
        </footer>
    </div>
</body>
</html>
