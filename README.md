# Desktop
Instructivo de uso para la applicacion Desktop:

DESCARGAR EL CODIGO FUENTE DE LA APPLICACION: 

Para esto es necesario tener instalado el entorno de desarollo Eclipse. Una vez instalado ir a 
File -> Import -> Git -> Projects from Git -> Clone Uri -> https://github.com/ams2Proyecto1JAP/Desktop -> Next ->
seleccionar solo "pre" -> Next -> seleccionar CloneSubmodule -> Finish.

CREAR LA BASE DATOS: 

Para esto es necesario tener MySQL Server operativo en el sistema. Entrar en MySQL y crear una base de
datos con el comando -> create database <nombre>;

PASOS PREVIOS ANTES DE LANZAR LA APPLICACION:

En el explorador de archivos de eclipse ir a lib -> src/main/java -> duolingo.lib.hibernate.util -> HibernateUtil.java
Abrir el archivo y hacer la siguientes modificaciones:

- linea 25, enlace base de datos: sustituir con tus datos ip, puerto y nombreBaseDatos en "jdbc:mysql://ip:puerto/nombreBaseDatos?serverTimezone=UTC"

- linea 26, usuario: sustituir "user" en settings.put(Environment.USER, "user"); con el nombre de tu usuario de la base datos.

- linea 27, password: sustituir "password" en settings.put(Environment.PASS, "password"); con tu password de la base datos.

LANZAR LA APPLICACION: 

En el explorador de archivos de eclipse ir a Duolingo_Desktop -> src -> main -> Interface.java.
Abrir el archvio y ejecutar el programa (Run Interface). La aplicaion esta lista para usarse. 
