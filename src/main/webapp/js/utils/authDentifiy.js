import {getCookie, getCookieRefresh, setCookie, tokenDetect} from "./Cookie.js";
import {request} from "./request.js";



// 重新获取token
export function authDentifiy(fun) {
    let te;
    let refresh=getCookieRefresh();//获取刷新token
    tokenDetect(getCookie(),refresh).then(r => {
        if (r === false) {
            //判断refresh_token是否过期
            tokenDetect(getCookieRefresh(),refresh).then(res => {
                if ( res=== false) {
                    Vue.prototype.$message({
                        message: "请重新登录！",
                        type: "error",
                        center: true
                    })
                } else {
                    request({
                        method: 'Post',
                        url: '/User/login',
                        headers: {
                            'Content-Type': 'application/json',
                            "Authorization": 'Access-Control-Request-Headers'
                        },
                        data: {
                            token: getCookie(),
                            refresh: getCookieRefresh()
                        }
                    }).then(res => {
                        if (res.data.data.state === true && res.data.data.state != null) {
                            //将获取到的token设置进入cookie里面
                            setCookie(res.data.data.token, res.data.data.refresh_token)
                        }
                    }).catch(e => {
                        Vue.prototype.$message({
                            message: e.message,
                            type: "error",
                            center: true
                        })
                    })
                }
            });
        } else {
               te=true
        }
    });
    return te
}
