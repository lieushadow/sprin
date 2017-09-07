package com.djdg.zzkg.supply.filter;



import com.djdg.zzkg.supply.common.RedisBean;
import com.djdg.zzkg.supply.common.RedisKeys;
import com.djdg.zzkg.supply.common.Result;
import com.djdg.zzkg.supply.enums.ErrCode;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 检查Token是否过期
 */
@Provider
@Component
public class SecurityRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger
			.getLogger(SecurityRequestFilter.class);
	public static final String SECURITY_FLAG = "token";


	@Context
	private ResourceInfo resourceInfo;
	@Resource
	private RedisBean redisBean;
	//延长时间
	@Value("${token.lost.time}")
	private int tokenLostTime;


	public void filter(ContainerRequestContext requestContext) {

		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(Secured.class)) {
			return;
		}

		// Get request headers
		final MultivaluedMap<String, String> headers = requestContext
				.getHeaders();
		//查找是否传入token
		List<String> tokens = headers.get(SECURITY_FLAG);
		Result result = new Result(ErrCode.LOGIN_FAILD.getCode(), ErrCode.LOGIN_FAILD.getMsg());
		Response failLoginResponse = Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
		if(tokens==null||tokens.isEmpty()){
			requestContext.abortWith(failLoginResponse);
			return;
		}
		//查询token是否有效
		String token = tokens.get(0);
		if(StringUtils.isEmpty(token)){
			requestContext.abortWith(failLoginResponse);
			return;
		}

		String key4UserToken = RedisKeys.key4UserToken(token);
		String stringValue = redisBean.getStringValue(key4UserToken);
		if(StringUtils.isEmpty(stringValue)){
			requestContext.abortWith(failLoginResponse);
			return;
		}

//		redisBean.expire(key4UserToken, tokenLostTime, TimeUnit.MINUTES);
		requestContext.getHeaders().remove("curUserId");
		requestContext.getHeaders().add("curUserId",stringValue);


	}




}
