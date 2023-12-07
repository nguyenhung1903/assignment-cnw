<%--
  Created by IntelliJ IDEA.
  User: nguyenhung1903
  Date: 12/7/2023
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>

    <link rel="stylesheet" href="./assets/css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<table>
    <tr>
    <td style="width:15%">
        <nav style="display: flex; flex-direction: column; align-items: self-start; justify-content: space-between; padding: 48px 16px 16px 16px; background: white; min-height: 100vh;">
            <section style="width: 100%;">
                <h1 style="text-align: center; width: 100%; margin-bottom: 32px; font-size: 36px; color: #f29a51;">Dashboard
                </h1>
                <ul style="list-style: none; gap: 16px; display: flex; flex-direction: column; align-items: self-start; width: 100%;">
                    <li class="list-item">
                        <a href="#" style="text-decoration: none;  width: 100%; height: 100%; display: block;" onclick='navChange("./pages/ManageProduct.html")'>Manage Product</a>
                    </li>
                    <li class="list-item">
                        <a href="#" style="text-decoration: none;  width: 100%; height: 100%; display: block;" onclick='navChange("./pages/ViewOrder.html")'>View Customer's Order</a>
                    </li>
                </ul>
            </section>
            <section style="display: grid; place-items: center; padding : 16px; width:100%;">
                <a href="" style="text-decoration: none; color: #f29a51;">Logout</a>
            </section>
        </nav>
    </td><td style="width:85%; vertical-align: top;" id="session_load">

    </td>
    </tr>
</table>
</body>

<script>
    let navChange = (href) => {
        $("#session_load").load(href);
    }
    $(document).ready(function () {
        $("#session_load").load("./pages/ManageProduct.html");
    });
</script>
</html>
