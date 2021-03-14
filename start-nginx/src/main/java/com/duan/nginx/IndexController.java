package com.duan.nginx;

import javax.annotation.Resource;

import com.xkcoding.justauth.AuthRequestFactory;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("/oauth")
public class IndexController {

  @Resource
  private AuthRequestFactory factory;

  @RequestMapping("/{type}/callback")
  public String login (@PathVariable String type, AuthCallback callback) {
    AuthRequest authRequest = factory.get(type);
    AuthResponse<AuthUser> response = authRequest.login(callback);
    return "success";
  }
}
