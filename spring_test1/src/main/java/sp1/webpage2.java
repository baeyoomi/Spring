package sp1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webpage2 {
	PrintWriter pw = null;
	
	//수정완료
	@PostMapping("/product_modifyok.do")
	public String ok_modify(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		
		String pidx = req.getParameter("pidx");
		String pcode = req.getParameter("pcode");
		String pname = req.getParameter("pname");
		String pmoney = req.getParameter("pmoney");
		String psale = req.getParameter("psale");
		String puse = req.getParameter("puse");
		
		product_ok ok = new product_ok();
		String msg = "";
		
		int result = ok.modify_sql(pidx, pcode, pname, pmoney, psale, puse);
		if(result == 1) {
			msg="<script>alert('정상적으로 수정 완료 되었습니다.');"
					+ "location.href='./product_list.do';</script>";
		}
		else {
			msg="<script>alert('수정 내용이 올바르지 않습니다.');"
					+ "history.go(-1);</script>";
		}
		try {
			this.pw = res.getWriter();
			this.pw.write(msg);
		}
		catch(Exception e) {
			System.out.println("올바른 값이 전달되지 않음");
		}
		
		return null;
	}
	
	
	//하나의 상품 출력 파트 (JSTL)
	@GetMapping("/product_modify.do")
	public String view_product(HttpServletRequest req, Model m) {
		String idx = req.getParameter("idx");
		try {
			product_modify pm = new product_modify();
			ArrayList<String> data = pm.view_ok(idx);
			m.addAttribute("data",data);
		}
		catch(Exception e) {
			System.out.println("수정 접근 오류!");
		}
		
		return "/WEB-INF/jsp/product_modify";
	}
	
	
	//상품 삭제 파트
	@GetMapping("/product_delete.do")
	public void del_product(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		
		try {
			this.pw = res.getWriter();
			String no = req.getParameter("idx");
			product_delete pd = new product_delete();
			int result = pd.delete_ok(no);
			if(result == 1) { // 정상적인 sql 작동
				this.pw.write("<script>alert('정상적으로 삭제 되었습니다.');"
						+ "location.href='./product_list.do';</script>");
			}
			else {   //0 SQL문법이 올바르게 작동되지 않을 경우
				this.pw.write("<script>alert('올바른 데이터 값이 아닙니다.');"
						+ "location.href='./product_list.do';</script>");
			}
		}
		catch(Exception e) {
			this.pw.write("<script>alert('잘못된 접근 방식 입니다.');"
					+ "history.go(-1);</script>");
		}
	}
	
	
	
	//JSTL로 view page 출력 파트
	@RequestMapping("/product_list.do")
	public String pd_list(HttpServletRequest req, Model m) {
		List<ArrayList<String>> product_data = null;
		
		try {
			product_list pl = new product_list();
			product_data = pl.pro_listdata();		
		}
		catch(Exception e) {
			System.out.println("Controller 오류!");
		}
		m.addAttribute("product",product_data);
		m.addAttribute("update","수정/삭제");
		
		return "/WEB-INF/jsp/product_list"; 	
	}
	
	
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
	
	//Controller에서 배열을 JSP (View)로 출력하는 형태
	@GetMapping("/spring5ok.do")
	public String datalist(HttpServletRequest req, Model m) {
				
		String data[] = {"이순신","홍길동","강감찬","이산","한석봉"};
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(data));
		
		//JSP 방식 (실무에선 거의 안쓰는 방식)
		//req.setAttribute("person_list",al);
		//return "/WEB-INF/jsp/spring5ok";  //일반 JSP view
		
		//표현식
		m.addAttribute("person_list",al); //표현식 방식을 사용하기 위해 사용함
		m.addAttribute("person_delete","10");
		return "/WEB-INF/jsp/spring5_2ok";  //표현식 JSP view
		
		//표현식 값을 javascript 전달(Front-end) Node형태로 출력
	}
	
	
	// 사용자 리스트 출력 Mysql 이용 (DAO,DTO 같이 구성)
	// 컨트롤러 부분
	@RequestMapping("/spring6ok.do")
	public String userlist(HttpServletRequest req, Model m) {
		List<ArrayList<String>> member_data = null;
		String search = req.getParameter("search");
		String part = req.getParameter("part");

		try {
			if(search == "null" || search == null || search =="") { //검색이 없을 경우
				user_list ul = new user_list();
				member_data = ul.listdata();
			}
			else {  //검색 단어가 있을 경우
				user_list ul = new user_list();
				member_data = ul.listdata(search,part);
			}
			
			//JSP
			req.setAttribute("total", new user_list().total_member());
			req.setAttribute("member_data", member_data);
			req.setAttribute("part", part);
		}
		catch(Exception e) {
			System.out.println("Controller 오류 발생");
		}
		return "/WEB-INF/jsp/member_list";
	}
}









