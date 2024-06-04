function categoryFilter() {
    var tbl = document.getElementById("category_table");
    var tableRows = tbl.rows.length;
    var bcValue = document.getElementsByName("bc")[0].value;
    var scValue = document.getElementsByName("sc")[0].value;

    if (scValue == "") {
        for (var i = 1; i < tableRows; i++) {
            var cellValue = tbl.rows[i].cells[0].innerText;
            if (bcValue == cellValue) {
                tbl.rows[i].style.display = 'table-row'; // 行を表示
            } else {
                tbl.rows[i].style.display = 'none'; // 行を非表示
            }
        }
    } else {
        for (var i = 1; i < tableRows; i++) {
            var cellValue = tbl.rows[i].cells[2].innerText;
            if (scValue == cellValue) {
                tbl.rows[i].style.display = 'table-row'; // 行を表示
            } else {
                tbl.rows[i].style.display = 'none'; // 行を非表示
            }
        }
    }
}
