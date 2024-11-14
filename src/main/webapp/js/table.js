var Main = {
    methods: {
        handleClick(tab, event) {
            switch (tab._uid) {
                case 3:
                    this.miniSetting = true,
                        this.userManage = false,
                        this.roleManage = false,
                        this.time_ = false
                    break;
                case 4:
                    this.miniSetting = false,
                        this.userManage = true,
                        this.roleManage = false,
                        this.time_ = false
                    break;
                case 5:
                    this.miniSetting = false,
                        this.userManage = false,
                        this.roleManage = true,
                        this.time_ = false
                    break;
                case 6:
                    this.miniSetting = false,
                        this.userManage = false,
                        this.roleManage = false,
                        this.time_ = true
                    break;
            }
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        },
        onshow() {
            let self = this;
            self.loading = true
            setTimeout({
            }, 5000)
            self.loading = false
        },

    },
    data() {
        return {
            activeName: 'first',
            miniSetting: true,
            userManage: false,
            roleManage: false,
            time_: false,
            currentPage4: 5,
            loading: "",
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1518 弄',
                zip: 200333
            }, {
                date: '2016-05-04',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1517 弄',
                zip: 200333
            }, {
                date: '2016-05-01',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1519 弄',
                zip: 200333
            }, {
                date: '2016-05-03',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1516 弄',
                zip: 200333
            }]
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app_tabs')