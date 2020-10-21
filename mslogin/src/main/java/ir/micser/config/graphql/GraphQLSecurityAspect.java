package ir.micser.config.graphql;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-22<br>
 * Time: 15:17:04<br>
 * Description: کلاس تنظیمات امنیتی دسترسی گراف کیو ال
 */
@Aspect
@Component
@Order(1)
public class GraphQLSecurityAspect {

    /**
     * @AdminSecured annotated methods can be called only by admin authenticated user.
     */
    @Before("pointcutGraphQLMutation()")
    public void checkGraphQLMutation(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        System.out.println("Mutation Before controllerMethods class.method:"+ className+ "."+ methodName);
    }

//    @Around("pointcutGraphQLQuery()")
//    public void checkGraphQLQuery(ProceedingJoinPoint jp) throws Exception {
//        String className = jp.getSignature().getDeclaringType().getName();
//        String methodName = jp.getSignature().getName();
//        MethodSignature signature = (MethodSignature) jp.getSignature();
//        Method method = signature.getMethod();
//        GraphQLQuery myAnnotation = method.getAnnotation(GraphQLQuery.class);
//
//        System.out.println("Query Before controllerMethods class.method:"+ className+ "."+ methodName + "," + myAnnotation.name());
//        //throw new getAOPException("**** Erooor GraphQLQuery **** ");
//        //throw new Exception("**** Erooor GraphQLQuery **** ");
//        throw new GraphQLExceptionWithErrorInformation(ErrorInformation.GRAPH_QL_EXCEPTION.getValue(), ErrorInformation.GRAPH_QL_EXCEPTION.getValue());
//    }


    /**
     * Any method annotated with @AdminSecured
     */
    //@Pointcut("@annotation(com.zerofiltre.samplegraphqlerrorhandling.security.AdminSecured)")
    @Pointcut("@annotation(io.leangen.graphql.annotations.GraphQLMutation)")
    private void pointcutGraphQLMutation() {
        //leave empty
    }
    @Pointcut("@annotation(io.leangen.graphql.annotations.GraphQLQuery)")
    private void pointcutGraphQLQuery() {
        //leave empty
    }
}