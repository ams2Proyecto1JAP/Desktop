
#INSTRUCCIONES DE INSTALACIÓN Y USO DE DESKTOP#

-----------------------------------------------------
****DESCARGAR EL CODIGO FUENTE DE LA APPLICACION****:
-----------------------------------------------------

Para poder utilizar esta release v2.0 no se puede descargar del propio .zip que GitHub ofrece, ya que este no incluye el submodule y sin él no puede funcionar la aplicación.<br>
· La manera de descargar el proyecto correctamente es:<br>
  &nbsp;&nbsp;&nbsp;&nbsp;1- Ir a la carpeta de tu sistema donde quieras almacenar el proyecto, y abrir una consola que permita ejecutar Git.<br>
  &nbsp;&nbsp;&nbsp;&nbsp;2- Ejecutar "git clone --recurse-submodules https://github.com/ams2Proyecto1JAP/Desktop/"<br>
  &nbsp;&nbsp;&nbsp;&nbsp;3- Ejecutar cd "Desktop"<br>
  &nbsp;&nbsp;&nbsp;&nbsp;4- Ejecutar "git checkout v2.0"<br>
  
· Para abrir el proyecto desde Eclipse:<br>
  &nbsp;&nbsp;&nbsp;&nbsp;1- File --> Import --> Git --> Projects From Git --> Existing Local Repository --> Add --> Browse --> Seleccionar carpeta local "\Desktop" donde se haya descargado anteriormente el proyecto --> Finish<br>
  &nbsp;&nbsp;&nbsp;&nbsp;2- Seleccionar el repositorio local añadido en el paso anterior --> NEXT --> (Por defecto: Import existing Eclipse Projects) NEXT --> (Por defecto: Seleccionar tanto "lib" como "Duolingo_Desktop") FINISH<br>

**El submodulo LIB debería resolver sus dependencias automáticamente al pasar un rato debido a que es un Maven project, en caso de que esto no ocurra se puede forzar a actualizar con los siguientes pasos:<br>
  &nbsp;&nbsp;&nbsp;&nbsp;1- Click derecho en el proyecto LIB --> Maven --> Update Project --> OK <br>
  &nbsp;&nbsp;&nbsp;&nbsp;2- Al updatear Maven se cambia el JRE System Library al J2SE1.5, hay que cambiarlo a 1.8:<br>
    Click derecho en LIB --> Build Path --> Configure Build Path --> Libraries --> SELECCIONAR: JRE System Library --> Edit --> Cambiar Execution Enviroment a JavaSE 1.8<br>


----------------------------
****CREAR LA BASE DATOS****:
----------------------------

Para esto es necesario tener MySQL Server operativo en el sistema. Entrar en MySQL y crear una base de datos con el comando -> create database "nombre"; <br>



-----------------------------------------------------
****PASOS PREVIOS ANTES DE LANZAR LA APPLICACION****:
-----------------------------------------------------

En el explorador de proyecto de eclipse ir al proyecto lib -> (carpeta) src/main/java -> (paquete) duolingo.lib.hibernate.util -> (Archivo) "HibernateUtil.java" --> Abrir el archivo y hacer la siguientes modificaciones: <br>

&nbsp;&nbsp;&nbsp;&nbsp;linea 25, [enlace base de datos]: sustituir con tus datos ip, puerto y nombreBaseDatos en "jdbc:mysql://ip:puerto/nombreBaseDatos?serverTimezone=UTC" <br>

&nbsp;&nbsp;&nbsp;&nbsp;linea 26, [usuario]: sustituir "user" en settings.put(Environment.USER, "user"); con el nombre de tu usuario de la base datos. <br>

&nbsp;&nbsp;&nbsp;&nbsp;linea 27, [password]: sustituir "password" en settings.put(Environment.PASS, "password"); con tu password de la base datos. <br>
 
Guardar el fichero al finalizar. <br>



------------------------------
****LANZAR LA APPLICACION****:
------------------------------

En el explorador de archivos de eclipse ir a Duolingo_Desktop -> src -> main -> Interface.java. Abrir el archvio y ejecutar el programa (Run Interface). La aplicacion esta lista para usarse.
