# YouApp (Android)

Pequeño proyecto Android basado en **Jetpack Compose** y **Material 3** con una pantalla de inicio de sesión, _splash screen_ 

> :wip: Working in Progress 

---

## 📦 Detalles del proyecto

- **targetSdk:** `36`  
- **minSdk:** `25`  
- **Android Gradle Plugin (AGP):** `8.13.0`  
- **Kotlin:** `2.2.10`  
- **Jetpack Compose UI:** `1.9.0`  
- **Compose Compiler:** `1.5.3`  
- **Material 3:** `1.3.2`  

---

## 🧰 Tecnologías y librerías clave

- **Kotlin** para el desarrollo Android.
- **Jetpack Compose** (UI declarativa)
- **Splash Screen API** (`androidx.core.splashscreen`).
- **JUnit** y pruebas de UI de Compose (dependencias incluidas).

Consulta `gradle/libs.versions.toml` para el catálogo completo de versiones.

---

## 🗂️ Estructura del proyecto (resumen)

```
app/
  src/main/
    AndroidManifest.xml
    java/com/sv/youapp/app/
      MainActivity.kt         # Entry point, instala Splash y muestra LoginScreen
      YouApp.kt               # Clase Application
      ui/
        GradientButton.kt     # Botón con fondo en gradiente
        login/
          LoginScreen.kt      # Pantalla de inicio de sesión (UI)
    res/
      values*/                # Temas, colores, strings
      drawable/               # Recursos de splash (layer/inset)
      xml/                    # Reglas de backup y data extraction
build.gradle.kts
settings.gradle.kts
gradle.properties
gradle/libs.versions.toml
```

---

## 🖥️ Funcionalidades actuales

- Pantalla de **Login** (correo/usuario y acciones de “Iniciar sesión”/“Registrarse”, estilo Material 3).
- **Splash Screen** nativo con fondo/ícono (usa `Theme.YouApp.Splash`).
- **Botón con gradiente** reutilizable (`GradientButton`).

## Vista rápida

### Abrir app
<p align="center">
  <img src="./docs/splash.gif" width="480" alt="Abrir app">
</p>

---

## 🚀 Requisitos

- **Android Studio** (Koala+ / Iguana+ que soporte AGP 8.13.0).
- **JDK 17** 
- **SDK Android**
- Dispositivo/emulador con **API ≥ 25**.


## 🎨 Temas y Splash

- Tema por defecto: `Theme_YouApp` (compatibilidad para < API 31).
- **Splash** definido por tema `Theme.YouApp.Splash` y drawables:
  - `drawable/ic_splay_layer.xml` (background + logo centrado)
  - `drawable/ic_splash_inset.xml` (inset de logo)
- **Strings** base: `values/strings.xml` (p.ej. `bienvenida`).

> Asegúrate de incluir el recurso `ic_logo` en `res/drawable` si deseas personalizar el ícono del splash, ya que se hace referencia a él en los drawables.
