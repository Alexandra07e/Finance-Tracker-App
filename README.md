# 💼 Finance Tracker – JavaFX Desktop Application

[![Language](https://img.shields.io/badge/language-Java-orange.svg)](https://en.wikipedia.org/wiki/Java_(programming_language))  
[![Framework](https://img.shields.io/badge/framework-JavaFX-green.svg)](https://openjfx.io/)  
📊 Modern desktop application for tracking income, expenses, and managing personal finances with an intuitive UI built in JavaFX.

---

## 📌 Overview

Finance Tracker is a **JavaFX-based desktop app** that helps users monitor and manage their finances efficiently.  
It supports **adding, viewing, updating, and deleting** transactions, each with details such as **amount, date, category, type, and description**.  

The app features a **visually appealing interface** with custom styles, animations, and background media to create an engaging user experience.

> 💡 Developed as part of an extracurricular project.
---

## 🧱 Components

| Component                  | Description                                                                 |
|----------------------------|-----------------------------------------------------------------------------|
| **Add Transaction**        | Form to input transaction details (amount, type, category, notes)          |
| **View Transactions**      | TableView with sorting, filtering, and search capabilities                  |
| **Update Transaction**     | Edit existing transaction details directly from the table                   |
| **Delete Transaction**     | Remove transactions with confirmation alerts                                |
| **Start Menu**             | Animated start screen with video background, text effects, and navigation  |

---

## 🔧 Technologies & Concepts

- **Java 17**, **JavaFX 21**
- **FXML** for UI layout
- **CSS** for theming & styling
- **SQLite** (or file-based storage)
- **Media integration** (video background & images)
- **TableView filtering** and **ChoiceBox** for category selection

---

## 🎨 Features

<details>
  <summary><strong>🏠 Start Screen</strong></summary>

- Video background with looping playback
- Animated welcome text & button reveal
- Background image and custom font styles
</details>

<details>
  <summary><strong>➕ Add Transaction</strong></summary>

- Minimalist card-style form
- Fields: **Amount**, **Date**, **Category**, **Type**, **Notes**
- Styled with black & white modern theme
</details>

<details>
  <summary><strong>📜 View Transactions</strong></summary>

- TableView with sorting & search
- Row highlighting and custom selection color
- ChoiceBox for filtering by category
</details>

<details>
  <summary><strong>✏️ Update Transaction</strong></summary>

- Edit any transaction from the table
- Validations to avoid duplicates
</details>

<details>
  <summary><strong>🗑️ Delete Transaction</strong></summary>

- Confirmation dialogs before deletion
- Black & white styled `Alert` windows
</details>

---

## 🚀 How to Run

### **Requirements**
- Java 17+
- JavaFX SDK (or Maven/Gradle to auto-manage dependencies)

---
