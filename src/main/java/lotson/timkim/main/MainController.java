package lotson.timkim.main;

import lotson.timkim.dto.HomeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    @Main.EncryptParams
    public String getHome(Model model, HomeDTO homeDTO) {
        model.addAttribute("name", homeDTO.getName());
        model.addAttribute("age", homeDTO.getAge());

        return "index";
    }

    @PostMapping
    @Main.EncryptParams
    public String postHome(Model model, HomeDTO homeDTO) {
        model.addAttribute("name", homeDTO.getName());
        model.addAttribute("age", homeDTO.getAge());

        return "index";
    }

    @GetMapping("/api")
    @ResponseBody
    @Main.EncryptParams
    public HomeDTO ajaxGet(Model model, HomeDTO homeDTO) {
        return homeDTO;
    }


    @PostMapping("/api")
    @ResponseBody
    @Main.EncryptParams
    public HomeDTO ajaxPost(Model model, HomeDTO homeDTO) {
        return homeDTO;
    }

}
