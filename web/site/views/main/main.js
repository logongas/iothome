app.controller('MainController', ['$scope','$window','session', function ($scope, $window, session) {
  
        $scope.businessMessages={
            
        };
  
        $scope.entrar = function(login, password) {
            session.login(login, password).then(function(user) {
                $window.location.href = getContextPath() + "/iothome/index.html";
            }, function(businessMessages) {
                $scope.businessMessages = businessMessages;
            });
        };
    }]);



