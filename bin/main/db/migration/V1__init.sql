CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  firstname varchar(50) NOT NULL,
  lastname varchar(50) DEFAULT NULL,
  address varchar(50) DEFAULT NULL,
  phone varchar(50) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  enabled tinyint DEFAULT true,
  usertype int(2) DEFAULT 1,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS customer (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  company varchar(100) NOT NULL,
  address varchar(255),
  phone varchar(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS orders (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  assignee_id bigint(20) NOT NULL,
  created_by bigint(20) NOT NULL,
  customer_id bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  contract_id varchar(255) NOT NULL,
  signed_at TIMESTAMP NULL,
  delivered_date TIMESTAMP NULL,
  status int(2) NOT NULL,
  net_price DECIMAL(19,4) NOT NULL,
  note varchar(255) NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS payment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  percent int(10) NOT NULL,
  order_id bigint(20) NOT NULL,
  paid tinyint DEFAULT false,
  paid_date TIMESTAMP NULL,
  invoice_no varchar(255) NULL,
  note varchar(255) NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`, `address`, `phone`, `email`, `enabled`, `usertype`, `created_at`) VALUES (NULL, 'admin', '$2a$04$NHFnUm9cNXsXya3STBFi2eM5kH5iBQWzBRAXbeo6sb2jDuP7Xv4pK', 'Anh', 'Nguyễn', 'HCMC', '0828997267', 'ntanh5239@gmail.com', '1', '2', '2019-01-28 20:43:19');

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`, `address`, `phone`, `email`, `enabled`, `usertype`, `created_at`) VALUES (NULL, 'user', '$2a$04$NHFnUm9cNXsXya3STBFi2eM5kH5iBQWzBRAXbeo6sb2jDuP7Xv4pK', 'firstname', 'lastname', 'KH', '01234', 'kh@gmail.com', '1', '1', '2019-01-28 20:43:35');