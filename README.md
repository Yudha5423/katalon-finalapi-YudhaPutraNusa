# 🚀 katalon-finalapi-YudhaPutraNusa

API Automation Testing project using **Katalon Studio** for **Restful Booker API**.  
This project demonstrates **end-to-end API testing with chaining** (Auth → Create → Update → Partial Update → Delete).

---

## 📌 Overview

This repository contains automated API test cases built with **Katalon Studio** to validate CRUD operations on the Restful Booker API.

The test flow simulates a real-world scenario where data created in one request is reused in subsequent requests (chaining).

---

## 🛠️ Tech Stack

- **Katalon Studio**
- **Groovy**
- **RESTful API**
- **Restful Booker API**

---

## 🧪 Test Coverage

- 🔐 **Auth – Generate Token**
- ➕ **Create Booking (POST)**
- ✏️ **Update Booking (PUT)**
- 🩹 **Partial Update Booking (PATCH)**
- ❌ **Delete Booking (DELETE)**

---

## 🔗 Test Flow (Chaining)

The test cases are executed sequentially with shared data:

1. Generate authentication token
2. Create booking → store `bookingId`
3. Update booking using `bookingId`
4. Partial update booking using `bookingId`
5. Delete booking using `bookingId`

Global Variables are used to store:
- `authToken`
- `createdBookingId`

---

## ▶️ How to Run

1. Open project in **Katalon Studio**
2. Navigate to **Test Cases**
3. Run the main test case that calls:
   - POST_Token
   - POST_Create_Booking
   - PUT_Update_Booking
   - PATCH_Partial_Update_Booking
   - DELETE_Booking

---

## ✅ Validation Strategy

- HTTP Status Code validation
- Response body validation (JSON fields)
- Empty response validation for DELETE
- Data consistency validation across chained requests

---

## ⚠️ Notes

- DELETE endpoint does not return JSON
- PATCH request only sends modified fields
- GlobalVariable-based chaining is not recommended for parallel execution

---

## 👤 Author

**Yudha Putra Nusa**

---

## 📄 License

This project is for learning and demonstration purposes.
