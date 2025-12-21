<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Cours</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #FF9800; color: white; }
        tr:hover { background-color: #f5f5f5; }
        .btn { padding: 8px 16px; text-decoration: none; border-radius: 4px; display: inline-block; margin: 2px; }
        .btn-add { background-color: #4CAF50; color: white; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
    </style>
</head>
<body>
<h1>📖 Gestion des Cours</h1>

<a href="${pageContext.request.contextPath}/cours?action=showAddForm" class="btn btn-add">
    ➕ Ajouter un cours
</a>

<br><br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Intitulé</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cours" items="${coursList}">
        <tr>
            <td>${cours.id}</td>
            <td>${cours.code}</td>
            <td>${cours.intitule}</td>
            <td>
                <a href="${pageContext.request.contextPath}/cours?action=showEditForm&id=${cours.id}"
                   class="btn btn-edit">✏️ Modifier</a>

                <a href="${pageContext.request.contextPath}/cours?action=delete&id=${cours.id}"
                   class="btn btn-delete"
                   onclick="return confirm('Supprimer ce cours ?')">🗑️ Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/">🏠 Retour à l'accueil</a>
</body>
</html>