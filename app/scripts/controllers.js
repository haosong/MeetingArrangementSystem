'use strict';

angular.module('adWebHW')
    .controller('BookController', ['$scope', 'bookFactory', function ($scope, bookFactory) {
        $scope.tab = 1;
        $scope.filtText = '';
        $scope.showDetails = false;
        $scope.showBook = true;
        $scope.message = "Loading ...";
        $scope.books = bookFactory.getBooks().query();
        $scope.select = function (setTab) {
            $scope.tab = setTab;
            if (setTab === 2)
                $scope.filtText = "xml";
            else if (setTab === 3)
                $scope.filtText = "javaee";
            else if (setTab === 4)
                $scope.filtText = "web3d";
            else if (setTab == 5)
                $scope.filtText = "hybrid";
            else
                $scope.filtText = "";
        };
        $scope.isSelected = function (checkTab) {
            return ($scope.tab === checkTab);
        };
        $scope.toggleDetails = function () {
            $scope.showDetails = !$scope.showDetails;
        };
    }])

    .controller('BookDetailController', ['$scope', '$stateParams', 'bookFactory', function($scope, $stateParams, bookFactory) {
        $scope.showBook = false;
        $scope.message = "Loading ...";
        $scope.book = bookFactory.getBooks().get({
                id: parseInt($stateParams.id, 10)
            })
            .$promise.then(
                function (response) {
                    $scope.book = response;
                    $scope.showBook = true;
                },
                function (response) {
                    $scope.message = "Error: " + response.status + " " + response.statusText;
                }
            );
    }])

    .controller('BookCommentController', ['$scope', 'bookFactory', function ($scope, bookFactory) {
        $scope.mycomment = {rating: 5, comment: "", author: "", date: ""};
        $scope.submitComment = function () {
            $scope.mycomment.date = new Date().toISOString();
            $scope.mycomment.rating = parseInt($scope.mycomment.rating, 10)
            console.log($scope.mycomment);
            $scope.book.comments.push($scope.mycomment);
            bookFactory.getBooks().update({id: $scope.book.id}, $scope.book);
            $scope.commentForm.$setPristine();
            $scope.mycomment = {rating: 5, comment: "", author: "", date: ""};
        }
    }])

    .controller('ContactController', ['$scope', function ($scope) {
        $scope.feedback = {mychannel: "", firstName: "", lastName: "", agree: false, email: ""};
        var channels = [{value: "tel", label: "Tel."}, {value: "Email", label: "Email"}];
        $scope.channels = channels;
        $scope.invalidChannelSelection = false;
    }])

    .controller('FeedbackController', ['$scope', 'feedbackFactory', function ($scope, feedbackFactory) {
        $scope.sendFeedback = function () {
            if ($scope.feedback.agree && ($scope.feedback.mychannel == "") && !$scope.feedback.mychannel) {
                $scope.invalidChannelSelection = true;
                console.log('incorrect');
            }
            else {
                console.log($scope.feedback);
                feedbackFactory.getFeedbacks().update($scope.feedback);
                //$http.post('http://localhost:3000/feedback',$scope.feedback);
                $scope.invalidChannelSelection = false;
                $scope.feedback = {mychannel: "", firstName: "", lastName: "", agree: false, email: ""};
                $scope.feedback.mychannel = "";
                $scope.feedbackForm.$setPristine();
            }
        };
    }])

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