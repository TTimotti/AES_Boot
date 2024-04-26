package lotson.timkim.main;

import lotson.timkim.dto.HomeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    @Main.EncryptParams
    public String getHome(Model model, HomeDTO homeDTO) {
        String name = homeDTO.getName();
        String age = homeDTO.getAge();
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        log.info("NAME : {}, AGE ; {}", name, age);

        return "index";
    }

    @PostMapping
    @Main.EncryptParams
    public String postHome(Model model, HomeDTO homeDTO) {
        String name = homeDTO.getName();
        String age = homeDTO.getAge();
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        log.info("NAME : {}, AGE ; {}", name, age);

        return "index";
    }
}
