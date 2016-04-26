package com.sand.util;

import com.sand.crypto.pay.SandPayUtil;

/**
 * ��������(ģ��ǩ��)
 *
 */
public class SandSign {
	
	/**
	 * ǩ��
	 * @param msg
	 * @return
	 */
	public static String sign(String msg) {
		String pkPath = SandSign.class.getResource("").getPath()+"PK_888002148160001_20121017170124.cer";
		//pkPath = pkPath.substring(1).replace("/", File.separator);
		System.out.println("˽Կ·����" + pkPath);
		SandPayUtil sandpay = new SandPayUtil(false);//false - ����ģʽ true - ����ģʽ
		boolean r = sandpay.LoadAcqKeyFile(pkPath,"E:PBK_SAND_20110225151631.cer");
		System.out.println("ǩ���Ƿ������Կ�ɹ���" + r);
		String sign = "";
		if(r){//������Կ�ɹ�����ʹ��,�����޷�ǩ��
			sign = sandpay.signature(msg, SandPayUtil.SD_SIGNTYPE_MD5RSA);
		}
		return sign;
	}
	
	/**
	 * ��ǩ
	 * @param sign
	 * @return
	 */
	public static boolean verify(String msg, String sign) {
		SandPayUtil sandpay = new SandPayUtil(false);
		boolean r = sandpay.LoadAcqKeyFile("E:PK_888002148160001_20121017170124.cer","E:PBK_SAND_20110225151631.cer");//�̻�id,�̻�˽Կ,ɼ�¹�Կ
		boolean res = false;
		System.out.println("��ǩ�Ƿ������Կ�ɹ���" + r);
		if(r){
			res =  sandpay.verify(msg,SandPayUtil.SD_SIGNTYPE_MD5RSA,sign);
		}
		System.out.println("��ǩ���:"+res);
		return res;	
	}

	
	public static void main(String[] args) {
		/*String version="01";
		String charset="UTF-8";
		String trans_type="0003";
		String merchant_id="";//�̻���
		String merchant_name="ɼ�µ���֧��";
		String goods_content="";//�˻���Ʒ��Ϣ
		String order_id="";
		String refund_amount="";
		String refund_currency="156";
		String order_time="";
		String back_url="";//���֪ͨ��ַ
		String merchant_attach="";//�̻�������Ϣ	
		
		StringBuffer buffer = new StringBuffer();
		String sign = "";
		buffer.append("version=").append(version).append("&").append("charset=").append(charset).append("&")
		      .append("trans_type=").append(trans_type).append("&").append("merchant_id=").append(merchant_id).append("&")
		      .append("merchant_name=").append(merchant_name).append("&").append("goods_content=").append(goods_content).append("&")
		      .append("order_id=").append(order_id).append("&").append("refund_amount=").append(refund_amount).append("&")
		      .append("refund_currency=").append(refund_currency).append("&").append("order_time=").append(order_time).append("&")
		      .append("back_url=").append(back_url).append("&").append("merchant_attach=").append(merchant_attach);
		System.out.println(buffer.toString());
		String msg = buffer.toString();*/
		String msg = "version=01&charset=UTF-8&trans_type=0002&merchant_id=999210053110001&pos_order_id=1&reason=&order_amount=000000011111&currency=156&revoked_time=20150812163145&merchant_attach=";
		String sign = sign(msg);
		System.out.println(sign);
		//sign = "65FC9E71EB88308AC3D665D1B919ED21479FC2BF20E9A7EA9C4ED38ADAED471CA119FE41A4C5E1071F4F5331F389F47104C4AA6CB22C1D297838EE6E71F7B9AD9E1B303E357ABF24A1FBFCA1BEE5B52E76FADEDD29DF1F4F52CBEFE62149ADF5B80CCD5587F808A8A439FED89A012D082D9EFB6069C85AA7B876A30E8AA866DD";
		//boolean isOk = verify(msg, sign);
		//System.out.println("ǩ�����:" + sign + "\n" + "�Ƿ���ǩͨ����" + isOk);
	}
}
