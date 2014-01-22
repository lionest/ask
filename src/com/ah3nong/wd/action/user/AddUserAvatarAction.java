package com.ah3nong.wd.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import com.ah3nong.wd.action.GenericActionSupport;
import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.common.Config;
import com.ah3nong.wd.service.UserService;

public class AddUserAvatarAction extends GenericActionSupport<User> {
	private static final long serialVersionUID = 9061783194234601363L;
	private File useravatar;
	private String useravatarFileName;
	private String avamsg;
	
	private UserService userService;
	
	public String execute() {
		try {
			User user = (User) request.getSession().getAttribute("user");

			String type = useravatarFileName.substring(useravatarFileName.lastIndexOf(".")).toLowerCase();

			long fileSize = useravatar.length();
			if (fileSize > 1024 * 1024) {
				request.setAttribute("msg", "IMGFAIL");
				return INPUT;
			}

			String DIR = Config.getString("avatar_img_dir");

			InputStream is;

			is = new FileInputStream(useravatar);

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String time = sdFormat.format(System.currentTimeMillis());
			String URL = DIR + user.getId() + "_" + time + type;

			OutputStream os = new FileOutputStream(URL);
			
			byte buffer[] = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
			os.close();
			is.close();

			User tmp = new User();
			tmp.setId(user.getId());
			tmp.setAvatar("user/avatar/" + user.getId() + "_" + time + type);
			userService.updateUserByPrimarykey(tmp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(avamsg==null||!"toIndex".equals(avamsg)){
			return SUCCESS;
		}else{
			return avamsg;
		}
	}

	public void setUseravatar(File useravatar) {
		this.useravatar = useravatar;
	}

	public void setUseravatarFileName(String useravatarFileName) {
		this.useravatarFileName = useravatarFileName;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAvamsg(String avamsg) {
		this.avamsg = avamsg;
	}
	
}
