# Flat World Mod — Minecraft 1.21.11 (Fabric)

## Cara 1: Build otomatis via GitHub (PALING MUDAH)

1. Buat akun GitHub di https://github.com (gratis)
2. Buat repository baru (klik tombol "+", lalu "New repository")
3. Upload semua isi folder ini ke repository tersebut
4. Klik tab **Actions** di repository kamu
5. Klik workflow **"Build Flat World Mod"** → klik **"Run workflow"**
6. Tunggu ~5 menit sampai selesai
7. Klik hasil build → scroll ke bawah → download **"flatworld-mod-jar"**
8. Ekstrak ZIP → dapat file `flatworld-1.0.0.jar`

---

## Cara 2: Build sendiri di PC

### Yang dibutuhkan:
- **Java 21** — download dari https://adoptium.net (pilih "Temurin 21")
- Koneksi internet

### Langkah:
1. Ekstrak folder ini ke PC kamu
2. Buka Terminal (Mac/Linux) atau Command Prompt (Windows)
3. Masuk ke folder mod:
   ```
   cd flatworld-mod
   ```
4. Jalankan build:
   - **Linux / Mac:** `./gradlew build`
   - **Windows:** `gradlew.bat build`
5. Tunggu 5-10 menit (download Fabric + Minecraft pertama kali)
6. JAR ada di: `build/libs/flatworld-1.0.0.jar`

---

## Cara Install di Server

1. Pastikan server pakai **Fabric Server 1.21.11**
   - Download: https://fabricmc.net/use/server/

2. Copy `flatworld-1.0.0.jar` ke folder `mods/` di server

3. Pastikan `server.properties`:
   ```
   level-type=minecraft:normal
   generator-settings={}
   ```

4. **PENTING:** Install mod SEBELUM world pertama dibuat!
   - Kalau world sudah ada, chunk lama tidak berubah
   - Hanya chunk BARU yang akan datar

5. Start server → dunia akan datar!

---

## Apa yang mod ini lakukan

- ✅ Permukaan tanah rata di Y=64
- ✅ Semua biome tetap ada (plains, desert, ocean, jungle, dll.)
- ✅ Semua struktur tetap ada (village, stronghold, mineshaft, dll.)
- ✅ Semua ore tetap ada di bawah tanah
- ✅ Air laut, sungai, danau tetap ada
- ✅ Tidak perlu Fabric API — hanya butuh Fabric Loader
