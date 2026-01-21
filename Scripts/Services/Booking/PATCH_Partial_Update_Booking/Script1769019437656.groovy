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
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Herokuapp/Booking/PATCH_Partial_Update_Booking', [
	'bookingId' : GlobalVariable.createdBookingId,
	'tokenAuth' : GlobalVariable.authToken,
	'firstname' : 'Jamie',
	'lastname' : 'Smith']))

// Verify Status Code
WS.verifyResponseStatusCode(response, 200)

// Parse JSON
JsonSlurper newJson = new JsonSlurper()
Object parseJson = newJson.parseText(response.getResponseBodyContent())

// Validate Updated Fields
assert parseJson.firstname == 'Jamie'
assert parseJson.lastname == 'Smith'


// Validate Unchanged Fields 
assert parseJson.totalprice instanceof Number
assert parseJson.depositpaid instanceof Boolean
assert parseJson.bookingdates.checkin != null
assert parseJson.bookingdates.checkout != null

// Log
KeywordUtil.logInfo("Booking ID " + GlobalVariable.createdBookingId + " partially updated successfully")