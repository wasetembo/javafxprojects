<%@ page import="java.util.*" %>
<%
    // Retrieve random numbers and user answers
    int[] num1 = (int[]) session.getAttribute("num1");
    int[] num2 = (int[]) session.getAttribute("num2");

    int questionCount = num1.length;
    int correctAnswers = 0;

    // Array to store user answers
    int[] userAnswers = new int[questionCount];
    for (int i = 0; i < questionCount; i++) {
        userAnswers[i] = Integer.parseInt(request.getParameter("answer" + i));
    }

    // Check answers and calculate results
    boolean[] isCorrect = new boolean[questionCount];
    for (int i = 0; i < questionCount; i++) {
        int correctAnswer = num1[i] + num2[i];
        if (userAnswers[i] == correctAnswer) {
            isCorrect[i] = true;
            correctAnswers++;
        } else {
            isCorrect[i] = false;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Results</title>
</head>
<body>
    <h1>Quiz Results</h1>
    <% for (int i = 0; i < questionCount; i++) { %>
        <p>
            Question <%= i + 1 %>: <%= num1[i] %> + <%= num2[i] %> = <strong><%= num1[i] + num2[i] %></strong><br>
            Your Answer: <%= userAnswers[i] %> -
            <% if (isCorrect[i]) { %>
                <span style="color: green;">Correct</span>
            <% } else { %>
                <span style="color: red;">Incorrect</span>
            <% } %>
        </p>
    <% } %>
    <h2>Total Correct Answers: <%= correctAnswers %> / <%= questionCount %></h2>
    <a href="index.jsp">Try Again</a>
</body>
</html>
