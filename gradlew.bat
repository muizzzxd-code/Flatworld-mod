@rem Gradle wrapper script untuk Windows
@echo off
setlocal

set DIRNAME=%~dp0
set GRADLE_WRAPPER_JAR=%DIRNAME%\gradle\wrapper\gradle-wrapper.jar

java -classpath "%GRADLE_WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
