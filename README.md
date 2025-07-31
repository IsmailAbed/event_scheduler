# 🗓️ Event Scheduler App

A full-stack web application for scheduling and managing events, built with **Spring Boot**, **Angular**, and **OpenAI API** for AI-powered event summarization.

---

## 🔧 Tech Stack

- **Backend**: Java 22, Spring Boot 3.5, Spring Data JPA, MySQL, REST API
- **Frontend**: Angular 17+, Angular Material, SCSS
- **AI Integration**: OpenAI GPT-3.5 Turbo API
- **Build Tools**: Maven, Vite (Angular)
- **Deployment-ready**: API proxy, CORS handled, modular structure

---

## ⚙️ Features

- Add / update / delete events with:
  - Title
  - Description
  - Date & time
  - Location
  - Status (Planned / Completed / Cancelled)
- Search by title/location/date range
- Summarize event descriptions using OpenAI
- Clean UI & responsive form

---

## 🚀 Getting Started

### ✅ Prerequisites

- Node.js v18+
- Angular CLI
- Java 21/22
- MySQL server running
- OpenAI API key

### 🔙 Backend Setup

```bash
cd scheduler
# Configure application.properties with DB + OpenAI key
mvn spring-boot:run
