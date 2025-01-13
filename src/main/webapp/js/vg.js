
var chargeBill =
    [{ timePhase: "peak", energy: 20, energyCost: 1, serviceCost: 0.3 },
        { timePhase: "valley", energy: 50, energyCost: 0.3, serviceCost: 0.2 }]
var dischargeBill =
    [{ timePhase: "peak", energy: 30, energyCost: 1, serviceCost: 0.3 },
        { timePhase: "valley", energy: 10, energyCost: 0.3, serviceCost: 0.2 }]

var totalCharge = chargeBill.sum(x => x[energy])//充电总量
var totalDischarge = dischargeBill.stream().sum(x => x[energy])//放电总量

var diff = totalCharge - totalDischarge //充放差额
var buy = diff > 0 ? diff : 0; //充>放=买电
var sell = diff < 0 ? -diff : 0;//充<放=售电
var processDiff = null;
var diffSettle = [diff, 0, 0]   // 电量，电费，服务费

if (buy > 0) {
    processDiff = chargeBill.sortAsc(x => x[energyCost] + x[serviceCost])  // 升序排序
} else if (sell > 0) {
    processDiff = dischargeBill.sortDesc(x => x[energyCost] - x[serviceCost])  // 降序排序
}
if (processDiff != null) {
    var totalDiff = abs(diff) //
    processDiff.forEach(x => {
        if(totalDiff === 0) return

        var reduce = min(totalDiff, x[energy]) //总差额电量与当前明细电量比较 取最小
        // 这里是汇总，如果需要，你们可以分开时段记录
        diffSettle[1] += reduce * x[energyCost] //计算当前电费
        diffSettle[2] += reduce * x[serviceCost] //计算当前服务费
        totalDiff -= reduce //总差额电量 - min(总差额电量,当前明细电量)
        x[energy] -= reduce //当前明细电量 - min(总差额电量,当前明细电量)
    })
}


var storeEnergyCost = chargeBill.stream().sum(x => x[energy] * x[energyCost]) // 存储电量成本
var releaseEnergyIncome = dischargeBill.stream().sum(x => x[energy] * x[energyCost])  // 放电收益
var arbitrage = releaseEnergyIncome - storeEnergyCost  // 套利收益=放电收益-存储电量成本

var buySettle = buy > 0 ? diffSettle : [0, 0, 0] // 电量，电费，服务费 //
var sellSettle = sell > 0 ? diffSettle : [0, 0, 0] // 电量，电费，服务费


// (-15)+7=-8