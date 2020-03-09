package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDto;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author wxl
 * @date 2020/3/3 - 17:19
 */
@Controller
public class AuthorController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request)
    {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDto);

        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null)
        {
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());

            userMapper.insert(user);
            //登陆成功，写cookie 和session
            request.getSession().setAttribute("user",githubUser);
            System.out.println(githubUser.getName());
            return "redirect:/";
        }
        else
        {
            //登陆失败，重新登陆
            System.out.println("11");
            return "redirect:/";
        }
    }

}
