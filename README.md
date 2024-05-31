# Health System API

This project implements a Health System API designed to address the complex requirements of modern healthcare management. The API serves as the foundation for various healthcare applications and systems, providing essential functionalities for patient management, appointment scheduling, medical record keeping, prescription management, and billing.

## Overview

The Health System API is designed to equip developers with practical skills and knowledge in REST API design and implementation using JAX-RS. It provides a robust framework for managing various aspects of healthcare systems.

## System Entities

The API consists of the following key entities:

1. **Person**: Represents a generic individual with attributes such as name, contact information, and address.
2. **Patient**: Extends the Person entity to include specific details relevant to patients, such as medical history and current health status.
3. **Doctor**: Extends the Person entity to include information about healthcare professionals, including their specialization and contact details.
4. **Appointment**: Represents scheduled appointments between patients and doctors, including details like date, time, and participants.
5. **Medical Record**: Holds comprehensive medical information about patients, covering diagnoses, treatments, and other relevant data.
6. **Prescription**: Records information about prescribed medications, including dosage, instructions, and duration.
7. **Billing**: Manages financial transactions related to healthcare services, including invoices, payments, and outstanding balances.

## Installation

To set up the project locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/health-system-api.git
   ```

2. Navigate to the project directory:

   ```bash
   cd health-system-api
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage

Once the project is deployed, you can access the API through the specified endpoints. The API follows RESTful principles, ensuring that resources are clearly identified and accessible through intuitive URIs.

## API Endpoints

### PersonResource

- **GET /persons**: Retrieves a list of all person records stored in the system.

### DoctorResource

- **GET /doctors**: Retrieves a list of all doctor records stored in the system.
- **GET /doctors/{id}**: Retrieves a specific doctor record based on the provided doctor ID.
- **POST /doctors**: Creates a new doctor record in the system.
- **PUT /doctors/{id}**: Updates an existing doctor record identified by the doctor ID.
- **DELETE /doctors/{id}**: Deletes a doctor record from the system based on the provided doctor ID.

### PatientResource

- **GET /patients**: Retrieves a list of all patient records stored in the system.
- **GET /patients/{id}**: Retrieves a specific patient record based on the provided patient ID.
- **POST /patients**: Creates a new patient record in the system.
- **PUT /patients/{id}**: Updates an existing patient record identified by the patient ID.
- **DELETE /patients/{id}**: Deletes a patient record from the system based on the provided patient ID.

### AppointmentResource

- **GET /appointments**: Retrieves a list of all appointment records stored in the system.
- **GET /appointments/{id}**: Retrieves a specific appointment record based on the provided appointment ID.
- **POST /appointments**: Creates a new appointment record in the system.
- **PUT /appointments/{id}**: Updates an existing appointment record identified by the appointment ID.
- **DELETE /appointments/{id}**: Deletes an appointment record from the system based on the provided appointment ID.
- **GET /appointments/doctor/{doctorId}**: Retrieves a list of appointment records associated with a specific doctor based on the provided doctor ID.
- **GET /appointments/patient/{patientId}**: Retrieves a list of appointment records associated with a specific patient based on the provided patient ID.

### BillingResource

- **GET /billings**: Retrieves a list of all billing records stored in the system.
- **GET /billings/{id}**: Retrieves a specific billing record based on the provided billing ID.
- **POST /billings**: Creates a new billing record in the system.
- **PUT /billings/{id}**: Updates an existing billing record identified by the billing ID.
- **DELETE /billings/{id}**: Deletes a billing record from the system based on the provided billing ID.
- **GET /billings/patient/{patientId}**: Retrieves a list of billing records associated with a specific patient based on the provided patient ID.

### MedicalRecordResource

- **GET /medical-records**: Retrieves a list of all medical record records stored in the system.
- **GET /medical-records/{id}**: Retrieves a specific medical record based on the provided medical record ID.
- **POST /medical- records**: Creates a new medical record in the system.
- **PUT /medical-records/{id}**: Updates an existing medical record identified by the medical record ID.
- **DELETE /medical-records/{id}**: Deletes a medical record from the system based on the provided medical record ID.
- **GET /medical-records/patient/{patientId}**: Retrieves a list of medical records associated with a specific patient based on the provided patient ID.

### PrescriptionResource

- **GET /prescriptions**: Retrieves a list of all prescription records stored in the system.
- **GET /prescriptions/{id}**: Retrieves a specific prescription record based on the provided prescription ID.
- **POST /prescriptions**: Creates a new prescription record in the system.
- **PUT /prescriptions/{id}**: Updates an existing prescription record identified by the prescription ID.
- **DELETE /prescriptions/{id}**: Deletes a prescription record from the system based on the provided prescription ID.
- **GET /prescriptions/patient/{patientId}**: Retrieves a list of prescription records associated with a specific patient based on the provided patient ID.

## Logging and Exception Handling

The project includes comprehensive logging throughout the codebase, providing detailed information for debugging and auditing. Exception handling covers a wide range of scenarios with informative error messages using appropriate HTTP response codes.
