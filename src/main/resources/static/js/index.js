function rowClicked(id) {
    location.href = "/test/location?id=" + id;
}

function datePick(id) {
    // let from = document.getElementById("startDate").value;
    // console.log(from)
    // let to = document.getElementById("endDate").value;
    // console.log(to)
    location.href = "/test/cars";

}

function carClicked(carId) {
    location.href = "/test/car?id=" + carId;
}

function carReserved(locId, carId) {
    let from = document.getElementById("from").value;
    console.log(from)
    let to = document.getElementById("to").value;
    console.log(to)
    location.href = "/test/insurances";
}

function insurancesSelected() {
    let selectedInsurances = [];
    $("input.is-selected:checkbox:checked").each(function () {
        selectedInsurances.push($(this).val());
    });
    selectedInsurances.forEach(function (entry) {
        console.log(entry);
    });
    let url = "/test/reservation/";
    selectedInsurances.forEach(function (entry) {
        url = url + entry + ","
    });
    url = url.slice(0, -1);
    location.href = url;
}

function reservationConfirmed() {
    let passengers = document.getElementById("passenger-input").value;
    location.href = "/test/reservation?people=" + passengers;
}




