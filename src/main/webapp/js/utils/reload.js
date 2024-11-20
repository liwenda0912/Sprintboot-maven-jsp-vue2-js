 export function reload(){
    setTimeout(()=>{
        window.location.reload();
    },3000)
}

 export  function openFullScreen2(self_) {
     const loading = self_.$loading({
         lock: true,
         text: '页面加载中...',
         spinner: 'el-icon-loading',
         background: 'rgba(0, 0, 0, 0.7)'
     });
     // setTimeout(() => {
     //     loading.close();
     // }, 2000);
 }
