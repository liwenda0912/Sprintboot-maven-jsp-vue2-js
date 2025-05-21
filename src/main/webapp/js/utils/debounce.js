// function debounce(func, wait) {
//     let timeout;
//     return function(...args) {
//         clearTimeout(timeout);
//         timeout = setTimeout(() => {
//             func.apply(this, args);
//         }, wait);
//     };
// }
import {roundUpFirst} from "./dataHandle.js";

export const debounce = (func, wait) => {
    let timeout;
    return function(...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            func.apply(this, args);
        }, wait);
    };
}

//运用了回调函数
export const scrollEventC = (callback,event,self_,list) => {
    if(event.target._prevClass === "el-select-dropdown__wrap el-scrollbar__wrap"){
        const domTarget =event.target
        const translateY = domTarget.scrollTop+1
        const viewHeight =domTarget.clientHeight
        const allHeight = domTarget.scrollHeight
        if(allHeight-translateY<=viewHeight){
            if(list.Page!==roundUpFirst(list.PageSize,list.TotalPage)){
                self_.$data.loading_bottom = true
                // 添加一个数据
                setTimeout(()=>{
                    callback(list.Page+1)
                    self_.$data.loading_bottom = false
                },1000)
            }
        }else {
            if (list.Page!==1){
                self_.$data.loading_top = true//
                setTimeout(()=>{
                    callback(list.Page-1)
                    self_.$data.loading_top = false
                },1000)
            }

        }
    }

}