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
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/Herokuapp/Booking/GET_Booking_IDs'))

//Verify Status Code
WS.verifyResponseStatusCode(response, 200)

//Parse JSON
JsonSlurper newJson = new JsonSlurper()
Object parseJson = newJson.parseText(response.getResponseBodyContent())

// Verify Response is Array
assert parseJson instanceof List
assert parseJson.size() > 0

// Validate Each bookingid
parseJson.eachWithIndex { item, index ->
	KeywordUtil.logInfo("Checking index: " + index)

	// field exists
	assert item.containsKey('bookingid')

	// not null
	assert item.bookingid != null

	// type integer / number
	assert item.bookingid instanceof Number

	KeywordUtil.logInfo("bookingid valid: " + item.bookingid)
}

// Optional: Save First Booking ID
// ========================
GlobalVariable.firstBookingId = parseJson[0].bookingid
println("First Booking ID saved: " + GlobalVariable.firstBookingId)