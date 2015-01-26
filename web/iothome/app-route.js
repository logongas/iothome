"use strict";

app.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider) {
        $urlRouterProvider.otherwise('/');      
    }]);
