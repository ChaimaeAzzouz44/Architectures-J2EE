<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 07/10/2025
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Articles</title>
    <%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 07/10/2025
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Liste des Articles</title>
        <%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 07/10/2025
  Time: 22:16
--%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>
        <head>
            <title>Liste des Articles</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: linear-gradient(to right, #f8f9fa, #e9ecef);
                    margin: 0;
                    padding: 0;
                }

                h1 {
                    text-align: center;
                    margin-top: 40px;
                    color: #343a40;
                }

                table {
                    width: 80%;
                    margin: 30px auto;
                    border-collapse: collapse;
                    background-color: #ffffff;
                    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                    border-radius: 10px;
                    overflow: hidden;
                }

                thead {
                    background-color: #007bff;
                    color: #ffffff;
                }

                th, td {
                    padding: 14px 18px;
                    text-align: center;
                    border-bottom: 1px solid #dee2e6;
                }

                tbody tr:hover {
                    background-color: #f1f3f5;
                }

                a, button {
                    display: inline-block;
                    padding: 6px 12px;
                    text-decoration: none;
                    border-radius: 4px;
                    font-size: 14px;
                    font-weight: bold;
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                }

                a {
                    background-color: #17a2b8;
                    color: white;
                }

                a:hover {
                    background-color: #138496;
                }

                button {
                    background-color: #dc3545;
                    color: white;
                    border: none;
                }

                button:hover {
                    background-color: #c82333;
                }

                .add-btn {
                    display: block;
                    width: 200px;
                    margin: 30px auto;
                    text-align: center;
                    background-color: #28a745;
                    color: white;
                    padding: 12px;
                    font-size: 16px;
                    font-weight: bold;
                    border-radius: 6px;
                    text-decoration: none;
                    transition: background-color 0.3s ease;
                }

                .add-btn:hover {
                    background-color: #218838;
                }

                /* Responsive */
                @media (max-width: 768px) {
                    table {
                        width: 95%;
                        font-size: 14px;
                    }
                    th, td {
                        padding: 10px;
                    }
                    .add-btn {
                        width: 80%;
                        font-size: 14px;
                    }
                }
            </style>

</head>
<body>
<h1>Liste des Articles</h1>

<table>
    <thead>
    <tr>
        <th>Code</th>
        <th>Désignation</th>
        <th>Prix</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="article" items="${articles}">
        <tr>
            <td>${article.code}</td>
            <td>${article.designation}</td>
            <td>${article.prix}</td>
            <td>
                <a href="app?action=edit&code=${article.code}">Modifier</a>
                <form action="app" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="code" value="${article.code}">
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="app?action=new">Ajouter un Article</a>
</body>
</html>

