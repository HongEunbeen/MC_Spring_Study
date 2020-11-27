package com.multicampus.biz.util;

import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactoryBean {
	private static SqlMapClient sqlMapClient = null;
	
	static { //무조건 실행 
		try {
			if (sqlMapClient == null) { //맨처음 한 번만 이 코드 실행
				Reader reader = Resources.getResourceAsReader("sql-map-config(ibatis).xml");
				sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlMapClient getSqlMapClientInstance() {
		return sqlMapClient;
	}
}
