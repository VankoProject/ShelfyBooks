# ShelfyBooks

ShelfyBooks is a simple and modern Android application for browsing New York Times bestseller books by category.  
This app was developed as a test project with a focus on clean architecture, multi-module structure, SOLID principles, and clear UI/UX.

## Features

- List of bestsellers by category  
- Book details with authors, publisher, description, and image  
- Dialog with sellers and clickable external links to buy books  
- Light and dark theme with custom paper-like background colors  
- Offline caching using Room Database  
- Pull-to-refresh support  
- Error and empty states handling  
- WebView support for seller websites  

## Technology Stack

- **Kotlin** — main language  
- **MVVM + Flow** — architecture and state management  
- **Jetpack Compose** — UI toolkit  
- **Jetpack Navigation** — screen transitions  
- **Retrofit** — networking  
- **Room** — local database caching  
- **Hilt** — dependency injection  
- **Firebase Auth** — login with Google  
- **Coil** — image loading  
- **Multi-module** — modular and scalable project structure  

## Modules

- **App** — build module only, connects other modules  
- **Data** — data layer with repositories, network and database sources  
- **Domain** — business logic and use cases  
- **Presentation** — UI logic, screens, navigation, and ViewModels

## Screens

- Splash screen  
- Authorization screen (Google sign-in)  
- Categories screen  
- Books screen (books by category)  
- WebView screen (seller website)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/VankoProject/ShelfyBooks.git
```

2. Open the project in Android Studio

3. Let Gradle sync the dependencies

4. Run the app on a real device or emulator with internet access
