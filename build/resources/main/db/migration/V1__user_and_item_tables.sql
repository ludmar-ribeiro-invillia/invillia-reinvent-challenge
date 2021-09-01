DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item (
	id bigint constraint ITEM_PK primary key,
	price decimal(2) NOT NULL,
	name varchar(255) NOT NULL,
	quantity integer NOT NULL,
	sku varchar(255) NOT NULL,
	user_id bigint NOT NULL
);

create sequence SQ_ITEM_ID
minvalue 1
no maxvalue
cache 1;