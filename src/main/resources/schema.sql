DROP DATABASE IF EXISTS graduate;
CREATE DATABASE IF NOT EXISTS graduate;
USE graduate;

CREATE TABLE example(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name CHAR(25) UNIQUE,
    value CHAR(25) NOT NULL
)engine=InnoDB;

CREATE TABLE GoodsType(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name CHAR(125) NOT NULL UNIQUE ,
    description CHAR(125) NOT NULL,
    length INT NOT NULL,
    width INT NOT NULL,
    height INT NOT NULL
);

CREATE TABLE Detail(
    goods_type INT NOT NULL ,
    name CHAR(125) NOT NULL,
    value CHAR(125) NOT NULL,

--    CONSTRAINT UNIQUE (goods_type, name, value)
    PRIMARY KEY (goods_type, name, value),
    FOREIGN KEY (goods_type) REFERENCES  GoodsType(id) ON DELETE CASCADE

);

CREATE TABLE Warehouse(
    id INT PRIMARY KEY  AUTO_INCREMENT,
    status ENUM('create', 'available', 'work', 'suspend') NOT NULL DEFAULT 'create',
    description CHAR(125) NULL,
    address CHAR(125) NOT NULL,
    length INT NULL,
    width INT NULL,
    height INT NULL,

    create_time DATETIME NULL ,
    available_time DATETIME NULL,
    work_time DATETIME NULL,
    suspend_time DATETIME NULL,
    stop_time DATETIME NULL

--    循环依赖，故使用第三方表解决
--    last_complete_in INT NULL,
--    last_submit_in INT NULL,
--    last_create_in INT NULL,
--    last_complete_out INT NULL,
--    last_submit_out INT NULL,
--    last_create_out INT NULL,

--    CONSTRAINT FOREIGN KEY (last_complete_in) REFERENCES InOrder(id),
--    CONSTRAINT FOREIGN KEY (last_submit_in) REFERENCES InOrder(id),
--    CONSTRAINT FOREIGN KEY (last_create_in) REFERENCES InOrder(id),
--    CONSTRAINT FOREIGN KEY (last_complete_out) REFERENCES OutOrder(id),
--    CONSTRAINT FOREIGN KEY (last_submit_out) REFERENCES OutOrder(id),
--    CONSTRAINT FOREIGN KEY (last_create_out) REFERENCES OutOrder(id),

);

CREATE TABLE Cabinet(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'work', 'stop') NOT NULL DEFAULT 'create',
    position CHAR(125) NULL,

    inner_length INT NOT NULL,
    inner_width INT NOT NULL,
    inner_height INT NOT NULL,

    goods_type INT NULL,
    goods_capacity INT NULL,
    goods_size INT NULL,
    goods_out INT NULL,
    goods_in INT NULL,

--    循环依赖，故使用第三方表解决
--    CONSTRAINT FOREIGN KEY (id) REFERENCES Goods(id),
    CONSTRAINT FOREIGN KEY (goods_type) REFERENCES GoodsType(id)

);

CREATE TABLE Goods(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status ENUM('free', 'in', 'storage', 'out', 'escape')  NOT NULL DEFAULT 'free',
    type INT NULL ,
    cabinet INT NULL,
    warehouse INT NULL,
    CONSTRAINT FOREIGN KEY (type) REFERENCES GoodsType(id),
    CONSTRAINT FOREIGN KEY (cabinet) REFERENCES Cabinet(id),
    CONSTRAINT FOREIGN KEY (warehouse) REFERENCES Warehouse(id)
);

CREATE TABLE CabinetGoods(
    cabinet INT NULL,
    goods INT NULL,
    CONSTRAINT UNIQUE (cabinet, goods),
    CONSTRAINT FOREIGN KEY (cabinet) REFERENCES Cabinet(id),
    CONSTRAINT FOREIGN KEY (goods)  REFERENCES Goods(id)
);

CREATE TABLE InOrder(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'submit', 'complete') NOT NULL DEFAULT 'create',
    create_time DATETIME NULL,
    submit_time DATETIME NULL,
    complete_time DATETIME NULL,
    warehouse INT NULL,
    CONSTRAINT FOREIGN KEY (warehouse) REFERENCES Warehouse(id)
);

CREATE TABLE InItem(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'complete') NOT NULL DEFAULT 'create',
    create_time DATETIME NULL,
    complete_time DATETIME NULL,

    inorder INT NULL,
    goods INT NULL,
    cabinet INT NULL,

    CONSTRAINT FOREIGN KEY (inorder) REFERENCES InOrder(id),
    CONSTRAINT FOREIGN KEY (goods) REFERENCES Goods(id),
    CONSTRAINT FOREIGN KEY (cabinet) REFERENCES Cabinet(id)
);

CREATE TABLE GetOrder(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM( 'create', 'submit', 'outing', 'complete') NOT NULL DEFAULT 'create',
    address CHAR(125) NOT NULL,
    reason CHAR(125) NOT NULL,
    create_time DATETIME NULL,
    submit_time DATETIME NULL,
    outing_time DATETIME NULL,
    complete_time DATETIME NULL
);

CREATE TABLE GetItem(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'outing', 'complete') NOT NULL DEFAULT 'create',
    create_time DATETIME NULL,
    outing_time DATETIME NULL,
    complete_time DATETIME NULL,

    warehouse INT NULL,
    goods_type INT NULL,
    goods_count INT NULL,
    getorder INT NULL,

    CONSTRAINT FOREIGN KEY (warehouse) REFERENCES Warehouse(id),
    CONSTRAINT FOREIGN KEY (goods_type) REFERENCES GoodsType(id),
    CONSTRAINT FOREIGN KEY (getorder) REFERENCES GetOrder(id)
);

CREATE TABLE OutOrder(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'submit', 'complete') NOT NULL DEFAULT 'create',
    create_time DATETIME NULL,
    submit_time DATETIME NULL,
    complete_time DATETIME NULL,
    warehouse INT NULL,

    CONSTRAINT FOREIGN KEY (warehouse) REFERENCES Warehouse(id)
);

CREATE TABLE OutItem(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('create', 'complete') NOT NULL DEFAULT 'create',
    create_time DATETIME NULL,
    complete_time DATETIME NULL,

    outorder INT NULL,
    goods INT NULL,
    cabinet INT NULL,
    getitem INT NULL,

    CONSTRAINT FOREIGN KEY (outorder) REFERENCES OutOrder(id),
    CONSTRAINT FOREIGN KEY (goods) REFERENCES Goods(id),
    CONSTRAINT FOREIGN KEY (cabinet) REFERENCES Cabinet(id),
    CONSTRAINT FOREIGN KEY (getitem) REFERENCES GetItem(id)
);

CREATE TABLE WarehouseLastOrder(
    warehouse INT NOT NULL,
    last_complete_in INT NULL,
    last_submit_in INT NULL,
    last_create_in INT NULL,
    last_complete_out INT NULL,
    last_submit_out INT NULL,
    last_create_out INT NULL,

    PRIMARY KEY (warehouse),
    CONSTRAINT FOREIGN KEY (warehouse) REFERENCES Warehouse(id),
    CONSTRAINT FOREIGN KEY (last_complete_in) REFERENCES InOrder(id),
    CONSTRAINT FOREIGN KEY (last_submit_in) REFERENCES InOrder(id),
    CONSTRAINT FOREIGN KEY (last_create_in) REFERENCES InOrder(id),
    CONSTRAINT FOREIGN KEY (last_complete_out) REFERENCES OutOrder(id),
    CONSTRAINT FOREIGN KEY (last_submit_out) REFERENCES OutOrder(id),
    CONSTRAINT FOREIGN KEY (last_create_out) REFERENCES OutOrder(id)
);
