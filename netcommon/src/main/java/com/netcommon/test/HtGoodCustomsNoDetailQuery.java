/*
*
* Powered by com.vip.osp.osp-idlc-2.5.11.
*
*/

package com.netcommon.test;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;


public  class HtGoodCustomsNoDetailQuery  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String customsCode;
	
	
	private String carrierCode;
	
	private String note;
	
	
	private String warehouse;
	
	private List<String> sizeSn;
	
	public String getCustomsCode(){
		return this.customsCode;
	}
	
	public void setCustomsCode(String value){
		this.customsCode = value;
	}
	public String getCarrierCode(){
		return this.carrierCode;
	}
	
	public void setCarrierCode(String value){
		this.carrierCode = value;
	}
	public String getNote(){
		return this.note;
	}
	
	public void setNote(String value){
		this.note = value;
	}
	public String getWarehouse(){
		return this.warehouse;
	}
	
	public void setWarehouse(String value){
		this.warehouse = value;
	}
	public List<String> getSizeSn(){
		return this.sizeSn;
	}
	
	public void setSizeSn(List<String> value){
		this.sizeSn = value;
	}
	
	
	public String toString(){
		
		return "customsCode:"+this.customsCode+","+"carrierCode:"+this.carrierCode+","+"note:"+this.note+","+"warehouse:"+this.warehouse+","+"sizeSn:"+this.sizeSn;
	}
	public static void main(String[] args) {
		HtGoodCustomsNoDetailQuery detailQuery = new HtGoodCustomsNoDetailQuery();
		detailQuery.setWarehouse("VIP_GZNS");
		List<String> data = new ArrayList<>();
		data.add("wagaga2");
		detailQuery.setSizeSn(data);
		System.out.println(JSON.toJSONString(detailQuery));
	}
	
}