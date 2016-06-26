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
        $scope.meetingRadio = {value: ''};
        $scope.isRecommend = false;
        $scope.isSuccess = false;
        $scope.isNetworkError = false;
        $scope.successResponse = '';
        $scope.failResponse = '';

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
            $scope.isSuccess = false;
            $scope.isRecommend = false;
            $scope.isNetworkError = false;
            var date = moment($('#datetimepicker4').val()).format("MM/DD/YYYY HH:mm");
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
                        sponsor: $scope.sponsor,
                        time: $scope.time,
                        names: names,
                        attend: attend,
                        content: $scope.content
                    }
                })
                .success(function (response) {
                    console.log("success");
                    if (response.status == -1) {
                        // 推荐会议
                        $scope.failResponse = response;
                        $scope.isRecommend = true;
                    } else {
                        // 会议安排成功
                        $scope.isSuccess = true;
                    }
                })
                .error(function (response) {
                    // 网络错误
                    console.log("error");
                    $scope.isNetworkError = true;
                });
        };

        $scope.select = function () {
            var index = $scope.meetingRadio.value;
            var selectedMeeting = $scope.failResponse.available[index];
            //console.log($scope.failResponse.available[index]);
            //console.log(JSON.stringify(selectedMeeting.employees));
            //console.log(JSON.stringify(selectedMeeting.attend));
            $http.get('/rest/meeting/create', {
                params: {
                    subject: selectedMeeting.title,
                    date: selectedMeeting.start,
                    time: selectedMeeting.duration,
                    names: selectedMeeting.names,
                    attend: selectedMeeting.attend,
                    content: selectedMeeting.content
                }
            }).success(function (response) {
                console.log("success");
                $scope.isRecommend = false;
                $scope.isSuccess = true;
            }).error(function (response) {
                // 网络错误
                console.log("error");
                $scope.isNetworkError = true;
            });
        }
    })
;