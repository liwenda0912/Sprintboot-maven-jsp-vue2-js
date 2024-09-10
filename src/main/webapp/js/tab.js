function tab(evt,tabName){
    var i,  tabBtns = document.getElementsByClassName("tab");
    for(i=0;i<tabBtns.length;i++){
        tabBtns[i].className = tabBtns[i].className.replace(" active","")
    }
    evt.currentTarget.className += " active";
    var tabContents = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabContents.length; i++) {
        console.log(tabName)
        if (tabContents[i].id === tabName) {
            tabContents[i].style.display = "block";
        } else {
            tabContents[i].style.display = "none";
        }
    }
}
