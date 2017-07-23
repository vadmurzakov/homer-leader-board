/**
 * Created by vadmurzakov on 20.07.17.
 */
LeaderBoard.controller('HomeController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope) {
    console.log('HomeController');

    $scope.progressBarShow = true;
    $scope.tabs = [];

    var HOST_URI = /^(https?:\/\/)?([\da-z\.-:]+)+/.exec(window.location.href)[0];

    // users = [
    //     {username: 'vmurzakov', fullname: 'Мурзаков Вадим'},
    //     {username: 'vuvarov', fullname: 'Уваров Владимир'},
    //     {username: 'rnemykin', fullname: 'Немыкин Рома'},
    //     {username: 'ilysenko', fullname: 'Илья Лысенко'}
    // ];
    //
    // users.forEach(function (item) {
    //     $http.get(HOST_URI + '/api/v1/issue/' + item.username + '/month/1')
    //         .then(function onSuccess(response) {
    //             $scope.tabs.push({
    //                 title: item.fullname,
    //                 issues: response.data
    //             });
    //             $scope.progressBar += 100 / users.length;
    //         }, function onError(response) {
    //             console.error(response);
    //         });
    // });

    $http.get(HOST_URI + '/api/v1/statistic/')
        .then(function onSuccess(response) {
            $scope.statistics = response.data;
            $scope.progressBarShow = false;
        }, function onError (response) {
            console.error(response);
        });

}]);
