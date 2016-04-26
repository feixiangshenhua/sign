package h2db.function.ext;

import java.util.UUID;

/**
 * @Description:针对h2数据库函数的扩展
 * @Author:	xiaoyun
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015-7-19
 */
public class H2DBFunctionExt {
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
