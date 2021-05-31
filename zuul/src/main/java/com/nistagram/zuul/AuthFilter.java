package com.nistagram.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import feign.FeignException;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

   
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }
    
    @Override
    public Object run() {  //izmeniti

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getHeader("email") == null) {
            return null;
        };

        String email = request.getHeader("email");
        try {
            //authClient.verify(email);

            ctx.addZuulRequestHeader("username", email);
            ctx.addZuulRequestHeader("role", "ROLE_KORISNIK");

        } catch (FeignException.NotFound e) {
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }
   

}