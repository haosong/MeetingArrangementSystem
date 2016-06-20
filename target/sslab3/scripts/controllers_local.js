'use strict';

angular.module('adWebHW')
    .controller('BookController', ['$scope', 'bookFactory', function ($scope, bookFactory) {
        $scope.tab = 1;
        $scope.filtText = '';
        $scope.showDetails = false;
        $scope.showBook = true;
        $scope.message = "Loading ...";
        $scope.books = bookFactory.getBooks();
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
        $scope.showBook = true;
        var book = bookFactory.getBook(parseInt($stateParams.id,10));
        $scope.book = book;
    }])

    .controller('BookCommentController', ['$scope', function ($scope) {
        $scope.mycomment = {rating: 5, comment: "", author: "", date: ""};
        $scope.submitComment = function () {
            $scope.mycomment.date = new Date().toISOString();
            $scope.book.comments.push($scope.mycomment.comment);
        }
    }])

    .controller('ContactController', ['$scope', function ($scope) {
        $scope.feedback = {mychannel: "", firstName: "", lastName: "", agree: false, email: ""};
        var channels = [{value: "tel", label: "Tel."}, {value: "Email", label: "Email"}];
        $scope.channels = channels;
        $scope.invalidChannelSelection = false;
    }])

    .controller('FeedbackController', ['$scope', function ($scope) {
        $scope.sendFeedback = function () {
            console.log($scope.feedback);
            if ($scope.feedback.agree && ($scope.feedback.mychannel == "") && !$scope.feedback.mychannel) {
                $scope.invalidChannelSelection = true;
                console.log('incorrect');
            }
            else {
                $scope.invalidChannelSelection = false;
                $scope.feedback = {
                    mychannel: "", firstName: "", lastName: "",
                    agree: false, email: ""
                };
                $scope.feedback.mychannel = "";

                $scope.feedbackForm.$setPristine();
                console.log($scope.feedback);
            }
        };
    }])

    .controller('IndexController', ['$scope', function ($scope) {

    }])

    .controller('AboutController', ['$scope', function ($scope) {
        $scope.feedback = {mychannel: "", firstName: "", lastName: "", agree: false, email: ""};
        var channels = [{value: "tel", label: "Tel."}, {value: "Email", label: "Email"}];
        $scope.channels = channels;
        $scope.invalidChannelSelection = false;
    }])
;