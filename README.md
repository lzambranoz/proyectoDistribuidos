# proyectoDistribuidos

#### Integrantes:
  - Nicole Alvarez
  - Sunjing Lama
  - Lessette Zambrano

## Prueba de librerias para convertir

Desde la siguiente url [https://pdfbox.apache.org/download.cgi](https://pdfbox.apache.org/download.cgi) podemos descargar [pdfbox-app-2.0.17.jar](https://www-eu.apache.org/dist/pdfbox/2.0.17/pdfbox-app-2.0.17.jar), entre otras cosas esta librería nos va a permitir convertir archivos con extensión **.pdf** en imágenes **.jpg/png**

#### PDF2IMG
En Linux
Nos aseguramos que tengamos el siguiente archivo **"pdfbox-app-2.0.17.jar"** en la carpeta **./src/lib/**
Abriendo una consola en el directorio **./src/test_libs/pdf2img/**
Ejecutando el siguiente comando obtendremos la imagen como resultado de la conversión del archivo **.pdf**

```sh
$ java -jar ../../lib/pdfbox-app-2.0.17.jar PDFToImage [OPTIONS] <PDF file>`
```

| Parámetro | Descripción |
| ------ | ------ |
|**PDF file**| tiene que ser la ruta de un archivo con extensión **.pdf**|
|**[OPTIONS]**| **-imageType png/jpg** para el de formato de salida. Por defecto será jpg. [(más opciones)](https://pdfbox.apache.org/2.0/commandline.html#pdftoimage)|


#### DOCS2PDF
En Linux
Se requiere instalar la libreria **unoconv** 
para su instalación ejecutamos el siguiente comando 
 
```sh
$ sudo apt install unoconv`
```
Ejecutando el siguiente comando obtendremos un **pdf** con el mismo nombre como resultado de la conversión del archivo **.docx**

```sh
$ unoconv  <doc file>`
```

| Parámetro | Descripción |
| ------ | ------ |
|**doc file**| tiene que ser la ruta de un archivo con extensión **.docx**|

### ALOJAMIENTO DE SITIO WEB
Se realizó inicialmente un registro de dominio en AWS Route 53, para lazal-converter.com.
El sitio web fue escrito en html con hojas de estilo css, alojado en 2 buckets de S3. 

El archivo de inicio index.html y el contenido estático se encuentra en el bucket lazal-converter.com (el contenido de la página web es el mismo que existe en este repositorio dentro del directorio "webPage") y el bucket www.lazal-converter.com fue creado como un subdominio que se redireccionará al primero. 

Los endpoints respectivos de cada bucket son: http://www.lazal-converter.com.s3-website-sa-east-1.amazonaws.com y http://lazal-converter.com.s3-website-sa-east-1.amazonaws.com. Se eligió la zona de América del Sur(Sao Paulo) para reducir la latencia debido a la distancia geográfica de las consultas porque solo serán probadas en nuestra localidad.

Ya existe el acceso público al sitio web a partir de cualquiera de los dos alias lazal-converter.com y www.lazal-converter.com


