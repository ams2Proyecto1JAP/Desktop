
#INSTRUCCIONES DE INSTALACIÓN Y USO DE DESKTOP#

-----------------------------------------------------
****DESCARGAR EL CODIGO FUENTE DE LA APPLICACION****:
-----------------------------------------------------

Para poder utilizar esta release v4.0 no se puede descargar del propio .zip que GitHub ofrece, ya que este no incluye el submodule y sin él no puede funcionar la aplicación.
· La manera de descargar el proyecto correctamente es:
  1- Ir a la carpeta de tu sistema donde quieras almacenar el proyecto, y abrir una consola que permita ejecutar Git.
  2- Ejecutar "git clone --recurse-submodules https://github.com/ams2Proyecto1JAP/Desktop/"
  3- Ejecutar cd "Desktop"
  4- Ejecutar "git checkout v4.0"
  
· Para abrir el proyecto desde Eclipse:
  1- File --> Import --> Git --> Projects From Git --> Existing Local Repository --> Add --> Browse --> Seleccionar carpeta local "\Desktop" donde se haya descargado anteriormente el proyecto --> Finish
  2- Seleccionar el repositorio local añadido en el paso anterior --> NEXT --> (Por defecto: Import existing Eclipse Projects) NEXT --> (Por defecto: Seleccionar tanto "lib" como "Duolingo_Desktop") FINISH

**El submodulo LIB debería resolver sus dependencias automáticamente al pasar un rato debido a que es un Maven project, en caso de que esto no ocurra se puede forzar a actualizar con los siguientes pasos:
  1- Click derecho en el proyecto LIB --> Maven --> Update Project --> OK 
  2- Al updatear Maven se cambia el JRE System Library al J2SE1.5, hay que cambiarlo a 1.8:
    Click derecho en LIB --> Build Path --> Configure Build Path --> Libraries --> SELECCIONAR: JRE System Library --> Edit --> Cambiar Execution Enviroment a JavaSE 1.8


----------------------------
****AÑADIR DEPENDENCIAS****:
----------------------------

Es necesario añadir las dependencias de JSON y de lipeRMI para poder ejecutar el proyecto, para ello es necesario tenerlas descargadas en el sistema con los siguientes links: 

lipermi-0.4.jar --> https://sourceforge.net/projects/lipermi/files/lipermi/lipermi-0.4/lipermi-0.4.jar/download
json20200518.jar --> https://jar-download.com/artifacts/org.json/json/20200518/source-code --> Click en "Download json (20200518)"

Una vez tengas ambas dependencias en local, desde Eclipse hacer click derecho en el proyecto Duolingo_Desktop --> Build Path --> Configure Build Path --> Libraries --> Classpath --> sustituir las rutas de los .jar haciendo doble click en ellos y seleccionando el lugar donde estén en tu sistema



----------------------------
****CREAR LA BASE DATOS****:
----------------------------

Para esto es necesario tener MySQL Server operativo en el sistema. Entrar en MySQL y crear una base de datos con el comando -> create database "nombre";



-----------------------------------------------------
****PASOS PREVIOS ANTES DE LANZAR LA APPLICACION****:
-----------------------------------------------------

En el explorador de proyecto de eclipse ir al proyecto lib -> (carpeta) src/main/java -> (paquete) duolingo.lib.hibernate.util -> (Archivo) "HibernateUtil.java" --> Abrir el archivo y hacer la siguientes modificaciones:

linea 25, [enlace base de datos]: sustituir con tus datos ip, puerto y nombreBaseDatos en "jdbc:mysql://ip:puerto/nombreBaseDatos?serverTimezone=UTC"

linea 26, [usuario]: sustituir "user" en settings.put(Environment.USER, "user"); con el nombre de tu usuario de la base datos.

linea 27, [password]: sustituir "password" en settings.put(Environment.PASS, "password"); con tu password de la base datos.

Guardar el fichero al finalizar.



------------------------------
****LANZAR LA APPLICACION****:
------------------------------

En el explorador de archivos de eclipse ir a Duolingo_Desktop -> src -> main -> Interface.java. Abrir el archvio y ejecutar el programa (Run Interface). La aplicacion esta lista para usarse.
