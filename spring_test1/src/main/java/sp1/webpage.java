package sp1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webpage {
	
	//로그인
	@RequestMapping("/login.do")
	public void loginok(HttpServletRequest req, HttpServletResponse res) {

	}
	
	//로그아웃
	@RequestMapping("/logout.do")
	public void logoutok(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
