app.controller('HistoricoController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {

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

        $scope.model = {
            startTime: "00:00",
            endTime: "23:59",
            endDate: new Date(),
            groupBy: $stateParams.groupBy
        };


        $scope.totales = {
            stream0: 0,
            stream1: 0,
            stream2: 0,
            stream3: 0,
            streamSum: 0
        }

        $scope.show = function () {

            var startDateTime = joinDateTime($scope.model.startDate, $scope.model.startTime);
            var endDateTime = joinDateTime($scope.model.endDate, $scope.model.endTime);

            $http({
                method: "GET",
                url: $scope.getContextPath() + "/api/Measure/$namedsearch/" + $scope.model.groupBy,
                params: {
                    device: $scope.device.idDevice,
                    startDateTime: startDateTime,
                    endDateTime: endDateTime
                }
            }).then(function (response) {

                var measures = response.data;

                var chartData0 = [];
                var chartData1 = [];
                var chartData2 = [];
                var chartData3 = [];
                var chartDataSum = [];

                $scope.totales.stream0 = 0;
                $scope.totales.stream1 = 0;
                $scope.totales.stream2 = 0;
                $scope.totales.stream3 = 0;
                $scope.totales.streamSum = 0;

                for (var i = 0; i < measures.length; i++) {
                    var measure = measures[i];
                    var m = moment(measure.time, "YYYY-MM-DDTHH:mm:ss.SSSZ", true).format("x") * 1;
                    chartData0.push([m, measure.stream0]);
                    chartData1.push([m, measure.stream1]);
                    chartData2.push([m, measure.stream2]);
                    chartData3.push([m, measure.stream3]);
                    chartDataSum.push([m, measure.stream0 + measure.stream1 + measure.stream2 + measure.stream3]);

                    $scope.totales.stream0 += measure.stream0;
                    $scope.totales.stream1 += measure.stream1;
                    $scope.totales.stream2 += measure.stream2;
                    $scope.totales.stream3 += measure.stream3;
                    $scope.totales.streamSum += (measure.stream0 + measure.stream1 + measure.stream2 + measure.stream3);

                }
                $scope.mainChart.updateSerie(0, chartData0);
                $scope.mainChart.updateSerie(1, chartData1);
                $scope.mainChart.updateSerie(2, chartData2);
                $scope.mainChart.updateSerie(3, chartData3);
                $scope.sumChart.updateSerie(0, chartDataSum);

            }, function (response) {
                alert.log("Error al cargar los datos:" + response.status);
                $scope.refresh();
            });
        };


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
            colors: ['#FF0000'],
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
                }]
        };


    }]);





function joinDateTime(date, time) {

    if (date && time) {
        var parts = time.split(":");
        var dateTime = moment(date).startOf('day').add({
            hours: parts[0] * 1,
            minutes: parts[1] * 1
        }).toDate();

        return dateTime;
    }
}