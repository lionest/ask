import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


public class Test {
	public static void main(String[] args) {
		//MD5加密
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		md5PasswordEncoder.setEncodeHashAsBase64(true);
		String md5Password = md5PasswordEncoder.encodePassword("cptbtptp", "askSys2013");
		System.out.println(md5Password);
	}
}
