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
    
    for (var i = 0; i < scBoxes; i++) {
		sc[i].style.display = "none";
            
		if (sc[i].id == bc) {
			sc[i].style.display = "";
        }
     }
}


function formSwitch() {
    hoge = document.getElementsByName('maker')
    if (hoge[0].checked) {
        document.getElementById('bcform').style.display = "";
        document.getElementById('scform').style.display = "none";
    } else if (hoge[1].checked) {
        document.getElementById('bcform').style.display = "none";
        document.getElementById('scform').style.display = "";
    } else {
        document.getElementById('bcform').style.display = "none";
        document.getElementById('scform').style.display = "none";
    }
}
window.onload = function(){
	document.getElementById('bcform').style.display = "";
    document.getElementById('scform').style.display = "none";
	}
