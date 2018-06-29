
var app = angular.module('redditHomePage', []).controller('HsController', ['$scope', '$window',
  function($scope, $window) {


    $scope.sbody;
    $scope.stitle;
    $scope.slien;
    $scope.sid;
    



    $scope.addTopic = function() {
      gapi.client.topicendpoint.topicEndpoint.createTopic({
        title: $scope.stitle,
        lien: "",
        body: "",
        sender: "u1"
      }).execute(
        function(resp) {
          console.log(resp); 
          $scope.Topics.push({
            id: $scope.slien,
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
      gapi.client.topicendpoint.topicEndpoint.getListTopic({limit: 100}).execute(
        function(resp) {
          $scope.Topics = resp.items;
          $scope.$apply();
          console.log(resp);
        });

    }
    
  
  $scope.upvote = function(topicID) {
      gapi.client.topicendpoint.topicEndpoint.upvoteTopic({
        idTopic: topicID
      }).execute(
        function(resp) {
          $scope.listTopic();
          console.log(resp); 
          $scope.$apply();
        });
    }
    
      $scope.downvote = function(topicID) {
      gapi.client.topicendpoint.topicEndpoint.downvoteTopic({
        idTopic: topicID
      }).execute(
        function(resp) {
          console.log("downvote"); 
          $scope.listTopic();
          console.log(resp); 
          $scope.$apply();
        });
    }
    
    

    // little hack to be sure that apis.google.com/js/client.js is loaded
    // before calling
    // onload -> init() -> window.init() -> then here
    $window.init = function() {
      console.log("windowinit called");
      var rootApi = 'https://reddit-m1miage.appspot.com/_ah/api/';
      gapi.client.load('topicendpoint', 'v1', function() {
        console.log("topic api loaded");
        $scope.listTopic();
      }, rootApi);
    }
  }
]);

