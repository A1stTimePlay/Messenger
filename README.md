# Messenger
1. Cấu hình server:
- Chạy query ./java/com.example.messager/Server/RESTfull/Database Query file.sql để tạo server (sử dụng MySQL)

2. Chạy Webservice
- Vào thư mục ./java/com.example.messager/Server/RESTfull 
- Chạy lệnh npm install
- Mở file ./java/com.example.messager/Server/RESTfull/server.js edit dòng 17 về IP local của máy đang dùng làm máy chủ
- Chạy lệnh npm run start
3. Chỉnh endpoint cho retrofit:
- Mở file ./java/com.example.messager/Utils/RetrofitUtils.java edit dòng 9 về IP local của máy đang dùng làm máy chủ
4. Chạy ứng dụng trên máy ảo
5. Các chức năng trong beta 1.1:
- Nhập tin nhắn + Gửi tin nhắn lên server và hiển thị tin nhắn trên recycler view (tin nhắn mặc định có người gửi là 1 và người nhận là 2)
- Lấy các tin nhắn từ server và show trong recycler view theo trình tự thời gian (mặc định lấy các tin nhắn của 2 người là 1 và 2)
- Xác minh tài khoản (đăng nhập)
- Tạo người dùng mới
* Known bug: Chưa thể xác minh tài khoản do dữ liệu lấy từ server luôn khác dữ liệu nhập, dù đã kiểm tra nhiều lần là 2 thông tin trên hoàn toàn giống nhau

