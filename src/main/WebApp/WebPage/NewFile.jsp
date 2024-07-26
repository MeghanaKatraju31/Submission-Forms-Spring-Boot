<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY WEBPAGE</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
    $(function() {
        $("#dateOfBirth").datepicker({
            dateFormat: "yy-mm-dd" // Format the date as needed
        });
    });
</script>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" action="details" modelAttribute="tableclass">
    <h1>WELCOME TO MEGHANA's WEBPAGE</h1>
    <h1>Here You Can Enter Your Details</h1>

    Enter your college id: <form:input path="cid"/>
    <form:errors path="cid" cssClass="error"/><br>

    Enter your Full Name: <form:input path="cname"/>
    <form:errors path="cname" cssClass="error"/><br>

    Enter your Email Address: <form:input path="cemail"/>
    <form:errors path="cemail" cssClass="error"/><br>

    Enter your Date of Birth: <input type="text" id="dateOfBirth" name="dateOfBirth" value="${tableclass.dateOfBirth}"/>
    <form:errors path="dateOfBirth" cssClass="error"/><br>

    Select your Time zone: 
    <form:select path="timezone" id="timezone">
        <form:option value="IST">IST</form:option>
        <form:option value="EST">EST</form:option>
        <!-- Add more timezones as needed -->
    </form:select>
    <form:errors path="timezone" cssClass="error"/><br>
    
    Enter your Time: <form:input path="time"/>
    <form:errors path="time" cssClass="error"/><br>
    
    <form:select path="ampm" id="ampm">
        <form:option value="AM">AM</form:option>
        <form:option value="PM">PM</form:option>
    </form:select>

    <input type="submit" value="Submit"/><br>
</form:form>
</body>
</html>


