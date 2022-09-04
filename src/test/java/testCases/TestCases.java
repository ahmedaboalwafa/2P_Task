package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Add_Customer_Page;
import pages.Customers_Page;
import pages.Home_Page;

import java.util.HashMap;

import static Utilities.Configuration_Data_Reader.getConfigData;
import static org.testng.Assert.*;

public class TestCases extends BaseTests {
    static Customers_Page customers_Page ;
    int ID ;
    @Test
    public void AddCustomerData(){
        customers_Page = new Home_Page(driver).navigateToLoginPage().navigateToManagerPage().openAddCustomerPage().addCustomerData(getConfigData("FirstName"), getConfigData("LastName"), getConfigData("PostCode"));
        ID = Add_Customer_Page.returnId();
        HashMap CustData = customers_Page.ReturnCustData(ID-1);
        SoftAssert SA = new SoftAssert();
        SA.assertEquals(CustData.get("FIRSTName"),getConfigData("FirstName"));
        SA.assertEquals(CustData.get("LASTName"),getConfigData("LastName"));
        SA.assertEquals(CustData.get("PostCode"),getConfigData("PostCode"));
    }

    @Test (dependsOnMethods = { "AddCustomerData" })
    public void AddCustomerNumber(){

        String CustNumInAlert = customers_Page.AddCustNumber(ID);
        String CustNumsInList = customers_Page.ReturnCustNumber(ID-1);
        System.out.println(CustNumInAlert);
        assertEquals(CustNumsInList,CustNumInAlert);
    }

    @Test (dependsOnMethods = { "AddCustomerData" })
    public void DeleteCustomer(){
        assertTrue(customers_Page.DeleteCust(ID));
    }

}
