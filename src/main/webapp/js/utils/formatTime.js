
export var formatter = function formatTime(row, column) {
        let self = this
        let date = row.time;
        return moment(date).format('YYYY-MM-DD HH:mm:ss')
}



