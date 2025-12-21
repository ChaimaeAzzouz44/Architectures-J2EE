<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${filiere != null ? 'Modifier' : 'Ajouter'} Filière</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 500px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ddd; border-radius: 4px; }
        textarea { height: 100px; }
        button { margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #45a049; }
        .btn-cancel { background-color: #f44336; margin-left: 10px; }
        .btn-cancel:hover { background-color: #da190b; }
    </style>
</head>
<body>
<h1>${filiere != null ? '✏️ Modifier' : '➕ Ajouter'} une Filière</h1>

<form method="post" action="${pageContext.request.contextPath}/filieres">
    <input type="hidden" name="action" value="${filiere != null ? 'update' : 'insert'}">
    <c:if test="${filiere != null}">
        <input type="hidden" name="id" value="${filiere.id}">
    </c:if>

    <label for="code">Code *</label>
    <input type="text" id="code" name="code" value="${filiere.code}" required>

    <label for="nom">Nom *</label>
    <input type="text" id="nom" name="nom" value="${filiere.nom}" required>

    <label for="description">Description *</label>
    <textarea id="description" name="description" required>${filiere.description}</textarea>

    <button type="submit">💾 Enregistrer</button>
    <a href="${pageContext.request.contextPath}/filieres?action=list">
        <button type="button" class="btn-cancel">❌ Annuler</button>
    </a>
</form>
</body>
</html>