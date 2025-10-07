#!/bin/bash

echo "🔑 Obteniendo Firebase Token para App Distribution"
echo "================================================="
echo ""

echo "📋 Pasos para obtener el Firebase Token:"
echo ""

echo "1. Ve a Firebase Console → Tu proyecto → Configuración del proyecto"
echo "2. Ve a la pestaña 'Cuentas de servicio'"
echo "3. En la sección 'Firebase Admin SDK', haz clic en 'Generar nueva clave privada'"
echo "4. Descarga el archivo JSON"
echo "5. Instala Firebase CLI si no lo tienes:"
echo "   npm install -g firebase-tools"
echo ""
echo "6. Ejecuta estos comandos:"
echo "   firebase login"
echo "   firebase projects:list"
echo "   firebase use stjosephappofficial"
echo ""
echo "7. Para obtener el token:"
echo "   firebase appdistribution:testers:list"
echo ""

echo "🔐 O alternativamente, puedes usar:"
echo ""
echo "1. Ve a Firebase Console → App Distribution"
echo "2. Si no está habilitado, habilítalo"
echo "3. Ve a 'Testers and groups'"
echo "4. El token se genera automáticamente cuando usas la CLI"
echo ""

echo "📝 Una vez que tengas el token, agrégalo como secret en GitHub:"
echo "   FIREBASE_TOKEN=tu_token_aqui"
echo ""

echo "⚠️  IMPORTANTE:"
echo "- El token debe tener permisos de App Distribution"
echo "- Mantén el token privado y seguro"
echo "- No lo compartas públicamente"
echo ""

read -p "¿Tienes Firebase CLI instalado? (y/n): " has_cli

if [ "$has_cli" = "y" ] || [ "$has_cli" = "Y" ]; then
    echo ""
    echo "🚀 Ejecutando comandos de Firebase..."
    
    echo "1. Verificando autenticación..."
    firebase login --no-localhost
    
    echo "2. Listando proyectos..."
    firebase projects:list
    
    echo "3. Configurando proyecto..."
    firebase use stjosephappofficial
    
    echo "4. Verificando App Distribution..."
    firebase appdistribution:testers:list
    
    echo ""
    echo "✅ Si todo funcionó, ya tienes el token configurado"
else
    echo ""
    echo "📦 Instala Firebase CLI primero:"
    echo "npm install -g firebase-tools"
    echo ""
    echo "Luego ejecuta este script nuevamente"
fi
