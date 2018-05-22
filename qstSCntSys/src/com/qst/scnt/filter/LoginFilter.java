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
        
        if(HttpRequestDeviceUtils.isMobileDevice(req))//������ֻ�App���ʣ�������
        {
        	chain.doFilter(req, resp);
        }
        else //pc�˷��ʣ�����
        {
	        //��ȡ����·��
	        String path = req.getRequestURI();
	        //��ȡsession����Ϊ�жϵ��ֶ�
	        UserInfo currentUser_session = (UserInfo) session.getAttribute("currentUser");
	        //�ж������ ·�����Ƿ������ ��¼ҳ�������
	        //��������ˣ���ô������ ����ִ�в���
	        if (path.indexOf("/login/login.do") > -1||path.indexOf("login.html")>-1 ) 
	        {
	        	chain.doFilter(req, resp);
	        } 
	        else 
	        {
	        	if(path.indexOf(".do") > -1||path.indexOf(".html")>-1 ){
	        		if (currentUser_session == null)//�粻��������ô��Ҫ�ж� session�з��б�־�����û�б�־����ô��������������ȥ��¼����ִ֮�в�����                     
	        		{	        			
	                	//resp.sendRedirect(loginUrl);
	        	    	 
	        	    	PrintWriter out = response.getWriter();
	        	    	out.print(" <script>window.parent.location.href='../login.html'; </script>");  
	        	            // req.getRequestDispatcher("/index.jsp").forward(request,  
	        	            // response);  
	        	            // ������Ϊresponse�Ѿ��������ת�رգ����Բ�����out�����������js����ִ�У�(���Ǻ��񻹴���һ������,��ϵ�ȴ�)  
	        	            
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
