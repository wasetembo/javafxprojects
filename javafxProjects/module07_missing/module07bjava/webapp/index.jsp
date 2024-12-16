<%@ page import="java.util.Random" %>
<%
    // Generate random questions
    int questionCount = 5; // Number of questions
    Random rand = new Random();

    // Arrays to hold random numbers
    int[] num1 = new int[questionCount];
    int[] num2 = new int[questionCount];

    for (int i = 0; i < questionCount; i++) {
        num1[i] = rand.nextInt(50) + 1; // Random numbers between 1 and 50
        num2[i] = rand.nextInt(50) + 1;
    }

    // Store numbers in session to access in result.jsp
    session.setAttribute("num1", num1);
    session.setAttribute("num2", num2);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
    <h1>Addition Quiz</h1>
    <form action="result.jsp" method="post">
        <% for (int i = 0; i < questionCount; i++) { %>
            <p>
                Question <%= i + 1 %>:
                <%= num1[i] %> + <%= num2[i] %> =
                <input type="text" name="answer<%= i %>" required>
            </p>
        <% } %>
        <br>
        <input type="submit" value="Submit Answers">
    </form>
</body>
</html>
