window.addEventListener("message",function(e) {
    Pagination.$data.dataNum =e.data
}, false);




var Pagination = new Vue({
    el:"#app—pagination",
    methods: {
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            window.parent.postMessage("pageSize:"+val);
        },
        handleCurrentChange(val) {
            window.parent.postMessage("page:"+val);
        },
    },
    data() {
        return {
            dataNum:null,
            currentPage4: 1
        };
    }
})
