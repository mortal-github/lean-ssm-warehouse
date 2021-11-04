INSERT INTO
GoodsType(name, description,length, width, height)
VALUES
("红米4A", "红米系列手机", 10, 5, 2),
("荣耀青春版10", "荣耀系列手机", 15, 7, 2),
("华硕ViVoBook", "华硕电脑", 40, 35, 5),
('小米储藏柜', "刚好能储藏10个红米4A手机", 105, 55, 25),
('荣耀储藏柜', "刚好能储藏10个荣耀青春版10手机", 155, 75, 25),
('华硕储藏柜', "刚好能储藏10个华硕电脑", 405, 355, 55)
;

DELETE FROM Detail;
INSERT INTO
Detail(goods_type, name, value)
VALUES
(1, "dimension", "1092,1080"),
(2, "dimension", "1692,1999"),
(3, "dimension", "2000,2201"),
(1, "price", "1000"),
(2, "price", "1000"),
(3, "price", "2000"),
(1, "group", "mobile computer"),
(2, "group", "mobile computer"),
(3, "group", "desktop computer"),
(4, "inner_length" , "100"),
(4, "inner_width", "50"),
(4, "inner_height", "20"),
(5, "inner_length", "150"),
(5, "inner_width", "70"),
(5, "inner_height", "20"),
(6, "inner_length", "400"),
(6, "inner_width", "350"),
(6, "inner_height", "50")
;