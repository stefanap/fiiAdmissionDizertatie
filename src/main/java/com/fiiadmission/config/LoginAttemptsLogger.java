package com.fiiadmission.config;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginAttemptsLogger extends WebMvcConfigurerAdapter{
 
//    @EventListener
//    public void auditEventHappened(
//      AuditApplicationEvent auditApplicationEvent) {
//         
//        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
//        System.out.println("Principal " + auditEvent.getPrincipal() 
//          + " - " + auditEvent.getType());
// 
//        OAuth2AuthenticationDetails details = 
//          (OAuth2AuthenticationDetails) auditEvent.getData().get("details");
//        System.out.println("Remote IP address: "
//          + details.getRemoteAddress());
//        System.out.println("  Session Id: " + details.getSessionId());
//        System.out.println("  Request URL: "
//                + auditEvent.getData().get("requestUrl"));
//    }
	private static final Logger LOG = LoggerFactory.getLogger(LoginAttemptsLogger.class);
	
	@EventListener
    public void onAuditEvent(AuditApplicationEvent event) {
        AuditEvent actualAuditEvent = event.getAuditEvent();

        LOG.info("On audit application event: timestamp: {}, principal: {}, type: {}, data: {}",
            actualAuditEvent.getTimestamp(),
            actualAuditEvent.getPrincipal(),
            actualAuditEvent.getType(),
            actualAuditEvent.getData()
        );
        LOG.info("details",actualAuditEvent.getData().values());

    }
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(new HandlerInterceptorAdapter() {
	         Logger logger = LoggerFactory.getLogger(LoginAttemptsLogger.class);

	         @Override
	         public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	            if (handler instanceof HandlerMethod) {
	               HandlerMethod handlerMethod = (HandlerMethod) handler;
	               Method method = handlerMethod.getMethod();
	               logger.info("{} - {} - method '{}' on controller '{}'",
	                     request.getMethod(), request.getRequestURI(), method.getName(),
	                     handlerMethod.getBean().getClass()
	               );
	            }
	            return true;
	         }
	      });
	   }
}