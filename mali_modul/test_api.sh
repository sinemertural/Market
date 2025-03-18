#!/bin/bash

BASE_URL="http://localhost:8080/api/hareketler"
MALI_DURUM_URL="http://localhost:8080/api/mali-durum"

# Tüm hareketleri getir
curl -X GET "$BASE_URL" -H "Content-Type: application/json"

echo -e "\n------------------------------\n"

# Belirli bir hareketi getir (örnek id: 1)
curl -X GET "$BASE_URL/1" -H "Content-Type: application/json"

echo -e "\n------------------------------\n"

# Yeni bir hareket oluştur
curl -X POST "$BASE_URL" \
     -H "Content-Type: application/json" \
     -d '{"id": 2, "tarih": "2025-03-18", "tutar": 1000, "aciklama": "Örnek hareket", "hareket_tipi":"gelir"}'

echo -e "\n------------------------------\n"

# Belirli bir hareketi sil (örnek id: 2)
curl -X DELETE "$BASE_URL/2" -H "Content-Type: application/json"

echo -e "\n------------------------------\n"

# Mali durumu getir
curl -X GET "$MALI_DURUM_URL" -H "Content-Type: application/json"

echo -e "\n------------------------------\n"
