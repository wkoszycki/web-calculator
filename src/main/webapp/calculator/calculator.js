angular.module('calculatorApp').controller('CalculatorController', ['$scope', '$http', function ($scope, $http) {

    $scope.STATE_ENUM = {
        NUMBER: {},
        INITIAL: {},
        OPERATOR: {},
        OPEN_BRACKET: {},
        CLOSED_BRACKET: {},
        SEPARATOR: {}
    };

    $scope.calculator = {expression: [0], history: ''};
    $scope.currentState = $scope.STATE_ENUM.INITIAL;

    $scope.clickButton = function ($event) {
        if (this.currentState === this.STATE_ENUM.INITIAL) {
            this.calculator.expression = [];
        }
        this.calculator.expression.push($event.target.value);
        this.currentState = this.STATE_ENUM.NUMBER;
    };

    $scope.clickClear = function ($event) {
        this.calculator.expression = [0];
        this.currentState = this.STATE_ENUM.INITIAL;
    };
    $scope.clickSeparator = function ($event) {
        if (this.currentState === this.STATE_ENUM.NUMBER || this.currentState
                                                            === this.STATE_ENUM.INITIAL) {
            this.calculator.expression.push($event.target.value);
            this.currentState = this.STATE_ENUM.SEPARATOR;
        }
    };
    $scope.clickHistory = function ($event) {
        $http.get('/web-calculator/rs-api/calculator/v1.0/history').
            success(function (data, status, headers, config) {
                        $scope.calculator.history = data;
                    }).
            error(function (data, status, headers, config) {
                      console.log('negative response status:' + status + ' data: '
                                  + data);
                      $scope.calculator.history =
                      'Can\'t retrieve history from server';
                  });
    };

    $scope.clickResult = function ($event) {
        var mathExp = this.calculator.expression.join('');
        console.log('math decoded' + mathExp);
        mathExp = encodeURIComponent(mathExp);
        console.log('math encoded' + mathExp);
        $http({
                  url: '/web-calculator/rs-api/calculator/v1.0/calculate',
                  method: 'POST',
                  data: 'mathString=' + mathExp + '',
                  headers: {
                      "Content-Type": "application/x-www-form-urlencoded"
                  }
              }).success(function (data, status, headers, config) {
            $scope.currentState = $scope.STATE_ENUM.NUMBER;
            $scope.calculator.expression = [data];
        }).error(function (data, status, headers, config) {
            console.log('negative response status:' + status + ' data: ' + data);
        });

    };

    $scope.clickOperator = function ($event) {
        if (this.STATE_ENUM.OPERATOR === this.currentState
            || this.STATE_ENUM.SEPARATOR === this.currentState) {
            this.calculator.expression.pop();
        }
        this.calculator.expression.push($event.target.value);
        this.currentState = this.STATE_ENUM.OPERATOR;
    };

}]);