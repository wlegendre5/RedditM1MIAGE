// Code goes here
/*
var app = angular.module('Authentification', []).controller('HsController', ['$scope', '$window',
  function($scope, $window) {

    $scope.addTopic = function() {
      gapi.client.topicentityendpoint.insertTopicEntity({
        id: $scope.sid,
        title: $scope.stitle,
        body: $scope.sbody
      }).execute(
        function(resp) {
          console.log(resp); // ok je teste pas que c'est bon ;)
          $scope.scores.push({
            id: $scope.sid,
            title: $scope.stitle,
            body: $scope.sbody
          });
          $scope.$apply();
        });
    }

    $scope.listTopic = function() {
      console.log("list topic called");      
      gapi.client.topicentityendpoint.listTopicEntity().execute(
        function(resp) {
          $scope.stitle = resp.items;
          $scope.$apply();
          console.log(resp);
        });

    }

    // little hack to be sure that apis.google.com/js/client.js is loaded
    // before calling
    // onload -> init() -> window.init() -> then here
    $window.init = function() {
      console.log("windowinit called");
      var rootApi = 'https://1-dot-cloudtest1-194515.appspot.com/_ah/api/';
      gapi.client.load('topicentityendpoint', 'v1', function() {
        console.log("topic api loaded");
        $scope.listTopic();
      }, rootApi);
    }
  }
]);*/
