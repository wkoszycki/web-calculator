'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'view1/view1.html',
                    controller: 'View1Ctrl'
                });
            }])

        .controller('View1Ctrl', ['$scope', '$http', function ($scope, $http) {

                $scope.STATE_ENUM = {
                    NUMBER: {},
                    INITIAL: {},
                    OPERATOR: {},
                    OPEN_BRACKET: {},
                    CLOSED_BRACKET: {},
                    SEPARATOR: {}
                };

                $scope.calculator = {expression: [0]};
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
                    if (this.currentState === this.STATE_ENUM.NUMBER || this.currentState === this.STATE_ENUM.INITIAL) {
                        this.calculator.expression.push($event.target.value);
                        this.currentState = this.STATE_ENUM.SEPARATOR;
                    }
                };

                $scope.clickResult = function ($event) {
                    var mathExp = this.calculator.expression.join('');

                    $http({
                        url: '/calculator-jetty/rs-api/calculator/v1.0/calculate',
                        method: 'POST',
                        data: 'mathString=' + mathExp,
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
                    if (this.STATE_ENUM.OPERATOR === this.currentState || this.STATE_ENUM.SEPARATOR === this.currentState) {
                        this.calculator.expression.pop();
                    }
                    this.calculator.expression.push($event.target.value);
                    this.currentState = this.STATE_ENUM.OPERATOR;
                };

            }]);