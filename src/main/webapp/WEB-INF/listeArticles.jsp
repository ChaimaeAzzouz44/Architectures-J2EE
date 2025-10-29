<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Articles</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-size: 2rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        thead {
            background-color: #2196F3;
        }

        th {
            padding: 15px;
            text-align: center;
            color: white;
            font-weight: 600;
        }

        tbody tr {
            border-bottom: 1px solid #e0e0e0;
        }

        tbody tr:hover {
            background-color: #f9f9f9;
        }

        td {
            padding: 15px;
            text-align: center;
            color: #333;
        }

        .actions {
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-size: 0.9rem;
            cursor: pointer;
            text-decoration: none;
            color: white;
            font-weight: 500;
            transition: opacity 0.3s;
        }

        .btn:hover {
            opacity: 0.8;
        }

        .btn-edit {
            background-color: #FF9800;
        }

        .btn-delete {
            background-color: #f44336;
        }

        .btn-add {
            display: inline-block;
            padding: 12px 24px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: 500;
            transition: opacity 0.3s;
        }

        .btn-add:hover {
            opacity: 0.8;
        }

        .add-container {
            text-align: center;
            margin-top: 20px;
        }

        .empty-state {
            text-align: center;
            padding: 40px;
            color: #999;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .container {
                padding: 15px;
            }

            table {
                font-size: 0.9rem;
            }

            th, td {
                padding: 10px 5px;
            }

            .actions {
                flex-direction: column;
                gap: 5px;
            }

            .btn {
                font-size: 0.85rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
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
        <c:choose>
            <c:when test="${empty articles}">
                <tr>
                    <td colspan="4" class="empty-state">
                        Aucun article disponible
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <td>${article.code}</td>
                        <td>${article.designation}</td>
                        <td>${article.prix} DH</td>
                        <td>
                            <div class="actions">
                                <a href="app?action=edit&code=${article.code}" class="btn btn-edit">
                                    Modifier
                                </a>
                                <form action="app" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="code" value="${article.code}">
                                    <button type="submit" class="btn btn-delete"
                                            onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet article ?');">
                                        Supprimer
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <div class="add-container">
        <a href="app?action=new" class="btn-add">
            Ajouter un Article
        </a>
    </div>
</div>
</body>
</html>