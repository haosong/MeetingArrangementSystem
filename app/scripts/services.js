'use strict';

angular.module('SELab3')
    .constant("baseURL", "http://localhost:3000/")
    .service('meetingFactory', ['$resource', 'baseURL', function ($resource, baseURL) {
        this.getMeeting = function () {
            var data = $resource(baseURL + "meeting/:id", null, {'update': {method: 'PUT', responseType: 'json'}});
            console.log(data);
            return data;
        };
    }]);