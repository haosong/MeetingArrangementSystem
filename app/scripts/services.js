'use strict';

angular.module('adWebHW')
    .constant("baseURL", "http://localhost:3000/")
    .service('bookFactory', ['$resource', 'baseURL', function ($resource, baseURL) {
        this.getBooks = function () {
            var data = $resource(baseURL + "books/:id", null, {'update': {method: 'PUT', responseType: 'json'}});
            console.log(data);
            return data;
        };
    }])

    .service('feedbackFactory', ['$resource', 'baseURL', function ($resource, baseURL) {
        this.getFeedbacks = function () {
            var data =  $resource(baseURL + "feedback", null, {'update': {method: 'PUT'}});
            console.log(data);
            return data;
        };
    }]);