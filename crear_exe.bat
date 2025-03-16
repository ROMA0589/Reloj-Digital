@echo off
echo Creando ejecutable con icono personalizado...
javac Reloj.java
jar cfm RelojDigital.jar MANIFEST.MF *.class
del *.class
"C:\Program Files (x86)\Launch4j\launch4jc.exe" config.xml
echo Ejecutable creado con exito!
pause
