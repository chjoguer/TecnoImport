CREATE DATABASE TecnoImportDB;
USE TecnoImportDB;
#DROP DATABASE  TecnoImportDB;
CREATE TABLE Login (
	 IDUSUARIO INT,
     USUARIO   VARCHAR(30),
	 CLAVE 	   VARCHAR(30),
     TIPO 	   VARCHAR(1) # 'g' gerente 'v' vendedor 'j' jefeBodega
);

CREATE TABLE Locall(
	IDLOCAL 	INT PRIMARY KEY,
    NOMBRE 		VARCHAR(50),
    DIRECCION  	VARCHAR(50),
    CIUDAD		VARCHAR(50),
    PAIS		VARCHAR(50),
	esMATRIZ	VARCHAR(1)		# m= es Matriz  l = es Local 
);

CREATE TABLE Producto(
	IDPRODUCTO 	INT PRIMARY KEY,
    NOMBRE 		VARCHAR(50),
    CANTIDADSTOCK INT,
    DESCRIPCION VARCHAR(200),
    CATEGORIA 	VARCHAR(50),
    PRECIOUNIT  INT
);
#DROP TABLE Producto;
#DROP TABLE INVENTARIO;

CREATE TABLE Bodega(
	IDBODEGA	INT PRIMARY KEY,
    NOMBRE		VARCHAR(50),
    DIRECCION   VARCHAR(50),
    CIUDAD		VARCHAR(50),
    PAIS 		VARCHAR(50)
);

CREATE TABLE Inventario(
    IDPRODUCTO 			INT,
    IDLOCAL 			INT,
    IDBODEGA 			INT,
    CANTIDADUBICACION 	INT,
    NOMBRE 				VARCHAR(50),
    FOREIGN KEY (IDLOCAL) REFERENCES Locall(IDLOCAL),
    FOREIGN KEY (IDBODEGA) REFERENCES Bodega(IDBODEGA),
    FOREIGN KEY (IDPRODUCTO) REFERENCES Producto(IDPRODUCTO)
);



CREATE TABLE Vendedor (
	IDVENDEDOR  VARCHAR(20) PRIMARY KEY,  #cedula
    NOMBRE 		VARCHAR(30),
    APELLIDO 	VARCHAR(30),
    TIPO 		VARCHAR(1),
    PERMISOS 	VARCHAR(1) 
);
CREATE TABLE JefeDeBodega(
	IDJEFEBODEGA VARCHAR(20) PRIMARY KEY, #cedula
	NOMBRE 		VARCHAR(30),
    APELLIDO 	VARCHAR(30),
    IDBODEGA	INT,
    FOREIGN KEY(IDBODEGA) REFERENCES Bodega(IDBODEGA)
);

CREATE TABLE Cliente(
	IDCLIENTE 	VARCHAR(20) PRIMARY KEY, #cedula
    NOMBRE 		VARCHAR(30),
    APELLIDO 	VARCHAR(30),
    CUENTABANCARIA VARCHAR(30),
    DIRECCION 	VARCHAR(30)
);

CREATE TABLE Gerente(
	IDGERENTE 	VARCHAR(20) PRIMARY KEY,  #cedula
    NOMBRE 		VARCHAR(30),
    APELLIDO 	VARCHAR(30),
    IDLOCAL		INT,
	FOREIGN KEY(IDLOCAL) REFERENCES Locall(IDLOCAL)
);
CREATE TABLE Repartidor(
	IDREPARTIDOR 	VARCHAR(20) PRIMARY KEY,  #cedula
    NOMBRE 			VARCHAR(30),
    APELLIDO 		VARCHAR(30),
    CANTIDADENTREGA INT, #Se incrementara por cada entrega que se le asigne
    IDBODEGA 		INT,
    FOREIGN KEY(IDBODEGA) REFERENCES BODEGA(IDBODEGA)
);
CREATE TABLE PEDIDOLOCAL(
	IDPEDIDO 		INT  PRIMARY KEY auto_increment,
    IDGERENTE 		VARCHAR(20),
    IDBODEGA 		INT,
    NOMBRE 			VARCHAR(50),
    CANTIDAD 		INT,
    DIRECCION 		VARCHAR(50),
	FOREIGN KEY(IDBODEGA) REFERENCES Bodega(IDBODEGA),
    FOREIGN KEY(IDGERENTE) REFERENCES Gerente(IDGERENTE)
);
CREATE TABLE ENTREGADELPEDIDO(
	IDENTREGA 		INT PRIMARY KEY AUTO_INCREMENT,
    IDPEDIDO 		INT,
    IDREPARTIDOR 	VARCHAR(20),
    DESCRIPCION		VARCHAR(100),
	FOREIGN KEY(IDPEDIDO) REFERENCES PEDIDOLOCAL(IDPEDIDO),
	FOREIGN KEY(IDREPARTIDOR) REFERENCES Repartidor(IDREPARTIDOR)
);


# <----------------------------DATA--------------------------->

INSERT INTO Login (IDUSUARIO,USUARIO,CLAVE,TIPO)
VALUES(111,'chjoguer','sistemas1','g'),
	  (222,'josepe','cambio','v'),
	  (333,'benjikrol','ben100','j'),
	  (444,'miglml','ambition','v');
      
      
INSERT INTO Locall(IDLOCAL,NOMBRE,DIRECCION,CIUDAD,PAIS,esMATRIZ)
VALUES(1,'Local Nor','VIA A DUALE KM 5','Guayaquil','Ecuador','l'),
	  (2,'Local Sur','AV/ BOMBERO KM 7','Guayaquil','Ecuador','m');
      
INSERT INTO Bodega(IDBODEGA,NOMBRE,DIRECCION,CIUDAD,PAIS)
VALUES(1,'Bodega Cel','VIA A Samborondon KM 5','Guayaquil','Ecuador');
INSERT INTO JefeDeBodega(IDJEFEBODEGA,NOMBRE,APELLIDO,IDBODEGA)
VALUES('09991','Benjamin','Krol',1);
INSERT INTO Gerente(IDGERENTE,NOMBRE,APELLIDO,IDLOCAL)
VALUES('08881','Christian','Guerrero',1);

INSERT INTO Repartidor(IDREPARTIDOR,NOMBRE,APELLIDO,CANTIDADENTREGA,IDBODEGA)
VALUES('01111','Joel','Espinoza',0,1),
		('01112','Pedro','Ramirez',0,1),
		('01113','Jose','Cordoba',0,1),
		('01114','Angel','Garcia',0,1);
        
INSERT INTO Producto(IDPRODUCTO,NOMBRE,CANTIDADSTOCK ,DESCRIPCION,CATEGORIA,PRECIOUNIT)
VALUES(1,'LCD 32',200,'NUEVO','ENTRETENIMIENTO',400),
		(2,'LCD 55',200,'NUEVO','ENTRETENIMIENTO',600),
		(3,'SMART TV 32',200,'NUEVO','ENTRETENIMIENTO',600),
		(4,'PS4',25,'NUEVO','ENTRETENIMIENTO',350),
		(5,'COCINA',200,'USADO','ELECTRODOMESTICO',400),
		(6,'SPLIT A/C',100,'USADO','ELECTRODOMESTICO',500),
		(7,'REFRIGERADORA',200,'NUEVO','ELECTRODOMESTICO',700),
		(8,'MICROONDAS',100,'NUEVO','ELECTRODOMESTICO',80);


INSERT INTO Inventario(IDPRODUCTO,IDLOCAL,IDBODEGA,CANTIDADUBICACION,NOMBRE)
VALUES(1,1,1,100,'LCD 32'),
		(2,2,1,50,'LCD 55'),
		(3,1,1,100,'SMART TV 55');
        
INSERT INTO  PEDIDOLOCAL(IDGERENTE,IDBODEGA,NOMBRE,CANTIDAD,DIRECCION)
VALUES('08881',1,'LCD 32',55,'FCO ORELLANA'),
	  ('08881',1,'PS4',25,'AV BOMBERO'),
	  ('08881',1,'MICROONDAS',25,'VIA DAULA');
      
      
      
      
INSERT INTO  ENTREGADELPEDIDO(IDPEDIDO,IDREPARTIDOR,DESCRIPCION)
VALUES(1,'01111','EN PROCESO'),
	  (2,'01111','EN PROCESO'),
	  (3,'01111','EN PROCESO');
           
#select * from pedidolocal;

# <----------------------------STORED PROCEDURES-------------------------->
DELIMITER //
CREATE PROCEDURE SOLICITARPEDIDO(IN GER VARCHAR(50),IN BODE VARCHAR(100),IN NOM VARCHAR(100),IN CAN INT, IN DIR VARCHAR(100) )
	BEGIN
		SET @ID := (SELECT IDBODEGA FROM Bodega WHERE NOMBRE=BODE);
        SET @IDG := (SELECT IDGERENTE FROM Gerente WHERE NOMBRE = GER);
		INSERT INTO PEDIDOLOCAL(IDGERENTE,IDBODEGA,NOMBRE,CANTIDAD,DIRECCION) VALUES(@IDG,@ID,NOM,CAN,DIR);    
    END//


DELIMITER //
CREATE PROCEDURE SHOWENTREGAPEDIDO()
	BEGIN
		SELECT e.IDPEDIDO,e.DESCRIPCION,p.DIRECCION,r.NOMBRE,r.Apellido 
        FROM ENTREGADELPEDIDO e,PEDIDOLOCAL p , Repartidor r 
        WHERE e.IDPEDIDO = p.IDPEDIDO AND e.IDREPARTIDOR = r.IDREPARTIDOR;
	END//
    
DELIMITER //
CREATE PROCEDURE login(IN USERNICK VARCHAR(20), IN PASS VARCHAR(20), OUT id INTEGER, OUT t VARCHAR(1))
  BEGIN
    SET id := 0;
    IF (SELECT count(*) FROM login WHERE USUARIO =  USERNICK AND CLAVE = pass) = 1
    THEN
      SET id := (SELECT login.IDUSUARIO FROM login WHERE USUARIO = USERNICK  AND CLAVE = pass);
      SET t := (SELECT login.TIPO FROM login WHERE USUARIO = USERNICK AND CLAVE = pass);
    END IF;
  END //	
 DELIMITER //
CREATE PROCEDURE INVENTARIO()
	BEGIN
		SELECT i.NOMBRE,p.CANTIDADSTOCK,p.PRECIOUNIT,i.CANTIDADUBICACION,b.NOMBRE,l.NOMBRE,l.esMAtriz
        FROM Inventario i, Producto p , Bodega b, Locall l WHERE i.IDPRODUCTO = p.IDPRODUCTO 
        AND i.IDBODEGA=b.IDBODEGA AND i.IDLOCAL=l.IDLOCAL;
    END//

#--------PROCEDURES DE PRODUCTOS
DELIMITER //
CREATE PROCEDURE UPDATESTOCK(IN NOMPRODUCT varchar(100), IN NUEVOSTOCK INT)
	BEGIN
		UPDATE PRODUCTO SET CANTIDADSTOCK = NUEVOSTOCK WHERE NOMBRE = NOMPRODUCT;
    END//
DELIMITER //
CREATE PROCEDURE UPDATEPRECIO(IN NOMPRODUCT varchar(100) , IN NUEVOPRECIO INT)
	BEGIN
		UPDATE PRODUCTO SET  PRECIOUNIT= NUEVOPRECIO WHERE NOMBRE = NOMPRODUCT;
    END//
DELIMITER //
CREATE PROCEDURE GETPROCUTOXDESCRIPCION(IN DESCRIP VARCHAR(200))
	BEGIN
		SELECT p.NOMBRE, p.CANTIDADSTOCK , p.DESCRIPCION , p.CATEGORIA , p.PRECIOUNIT 
		FROM PRODUCTO p 
        WHERE p.DESCRIPCION = DESCRIP;
	END//

DELIMITER //
CREATE PROCEDURE GETPROCUTOXCATEGORIA(IN CATEG VARCHAR(200))
	BEGIN
		SELECT  p.NOMBRE, p.CANTIDADSTOCK , p.DESCRIPCION , p.CATEGORIA , p.PRECIOUNIT 
		FROM PRODUCTO p WHERE p.CATEGORIA = CATEG;
    END//
DELIMITER //
CREATE PROCEDURE GETPROCUTOXNOMBRE(IN NOMBRE VARCHAR(200))
	BEGIN
		SELECT  p.NOMBRE, p.CANTIDADSTOCK , p.DESCRIPCION , p.CATEGORIA , p.PRECIOUNIT 
		FROM PRODUCTO p WHERE p.NOMBRE = NOMBRE;
	END//

DELIMITER //
CREATE PROCEDURE GETALLPRUDUCTOS()
	BEGIN
		SELECT p.NOMBRE
        FROM PRODUCTO p;
	END //
    DROP PROCEDURE GETPRUDUCTO;
DELIMITER //
CREATE PROCEDURE GETPRUDUCTO(IN PRODUCTO varchar(100))
	BEGIN
		SELECT p.NOMBRE, p.CANTIDADSTOCK , p.DESCRIPCION , p.CATEGORIA , p.PRECIOUNIT
        FROM PRODUCTO p
        WHERE p.NOMBRE = PRODUCTO;
	END //
DELIMITER //
CREATE PROCEDURE GETREPARTIDORES()
	BEGIN
		SELECT r.NOMBRE, r.APELLIDO,r.CANTIDADENTREGA 
        FROM REPARTIDOR r, BODEGA b
        WHERE r.IDBODEGA = b.IDBODEGA;
	END //

DELIMITER //
CREATE PROCEDURE GETPEDIDOS()
	BEGIN
		SELECT r.IDPEDIDO, r.NOMBRE,r.CANTIDAD,r.DIRECCION 
        FROM PEDIDOLOCAL r;
	END //
   # DROP PROCEDURE ASIGNARPEDIDOS
DELIMITER //
CREATE PROCEDURE ASIGNARPEDIDOS(IN IDPEDID INT, IN NOMBREREPARTIDOR VARCHAR(50))
	BEGIN
		SET SQL_SAFE_UPDATES=0;
		SET FOREIGN_KEY_CHECKS = 0;
		set @id := (select IDREPARTIDOR FROM Repartidor WHERE NOMBRE= NOMBREREPARTIDOR);
		UPDATE Repartidor SET CANTIDADENTREGA = CANTIDADENTREGA+1 WHERE NOMBRE = NOMBREREPARTIDOR;
		UPDATE ENTREGADELPEDIDO SET IDREPARTIDOR = @id WHERE IDPEDIDO = IDPEDID;
        INSERT INTO  ENTREGADELPEDIDO(IDPEDIDO,IDREPARTIDOR,DESCRIPCION) VALUES(IDPEDID,@id,'EN PROCESO');
		SET FOREIGN_KEY_CHECKS = 1;
		SET SQL_SAFE_UPDATES=1;

	END //   

 DELIMITER //   
CREATE PROCEDURE RESETCANTIDADREPARTIDOR(IN NOMBREREPARTIDOR VARCHAR(50))
	BEGIN
		SET SQL_SAFE_UPDATES=0;
		UPDATE Repartidor SET CANTIDADENTREGA = 0 WHERE NOMBRE = NOMBREREPARTIDOR;
		SET SQL_SAFE_UPDATES=1;
	END //   

DELIMITER //
CREATE PROCEDURE UPDATEESTADOENTREGA(IN DES VARCHAR(100),IN IDENTRE INT)
	BEGIN
		SET SQL_SAFE_UPDATES=0;
		UPDATE  ENTREGADELPEDIDO SET DESCRIPCION = DES WHERE IDENTREGA = IDENTRE;
		SET SQL_SAFE_UPDATES=1;
	END//
    
DELIMITER //
CREATE PROCEDURE UPDATEPERMISOS(IN PERMISO VARCHAR(10), IN USUAR VARCHAR(50))
	BEGIN
		SET SQL_SAFE_UPDATES=0;
		UPDATE Login SET TIPO = PERMISO WHERE USUARIO = USUAR;
		SET SQL_SAFE_UPDATES=1;
    END//
    
DELIMITER //
CREATE PROCEDURE SHOWUSER()
	BEGIN
		select USUARIO, TIPO, IDUSUARIO FROM Login;
    END//
    
# <----------
# <----------------------------VIEWS-------------------------->


