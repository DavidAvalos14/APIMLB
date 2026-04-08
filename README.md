# APIMLB - MLB Tracker

Aplicación Android desarrollada con Kotlin y Jetpack Compose que consume la API oficial de MLB para mostrar:

- Juegos por fecha
- Standings por temporada (Liga Americana y Liga Nacional)

El proyecto está orientado a practicar consumo de APIs REST con Retrofit, manejo de estado con ViewModel y construcción de UI moderna con Compose.

## Características

- Pantalla de inicio con accesos a secciones principales
- Vista de juegos del día con selector de fecha
- Lista de partidos con:
	- Equipo visitante y local
	- Marcador
	- Estado detallado del juego
- Vista de standings por temporada
- Agrupación de standings por liga y división
- Splash screen al iniciar la app

## Stack Tecnológico

- Lenguaje: Kotlin
- UI: Jetpack Compose + Material 3
- Arquitectura: UI + ViewModel (estado en capa de presentación)
- Networking: Retrofit + Gson Converter
- Navegación: Navigation Compose
- Concurrencia: Coroutines (viewModelScope)

## API Consumida

Base URL usada en el proyecto:

- `https://statsapi.mlb.com/`
- `https://statsapi.mlb.com/api/v1/`

Endpoints principales:

1. Juegos por fecha
	 - `GET /api/v1/schedule`
	 - Query params:
		 - `sportId=1`
		 - `date=YYYY-MM-DD`

2. Standings por temporada
	 - `GET /api/v1/standings`
	 - Query params:
		 - `leagueId=103,104`
		 - `season=YYYY`
		 - `standingsType=regularSeason`

## Estructura del Proyecto

```text
app/src/main/java/com/example/apimlb/
|- data/
|  |- APIMlb.kt            # Retrofit para schedule
|  |- ApiService.kt        # Retrofit para standings
|- model/
|  |- ScheduleModels.kt
|  |- StandingModel.kt
|  |- League.kt
|- view/
|  |- SplashScreen.kt
|  |- HomeView.kt
|  |- JuegosView.kt
|  |- StandingsView.kt
|- viewModel/
|  |- JuegosViewModel.kt
|  |- StandingsViewModel.kt
|- navigation/
|  |- NavManager.kt
|- components/
|  |- GameCard.kt
|  |- StandingCard.kt
```

## Requisitos

- Android Studio (recomendado: versión reciente)
- JDK 11
- Android SDK configurado
- Conexión a internet (la app consulta datos en tiempo real)

Parámetros Android del proyecto:

- `minSdk = 24`
- `targetSdk = 36`
- `compileSdk = 36`

## Cómo Ejecutar

1. Clona este repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
```

2. Abre el proyecto en Android Studio.
3. Sincroniza Gradle.
4. Ejecuta en un emulador o dispositivo físico.

También puedes compilar por terminal:

```bash
./gradlew assembleDebug
```

En Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Flujo de Navegación

- `Splash` -> `Home`
- Desde `Home`:
	- `Juegos`
	- `Standings`
	- Enlace externo a MLB Grid

## Estado Actual

- Se manejan errores de red de forma básica (fallback y mensaje de error).
- La app prioriza simplicidad para aprendizaje de consumo de API y Compose.

## Mejoras Sugeridas

- Inyección de dependencias (Hilt/Koin)
- Capa de repositorio y casos de uso
- Manejo robusto de errores (sealed UI state)
- Pruebas unitarias para ViewModels
- Caché local (Room) para mejorar experiencia offline
- Pull-to-refresh y estados vacíos más ricos

## Créditos

- API: MLB Stats API
- UI y lógica implementadas en Kotlin + Jetpack Compose

