angular.module('moduloPrincipal').controller('rolesController',
		[ '$scope', '$rootScope','$uibModal', 'rolesService', RolesController ]);

function RolesController($scope, $rootScope, $uibModal, rolesService) {
	$scope.data=[];
	$scope.instancia={};
	rolesService.list().then(
			function(res){$scope.data=res.data;},
			function(err){$scope.data=[];}
	);
	$scope.editar = function(i) {
		// $scope.instancia=i;
		angular.copy(i, $scope.instancia);
	}
	$scope.guardar= function(nuevo) {
		if(nuevo) {
			rolesService.add($scope.instancia).then(
					function(res){
						$scope.data.push(res.data);
						$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}else{
			rolesService.edit($scope.instancia).then(
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
			rolesService.remove(id).then(function(r){
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
				templateUrl : 'views/rolesAddForm.html',
				controller : 'AddRolesController',
				controllerAs : '$ctrl',
				size : 'lg',
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

angular.module('moduloPrincipal').controller('AddRolesController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.instancia={};

			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.instancia);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});
