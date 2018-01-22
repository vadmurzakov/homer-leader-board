/**
 * Created by vadmurzakov on 20.07.17.
 */
LeaderBoard.controller('LiferayController', ['$scope', '$http', '$rootScope', function ($scope, $http) {
    console.log('LiferayController');

    $scope.ckName = 'Liferay';
    $scope.progressBar = 0;
    $scope.users = [];
    $scope.isProgressBarShow = true;
    $scope.countMonth = 3;

    $scope.capacity = {
        isShow: false,
        button: 'danger'
    };
    $scope.analytic = {
        isShow: false,
        button: 'danger'
    };
    $scope.patch = {
        isShow: false,
        button: 'danger'
    };

    var HOST_URI = /^(https?:\/\/)?([\da-z0-9\.\-:]+)/.exec(window.location.href)[0];

    usernames = [
        "dsnimshchikov",
        "dtoropchin",
        "dkolvakh",
        "asayapin"
    ];

    usernames.forEach(function (item) {
        $http.get(HOST_URI + '/api/v1/user/' + item + '/month/' + $scope.countMonth)
            .then(function onSuccess(response) {
                $scope.users.push(response.data);
                $scope.progressBar += Math.ceil(100 / usernames.length);
                $scope.isProgressBarShow = $scope.progressBar < 100;
            }, function onError(response) {
                console.error(response);
            });
    });

    // $scope.propertyName = 'fullname';
    $scope.reverse = true;

    $scope.sortBy = function(propertyName) {
        $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
        $scope.propertyName = propertyName;
    };

    $scope.showBy = function(columnName) {
        if (columnName === 'capacity') {
            $scope.capacity.isShow = !$scope.capacity.isShow;
            $scope.capacity.button = $scope.capacity.isShow ? 'success' : 'danger';
        }
        if (columnName === 'analytic') {
            $scope.analytic.isShow = !$scope.analytic.isShow;
            $scope.analytic.button = $scope.analytic.isShow ? 'success' : 'danger';
        }
        if (columnName === 'patch') {
            $scope.patch.isShow = !$scope.patch.isShow;
            $scope.patch.button = $scope.patch.isShow ? 'success' : 'danger';
        }
    }

}]);
