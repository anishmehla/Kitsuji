Kitsuji is a native Android application built with Kotlin and Jetpack Compose that allows users to explore trending anime, search for titles, and manage a personalized watch list.

This project was built to demonstrate proficiency in modern Android development patterns, including reactive UI, local data persistence, and REST API integration.

# Key Features

    Live Discovery: Fetches real-time anime data and trending titles using a REST API.

    Smart Search: Search for any anime title with instant results.

    Local Persistence: Users can save anime to a "Watchlist" or "Favorites" which is accessible offline via Room Database.

    Detailed Insights: View synopses, ratings, episodes, and genres for every title.

    Modern UI: A fully responsive and fluid interface built entirely with Jetpack Compose.

# Tech Stack

    Language: Kotlin

    UI Framework: Jetpack Compose (Declarative UI)

    Architecture: MVVM (Model-View-ViewModel)

    Local Database: Room (SQLite abstraction)

    Networking: Retrofit for API communication

    Image Loading: Coil (Kotlin-first image loading)

    Dependency Injection: Hilt

# Architecture & Design

The app follows the MVVM (Model-View-ViewModel) architectural pattern to ensure a clean separation of concerns:

    UI Layer (Compose): Observes StateFlow from the ViewModel and renders the UI.

    ViewModel Layer: Handles UI logic and communicates with the Repository.

    Repository Layer: The single source of truth that decides whether to fetch data from the Network (API) or the Local Database (Room).

    Data Layer: Consists of the DAO for Room and the Service interface for the REST API.

# Screenshots
![1](https://github.com/user-attachments/assets/f8d8ffaf-298a-4817-9181-6eb5c1c27fae)
![2](https://github.com/user-attachments/assets/86578265-fd62-4dc5-b5b6-95a41d06c082)
![3](https://github.com/user-attachments/assets/a53fa7f2-892a-438b-a9ba-7ba9e6f6ddc8)
![4](https://github.com/user-attachments/assets/300ae531-7ecc-41af-80d6-12c1789650b2)
![5](https://github.com/user-attachments/assets/36c3d5fe-942c-431d-ac9c-12f7d18c7838)
![6](https://github.com/user-attachments/assets/142a1cab-05b7-43d0-b6bb-231b7575c661)

    
# How to Run

    Clone the repository:
    Bash

git clone https://github.com/anishmehla/Kitsuji.git

Open the project in Android Studio (Ladybug or newer).

Ensure you have the latest Android SDK installed.

Build and run the project on an emulator or a physical device.
