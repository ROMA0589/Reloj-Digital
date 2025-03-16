@echo off
echo Compilando y ejecutando el Reloj Digital...
javac Reloj.java
if %errorlevel% equ 0 (
    start javaw Reloj
) else (
    echo Error al compilar
    pause
)
