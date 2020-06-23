$(document).ready(function () {
    $('#table_id').dataTable();
    
    $(".loader").fadeOut("slow");
    var graficaEstado = 'ok';
    var graficaGenero = 'ok';
    var graficaArl = 'ok';
    var graficaEps = 'ok';
    var graficaAfp = 'ok';

    $.ajax({
        url: "/DashboardServlet",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        method: "GET",
        data: 'graficaEstado=' + graficaEstado,
        success: function (data) {
            var nombre = [];
            var stock = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            var bordercolor = ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'];
            console.log(data);

            for (var i in data) {
                nombre.push(data[i].Estado);
                stock.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: nombre,
                datasets: [{
                        label: nombre,
                        backgroundColor: color,
                        borderColor: color,
                        borderWidth: 2,
                        hoverBackgroundColor: color,
                        hoverBorderColor: bordercolor,
                        data: stock
                    }]
            };

            var mostrar = $("#graficoEstado");

            var grafico = new Chart(mostrar, {
                type: 'doughnut',
                data: chartdata,
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });

    $.ajax({
        url: "/DashboardServlet",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        method: "GET",
        data: 'graficaGenero=' + graficaGenero,
        success: function (data) {
            var genero = [];
            var cantidad = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            var bordercolor = ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'];
            console.log(data);

            for (var i in data) {
                genero.push(data[i].Genero);
                cantidad.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: genero,
                datasets: [{
                        label: "Menor en cantidad",
                        backgroundColor: color,
                        borderColor: bordercolor,
                        borderWidth: 1,
                        data: cantidad
                    }]
            };

            var mostrar = $("#graficoGenero");

            var grafico = new Chart(mostrar, {
                type: 'bar',
                data: chartdata,
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });

    $.ajax({
        url: "/DashboardServlet",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        method: "GET",
        data: 'graficaArl=' + graficaArl,
        success: function (data) {
            var Arl = [];
            var cantidad = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            console.log(data);

            for (var i in data) {
                Arl.push(data[i].Arl);
                cantidad.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: Arl,
                datasets: [{
                        label: "Menor en cantidad",
                        backgroundColor: color,
                        borderColor: color,
                        borderWidth: 1,
                        data: cantidad
                    }]
            };

            var mostrar = $("#graficaArl");

            var grafico = new Chart(mostrar, {
                type: 'pie',
                data: chartdata,
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });

    $.ajax({
        url: "/DashboardServlet",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        method: "GET",
        data: 'graficaEps=' + graficaEps,
        success: function (data) {
            var Eps = [];
            var cantidad = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            console.log(data);

            for (var i in data) {
                Eps.push(data[i].Eps);
                cantidad.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: Eps,
                datasets: [{
                        label: "Menor en cantidad",
                        backgroundColor: color,
                        borderColor: color,
                        borderWidth: 1,
                        data: cantidad
                    }]
            };

            var mostrar = $("#graficaEps");

            var grafico = new Chart(mostrar, {
                type: 'pie',
                data: chartdata,
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });

    $.ajax({
        url: "/DashboardServlet",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        method: "GET",
        data: 'graficaAfp=' + graficaAfp,
        success: function (data) {
            var Afp = [];
            var cantidad = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            console.log(data);

            for (var i in data) {
                Afp.push(data[i].Afp);
                cantidad.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: Afp,
                datasets: [{
                        label: "Menor en cantidad",
                        backgroundColor: color,
                        borderColor: color,
                        borderWidth: 1,
                        data: cantidad
                    }]
            };

            var mostrar = $("#graficaAfp");

            var grafico = new Chart(mostrar, {
                type: 'pie',
                data: chartdata,
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });
});