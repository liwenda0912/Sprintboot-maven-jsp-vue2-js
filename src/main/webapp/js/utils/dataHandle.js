

export const dataHandle = (s) => {
    let datalist = {}
    let data = s.split(":")
    datalist.iv = data[2]
    datalist.key = data[1]
    datalist.iciphertext = data[0]
    return datalist
}
export const  jsonStringSwitch=(type,s)=>{
    if (type === "String"){
        return JSON.stringify(s)
    }else if(type === "Json"){
        return JSON.parse(s)
    }
}