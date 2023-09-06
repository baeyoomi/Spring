package sp1;

import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class webpage2 {
	PrintWriter pw = null;
	
	//spring1.html에 넘어온 값을 view를 통해서 핸들링함 2
	@PostMapping("/spring1ok.do")
	public String product(HttpServletRequest req, HttpServletResponse res, Model m) {
		String pdcode = req.getParameter("pdcode").intern();
		String pdname = req.getParameter("pdname").intern();
		try {
			m.addAttribute("code",pdcode);
			m.addAttribute("name",pdname);
		}
		catch(Exception e) {
			System.out.println("오류 발생");
		}
		return "/WEB-INF/jsp/spring1ok";     //보여지는 디자인 3
	}
	
	@PostMapping("/spring2ok.do")
	public String agree(HttpServletRequest req, Model m) {
		String ag = req.getParameter("mail");   //check_box 사용시 intern()은 사용하지 않음 *중요
		String ad = req.getParameter("ad").intern();   //radio 사용시 intern() 사용해야함
		
		//radio 사용
		if(ad == "N") {
			System.out.println("동의안함");
		}
		else {
			System.out.println("동의함");
		}
		
		//check_box 사용
		if(ag==null) {
			ag = "N";
		}
		System.out.println(ag);
		return null;
	}
	
	//getter와 setter를 이용해서 값을 로드
	@PostMapping("/spring3ok.do")
	public String user(HttpServletRequest req, Model m) {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		
		userdata ud = new userdata(mid, mname);
		System.out.println(ud.getMid());
		System.out.println(ud.getMname());
		return null;
	}
	
	
	//실제 메일 서비스 + naver.com 메일서버
	@PostMapping("/spring4ok.do")
	public String mails(HttpServletRequest req, Model m) {
		String pname = req.getParameter("pname");  //보낸이
		String pemail = req.getParameter("pemail"); //회신 받을 메일
		String ptitle = req.getParameter("ptitle"); //제목
		String pcontent = req.getParameter("pcontent"); //내용
		
		/* 실제 메일 API 서버 정보를 입력 */
		String host = "smtp.naver.com";
		String user = "cxz3930@naver.com";
		String password = "";
		String to_mail = "cxz3930@naver.com";
		
		/* API 포트번호 및 TLS정보를 셋팅 */
		Properties props = new Properties(); //얘도 배열
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", 587);
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		//SMTP 서버에 로그인을 시키키 위한 작업(암호화)
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(user, password);
			}
		});
		try {
			//MimeMessage : okcall 발생함 (아이디,패스워드,포트 모두 맞을 경우)
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(pemail,pname));  //보낸이
			//받는메일주소
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_mail));
			msg.setSubject(ptitle);  //제목
			msg.setText(pcontent);   //내용
			Transport.send(msg);     //발송
			System.out.println("메일 발송이 정상적으로 되었습니다.");
		}
		catch(Exception e) {
			System.out.println("메일 서버 통신 오류!");
		}
		return null;
	}
}