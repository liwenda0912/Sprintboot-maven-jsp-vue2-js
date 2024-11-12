import jwtDecode from 'jwt-decode'
let code;
code = jwtDecode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MzE5ODMyMzJ9.O4r4FTHhxLo2wqMcJbzKI0pbnafer-Qr_LjAp1AZqdw");
console.log(code)// 就可以解析成功了