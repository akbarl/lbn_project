    var app = angular
              .module('LbnApp', ['ngMaterial', 'ngMessages', 'ui.router', 'ngStorage', 'pascalprecht.translate', 'md.data.table', 'ngResource'])
              .config(config)
              .run(run)
              .filter('range', rangeFilter);

    function config($stateProvider, $urlRouterProvider, $translateProvider, $mdThemingProvider) {
        // default route
        $urlRouterProvider.otherwise("/home");
 
        // app routes
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'template/home/index.view.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .state('login', {
                url: '/login',
                templateUrl: 'template/login/index.view.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .state('logout', {
                url: '/logout',
                controller: 'LogoutController',
                controllerAs: 'vm'
            })
            .state('me', {
                url: '/me',
                templateUrl: 'template/me/index.view.html',
                controller: 'MeController',
                controllerAs: 'vm'
            })
            .state('customer', {
                url: '/customer',
                templateUrl: 'template/customer/index.view.html',
                controller: 'CustomerController',
                controllerAs: 'vm'
            })
            .state('order', {
                url: '/order',
                templateUrl: 'template/order/index.view.html',
                controller: 'IndexOrderController',
                controllerAs: 'vm'
            });
        $translateProvider.useStaticFilesLoader({
            prefix: 'locales/locale-',
            suffix: '.json'
        });
        // remove the warning from console log by putting the sanitize strategy
        $translateProvider.useSanitizeValueStrategy('sanitizeParameters')    
        $translateProvider.preferredLanguage('vn');
    }
    
    function rangeFilter() {
        return function(list, total) {
            total = parseInt(total, 10);
            
            for (var i = 0; i < total; i++) {
              list.push(i);
            }
            return list;
        };
     }
    
    function run($rootScope, $http, $location, $localStorage) {
        // keep user logged in after page refresh
        if ($localStorage.currentUser) {
            console.log($localStorage.currentUser);
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
            if($location.path() == "/login") {
                $location.path('/home');
            }
        }
 
        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$localStorage.currentUser) {
                $location.path('/login');
            }
            
            if ($localStorage.currentUser) {
               if($location.path() == "/login") {
                   $location.path('/home');
               }
            }
        });
    }