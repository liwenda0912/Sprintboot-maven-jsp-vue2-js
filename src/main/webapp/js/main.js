import { createApp } from 'vue';
import App from './App.vue';
import selectLoadMore from './directives/selectLoadMore';

const app = createApp(App);
app.directive('select-loadmore', selectLoadMore);
app.mount('#app');