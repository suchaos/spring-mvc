package com.suchaos.spring.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 异步 Servlet
 *
 * @author suchao
 * @date 2020/8/22
 */
@WebServlet(
        asyncSupported = true,
        name = "my-async-servlet",
        urlPatterns = "/async-servlet"
)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printWithThreadName("service start");
        if (req.isAsyncSupported()) {
            AsyncContext asyncContext = req.startAsync();
            asyncContext.setTimeout(50L);
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                    printWithThreadName("onComplete: " + event);
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    HttpServletResponse response = (HttpServletResponse)event.getSuppliedResponse();
                    response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    printWithThreadName("onTimeout: " + event);
                    response.flushBuffer();
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    printWithThreadName("onError: " + event);
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                    printWithThreadName("onStartAsync: " + event);
                }
            });
        }
    }

    public static void printWithThreadName(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncServlet " + threadName + ": " + object);
    }
}
