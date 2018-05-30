package webbey.auth;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminLogonFilter implements Filter {

    @Inject
    private AuthBean authBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        if (authBean.isLoggetIn()){ // если уже залогинен то передаем дальше по цепочке
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // иначе идем на страницу логина
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        authBean.setRequestedPage(request.getRequestURI()); // сохраняем URL который запрашивали
        response.sendRedirect(request.getContextPath()+"/login.xhtml");
    }

    @Override
    public void destroy() {

    }
}
