<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${eleve != null ? 'Modifier' : 'Ajouter'} Élève</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 500px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ddd; border-radius: 4px; }
        button { margin-top: 20px; padding: 10px 20px; background-color: #2196F3; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #0b7dda; }
        .btn-cancel { background-color: #f44336; margin-left: 10px; }
        .btn-cancel:hover { background-color: #da190b; }
    </style>
</head>
<body>
<h1>${eleve != null ? '✏️ Modifier' : '➕ Ajouter'} un Élève</h1>

<form method="post" action="${pageContext.request.contextPath}/eleves">
    <input type="hidden" name="action" value="${eleve != null ? 'update' : 'insert'}">
    <c:if test="${eleve != null}">
        <input type="hidden" name="id" value="${eleve.id}">
    </c:if>

    <label for="matricule">Matricule *</label>
    <input type="text" id="matricule" name="matricule" value="${eleve.matricula}" required>

    <label for="nom">Nom *</label>
    <input type="text" id="nom" name="nom" value="${eleve.nom}" required>

    <label for="prenom">Prénom *</label>
    <input type="text" id="prenom" name="prenom" value="${eleve.prenom}" required>

    <label for="email">Email *</label>
    <input type="email" id="email" name="email" value="${eleve.email}" required>

    <label for="filiereId">Filière *</label>
    <select id="filiereId" name="filiereId" required>
        <option value="">-- Sélectionner une filière --</option>
        <c:forEach var="filiere" items="${filieres}">
            <option value="${filiere.id}"
                ${eleve != null && eleve.filiere.id == filiere.id ? 'selected' : ''}>
                    ${filiere.nom}
            </option>
        </c:forEach>
    </select>

    <button type="submit">💾 Enregistrer</button>
    <a href="${pageContext.request.contextPath}/eleves?action=list">
        <button type="button" class="btn-cancel">❌ Annuler</button>
    </a>
</form>
</body>
</html>