# 🌳 Estrategia de Branches - Devocionario San José

## 📋 Flujo de Trabajo

### **Branches Principales:**

#### **`main`** 🌟
- **Propósito**: Código de producción estable
- **Trigger**: Build automático + Release a Google Play
- **Protección**: Requiere PR + Review + Tests passing

#### **`develop`** 🔧
- **Propósito**: Integración de features para desarrollo
- **Trigger**: Build automático + Firebase App Distribution (Dev)
- **Protección**: Requiere PR + Review

#### **`release/*`** 🚀
- **Propósito**: Preparación de releases para QA
- **Ejemplo**: `release/v1.0.17`
- **Trigger**: Build automático + Firebase App Distribution (QA)

### **Branches de Feature:**

#### **`feature/*`** ✨
- **Propósito**: Desarrollo de nuevas funcionalidades
- **Ejemplo**: `feature/notifications`, `feature/splash-animation`
- **Merge**: A `develop` via PR

#### **`hotfix/*`** 🔥
- **Propósito**: Correcciones urgentes en producción
- **Ejemplo**: `hotfix/crash-fix`
- **Merge**: A `main` y `develop` via PR

## 🔄 Flujo de Desarrollo

### **1. Desarrollo de Features:**
```
feature/new-feature → develop → release/v1.0.17 → main
```

### **2. Hotfixes:**
```
hotfix/critical-fix → main → develop
```

### **3. Release Process:**
```
develop → release/v1.0.17 → QA Testing → main → Production
```

## 🚀 Entornos de Despliegue

### **Development (`develop`)**
- **Build**: APK Debug
- **Distribución**: Firebase App Distribution (Dev Testers)
- **Frecuencia**: Cada push
- **Objetivo**: Testing rápido de features

### **QA (`release/*`)**
- **Build**: AAB Release (Dev flavor)
- **Distribución**: Firebase App Distribution (QA Testers)
- **Frecuencia**: Al crear release branch
- **Objetivo**: Testing de integración

### **Production (`main`)**
- **Build**: AAB Release (Prod flavor)
- **Distribución**: Firebase App Distribution (Production Testers) + Google Play
- **Frecuencia**: Al merge a main
- **Objetivo**: Release final

## 📱 Grupos de Testers en Firebase

### **`dev-testers`**
- Desarrolladores
- Beta testers internos
- Testing de features nuevas

### **`qa-testers`**
- QA Team
- Beta testers externos
- Testing de integración

### **`production-testers`**
- Stakeholders
- Usuarios finales seleccionados
- Testing de release final

## 🔧 Comandos Útiles

### **Crear Feature Branch:**
```bash
git checkout develop
git pull origin develop
git checkout -b feature/nueva-funcionalidad
```

### **Crear Release Branch:**
```bash
git checkout develop
git pull origin develop
git checkout -b release/v1.0.17
git push origin release/v1.0.17
```

### **Crear Hotfix Branch:**
```bash
git checkout main
git pull origin main
git checkout -b hotfix/fix-critico
```

## 📊 Monitoreo

### **Métricas a Seguir:**
- Build success rate
- Test coverage
- Deployment frequency
- Lead time for changes
- Mean time to recovery

### **Notificaciones:**
- Slack/Teams para builds fallidos
- Email para releases de producción
- Firebase para distribución de apps
