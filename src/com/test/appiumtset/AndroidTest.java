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
		
		//�ص����ڣ����session�޷��������⣩
      
		//capabilities.setCapability("appWaitActivity","com.vanchu.apps.insurance.module.splash.SplashActivity"); //����Ҫ�ȴ�������Activity����
		//capabilities.setCapability("sessionOverride", true); // ÿ������ʱ����session������ڶ��κ����лᱨ�����½�session
	
		//��ʼ��
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
			
	}
	
	/***
	 * ���������½
	 * @auth jolie
	 * 
	 ***/
	@Test
	public void test001PwdLongin()
	{
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
	
	/**
	 * �޸�ͷ�� 
	 * δ���
	 */
	@Test
	public void test002ChangeMineImage() {
		//��ȡ����ҳ�����ͷ���
		WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		mineImage.click();
		WebElement imageUser = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_img_user_icon");;
		imageUser.click();
		//ѡ��ͼ��ĵ�һ��ͼƬ����Ԥ��
		List<AndroidElement> imageList = driver.findElementsByClassName("android.widget.ImageView");
		imageList.get(1).click();
		
		//ѡ�е�ǰ��Ƭ
		WebElement selectImage = driver.findElementById("com.vanchu.apps.insurance:id/photowall_browse_checkbox");
		selectImage.click();
		WebElement finishBtn = driver.findElementById("com.vanchu.apps.insurance:id/photowall_browse_txt_finish");
		finishBtn.click();
		WebElement doneBtn = driver.findElementById("com.vanchu.apps.insurance:id/btn_done");
		doneBtn.click();
				
	}
	
	
	/**
	 * �޸��û�����
	 * 
	 */
	@Test
	public void test003ChangeMineName(){
		//��ȡ����ҳ�����ͷ���,�������ͷ����뵽��Ϣ�༭Ҳҳ
		//WebElement mineImage = driver.findElementById("com.vanchu.apps.insurance:id/mine_layout_user");
		//mineImage.click();
		
		//��ȡ�����򣬽��뵽�����޸�ҳ
		WebElement mineName = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_layout_name");
		mineName.click();
		
			
		//��ȡҳ������������
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		
		//��ȡ���������
		String text = textFieldsList.get(0).getText();
		textFieldsList.get(0).click();//��������
		//����ı��������	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
	    }
		
		//�����޸���Ϣ
		textFieldsList.get(0).sendKeys("jolie");
		
		//�������
		WebElement saveKeyElement = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_btn_save");
		saveKeyElement.click();
		
		//����֮�󷵻ص���һҳ�����½��뵽�޸�ҳ��
		mineName.click();
		
		//���޸��κζ�����ֱ�ӵ������
		WebElement returnKey = driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back");
		returnKey.click();
		
	}
	
	/**
	 * �޸����֤����
	 */
	@Test
	public void  test004ChangeIdNo(){
		WebElement mineIdNo = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_layout_idcard");
		mineIdNo.click();
		
		//��ȡҳ������������
		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
		
		//��ȡ���������
		String text = textFieldsList.get(0).getText();
		textFieldsList.get(0).click();//��������
		//����ı��������	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
	    }
		
		//�����޸���Ϣ
		textFieldsList.get(0).sendKeys("1111111");
		WebElement finishBtn = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_btn_save");
		finishBtn.click();			
		
		textFieldsList.get(0).click();
		//����ı��������	
		((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
		for (int i = 0; i < text.length(); i++) {
	        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
	    }
		
		textFieldsList.get(0).sendKeys("110101199809018853");
		finishBtn.click();
		
		//���뵽�޸����֤��Ϣҳ��ʲô�����޸�ֱ�ӵ������
		mineIdNo.click();
		driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back").click();
		
			
	
	}
	
	/**
	 * 
	 * ��ַ����
	 */
	@Test
	public void test005Address(){
		WebElement address = driver.findElementById("com.vanchu.apps.insurance:id/mine_info_txt_address");
		address.click();
		
		WebElement addAddress = driver.findElementById("com.vanchu.apps.insurance:id/mine_address_btn_add_address");
		addAddress.click();
		
		
//		List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
//		textFieldsList.get(0).sendKeys("��С��");
//		textFieldsList.get(1).sendKeys("15019226377");
//		textFieldsList.get(2).sendKeys("���������д�ĵ�ַ");
//		
//			�˴���BUG����������Ϊ���̫�죬��Ҫ��һ���ȴ�ʱ��
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
//		//�������µ�ַʲô������дֱ�ӵ������
//		addAddress.click();
//		WebElement addAddressBtn = driver.findElementById("com.vanchu.apps.insurance:id/title_bar_txt_back");
//		addAddressBtn.click();
//		
//		
//		
//		//���ص��ҵĵ�ַҳ������ڶ�����ַ����༭ҳ
//		List<AndroidElement> editAddressList = driver.findElementsById("android.widget.RelativeLayout");
//		//����ҵĵ�ַ��Ϊ��
//		if(editAddressList!=null)
//		{
//			editAddressList.get(1).click();
//			textFieldsList.get(0).click();
//			//�����ϵ������������	
//			String text = textFieldsList.get(0).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
//		    }
//			textFieldsList.get(0).sendKeys("�Ŵ���");
//			
//			
//			textFieldsList.get(1).click();
//			//����ֻ�����
//			text = textFieldsList.get(1).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
//		    }
//			textFieldsList.get(1).sendKeys("15019226374");
//			
//			textFieldsList.get(2).click();
//			//�����ϸ��ַ
//			text = textFieldsList.get(2).getText();
//			((AndroidDeviceActionShortcuts) driver).sendKeyEvent(123);//123������ƶ�����������ұ�
//			for (int i = 0; i < text.length(); i++) {
//		        ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(67);//67��delete��
//		    }
//			textFieldsList.get(2).sendKeys("���Ա༭��ַ");
//			
//			//�޸�����
//			cityList.get(1).click();;
//			provinceList.get(1).click();
//				
//		}
		//�޸�Ĭ�ϵ�ַ
		
		//ɾ��
		
		//�ҵĵ�ַ�������
		
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception
	{
		driver.quit();
	}
}
