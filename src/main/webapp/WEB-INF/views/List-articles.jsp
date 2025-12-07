<%--
  Created by IntelliJ IDEA.
  User: Mohamed EL HADDAD
  Date: 22/01/2023
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of items</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bootstrap Table with Add and Delete Row Feature</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            color: #404E67;
            background: #F5F7FA;
            font-family: 'Open Sans', sans-serif;
        }
        .table-wrapper {
            width: 900px;
            margin: 30px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            padding-bottom: 10px;
            margin: 0 0 10px;
        }
        .table-title h2 {
            margin: 6px 0 0;
            font-size: 22px;
        }
        .table-title .add-new {
            float: right;
            height: 30px;
            font-weight: bold;
            font-size: 12px;
            text-shadow: none;
            min-width: 100px;
            border-radius: 50px;
            line-height: 13px;
        }
        .table-title .add-new i {
            margin-right: 4px;
        }
        table.table {
            table-layout: fixed;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
        }
        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }
        table.table th:last-child {
            width: 100px;
        }
        table.table td a {
            cursor: pointer;
            display: inline-block;
            margin: 0 5px;
            min-width: 24px;
        }
        table.table td a.add {
            color: #27C46B;
        }
        table.table td a.edit {
            color: #FFC107;
        }
        table.table td a.delete {
            color: #E34724;
        }
        table.table td i {
            font-size: 12px;
        }
        table.table td a.add i {
            font-size: 14px;
            margin-right: -1px;
            position: relative;
            top: 3px;
        }
        table.table .form-control {
            height: 32px;
            line-height: 32px;
            box-shadow: none;
            border-radius: 2px;
        }
        table.table .form-control.error {
            border-color: #f50000;
        }
        table.table td .add {
            display: none;
        }
        /* alerte **/
        .alert {
            padding: 20px;
            background-color: #f44336;
            color: white;
            opacity: 1;
            transition: opacity 0.6s;
            margin-bottom: 15px;
        }

        .alert.success {background-color: #04AA6D;}
        .alert.info {background-color: #2196F3;}
        .alert.warning {background-color: #ff9800;}

        .closebtn {
            margin-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 22px;
            line-height: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .closebtn:hover {
            color: black;
        }
    </style>
    <script>
        var close = document.getElementsByClassName("closebtn");
        var i;

        for (i = 0; i < close.length; i++) {
            close[i].onclick = function(){
                var div = this.parentElement;
                div.style.opacity = "0";
                setTimeout(function(){ div.style.display = "none"; }, 600);
            }
        }
    </script>
</head>
<body>
<c:if test="${not empty success}">
    <div class="alert success" role="alert">

        <strong>Success!</strong> ${success}

        <!--a href="listart" class="alert-link">List of articles </a-->.
    </div>
</c:if>

<div class="container-lg">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2> <b> Manage articles</b></h2></div>
                    <div class="col-sm-4">

                        <a    href="#addArticleModal" class="button" data-toggle="modal" >
                            <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> New Article</button>
                        </a>
                    </div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Code</th>
                    <th>Designation</th>
                    <th>Prix</th>

                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="art" items="${list}">
                    <tr id="${art.code}">
                        <td><c:out value="${art.code}"/></td>
                        <td><c:out value="${art.designation}"/></td>
                        <td><c:out value="${art.prix}"/></td>

                        <td>
                            <a onclick="editArticle('${art.code}')" href="#editArticleeModal" class="edit" data-toggle="modal" id="edit-btnn"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>

                            <!--a   onclick="editArticle('${art.code}')" class="edit"  href="#editArticleeModal"   title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a-->

                            <a class="delete"    href="delete/<c:out value='${art.code}' />"
                               onclick="return confirm('Are you sure?')"
                               title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                        </td>
                    </tr>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Add Modal HTML -->
<div id="addArticleModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="create" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Ajouter un article</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Code</label>
                        <input  type="text" class="form-control" required name="code" >
                    </div>
                    <div class="form-group">
                        <label>Designation</label>
                        <input  type="text" class="form-control" required name="designation">
                    </div>
                    <div class="form-group">
                        <label>Prix</label>
                        <input  type="text" class="form-control" required name="prix">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- EDIT MODAL-->
<div id="editArticleeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="update">
                <div class="modal-header">
                    <h4 class="modal-title">Modifier Article</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Code</label>
                        <input id="edtCodIn" type="text" class="form-control" required name="code" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label>Nom</label>
                        <input id="edtDesIn" type="text" class="form-control" required name="designation">
                    </div>
                    <div class="form-group">
                        <label>Prix</label>
                        <input id="edtPrIn" type="text" class="form-control" required name="prix">
                    </div>


                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-info" value="Update">
                </div>
            </form>
        </div>
    </div>
</div>


</body>


<script type="text/javascript">
    function editArticle(code)
    {

        let art= document.getElementById(code);

        console.log("article CLicked " + art.innerHTML);

        initEdtValue("edtCodIn",art,1);
        initEdtValue("edtDesIn",art,2);
        initEdtValue("edtPrIn",art,3);
    };

    function initEdtValue(edtId,article, cellIdx)
    {
        document.getElementById(edtId).setAttribute("value",article.cells[cellIdx-1].innerHTML);
    }
</script>




</body>
</html>