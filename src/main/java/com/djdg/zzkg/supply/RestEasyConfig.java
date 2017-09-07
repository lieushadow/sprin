package com.djdg.zzkg.supply;

import io.swagger.jaxrs.config.BeanConfig;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * SampleUser:刘敏华 shadow.liu@hey900.com
 * DateDeserializer: 2017-08-07
 * Time: 16:06
 */

@ApplicationPath("/ja/supply")
@Component
public class RestEasyConfig extends Application {

    private Logger logger = Logger.getLogger(RestEasyConfig.class);

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }




    @Bean
    public BeanConfig beanConfig(){

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"https"});
        beanConfig.setHost("dev-oa.thy360.com");
        beanConfig.setBasePath("/ja/oa");
        beanConfig.setResourcePackage("com.hey900.oa");
        beanConfig.setScan(true);
        return beanConfig;
    }
}
