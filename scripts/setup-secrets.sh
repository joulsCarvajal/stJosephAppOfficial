#!/bin/bash

# Script para configurar secrets de GitHub
# Ejecutar este script para obtener los valores necesarios

echo "🔐 Configuración de Secrets para GitHub Actions"
echo "================================================"

echo ""
echo "📋 Secrets necesarios para GitHub:"
echo ""
echo "1. FIREBASE_TOKEN"
echo "   - Ve a Firebase Console → Project Settings → Service Accounts"
echo "   - Genera nueva clave privada"
echo "   - Copia el token generado"
echo ""

echo "2. FIREBASE_APP_ID_DEV"
echo "   - Ve a Firebase Console → Project Settings → General"
echo "   - Copia el App ID para el flavor DEV"
echo ""

echo "3. FIREBASE_APP_ID_PROD"
echo "   - Ve a Firebase Console → Project Settings → General"
echo "   - Copia el App ID para el flavor PROD"
echo ""

echo "4. KEYSTORE_BASE64"
echo "   - Convierte tu keystore a base64:"
echo "   base64 -i path/to/your/keystore.jks"
echo ""

echo "5. KEYSTORE_PASSWORD"
echo "   - Contraseña de tu keystore"
echo ""

echo "6. KEY_ALIAS"
echo "   - Alias de tu clave de firma"
echo ""

echo "7. KEY_PASSWORD"
echo "   - Contraseña de tu clave de firma"
echo ""

echo "🚀 Para configurar en GitHub:"
echo "1. Ve a tu repositorio → Settings → Secrets and variables → Actions"
echo "2. Agrega cada secret con su valor correspondiente"
echo ""

echo "✅ Una vez configurados todos los secrets, el pipeline funcionará automáticamente"
