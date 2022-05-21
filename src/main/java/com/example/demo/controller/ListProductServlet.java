package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.MysqlProductModel;
import com.example.demo.model.ProductModel;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProductServlet extends HttpServlet {
    private ProductModel productModel;

    public ListProductServlet() {
        this.productModel = (ProductModel) new MysqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productModel.findAll();
        req.setAttribute("listStudent", list);
        req.getRequestDispatcher("/admin/students/list.jsp").forward(req, resp);
    }
}
