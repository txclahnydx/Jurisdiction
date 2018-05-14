package com.ruyidd.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface SystemLog {
	
	String operationType() default "";
	String module()  default "";  
    String methods()  default ""; 

}
