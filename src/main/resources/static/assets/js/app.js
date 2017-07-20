/**
 * Created by vadmurzakov on 20.07.17.
 */
var LeaderBoard = angular.module('LeaderBoard', ['ngRoute'])
    .config(function($routeProvider){

        $routeProvider
            .when('/', {
                templateUrl: '/views/home.html',
                controller: 'HomeController'
            });

    });