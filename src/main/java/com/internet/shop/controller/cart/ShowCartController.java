package com.internet.shop.controller.cart;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static final String USER_ID = "user_id";
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        List<Product> cart = shoppingCart.getProducts();
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/views/shopping-cart/products/cart.jsp")
                .forward(req, resp);
    }
}
