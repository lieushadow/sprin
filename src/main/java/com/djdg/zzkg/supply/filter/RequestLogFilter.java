package com.djdg.zzkg.supply.filter;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;

/**
 * Created by lmh on 2016/6/14.
 */
@Provider
@Component
public class RequestLogFilter implements ContainerRequestFilter,ContainerResponseFilter {

    private Logger logger = Logger.getLogger(RequestLogFilter.class);


    public RequestLogFilter() {
    }


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		URI requestUri = containerRequestContext
				.getUriInfo().getRequestUri();

		if(requestUri.toString().contains("swagger")){
//			swagger 日志不打印
			return;
		}

		logger.info("--------------------------请求内容-----------------------------");
		logger.infov("time {0}",System.currentTimeMillis());
		log(containerRequestContext);
//    	WebContext webContext = new WebContext();
//    	webContext.setStartTime(System.currentTimeMillis());
//      	 String url = MessageFormat.format("Request {0} | {1} | {2} ", containerRequestContext.getRequest().getMethod(), containerRequestContext
//				.getUriInfo().getRequestUri(), containerRequestContext
//				.getHeaders());
//        webContext.setUrl(url);
//    	WebContextHolder.set(webContext);

    }

    private void log(ContainerRequestContext requestContext) {

        MultivaluedMap<String, String> headers = requestContext
                .getHeaders();
	    URI requestUri = requestContext
			    .getUriInfo().getRequestUri();
        String requestMethod = requestContext.getMethod();

        if("OPTIONS".equals(requestMethod)){
			requestContext.abortWith(Response.ok(MediaType.APPLICATION_JSON_TYPE).build());
			return;
		}

        logger.infov("Request {0} {1}", requestMethod, requestUri);
        logger.info("Request MediaType:" + headers.get("Content-Type"));
        logger.info("Request Headers:" + headers);
        if ("OPTIONS".equals(requestMethod)){
            Response response = new ServerResponse(null,200,new Headers<Object>());
            requestContext.abortWith(response);
			return;
		}

	    try {
		    /***
		     * 注册登录不打印请求内容
		     */
//		    if(!requestUri.toString().contains("/user/login")&&!requestUri.toString().contains("/user/regist")&&!requestUri.toString().contains("pwd")){
			    if (("POST".equals(requestMethod)||"PUT"
					    .equals(requestMethod)
					    && requestContext.getEntityStream() != null)) {
				    ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    IOUtils.copy(requestContext.getEntityStream(), baos);
				    byte[] bytes = baos.toByteArray();
				    if(requestContext.getMediaType()!=null
						    && !requestContext.getMediaType().toString().contains("multipart/form-data")){
					    logger.infov(" Body:{0}", new String(bytes, "UTF-8"));
				    }
				    requestContext.setEntityStream(new ByteArrayInputStream(
						    bytes));
			    }
//		    }

        } catch (Exception e) {
            logger.error("log:", e);
        }
    }

	@Override
	public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
		URI requestUri = containerRequestContext
				.getUriInfo().getRequestUri();
		if(requestUri.toString().contains("swagger")){
//			swagger 日志不打印
			return;
		}

//    	if (WebContextHolder.get() != null) {
//			long startTime4request = WebContextHolder.get().getStartTime();
//			if (startTime4request != 0) {
//				recordApiSpendTime(startTime4request, containerRequestContext);
//			} else {
//				logger.info("startTime4request is zero---" + "RequestUri:" + containerRequestContext.getUriInfo().getRequestUri());
//			}
//		} else {
//			logger.info("WebContextHolder.get() is null---" + "RequestUri:" + containerRequestContext.getUriInfo().getRequestUri());
//		}
		logger.info("--------------------------响应内容-----------------------------");
		logOption(containerRequestContext);
		logResponse(containerResponseContext);
	}

	private void recordApiSpendTime(long startTime4request, ContainerRequestContext requestContext) {
		long apiSpendTime = System.currentTimeMillis() - startTime4request;
		logger.info("spend time: " + apiSpendTime + " millisecond");
		if (apiSpendTime >= 1000) {
			logger.info("slow api and spend time: " + apiSpendTime + " millisecond" +
					"RequestUri:" + requestContext
					.getUriInfo().getRequestUri());
		}
	}

	private void logResponse(ContainerResponseContext responseContext) {
		int status = responseContext.getStatus();
		logger.infov("Response Status:{0}", status
				+ " " + responseContext.getStatusInfo() + "");
		logger.infov("Response Headers:{0}", responseContext.getHeaders());

		logger.infov("Response Body:{0}", JSONObject.toJSONString(responseContext.getEntity()), SerializerFeature.DisableCircularReferenceDetect);


	}


	private void logOption(ContainerRequestContext requestContext) {
		String requestMethod = requestContext.getMethod();
		if (StringUtils.equals(requestMethod, "OPTIONS")) {
			MultivaluedMap<String, String> headers = requestContext
					.getHeaders();
			logger.infov("Request {0} {1}", requestMethod, requestContext
					.getUriInfo().getRequestUri());
			logger.info("Request MediaType:"
					+ requestContext.getMediaType());
			logger.info("Request Headers:" + headers);
		}
	}
}
