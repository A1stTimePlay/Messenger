# Messenger
1. Cấu hình server:
- Chạy query ./Server/Database Query file.sql trong Mysql

2. Chạy Webservice (yêu cầu có git bash và nodejs)
- Vào thư mục ./Server/RESTfull 
- Chạy lệnh: npm install
- Mở file ./Server/RESTfull/server.js edit dòng 17 về IP local của máy đang dùng làm máy chủ
- Chạy lệnh: npm run start
3. Chỉnh endpoint của retrofit
- Mở file ./java/com.example.messenger/Utils/RetrofitUtils.java edit dòng 9 về IP local của máy đang dùng làm máy chủ
4. Chạy project bằng máy ảo có api tối thiểu là 26
