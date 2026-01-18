# 🧠 Smart Complaint & Feedback Analyzer (LLM-Powered, FREE & Offline)

A **Spring Boot–based AI system** that analyzes user complaints and feedback to automatically detect **sentiment**, **priority**, **category**, and generate a **polite auto-response** using a **local LLM (LLaMA / Mistral via Ollama)**.

✅ **100% FREE**  
✅ **No API key required**  
✅ **Offline / Local LLM**  
✅ **Production-style architecture**

---

## 🚀 Features

- Accepts customer feedback / complaints via REST API
- AI-powered analysis:
  - Sentiment detection (Positive / Neutral / Negative)
  - Priority classification (LOW / MEDIUM / HIGH)
  - Category detection (Service / Staff / Cleanliness / Other)
  - Auto-generated response
- Uses **local LLM (Ollama)** — no OpenAI billing
- Clean layered architecture (Controller → Service → Client → DB)
- Easily switchable to OpenAI / Gemini later

---

## 🏗️ Tech Stack

- **Java 17**
- **Spring Boot 3+**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Ollama (Local LLM)**
- **LLaMA 3 / Mistral model**
- **REST APIs**

---
## ⚙️ Prerequisites

Make sure you have:

- Java 17+
- Maven
- Linux / macOS / Windows (WSL recommended)
- Minimum **8 GB RAM** (for LLaMA models)

---

## 🧠 Setup Local LLM (Ollama)

### 1️⃣ Install Ollama

```bash
curl -fsSL https://ollama.com/install.sh | sh

Verify:
ollama --version

2️⃣ Download a FREE model
Choose one:
ollama pull llama3
or (lighter & faster):
ollama pull mistral

Verify:
ollama list

3️⃣ Start Ollama Server
ollama serve
(Ollama runs on http://localhost:11434)

▶️ Run the Application
mvn clean spring-boot:run

Spring Boot will start on:
http://localhost:8080

🔌 API Usage
Endpoint
POST /api/feedback

Request Body
{
  "userName": "Amit",
  "message": "Room was dirty and staff was rude"
}

Sample Response
{
  "id": 1,
  "userName": "Amit",
  "message": "Room was dirty and staff was rude",
  "sentiment": "Negative",
  "priority": "HIGH",
  "category": "Cleanliness",
  "autoResponse": "We sincerely apologize for your experience. Our team will address this immediately."
}

🧪 Local Testing (Without LLM Cost)
    No API key required
    Runs completely offline
    Ideal for development, demos, and interviews

🧠 Interview-Ready Explanation

    “This project demonstrates how to integrate LLMs into a Spring Boot backend using a provider-agnostic design. To avoid API costs, I used a local LLaMA model via Ollama, but the architecture allows switching to cloud LLMs in production.”

👨‍💻 Author
Ranajit Khandual
Java & Spring Boot Developer
Focused on backend systems, clean architecture, and AI integration.
