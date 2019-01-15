<div xmlns:jsp="http://java.sun.com/JSP/Page">
    <form action="products" method="POST">
        <br/>
        <input type="text" placeholder="Enter username" class="form" name="username"/>
        <br/>
        <input type="text" placeholder="Enter password" class="form" name="password"/>
        <br/>
        <input type="submit" class="button" value="SUBMIT"/>
    </form>
    <form action="servlet?command=registration" method="GET">
        <input type="hidden" name="command" value="registration"/>
        <input type="submit" class="button" value="REGISTRATION"/>
    </form>
</div>
</body>
</html>
