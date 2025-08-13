# ElAsadosSteaks — Demo (Mock Mode)

Esta versión es una **demo funcional sin dependencia de backend**. Usa `MockData.MOCK_MODE = true`
para simular login, productos y pedidos. Las imágenes de productos se cargan desde Internet (Unsplash URLs).

## Qué incluye
- Login/Register mock (no requiere red).
- Lista de productos con imágenes desde Internet (Glide).
- Carrito funcional en memoria.
- Seguimiento simulado con imagen estática (sin Google Maps API key).
- Perfil editable guardado localmente.
- Módulo repartidor simulado con pedidos ficticios.
- Compila en Android Studio sin pasos adicionales.

## APK
No incluí un APK en este paquete porque el entorno donde se generó el proyecto no puede ejecutar Gradle para compilar un APK.
Puedes generar el APK localmente siguiendo estos pasos:

1. Abre Android Studio y selecciona **Open an existing project** → abre la carpeta `ElAsadosSteaks`.
2. Espera a que Gradle sincronice (asegúrate de tener JDK 17 y Android SDK instalado).
3. Ve a **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
4. Android Studio generará el APK y te mostrará una notificación con un botón para localizarlo.

Si quieres, puedo añadir un archivo de configuración de GitHub Actions para construir el APK automáticamente en CI y entregarte el artefacto. Dime si quieres ese flujo y lo preparo.

## Notas técnicas
- Para volver a modo "real" (usar la API real), pon `MockData.MOCK_MODE = false` y añade `google-services.json` si activas FCM/Maps.
- Las interfaces Retrofit están presentes y pueden activarse fácilmente.

