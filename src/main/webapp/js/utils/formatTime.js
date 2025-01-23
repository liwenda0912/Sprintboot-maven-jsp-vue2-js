
export var formatter = function formatTime(row, column,cellValue) {
        return moment(cellValue* 1000).format('YYYY-MM-DD HH:mm:ss');
}


export const timestamp=(time)=>{
        const date = new Date(time);
        const timestamp = date.getTime();
        return timestamp
}




