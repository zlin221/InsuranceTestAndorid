package com.test.appiumtset;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import bsh.Capabilities;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AndroidTest {
private static AppiumDriver driver;
	
	@BeforeClass
	public static void setUp()throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot,"/apps");
		File app = new File(appDir,"com.vanchu.apps.insurance.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","lenovo_K50-t5_RSY5LZ89F67SJZYT");//xiaomi-mi-2-208cbeb0;lenovo_K50-t5_RSY5LZ89F67SJZYT;oneplus-a0001-6428f2b8(android4.3)
		capabilities.setCapability("platformVersion","5.1");//leveno5.1;xiaomi4.1.1;oneplus4.3
		//capabilities.setCapability("avd", "appium");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.vanchu.apps.insurance");
		capabilities.setCapability("appActivity", ".module.splash.SplashActivity");
		
		//重点所在（解决session无法创建问题）
      
		//capabilities.setCapability("appWaitActivity","com.vanchu.apps.insurance.module.splash.SplashActivity"); //你想要等待启动的Activity名称
		//capabilities.setCapability("sessionOverride", true); // 每次启动时覆盖session，否则第二次后运行会报错不能新建session
	
		//初始化
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
			
	}
	
	/***
	 * 测试密码登陆
	 * @auth jolie
	 * 
	 ***/
	@Test
	public void test001PwdLongin()
	{
		//启动app之后等待一段时间
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//获取个人主页
		WebElement tabMine = driver.findElement(By.id("com.vanchu.apps.insurance:id/main_layout_tab_mine"));
		tabMine.click();
		
		//点击登录，进入登录页面
		WebElement loginBtn = driver.findElement(By.id("com.vanchu.apps.insurance:id/mine_txt_user_name"));
		loginBtn.click();
	
		//点击密码登录，进入密码登录页面
		WebElement pwdLogin = driver.findElement(By.id("com.vanchu.apps.insurance:id/login_txt_pass_login"));
		pwdLogin.click();
			
		
		//获取页面的所有输入框
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		//输入手机号码
		textFieldsList.get(0).sendKeys("15019226379");
		//输入密码
		textFieldsList.get(1).sendKeys("123456");
		
		//登录按钮：com.vanchu.apps.insurance:id/pass_login_btn_login
		WebElement pwdLoginBtn = driver.findElementById("com.vanchu.apps.insurance:id/pass_login_btn_login");
		pwdLoginBtn.click();
		
	}
	
	/**
	 * 修改头像 
	 * 未完成
	 */
	@Test
	public void test002ChangeMineImage() {
		//获取个人页面个人头像框
		WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		mineImage.click();
		WebElement imageUser = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_img_user_icon");;
		imageUser.click();
		//选中图库的第一张图片进入预览
		List<AndroidElement> imageList = driver.findElementsByClassName("android.widget.ImageView");
		imageList.get(1).click();
		
		//选中当前照片
		WebElement selectImage = driver.findElementById("com.vanchu.apps.insurance:id/photowall_browse_checkbox");
		selectImage.click();
		WebElement finishBtn = driver.findElementById("com.vanchu.apps.insurance:id/photowall_browse_txt_finish");
		finishBtn.click();
		WebElement doneBtn = driver.findElementById("com.vanchu.apps.insurance:id/btn_done");
		doneBtn.click();
				
	}
	
	
	/**
	 * 修改用户姓名
	 * 
	 */
	@Test
	public void test003ChangeMineName(){
		//获取个人页面个人头像框,点击个人头像进入到信息编辑也页
		//WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		//mineImage.click();
		
		//获取姓名框，进入到姓名修改页
		WebElement mineName = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_layout_name");
		mineName.click();
		
			
		//获取页面的所有输入框
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		
		//获取输入框内容
		String text = textFieldsList.get(0).getText();
		textFieldsList.get(0).click();//点击输入框
		//清除文本框的内容	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
	    }
		
		//输入修改信息
		textFieldsList.get(0).sendKeys("jolie");
		
		//点击保存
		WebElement saveKeyElement = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_btn_save");
		saveKeyElement.click();
		
		//保存之后返回到上一页，重新进入到修改页面
		mineName.click();
		
		//不修改任何东西，直接点击返回
		WebElement returnKey = driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back");
		returnKey.click();
		
	}
	
	/**
	 * 修改身份证号码
	 */
	@Test
	public void  test004ChangeIdNo(){
		WebElement mineIdNo = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_layout_idcard");
		mineIdNo.click();
		
		//获取页面的所有输入框
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		
		//获取输入框内容
		String text = textFieldsList.get(0).getText();
		textFieldsList.get(0).click();//点击输入框
		//清除文本框的内容	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
	    }
		
		//输入修改信息
		textFieldsList.get(0).sendKeys("1111111");
		WebElement finishBtn = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_btn_save");
		finishBtn.click();			
		
		textFieldsList.get(0).click();
		//清除文本框的内容	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
	    }
		
		textFieldsList.get(0).sendKeys("110101199809018853");
		finishBtn.click();
		
		//进入到修改身份证信息页，什么都不修改直接点击返回
		mineIdNo.click();
		driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back").click();
		
			
	
	}
	
	/**
	 * 
	 * 地址管理
	 */
	@Test
	public void test005Address(){
		WebElement address = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_txt_address");
		address.click();
		
		WebElement addAddress = driver.findElementById("com.vanchu.apps.insurance:id/mine_address_btn_add_address");
		addAddress.click();
		
		
//		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
//		textFieldsList.get(0).sendKeys("王小明");
//		textFieldsList.get(1).sendKeys("15019226377");
//		textFieldsList.get(2).sendKeys("测试随便填写的地址");
//		
//			此处有BUG：估计是因为点击太快，需要加一个等待时间
//		WebElement region = driver.findElementById("com.vanchu.apps.insurance:id/mine_address_txt_city");
//		region.click();
//		
//		
//		List<AndroidElement> cityList = driver.findElementsByClassName("android.widget.TextView");
//		cityList.get(1).click();
//		
//		List<AndroidElement> provinceList = driver.findElementsByClassName("android.widget.TextView");
//		provinceList.get(2).click();
//		
//		WebElement saveBtn = driver.findElementById("com.vanchu.apps.insurance:id/mine_address_edit_save");
//		saveBtn.click();
//		
//		//点击添加新地址什么都不填写直接点击返回
//		addAddress.click();
//		WebElement addAddressBtn = driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back");
//		addAddressBtn.click();
//		
//		
//		
//		//返回到我的地址页，点击第二个地址进入编辑页
//		List<AndroidElement> editAddressList = driver.findElementsById("android.widget.RelativeLayout");
//		//如果我的地址不为空
//		if(editAddressList!=null)
//		{
//			editAddressList.get(1).click();
//			textFieldsList.get(0).click();
//			//清除联系人姓名的内容	
//			String text = textFieldsList.get(0).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
//		    }
//			textFieldsList.get(0).sendKeys("张大炮");
//			
//			
//			textFieldsList.get(1).click();
//			//清空手机号码
//			text = textFieldsList.get(1).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
//		    }
//			textFieldsList.get(1).sendKeys("15019226374");
//			
//			textFieldsList.get(2).click();
//			//清空详细地址
//			text = textFieldsList.get(2).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123：光标移动到输入框最右边
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67：delete键
//		    }
//			textFieldsList.get(2).sendKeys("测试编辑地址");
//			
//			//修改区域
//			cityList.get(1).click();;
//			provinceList.get(1).click();
//				
//		}
		//修改默认地址
		
		//删除
		
		//我的地址点击返回
		
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		driver.quit();
	}
}
