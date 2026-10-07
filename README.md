# 🌱 FreshLink — Agri-Tech Marketplace & Crop Quality Platform

[![Java Spring Boot](https://img.shields.io/badge/Backend-Java%20Spring%20Boot-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![JavaScript](https://img.shields.io/badge/Frontend-Vanilla%20JS%20%2F%20HTML5-yellow?style=flat-square&logo=javascript)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
[![Python AI](https://img.shields.io/badge/AI%20Vision-Python%20Microservice-informational?style=flat-square&logo=python)](https://www.python.org/)

**FreshLink** is a full-stack agricultural e-commerce platform built to connect farmers directly with commercial buyers. By combining secure role-based authentication, an AI-driven computer vision crop grading system, and real-time shipment tracking, FreshLink streamlines transparent and efficient agricultural trade.

---

## 🚀 Core Features

* **🔐 Role-Based Authentication:** Secure user onboarding and session management supporting distinct user personas (`Farmer` and `Buyer`), backed by a persistent PostgreSQL database.
* **🌾 Marketplace & Yield Management:** Farmers can publish harvest listings with pricing, quantities, and harvest dates, while buyers can browse active batches and accept deals.
* **🧠 AI-Driven Crop Quality Vision:** Integrated Python AI module that analyzes crop photos, detects defects, calculates quality grades (e.g., Grade A Premium), and suggests fair market values.
* **🚚 Real-Time Shipment Tracking:** Interactive status management tracking agricultural yields live as they move from farm origin to destination.

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot, Spring Data JPA, RESTful APIs
* **Database:** PostgreSQL (Relational persistence for users, listings, and quality reports)
* **Frontend:** HTML5, Modern CSS3, Dynamic JavaScript (`app.js`)
* **AI Microservice:** Python (Image processing and quality grading)

---

## 📊 Database Schema

1. **`users`**: Manages entity credentials, phone identifiers, and role types (`farmer` | `buyer`).
2. **`listings`**: Stores harvest details, crop types, pricing, quantities, regional locations, and delivery statuses (`AVAILABLE`, `TRANSIT`, `DELIVERED`).
3. **`quality_reports`**: Logs AI diagnostic scores and visual defect evaluations per batch.

---

## ⚙️ Getting Started & Setup

### Prerequisites
* Java JDK 17+
* PostgreSQL
* Maven

### 1. Clone the Repository
```bash
git clone [https://github.com/SowndaryaLakshmi04/freshlink.git](https://github.com/SowndaryaLakshmi04/freshlink.git)
cd freshlink
