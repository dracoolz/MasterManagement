document.addEventListener('DOMContentLoaded', function() {
   // サブBOXを全て非表示にする
   var allSubBoxes = document.getElementsByClassName("scSelect");
   for (var i = 0; i < allSubBoxes.length; i++) {
      allSubBoxes[i].style.display = 'none';
   }
});

function ctrlTab() {
    var bc = document.getElementsByClassName("bcSelect")[0].value;
    var sc = document.getElementsByClassName("scSelect");
    var scBoxes = sc.length;
    
     for (var j = 0; j < sc.length; j++) {
                sc[j].style.display = 'none';
            }
    for (var i = 0; i < scBoxes; i++) {
        if (sc[i].id == bc) {
            sc[i].style.display = 'block';
        }
    }
}
