import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//Send Request
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Herokuapp/Booking/GET_Booking_By_Id',[
	'bookingId' : '38']))

//Verify Status Code
WS.verifyResponseStatusCode(response, 200)

//Parse JSON
JsonSlurper newJson = new JsonSlurper()
Object parseJson = newJson.parseText(response.getResponseBodyContent())

// Root Validation 
assert parseJson.firstname != null
assert parseJson.lastname != null
assert parseJson.totalprice instanceof Number
assert parseJson.depositpaid instanceof Boolean
assert parseJson.additionalneeds != null

// Log Info
KeywordUtil.logInfo("Firstname: " + parseJson.firstname)
KeywordUtil.logInfo("Lastname: " + parseJson.lastname)
KeywordUtil.logInfo("Check-in: " + parseJson.bookingdates.checkin)
KeywordUtil.logInfo("Check-out: " + parseJson.bookingdates.checkout)
