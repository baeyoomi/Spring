import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {

	public static void main(String[] args) {
		String ip = "192.168.110.217";
		int port = 8009;
		Socket sc = null;
		Scanner sn = null;
		
		try {
			sc = new Socket(ip,port);		//서버에 접속함		
			sn = new Scanner(System.in);
			System.out.println("아이디 : ");
			String mid = sn.next();
			
			InputStream is = sc.getInputStream();
			OutputStream os = sc.getOutputStream();
			System.out.println(mid + " 님 서버 채팅서버 접속완료!!");
			os.write(mid.getBytes());
			
			while(true) {
				sc = new Socket(ip,port);  //서버 접속 유지시킴
				InputStream is2 = sc.getInputStream();
				OutputStream os2 = sc.getOutputStream();
				
				System.out.print("입력하실 내용을 적어주세요 : ");
				String msg = mid + "님 : " + sn.next();
				os2.write(msg.getBytes());
				os2.flush();
				
				byte[] data = new byte[1024];
				int n = is2.read(data);
				String smsg = new String(data,0,n);
				System.out.println(smsg);
			}
		}
		catch(Exception e) {
			System.out.println("Not Connection...");
		}
	}

}
