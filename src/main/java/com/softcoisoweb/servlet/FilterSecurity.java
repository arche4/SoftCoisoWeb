package com.softcoisoweb.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
public class FilterSecurity implements Filter{
     @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("X-Frame-Options", "DENY");
        res.addHeader("X-XSS-Protection", "1");
        res.addHeader("X-Content-Type-Options", "nosniff");
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        addSameSiteCookieAttribute((HttpServletResponse) res);
    }

    private void addSameSiteCookieAttribute(HttpServletResponse response) {
        boolean firstHeader = true;
        Collection<String> cookiesHeaders = response.getHeaders("Set-Cookie");
        for (String header : cookiesHeaders) {
            if (firstHeader) {
                response.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
                firstHeader = false;
                continue;
            }
            response.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}