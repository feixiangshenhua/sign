package h2db.function.ext;

import java.util.UUID;

/**
 * @Description:���h2���ݿ⺯������չ
 * @Author:	xiaoyun
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015-7-19
 */
public class H2DBFunctionExt {
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
