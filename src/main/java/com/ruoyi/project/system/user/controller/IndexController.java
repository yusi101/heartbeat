package com.ruoyi.project.system.user.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.notice.domain.Notice;
import com.ruoyi.project.system.notice.service.INoticeService;
import com.ruoyi.project.system.post.domain.Post;
import com.ruoyi.project.system.post.service.IPostService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @Value("${visitor.userId}")
    private long userId;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        if(user == null){//游客访问 模拟登陆
            UsernamePasswordToken token = new UsernamePasswordToken("visitor", "123456", "true");
            Subject subject = SecurityUtils.getSubject();
            try
            {
                subject.login(token);
            }
            catch (AuthenticationException e)
            {
            }
            user = getSysUser();
        }
        List<Menu> menus = menuService.selectMenusByUser(user);
        List<Post> posts = postService.selectPostsByUserId(user.getUserId());
        mmap.put("user", user);
        mmap.put("menus", menus);
        mmap.put("posts", posts.get(0));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());
        return "main_v1";
    }
}
