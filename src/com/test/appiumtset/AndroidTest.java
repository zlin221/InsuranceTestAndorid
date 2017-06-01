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
		
		//�ص����ڣ����session�޷��������⣩
      
		//capabilities.setCapability("appWaitActivity","com.vanchu.apps.insurance.module.splash.SplashActivity"); //����Ҫ�ȴ�������Activity����
		//capabilities.setCapability("sessionOverride", true); // ÿ������ʱ����session������ڶ��κ����лᱨ�����½�session
	
		//��ʼ��
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
			
	}
	
	@Test
	public void longin()
	{
		//����app֮��ȴ�һ��ʱ��
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//��ȡ������ҳ
		WebElement tab_mine = driver.findElement(By.id("com.vanchu.apps.insurance:id/main_layout_tab_mine"));
		tab_mine.click();
		
		//�����¼�������¼ҳ��
		WebElement loginbtn = driver.findElement(By.id("com.vanchu.apps.insurance:id/mine_txt_user_name"));
		loginbtn.click();
	
		//��������¼�����������¼ҳ��
		WebElement pwdlogin = driver.findElement(By.id("com.vanchu.apps.insurance:id/login_txt_pass_login"));
		pwdlogin.click();
		
		//��ȡ�ֻ����������:com.vanchu.apps.insurance:id/user_info_input_edit_phone_number
		//��ȡ���������:com.vanchu.apps.insurance:id/user_info_input_edit_password
		//��¼��ť��com.vanchu.apps.insurance:id/pass_login_btn_login
		
	}
	
	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
}
