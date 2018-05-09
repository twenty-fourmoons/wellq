package util;


public class ValidationUtil {
	//验证身份证号 18位
	public static boolean validateIDCard(String IDCard) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!IDCard.matches(reg)) {
            System.out.println("Format Error!");
            return false;
        }
        return true;
    }
	//验证姓名 汉字
	public static boolean validateName(String name) {
        String reg = "^[\\u4e00-\\u9fa5]{0,}$";
        if (!name.matches(reg)) {
            System.out.println("Format Error!");
            return false;
        }
        return true;
    }
	//验证手机号码
	public static boolean validateTel(String tel) {
        String reg = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        if (!tel.matches(reg)) {
            System.out.println("Format Error!");
            return false;
        }
        return true;
    }
	
	
	
}
