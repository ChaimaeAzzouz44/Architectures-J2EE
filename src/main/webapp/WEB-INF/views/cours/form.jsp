<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${cours != null ? 'Modifier' : 'Ajouter'} Cours</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 500px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ddd; border-radius: 4px; }
        button { margin-top: 20px; padding: 10px 20px; background-color: #FF9800; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #e68900; }
        .btn-cancel { background-color: #f44336; margin-left: 10px; }
        .btn-cancel:hover { background-color: #da190b; }
    </style>
</head>
<body>
<h1>${cours != null ? '✏️ Modifier' : '➕ Ajouter'} un Cours</h1>

<form method="post" action="${pageContext.request.contextPath}/cours">
    <input type="hidden" name="action" value="${cours != null ? 'update' : 'insert'}">
    <c:if test="${cours != null}">
        <input type="hidden" name="id" value="${cours.id}">
    </c:if>

    <label for="code">Code *</label>
    <input type="text" id="code" name="code" value="${cours.code}" required>

    <label for="intitule">Intitulé *</label>
    <input type="text" id="intitule" name="intitule" value="${cours.intitule}" required>

    <button type="submit">💾 Enregistrer</button>
    <a href="${pageContext.request.contextPath}/cours?action=list">
        <button type="button" class="btn-cancel">❌ Annuler</button>
    </a>
</form>
</body>
</html>