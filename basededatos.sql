-- --------------------------------------------------------
-- Host:                         ec2-54-220-243-77.eu-west-1.compute.amazonaws.com
-- Versión del servidor:         PostgreSQL 13.6 (Ubuntu 13.6-1.pgdg20.04+1+b1) on x86_64-pc-linux-gnu, compiled by gcc (Ubuntu 9.4.0-1ubuntu1~20.04.1) 9.4.0, 64-bit
-- SO del servidor:              
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla public.auction
CREATE TABLE IF NOT EXISTS "auction" (
	"startdate" TIMESTAMP NOT NULL,
	"maxbid" REAL NULL DEFAULT NULL,
	"title" VARCHAR NOT NULL,
	"description" VARCHAR NULL DEFAULT NULL,
	"fotourl" VARCHAR NULL DEFAULT NULL,
	"closeprice" REAL NULL DEFAULT NULL,
	"closedate" TIMESTAMP NULL DEFAULT NULL,
	"closenumberofbids" INTEGER NULL DEFAULT NULL,
	"sellerid" INTEGER NOT NULL,
	"startprice" REAL NULL DEFAULT NULL,
	"auctionid" BIGINT NOT NULL DEFAULT 'nextval(''auction_auctionid_seq''::regclass)',
	"active" BOOLEAN NULL DEFAULT 'true',
	PRIMARY KEY ("auctionid"),
	CONSTRAINT "seller_FK" FOREIGN KEY ("sellerid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.auction: 1 rows
/*!40000 ALTER TABLE "auction" DISABLE KEYS */;
INSERT INTO "auction" ("startdate", "maxbid", "title", "description", "fotourl", "closeprice", "closedate", "closenumberofbids", "sellerid", "startprice", "auctionid", "active") VALUES
	('2022-05-21 19:22:19.66', NULL, 'Viaje a Malaga', 'Malaga arriba!!!', 'https://www.bing.com/th?id=OIP.tuzYE675InhA5UfWwjL1QwHaFj&w=176&h=170&c=8&rs=1&qlt=90&o=6&dpr=1.25&pid=3.1&rm=2', 900, NULL, NULL, 1, 100, 139, 'false'),
	('2022-05-21 13:09:44.965', NULL, 'Coche', 'Rojo', 'https://www.diariomotor.com/imagenes/picscache/1440x655c/ayudas-coche-nuevo-seat-leon-rojo-2020_1440x655c.jpg', NULL, '2022-05-21 15:00:00', NULL, 1, 100, 137, 'true'),
	('2022-05-21 13:15:58.156', NULL, 'Platano', 'Se vende platanos!', 'https://i.blogs.es/9e2919/platano/1366_2000.jpg', 10, '2022-05-31 20:00:00', 1000, 1, 1, 138, 'true');
/*!40000 ALTER TABLE "auction" ENABLE KEYS */;

-- Volcando estructura para tabla public.auctioncategory
CREATE TABLE IF NOT EXISTS "auctioncategory" (
	"categoryid" BIGINT NOT NULL,
	"auctionid" BIGINT NOT NULL DEFAULT 'nextval(''auctioncategory_auctionid_seq''::regclass)',
	CONSTRAINT "auctioncategory_auctionid_fkey" FOREIGN KEY ("auctionid") REFERENCES "public"."auction" ("auctionid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "category_KEY" FOREIGN KEY ("categoryid") REFERENCES "public"."category" ("categoryid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.auctioncategory: 4 rows
/*!40000 ALTER TABLE "auctioncategory" DISABLE KEYS */;
INSERT INTO "auctioncategory" ("categoryid", "auctionid") VALUES
	(8, 137),
	(29, 138),
	(27, 139);
/*!40000 ALTER TABLE "auctioncategory" ENABLE KEYS */;

-- Volcando estructura para tabla public.bid
CREATE TABLE IF NOT EXISTS "bid" (
	"bid" DOUBLE PRECISION NULL DEFAULT NULL,
	"bidid" BIGINT NOT NULL DEFAULT 'nextval(''bid_bidid_seq''::regclass)',
	"biderid" BIGINT NOT NULL,
	"auctionid" BIGINT NOT NULL DEFAULT 'nextval(''bid_auctionid_seq''::regclass)',
	PRIMARY KEY ("bidid"),
	CONSTRAINT "bid_auctionid_fkey" FOREIGN KEY ("auctionid") REFERENCES "public"."auction" ("auctionid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "biderid_KEY" FOREIGN KEY ("biderid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.bid: 5 rows
/*!40000 ALTER TABLE "bid" DISABLE KEYS */;
INSERT INTO "bid" ("bid", "bidid", "biderid", "auctionid") VALUES
	(2, 42, 1, 138);
/*!40000 ALTER TABLE "bid" ENABLE KEYS */;

-- Volcando estructura para tabla public.category
CREATE TABLE IF NOT EXISTS "category" (
	"name" VARCHAR NULL DEFAULT NULL,
	"categoryid" BIGINT NOT NULL DEFAULT 'nextval(''category_categoryid_seq''::regclass)',
	PRIMARY KEY ("categoryid")
);

-- Volcando datos para la tabla public.category: 2 rows
/*!40000 ALTER TABLE "category" DISABLE KEYS */;
INSERT INTO "category" ("name", "categoryid") VALUES
	('Bebés', 3),
	('Artesanía y manualidades', 2),
	('Arte y antigüedades', 1),
	('Belleza y salud', 4),
	('Cámaras y fotografía', 5),
	('Casa, jardín y bricolaje', 6),
	('Cine, DVD y películas', 7),
	('Coches, motos y otros vehíc.', 8),
	('Coleccionismo', 9),
	('Consolas y videojuegos', 10),
	('Deportes', 11),
	('Electrodomésticos', 12),
	('Entradas y eventos', 13),
	('Equipamiento y maquinaria', 14),
	('Imagen y sonido', 15),
	('Informática y tablets', 16),
	('Intrumentos musicales', 17),
	('Juguetes', 18),
	('Libros, revistas y cómics', 19),
	('Monedas y billetes', 20),
	('Motor: recambios y accesorios', 21),
	('Móviles y telefonía', 22),
	('Música, CDs y vinilos', 23),
	('Relojes y joyas', 24),
	('Ropa, calzado y complementos', 25),
	('Sellos', 26),
	('Viajes', 27),
	('Vinos y gastronomía', 28),
	('Otras categorías', 29);
/*!40000 ALTER TABLE "category" ENABLE KEYS */;

-- Volcando estructura para tabla public.favoriteauction
CREATE TABLE IF NOT EXISTS "favoriteauction" (
	"userid" BIGINT NOT NULL,
	"auctionid" BIGINT NOT NULL DEFAULT 'nextval(''favoriteauction_auctionid_seq''::regclass)',
	CONSTRAINT "favoriteauction_auctionid_fkey" FOREIGN KEY ("auctionid") REFERENCES "public"."auction" ("auctionid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "userid_KEY" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.favoriteauction: 0 rows
/*!40000 ALTER TABLE "favoriteauction" DISABLE KEYS */;
INSERT INTO "favoriteauction" ("userid", "auctionid") VALUES
	(1, 137),
	(1, 138);
/*!40000 ALTER TABLE "favoriteauction" ENABLE KEYS */;

-- Volcando estructura para tabla public.groups
CREATE TABLE IF NOT EXISTS "groups" (
	"groupid" BIGINT NOT NULL DEFAULT 'nextval(''groups_groupid_seq''::regclass)',
	"name" VARCHAR NULL DEFAULT NULL,
	PRIMARY KEY ("groupid")
);

-- Volcando datos para la tabla public.groups: 1 rows
/*!40000 ALTER TABLE "groups" DISABLE KEYS */;
INSERT INTO "groups" ("groupid", "name") VALUES
	(131, 'vip'),
	(140, 'marketing'),
	(142, 'Grupo con Parsa'),
	(145, 'Gruoi'),
	(146, 'marketing-Grupo con Parsa-Grupo con Parsa2'),
	(150, 'Admin y Parsa'),
	(151, 'marketing-Grupo con Parsa-Grupo con Parsa2-Admin y Parsa'),
	(152, 'hols'),
	(153, 'hols2');
/*!40000 ALTER TABLE "groups" ENABLE KEYS */;

-- Volcando estructura para tabla public.groupsmail
CREATE TABLE IF NOT EXISTS "groupsmail" (
	"groupid" BIGINT NOT NULL,
	"mailid" BIGINT NOT NULL,
	CONSTRAINT "groupid_KEY" FOREIGN KEY ("groupid") REFERENCES "public"."groups" ("groupid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "mailid_KEY" FOREIGN KEY ("mailid") REFERENCES "public"."mail" ("mailid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.groupsmail: 0 rows
/*!40000 ALTER TABLE "groupsmail" DISABLE KEYS */;
INSERT INTO "groupsmail" ("groupid", "mailid") VALUES
	(142, 31),
	(131, 34),
	(140, 34),
	(142, 34),
	(145, 34),
	(146, 34),
	(146, 36),
	(146, 37),
	(150, 38),
	(140, 39),
	(142, 39);
/*!40000 ALTER TABLE "groupsmail" ENABLE KEYS */;

-- Volcando estructura para tabla public.mail
CREATE TABLE IF NOT EXISTS "mail" (
	"subject" VARCHAR NULL DEFAULT NULL,
	"body" VARCHAR NULL DEFAULT NULL,
	"mailid" BIGINT NOT NULL DEFAULT 'nextval(''mail_mailid_seq''::regclass)',
	"senderid" BIGINT NOT NULL,
	"sentdate" DATE NULL DEFAULT 'now()',
	PRIMARY KEY ("mailid"),
	CONSTRAINT "senderid_KEY" FOREIGN KEY ("senderid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.mail: 0 rows
/*!40000 ALTER TABLE "mail" DISABLE KEYS */;
INSERT INTO "mail" ("subject", "body", "mailid", "senderid", "sentdate") VALUES
	('Has ganado la subasta Bufanda', 'Has ganado la subasta Bufanda', 24, 32, '2022-05-15'),
	('Has ganado la subasta Coche', 'Has ganado la subasta Coche', 25, 32, '2022-05-15'),
	('Has ganado la subasta Coche', 'Has ganado la subasta Coche', 26, 32, '2022-05-15'),
	('Has ganado la subasta Muletas', 'Has ganado la subasta Muletas', 27, 32, '2022-05-15'),
	('Has ganado la subasta Coche', 'Has ganado la subasta Coche', 30, 32, '2022-05-16'),
	('Mirad quÃ© coche!', 'Mirad quÃ© coche!', 31, 1, '2022-05-16'),
	('Has ganado la subasta Coche', 'Has ganado la subasta Coche', 32, 32, '2022-05-16'),
	('Has ganado la subasta Lavavajillas', 'Has ganado la subasta Lavavajillas', 33, 32, '2022-05-16'),
	('Buenas ofertas', 'Buenas ofertas', 34, 1, '2022-05-16'),
	('Has ganado la subasta Muletas', 'Has ganado la subasta Muletas', 35, 32, '2022-05-16'),
	('Viaje', 'Viaje', 36, 1, '2022-05-21'),
	('A', 'A', 37, 1, '2022-05-21'),
	('Coche Rojo', 'Coche Rojo', 38, 1, '2022-05-21'),
	('Fufa', 'Fufa', 39, 32, '2022-05-22');
/*!40000 ALTER TABLE "mail" ENABLE KEYS */;

-- Volcando estructura para tabla public.purchasedauction
CREATE TABLE IF NOT EXISTS "purchasedauction" (
	"userid" BIGINT NULL DEFAULT NULL,
	"auctionid" BIGINT NULL DEFAULT NULL,
	CONSTRAINT "purchasedauction_auctionid_fkey" FOREIGN KEY ("auctionid") REFERENCES "public"."auction" ("auctionid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "purchasedauction_userid_fkey" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.purchasedauction: 4 rows
/*!40000 ALTER TABLE "purchasedauction" DISABLE KEYS */;
/*!40000 ALTER TABLE "purchasedauction" ENABLE KEYS */;

-- Volcando estructura para tabla public.rol
CREATE TABLE IF NOT EXISTS "rol" (
	"rolid" BIGINT NOT NULL,
	"type" INTEGER NOT NULL,
	"name" VARCHAR NOT NULL,
	PRIMARY KEY ("rolid")
);

-- Volcando datos para la tabla public.rol: 0 rows
/*!40000 ALTER TABLE "rol" DISABLE KEYS */;
INSERT INTO "rol" ("rolid", "type", "name") VALUES
	(1, 1, 'Default'),
	(2, 2, 'Marketing'),
	(3, 3, 'Analist'),
	(4, 4, 'Administrator');
/*!40000 ALTER TABLE "rol" ENABLE KEYS */;

-- Volcando estructura para tabla public.suggestedauction
CREATE TABLE IF NOT EXISTS "suggestedauction" (
	"mailid" BIGINT NOT NULL,
	"auctionid" BIGINT NOT NULL DEFAULT 'nextval(''suggestedauction_auctionid_seq''::regclass)',
	CONSTRAINT "mailid_KEY" FOREIGN KEY ("mailid") REFERENCES "public"."mail" ("mailid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "suggestedauction_auctionid_fkey" FOREIGN KEY ("auctionid") REFERENCES "public"."auction" ("auctionid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.suggestedauction: 5 rows
/*!40000 ALTER TABLE "suggestedauction" DISABLE KEYS */;
INSERT INTO "suggestedauction" ("mailid", "auctionid") VALUES
	(36, 139),
	(37, 137),
	(38, 137),
	(39, 139),
	(39, 137);
/*!40000 ALTER TABLE "suggestedauction" ENABLE KEYS */;

-- Volcando estructura para tabla public.users
CREATE TABLE IF NOT EXISTS "users" (
	"username" VARCHAR NOT NULL,
	"password" VARCHAR NOT NULL,
	"email" VARCHAR NOT NULL,
	"joineddate" DATE NULL DEFAULT 'now()',
	"name" VARCHAR NULL DEFAULT NULL,
	"surname" VARCHAR NULL DEFAULT NULL,
	"sex" INTEGER NULL DEFAULT NULL,
	"birthyear" DATE NOT NULL,
	"country" VARCHAR NULL DEFAULT NULL,
	"city" VARCHAR NULL DEFAULT NULL,
	"address" VARCHAR NULL DEFAULT NULL,
	"userid" INTEGER NOT NULL DEFAULT 'nextval(''users_userid_seq''::regclass)',
	UNIQUE INDEX "User_email_key" ("email"),
	UNIQUE INDEX "User_username_key" ("username"),
	PRIMARY KEY ("userid")
);

-- Volcando datos para la tabla public.users: 2 rows
/*!40000 ALTER TABLE "users" DISABLE KEYS */;
INSERT INTO "users" ("username", "password", "email", "joineddate", "name", "surname", "sex", "birthyear", "country", "city", "address", "userid") VALUES
	('marketing', 'marketing', 'marketing@eguay.com', NULL, 'marketingName', 'marketingSurname', 3, '2022-05-11', 'marketingCountry', 'marketingCity', 'marketingAddress', 32),
	('Hola', 'admin', '0619904874@uma.es', NULL, '0619904874@uma.es', 'x', 3, '2022-05-24', 'HelloKitty', 'k', 'Pellegrini < Arteta', 33),
	('admin', 'admin', 'admin@admin.com', NULL, 'Admin', 'admin', 0, '2000-03-23', 'admin', 'admin', 'kos', 1),
	('parsa', 'parsa', 'parsa@parsa.parsa', NULL, 'Parsaaa', 'null', 0, '2022-04-06', 'Iran', 'No City', 'kos', 17),
	('prueba', 'prueba', 'prueba@prueba.es', NULL, 'Prueba', 'Prueba', 3, '1998-11-10', 'EspaÃ±a', 'Malaga', 'Prueba', 37);
/*!40000 ALTER TABLE "users" ENABLE KEYS */;

-- Volcando estructura para tabla public.userscategory
CREATE TABLE IF NOT EXISTS "userscategory" (
	"userid" BIGINT NOT NULL,
	"categoryid" BIGINT NOT NULL,
	CONSTRAINT "userscategory_categoryid_fkey" FOREIGN KEY ("categoryid") REFERENCES "public"."category" ("categoryid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "userscategory_userid_fkey" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.userscategory: 1 rows
/*!40000 ALTER TABLE "userscategory" DISABLE KEYS */;
INSERT INTO "userscategory" ("userid", "categoryid") VALUES
	(1, 9);
/*!40000 ALTER TABLE "userscategory" ENABLE KEYS */;

-- Volcando estructura para tabla public.usersgroups
CREATE TABLE IF NOT EXISTS "usersgroups" (
	"userid" BIGINT NULL DEFAULT NULL,
	"groupid" BIGINT NULL DEFAULT NULL,
	CONSTRAINT "groupid_KEY" FOREIGN KEY ("groupid") REFERENCES "public"."groups" ("groupid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "usersgroups_userid_fkey" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.usersgroups: 22 rows
/*!40000 ALTER TABLE "usersgroups" DISABLE KEYS */;
INSERT INTO "usersgroups" ("userid", "groupid") VALUES
	(17, 131),
	(32, 140),
	(17, 142),
	(1, 142),
	(32, 131),
	(17, 145),
	(32, 146),
	(17, 146),
	(1, 146),
	(17, 146),
	(1, 146),
	(1, 150),
	(17, 150),
	(32, 151),
	(17, 151),
	(1, 151),
	(17, 151),
	(1, 151),
	(1, 151),
	(17, 151),
	(32, 152),
	(1, 152),
	(17, 152),
	(32, 153),
	(1, 153),
	(17, 153),
	(37, 153);
/*!40000 ALTER TABLE "usersgroups" ENABLE KEYS */;

-- Volcando estructura para tabla public.usersmail
CREATE TABLE IF NOT EXISTS "usersmail" (
	"userid" BIGINT NOT NULL,
	"mailid" BIGINT NOT NULL,
	CONSTRAINT "mailid_KEY" FOREIGN KEY ("mailid") REFERENCES "public"."mail" ("mailid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "usersmail_userid_fkey" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.usersmail: 0 rows
/*!40000 ALTER TABLE "usersmail" DISABLE KEYS */;
INSERT INTO "usersmail" ("userid", "mailid") VALUES
	(17, 25),
	(17, 26),
	(17, 27),
	(17, 30),
	(1, 32),
	(1, 33),
	(1, 35);
/*!40000 ALTER TABLE "usersmail" ENABLE KEYS */;

-- Volcando estructura para tabla public.usersrol
CREATE TABLE IF NOT EXISTS "usersrol" (
	"userid" BIGINT NULL DEFAULT NULL,
	"rolid" BIGINT NULL DEFAULT NULL,
	CONSTRAINT "rolid_KEY" FOREIGN KEY ("rolid") REFERENCES "public"."rol" ("rolid") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "usersrol_userid_fkey" FOREIGN KEY ("userid") REFERENCES "public"."users" ("userid") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Volcando datos para la tabla public.usersrol: 0 rows
/*!40000 ALTER TABLE "usersrol" DISABLE KEYS */;
INSERT INTO "usersrol" ("userid", "rolid") VALUES
	(1, 4),
	(1, 3),
	(32, 2),
	(1, 2);
/*!40000 ALTER TABLE "usersrol" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
