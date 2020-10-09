package filter;

import dao.ResourceDao;
import vo.Resource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "PermissionFilter")
public class PermissionFilter implements Filter {
    private  String notCheckPath;//不要过滤的请求地址，从web.xml文件读取

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型参数转换为HttpServletRequest类型
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();//获取请求的URL-Pattern地址("xxx.do或xxx.jsp")
        System.out.println("请求地址URL-Pattern："+path);

        if(!notCheckPath.contains(path)){ //若请求的地址并不在 不需要过滤的列表 范围内，则需要判断是否已经登录
            HttpSession session = request.getSession();

            if(session.getAttribute("currentUserName") == null) { //未登录
                request.setAttribute("errorInfo","对不起，您只是游客，没有权限访问！");
                request.getRequestDispatcher("/error.jsp").forward(request,resp);
            }else{ //已经登录，判断当前用户 能否访问 请求地址（权限过滤）
                String currentUserName = session.getAttribute("currentUserName").toString();
                ArrayList<Resource> resourcesList = ResourceDao.findResourceListByUserName(currentUserName);

                boolean tag = false;
                for(Resource res:resourcesList){ //将 path 与 resourcesList中的Resource对象的url属性 循环比对
                    if (res.getUrl().equals(path)) {
                        tag = true;
                        break;
                    }
                }
                if(!tag){ //若请求的地址并不在 当前用户可访问的资源列表中 跳转至/error.jsp
                    request.setAttribute("errorInfo","对不起，您没有权限访问该资源！");
                    request.getRequestDispatcher("/block.jsp").forward(request,resp);
                }else{ //请求的地址在 当前用户可访问的资源列表中，放行
                    chain.doFilter(req,resp);
                }
            }
        }else{ //请求地址是不需要过滤的，直接放行
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //从web.xml配置文件的filter中读取名为notCheckPath的初始化值
        notCheckPath = config.getInitParameter("notCheckPath");
    }

}
