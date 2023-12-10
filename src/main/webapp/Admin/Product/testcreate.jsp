<%--
  Created by IntelliJ IDEA.
  User: nguyenhung1903
  Date: 12/8/2023
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="create" method="post" enctype="multipart/form-data">
    <h2>Create</h2>
    <table>
        <tbody>
        <tr>
            <td><label for="ProductId">ProductId</label></td>
            <td><input type="text" name="ProductId"></td>
        </tr>
        <tr>
            <td><label for="ProductName">ProductName</label></td>
            <td><input type="text" name="ProductName"></td>
        </tr>
        <tr>
            <td><label for="CategoryId">CategoryId</label></td>
            <td><input type="text" name="CategoryId"></td>
        </tr>
        <tr>
            <td><label for="Price">Price</label></td>
            <td><input type="text" name="Price"></td>
        </tr>
        <tr>
            <td><label for="Image">Image</label></td>
            <td><input type="file" name="Image"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="OK"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>