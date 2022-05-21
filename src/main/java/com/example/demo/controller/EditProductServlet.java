package com.example.demo.controller;



import com.example.demo.entity.Product;
import com.example.demo.model.MysqlProductModel;
import com.example.demo.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    private ProductModel productModel;

    public EditProductServlet() {
        this.productModel = new MysqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String id = req.getParameter("id");
        // kiểm tra trong database xem có tồn tại không.
        Product product = productModel.findById(id);
        // nếu không trả về trang 404
        if (product == null) {
            req.setAttribute("message", "Product not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("student", product);
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String id = req.getParameter("id");
        Product existingStudent = productModel.findById(id);
        if(existingStudent == null){
            req.setAttribute("message", "Product not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else{
            id = req.getParameter("id");
            String name = req.getParameter("name");
            String thumbnail = req.getParameter("thumbnail");
            String price = req.getParameter("price");
            System.out.println(name);
            Product product = new Product(id, name, thumbnail, price);
            // validate dữ liệu
            if (productModel.update(id, product) != null) {
                resp.sendRedirect("/admin/product/list");
            } else {
                // nếu có trả về trang form
                req.setAttribute("product", product);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            }
        }
    }
}
