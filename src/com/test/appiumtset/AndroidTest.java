package com.test.appiumtset;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;

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
		capabilities.setCapability("deviceName","oneplus-a0001-6428f2b8");//xiaomi-mi-2-208cbeb0;lenovo_K50-t5_RSY5LZ89F67SJZYT;oneplus-a0001-6428f2b8(android4.3)
		capabilities.setCapability("platformVersion","4.3");//leveno5.1;xiaomi4.1.1;oneplus4.3
		capabilities.setCapability("avd", "appium");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.vanchu.apps.insurance");
		capabilities.setCapability("appActivity", ".module.splash.SplashActivity");
		
		//重点所在（解决session无法创建问题）
      
		capabilities.setCapability("appWaitActivity","com.vanchu.apps.insurance.module.splash.SplashActivity"); //你想要等待启动的Activity名称
		capabilities.setCapability("sessionOverride", true); // 每次启动时覆盖session，否则第二次后运行会报错不能新建session
	
		//初始化
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
			
	}
	
	@Test
	public void longin()
	{
		WebElement loginbtn = driver.findElement(By.id("com.vanchu.apps.insurance:id/mine_txt_user_name"));
		loginbtn.click();
		
	}
	
	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
}
