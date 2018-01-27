package hello;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/getMe")
	public String getMyName(@RequestParam String name) {
		return name;
	}
	
	@RequestMapping("/Users")
	public List<String> getAllUsers() {
		return Arrays.asList(
				"Ethan",
				"Max",
				"Tanner",
				"Danny"
				
				);
	}

}
