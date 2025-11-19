<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Author</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>Create New Author</h1>
            <nav>
                <a href="<c:url value='/'/>" class="nav-link">Home</a>
                <a href="<c:url value='/authors'/>" class="nav-link active">Authors</a>
                <a href="<c:url value='/books'/>" class="nav-link">Books</a>
            </nav>
        </header>

        <main>
            <div class="form-container">
                <h2>Author Information</h2>

                <form:form action="${pageContext.request.contextPath}/authors" method="post" modelAttribute="author" cssClass="form">
                    <div class="form-group">
                        <label for="name">Name: <span class="required">*</span></label>
                        <form:input path="name" id="name" cssClass="form-control" placeholder="Enter author name" required="true"/>
                        <form:errors path="name" cssClass="error-message"/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email: <span class="required">*</span></label>
                        <form:input path="email" id="email" type="email" cssClass="form-control" placeholder="Enter email address" required="true"/>
                        <form:errors path="email" cssClass="error-message"/>
                    </div>

                    <div class="form-group">
                        <label for="country">Country:</label>
                        <form:input path="country" id="country" cssClass="form-control" placeholder="Enter country"/>
                        <form:errors path="country" cssClass="error-message"/>
                    </div>

                    <div class="form-group">
                        <label for="biography">Biography:</label>
                        <form:textarea path="biography" id="biography" cssClass="form-control" rows="5" placeholder="Enter author biography"/>
                        <form:errors path="biography" cssClass="error-message"/>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Create Author</button>
                        <a href="<c:url value='/authors'/>" class="btn btn-secondary">Cancel</a>
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
