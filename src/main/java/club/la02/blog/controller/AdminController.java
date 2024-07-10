package club.la02.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping({ "", "/", "/index", "/index.html" })
    public String index() {
        return "admin/index";
    }

    @PostMapping("login")
    public String loginApi(
            @RequestParam("userName") String username,
            @RequestParam("password") String password,
            @RequestParam("verifyCode") String verifyCode,
            HttpSession session) {
        if (!verifyCode.equals(session.getAttribute("verifyCode"))) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        if (username.equals("admin") && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "admin/login";
        }

    }

}
