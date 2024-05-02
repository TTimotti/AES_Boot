package lotson.timkim.aspect;

import lotson.timkim.dto.EncryptDTO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Aspect
@Component
public class EncryptionAspect {
    private static final Logger log = LoggerFactory.getLogger(EncryptionAspect.class);

    @Before("@annotation(lotson.timkim.aspect.Api.EncryptParams)")
    public void encryptParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof EncryptDTO encryptDTO) {
                encryptFields(encryptDTO);
            }
        }
    }

    private void encryptFields(EncryptDTO encryptDTO) {
        if (StringUtils.isBlank(encryptDTO.getValue())) {
            throw new IllegalArgumentException("Value is null or empty");
        }
        if (StringUtils.isBlank(encryptDTO.getAlgorithm())) {
            throw new IllegalArgumentException("Algorithm is null or empty");
        }
        if (StringUtils.isBlank(encryptDTO.getMode())) {
            throw new IllegalArgumentException("Mode is null or empty");
        }
        if (StringUtils.isBlank(encryptDTO.getPadding())) {
            throw new IllegalArgumentException("Padding is null or empty");
        }
        if (StringUtils.isBlank(encryptDTO.getSecretKey())) {
            throw new IllegalArgumentException("SecretKey is null or empty");
        }
        byte[] encryptedData = null;

        try {
            SecretKeySpec keySpec = new SecretKeySpec(encryptDTO.getSecretKey().getBytes(StandardCharsets.UTF_8), encryptDTO.getAlgorithm());
            IvParameterSpec ivParamSpec = new IvParameterSpec(Arrays.copyOf(encryptDTO.getSecretKey().getBytes(StandardCharsets.UTF_8), 16));
            Cipher cipher = Cipher.getInstance(encryptDTO.getEncodingAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            encryptedData = cipher.doFinal(encryptDTO.getValue().getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        encryptDTO.setValue(Base64.getEncoder().encodeToString(encryptedData));
    }

}
