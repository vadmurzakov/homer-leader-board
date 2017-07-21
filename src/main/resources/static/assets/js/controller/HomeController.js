/**
 * Created by vadmurzakov on 20.07.17.
 */
LeaderBoard.controller('HomeController', ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope) {
    console.log('HomeController');

    $scope.progressBar = 0;
    $scope.tabs = [];

    users = [
        {username: 'vmurzakov', fullname: 'Мурзаков Вадим'},
        {username: 'vuvarov', fullname: 'Уваров Владимир'},
        {username: 'rnemykin', fullname: 'Немыкин Рома'},
        {username: 'ilysenko', fullname: 'Илья Лысенко'}
    ];

    users.forEach(function (item) {
        $http.get('http://localhost:8080/api/v1/issue/' + item.username + '/month/1')
            .then(function onSuccess(response) {
                $scope.tabs.push({
                    title: item.fullname,
                    issues: response.data
                });
                $scope.progressBar += 100 / users.length;
            }, function onError(response) {
                console.error(response);
            });
    });

}]);
