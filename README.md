# Guía de Git Flow

Esta guía está diseñada para ayudarte a entender cómo funciona Git y cómo puedes utilizar GitKraken para gestionar tu flujo de trabajo. Nos enfocaremos en el trabajo con ramas, fusiones (merge) y pull requests, tanto desde la línea de comandos como desde GitKraken. Asumimos que cada programador trabajará en una rama con su propio nombre.

---

## Conceptos Básicos de Git

Antes de sumergirnos en los comandos y herramientas, es importante comprender algunos conceptos fundamentales:

- **Repositorio**: Es el lugar donde se almacena el código de tu proyecto junto con su historial de cambios.
- **Commit**: Es un registro de cambios en el repositorio. Cada commit guarda una instantánea del proyecto en un momento dado.
- **Rama (Branch)**: Permite trabajar en diferentes líneas de desarrollo dentro del mismo repositorio.

---

## Trabajando con Ramas

### Usando la Línea de Comandos (CMD)

#### 1. Clonar el Repositorio

Si aún no has clonado el repositorio, puedes hacerlo con:

```bash
git clone [URL_DEL_REPOSITORIO]
```

#### 2. Crear y Cambiar a tu Rama Personal

Crea una nueva rama con tu nombre y cambia a ella:

```bash
git checkout -b tu_nombre
```

#### 3. Trabajar en tu Rama

Realiza los cambios necesarios en el código.

#### 4. Hacer Commit de tus Cambios

Después de modificar los archivos, añade y comitea tus cambios:

```bash
git add .
git commit -m "Descripción de los cambios realizados"
```

#### 5. Subir tu Rama al Repositorio Remoto

Envía tus cambios a la rama remota:

```bash
git push origin tu_nombre
```

Si es la primera vez que subes esta rama, puede que necesites establecer la rama upstream:

```bash
git push -u origin tu_nombre
```

#### 6. Actualizar tu Rama con Cambios de la Rama Principal

Para mantener tu rama actualizada con la rama principal (`main` o `master`):

```bash
git checkout main
git pull origin main
git checkout tu_nombre
git merge main
```

Resuelve cualquier conflicto si es necesario.

#### 7. Crear un Pull Request

Una vez que hayas terminado tu trabajo, puedes solicitar que tus cambios se fusionen con la rama principal. Esto se hace generalmente a través de la plataforma que estés usando (GitHub, GitLab, etc.).

### Usando GitKraken

#### 1. Clonar el Repositorio

- Abre GitKraken.
- Haz clic en **Clone a repo**.
- Ingresa la URL del repositorio y elige la ubicación en tu computadora.

#### 2. Crear y Cambiar a tu Rama Personal

- Haz clic en la rama principal (`main` o `master`).
- Selecciona **Checkout new branch**.
- Nombra la rama con tu nombre y confirma.

#### 3. Trabajar en tu Rama

Realiza los cambios necesarios en tu código.

#### 4. Hacer Commit de tus Cambios

- En GitKraken, verás los archivos modificados en la sección **Unstaged Files**.
- Haz clic en **Stage all changes** para prepararlos para el commit.
- Escribe un mensaje descriptivo en **Commit message** y haz clic en **Commit changes**.

#### 5. Subir tu Rama al Repositorio Remoto

- Haz clic en **Push** en la parte superior para enviar tus cambios al repositorio remoto.
- Si es una nueva rama, GitKraken te preguntará si deseas establecer el upstream; confirma para continuar.

#### 6. Actualizar tu Rama con Cambios de la Rama Principal

- Cambia a la rama principal haciendo doble clic en ella.
- Haz **Pull** para actualizarla con los últimos cambios.
- Vuelve a tu rama personal.
- Haz clic derecho en la rama principal y selecciona **Merge main into tu_nombre**.
- Resuelve conflictos si los hay y realiza un commit de la fusión.

#### 7. Crear un Pull Request

- Después de hacer push, GitKraken te ofrecerá la opción de crear un pull request.
- Sigue las instrucciones para completar el proceso en la plataforma correspondiente.

---

## Fusiones (Merge) y Pull Requests

### ¿Qué es un Merge?

Un **merge** es el proceso de tomar los cambios de una rama y aplicarlos a otra. Esto es esencial cuando múltiples desarrolladores trabajan en diferentes características simultáneamente.

### ¿Qué es un Pull Request?

Un **pull request** es una solicitud para que tus cambios sean revisados y fusionados en la rama principal. Permite la colaboración y revisión del código antes de que se integren los cambios.

### Realizar un Merge desde la Línea de Comandos

Si tienes permisos para fusionar directamente:

```bash
git checkout main
git merge tu_nombre
git push origin main
```

### Realizar un Merge desde GitKraken

- Cambia a la rama principal.
- Haz clic derecho en tu rama y selecciona **Merge tu_nombre into main**.
- Resuelve cualquier conflicto y realiza el commit.
- Haz **Push** para actualizar el repositorio remoto.

---

## Buenas Prácticas

- **Actualiza regularmente tu rama**: Fusiona los cambios de `main` a tu rama para minimizar conflictos.
- **Commits frecuentes**: Realiza commits pequeños y frecuentes para facilitar el seguimiento de cambios.
- **Comentarios claros**: Utiliza mensajes descriptivos en tus commits y pull requests.
- **Revisión de código**: Aprovecha los pull requests para recibir feedback y mejorar la calidad del código.

---

## Resumen

- Cada desarrollador trabaja en su propia rama nombrada según su nombre.
- Realiza cambios en tu rama y sube los cambios al repositorio remoto.
- Mantén tu rama actualizada fusionando regularmente los cambios de `main`.
- Usa pull requests para solicitar la fusión de tus cambios a la rama principal.
- Puedes realizar estas acciones tanto desde la línea de comandos como desde GitKraken.

---

