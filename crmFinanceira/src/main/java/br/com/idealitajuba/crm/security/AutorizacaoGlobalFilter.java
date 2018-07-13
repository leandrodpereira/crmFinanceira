package br.com.idealitajuba.crm.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.idealitajuba.crm.mbeans.LoginMBean;

/**
 * Classe que implementa a os filtros de autentição.
 * 
 * @author Leandro Duarte
 */

@WebFilter("*.xhtml")
public class AutorizacaoGlobalFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		LoginMBean loginMBean = (LoginMBean) ((HttpServletRequest) request).getSession().getAttribute("loginMBean");

		if ((loginMBean == null || !loginMBean.isLogado()) && !request.getRequestURI().endsWith("/Login.xhtml")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {
			response.sendRedirect(request.getContextPath() + "/Login.xhtml");
		} else {
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
