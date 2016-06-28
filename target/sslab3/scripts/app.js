'use strict';

var app = angular.module('SELab3', ['ui.router', 'ui.bootstrap', 'ngResource'])
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('app', {
                url: '/',
                views: {
                    'header': {
                        templateUrl: 'views/header.html'
                    },
                    'content': {
                        templateUrl: 'views/home.html'
                    }
                }
            })
            .state('app.home', {
                url: 'home',
                views: {
                    'content@': {
                        templateUrl: 'views/home.html'
                    }
                }
            })
            .state('app.login', {
                url: 'login',
                views: {
                    'content@': {
                        templateUrl: 'views/login.html',
                        controller: 'LoginController'
                    }
                }
            })
            .state('app.calendar', {
                url: 'calender',
                views: {
                    'content@': {
                        templateUrl: 'views/calendar.html',
                        controller: 'CalendarController'
                    }
                }
            })
            .state('app.signup', {
                url: 'signup',
                views: {
                    'content@': {
                        templateUrl: 'views/signup.html',
                        controller: 'SignupController'
                    }
                }
            })
            .state('app.arrange', {
                url: 'arrange',
                views: {
                    'content@': {
                        templateUrl: 'views/arrange.html',
                        controller: 'ArrangeController'
                    }
                }
            })
        ;
        $urlRouterProvider.otherwise('home');
    });

app.filter('capitalize', function () {
    return function (input, scope) {
        if (input != null)
            input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
});
;
