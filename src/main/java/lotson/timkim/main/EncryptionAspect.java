package lotson.timkim.main;

import lotson.timkim.dto.HomeDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncryptionAspect {
    private static final Logger log = LoggerFactory.getLogger(EncryptionAspect.class);

    @Before("@annotation(Main.EncryptParams)")
    public void encryptParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HomeDTO homeDTO) {
                encryptFields(homeDTO);
            }
        }
    }

    private void encryptFields(HomeDTO homeDTO) {
        String name = homeDTO.getName();
        String age = homeDTO.getAge();
        log.info("ENCRYPTED FIELDS: NAME = {}, AGE = {}", name, age);
        homeDTO.setName("encrypted-" + name);
        homeDTO.setAge("encrypted-" + age);
    }

}
