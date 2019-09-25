package io.nakong.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.alibaba.fastjson.JSON;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Page<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 6448507082780763506L;

	// ----------------------------------------------------------

	private int total; // 总条数

	private int start; // 开始

	private int end; // 结束

	private Map<String, Serializable> map; // 查询条件

	private List<T> data;  // 一般是结果数据（但可能被用来传列表参数，建议不要这样做）

	private boolean isPaging = true; //是否需要分页
	
	public Page(){
		
	}
	public Page(int start, int  end){
		this.end = end ;
		this.start = start;
	}
	

	// ----------------------------------------------------------

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Map<String, Serializable> getMap() {
		return map;
	}

	public void setMap(Map<String, Serializable> map) {
		this.map = map;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public boolean isPaging() {
		return isPaging;
	}

	public void setPaging(boolean paging) {
		isPaging = paging;
	}

	// ----------------------------------------------------------

	public static void main(String[] args) {

		Page<String> pageSo = new Page();
		pageSo.setData(Arrays.asList(new String[] {"1", "2", "3"}));

		HashMap<String, Serializable> map = new HashMap<>();
		map.put("key1", new Integer(1));
		map.put("key2", "2");
		map.put("key3", new ArrayList<String>());

		pageSo.setMap(map);

		System.out.println(JSON.toJSONString(pageSo, true));
	}
}
