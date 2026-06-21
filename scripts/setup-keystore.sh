#!/bin/bash

# Script para configurar keystore para CI/CD
# Ejecutar este script para generar el keystore y obtener el base64

echo "🔐 Configuración de Keystore para CI/CD"
echo "======================================="

# Verificar si ya existe un keystore
if [ -f "keystore.jks" ]; then
    echo "⚠️  Ya existe un keystore.jks"
    echo "¿Deseas usar el existente o crear uno nuevo?"
    read -p "1) Usar existente, 2) Crear nuevo [1/2]: " choice
    
    if [ "$choice" = "2" ]; then
        echo "🗑️  Eliminando keystore existente..."
        rm keystore.jks
    else
        echo "✅ Usando keystore existente"
        KEYSTORE_PATH="./keystore.jks"
    fi
fi

# Crear nuevo keystore si no existe
if [ ! -f "keystore.jks" ]; then
    echo ""
    echo "📝 Creando nuevo keystore..."
    echo "Por favor, proporciona la siguiente información:"
    
    read -p "Alias de la clave [devocionario-key]: " KEY_ALIAS
    KEY_ALIAS=${KEY_ALIAS:-devocionario-key}
    
    read -s -p "Contraseña del keystore: " KEYSTORE_PASSWORD
    echo ""
    
    read -s -p "Contraseña de la clave: " KEY_PASSWORD
    echo ""
    
    read -p "Nombre completo [Devocionario San Jose]: " FULL_NAME
    FULL_NAME=${FULL_NAME:-"Devocionario San Jose"}
    
    read -p "Organización [AlphaZeta]: " ORGANIZATION
    ORGANIZATION=${ORGANIZATION:-AlphaZeta}
    
    read -p "Ciudad [Buenos Aires]: " CITY
    CITY=${CITY:-Buenos Aires}
    
    read -p "Estado/Provincia [Buenos Aires]: " STATE
    STATE=${STATE:-Buenos Aires}
    
    read -p "País [AR]: " COUNTRY
    COUNTRY=${COUNTRY:-AR}
    
    echo ""
    echo "🔨 Generando keystore..."
    
    keytool -genkey -v -keystore keystore.jks \
        -alias "$KEY_ALIAS" \
        -keyalg RSA -keysize 2048 -validity 10000 \
        -storepass "$KEYSTORE_PASSWORD" \
        -keypass "$KEY_PASSWORD" \
        -dname "CN=$FULL_NAME, OU=Development, O=$ORGANIZATION, L=$CITY, S=$STATE, C=$COUNTRY"
    
    KEYSTORE_PATH="./keystore.jks"
    
    echo "✅ Keystore creado exitosamente!"
else
    # Si existe, pedir las contraseñas
    read -s -p "Contraseña del keystore existente: " KEYSTORE_PASSWORD
    echo ""
    
    read -s -p "Contraseña de la clave: " KEY_PASSWORD
    echo ""
    
    read -p "Alias de la clave [devocionario-key]: " KEY_ALIAS
    KEY_ALIAS=${KEY_ALIAS:-devocionario-key}
fi

echo ""
echo "📋 Información para GitHub Secrets:"
echo "=================================="
echo ""

# Generar base64 del keystore
echo "KEYSTORE_BASE64:"
echo "$(base64 -i "$KEYSTORE_PATH" | tr -d '\n')"
echo ""

echo "KEYSTORE_PASSWORD:"
echo "$KEYSTORE_PASSWORD"
echo ""

echo "KEY_ALIAS:"
echo "$KEY_ALIAS"
echo ""

echo "KEY_PASSWORD:"
echo "$KEY_PASSWORD"
echo ""

echo "🔒 IMPORTANTE:"
echo "- Guarda estos valores de forma segura"
echo "- NO los compartas públicamente"
echo "- Agrégalos como secrets en GitHub"
echo "- El keystore.jks debe mantenerse privado"
echo ""

echo "📁 Ubicación del keystore: $KEYSTORE_PATH"
echo ""

# Crear archivo de configuración local (no commitear)
cat > local.properties << EOF
# Configuración local para desarrollo
# NO COMMITEAR ESTE ARCHIVO

# Keystore
keystore.file=$KEYSTORE_PATH
keystore.password=$KEYSTORE_PASSWORD
key.alias=$KEY_ALIAS
key.password=$KEY_PASSWORD
EOF

echo "📝 Archivo local.properties creado"
echo "✅ Configuración completada!"
