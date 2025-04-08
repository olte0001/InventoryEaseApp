# InventoryEase Mobile
## Overview
**InventoryEase Mobile** is an inventory management application designed to streamline
operations for small and medium-sized enterprises (SMEs), with scalability to support
larger organizations. The app focuses on intuitive, real-time inventory handling, supported
by barcode scanning and key automation features like low-stock and expiry alerts.
## Features Implemented (Phase 3)
### Company Selection (Multi-Tenant Support)
- Implemented multi-tenancy using Spring's Multi-Tenant support.
- Users select a company ID at login.
- Application dynamically connects users to the correct schema based on company ID,
ensuring data isolation between companies.
### User Login & Role Assignment
- Secure login system using **Spring Security**.
- Supports password encryption and role-based access control.
- Redirects authenticated users to their role-specific dashboard.
### Dashboard
- Role-based dashboard displays available actions.
- Clicking on an action navigates the user to the corresponding activity (some features still
pending implementation).
### Receiving Inventory
- Users can add new inventory by selecting:
 - Product (from database)
 - Supplier (from database)
 - Quantity
- On submission, the item is stored and a unique barcode is generated.
### Barcode Generation & Printing
- Barcodes generated using **ZXing** library.
- QR codes created and stored as bitmap images.
- Barcodes are printed using **PrintHelper** for improved compatibility and performance.
## Upcoming Features (Phase 4)
- **Move Inventory**: Implement functionality for relocating inventory and tracking its
movement in the database.
- **Find Inventory**: Allow users to search for and locate inventory across various
locations.
- **Consume Inventory**: Add support for consuming inventory, reflecting product usage
or removal.
- **Barcode Scanning**: Introduce scanning functionality to read and process QR codes
tied to inventory items.
## Technologies Used
- **Java**
- **Spring Boot**
- **Spring Security**
- **ZXing (Barcode Library)**
- **Android SDK**
- **PrintHelper API**
## Team Members
- Qirong Chen (041072747)
- Iulia Oltean (041117567)
- Sonu Thapa (041173288)
- Kevin Van Rens (040743089)
- Taylor Vance (041140729)
## Course
**CST 8319: Software Development Project**
Algonquin College Online
Instructor: Moe Osman
