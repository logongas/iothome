app.directive('highCharts', [function () {


        return {
            restrict: 'A',
            link: function ($scope, element, attributes) {
                var config = $scope.$eval(attributes.highCharts);
                $(element).highcharts(config);
            },
            controller: function ($scope, $element, $attrs) {
                if ($attrs.highChartsBindController) {
                    $scope[$attrs.highChartsBindController] = this;
                }

                this.updateSerie = function (serie, data) {
                    $($element).highcharts().series[serie].setData(data);
                };

                this.updatePoint = function (value) {
                    $($element).highcharts().series[0].points[0].update(value);
                };
                this.updatePointSerie = function (serie, x, y) {
                    $($element).highcharts().series[serie].addPoint([x, y], true, true);
                };

            }
        };
    }]);