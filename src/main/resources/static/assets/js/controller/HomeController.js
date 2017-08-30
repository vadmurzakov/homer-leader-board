/**
 * Created by vadmurzakov on 20.07.17.
 */
LeaderBoard.controller('HomeController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope, $q) {
    console.log('HomeController');

    $scope.progressBar = 0;
    $scope.users = [];
    $scope.isProgressBarShow = true;

    var HOST_URI = /^(https?:\/\/)?([\da-z0-9\.\-:]+)/.exec(window.location.href)[0];

    usernames = [
        "eplotnikov",
        "vmurzakov",
        "mnikolaenko",
        "vuvarov",
        "ytrunov",
        "rnemykin",
        "akovlyashenko",
        "ilysenko",
        "kafonin",
        "ismorodin",
        "kilichev",
        "nbloshkin"
    ];

    usernames.forEach(function (item) {
        $http.get(HOST_URI + '/api/v1/user/' + item + '/month/6')
            .then(function onSuccess(response) {
                $scope.users.push(response.data);
                $scope.progressBar += Math.ceil(100 / usernames.length);
                $scope.isProgressBarShow = $scope.progressBar <= 100;
            }, function onError(response) {
                console.error(response);
            });
    });

    $scope.propertyName = 'fullname';
    $scope.reverse = true;

    $scope.sortBy = function(propertyName) {
        $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
        $scope.propertyName = propertyName;
    };

}]);
