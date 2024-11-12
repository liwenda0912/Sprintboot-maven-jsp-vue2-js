import {reloadLogin} from "./reloadLogin.js";
import {request} from "./request.js";


export function setCookie(token,refresh) {
    const date = new Date();
    date.setTime(date.getTime() + (24 * 60 * 60 * 1000));// 1天后过期
    let string = refresh + "?" + token
    document.cookie = "cookies=" + string + "; expires=" + date.toUTCString() + "; path=/";
}


export function getCookie() {
    let str = document.cookie;
    if (!str.includes("cookies")) {
        reloadLogin("请重新登录!")
    } else {
            let arr = str.split(';')
            //遍历数组（查找以name=开头的元素）
            str = arr.filter(item => item.startsWith('cookies'))[0]
            return str === undefined ? undefined : str.split('=')[1].split("?")[1]
    }

}

export function getCookieRefresh() {
    let str = document.cookie;
        let arr = str.split(';')
        //遍历数组（查找以name=开头的元素）
        str = arr.filter(item => item.startsWith('cookies'))[0]
        return str === undefined ? undefined : str.split('=')[1].split("?")[0]
}

export async function tokenDetect(token,refresh) {
    let detect;
    console.log(refresh)
    await request({
        method: 'POST',
        url: '/User/userAging',
        // headers: {
        //     'Content-Type': 'application/json',
        //     "Authorization": 'Access-Control-Request-Headers'
        // },
        params: {
            token: token
        }
    }).then(r => {
        if (r.data.data.state === true) {
            detect = true
        } else {
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
