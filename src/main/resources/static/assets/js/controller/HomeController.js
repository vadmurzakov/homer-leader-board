/**
 * Created by vadmurzakov on 20.07.17.
 */
LeaderBoard.controller('HomeController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope, $q) {
    console.log('HomeController');

    $scope.progressBar = 0;
    $scope.statistics = [];
    $scope.isProgressBarShow = true;

    var HOST_URI = /^(https?:\/\/)?([\da-z\.-:]+)+/.exec(window.location.href)[0];

    users = [
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

    users.forEach(function (item) {
        $http.get(HOST_URI + '/api/v1/statistic/' + item + '/month/6')
            .then(function onSuccess(response) {
                $scope.statistics.push(response.data);
                $scope.progressBar += Math.ceil(100 / users.length);
                $scope.isProgressBarShow = $scope.progressBar <= 100;
            }, function onError(response) {
                console.error(response);
            });
    });



}]);
