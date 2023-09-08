package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product_dto {
	String pidx,pcode,pname,pmoney,pimg,psale,puse;
	
	public ArrayList<String> db_product(){
		ArrayList<String> products = new ArrayList<String>();
		products.add(getPidx());
		products.add(getPcode());
		products.add(getPname());
		products.add(getPmoney());
		products.add(getPsale());
		return products;
	}
}
