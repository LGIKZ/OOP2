package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class TrigonometryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем данные из формы
        double angle = Double.parseDouble(req.getParameter("angle"));
        String unit = req.getParameter("unit");
        String function = req.getParameter("function");
        int precision = Integer.parseInt(req.getParameter("precision"));

        // Конвертируем угол в радианы, если он в градусах
        if ("degrees".equals(unit)) {
            angle = Math.toRadians(angle);
        }

        // Вычисляем значение функции
        double result = 0;
        switch (function) {
            case "sin":
                result = Math.sin(angle);
                break;
            case "cos":
                result = Math.cos(angle);
                break;
            case "tan":
                result = Math.tan(angle);
                break;
        }

        // Округляем результат до указанной точности
        double scale = Math.pow(10, precision);
        result = Math.round(result * scale) / scale;

        // Отправляем результат клиенту
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Result:</h1>");
        out.println("<p>" + function + "(" + angle + " " + unit + ") = " + result + "</p>");
        out.println("<a href='/trigonometry-app'>Back</a>");
        out.println("</body></html>");
    }
}
