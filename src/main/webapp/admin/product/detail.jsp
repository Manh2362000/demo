<%@ page import="t2010a.assginment.entity.Product" %>
<%@ page import="com.example.demo.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 5/12/22
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product product = (Product) request.getAttribute("product");
%>
<jsp:include page="../includes/header.jsp">
    <jsp:param name="title" value="${product.getname()} | Product detail"/>
</jsp:include>
<jsp:include page="../includes/menu.jsp"></jsp:include>
    <a href="/admin/product/list">Back to list</a> &nbsp;
    <a href="/admin/product/create">Create new product</a>
    <div>
        id: <%=product.getId()%>
    </div>
    <div>
        Fullname: <%=product.getName()%>
    </div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
