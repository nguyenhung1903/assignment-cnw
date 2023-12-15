<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form method="post" action="login" onsubmit="return checkInput()">
    <h2>Login</h2>
    <table>
        <tbody>
        <tr>
            <td><label for="username">Username: </label></td>
            <td><input type="text" id="username" name="username"></td>
        </tr>
        <tr id="error-username" hidden>
            <td>&nbsp;</td>
            <td><span class="invalid-input">
                            Username cannot be empty!
                        </span></td>
        </tr>
        <tr>
            <td><label for="password">Password: </label></td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
        <tr id="error-password" hidden>
            <td>&nbsp;</td>
            <td><span class="invalid-input">
                            Password cannot be empty!
                        </span></td>
        </tr>
        <tr>
            <td colspan="2">
                <div>
                    <button type="submit">Login</button>
                    <button type="reset" onclick="resetInput()">Reset</button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>