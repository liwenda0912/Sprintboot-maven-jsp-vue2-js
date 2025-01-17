

export const dataHandle = (s) => {
    if(typeof  s ==="string"){
        let datalist = {}
        let data = s.split(":")
        datalist.iv = data[2]
        datalist.key = data[1]
        datalist.iciphertext = data[0]
        return datalist
    }else {
        return s
    }

}
export const  jsonStringSwitch=(type,s)=>{
    if (type === "String" && typeof s ==="object"){
        return JSON.stringify(s)
    }else if(type === "Json" && typeof s ==="string"){
        return JSON.parse(s)
    }else {
        return s
    }
}