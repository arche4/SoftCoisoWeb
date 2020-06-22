$(document).ready(function () {
    var graficaEstado = 'ok';
    var graficaGenero = 'ok';
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
            var nombre = [];
            var stock = [];
            var color = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
            var bordercolor = ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'];
            console.log(data);

            for (var i in data) {
                nombre.push(data[i].Genero);
                stock.push(data[i].Cantidad);
            }

            var chartdata = {
                labels: nombre,
                datasets: [{
                        label: 'Dataset 1',
                        backgroundColor: color,
                        borderColor: color,
                        borderWidth: 1,
                        data: stock
                    }]
            };

            var mostrar = $("#graficoGenero");

            var grafico = new Chart(mostrar, {
                type: 'bar',
                data: chartdata,
                options: {
                    responsive: true,
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Chart.js Bar Chart'
                    }
                }
            });
        },
        error: function (data) {
            console.log(data);
        }
    });
});