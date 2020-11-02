package com.tyss.acttime.baseutil;

import org.openqa.selenium.WebDriver;

import com.tyss.actitime.pages.Amazon_Page;
import com.tyss.actitime.pages.BlueStone_Page;
import com.tyss.actitime.pages.Calender_Page;
import com.tyss.actitime.pages.CompletedTasks_Page;
import com.tyss.actitime.pages.GoibiboHotel_Page;
import com.tyss.actitime.pages.Goibibo_Page;
import com.tyss.actitime.pages.EaseMyTrip_Page;
import com.tyss.actitime.pages.JustDialChemist_Page;
import com.tyss.actitime.pages.JustDial_Page;
import com.tyss.actitime.pages.Login_Page;
import com.tyss.actitime.pages.MmtCalender_Page;
import com.tyss.actitime.pages.NaukriHome_Page;
import com.tyss.actitime.pages.Nykaa_Page;
import com.tyss.actitime.pages.OpenTasks_Page;
import com.tyss.actitime.pages.ProjectsAndCustomers_Page;
import com.tyss.actitime.pages.RelianceDigital_Page;
import com.tyss.actitime.pages.SnapDeal_Page;
import com.tyss.actitime.pages.Task_Page;
import com.tyss.actitime.pages.Tasks_Page;
import com.tyss.actitime.pages.TimeTrack_Page;
import com.tyss.actitime.pages.Users_Page;
import com.tyss.acttime.dataproviders.EaseMyTripDataProvider;
import com.tyss.acttime.util.WebActionUtil;

/**
 * Description Initialize all pages with driver,ETO, WebAactionUtil
 * 
 * @author shreya.u@testyantra.com,vivek.d@testyantra.com,aatish.s@testyantra.com
 * 
 */

public class InitializePages {
	public Login_Page loginPage;
	public Task_Page taskPage;
	public Tasks_Page tasksPage;
	public ProjectsAndCustomers_Page projectsAndCustomersPage;
	public TimeTrack_Page timeTrackPage;
	public CompletedTasks_Page completedTasksPage;
	public OpenTasks_Page openTasksPage;
	public Users_Page usersPage;
	public NaukriHome_Page naukriHomePage;
	public Goibibo_Page goibiboPage;
	public GoibiboHotel_Page goibiboHotelPage;
	public Calender_Page calenderPage;
	public MmtCalender_Page mmtCalenderPage;
	public Nykaa_Page nykaaPage;
	public JustDial_Page justDialPage;
	public JustDialChemist_Page justDialChemistPage;
	public RelianceDigital_Page relianceDigitalPage;
	public EaseMyTrip_Page easeMyTripPage;
	public BlueStone_Page blueStonePage;
	public SnapDeal_Page snapDealPage;
	public Amazon_Page amazonPage;
	public InitializePages(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		loginPage = new Login_Page(driver, ETO, WebActionUtil);
		taskPage = new Task_Page(driver, ETO, WebActionUtil);
		projectsAndCustomersPage = new ProjectsAndCustomers_Page(driver, ETO, WebActionUtil);
		tasksPage = new Tasks_Page(driver, ETO, WebActionUtil);
		timeTrackPage = new TimeTrack_Page(driver, ETO, WebActionUtil);
		completedTasksPage = new CompletedTasks_Page(driver, ETO, WebActionUtil);
		openTasksPage = new OpenTasks_Page(driver, ETO, WebActionUtil);
		usersPage =new Users_Page(driver,ETO,WebActionUtil);
		naukriHomePage =new NaukriHome_Page(driver,ETO,WebActionUtil);
		goibiboPage =new Goibibo_Page(driver,ETO,WebActionUtil);
		goibiboHotelPage =new GoibiboHotel_Page(driver,ETO,WebActionUtil);
		calenderPage =new Calender_Page(driver,ETO,WebActionUtil);
		nykaaPage =new Nykaa_Page(driver,ETO,WebActionUtil);
		justDialPage =new JustDial_Page(driver,ETO,WebActionUtil);
		justDialChemistPage =new JustDialChemist_Page(driver,ETO,WebActionUtil);
		relianceDigitalPage =new RelianceDigital_Page(driver,ETO,WebActionUtil);
		easeMyTripPage =new EaseMyTrip_Page(driver,ETO,WebActionUtil);
		blueStonePage =new BlueStone_Page(driver,ETO,WebActionUtil);
		snapDealPage =new SnapDeal_Page(driver,ETO,WebActionUtil);
		amazonPage=new Amazon_Page(driver, ETO, WebActionUtil);
	}

}
