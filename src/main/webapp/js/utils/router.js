// const { createApp } = Vue;
// const { createRouter, createWebHistory } = VueRouter;

// 定义组件
const routes =[
    { path: '/success', component: "page/public/Result.jsp" }
];
const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
});
// 创建路由实例
// const router = createRouter({
//     history: createWebHistory(),
//     // routes: [
//     //     { path: '/success', component: Home },
//     //     { path: '/about'}
//     // ]
// });
export default router;

// // 创建Vue应用实例并挂载路由
// createApp({
//     setup() {
//         // 可以在这里编写其他逻辑
//     }
// }).use(router).mount('#app');
//
// createApp({
//     setup() {
//         const router = useRouter(); // 获取路由实例
//
//         const goToHome = () => {
//             router.push('/');
//         };
//
//         const goToAbout = () => {
//             router.push('/about');
//         };
//
//         return {
//             goToHome,
//             goToAbout
//         };
//     }
// }).use(router).mount('#app');
