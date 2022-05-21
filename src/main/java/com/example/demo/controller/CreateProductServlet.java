package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.MysqlProductModel;
import com.example.demo.model.ProductModel;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CreateProductServlet extends HttpServlet {
    private ProductModel productModel;

    public CreateProductServlet() {
        this.productModel = (ProductModel) new MysqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("product", new Product());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String thumbnail = req.getParameter("thumbnail");
        String price = req.getParameter("price");
        System.out.println(name);
        Product product = new Product(id, name, thumbnail, price);
        HashMap<String, String> errors = new HashMap<>();

        if (id == null || id.length() == 0) {
            errors.put("id", "Please enter id");
        }
        if (name == null || name.length() == 0) {
            errors.put("name", "Please enter name");
        }
        if (thumbnail == null || thumbnail.length() == 0) {
            errors.put("thumbnail", "Please enter thumbnail");
        }

        if (price == null || price.length() == 0) {
            errors.put("price", "Please enter price");
        }
        if (errors.size() > 0) {
            req.setAttribute("product", product);
            req.setAttribute("action", 1);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            return;
        }
        if (productModel.save(product) != null) {
            resp.sendRedirect("/admin/product/list");
        } else {
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
        }
    }
}
