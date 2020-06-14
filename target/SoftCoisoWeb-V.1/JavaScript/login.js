function myFunctionReload() {
    var loader = document.getElementById('loader');
    if (loader.style.display === 'none') {
        loader.style.display = 'block';
    }else{
         loader.style.display = 'none';
    }
}