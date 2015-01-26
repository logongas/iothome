var RealTimeData = function (layers) {
    this.layers = layers;
    this.timestamp = ((new Date()).getTime() / 1000) | 0;
};

RealTimeData.prototype.rand = function () {
    return parseInt(Math.random() * 100) + 50;
};

RealTimeData.prototype.history = function (entries) {
    if (typeof (entries) != 'number' || !entries) {
        entries = 60;
    }

    var history = [];
    for (var k = 0; k < this.layers; k++) {
        history.push({values: []});
    }

    for (var i = 0; i < entries; i++) {
        for (var j = 0; j < this.layers; j++) {
            history[j].values.push({time: this.timestamp, y: this.rand()});
        }
        this.timestamp++;
    }

    return history;
};

RealTimeData.prototype.next = function () {
    var entry = [];
    for (var i = 0; i < this.layers; i++) {
        entry.push({time: this.timestamp, y: this.rand()});
    }
    this.timestamp++;
    return entry;
}


app.controller('MainController', ['$scope', '$http', '$timeout', function ($scope, $http, $timeout) {

        $scope.valueTotal = 0;
        $scope.value0 = 0;
        $scope.value1 = 0;
        $scope.value2 = 0;
        var length=60;
        $scope.lineChartConfig = {
            type: 'time.line',
            data: [
                {
                    label:"Stream 0",
                    values:[length]
                },
                {
                    label:"Stream 1",
                    values:[length]
                },
                {
                    label:"Stream 2",
                    values:[length]
                },
                {
                    label:"Stream Total",
                    values:[length]
                }                
            ],
            axes: ['left', 'bottom', 'right']
        }

        $scope.refresh = function () {
            $timeout(function () {

                $http({
                    method: "GET",
                    url: $scope.getContextPath() + "/api/Measure/last",
                    params: {
                        idDevice: 1
                    }
                }).then(function (response) {

                    response.time = moment(response.data.time, "YYYY-MM-DDTHH:mm:ss.SSSZZ", true).toDate();

                    $scope.value0 = response.data.stream0;
                    $scope.value1 = response.data.stream1;
                    $scope.value2 = response.data.stream2;
                    $scope.valueTotal = $scope.value0 + $scope.value1 + $scope.value2;

                    var entry = [];
                    entry.push({time: response.time.getTime(), y: $scope.value0});
                    entry.push({time: response.time.getTime(), y: $scope.value1});
                    entry.push({time: response.time.getTime(), y: $scope.value2});
                    entry.push({time: response.time.getTime(), y: $scope.valueTotal});
                    $scope.lineChartConfig.push(entry);

                    $scope.refresh();
                }, function (response) {
                    alert.log("Error al cargar los datos:" + response.status);
                    $scope.refresh();
                });

            }, 6000);
        };

        $scope.format = function (value) {
            return value.toFixed(0) + 'w';
        };

        $scope.refresh();

    }]);


app.directive('iotGauge', [function () {
        return {
            restrict: 'A',
            link: function ($scope, element, attributes) {
                var gauge;
                var config = $scope.$eval(attributes.iotGauge);
                config.type = "time.gauge";
                gauge = $(element).epoch(config);

                $scope.$watch(attributes.iotGauge, function (newValue, oldValue) {
                    gauge.update(newValue.value);
                }, true)

            }
        };
    }]);

app.directive('iotLineChart', [function () {
        return {
            restrict: 'A',
            link: function ($scope, element, attributes) {
                var lineChart;
                var config = $scope.$eval(attributes.iotLineChart);
                config.type = "time.line";

                config.push = function (entry) {
                    lineChart.push(entry);
                }

                lineChart = $(element).epoch(config);



            }
        };
    }]);


