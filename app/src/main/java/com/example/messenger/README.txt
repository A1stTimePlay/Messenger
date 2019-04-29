cách khỏi động rest server:
1. vào thự mục ./Messenger\app\src\main\java\com\example\messenger\Server\RESTful bật gitbash here
2. cài dependancy bằng lệnh npm install
3. chỉnh lại ip của server trong file server.js theo hướng dẫn trong file
4. chạy server bằng lệnh npm run start
5. test HTTP get bằng cách vào browser rồi chạy đường dẫn "<ip>:3000/Account" lệnh trên sẽ trả về tất cả transaction trong bảng account dưới dạng json
                                                          "<ip>:3000/Account/<AccountID>" lệnh trên sẽ trả transaction có AccountID trùng với AccountID đã nhập
6. Test REST bằng curl
                                                          curl -i -X POST -H 'Content-Type: application/json' -d '{"AccountID": "A3", "Username": "abc", "Password": "123"}' http://192.168.1.16:3000/Account
                                                          curl -i -X DELETE http://192.168.1.16:3000/Account/a3  // chỉ xóa được các account chưa có message vì khoái ngoại
                                                          curl -i -X GET http://192.168.1.16:3000/Account/a1

