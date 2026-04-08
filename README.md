# 🛒 Bazar API - Spring Boot

REST API for managing products, clients, and sales in a retail store.  
Built with **Spring Boot, JPA (Hibernate), and MySQL**.

---

## 🚀 Features

- Full CRUD for Products, Clients, and Sales
- Automatic total calculation for sales
- Stock update on create/edit/delete sales
- DTO pattern implementation
- Custom endpoints:
  - Low stock products
  - Sales summary by date
  - Highest sale
  - Products per sale
- Bonus:
  - Sales by client

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

---

## ⚙️ Setup

```bash
git clone https://github.com/YOUR-USER/bazar-api-java.git
cd bazar-api-java
```

---

## 📦 API Endpoints

#Products
- POST /productos/crear
- GET /productos
- GET /productos/{id}
- PUT /productos/editar/{id}
- DELETE /productos/eliminar/{id}
- GET /productos/falta_stock
#Clients
- POST /clientes/crear
- GET /clientes
- GET /clientes/{id}
- PUT /clientes/editar/{id}
- DELETE /clientes/eliminar/{id}
#Sales
- POST /ventas/crear
- GET /ventas
- GET /ventas/{id}
- PUT /ventas/editar/{id}
- DELETE /ventas/eliminar/{id}
#Custom
- GET /ventas/productos/{id}
- GET /ventas/fecha/{date}
- GET /ventas/mayor_venta
- GET /ventas/cliente/{id}

---

## 📌 Business Rules

- Each sale has one client and multiple products
- Total is calculated automatically
- Stock is updated on sales operations
- Each product counts as 1 unit per sale
