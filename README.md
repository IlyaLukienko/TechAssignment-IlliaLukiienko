# TechAssignment-IlliaLukiienko

# TechAssignment-IlliaLukiienko

A simple Android Payment App built as part of the Wallee Tech Assignment. The app demonstrates modern Android development practices including Clean Architecture, MVVM, Jetpack Compose, Ktor, and Hilt.

---

## Features

- **PinPad Screen** — UI for entering a transaction amount using a custom keypad  
- **Network Fetch** — Simulates downloading transaction data from an API  
- **Receipt Screen** — Displays receipt details with calculated tax and total  
- **Business Logic** — Tax and total calculations encapsulated in use cases  
- **Pixel-Perfect UI** — Fully styled UI matching Figma design  
- **Unit Tests** — Business logic is covered by tests  
- **Clean Architecture** — Presentation, Domain, and Data layers separated  
- **Dependency Injection** — Managed by Hilt

---

## 📱 Screenshots

| PinPad Screen                             | Receipt Screen                             |
|------------------------------------------|--------------------------------------------|
| ![PinPad](screenshots/pinpad.png)        | ![Receipt](screenshots/receipt.png)        |

---

## 🛠 Tech Stack

| Layer         | Tools & Libraries                            |
|---------------|-----------------------------------------------|
| UI            | Jetpack Compose, Material3                    |
| DI            | Hilt                                          |
| Networking    | Ktor + Kotlinx Serialization                  |
| Architecture  | MVVM + Clean Architecture                     |
| Testing       | JUnit, MockK                                  |
| Utilities     | StringResourcesHelper for string management   |

---

## 🧱 Architecture

📁 app
├── core/
│   └── StringResourcesHelper.kt
├── data/
│   └── repository/ReceiptRepositoryImpl.kt
├── domain/
│   ├── model/Transaction.kt
│   ├── repository/ReceiptRepository.kt
│   ├── screen/IPinPadScreen.kt
│   ├── screen/IReceiptScreen.kt
│   └── usecase/
│       ├── PinPadUseCase.kt
│       └── ReceiptUseCase.kt
├── presentation/
│   ├── navigation/NavGraph.kt
│   ├── ui/
│   │   ├── pinpad/PinPadScreen.kt
│   │   └── receipt/ReceiptScreen.kt
│   └── viewmodel/
│       ├── PinPadViewModel.kt
│       └── ReceiptViewModel.kt
└── di/
    ├── NetworkModule.kt
    └── RepositoryModule.kt
