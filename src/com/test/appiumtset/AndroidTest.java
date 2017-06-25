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
	
	public void test002ChangeMineImage() {
		//获取个人页面个人头像框
		WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		mineImage.click();
		
		
	}
	
	@Test
	public void test003ChangeMineName(){
		//获取个人页面个人头像框,点击个人头像进入到信息编辑也页
		WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		mineImage.click();
		
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
	
	
	
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		driver.quit();
	}
}
