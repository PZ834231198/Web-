package controller;

import dao.CreatVCodeImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(urlPatterns = "/CreateVerifyImage.do")
public class CreateVerifyImageController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把验证码图片生成的过程封装在dao包下的CreateVCodeImage类中

        //System.out.println("123");

        CreatVCodeImage creatVCodeImage = new CreatVCodeImage();
        //产生四位随机字符串
        String vCode = creatVCodeImage.createCode();
        System.out.println(vCode);
        //获取httpSession对象
        HttpSession session = request.getSession();
        //将产生的四位随机字符串存放于session中（存放在session中的数据在一个会话范围内多个程序全局共享），以便验证
        session.setAttribute("vCode",vCode);

        //设置返回的内容
        response.setContentType("img/jpeg");
        //调用封装的类方法生成指定验证码字符串的内存图片
        BufferedImage image = creatVCodeImage.CreatImage(vCode);
        //获取字节流对象
        ServletOutputStream out = response.getOutputStream();
        //将内存图像输出到浏览器，格式为JPEG
        ImageIO.write(image,"JPEG",out);
        //刷新输出缓冲器（立即输出，而不用等待输出缓存满后才送至网络）
        out.flush();
        out.close();
    }
}
