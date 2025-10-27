<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 07/10/2025
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Article</title>
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

        form {
            background-color: #ffffff;
            max-width: 400px;
            margin: 30px auto;
            padding: 25px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #495057;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            font-size: 15px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #007bff;
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Responsive */
        @media (max-width: 480px) {
            form {
                width: 90%;
                padding: 20px;
            }
            h1 {
                font-size: 22px;
            }
        }
    </style>

</head>
<body>
<h1>Ajouter un Nouvel Article</h1>

<form action="${pageContext.request.contextPath}/app" method="POST">
    <input type="hidden" name="action" value="create">

    <label for="code">Code:</label>
    <input type="text" name="code" id="code" required><br>

    <label for="designation">Désignation:</label>
    <input type="text" name="designation" id="designation" required><br>

    <label for="prix">Prix:</label>
    <input type="number" step="0.01" name="prix" id="prix" required><br>

    <button type="submit">Ajouter</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/app?action=list">Retour à la liste</a>
</body>
</html>

