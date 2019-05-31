## Messenger
# Server
1. Cấu hình server: chạy ./Server/Database Query file.sql trong MySQL
2. Cài đặt các dependancies cần thiết cho server: Mở gitbash trong đường dẫn ./Server/RESTful ; chạy lệnh npm install
3. Chạy server: Mở gitbash trong đường dẫn ./Server/RESTful ; chạy lệnh npm run start
# Client
1. Chỉnh endpoint của Retrofit:
- Mở file ./app/src/main/java/com.example.messenger/Utils/RetrofitUtils.java
- Chỉnh dòng 9 về local ip của máy server
2. Chỉnh endpoint của Socket:
- Mở file MainActivity.java
- Chỉnh dòng 47 về local ip của máy server
3. Chạy project bằng máy có API tối thiểu là 26, tối đa là 27
