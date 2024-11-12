
export  function request(config) {
    return new Promise((resolve, reject) => {
        //1.创建axios的实例
        const instance = axios.create({
            baseURL:'http://127.0.0.1:8090',
            timeout:5000,
            withCredentials: true,
            headers: {
                'Content-Type': 'application/json',
                "Authorization":'Access-Control-Request-Headers',
                // 设置后端需要的传参类型
                'content-type': 'application/json; charset=utf-8',
                // "Lux-mateApiNonce": uuid, //每次调取接口生成的uuid
                // "Lux-mateApiSignature": mateApi, //md5(method+requestURI+nonce+timestamp+appId+appSecret)
                // "Lux-mateApiTimestamp": timestamp, // 每次调取接口的时间戳（秒级）
            },
        })
        // instance.interceptors.request.use(config => {
        //         // console.log(config);  //拦截下来的是这个请求的所有配置，发送成功但是被拦截了
        //         return config  //return 出去的话就可以拿到请求的数据了
        //     },
        //     err=>{
        //         // console.log(err);  //来到这里的情况比较少
        //     })
        //
        // //2.2响应拦截的作用
        // instance.interceptors.response.use(result => {
        //         return result.data
        //     },
        //     err => {
        //         console.log(err);
        //     })
        //2.发送真正的请求
        instance(config)
            .then(res => {
                resolve(res);
            })
            .catch(err => {
                reject(err);
            })
    })
}