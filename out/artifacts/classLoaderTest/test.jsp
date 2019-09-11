
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.jvm.*" %>

<%
    InputStream is = new FileInputStream("c:/TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();

    out.println("<textarea style='width:1000px;heigth=800px'>");
    out.println(JavaClassExecuter.executer(b));
    out.println("</textarea>");
%>