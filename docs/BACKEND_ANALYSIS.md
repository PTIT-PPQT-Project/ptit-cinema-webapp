# 📊 PHÂN TÍCH BACKEND - PTIT CINEMA WEBAPP

## 🛠️ **Công nghệ sử dụng**

### **Framework & Version**
- **Java Spring Boot 4.0.0**
- **Java 17**
- **Maven** (build tool với wrapper)

### **Dependencies chính**

#### Core Framework:
- `spring-boot-starter-data-jdbc` - Xử lý database với JDBC
- `spring-boot-starter-webmvc` - REST API framework
- `spring-boot-starter-security` - Authentication & Authorization

#### Security & JWT:
- `jjwt-api` 0.12.6 - JWT API
- `jjwt-impl` 0.12.6 - JWT Implementation
- `jjwt-jackson` 0.12.6 - JWT JSON processing

#### Database:
- `mssql-jdbc` - Microsoft SQL Server JDBC Driver

#### Documentation:
- `springdoc-openapi-starter-webmvc-ui` 2.8.5 - Swagger/OpenAPI

---

## 🏗️ **Kiến trúc ứng dụng**

Dự án tuân theo kiến trúc **Layered Architecture** (MVC pattern):

```
backend/
├── src/main/java/com/example/ptitcinema/
│   ├── config/                    # Cấu hình
│   │   ├── DataSourceConfig.java
│   │   ├── SecurityConfig.java    # Spring Security + JWT
│   │   ├── JwtUtil.java           # JWT utilities
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtAuthenticationEntryPoint.java
│   │   └── SwaggerConfig.java     # API documentation
│   │
│   ├── controller/                # REST Controllers (API Layer)
│   │   ├── AuthController.java    # /auth/* endpoints
│   │   ├── MovieController.java   # /movies/* endpoints
│   │   ├── BookingController.java # /bookings/* endpoints
│   │   ├── ShowtimeController.java
│   │   ├── SeatController.java
│   │   └── UserController.java
│   │
│   ├── service/                   # Business Logic Layer
│   │   ├── IMovieService.java
│   │   ├── IBookingService.java
│   │   ├── IShowtimeService.java
│   │   ├── ISeatService.java
│   │   ├── IUserService.java
│   │   └── impl/                  # Service implementations
│   │       ├── MovieService.java
│   │       ├── BookingService.java
│   │       ├── ShowtimeService.java
│   │       ├── SeatService.java
│   │       └── UserService.java
│   │
│   ├── repository/                # Data Access Layer
│   │   ├── IMovieRepository.java
│   │   ├── IBookingRepository.java
│   │   ├── IShowtimeRepository.java
│   │   ├── ISeatRepository.java
│   │   └── IUserRepository.java
│   │
│   └── model/                     # Entities & DTOs
│       ├── Movie.java
│       ├── Booking.java
│       ├── Showtime.java
│       ├── Cinema.java
│       ├── User.java
│       └── dto/                   # Data Transfer Objects
│           ├── MovieDetailDto.java
│           ├── MovieListItemDto.java
│           ├── MovieRequest.java
│           ├── LoginRequest.java
│           ├── LoginResponse.java
│           └── ...
│
└── src/main/resources/
    └── application.properties     # Cấu hình ứng dụng
```

---

## 🔐 **Bảo mật**

### **JWT Authentication (Stateless)**
- Token-based authentication
- Bearer token trong Authorization header
- Token expiration: 1 hour (3600 seconds)
- Secret key: 256-bit HMAC SHA

### **Spring Security Configuration**
- CSRF disabled (REST API)
- Session management: STATELESS
- Public endpoints: `/auth/login`, `/auth/register`
- Protected endpoints: Tất cả các endpoint khác yêu cầu JWT token

### **Security Flow**
1. User login → Nhận JWT token
2. Mỗi request gửi token trong header: `Authorization: Bearer <token>`
3. `JwtAuthenticationFilter` validate token
4. Nếu valid → Set authentication context
5. Controller xử lý request

---

## 💾 **Database Configuration**

### **SQL Server**
```properties
Host: localhost:1433
Database: PTIT_Cinema
Username: sa
Password: @Dmin_B_72025
Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### **Connection Pool (HikariCP)**
- Maximum pool size: 10
- Minimum idle: 5
- Connection timeout: 20000ms

### **Database Script**
File khởi tạo database: `db/PTIT-Cinema-Initial_Scripts.sql`

---

## 🚀 **Cách chạy Backend**

### **Yêu cầu hệ thống**
1. ✅ Java 17 hoặc cao hơn
2. ✅ SQL Server đang chạy trên localhost:1433
3. ✅ Database `PTIT_Cinema` đã được tạo và import script

### **Các bước chạy**

#### **1. Khởi tạo Database**
```bash
# Chạy SQL script để tạo database và tables
# File: db/PTIT-Cinema-Initial_Scripts.sql
```

#### **2. Build project**
```bash
cd backend
./mvnw clean install -DskipTests
```

Trên Windows:
```bash
mvnw.cmd clean install -DskipTests
```

#### **3. Chạy ứng dụng**
```bash
./mvnw spring-boot:run
```

Hoặc:
```bash
mvnw.cmd spring-boot:run
```

#### **4. Truy cập ứng dụng**
- **API Base URL:** `http://localhost:8091/PTITCinema`
- **Swagger UI:** `http://localhost:8091/PTITCinema/swagger-ui.html`
- **API Docs (JSON):** `http://localhost:8091/PTITCinema/api-docs`

---

## 📡 **API Endpoints**

### **Authentication** (`/auth`)
- `POST /auth/login` - Đăng nhập (Public)
- `POST /auth/register` - Đăng ký (Public)

### **Movies** (`/movies`)
- `GET /movies` - Lấy danh sách phim
- `GET /movies/{id}` - Lấy chi tiết phim
- `POST /movies` - Tạo phim mới (MANAGER/ADMIN)
- `PUT /movies/{id}` - Cập nhật phim (MANAGER/ADMIN)
- `DELETE /movies/{id}` - Xóa phim (MANAGER/ADMIN)

### **Showtimes** (`/showtimes`)
- CRUD operations cho lịch chiếu

### **Bookings** (`/bookings`)
- CRUD operations cho đặt vé

### **Seats** (`/seats`)
- Quản lý ghế ngồi

### **Users** (`/users`)
- Quản lý người dùng

---

## ⚙️ **Cấu hình đặc biệt**

### **Application Properties**
```properties
# Server
server.port=8091
server.servlet.context-path=/PTITCinema

# Application Mode
app.mode=development  # Auto-login enabled

# Encoding
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

# Static Resources
spring.web.resources.static-locations=classpath:/static/
spring.web.resources.cache.period=3600

# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.operationsSorter=method
```

---

## 🐛 **Các vấn đề đã sửa**

### **1. Maven Wrapper Configuration**
**Lỗi:** `cannot read distributionUrl property in maven-wrapper.properties`

**Nguyên nhân:** Format file sai (có khoảng trắng xung quanh dấu `=`)

**Giải pháp:** Sửa format file `.mvn/wrapper/maven-wrapper.properties`

### **2. Missing Dependencies**
**Lỗi:** `package org.springframework.security.* does not exist`

**Nguyên nhân:** Thiếu Spring Security và JWT dependencies

**Giải pháp:** Thêm vào `pom.xml`:
- `spring-boot-starter-security`
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson`

### **3. Wrong Package Names**
**Lỗi:** `duplicate class: com.example.config.JwtUtil`

**Nguyên nhân:** Các file config có package `com.example.config` thay vì `com.example.ptitcinema.config`

**Giải pháp:** Sửa package name trong:
- `JwtUtil.java`
- `JwtAuthenticationFilter.java`
- `JwtAuthenticationEntryPoint.java`
- `SecurityConfig.java`

### **4. JWT API Version Mismatch**
**Lỗi:** `cannot find symbol: method parserBuilder()`

**Nguyên nhân:** JWT API đã thay đổi trong version 0.12.x

**Giải pháp:** Cập nhật code:
- `Jwts.parserBuilder()` → `Jwts.parser()`
- `setSigningKey()` → `verifyWith()`
- `parseClaimsJws()` → `parseSignedClaims()`
- `getBody()` → `getPayload()`
- `setSubject()` → `subject()`
- `setIssuedAt()` → `issuedAt()`
- `setExpiration()` → `expiration()`

### **5. Missing Import**
**Lỗi:** `cannot find symbol: variable Arrays`

**Giải pháp:** Thêm `import java.util.Arrays;` vào `AuthController.java`

---

## 📝 **Lưu ý quan trọng**

1. **Database phải được setup trước** khi chạy backend
2. **SQL Server phải đang chạy** trên port 1433
3. **Java 17** là bắt buộc (không tương thích với Java 8 hoặc 11)
4. **JWT token** có thời hạn 1 giờ
5. **Development mode** đang bật - tắt khi deploy production
6. **Role-based access control** chưa được implement đầy đủ (có comment trong code)

---

## 🔍 **Testing với Swagger**

1. Truy cập: `http://localhost:8091/PTITCinema/swagger-ui.html`
2. Test endpoint `/auth/register` để tạo user
3. Test endpoint `/auth/login` để lấy JWT token
4. Click "Authorize" button, nhập: `Bearer <your-token>`
5. Test các protected endpoints

---

## 📦 **Build Production**

```bash
# Build JAR file
./mvnw clean package -DskipTests

# JAR file sẽ được tạo tại:
# target/PTITCinema-0.0.1-SNAPSHOT.jar

# Chạy JAR file
java -jar target/PTITCinema-0.0.1-SNAPSHOT.jar
```

---

## 🎯 **Tổng kết**

✅ **Backend đã được phân tích và sửa lỗi thành công**
✅ **Build thành công với Maven**
✅ **Sẵn sàng để chạy và test**

**Công nghệ:** Java 17 + Spring Boot 4.0 + Spring Security + JWT + SQL Server
**Kiến trúc:** Layered Architecture (Controller → Service → Repository)
**Bảo mật:** JWT-based stateless authentication
**Documentation:** Swagger/OpenAPI tích hợp sẵn
