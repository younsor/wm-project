package com.wisemk.web.spg.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wisemk.web.spg.service.SrvImplAuth;
import com.wisemk.web.spg.share.Constants;
import com.wisemk.web.spg.share.JsonResult;

import cn.zyy.oss.share.OssLog;

@Component
public class AuthFilter implements Filter
{
    private static final OssLog log = new OssLog();

    @Value("${filter.auth.exclude:/mngr/login,/mngr/logout}")
    private String[]            excludeUrls;

    @Autowired
    private SrvImplAuth         srvAuth;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        /* 不需要认证，直接通过 */
        if (!isNeedAuth(httpServletRequest.getServletPath()))
        {
            chain.doFilter(request, response);
            return;
        }

        /* token认证 */
        String token = httpServletRequest.getHeader(Constants.HTTP_HEADER_USER_TOKEN);
        Integer userId = srvAuth.getUserIdFromToken(token);
        if (userId != null && userId > 0)
        {
            /* 将token有效期复位 */
            srvAuth.resetTokenExpireTime(token, userId);

            httpServletRequest.setAttribute(Constants.HTTP_ATTR_USER_ID, userId);
            chain.doFilter(request, response);
            return;
        }

        /* token认证不通过, 则返回错误 */
        JsonResult jsonResult = new JsonResult(HttpStatus.UNAUTHORIZED, "login timeout");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(JSONObject.toJSONString(jsonResult, true));
    }

    private boolean isNeedAuth(String url)
    {
        for (String excludeUrl : excludeUrls)
        {
            if (url.equals(excludeUrl))
            {
                return false;
            }
        }

        if (url.startsWith("/mngr/") || url.startsWith("mngr/") || url.equals("/mngr") || url.equals("mngr"))
        {
            return true;
        }

        return false;
    }
}