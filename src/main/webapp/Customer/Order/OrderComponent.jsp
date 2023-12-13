<%--
  Created by IntelliJ IDEA.
  User: nguyenhung1903
  Date: 12/12/2023
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String addr = (String) request.getAttribute("addr");
    String phone = (String) request.getAttribute("phone");
%>
<button class="form-close-btn" onclick="handleClosePopup('cart')"><i class="fa-solid fa-xmark" style="font-size: 16px; color: white;"></i></button>
<form name="ConfirmOrder" action="${pageContext.request.contextPath}/Order/create" style="display: flex; flex-direction: column; gap: 8px; width: 80%; align-items: self-start;" method="post">
    <h1 style="line-height: 150%;">Order Information</h1>
    <input name="confirmPhone" class="login-inp" type="tel" pattern="0[0-9]{9}" placeholder="Phone" value="<%= phone %>" >
    <input name="confirmAddr" class="login-inp" type="text" placeholder="Address" value="<%= addr %>" >
    <button id="btnOrderConfirm" class="btn" style="background: #f29a51; border: none; width: 100%; margin-top: 16px;">Confirm</button>
</form>

<script>
$("#btnOrderConfirm").click(function (e) {
    e.preventDefault();
    let phone = $("input[name='confirmPhone']").val();
    let addr = $("input[name='confirmAddr']").val();
    if (phone === "" || addr === "") {
        alert("Please fill in all fields");
        return;
    }

    let jsonOrder = {
        "phone": phone,
        "addr": addr,
        "cart": JSON.parse(localStorage.getItem("cart"))
    };

    console.log(jsonOrder);

    $.ajax({
        type: "POST",
        url:"./Order/create",
        data: JSON.stringify(jsonOrder),
        success:function(result){
            alert("Order successfully");
        }
    });
})

</script>