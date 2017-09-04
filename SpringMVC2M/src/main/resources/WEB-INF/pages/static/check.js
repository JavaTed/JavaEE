function checkIfChecked(){
    if  ($(":checkbox:checked").length>0){
        return true;
    }
    else{
        alert("No photos selected");
        return false;
    }
}