'use strict';

var app = angular.module('adWebHW', ['ui.router','ui.bootstrap', 'ngResource'])
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        // route for the home page
            .state('app', {
                url: '/',
                views: {
                    'header': {
                        templateUrl: 'views/header.html'
                    },
                    'content': {
                        templateUrl: 'views/login.html'
                    }
                }
            })
            // route for the about page
            .state('app.home', {
                url: 'home',
                views: {
                    'content@': {
                        templateUrl: 'views/home.html'
                    }
                }
            })
            .state('app.about', {
                url: 'about',
                views: {
                    'content@': {
                        templateUrl: 'views/about.html'
                    }
                }
            })
            // route for the contact page
            .state('app.contact', {
                url: 'contact',
                views: {
                    'content@': {
                        templateUrl: 'views/contact.html',
                        controller: 'ContactController'
                    }
                }
            })
            // route for the book page
            .state('app.book', {
                url: 'book',
                views: {
                    'content@': {
                        templateUrl: 'views/book.html',
                        controller: 'BookController'
                    }
                }
            })
            // route for the bookdetail page
            .state('app.bookdetails', {
                url: 'book/:id',
                views: {
                    'content@': {
                        templateUrl: 'views/bookdetail.html',
                        controller: 'BookDetailController'
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

        $urlRouterProvider.otherwise('login');
    });

    app.filter('capitalize', function() {
        return function(input, scope) {
            if (input!=null)
                input = input.toLowerCase();
            return input.substring(0,1).toUpperCase()+input.substring(1);
        }
    });
;
