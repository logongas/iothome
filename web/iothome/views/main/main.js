app.controller('MainController', ['$scope', '$http', '$timeout', function ($scope, $http, $timeout) {

        $scope.device = {
            idDevice: 1,
            name: "Consumo casa",
            streamName0: "cocina",
            streamName1: "enchufes A",
            streamName2: "enchufes B",
            streamName3: "luces",
            streamColor0: "#7CB5EC",
            streamColor1: "#434348",
            streamColor2: "#90ED7D",
            streamColor3: "#F7A35C"
        };



        $scope.measure = {
            stream0: 0,
            stream1: 0,
            stream2: 0,
            stream3: 0,
            valueTotal: 0
        }

        var lastTime = 0;
        var inicializado = false;
        $scope.refresh = function () {
            $http({
                method: "GET",
                url: $scope.getContextPath() + "/api/Measure/$namedsearch/getLast",
                params: {
                    device: $scope.device.idDevice
                }
            }).then(function (response) {

                $scope.measure = response.data;
                var currentTime = moment($scope.measure.time, "YYYY-MM-DDTHH:mm:ss.SSSZ", true).format("x") * 1; //.subtract(moment().utcOffset(),"minute");

                if (inicializado === false) {

                    $scope.inicializar(currentTime);

                    inicializado = true;
                }


                if (currentTime !== lastTime) {
                    $scope.measure.valueTotal = $scope.measure.stream0 + $scope.measure.stream1 + $scope.measure.stream2 + $scope.measure.stream3;

                    $scope.sumChart.updatePointSerie(0, currentTime, $scope.measure.valueTotal);
                    $scope.mainChart.updatePointSerie(0, currentTime, $scope.measure.stream0);
                    $scope.mainChart.updatePointSerie(1, currentTime, $scope.measure.stream1);
                    $scope.mainChart.updatePointSerie(2, currentTime, $scope.measure.stream2);
                    $scope.mainChart.updatePointSerie(3, currentTime, $scope.measure.stream3);

                    $scope.gauge0.updatePoint($scope.measure.stream0);
                    $scope.gauge1.updatePoint($scope.measure.stream1);
                    $scope.gauge2.updatePoint($scope.measure.stream2);
                    $scope.gauge3.updatePoint($scope.measure.stream3);

                    lastTime = currentTime;

                }
                $timeout($scope.refresh, 3000);
            }, function (response) {
                alert.log("Error al cargar los datos:" + response.status);
                $timeout($scope.refresh, 3000);
            });



        };


        $scope.refresh();

        $scope.inicializar = function (time) {
            $scope.mainChart.updateSerie(0, getInitialDataArray(time));
            $scope.mainChart.updateSerie(1, getInitialDataArray(time));
            $scope.mainChart.updateSerie(2, getInitialDataArray(time));
            $scope.mainChart.updateSerie(3, getInitialDataArray(time));
            $scope.sumChart.updateSerie(0, getInitialDataArray(time));
        }

        $scope.configMainChart = {
            title: {
                text: null
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Consumo (watios)'
                },
                min: 0
            },
            legend: {
                enabled: true
            },
            credits: {
                enabled: false
            },
            color: [$scope.device.streamColor0, $scope.device.streamColor1, $scope.device.streamColor2, $scope.device.streamColor3],
            series: [{
                    type: 'line',
                    name: $scope.device.streamName0,
                    data: []

                }, {
                    type: 'line',
                    name: $scope.device.streamName1,
                    data: []
                }, {
                    type: 'line',
                    name: $scope.device.streamName2,
                    data: []
                }, {
                    type: 'line',
                    name: $scope.device.streamName3,
                    data: []
                }]
        };

        $scope.configSumChart = {
            title: {
                text: null
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Consumo (watios)'
                },
                min: 0
            },
            legend: {
                enabled: true
            },
            credits: {
                enabled: false
            },
            series: [{
                    type: 'line',
                    name: 'Total',
                    data: []
                }],
             colors:['#FF0000']
        };

        var gaugeOptions = {
            chart: {
                type: 'solidgauge'
            },
            credits: {
                enabled: false
            },
            title: null,
            pane: {
                center: ['50%', '85%'],
                size: '140%',
                startAngle: -90,
                endAngle: 90,
                background: {
                    backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                    innerRadius: '60%',
                    outerRadius: '100%',
                    shape: 'arc'
                }
            },
            tooltip: {
                enabled: false
            },
            // the value axis
            yAxis: {
                lineWidth: 0,
                minorTickInterval: null,
                tickPixelInterval: 400,
                tickWidth: 0,
                title: {
                    y: -70,
                    text: 'Watios'
                },
                labels: {
                    y: 16
                },
                min: 0,
                max: 2000
            },
            plotOptions: {
                solidgauge: {
                    dataLabels: {
                        y: 5,
                        borderWidth: 0,
                        useHTML: true
                    }
                }
            }
        };


        $scope.configGauge0 = Highcharts.merge(gaugeOptions, {
            series: [{
                    name: 'Watios',
                    data: [1]
                }],
            yAxis: {
                minColor: $scope.device.streamColor0,
                maxColor: $scope.device.streamColor0
            }

        })

        $scope.configGauge1 = Highcharts.merge(gaugeOptions, {
            series: [{
                    name: 'Watios',
                    data: [1]
                }],
            yAxis: {
                minColor: $scope.device.streamColor1,
                maxColor: $scope.device.streamColor1
            }

        })

        $scope.configGauge2 = Highcharts.merge(gaugeOptions, {
            series: [{
                    name: 'Watios',
                    data: [1]
                }],
            yAxis: {
                minColor: $scope.device.streamColor2,
                maxColor: $scope.device.streamColor2
            }

        })
        $scope.configGauge3 = Highcharts.merge(gaugeOptions, {
            series: [{
                    name: 'Watios',
                    data: [1]
                }],
            yAxis: {
                minColor: $scope.device.streamColor3,
                maxColor: $scope.device.streamColor3
            }

        })

    }]);



function getInitialDataArray(time) {
    var data = [];
    for (var i = -50; i < 0; i++) {
        data.push({
            x: time + (i * 10000),
            y: 0
        });
    }
    return data;
}

