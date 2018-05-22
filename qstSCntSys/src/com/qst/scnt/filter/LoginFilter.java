package com.qst.scnt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qst.scnt.model.UserInfo;
import com.qst.scnt.utils.HttpRequestDeviceUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        if(HttpRequestDeviceUtils.isMobileDevice(req))//如果是手机App访问，则不拦截
        {
        	chain.doFilter(req, resp);
        }
        else //pc端访问，拦截
        {
	        //获取请求路径
	        String path = req.getRequestURI();
	        //获取session中作为判断的字段
	        UserInfo currentUser_session = (UserInfo) session.getAttribute("currentUser");
	        //判断请求的 路径中是否包含了 登录页面的请求
	        //如果包含了，那么不过滤 继续执行操作
	        if (path.indexOf("/login/login.do") > -1||path.indexOf("login.html")>-1 ) 
	        {
	        	chain.doFilter(req, resp);
	        } 
	        else 
	        {
	        	if(path.indexOf(".do") > -1||path.indexOf(".html")>-1 ){
	        		if (currentUser_session == null)//如不包含，那么就要判断 session中否有标志，如果没有标志，那么不让他看，让他去登录，反之执行操作！                     
	        		{	        			
	                	//resp.sendRedirect(loginUrl);
	        	    	 
	        	    	PrintWriter out = response.getWriter();
	        	    	out.print(" <script>window.parent.location.href='../login.html'; </script>");  
	        	            // req.getRequestDispatcher("/index.jsp").forward(request,  
	        	            // response);  
	        	            // 这里因为response已经服务端跳转关闭，所以不会有out流输出，所以js不会执行，(但是好像还存在一个进程,设断点等待)  
	        	            
	                	out.flush();
	                	out.close(); 
	            	} else {
	            		chain.doFilter(req, resp);
	            	}
	        	}
	        	else
	        	{
	        		chain.doFilter(req, resp);
	        	}
	        }
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
