export const dataHandle = (s) => {
    if (typeof s === "string") {
        let datalist = {}
        let data = s.split(":")
        datalist.iv = data[2]
        datalist.key = data[1]
        datalist.iciphertext = data[0]
        return datalist
    } else {
        return s
    }

}
export const jsonStringSwitch = (type, s) => {
    if (type === "String" && typeof s === "object") {
        return JSON.stringify(s)
    } else if (type === "Json" && typeof s === "string") {
        return JSON.parse(s)
    } else {
        return s
    }
}


export const roundUpFirst = (num, pageSize) => {
    let num_ = pageSize / num
    if (num_.toString().split(".")[1] === "0") {
        return parseInt(num_.toString().split(".")[0])
    } else {
        return parseInt(num_.toString().split(".")[0]) + 1
    }
}


