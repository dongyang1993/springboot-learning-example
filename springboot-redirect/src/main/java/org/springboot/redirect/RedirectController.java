package org.springboot.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", required = false) String name) {
        String msg = name == null ? "hello world" : "hello world " + name;
        return msg;
    }

    @RequestMapping("helloSession")
    @ResponseBody
    public String helloSession(@RequestParam("name") String name,
                               @ModelAttribute("name") String sessionName,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        return "hello word " + name + "\n" + "session: " + sessionName;
    }


    /**
     *==============================
     * 转发 forward
     * ==============================
     * 在Spring MVC 中, 构建forward 目标有两种方式:
     * 1. 以字符串的形式构建目标url, url 需要加上 forward: 前缀
     * 2. 使用 ModelAndView 对象来设置转发的forward目标, viewName 可以省略 forward: 前缀, viewName 应该是目标url, 而不是目标视图的函数名.
     * 传参方式:
     * 1. 以字符串的形式构建目标url, 可以使用 query variable的格式拼url
     * 2. 使用 ModelAndView 对象来增加 attribute Object, 其结果也是在拼接url.
     * 取参的方式: 可以使用 @RequestParam 来取参.
     *
     * ==============================
     * 重定向 redirect
     * ==============================
     * redirect 目标有三种构建方式
     * 1. 使用 redirect: 前缀url方式构建目标url
     * 2. 使用 RedirectView 类型指定目标, 推荐使用这个,
     * 3. 使用 ModelAndView 类型指定目标, ModelAndView 视图名默认是forward, 所以对于redirect, 需要加上 redirect: 前缀
     *
     * 传参和取参方式:
     * 1. 传参: 以字符串的形式构建目标url, 可以使用 query variable的格式拼url. 取参: @RequestParam()来fetch
     * 2. 传参: redirectAttributes.addAttribute() 加的attr. 取参: @RequestParam()来fetch
     * 3. 传参: redirectAttributes.addFlashAttribute() 加的attr. 取参: @ModelAttribute()来fetch
     *
     * Flash attribute的特点:
     * 1. addFlashAttribute() 可以是任意类型的数据(不局限在String等基本类型), addAttribute()只能加基本类型的参数.
     * 2. addFlashAttribute() 加的 attr, 不会出现在url 地址栏上.
     * 3. addFlashAttribute() 加的 attr, 一旦fetch后, 就会自动清空, 非常适合 form 提交后 feedback Message.
     *
     */

    /**
     * "redirect:"后面跟着的是"/"和不跟着"/"是不一样的：
     * 1) "redirect:"后面跟着"/"： 说明该URI是相对于项目的Context ROOT的相对路径
     * 2) "redirect:"后面没有跟着"/"： 说明该URI是相对于当前路径
     */

    //重定向到当前目录的兄弟目录
    @RequestMapping("/r11")
    public String redirect11() {
        return "redirect:hello";
    }

    //重定向从根目录开始
    @RequestMapping("/r12")
    public String redirect12() {
        return "redirect:/redirect/hello";
    }

    //重定向到外部链接
    @RequestMapping("/r13")
    public String redirect13() {
        return "redirect:https://www.baidu.com";
    }

    //重定向带参数
    @RequestMapping("/r14")
    public String redirect14(RedirectAttributes redirectAttributes) {
        //用redirectAttributes.addAttribute() 参数会带在重定向URL后面
        redirectAttributes.addAttribute("name", "henry");
        return "redirect:hello";
    }

    //重定向带参数
    @RequestMapping("/r15")
    public String redirect15(RedirectAttributes redirectAttributes) {
        //用redirectAttributes.addFlashAttribute() 参数会暂存于服务器session中，重定向链接会带上sessionId, 重定向URL获取参数后，会从session中移除
        redirectAttributes.addFlashAttribute("name", "henry");
        return "redirect:helloSession";
    }

    @RequestMapping("/r17")
    public String redirect17() {
        String encode = "";
        try {
            encode = URLEncoder.encode("Henry 中文", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:hello?name=" + encode;
    }


    @RequestMapping("r21")
    public void redirect21(HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            response.sendRedirect("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("r22")
    public void redirect22(HttpServletRequest request,
                           HttpServletResponse response) {
        try {
            response.sendRedirect("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("r31")
    public ModelAndView redirect31() {
        ModelAndView modelAndView = new ModelAndView("redirect:hello");
        return modelAndView;
    }

    @RequestMapping("r32")
    public ModelAndView redirect32() {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:hello");
        //等同于RedirectAttributes
        model.addObject("name", "重定向 中文");
        return model;
    }

    //转发Forward
    @RequestMapping("r41")
    public String redirect41() {
        //forward:hello?name=Henry 这种方式可以带参数，当然重定向的时候也可以用这种拼接参数的方法
        return "forward:hello";
    }

    @RequestMapping("r51")
    public RedirectView redirect51() {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("hello");
        return redirectView;
    }

    @RequestMapping("r52")
    public RedirectView redirect52(RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("helloSession");
        redirectAttributes.addAttribute("name", "重定向 zh_CN");
        redirectAttributes.addFlashAttribute("name", "重定向 session zh_CN");
        return redirectView;
    }

}
