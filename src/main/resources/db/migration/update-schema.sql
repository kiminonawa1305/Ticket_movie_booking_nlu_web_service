CREATE TABLE chair_showtimes
(
    id          INT NOT NULL,
    status      ENUM ('AVAILABLE', 'SELECTED', 'SOLD') NULL,
    chair_id    INT NULL,
    customer_id INT NULL,
    showtime_id INT NULL,
    CONSTRAINT pk_chair_showtimes PRIMARY KEY (id)
);

CREATE TABLE chairs
(
    id      INT AUTO_INCREMENT               NOT NULL,
    name    VARCHAR(255) NULL,
    type    ENUM ('SINGLE', 'COUPLE', 'VIP') NULL,
    room_id INT NULL,
    CONSTRAINT pk_chairs PRIMARY KEY (id)
);

CREATE TABLE cinemas
(
    id   INT AUTO_INCREMENT NOT NULL,
    name ENUM 'Cinestar Sinh Viên (Bình Dương) NULL, address VARCHAR(255) NULL, url_image VARCHAR(255) NULL, detail_address VARCHAR(255) NULL, CONSTRAINT pk_cinemas PRIMARY KEY (id));

CREATE TABLE customers
(
    id     INT AUTO_INCREMENT NOT NULL,
    api_id VARCHAR(255)       NULL,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE movie_favorites
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    movie_id    INT                   NULL,
    customer_id INT                   NULL,
    CONSTRAINT pk_movie_favorites PRIMARY KEY (id)
);

CREATE TABLE movie_reviews
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    star        INT                   NULL,
    content     VARCHAR(255)          NULL,
    date        datetime              NULL,
    movie_id    INT                   NULL,
    customer_id INT                   NULL,
    CONSTRAINT pk_movie_reviews PRIMARY KEY (id)
);

CREATE TABLE movies
(
    id     INT AUTO_INCREMENT NOT NULL,
    id_api VARCHAR(255)       NULL,
    CONSTRAINT pk_movies PRIMARY KEY (id)
);

CREATE TABLE price_boards
(
    id        INT AUTO_INCREMENT NOT NULL,
    single    DOUBLE             NULL,
    couple    DOUBLE             NULL,
    vip       DOUBLE             NULL,
    cinema_id INT                NULL,
    CONSTRAINT pk_price_boards PRIMARY KEY (id)
);

CREATE TABLE rooms
(
    id        INT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)       NULL,
    cinema_id INT                NULL,
    CONSTRAINT pk_rooms PRIMARY KEY (id)
);

CREATE TABLE showtimes
(
    id       INT AUTO_INCREMENT NOT NULL,
    start    datetime           NOT NULL,
    avail    BIT(1)             NOT NULL,
    movie_id INT                NULL,
    room_id  INT                NULL,
    CONSTRAINT pk_showtimes PRIMARY KEY (id)
);

CREATE TABLE tickets
(
    id          VARCHAR(255)     NOT NULL,
    avail       BIT(1) DEFAULT 1 NULL,
    showtime_id INT              NULL,
    customer_id INT              NULL,
    chair_id    INT              NULL,
    CONSTRAINT pk_tickets PRIMARY KEY (id)
);

ALTER TABLE chairs
    ADD CONSTRAINT FK_CHAIRS_ON_ROOM FOREIGN KEY (room_id) REFERENCES rooms (id);

ALTER TABLE chair_showtimes
    ADD CONSTRAINT FK_CHAIR_SHOWTIMES_ON_CHAIR FOREIGN KEY (chair_id) REFERENCES chairs (id);

ALTER TABLE chair_showtimes
    ADD CONSTRAINT FK_CHAIR_SHOWTIMES_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE chair_showtimes
    ADD CONSTRAINT FK_CHAIR_SHOWTIMES_ON_SHOWTIME FOREIGN KEY (showtime_id) REFERENCES showtimes (id);

ALTER TABLE movie_favorites
    ADD CONSTRAINT FK_MOVIE_FAVORITES_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE movie_favorites
    ADD CONSTRAINT FK_MOVIE_FAVORITES_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE movie_reviews
    ADD CONSTRAINT FK_MOVIE_REVIEWS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE movie_reviews
    ADD CONSTRAINT FK_MOVIE_REVIEWS_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE price_boards
    ADD CONSTRAINT FK_PRICE_BOARDS_ON_CINEMA FOREIGN KEY (cinema_id) REFERENCES cinemas (id);

ALTER TABLE rooms
    ADD CONSTRAINT FK_ROOMS_ON_CINEMA FOREIGN KEY (cinema_id) REFERENCES cinemas (id);

ALTER TABLE showtimes
    ADD CONSTRAINT FK_SHOWTIMES_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE showtimes
    ADD CONSTRAINT FK_SHOWTIMES_ON_ROOM FOREIGN KEY (room_id) REFERENCES rooms (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_CHAIR FOREIGN KEY (chair_id) REFERENCES chairs (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_SHOWTIME FOREIGN KEY (showtime_id) REFERENCES showtimes (id);