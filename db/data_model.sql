/***************************
* DATABASE application.yml *
****************************/
-- CREATE DATABASE tourism_pass_mgmt;


/*****************
* TABLE customer *
******************/
CREATE TABLE customer
(
    id               uuid PRIMARY KEY,
    first_name       varchar(50) NOT NULL,
    last_name        varchar(50) NOT NULL,
    home_city        varchar(20) NOT NULL,
    create_date      timestamp   NOT NULL DEFAULT now(),
    last_update_date timestamp   NOT NULL DEFAULT now()
);

/***************
* TABLE vendor *
****************/
CREATE TABLE vendor
(
    id               uuid PRIMARY KEY,
    name             varchar(100) NOT NULL,
    create_date      timestamp    NOT NULL DEFAULT now(),
    last_update_date timestamp    NOT NULL DEFAULT now()
);

/*************
* TABLE pass *
**************/
CREATE TABLE pass
(
    id               uuid PRIMARY KEY,
    city             varchar(50) NOT NULL,
    length           integer     NOT NULL,
    start_date       date        NOT NULL,
    expiration_date  date        NOT NULL,
    cancel_indicator boolean     NOT NULL,
    create_date      timestamp   NOT NULL DEFAULT now(),
    last_update_date timestamp   NOT NULL DEFAULT now(),
    customer_id      uuid REFERENCES customer (id),
    vendor_id        uuid REFERENCES vendor (id)
);