import {getCookie, getCookieRefresh, setCookie, tokenDetect} from "./Cookie.js";
import {request} from "./request.js";


export function authDentifiy(fun) {
    let te;
    let refresh=getCookieRefresh();
    tokenDetect(getCookie(),refresh).then(r => {

        if (r === false) {
            tokenDetect(getCookieRefresh(),refresh).then(res => {
                if ( res=== false) {

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
                            console.log("获取到")
                            setCookie(res.data.data.token, res.data.data.refresh_token)
                            this.onshow()
                        }
                    }).catch(e => {
                        self.loading = false
                        self.$message({
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
