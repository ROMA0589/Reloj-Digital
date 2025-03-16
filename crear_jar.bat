@echo off
echo Creando JAR ejecutable...
javac Reloj.java
jar cfm RelojDigital.jar MANIFEST.MF *.class
del *.class
echo JAR creado con exito!
pause
