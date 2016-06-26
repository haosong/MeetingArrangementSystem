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
        $scope.choices = [{id: '1', 'name': '', 'attend': false}];
        $scope.content = '';
        $scope.isRecommend = false;
        $scope.successResponse = {
            "meeting": {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-01 14:00:00.0",
                "id": 1,
                "title": "b",
                "employees": ["c", "d"],
                "roomId": 0,
                "content": "hhh"
            }, "status": "0"
        };
        $scope.failResponse = {
            "available": [{
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-01 14:40:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }, {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-01 16:40:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }, {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-01 18:40:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }, {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-04 11:10:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }, {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-04 13:10:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }, {
                "duration": 30,
                "sponsor": "a",
                "start": "2016-07-04 15:10:00.0",
                "id": 0,
                "title": "b",
                "employees": ["b"],
                "roomId": 0,
                "content": "hhh"
            }], "status": "-1"
        };

        $scope.addNewChoice = function () {
            var newItemNo = $scope.choices.length + 1;
            $scope.choices.push({'id': newItemNo, 'name': '', 'attend': false});
        };

        $('#datetimepicker4').datetimepicker();

        $scope.removeChoice = function () {
            var lastItem = $scope.choices.length - 1;
            $scope.choices.splice(lastItem);
        };

        $scope.confirm = function () {
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
            $http.get('/rest/meeting/create', {
                    params: {
                        subject: $scope.subject,
                        date: date,
                        time: $scope.time,
                        names: names,
                        attend: attend,
                        content: $scope.content
                    }
                })
                .success(function (response) {
                    console.log(response);
                    console.log("success");
                    response.status = -1;
                    if (response.status = -1) {
                        $scope.isRecommend = true;
                    } else {

                    }
                })
                .error(function (data, header, config, status) {
                    console.log("error");
                });
        }

    })
;