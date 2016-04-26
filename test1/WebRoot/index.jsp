<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

       <head>

              <title>测试TestUrlRewrite</title>

       </head>

       <body>

              <%

                     String id = (String) request.getParameter("id");

                     out.println("<h3> the value is  : " + id +" </h3> ");

              %>  
              
        <!-- <form action="http://query-test.sandpay.com.cn/payment/tradition/orderCancelServlet.do" method="post">
    		<input type="submit" value="提交">
    	</form> -->
              

	</body>

</html>
