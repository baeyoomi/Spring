package sp1;
//module getter,setter
public class userdata {
	String mid = null;
	String mname = null;
	
	//setter : this를 이용하여 값을 이관   (즉시 실행 문법을 사용함)
	public userdata(String data1, String data2) {
		this.mid = data1;
		this.mname = data2;
	}
	
	//getter : return
	public String getMid() {
		return mid;
	}
	public String getMname() {
		return mname;
	}

	
}
