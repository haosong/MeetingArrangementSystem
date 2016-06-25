'use strict';

angular.module('SELab3')

    .controller('LoginController', function ($scope) {

    })

    .controller('SignupController', function ($scope) {

    })

    .controller('ArrangeController', function ($scope, $http) {
        $scope.subject = '';
        $scope.meetingDate = null;
        $scope.time = '';
        $scope.sponsor = '';
        $scope.choices = [{id: '1','name':'','attend':false}];
        $scope.content = '';
        $scope.addNewChoice = function() {
            var newItemNo = $scope.choices.length + 1;
            $scope.choices.push({'id': newItemNo,'name':'','attend':false});
        };

        $('#datetimepicker4').datetimepicker();

        $scope.removeChoice = function() {
            var lastItem = $scope.choices.length - 1;
            $scope.choices.splice(lastItem);
        };

        $scope.confirm = function() {
            var date = moment($('#datetimepicker4').val()).format("MM-DD-YYYY HH:mm");
            var names = "";
            var attend = "";
            for (var index in $scope.choices) {
                names = names + $scope.choices[index].name + ",";
                attend = attend + $scope.choices[index].attend + ",";
            }
            console.log('subject: ' + $scope.subject);
            console.log('date: ' + date);
            console.log('time: ' + $scope.time);
            console.log('sponsor: ' + $scope.sponsor);
            console.log('names: ' + names);
            console.log('attend: ' + attend);
            console.log('content: ' + $scope.content);
            $http.get('/rest/meeting/create', {params: {subject: $scope.subject, date: date, time: $scope.time, names: names, attend: attend, content: $scope.content}})
                .success(function(response) {
                    console.log(response);
                    console.log("success");
                });
        }

    })
;