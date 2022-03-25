package com.oline.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/colculateOper")
public class Oper extends HttpServlet {

       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		RequestDispatcher requestdispatcher = request.getRequestDispatcher("/WEB-INF/calc.jsp");
//		requestdispatcher.forward(request, response);
		
	response.setContentType("text/html; charset=UTF-8");
	String input = request.getParameter("input");
	String[] arTemp = null;
	String opers = "+-*/";
	Calc c = null;
	
	int temp = 0, result = 0, count = 0;
	PrintWriter out = response.getWriter();
	
	input = input.replaceAll(" ", ""); //공백을 입력했을 경우 공백을 없애주는 기능
	
	for(int i = 0; i < opers.length(); i++) {
		for(int j = 0; j < input.length(); j++) {
			if(opers.charAt(i) == input.charAt(j)) {
				temp = i;
				count++;
			}
		}
	}

	
	out.print("<p>");
	if(count == 1) {
		
		arTemp = input.split("\\" + opers.charAt(temp)); //사칙연산을 나눌 경우 앞에 \\를 사용해 준다
		
		try {
			c = new Calc(Integer.parseInt(arTemp[0]), Integer.parseInt(arTemp[1]));
			
			switch(temp) {
			case 0:
				result = c.add();
				break;
			case 1:
				result = c.sub();
				break;
			case 2:
				result = c.mul();
				break;
			case 3:
				result = c.div();
				break;
			}
			out.print("결과 :" + result);
		} catch (NumberFormatException e) {
				out.print("정수만 입력 하세요");
		} catch (ArithmeticException e){
			out.print("0으로 나눌 수 없습니다.");
		}
	}else if(count == 0) {
		out.print("연산자를 찾을 수 없습니다.");
	}else {
		out.print("두 정수의 연산만 가능 합니다.");
	}
	out.print("</p>");
	out.print("<a href=calc>JSP 페이지 이동</a>");
	out.close();
	
	
	
	
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
