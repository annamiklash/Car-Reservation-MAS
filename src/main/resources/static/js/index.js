function rowClicked(id) {
    location.href = "/mas/location?id=" + id;
}

function datePick(id) {
    location.href = "/mas/car/all";

}

function carClicked(carId) {
    location.href = "/mas/car?id=" + carId;
}

function carReserved(locId, carId) {
    let from = document.getElementById("from").value;
    console.log(from)
    let to = document.getElementById("to").value;
    console.log(to)
    location.href = "/mas/insurance/all";
}

function insurancesSelected() {

    let selectedInsurances = [];
    $("input.is-selected:checkbox:checked").each(function () {
        selectedInsurances.push($(this).val());
    });
    let selected = selectedInsurances.length;
    if (selected < 1) {
        document.getElementById("submit-btn").disabled = true;
        document.getElementById("error").innerHTML = "You need to pick at least one insurance";
        document.getElementById("submit-btn").disabled = false;

        return;
    }

    if (selected > 3) {
        document.getElementById("submit-btn").disabled = true;
        document.getElementById("error").innerHTML = "You need to pick at most three insurances";
        document.getElementById("submit-btn").disabled = false;
        return;
    }
    let url = "/mas/insurance/";
    selectedInsurances.forEach(function (entry) {
        url = url + entry + ","
    });
    url = url.slice(0, -1);
    location.href = url;
}



