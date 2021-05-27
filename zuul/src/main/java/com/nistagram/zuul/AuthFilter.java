package com.nistagram.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

   
   
    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    

	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}



	@Override
	public int filterOrder() {
		return 1;
	}

}