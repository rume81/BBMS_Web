package com.ey.bbms.model.main;

import java.util.ArrayList;
import java.util.List;

public class HCommon extends HObject {
	List<String> datas = new ArrayList<String>();

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
}
