<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion École</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; min-height: 100vh; }
        h1 { font-size: 2.5em; margin-bottom: 30px; }
        .menu { display: inline-block; margin: 20px; }
        .menu a { display: block; padding: 15px 30px; margin: 10px; background-color: white; color: #667eea; text-decoration: none; border-radius: 10px; font-size: 18px; font-weight: bold; box-shadow: 0 4px 6px rgba(0,0,0,0.1); transition: transform 0.3s; }
        .menu a:hover { transform: translateY(-5px); box-shadow: 0 6px 12px rgba(0,0,0,0.2); }
    </style>
</head>
<body>
<h1>🎓 Système de Gestion Académique</h1>

<div class="menu">
    <a href="${pageContext.request.contextPath}/filieres?action=list">📚 Gestion des Filières</a>
    <a href="${pageContext.request.contextPath}/eleves?action=list">👨‍🎓 Gestion des Élèves</a>
    <a href="${pageContext.request.contextPath}/cours?action=list">📖 Gestion des Cours</a>
    <a href="${pageContext.request.contextPath}/dossiers?action=list">📋 Gestion des Dossiers</a>
</div>
</body>
</html>