
export function operations(type_message,data_message,self_){
        self_.$message({
            message:data_message,
            center:true,
            type:type_message
        });

}