package mx.lania.carrito.servicios;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectoLogging {
    
    @Pointcut("execution(public * mx.lania.carrito.servicios.*.*(..))")
    private void metodosServicios() {}

    private final Logger LOGGER = LoggerFactory.getLogger(AspectoLogging.class);

    @Around(value = "metodosServicios()")
    public Object logAntesDespues(ProceedingJoinPoint puntoUnion) throws Throwable {

        // Antes
        String nombreServicio = puntoUnion.getSignature().getDeclaringTypeName();
        String nombreMetodo = puntoUnion.getSignature().getName();
        LOGGER.debug("-> {}.{}()", nombreServicio, nombreMetodo);

        // Ejecuta método de Servicio
        Object resultado = puntoUnion.proceed();

        // Después
        LOGGER.debug("<- {}.{}()", nombreServicio, nombreMetodo);
        return resultado;
    }
}
