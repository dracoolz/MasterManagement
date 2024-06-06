function BCcategoryFilter() {
    var tbl = document.getElementById("bc_table");
    var tableRows = tbl.rows.length;
    var bcValue = document.getElementsByName("bc")[0].value;

    for (var i = 1; i < tableRows; i++) {
            var cellValue = tbl.rows[i].cells[0].innerText;
            if (bcValue == cellValue) {
                tbl.rows[i].style.display = 'table-row'; // 行を表示
            } else {
                tbl.rows[i].style.display = 'none'; // 行を非表示
            }
        }
}


function SCcategoryFilter() {
    var tbl = document.getElementById("sc_table");
    var tableRows = tbl.rows.length;
    var bcElements = document.getElementsByName("bc");
    var scElements = document.getElementsByName("sc");
    var selectedSCValues = []; // Array to store selected values of sc selects

    // Loop through all "bc" select elements
    for (var i = 0; i < bcElements.length; i++) {
        var bcValue = bcElements[i].value;

        // Loop through all "sc" select elements
        for (var j = 0; j < scElements.length; j++) {
            // If the "bc" select element's value matches the current "sc" select's id, get the selected "sc" value
            if (scElements[j].id == bcValue && scElements[j].value !== "") {
                selectedSCValues.push(scElements[j].value);
                break; // Exit loop once a selected value is found for this "bc"
            }
        }
    }

    console.log(selectedSCValues);

	// Filter the table based on the selected values
    if (selectedSCValues.length > 0) {
        // Display rows based on selected "sc" values
        for (var i = 1; i < tableRows; i++) {
            var cellValue = tbl.rows[i].cells[2].innerText;
            if (selectedSCValues.includes(cellValue)) {
                tbl.rows[i].style.display = 'table-row'; // Display row
            } else {
                tbl.rows[i].style.display = 'none'; // Hide row
            }
        }
    } else {
        // If no "sc" values are selected, show rows based on selected "bc" value
        for (var i = 1; i < tableRows; i++) {
            var cellValue = tbl.rows[i].cells[0].innerText;
            if (bcValue == cellValue) {
                tbl.rows[i].style.display = 'table-row'; // Display row
            } else {
                tbl.rows[i].style.display = 'none'; // Hide row
            }
        }
    }
}

