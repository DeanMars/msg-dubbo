package com.msgtouch.server;

import com.msgtouch.framework.Bootstrap;
import com.msgtouch.framework.router.RouterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

/**
 * Created by Dean on 2016/10/9.
 */
@SpringBootApplication
@ImportResource({"classpath:dubboContext.xml"})
public class Launch {

    private  static Logger logger= LoggerFactory.getLogger(Launch.class);

    public static void main(String []args){

        ApplicationContext applicationContext=SpringApplication.run(Launch.class,args);
        Environment environment=applicationContext.getEnvironment();
        String profileName=environment.getProperty("my.profile.name");

        logger.info("MsgTouch Server Launch ApplicationContext Environment profile={}",profileName);

        RouterManager.setPushRouter();

        logger.info("init msg dubbo Router success");

        Bootstrap.getInstances().startServerSocket(applicationContext);

        logger.info("Launch success !");

    }

}
