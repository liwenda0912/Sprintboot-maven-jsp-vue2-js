

window.addEventListener('message', (event) => {
    Pagination.$data.dataNum = event.data

    // 检查来源（可选，但建议添加以增强安全性）
    // if (event.origin !== 'http://example.com') {
    //     return;
    // }
});


var Pagination = new Vue({
    el: "#app—pagination",
    methods: {
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            window.parent.postMessage("pageSize:" + val);
        },
        handleCurrentChange(val) {
            window.parent.postMessage("page:" + val);
        },
    },
    data() {
        return {
            dataNum: null,
            currentPage4: 1
        };
    }
})
