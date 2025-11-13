# Tutorial: Como Rodar um Aplicativo Android Usando Apenas Linha de Comando

## 📌 Introdução
Este guia explica passo a passo como compilar, montar, assinar e instalar um aplicativo Android **usando apenas ferramentas de linha de comando**: `aapt`, `javac`, `d8`, `zipalign`, `apksigner` e `adb`.

Os comandos são baseados nos utilizados na prática AP03.

---

## 🧩 1. Pré-requisitos

### **Ferramentas necessárias**
- Java Development Kit (JDK 17)
- Android Command-Line Tools
- Android SDK + Build-tools
- USB Debugging ativado no celular (ou AVD)

### **Instalar o JDK**
```bash
sudo apt install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

---

## 🧩 2. Instalar as ferramentas do Android

### **Baixar e instalar o Command-Line Tools**
```bash
mkdir -p ~/android_sdk/cmdline-tools
cd ~/android_sdk/cmdline-tools

curl -LO https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
unzip commandlinetools-linux-11076708_latest.zip -d latest
```

### **Configurar variáveis**
```bash
export ANDROID_SDK_ROOT=$HOME/android_sdk
export PATH=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$PATH
```

### **Instalar plataformas e ferramentas**
```bash
sdkmanager --sdk_root=$ANDROID_SDK_ROOT --install "platforms;android-34" "build-tools;34.0.0" "platform-tools"

sdkmanager --licenses
```

---

## 🧩 3. Estrutura mínima do projeto

```
myapp/
 ├── AndroidManifest.xml
 ├── res/
 │   └── layout/activity_main.xml
 └── java/net/hanshq/hello/MainActivity.java
```

---

## 🧩 4. Configurar caminhos das ferramentas

```bash
export ANDROID_HOME=$HOME/Android/Sdk
export SDK=$ANDROID_HOME
export BUILD_TOOLS=$SDK/build-tools/34.0.0
export PLATFORM=$SDK/platforms/android-34
export PATH=$BUILD_TOOLS:$SDK/platform-tools:$PATH
```

---

## 🧩 5. Criar diretórios de build

```bash
mkdir -p build/gen build/obj build/apk
```

---

## 🧩 6. Gerar o arquivo R.java

```bash
$BUILD_TOOLS/aapt package -f -m -J build/gen     -S res     -M AndroidManifest.xml     -I $PLATFORM/android.jar
```

---

## 🧩 7. Compilar os arquivos Java

```bash
javac --release 11 -classpath $PLATFORM/android.jar -d build/obj     build/gen/net/hanshq/hello/R.java     java/net/hanshq/hello/MainActivity.java
```

---

## 🧩 8. Converter .class para .dex (D8)

Se necessário, habilite globstar:
```bash
shopt -s globstar
```

Converter:
```bash
$BUILD_TOOLS/d8 --release --lib $PLATFORM/android.jar     --output build/apk build/obj/**/*.class
```

---

## 🧩 9. Criar o APK não assinado

```bash
$BUILD_TOOLS/aapt package -f     -M AndroidManifest.xml     -S res     -I $PLATFORM/android.jar     -F build/Hello.unsigned.apk build/apk/
```

---

## 🧩 10. Criar keystore (somente uma vez)

```bash
keytool -genkeypair -keystore keystore.jks -alias androidkey   -validity 10000 -keyalg RSA -keysize 2048   -storepass android -keypass android
```

---

## 🧩 11. Alinhar o APK

```bash
$BUILD_TOOLS/zipalign -f -p 4 build/Hello.unsigned.apk build/Hello.aligned.apk
```

---

## 🧩 12. Assinar o APK

```bash
$BUILD_TOOLS/apksigner sign --ks keystore.jks   --ks-key-alias androidkey --ks-pass pass:android   --key-pass pass:android --out build/Hello.apk   build/Hello.aligned.apk
```

---

## 🧩 13. Instalar no dispositivo Android

### Verificar se o dispositivo está conectado
```bash
$SDK/platform-tools/adb devices
```

### Instalar o APK
```bash
$SDK/platform-tools/adb install -r build/Hello.apk
```

### Iniciar o app manualmente via ADB
```bash
$SDK/platform-tools/adb shell am start -n net.hanshq.hello/.MainActivity
```

---

## 🎉 Conclusão
Com esse procedimento, você consegue desenvolver, compilar, montar, assinar e instalar um app Android **completamente sem Android Studio**, usando apenas terminal e ferramentas oficiais.

---

## 📥 Download do arquivo
O arquivo `tutorial_linha_de_comando_android.md` contém este tutorial completo.
