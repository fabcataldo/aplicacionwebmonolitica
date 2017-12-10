angular.module('moduloPrincipal').controller('usuariosController', ['$scope', '$rootScope', '$uibModal', 'usuariosService', UsuariosController]);

function UsuariosController($scope, $rootScope, $uibModal, usuariosService) {
	$scope.titulo = "Usuarios";
	$scope.data=[];
	$scope.instancia={};
	$scope.instancia2={};
	
	usuariosService.list().then(
			function(res){$scope.data=res.data;},
			function(err){$scope.data=[];}
	);
	$scope.editar = function(rdatos) {
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : '/views/usuarioEditForm.html',
			controller : 'EditUsuarioController',
			controllerAs : '$ctrl2',
			size : 'lg',
			//VER DE SACAR EL RESOLVE, ya que son parametros que no necesito
			resolve : {
				parametro0 : function() {
					return "Un valor";
				},
				parametro1 : {id:rdatos.idUser}
			}
		});
		modalInstance.result.then(function(instancia) {
			if (instancia)
				instancia.credentialsExpired=0;
				instancia.accountLocked=0;
				instancia.accountExpired=0;
				instancia.accountEnabled=1;
				$scope.instancia2 = instancia;
				$scope.instancia2.idUser=rdatos.idUser;
				if($scope.instancia2.username==null){
					$scope.instancia2.username=rdatos.username;
				}
				if($scope.instancia2.password==null){
					$scope.instancia2.password=rdatos.password;
				}
				if($scope.instancia2.firstName==null){
					$scope.instancia2.firstName=rdatos.firstName;
				}
				if($scope.instancia2.lastName==null){
					$scope.instancia2.lastName=rdatos.lastName;
				}
				if($scope.instancia2.email==null){
					$scope.instancia2.email=rdatos.email;
				}
				if($scope.instancia2.accountEnabled==null){
					$scope.instancia2.accountEnabled=rdatos.accountEnabled;
				}
				if($scope.instancia2.accountExpired==null){
					$scope.instancia2.accountExpired=rdatos.accountExpired;
				}
				if($scope.instancia2.accountLocked==null){
					$scope.instancia2.accountLocked=rdatos.accountLocked;
				}
				if($scope.instancia2.credentialsExpired==null){
					$scope.instancia2.credentialsExpired=rdatos.credentialsExpired;
				}
			$scope.guardar(false);
		}, function() {
			$scope.cancelar();
		});
	}
	
	
	$scope.guardar= function(nuevo) {
		if(nuevo) {
			usuariosService.add($scope.instancia).then(
					function(res){
						$scope.data.push(res.data);
						$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}else{
			usuariosService.edit($scope.instancia2).then(
					function(res){
						$scope.data.forEach(function(o,i){
							if(o.idUser==$scope.instancia2.idUser) {
								$scope.data[i]=res.data;
								return false;
							}
						});
						$scope.instancia2={};
					},
					function(err){$scope.instancia2={};}
			);
		}
		
	}
	$scope.cancelar = function(i) {
		$scope.instancia={};
	}
	$scope.remove = function(id) {
		if(confirm("Desea eliminar el item seleccionado?")) {
			console.log("ENTRO AL IF DEL CONFIRM");
			console.log(id);
			usuariosService.remove(id).then(function(id){
				$scope.data.forEach(function(o,i){
					if(o.idUser==id) {
						$scope.data.splice(i,1);
						console.log("ENTRO AL IFFF");
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
				templateUrl : '/views/usuarioForm.html',
				controller : 'AddUsuarioController',
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
					instancia.credentialsExpired=0;
					instancia.accountLocked=0;
					instancia.accountExpired=0;
					instancia.accountEnabled=0;
					$scope.instancia = instancia;
				$scope.guardar(true);
			}, function() {
				$scope.cancelar();
			});
		};
}

angular.module('moduloPrincipal').controller('AddUsuarioController',
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

angular.module('moduloPrincipal').controller('EditUsuarioController',
		function($uibModalInstance, parametro0, parametro1) {
			var $ctrl2 = this;
			$ctrl2.instancia={};
			
			
			$ctrl2.ok = function() {
				$uibModalInstance.close($ctrl2.instancia);
			};

			$ctrl2.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});

