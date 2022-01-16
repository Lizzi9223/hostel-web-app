<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
    
      <style>
        
          .container{
            background-color: #D2B48C;
            display: flex;
            flex-direction: column;
            height: 100vh;
            justify-content: center;
            align-items: center;
        }
    
    </style>
    

</head>
<body>

    <div class="container">    
        <div>    
            <h1>Oops...</h1>
            <h2>Something went wrong :(</h2>
            <h3>Try again later</h3>
            <br><br>
            <a href="Controller?command=GO_TO_WELCOME_PAGE"><button class="buttons">Main</button></a>            
        </div>        
    </div>

</body>
</html>