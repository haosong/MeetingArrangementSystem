describe("ArrangeController", function() {
    var $controller;

    beforeEach(module('SELab3'));

    beforeEach(inject(function(_$controller_){
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $controller = _$controller_;
        //$controller('ArrangeController', {'$scope': $scope});
    }));

    describe('$scope.confirm', function() {
        it('detects duplicate people', function() {
            var $scope = {};
            var controller = $controller('ArrangeController', { $scope: $scope });
            $scope.choices = [{id: '1', 'name': 'Jack', 'attend': true},{id: '2', 'name': 'Jack', 'attend': true}];
            $scope.confirm();
            expect(isNameDuplicate).toEqual(true);
        });

        it('makes sure there to be no duplicate people', function() {
            var $scope = {};
            var controller = $controller('ArrangeController', { $scope: $scope });
            $scope.choices = [{id: '1', 'name': 'Jack', 'attend': true},{id: '2', 'name': 'Jackie', 'attend': true}];
            $scope.confirm();
            expect(isNameDuplicate).toEqual(false);
        });
    });
});