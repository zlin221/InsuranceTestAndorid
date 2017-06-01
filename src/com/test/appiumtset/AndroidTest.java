package com.test.appiumtset;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import bsh.Capabilities;


public class AndroidTest {
private AppiumDriver driver;
	
	@Before
	public void setUp()throws Exception{
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
	
	@Test
	public void longin()
	{
		//启动app之后等待一段时间
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//获取个人主页
		WebElement tab_mine = driver.findElement(By.id("com.vanchu.apps.insurance:id/main_layout_tab_mine"));
		tab_mine.click();
		
		//点击登录，进入登录页面
		WebElement loginbtn = driver.findElement(By.id("com.vanchu.apps.insurance:id/mine_txt_user_name"));
		loginbtn.click();
	
		//点击密码登录，进入密码登录页面
		WebElement pwdlogin = driver.findElement(By.id("com.vanchu.apps.insurance:id/login_txt_pass_login"));
		pwdlogin.click();
		
		//获取手机号码输入框:com.vanchu.apps.insurance:id/user_info_input_edit_phone_number
		//获取密码输入框:com.vanchu.apps.insurance:id/user_info_input_edit_password
		//登录按钮：com.vanchu.apps.insurance:id/pass_login_btn_login
		
	}
	
	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
}
