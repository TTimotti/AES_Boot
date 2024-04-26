package lotson.timkim.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping
    public String home(Model model) {
		model.addAttribute("name", "타임리프");
		
        return "index";
    }
}
