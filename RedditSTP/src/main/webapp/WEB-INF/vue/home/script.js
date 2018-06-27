// Code goes here
var app = angular.module('redditHomePage', []).controller('HsController', ['$scope', '$window',
  function($scope, $window) {


    $scope.Topics = [{
      id: 'toto',
      title: 'lit un livree',
      body: 'kro bi1'
    }, {
      id: 'titi',
      title: 'regarde un film',
      body: 'kro bi1'
    }];

    $scope.sbody;
    $scope.stitle;
    $scope.sid;

    $scope.addTopic = function() {
      gapi.client.topicentityendpoint.insertTopicEntity({
        id: $scope.sid,
        title: $scope.stitle,
        body: $scope.sbody,
        karma: 0,
        lien: "",
        sender: "moi",
        voters: null
      }).execute(
        function(resp) {
          console.log(resp); // ok je teste pas que c'est bon ;)
          $scope.Topics.push({
            id: $scope.sid,
            title: $scope.stitle,
            body: $scope.sbody,
            karma: 0,
            lien: "",
            sender: "moi",
            voters: null
          });
          $scope.$apply();
        });
    }

    $scope.listTopic = function() {
      console.log("list topic called");      
      gapi.client.topicentityendpoint.listTopicEntity().execute(
        function(resp) {
          $scope.Topics = resp.items;
          $scope.$apply();
          console.log(resp);
        });

    }
    
    
  $scope.upvote = function() {
      gapi.client.topicentityendpoint.upvoteTopic({
        id: $scope.sid,
      }).execute(
        function(resp) {
          console.log(resp); // ok je teste pas que c'est bon ;)
          $scope.$apply();
        });
    }
    
    

    // little hack to be sure that apis.google.com/js/client.js is loaded
    // before calling
    // onload -> init() -> window.init() -> then here
    $window.init = function() {
      console.log("windowinit called");
      var rootApi = 'https://reddit-m1miage.appspot.com/_ah/api/';
      gapi.client.load('topicentityendpoint', 'v1', function() {
        console.log("topic api loaded");
        $scope.listTopic();
      }, rootApi);
    }
  }
]);
