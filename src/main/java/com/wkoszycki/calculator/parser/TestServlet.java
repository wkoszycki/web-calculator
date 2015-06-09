//package com.wkoszycki.calculator.parser;
//
//import com.wkoszycki.calculator.CalculatorService;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet(name = "testServlet", urlPatterns = {"/testcdi"})
//public class TestServlet extends HttpServlet {
//
//  private static final long serialVersionUID = 2638127270022516617L;
//
//  @Inject
//  private CalculatorService service;
//
//  protected void doGet(HttpServletRequest request,
//    HttpServletResponse response)
//    throws ServletException, IOException {
//
//    int a = 2;
//    int b = 4;
//
//    PrintWriter out = response.getWriter();
//    out.println("Hello World: " + service.calculateString("2+7"));
//    out.close();
//  }
//
//}