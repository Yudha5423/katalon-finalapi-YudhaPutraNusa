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

// Send Request
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Herokuapp/Booking/PUT_Update_Booking',[
	'bookingId' : GlobalVariable.createdBookingId,
	'tokenAuth' : GlobalVariable.authToken,
	'firstname' : 'Yudha',
	'lastname' : 'Test',
	'totalprice' : 111,
	'depositpaid' : true,
	'checkin' : '2026-01-01',
	'checkout' : '2027-01-01',
	'additionalneeds' : 'WC Jongkok']))

// Verify Status Code
WS.verifyResponseStatusCode(response, 200)

// Parse JSON
JsonSlurper newJson = new JsonSlurper()
Object parseJson = newJson.parseText(response.getResponseBodyContent())

// Validate Updated Data
assert parseJson.firstname == 'Yudha'
assert parseJson.lastname == 'Test'
assert parseJson.totalprice == 111
assert parseJson.depositpaid == true
assert parseJson.additionalneeds == 'WC Jongkok'

// Validate Booking Dates
assert parseJson.bookingdates.checkin == '2026-01-01'
assert parseJson.bookingdates.checkout == '2027-01-01'


// Log
KeywordUtil.logInfo("Booking ID " + GlobalVariable.createdBookingId + " successfully updated")