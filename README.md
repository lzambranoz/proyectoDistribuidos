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
|**<PDF file>**| tiene que ser la ruta de un archivo con extensión **.pdf**|
|**[OPTIONS]**| **-imageType png/jpg** para el de formato de salida. Por defecto será jpg. [(más opciones)](https://pdfbox.apache.org/2.0/commandline.html#pdftoimage)|