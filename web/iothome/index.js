"use strict";

app.controller("IndexController", ['$scope', 'session', function ($scope, session) {

        $scope.cloneMenu = function () {
            var el = $('.sidebar-nav > ul > *').clone();
            el.addClass('visible-xs');
            $('#main-menu').append(el.clone());
        };

    }]);