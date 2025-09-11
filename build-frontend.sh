#!/bin/bash
set -e

# 1. Ir al proyecto React
cd "$(dirname "$0")/frontend"

echo "👉 Construyendo React para producción..."
npm run build

# 2. Regresar a /app
cd ..

# 3. Definir rutas
APP_DIR="$(dirname "$0")"
DIST_DIR="$APP_DIR/frontend/dist"
RESOURCES_DIR="$APP_DIR/src/main/resources"
TEMPLATES_DIR="$RESOURCES_DIR/templates"

# 4. Crear carpetas si no existen
mkdir -p "$TEMPLATES_DIR"
mkdir -p "$RESOURCES_DIR/assets"

# 5. Copiar index.html y assets
echo "👉 Copiando archivos compilados..."
cp "$DIST_DIR/index.html" "$TEMPLATES_DIR/index.html"
rm -rf "$RESOURCES_DIR/assets"
cp -r "$DIST_DIR/assets" "$RESOURCES_DIR/"

# 6. Editar index.html para ajustar rutas
echo "👉 Corrigiendo rutas en index.html..."
sed -i 's|src="/assets/|src="../assets/|g' "$TEMPLATES_DIR/index.html"
sed -i 's|href="/assets/|href="../assets/|g' "$TEMPLATES_DIR/index.html"

echo "✅ Build y copia completados."
