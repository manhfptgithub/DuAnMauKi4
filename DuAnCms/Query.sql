CREATE DATABASE EduSys ;
GO

USE EduSys ;
GO

SELECT DISTINCT YEAR(NgayKG) N'YEAR' FROM KhoaHoc ORDER BY YEAR(NgayKG) DESC

CREATE TABLE NhanVien(
	MaNV VARCHAR(7) PRIMARY KEY NOT NULL ,
	MatKhau NVARCHAR(30) NOT NULL ,
	HoTen NVARCHAR(50) NOT NULL ,
	VaiTro BIT NOT NULL -- Truong phong la 1 , 0 la nhan vien
)
GO

INSERT INTO NhanVien(MaNV , MatKhau , HoTen , VaiTro) 
VALUES ('NV001' , 'MKNV001' , N'Nguyễn Hùng Mạnh' , 1),
('NV002' , 'MKNV002' , N'Nguyễn Bảo Long' , 1),
('NV003' , 'MKNV003' , N'Cháng Văn Tình' , 0),
('NV004' , 'MKNV004' , N'Nguyễn Hữu Tiến' , 0),
('NV005' , 'MKNV005' , N'Nguyễn Ngọc Nga' , 1),
('NV006' , 'MKNV006' , N'Nguyễn Ngọc Nga' , 1)
GO


CREATE TABLE ChuyenDe(
	MaCD VARCHAR(7) PRIMARY KEY NOT NULL ,
	TenCD NVARCHAR(50) NOT NULL ,
	HocPhi FLOAT NOT NULL ,
	ThoiLuong INT NOT NULL ,
	Hinh NVARCHAR(255) NOT NULL ,
	MoTa NVARCHAR(255) NOT NULL 
)
GO
update ChuyenDe SET Hinh=N'javaSwing.png' where MaCD=N'CD002'

INSERT INTO ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa)
VALUES ('CD001' , N'Dự án với công nghệ Spring MVC' , 200000 , 6 , N'spring mvc.png',N'Chuyên đề thực làm dự án cho những người đã có kĩ năng với Spring MVC có thể hiểu hơn về nó .'),
('CD002' , N'Lập trình destop với swing' , 300000 , 5 ,N'javaSwing.png' ,N'Chuyên đề giúp người tham gia có thể tiếp cận với các kiến thức java swing một cách dễ dàng nhất .'),
('CD003' , N'Lập trình java cơ bản' ,500000 , 10 , N'java.jpg',N'Chuyên đề dành cho với mọi người muốn tìm hiều về java .'),
('CD004' , N'Lập trình java nâng cao' , 600000 , 8 , N'javaNangCao.png',N'Chuyên đề dành cho người đã có nền tảng về java và muốn học nâng cao lên .'),
 ('CD005' , N'Lập trình mạng với java' , 600000 , 9 , N'mangJava.jpg',N'Chuyên đề dành cho người muốn tìm hiểu về lập trình mạng với java một cách đúng nhất .')
go


CREATE TABLE KhoaHoc(
	MaKH INT PRIMARY KEY IDENTITY(1,1) NOT NULL ,
	TenKH NVARCHAR(255) ,
	MaCD VARCHAR(7) NOT NULL ,
	HocPhi FLOAT NOT NULL ,
	ThoiLuong INT NOT NULL ,
	NgayKG DATE NOT NULL ,
	GhiChu NVARCHAR(50) NULL ,
	MaNV VARCHAR(7) NOT NULL ,
	NgayTao DATE NOT NULL,
	FOREIGN KEY(MaCD) REFERENCES ChuyenDe(MaCD) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE
)
GO

SELECT * FROM KhoaHoc WHERE MaCD='CD002'

INSERT INTO KhoaHoc
             (TenKH , MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao )
VALUES (N'Dự án với công nghệ Spring MVC 1','CD001',200000,6,'2022-10-12',N'Dự án với công nghệ Spring MVC','NV001','2022-10-10'),
(N'Dự án với công nghệ Spring MVC 2','CD001',300000,6,'2022-10-14',N'Dự án với công nghệ Spring MVC','NV001','2022-10-12'),
(N'Dự án với công nghệ Spring MVC 3','CD001',400000,6,'2022-10-15',N'Dự án với công nghệ Spring MVC','NV001','2022-10-14'),
		(N'Lập trình destop với swing 1','CD002',300000,5,'2022-11-17',N'Lập trình destop với swing','NV002','2022-11-15'),
		(N'Lập trình destop với swing 2','CD002',400000,5,'2022-11-18',N'Lập trình destop với swing','NV002','2022-11-17'),
		(N'Lập trình destop với swing 3','CD002',500000,5,'2022-11-20',N'Lập trình destop với swing','NV002','2022-11-19'),
		(N'Lập trình destop với swing 4','CD002',600000,5,'2022-11-23',N'Lập trình destop với swing','NV002','2022-11-22'),
		(N'Lập trình java cơ bản 1','CD003',400000,10,'2022-12-02',N'Lập trình java cơ bản','NV003','2022-12-01'),
		(N'Lập trình java cơ bản 2','CD003',500000,10,'2022-12-04',N'Lập trình java cơ bản','NV003','2022-12-03'),
		(N'Lập trình java cơ bản 3','CD003',600000,10,'2022-12-07',N'Lập trình java cơ bản','NV003','2022-12-06'),
		(N'Lập trình java nâng cao 1','CD004',500000,8,'2023-03-08',N'Lập trình java nâng cao','NV004','2022-03-07'),
		(N'Lập trình java nâng cao 2','CD004',600000,8,'2023-03-12',N'Lập trình java nâng cao','NV004','2022-03-11'),
		(N'Lập trình java nâng cao 3','CD004',700000,8,'2023-03-15',N'Lập trình java nâng cao','NV004','2022-03-14'),
		(N'Lập trình java nâng cao 4','CD004',800000,8,'2023-03-18',N'Lập trình java nâng cao','NV004','2022-03-17'),
		(N'Lập trình mạng với java 1','CD005',600000,9,'2023-04-16',N'Lập trình mạng với java','NV005','2022-04-20'),
		(N'Lập trình mạng với java 2','CD005',700000,9,'2023-04-26',N'Lập trình mạng với java','NV005','2022-04-25'),
		(N'Lập trình mạng với java 3','CD005',800000,9,'2023-04-30',N'Lập trình mạng với java','NV005','2022-04-29')
		GO


CREATE TABLE NguoiHoc(
	MaNH VARCHAR(7) PRIMARY KEY NOT NULL ,
	HoTen NVARCHAR(50) NOT NULL ,
	NgaySinh DATE NOT NULL ,
	GioiTinh BIT NOT NULL , -- 1 là nam 0 là nữ
	DienThoai NVARCHAR(50) NOT NULL ,
	Email NVARCHAR(50) NOT NULL ,
	GhiChu NVARCHAR(MAX) NULL ,
	MaNV VARCHAR(7) NOT NULL ,
	NgayDK DATE NOT NULL ,
	FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE 
)
GO

INSERT INTO NguoiHoc
             (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK)
VALUES ('NH001',N'Cháng Văn Tình','2004/12/25',1,'0123456789','tinhcv@gmail.com',N'Ghi chú','NV001','2022/10/22'),
		('NH002',N'Nguyễn Văn Nam','2001-10-21',1,'0123426789','namnv@gmail.com',N'Ghi chú','NV002','2022-10-25'),
		('NH003',N'Đào Mai Anh','1978-06-12',0,'0123455589','tuantv@gmail.com',N'Ghi chú','NV003','2022-10-27'),
		('NH004',N'Nguyễn Bảo Long','2000-05-22',1,'0122256789','longlb@gmail.com',N'Ghi chú','NV004','2022-10-29'),
		('NH005',N'Nguyễn Hữu Tiến','2001-12-25',1,'0124456789','tiennh@gmail.com',N'Ghi chú','NV005','2022-10-30'),
		('NH006',N'Nguyễn Văn Tuân','2002-11-25',1,'0125556789','tuannv@gmail.com',N'Ghi chú','NV005','2022-11-01') ,
		('NH007',N'Lê Văn Nam','2003-10-25',1,'0125556789','namlv@gmail.com',N'Ghi chú','NV001','2022-11-01') ,
		('NH008',N'Đào Duy Khải','2004-09-25',1,'0125556789','khaidd@gmail.com',N'Ghi chú','NV002','2022-11-01') ,
		('NH009',N'Lê Bá Thế','2006-08-15',1,'0125556789','thelb@gmail.com',N'Ghi chú','NV004','2022-11-01') ,
		('NH010',N'Đào Khắc Quang','2002-08-22',1,'0125556789','quangdk@gmail.com',N'Ghi chú','NV001','2022-11-01') ,
		('NH011',N'Lương Duy Khải','2002-01-11',1,'0125556789','khaild@gmail.com',N'Ghi chú','NV002','2022-11-01') ,
		('NH012',N'Nguyễn Hữu Tú','2004-02-21',1,'0125556789','tunh@gmail.com',N'Ghi chú','NV003','2022-11-01') ,
		('NH013',N'Lê Nhật Kim Anh','2005-03-29',0,'0125556789','anhlnk@gmail.com',N'Ghi chú','NV005','2022-11-01') ,
		('NH014',N'Nguyễn Tuấn Hào','2001-05-26',1,'0125556789','haont@gmail.com',N'Ghi chú','NV001','2022-11-01') ,
		('NH015',N'Đào Minh Tuấn','2000-09-11',1,'0125556789','tuandm@gmail.com',N'Ghi chú','NV002','2022-11-01') ,
		('NH016',N'Đào Anh Tú','2000-09-11',1,'0125226789','tuda@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH017',N'Đoàn Trung Hiếu','2000-09-11',1,'0125226119','hieudt@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH018',N'Dương Anh Tài','2001-09-11',1,'0125226789','taida@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH019',N'Nguyễn Thu Trang','2002-11-11',1,'0125226789','trangnt@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH020',N'Hà Đình Minh Mạnh','2000-09-12',1,'0125226789','tuda@gmail.com',N'Ghi chú','NV003','2022-11-05'),
		('NH021',N'Nguyễn Trung Kiên','2000-09-15',1,'0125226789','kiennk@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH022',N'Hà Minh Tuấn','2001-09-11',1,'0125226789','tuanhm@gmail.com',N'Ghi chú','NV003','2022-12-01'),
		('NH023',N'Lương Minh Trang','2000-09-11',1,'0125226789','tranglm@gmail.com',N'Ghi chú','NV003','2022-11-05'),
		('NH024',N'Lê Mông Chí Bằng','2000-09-11',1,'0125226789','banglmc@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH025',N'Đào Hải Nam','2000-09-11',1,'0125226789','namdh@gmail.com',N'Ghi chú','NV003','2022-11-01'),
		('NH026',N'Hoàng Hải Lâm','2000-11-11',1,'0125226789','lamhh@gmail.com',N'Ghi chú','NV003','2022-11-01')
		go



SELECT * FROM KhoaHoc WHERE MaCD=N'CD002'
GO


CREATE TABLE HocVien(
	MaHV INT PRIMARY KEY IDENTITY(1,1) NOT NULL ,
	MaKH INT NOT NULL ,
	MaNH VARCHAR(7) NOT NULL ,
	Diem FLOAT NOT NULL ,
	FOREIGN KEY(MaNH) REFERENCES NguoiHoc(MaNH) ON DELETE NO ACTION ON UPDATE CASCADE ,
	FOREIGN KEY(MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE
)
GO
SELECT * FROM HocVien WHERE MaHV=1


INSERT INTO HocVien
             ( MaKH, MaNH, Diem)
VALUES (1,'NH001',8),
	(4,'NH002',8),
	(5,'NH003',7),
	(6,'NH004',5),
	(8,'NH005',8),
	(9,'NH007' , 10),
	(9,'NH008',9),
	(9,'NH008',9),
	(11,'NH009',9)
GO	
		



-- report : nam , so_luong , dau_tien , cuoi_cung
CREATE PROCEDURE SP_ThongKeNguoiHoc
AS 
BEGIN
	SELECT 
		YEAR(NgayDK) AS N'Nam' ,
		COUNT(*) AS N'SoLuong' ,
		MIN(NgayDK) AS N'DauTien',
		MAX(NgayDK) AS N'CuoiCung'
	FROM NguoiHoc 
	GROUP BY YEAR(NgayDK)
END
GO

-- REPORT : MaNH - Hoten - diem
CREATE PROCEDURE SP_BangDiem
(@MaKH INT)
AS
BEGIN
	SELECT 
		nh.MaNH ,
		nh.HoTen, 
		hv.Diem
	FROM HocVien hv JOIN NguoiHoc nh ON hv.MaNH = nh.MaNH
	WHERE hv.MaKH = @MaKH
	ORDER BY hv.Diem DESC
END
GO

-- report : ten_cd - so_hv - thap_nhat - caoNhat - trungbinh

CREATE PROC SP_ThongKeDiem
AS
BEGIN
	SELECT 
		TenCD N'ChuyenDe' ,
		COUNT(MaHV) N'SoHV' ,
		MIN(Diem) N'ThapNhat' ,
		MAX(Diem) N'CaoNhat' , 
		AVG(Diem) N'TrungBinh'
	FROM KhoaHoc kh JOIN HocVien hv ON kh.MaKH = hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD
	GROUP BY TenCD
END
GO

-- report : ten_cd , so_kh , so_hv , doanh_thu , thap_nhat , cao_nhat , trung_binh
ALTER PROCEDURE SP_ThongKeDoanhThu
(@Year INT)
AS
BEGIN
	SELECT 
		cd.TenCD AS 'ChuyenDe' ,
		COUNT(DISTINCT kh.MaKH) AS 'SoKH' ,
		COUNT(hv.MaHV) AS 'SoHV',
		SUM(kh.HocPhi) AS 'DoanhThu' ,
		MIN(kh.HocPhi) AS 'ThapNhat' ,
		MAX(kh.HocPhi) AS 'CaoNhat' ,
		AVG(kh.HocPhi) AS 'TrungBinh'
	FROM KhoaHoc kh 
	JOIN HocVien hv ON kh.MaKH = hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD
END

SELECT MatKhau, HoTen, VaiTro
FROM   NhanVien
WHERE (MaNV = N'NV001')

UPDATE NhanVien
SET       MatKhau = N'mknv001', HoTen = N'Nguyễn Văn A', VaiTro = 1
WHERE (MaNV = N'NV001')

-- INSERT NHANVIEN
INSERT INTO NhanVien
             (MaNV, MatKhau, HoTen, VaiTro)
VALUES (N'NV001', N'mknv001', N'Nguyễn Văn A', 1)