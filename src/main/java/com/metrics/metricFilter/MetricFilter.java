package com.metrics.metricFilter;

import com.metrics.metricService.MetricService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MetricFilter implements Filter {

    @Autowired
    private MetricService metricService;

    @Override
    public void init(FilterConfig config) throws ServletException {
//        metricService = (MetricService) WebApplicationContextUtils
//                .getRequiredWebApplicationContext(config.getServletContext())
//                .getBean("metricService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
        System.out.println(httpRequest.getMethod() + " " + httpRequest.getRequestURI());
        chain.doFilter(request, response);

        int status = ((HttpServletResponse) response).getStatus();
        metricService.increaseCount(req,status);
    }

}