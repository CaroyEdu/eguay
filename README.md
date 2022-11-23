# eguay

### ¿Qué es eguay?

eguay es una aplicación que busca reunir a vendedores y compradores en una misma plataforma.

### Roles

- Vendedor:
    - Podrá publicar los productos que vende que, como mínimo, contarán con la siguiente información: título, descripción, precio de salida, URL de la foto, categoría/s del producto, etc.  
    - Podrá realizar un CRUD de todos los productos que venda o que haya vendido. Cuando se autentique en el sistema, lo primero que se le mostrarán son todos los productos ordenados en orden inverso y podrá realizar búsquedas y filtrados sobre los mismos.
    - Podrá registrarse a través de la aplicación. Deberá indicar información personal como nombre, apellidos, domicilio, ciudad de residencia, edad, sexo, etc. 
    - Deberá gestionar el proceso de subasta de los productos: desde su apertura hasta las condiciones de cierre y asignación del producto vendido al comprador que corresponda.

- Administrador del sistema: 
    - Podrá realizar un CRUD sobre todos los usuarios y también de los diferentes productos. Podrá visualizar listados de los usuarios y los productos, así como realizar filtrados y búsquedas sobre ellos. Dará de alta (o asignará los permisos correspondientes) a los usuarios de marketing y a los analistas.
    - También podrá gestionar (CRUD) las diferentes categorías de productos existentes en la plataforma web. Podrá realizar un CRUD sobre todos los usuarios y también de los diferentes productos. 

- Comprador:
    - Podrá registrarse a través de la aplicación. Deberá indicar información personal como nombre, apellidos, domicilio, ciudad de residencia, edad, sexo, categorías preferidas, etc. 
    - Podrá realizar búsquedas de productos, marcar favoritos para hacer seguimientos, pujar por productos, etc. Se le notificará cuándo se ha cerrado el proceso de puja y si si finalmente se queda con el producto.
    - Podrá realizar un CRUD de los productos que ha comprado o que sigue como favoritos, así como listarlos y filtrarlos.
- Marketing: 
    - Empleados de la plataforma que puede crear y gestionar (editar, borrar, etc.) listas de usuarios compradores a partir de una búsqueda, en función de diversos criterios. Podrán también enviarles notificaciones a todos los compradores de una lista sobre promociones de ciertos artículos, etc.
        Deberán también gestionar la recepción de los mensajes en la bandeja de entrada de mensajes de los usuarios compradores.

### Convenciones

- Notación Camel.
    - Comenzando por **minúsculas** para nombres de paquete, métodos, atributos y variables. 
    - Comenzando por **mayúsculas** para nombres de clases y Ficheros. Ej.  Customer.java: class Customer{...}
- Mayúscula separado por barrabaja para constantes.
- Cada fichero en su capa correspondiente. 
    - Capas: 
        - Vistas (HTML y JSP)
        - Controladores (Servlets)
        - Modelo (Clases java de lógica de la aplicación)
        - Mapeo. EJB (Operaciones CRUD)

### Características
Aplicación web creada en Java y usando un servidor GlassFish.

### Instalación

Abrir el proyecto en un IDE estilo IntelliJ o NetBeans.
