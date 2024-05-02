package lotson.timkim.api;

import lotson.timkim.aspect.Api;
import lotson.timkim.dto.DecryptDTO;
import lotson.timkim.dto.EncryptDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/encrypt")
    public String getEncrypt() {
        return "encrypt";
    }

    @PostMapping("/encrypt")
    @ResponseBody
    @Api.EncryptParams
    public ResponseEntity<EncryptDTO> postEncrypt(EncryptDTO encryptDTO) {
        log.info("DTO : {}", encryptDTO);
        return new ResponseEntity<>(encryptDTO, HttpStatus.OK);
    }

    @GetMapping("/decrypt")
    public String getDecrypt() {
        return "decrypt";
    }


    @PostMapping("/decrypt")
    @ResponseBody
    @Api.DecryptParams
    public ResponseEntity<DecryptDTO> postDecrypt(DecryptDTO decryptDTO) {
        log.info("DTO : {}", decryptDTO);
        return new ResponseEntity<>(decryptDTO, HttpStatus.OK);
    }



}