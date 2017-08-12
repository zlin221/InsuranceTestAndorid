package com.test.appiumtset;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class NonAutoInsurance {
private static AppiumDriver driver;
	
	@BeforeClass
	public static void setUp()throws Exception{
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot,"/apps");
		File app = new File(appDir,"com.vanchu.apps.insurance.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","lenovo_K50-t5_RSY5LZ89F67SJZYT");//xiaomi-mi-2-208cbeb0;lenovo_K50-t5_RSY5LZ89F67SJZYT;oneplus-a0001-6428f2b8(android4.3);huawei-pe_tl20-F8UDU15126000635;
		capabilities.setCapability("platformVersion","5.1");//leveno5.1;xiaomi4.1.1;oneplus4.3
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.vanchu.apps.insurance");
		capabilities.setCapability("appActivity", ".module.splash.SplashActivity");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		
	
		//��ʼ��
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
		
		
		//��½app
		//����app֮��ȴ�һ��ʱ��
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//��ȡ������ҳ
		WebElement tabMine = driver.findElement(By.id("com.vanchu.apps.insurance:id/main_layout_tab_mine"));
		tabMine.click();
		
		//�����¼�������¼ҳ��
		WebElement loginBtn = driver.findElement(By.id("com.vanchu.apps.insurance:id/mine_txt_user_name"));
		loginBtn.click();
	
		//��������¼�����������¼ҳ��
		WebElement pwdLogin = driver.findElement(By.id("com.vanchu.apps.insurance:id/login_txt_pass_login"));
		pwdLogin.click();
			
		
		//��ȡҳ������������
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		//�����ֻ�����
		textFieldsList.get(0).sendKeys("15019226379");
		//��������
		textFieldsList.get(1).sendKeys("123456");
		
		//��¼��ť��com.vanchu.apps.insurance:id/pass_login_btn_login
		WebElement pwdLoginBtn = driver.findElementById("com.vanchu.apps.insurance:id/pass_login_btn_login");
		pwdLoginBtn.click();
		
	}
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		driver.quit();
	}
	
	/*
	 * ���˴���
	 * */
	@Test
	public void test001AudltSeriousDisease()
	{
		WebElement otherInsurance = driver.findElementById("com.vanchu.apps.insurance:id/main_layout_tab_other_insurance");
		otherInsurance.click();
		
		
	}
	
	
	
}
