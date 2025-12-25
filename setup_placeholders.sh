#!/bin/bash

BASE="src/main/resources/assets/dragonblockinfinity/textures/entity/race/base"

mkdir -p \
"$BASE/hair" \
"$BASE/eyes" \
"$BASE/nose" \
"$BASE/mouth"

# Hair
touch \
"$BASE/hair/hair_00.png" \
"$BASE/hair/hair_01.png" \
"$BASE/hair/hair_02.png" \
"$BASE/hair/hair_03.png"

# Eyes
touch \
"$BASE/eyes/eyes_00.png" \
"$BASE/eyes/eyes_01.png" \
"$BASE/eyes/eyes_02.png" \
"$BASE/eyes/eyes_03.png"

# Nose
touch \
"$BASE/nose/nose_00.png" \
"$BASE/nose/nose_01.png" \
"$BASE/nose/nose_02.png"

# Mouth
touch \
"$BASE/mouth/mouth_00.png" \
"$BASE/mouth/mouth_01.png" \
"$BASE/mouth/mouth_02.png"

echo "Placeholders criados com sucesso."
