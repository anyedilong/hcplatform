package com.java.moudle.bphs;

import com.alibaba.fastjson.serializer.NameFilter;

public class NameFilters  implements NameFilter{

	@Override
	public  String process(Object object, String name, Object value) {
		if(name.indexOf("_")>-1) {
			StringBuilder sBuilder=new StringBuilder(name);
			sBuilder.replace(name.indexOf("_")-1,name.indexOf("_")+1,name.substring(name.indexOf("_"),name.indexOf("_"+1)).toUpperCase());
			return sBuilder.toString();
		}
		return null;
	}

}
