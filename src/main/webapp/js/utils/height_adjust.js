export  function height_adjust(val,name,name_id,name_) {
    if (val.length === 2){
        for (let i =0;i<name.length;i++) {
            name[i].style.height = "790px";
        }
        for (let j =0;j<name.length;j++) {
            name_id[j].style.margin = "20px 20px 60px 20px";
        }
        for (let e =0;e<name_.length;e++) {
            name_[e].style.height = "106%";
        }
    } else{
        for (let i =0;i<name.length;i++) {
            name[i].style.height = "760px";
        }
        for (let j =0;j<name_.length;j++) {
            name_[j].style.height = "100%";
        }
    }
}


export const adjustScrollHeight=(data_,dom)=>{
    if (data_.length<7){
        for(let i = 0;i<dom.length;i++){
            dom[i].style.setProperty('max-height', '160px', 'important');
        }
    }else {
        for(let i = 0;i<dom.length;i++){
            dom[i].style.setProperty('max-height', '274px', 'important');
        }
    }
}
