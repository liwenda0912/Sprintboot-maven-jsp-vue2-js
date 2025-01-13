import {reloadLogin} from "./reloadLogin.js";
import {request} from "./request.js";


// 原生js设置cookie
export function setCookie(token,refresh) {
    var date = new Date();
    date.setTime(date.getTime() + (24 * 60 * 60 * 1000));
    var expires = "expires=" + date.toUTCString();
    let string = refresh + "?" + token
    document.cookie = "cookies=" + string + "; expires=" + expires + "; path=/";
}

//获取token
export function getCookie() {
    let str = document.cookie;
    if (!str.includes("cookies")) {
        reloadLogin("请重新登录!")
    } else {
            let arr = str.split(';')
            //遍历数组（查找以"cookies"开头的元素）
            str = arr.filter(item => item.startsWith('cookies'))[0]
            return str === undefined ? undefined : str.split('=')[1].split("?")[1]
    }

}
//获取refresh_token
export function getCookieRefresh() {
    let str = document.cookie;
        let arr = str.split(';')
        //遍历数组（查找以name=开头的元素）
        str = arr.filter(item => item.startsWith('cookies'))[0]
        return str === undefined ? undefined : str.split('=')[1].split("?")[0]
}

// 验证token
export async function tokenDetect(token,refresh) {
    let detect;
    await request({
        method: 'POST',
        url: '/User/userAging',
        params: {
            token: token
        }
    }).then(r => {
        if (r.data.data.state === true) {
            detect = true
        } else {
            // 如果请求的refresh_token过期就返回登录界面
            if(token===refresh){
                reloadLogin(r.data.data.msg)
            }
        }
    }).catch(error => {
        alert(error.message)
    });
    if (detect) {
        return true
    }else {
        return false
    }
}
