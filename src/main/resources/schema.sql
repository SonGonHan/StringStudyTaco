
CREATE SEQUENCE IF NOT EXISTS taco_order_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS taco_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS ingredient
(
    id   VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    type SMALLINT,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS taco
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_taco PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS taco_ingredients
(
    taco_id        BIGINT       NOT NULL,
    ingredients_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS taco_order
(
    id              BIGINT NOT NULL,
    placed_at       TIMESTAMP WITHOUT TIME ZONE,
    delivery_name   VARCHAR(255),
    delivery_street VARCHAR(255),
    delivery_city   VARCHAR(255),
    delivery_state  VARCHAR(255),
    delivery_zip    VARCHAR(255),
    cc_number       VARCHAR(255),
    cc_expiration   VARCHAR(255),
    cccvv           VARCHAR(255),
    CONSTRAINT pk_tacoorder PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS taco_order_tacos
(
    taco_order_id BIGINT NOT NULL,
    tacos_id      BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id           BIGINT NOT NULL,
    username     VARCHAR(255),
    password     VARCHAR(255),
    fullname     VARCHAR(255),
    street       VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    zip          VARCHAR(255),
    phone_number VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE taco_order_tacos
    ADD CONSTRAINT uc_taco_order_tacos_tacos UNIQUE (tacos_id);

ALTER TABLE taco_ingredients
    ADD CONSTRAINT fk_tacing_on_ingredient FOREIGN KEY (ingredients_id) REFERENCES ingredient (id);

ALTER TABLE taco_ingredients
    ADD CONSTRAINT fk_tacing_on_taco FOREIGN KEY (taco_id) REFERENCES taco (id);

ALTER TABLE taco_order_tacos
    ADD CONSTRAINT fk_tacordtac_on_taco FOREIGN KEY (tacos_id) REFERENCES taco (id);

ALTER TABLE taco_order_tacos
    ADD CONSTRAINT fk_tacordtac_on_taco_order FOREIGN KEY (taco_order_id) REFERENCES taco_order (id);