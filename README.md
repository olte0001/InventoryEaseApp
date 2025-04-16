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
## Setting up Spring Boot and Docker (with Windows Subsystem for Linux, Docker Desktop, IntelliJ IDEA and pgAdmin Desktop already installed)
- **Open Project**: In IntelliJ IDEA, open the "InventoryEase" folder: InventoryEaseApp > backend > InventoryEase
- **Set PostgreSQL Database**: In the docker-compose.yml, change the credentials of 'POSTGRES_DB', 'POSTGRES_PASSWORD' and 'POSTGRES_USER' to your corresponding database, user and password.
- **Build Project**: In the IntelliJ IDEA terminal, enter "./gradlew build -t test"
- **Build and Run Docker container**: In the IntelliJ IDEA terminal, enter "docker-compose up --build"
- **Connect pgAdmin with Dockerized PostgreSQL**:
    - In pgAdmin, right-click Servers > Create > Server and name it.
    - In the Connection tab, insert "127.0.0.1" for the address as were using WSL, your database for maintenance database, your database username for the username and your password for password. Make sure to toggle on "Save password?". Click "Save".
- **Restore PostgreSQL database**: 
    - In bash terminal, navigate to the "InventoryEaseApp" folder
    - In bash terminal, enter the following command to copy the included backup.sql into the running postgres container "docker cp backup.sql inventoryease-postgres-1:/backup.sql"
    - In the bash terminal, enter the following command to open a terminal session in the running postgres container "docker exec -it inventoryease-postgres-1 bash"
    - In the bash terminal, enter the following command to restore the data "psql -U your_user -d your_db -f /backup.sql". Don't forget to replace "your_user" and "your_db" with your own credentials.

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
