package com.multicampus.biz.collection;

import java.util.Collection;
import java.util.Properties;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionClient {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean = (CollectionBean) container.getBean("collection");
		
		Properties collection = bean.getAddressList();//key value 쌍으로 정보 존재
		Collection<Object> addressList = collection.values();
		System.out.println("[ 주소 목록 ]");
		for(Object address : addressList) {
			System.out.println("---> " + address.toString());
		}
	}
}
