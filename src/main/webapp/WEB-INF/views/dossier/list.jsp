<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Dossiers Administratifs</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #9C27B0; color: white; }
        tr:hover { background-color: #f5f5f5; }
        .btn { padding: 8px 16px; text-decoration: none; border-radius: 4px; display: inline-block; margin: 2px; }
        .btn-add { background-color: #4CAF50; color: white; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
    </style>
</head>
<body>
<h1>📋 Gestion des Dossiers Administratifs</h1>

<a href="${pageContext.request.contextPath}/dossiers?action=showAddForm" class="btn btn-add">
    ➕ Ajouter un dossier
</a>

<br><br>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>N° Inscription</th>
        <th>Date Création</th>
        <th>Élève</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dossier" items="${dossiers}">
    <tr>
        <td>${dossier.id}</td>
        <td>${dossier.numeroInscription}</td>
        <td>${dossier.dateCreation}</td>
        <td>${dossier.eleve.nom} ${dossier.eleve.prenom}</td>
        <td>
            <a href="${pageContext.request.contextPath}/dossiers?action=showEditForm&id=${dossier.id}"
               class="btn btn-edit">✏️ Modifier</a>

            <a href="${pageContext.request.contextPath}/dossiers?action=delete&id=${dossier.id}"
               class="btn btn-delete"
               onclick="return confirm('Supprimer ce dossier ?')">🗑️ Supprimer</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/">🏠 Retour à l'accueil</a>
</body>
</html>