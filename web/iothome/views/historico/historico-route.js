app.config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('/historico', {
            url:'/historico/:groupBy',
            templateUrl: 'views/historico/historico.html',
            controller: 'HistoricoController'
        });
    }]);