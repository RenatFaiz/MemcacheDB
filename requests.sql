CREATE TABLE products
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT    NOT NULL,
    category    TEXT    NOT NULL,
    quantity    INTEGER NOT NULL,
    isAvailable INTEGER NOT NULL,
    price       INTEGER CHECK ( price > 0 )
);

INSERT INTO products (name, category, quantity,
                      isAvailable, price)
VALUES ('Микроволновая печь V-HOME P70H20L-KH', 'Бытовая техника', 15, 1, 3200),
       ('Смартфон Xiaomi Redmi GO 1/8Gb blue', 'Смартфоны и гаджеты', 5, 0, 5550),
       ('Ноутбук ASUS X570UD, 90NB0HS1-M03530', 'Компьютеры и ноутбуки', 3, 1, 55440),
       ('Телевизор Panasonic TX-43FSR400', 'ТВ, Аудио, Видео', 10, 1, 21980),
       ('Телевизор LG 43UM7500, серебристый', 'ТВ, Аудио, Видео', 7, 1, 37990),
       ('Телевизор KIVI 40FR52BR', 'ТВ, Аудио, Видео', 13, 1, 16990),
       ('Блендер Kitfort КТ-1363-5', 'Бытовая техника', 8, 1, 1790);


CREATE TABLE customers
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    login    TEXT UNIQUE NOT NULL,
    name     TEXT        NOT NULL,
    order_id INTEGER REFERENCES orders (id) deferrable initially deferred
);

INSERT INTO customers (id, login, name, order_id)
VALUES (1, 'tolya76', 'Анатолий', 1),
       (2, 'ola-la', 'Ольга', 4),
       (3, 'rus96', 'Руслан', 3),
       (4, 'lili', 'Лиля', null);


CREATE TABLE orders
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    customer_id  INTEGER REFERENCES customers (id),
    product_id   INTEGER REFERENCES products (id),
    product_name TEXT    NOT NULL,
    quantity     INTEGER NOT NULL,
    order_price  INTEGER CHECK ( order_price > 0),
    delivery     TEXT    NOT NULL,
    status       INTEGER NOT NULL,
    datetime     INTEGER NOT NULL
);

INSERT INTO orders (id, customer_id, product_id, product_name,
                    quantity, order_price, delivery, status, datetime)
VALUES (1, 1, 0, 'Микроволновая печь V-HOME P70H20L-KH', 1, 3200, 'Самовывоз', 1,
        '2019-01-01 20:00:00'),
       (2, null, 2, 'Ноутбук ASUS X570UD, 90NB0HS1-M03530', 1, 55440, 'Доставка: ул.Н Ершова, 30', 1,
        '2019-06-10 12:00:00'),
       (3, 3, 5, 'Телевизор KIVI 40FR52BR', 1, 16990, 'Доставка: Осиново, ул.Садовая, 43', 1,
        '2019-11-09 13:45:00'),
       (4, 2, 6, 'Блендер Kitfort КТ-1363-5', 2, 3580, 'Самовывоз', 1, '2019-09-23 18:30:00');

