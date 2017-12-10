angular.module('moduloPrincipal').controller('privilegiosController',
		[ '$scope', '$rootScope','$uibModal', 'privilegiosService', PrivilegiosController ]);

function PrivilegiosController($scope, $rootScope, $uibModal, privilegiosService) {
	$scope.data=[];
	$scope.instancia={};
	privilegiosService.list().then(
			function(res){$scope.data=res.data;},
			function(err){$scope.data=[];}
	);
	$scope.editar = function(i) {
		// $scope.instancia=i;
		angular.copy(i, $scope.instancia);
	}
	$scope.guardar= function(nuevo) {
		if(nuevo) {
			privilegiosService.add($scope.instancia).then(
					function(res){
						$scope.data.push(res.data);
						$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}else{
			privilegiosService.edit($scope.instancia).then(
					function(res){
						$scope.data.forEach(function(o,i){
							if(o.id==$scope.instancia.id) {
								$scope.data[i]=res.data;
								return false;
							}
						});
						$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}
		
	}
	$scope.cancelar = function(i) {
		$scope.instancia={};
	}
	$scope.remove = function(id) {
		if(confirm("Desea eliminar el item seleccionado?")) {
			privilegiosService.remove(id).then(function(r){
				$scope.data.forEach(function(o,i){
					if(o.id==id) {
						$scope.data.splice(i,1);
						return false;
					}
				});
			})
		}
	};
	$scope.agregar = function() {
			var modalInstance = $uibModal.open({
				animation : true,
				backdrop: false,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'views/privilegiosForm.html',
				controller : 'AddPrivilegiosController',
				controllerAs : '$ctrl',
				size : 'lg',
				resolve : {
					parametro0 : function() {
						return "Un valor";
					},
					parametro1 : {id:1}
				}
			});
			modalInstance.result.then(function(instancia) {
				if (instancia)
					$scope.instancia = instancia;
				$scope.guardar(true);
			}, function() {
				$scope.cancelar();
			});
		};
}

angular.module('moduloPrincipal').controller('AddPrivilegiosController',
		function($uibModalInstance, parametro0, parametro1) {
			var $ctrl = this;
			console.log(parametro0);
			console.log(parametro1);
			$ctrl.instancia={};

			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.instancia);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});
