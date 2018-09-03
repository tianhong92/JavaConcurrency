package com.tianhong.javaconcurrency.annotationExample;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordUtils {
    @UseCase(id = "47", description = "Password must contain at least one numeric")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = "48")
    public String encryptPassword(String password){
        return new StringBuffer(password).reverse().toString();
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        traceUseCases(useCases, PasswordUtils.class);
    }

    // 使用注解最主要的部分在于对注解的处理，那么就会涉及到注解处理器。
    // 从原理上讲，注解处理器就是通过反射机制获取被检查方法上的注解信息，然后根据注解元素的值进行特定的处理。

    public static void traceUseCases(List<Integer> useCases, Class<?> cl){
        for(Method m : cl.getDeclaredMethods()){
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.println("Found Use Case:"+uc.id()+" "+uc.description());
            }
        }
        for(int i : useCases){
            System.out.println("Warning: missing use case-"+i);
        }
    }
}
