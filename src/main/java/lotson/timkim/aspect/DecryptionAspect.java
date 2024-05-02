package lotson.timkim.aspect;

import lotson.timkim.dto.DecryptDTO;
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
public class DecryptionAspect {
    private static final Logger log = LoggerFactory.getLogger(DecryptionAspect.class);

    @Before("@annotation(lotson.timkim.aspect.Api.DecryptParams)")
    public void decryptParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof DecryptDTO decryptDTO) {
                decryptFields(decryptDTO);
            }
        }
    }

    private void decryptFields(DecryptDTO decryptDTO) {
        if (StringUtils.isBlank(decryptDTO.getValue())) {
            throw new IllegalArgumentException("Value is null or empty");
        }
        if (StringUtils.isBlank(decryptDTO.getAlgorithm())) {
            throw new IllegalArgumentException("Algorithm is null or empty");
        }
        if (StringUtils.isBlank(decryptDTO.getMode())) {
            throw new IllegalArgumentException("Mode is null or empty");
        }
        if (StringUtils.isBlank(decryptDTO.getPadding())) {
            throw new IllegalArgumentException("Padding is null or empty");
        }
        if (StringUtils.isBlank(decryptDTO.getSecretKey())) {
            throw new IllegalArgumentException("SecretKey is null or empty");
        }
        byte[] plainByteArray = Base64.getDecoder().decode(decryptDTO.getValue());
        String decryptedStr = null;

        try {

            SecretKeySpec keySpec = new SecretKeySpec(decryptDTO.getSecretKey().getBytes(StandardCharsets.UTF_8), decryptDTO.getAlgorithm());
            IvParameterSpec ivParamSpec = new IvParameterSpec(Arrays.copyOf(decryptDTO.getSecretKey().getBytes(StandardCharsets.UTF_8), 16));
            Cipher cipher = Cipher.getInstance(decryptDTO.getEncodingAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            byte[] decryptedData = cipher.doFinal(plainByteArray);
            decryptedStr = new String(decryptedData, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        decryptDTO.setValue(decryptedStr);
    }

}
