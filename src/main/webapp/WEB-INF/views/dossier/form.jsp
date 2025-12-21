<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${dossier != null ? 'Modifier' : 'Ajouter'} Dossier Administratif</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 500px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ddd; border-radius: 4px; }
        button { margin-top: 20px; padding: 10px 20px; background-color: #9C27B0; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #7B1FA2; }
        .btn-cancel { background-color: #f44336; margin-left: 10px; }
        .btn-cancel:hover { background-color: #da190b; }
    </style>
</head>
<body>
<h1>${dossier != null ? '✏️ Modifier' : '➕ Ajouter'} un Dossier Administratif</h1>

<form method="post" action="${pageContext.request.contextPath}/dossiers">
    <input type="hidden" name="action" value="${dossier != null ? 'update' : 'insert'}">
    <c:if test="${dossier != null}">
        <input type="hidden" name="id" value="${dossier.id}">
    </c:if>

    <label for="numeroInscription">N° Inscription *</label>
    <input type="text" id="numeroInscription" name="numeroInscription"
           value="${dossier.numeroInscription}" required>

    <label for="dateCreation">Date de Création *</label>
    <input type="date" id="dateCreation" name="dateCreation"
           value="${dossier.dateCreation}" required>

    <label for="eleveId">Élève *</label>
    <select id="eleveId" name="eleveId" required>
        <option value="">-- Sélectionner un élève --</option>
        <c:forEach var="eleve" items="${eleves}">
            <option value="${eleve.id}"
                ${dossier != null && dossier.eleve.id == eleve.id ? 'selected' : ''}>
                    ${eleve.nom} ${eleve.prenom} (${eleve.matricula})
            </option>
        </c:forEach>
    </select>

    <button type="submit">💾 Enregistrer</button>
    <a href="${pageContext.request.contextPath}/dossiers?action=list">
        <button type="button" class="btn-cancel">❌ Annuler</button>
    </a>
</form>
</body>
</html>