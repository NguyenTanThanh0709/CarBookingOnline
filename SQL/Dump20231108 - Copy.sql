
CREATE TABLE `Đặt_Vé` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ngày` datetime(6) DEFAULT NULL,
  `mô_tả` text,
  `giá` bigint(20) DEFAULT NULL,
  `isProtect` bit(1) DEFAULT NULL,
  `trạng_thái` varchar(50) DEFAULT NULL,
  `mã_xe` varchar(255) DEFAULT NULL,
  `mã_chuyến` bigint(20) DEFAULT NULL,
  `mã_điểm_dừng` bigint(20) DEFAULT NULL,
  `mã_điểm_đón` bigint(20) DEFAULT NULL,
  `mã_khách_hàng` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK128686spaxk6s1xffsqyb2fix` (`mã_xe`),
  KEY `FKbjr8bmbrgd65nxrekrtka0kfd` (`mã_chuyến`),
  KEY `FKtie0mooeplx1wu74mayeocogu` (`mã_điểm_dừng`),
  KEY `FK5kfyqs9rylc05mubq3isqrus7` (`mã_điểm_đón`),
  KEY `FKksyqug2s7al3nwt9laoclhp6f` (`mã_khách_hàng`),
  CONSTRAINT `FK128686spaxk6s1xffsqyb2fix` FOREIGN KEY (`mã_xe`) REFERENCES `Xe` (`id`),
  CONSTRAINT `FK5kfyqs9rylc05mubq3isqrus7` FOREIGN KEY (`mã_điểm_đón`) REFERENCES `Chi_Tiết_Điểm_Đón` (`id`),
  CONSTRAINT `FKbjr8bmbrgd65nxrekrtka0kfd` FOREIGN KEY (`mã_chuyến`) REFERENCES `Chuyến_Đi` (`id`),
  CONSTRAINT `FKksyqug2s7al3nwt9laoclhp6f` FOREIGN KEY (`mã_khách_hàng`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtie0mooeplx1wu74mayeocogu` FOREIGN KEY (`mã_điểm_dừng`) REFERENCES `Chi_Tiết_Điểm_Trả` (`id`)
)
CREATE TABLE `đăt_chỗ_ngồi` (
  `mã_đặt_xe` bigint(20) NOT NULL,
  `mã_chỗ_ngồi` bigint(20) NOT NULL,
  PRIMARY KEY (`mã_đặt_xe`,`mã_chỗ_ngồi`),
  KEY `FK22okdpk3nw2twqxmps0lp0j0j` (`mã_chỗ_ngồi`),
  CONSTRAINT `FK22okdpk3nw2twqxmps0lp0j0j` FOREIGN KEY (`mã_chỗ_ngồi`) REFERENCES `chỗ_ngồi` (`id`),
  CONSTRAINT `FKqngtja5c01j14c58a4cix9tss` FOREIGN KEY (`mã_đặt_xe`) REFERENCES `Đặt_Vé` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Xe` (
  `id` varchar(255) NOT NULL,
  `tình trạng` bit(1) DEFAULT NULL,
  `biển_số_xe` varchar(255) DEFAULT NULL,
  `sdt_nhà_xe` varchar(255) DEFAULT NULL,
  `mã_loại_xe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlexv2k05j0ip9wpitsni3ssnj` (`biển_số_xe`),
  KEY `FKqr82uebs1lsiy79ypr01t2d5g` (`sdt_nhà_xe`),
  KEY `FKju5hovvshod2k3iq22ep3ssbj` (`mã_loại_xe`),
  CONSTRAINT `FKju5hovvshod2k3iq22ep3ssbj` FOREIGN KEY (`mã_loại_xe`) REFERENCES `Loại_Xe` (`id`),
  CONSTRAINT `FKqr82uebs1lsiy79ypr01t2d5g` FOREIGN KEY (`sdt_nhà_xe`) REFERENCES `Nhà_Xe` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Nhà_Xe` (
  `phone` varchar(255) NOT NULL,
  `mô tả` text,
  `email` varchar(255) DEFAULT NULL,
  `tên` varchar(255) DEFAULT NULL,
  `trạng thái` bit(1) DEFAULT NULL,
  `vnp_HashSecret` varchar(255) DEFAULT NULL,
  `vnp_TmnCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `Chi_Tiết_Điểm_Trả` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tên` varchar(255) DEFAULT NULL,
  `giờ` time(6) DEFAULT NULL,
  `mã_tuyến` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKadqptevyhoy240ra9lq6a9440` (`mã_tuyến`),
  CONSTRAINT `FKadqptevyhoy240ra9lq6a9440` FOREIGN KEY (`mã_tuyến`) REFERENCES `Tuyến_Đường` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Chi_Tiết_Điểm_Đón` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tên` varchar(255) DEFAULT NULL,
  `giờ` time(6) DEFAULT NULL,
  `mã_tuyến` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh9vs6ffpp7i6ewfi19e9eumn9` (`mã_tuyến`),
  CONSTRAINT `FKh9vs6ffpp7i6ewfi19e9eumn9` FOREIGN KEY (`mã_tuyến`) REFERENCES `Tuyến_Đường` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Danh_sách_chỗ_đã_đặt_cho_tuyến`(
  `mã_chuyến` bigint(20) NOT NULL,
  `mã_chỗ_ngồi` bigint(20) NOT NULL,
  PRIMARY KEY (`mã_chuyến`,`mã_chỗ_ngồi`),
  KEY `FKc4xuiuyljhu9wml1dgnp17o0k` (`mã_chỗ_ngồi`),
  CONSTRAINT `FK4mnsp1o1aia9kderuovk8c4k` FOREIGN KEY (`mã_chuyến`) REFERENCES `Chuyến_Đi` (`id`),
  CONSTRAINT `FKc4xuiuyljhu9wml1dgnp17o0k` FOREIGN KEY (`mã_chỗ_ngồi`) REFERENCES `chỗ_ngồi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Chuyến_Đi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ngày` datetime(6) DEFAULT NULL,
  `trạng thái` bit(1) DEFAULT NULL,
  `mã_xe` varchar(255) DEFAULT NULL,
  `mã_tuyến` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKibg27p2xas3b7p8pxxcd7adba` (`mã_xe`),
  KEY `FKd63hvhp0qa15tcbs6r0v420qj` (`mã_tuyến`),
  CONSTRAINT `FKd63hvhp0qa15tcbs6r0v420qj` FOREIGN KEY (`mã_tuyến`) REFERENCES `Tuyến_Đường` (`id`),
  CONSTRAINT `FKibg27p2xas3b7p8pxxcd7adba` FOREIGN KEY (`mã_xe`) REFERENCES `Xe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Thanh_Toán` (
  `id` bigint(20) NOT NULL,
  `tiền` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `trạng thái` varchar(255) DEFAULT NULL,
  `transDate` varchar(255) DEFAULT NULL,
  `trantype` varchar(255) DEFAULT NULL,
  `mã_đặt_vé` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qq21idr3pxkcr2k3dewxakkjc` (`mã_đặt_vé`),
  CONSTRAINT `FKt30qv3axmqwhk1wag867yxqum` FOREIGN KEY (`mã_đặt_vé`) REFERENCES `Đặt_Vé` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


"mã_khuyến_mãi" [name: "FKp5dp0rpwv55oiok14l40gfirq"]

"mã_tuyến" bigint(20) [default: NULL]
"mã_tuyến" [name: "FKd63hvhp0qa15tcbs6r0v420qj"]
"ngày" datetime(6) [default: NULL]

CREATE TABLE `Khuyến_Mãi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(20) DEFAULT NULL,
  `mô tả` text,
  `giá_giảm` decimal(38,2) DEFAULT NULL,
  `trạng_thái` bit(1) DEFAULT NULL,
  `mã_nhà_xe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yi9862p4dvde0uh59bs79vdi` (`mã_nhà_xe`),
  CONSTRAINT `FK7yi9862p4dvde0uh59bs79vdi` FOREIGN KEY (`mã_nhà_xe`) REFERENCES `Nhà_Xe` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chỗ_ngồi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tên` varchar(20) DEFAULT NULL,
  `mã_xe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrvu18fkfs7ixs2n7wod9r32x` (`mã_xe`),
  CONSTRAINT `FKsrvu18fkfs7ixs2n7wod9r32x` FOREIGN KEY (`mã_xe`) REFERENCES `Xe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tokenType` enum('BEARER') DEFAULT NULL,
  `mã_KH` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3wi2t4g8oiplxjflw3o2lkv2y` (`token`),
  KEY `FKiiyr9nhulmfrvje08nvravy02` (`mã_KH`),
  CONSTRAINT `FKiiyr9nhulmfrvje08nvravy02` FOREIGN KEY (`mã_KH`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `token_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Tuyến_Đường` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `số_giờ` int(11) DEFAULT NULL,
  `nơi_đến` varchar(255) DEFAULT NULL,
  `giờ_đến` time(6) DEFAULT NULL,
  `nơi_đi` varchar(255) DEFAULT NULL,
  `giờ_đi` time(6) DEFAULT NULL,
  `giá` int(11) DEFAULT NULL,
  `trạng_thái` bit(1) DEFAULT NULL,
  `urlimage` varchar(255) DEFAULT NULL,
  `sdt_nhà_xe` varchar(255) DEFAULT NULL,
  `mã_khuyến_mãi` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtrvomwrd100aj9y8cm00ilnh0` (`sdt_nhà_xe`),
  KEY `FKp5dp0rpwv55oiok14l40gfirq` (`mã_khuyến_mãi`),
  CONSTRAINT `FKp5dp0rpwv55oiok14l40gfirq` FOREIGN KEY (`mã_khuyến_mãi`) REFERENCES `Khuyến_Mãi` (`id`),
  CONSTRAINT `FKtrvomwrd100aj9y8cm00ilnh0` FOREIGN KEY (`sdt_nhà_xe`) REFERENCES `Nhà_Xe` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `Loại_Xe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tên` varchar(255) DEFAULT NULL,
  `số_chỗ` int(11) DEFAULT NULL,
  `trạng_thái` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `bằng_lái` varchar(255) DEFAULT NULL,
  `tên` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `SĐT` varchar(20) DEFAULT NULL,
  `điểm` int(11) DEFAULT NULL,
  `role` enum('ADMIN','DRIVER','OWNER','STAFF','USER') DEFAULT NULL,
  `trạng_thái` bit(1) DEFAULT NULL,
  `sđt_nhà xe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe6gkqunxajvyxl5uctpl2vl2p` (`email`),
  UNIQUE KEY `UK8f9egnwceetj2aq0hwqd8togf` (`phone`),
  KEY `FK4hxjoiymxx8bgkn1hlkjdcu72` (`sđt_nhà xe`),
  CONSTRAINT `FK4hxjoiymxx8bgkn1hlkjdcu72` FOREIGN KEY (`sđt_nhà xe`) REFERENCES `Nhà_Xe` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tài_xế_cho_chuyến_đi` (
  `mã_chuyến` bigint(20) NOT NULL,
  `mã_tài_xế` int(11) NOT NULL,
  `ngày_đi` datetime(6) DEFAULT NULL,
  `trạng_thái` bit(1) DEFAULT NULL,
  PRIMARY KEY (`mã_chuyến`,`mã_tài_xế`),
  KEY `FKeerub6vu23idg6j4qmckbjv0y` (`mã_tài_xế`),
  CONSTRAINT `FK4bognvj83cb9sbkb2it4gn1l2` FOREIGN KEY (`mã_chuyến`) REFERENCES `Chuyến_Đi` (`id`),
  CONSTRAINT `FKeerub6vu23idg6j4qmckbjv0y` FOREIGN KEY (`mã_tài_xế`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
