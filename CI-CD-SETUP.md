# 🚀 CI/CD Setup - Devocionario San José

## 📋 Resumen

Pipeline completo de CI/CD con GitHub Actions y Firebase App Distribution para automatizar el desarrollo, testing y despliegue de la aplicación.

## 🏗️ Arquitectura del Pipeline

```
┌─────────────┐    ┌──────────────┐    ┌─────────────┐
│   Feature   │───▶│   Develop    │───▶│    QA       │
│   Branch    │    │   Branch     │    │   Branch    │
└─────────────┘    └──────────────┘    └─────────────┘
                           │                    │
                           ▼                    ▼
                    ┌──────────────┐    ┌─────────────┐
                    │ Dev Build    │    │ QA Build    │
                    │ (APK Debug)  │    │ (AAB Release)│
                    └──────────────┘    └─────────────┘
                           │                    │
                           ▼                    ▼
                    ┌──────────────┐    ┌─────────────┐
                    │ Firebase     │    │ Firebase    │
                    │ Dev Testers  │    │ QA Testers  │
                    └──────────────┘    └─────────────┘
                                                 │
                                                 ▼
                                          ┌─────────────┐
                                          │ Production  │
                                          │   Branch    │
                                          └─────────────┘
                                                 │
                                                 ▼
                                          ┌─────────────┐
                                          │Prod Build   │
                                          │(AAB Release)│
                                          └─────────────┘
                                                 │
                                                 ▼
                                          ┌─────────────┐
                                          │ Firebase    │
                                          │Prod Testers │
                                          └─────────────┘
```

## 🔧 Configuración Inicial

### **1. Secrets de GitHub**

Configura estos secrets en GitHub → Settings → Secrets and variables → Actions:

```bash
# Firebase
FIREBASE_TOKEN=tu_firebase_token
FIREBASE_APP_ID_DEV=com.alphazetakapp.stjosephappofficial.dev
FIREBASE_APP_ID_PROD=com.alphazetakapp.stjosephappofficial

# Keystore
KEYSTORE_BASE64=base64_del_keystore
KEYSTORE_PASSWORD=contraseña_del_keystore
KEY_ALIAS=alias_de_la_clave
KEY_PASSWORD=contraseña_de_la_clave
```

### **2. Firebase App Distribution**

1. Habilita App Distribution en Firebase Console
2. Crea grupos de testers:
   - `dev-testers` - Desarrolladores y beta testers internos
   - `qa-testers` - QA team y beta testers externos
   - `production-testers` - Stakeholders y usuarios finales

### **3. Configurar Keystore**

```bash
# Ejecutar script de configuración
./scripts/setup-keystore.sh
```

## 🌳 Estrategia de Branches

### **Branches Principales:**
- **`main`** - Producción estable
- **`develop`** - Integración de features
- **`release/*`** - Preparación de releases

### **Branches de Desarrollo:**
- **`feature/*`** - Nuevas funcionalidades
- **`hotfix/*`** - Correcciones urgentes

## 🔄 Flujo de Trabajo

### **Desarrollo de Features:**
1. Crear branch desde `develop`
2. Desarrollar feature
3. Crear PR a `develop`
4. Merge automático → Build Dev → Firebase Distribution

### **Release Process:**
1. Crear `release/v1.0.17` desde `develop`
2. Build automático → QA Testing
3. Aprobación → Merge a `main`
4. Build automático → Production Release

### **Hotfixes:**
1. Crear `hotfix/fix-critico` desde `main`
2. Desarrollo y testing
3. Merge a `main` y `develop`
4. Build automático → Production Release

## 📱 Entornos de Despliegue

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
- **Distribución**: Firebase App Distribution (Production Testers)
- **Frecuencia**: Al merge a main
- **Objetivo**: Release final

## 🚀 Comandos Útiles

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

### **Release Manual:**
1. Ve a GitHub → Actions → Manual Release
2. Selecciona entorno (dev/qa/prod)
3. Especifica versión y notas
4. Ejecuta workflow

## 📊 Monitoreo y Métricas

### **Métricas de Pipeline:**
- Build success rate
- Deployment frequency
- Lead time for changes
- Mean time to recovery

### **Métricas de App:**
- Crash rate
- ANR rate
- User retention
- Feature adoption

## 🔍 Troubleshooting

### **Build Failures:**
1. Revisar logs en GitHub Actions
2. Verificar secrets de GitHub
3. Validar configuración de Firebase
4. Comprobar keystore y firma

### **Distribution Issues:**
1. Verificar Firebase App Distribution
2. Comprobar grupos de testers
3. Validar App IDs
4. Revisar permisos de Firebase

### **Common Issues:**
- **Keystore not found**: Verificar KEYSTORE_BASE64
- **Firebase auth failed**: Verificar FIREBASE_TOKEN
- **Build timeout**: Aumentar timeout en workflow
- **Permission denied**: Verificar permisos de Firebase

## 📚 Recursos Adicionales

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Firebase App Distribution](https://firebase.google.com/docs/app-distribution)
- [Android CI/CD Best Practices](https://developer.android.com/studio/build/build-variants)

## 🎯 Próximos Pasos

1. ✅ Configurar secrets de GitHub
2. ✅ Configurar Firebase App Distribution
3. ✅ Crear keystore y configurar firma
4. ✅ Probar pipeline con feature branch
5. ✅ Configurar notificaciones (Slack/Email)
6. ✅ Implementar testing automático
7. ✅ Configurar métricas y monitoreo
