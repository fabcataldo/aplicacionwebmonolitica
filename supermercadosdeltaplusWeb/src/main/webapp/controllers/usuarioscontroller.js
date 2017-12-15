angular.module('moduloPrincipal').controller('usuariosController',
		[ '$scope', '$rootScope','$uibModal', 'usuariosService', 'rolesService', 'privilegiosService' ,UsuariosController ]);

function UsuariosController($scope, $rootScope, $uibModal, usuariosService, rolesService, privilegiosService) {
	$scope.data=[];
	$scope.instancia={};
	$scope.cantidadDePrivilegiosEnElSistema=0;
	$scope.cantidadDeRolesDisponiblesEnElSistema=0;
	
	privilegiosService.list().then(
			function(respuesta){$scope.cantidadDePrivilegiosEnElSistema=respuesta.data.length;},
			function(error){$scope.cantidadDePrivilegiosEnElSistema=0;}
	);
	
	rolesService.list().then(
			function(respuesta){$scope.cantidadDeRolesDisponiblesEnElSistema=respuesta.data.length;},
			function(error){$scope.cantidadDeRolesDisponiblesEnElSistema=0;}
	);
	
	usuariosService.list().then(
			function(res){
				$scope.data=res.data;
			},
			function(err){
				$scope.data=[];
			}
	);
	$scope.editar = function(i) {
		// $scope.instancia=i;
		angular.copy(i, $scope.instancia);
	}
	
	$scope.administrarPrivilegio = function (r,opcion){
		$scope.editar(r);
		if(opcion==3){
			var modalInstance = $uibModal.open({
				animation : true,
				backdrop: false,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'views/privilegeRemoveForm.html',
				controller : 'RemovePrivilegeController',
				controllerAs : '$ctrl',
				size : 'lg',
			});
			modalInstance.result.then(function(privilegioaeliminar) {
				if (privilegioaeliminar){
					privilegiosService.list().then(function(respuesta) {
						for(i=0;i<$scope.instancia.privileges.length;i++){
							if($scope.instancia.privileges[i].description==privilegioaeliminar.description){
								$scope.instancia.privileges.splice(i,1);
								break;
							}
						}
						$scope.guardar(false);
					}, function(respuesta) {
						$scope.cancelar();
					});
				}
			}, function() {
				$scope.cancelar();
			});
		}
		else{
			if($scope.instancia.privileges.length>=$scope.cantidadDePrivilegiosEnElSistema){
				var modalInstance = $uibModal.open({
					animation : true,
					backdrop: false,
					ariaLabelledBy : 'modal-title',
					ariaDescribedBy : 'modal-body',
					templateUrl : 'views/roleError.html',
					controller : 'RoleErrorController',
					controllerAs : '$ctrl',
					size : 'lg',
				});
				modalInstance.result.then(
						function(message) {
							if (message){
								$scope.instancia={};
								return;
							}
						}, function() {
							$scope.cancelar();
						}
				);
			}
			else{
				if(opcion==1){
					var modalInstance = $uibModal.open({
						animation : true,
						backdrop: false,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'views/privilegeAddForm.html',
						controller : 'AddPrivilegeController',
						controllerAs : '$ctrl',
						size : 'lg',
					});
					modalInstance.result.then(function(privilegioaagregar) {
						if (privilegioaagregar){
							privilegiosService.list().then(function(respuesta) {
								for(i=0;i<respuesta.data.length;i++){
									if(respuesta.data[i].description==privilegioaagregar.description){
											$scope.instancia.privileges[$scope.instancia.privileges.length]=respuesta.data[i];
											$scope.guardar(false);
											break;
		
									}
								}
							}, function(respuesta) {
								$scope.cancelar();
							});
						}
					}, function() {
						$scope.cancelar();
					});
				}
				if(opcion==2){
					var modalInstance = $uibModal.open({
						animation : true,
						backdrop: false,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'views/privilegeEditForm.html',
						controller : 'EditPrivilegeController',
						controllerAs : '$ctrl',
						size : 'lg',
						resolve : {
							parametro0 : $scope.instancia.privileges.length
						}
					});
					modalInstance.result.then(function(instancianuevoprivilegio) {
						if (instancianuevoprivilegio){						
							privilegiosService.list().then(function(respuesta) {
								$scope.editar(r);
								$scope.nuevoprivilegioencontradoenprivilegiosservice={};
								for(i=0;i<respuesta.data.length;i++){
									if(respuesta.data[i].description==instancianuevoprivilegio.descriptionnuevoprivilegio){
										$scope.nuevoprivilegioencontradoenprivilegiosservice=respuesta.data[i];
										if(instancianuevoprivilegio.descriptionviejoprivilegio==null){
											$scope.instancia.privileges=[];
											$scope.instancia.privileges[0]=$scope.nuevoprivilegioencontradoenprivilegiosservice;
										}
										else
											for(j=0;j<$scope.instancia.privileges.length;j++){
												if($scope.instancia.privileges[j].description==instancianuevoprivilegio.descriptionviejoprivilegio)
														$scope.instancia.privileges[j]=$scope.nuevoprivilegioencontradoenprivilegiosservice;
											}
										break;
									}
								}
								$scope.guardar(false);
							}, function(respuesta) {
								$scope.cancelar();
							});
						}
					}, function() {
						$scope.cancelar();
					});

				}
			}
		}
	}
	
	$scope.administrarRol = function (r,opcion){
		$scope.editar(r);
		if(opcion==3){
			var modalInstance = $uibModal.open({
				animation : true,
				backdrop: false,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'views/roleRemoveForm.html',
				controller : 'RemoveRoleController',
				controllerAs : '$ctrl',
				size : 'lg',
			});
			modalInstance.result.then(function(instanciarolaeliminar) {
				if (instanciarolaeliminar){
					rolesService.list().then(function(respuesta) {
						$scope.editar(r);
						for(i=0;i<$scope.instancia.roles.length;i++){
							if($scope.instancia.roles[i].description==instanciarolaeliminar.description){
								$scope.instancia.roles.splice(i,1);
								break;
							}
						}
						$scope.guardar(false);
					}, function(respuesta) {
						$scope.cancelar();
					});
				}
			}, function() {
				$scope.cancelar();
			});
		}
		else{
			if($scope.instancia.roles.length>=$scope.cantidadDeRolesDisponiblesEnElSistema){
				var modalInstance = $uibModal.open({
					animation : true,
					backdrop: false,
					ariaLabelledBy : 'modal-title',
					ariaDescribedBy : 'modal-body',
					templateUrl : 'views/roleError.html',
					controller : 'RoleErrorController',
					controllerAs : '$ctrl',
					size : 'lg',
				});
				modalInstance.result.then(
						function(message) {
							if (message){
								$scope.instancia={};
								return;
							}
						}, function() {
							$scope.cancelar();
						}
				);
			}
			else{
				if(opcion==1){
					var modalInstance = $uibModal.open({
						animation : true,
						backdrop: false,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'views/roleAddForm.html',
						controller : 'AddRoleController',
						controllerAs : '$ctrl',
						size : 'lg',
					});
					modalInstance.result.then(function(instancianewrol) {
						if (instancianewrol){
							rolesService.list().then(function(respuesta) {
								for(i=0;i<respuesta.data.length;i++){
									if(respuesta.data[i].description==instancianewrol.description){
											$scope.instancia.roles[$scope.instancia.roles.length]=respuesta.data[i];
											$scope.guardar(false);
											break;
		
									}
								}
							}, function(respuesta) {
								$scope.cancelar();
							});
						}
					}, function() {
						$scope.cancelar();
					});
				}
				if(opcion==2){
					var modalInstance = $uibModal.open({
						animation : true,
						backdrop: false,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'views/roleEditForm.html',
						controller : 'EditRoleController',
						controllerAs : '$ctrl',
						size : 'lg',
						resolve : {
							parametro0 : $scope.instancia.roles.length
						}
					});
					modalInstance.result.then(function(instancianuevorol) {
						if (instancianuevorol){						
							rolesService.list().then(function(respuesta) {
								$scope.editar(r);
								$scope.nuevorolencontradoenroleservice={};
								for(i=0;i<respuesta.data.length;i++){
									if(respuesta.data[i].description==instancianuevorol.description){
										$scope.nuevorolencontradoenroleservice=respuesta.data[i];
										if(instancianuevorol.descriptionviejorol==null){
											$scope.instancia.roles=[];
											$scope.instancia.roles[0]=$scope.nuevorolencontradoenroleservice;
										}
										else
											for(j=0;j<$scope.instancia.roles.length;j++){
												if($scope.instancia.roles[j].description==instancianuevorol.descriptionviejorol)
														$scope.instancia.roles[j]=$scope.nuevorolencontradoenroleservice;
											}
										break;
									}
								}
								$scope.guardar(false);
							}, function(respuesta) {
								$scope.cancelar();
							});
						}
					}, function() {
						$scope.cancelar();
					});
				}
			}
		}
	}

	$scope.guardar = function(nuevo) {
		if(nuevo) {
			usuariosService.add($scope.instancia).then(
					function(res){
							$scope.data.push(res.data);
							$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}else{
			usuariosService.edit($scope.instancia).then(
					function(res){
						$scope.data.forEach(function(o,i){
							if(o.idUser==$scope.instancia.idUser) {
								$scope.data[i]=res.data;
								$scope.instancia={};
							}
						});
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
			usuariosService.remove(id).then(function(r){
				$scope.data.forEach(function(o,i){
					if(o.idUser==id) {
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
				templateUrl : 'views/usuariosAddForm.html',
				controller : 'AddUsuariosController',
				controllerAs : '$ctrl',
				size : 'lg',
			});
			modalInstance.result.then(function(instancia) {
				if (instancia){
					rolesService.list().then(function(respuesta) {
						for(i=0;i<respuesta.data.length;i++){
							console.log("AAAGREGO ROLL");
							if(respuesta.data[i].description==instancia.roles.description){
								console.log("ENTRO AL IF DEL AG ROL");
								instancia.roles=[];
								instancia.roles[0]=respuesta.data[i];
								break;
							}
						}					
					}, function() {
					});
					privilegiosService.list().then(function(respuesta2) {
						for(i=0;i<respuesta2.data.length;i++){
							if(respuesta2.data[i].description==instancia.privileges.description){
								instancia.privileges=[];
								instancia.privileges[0]=respuesta2.data[i];
								$scope.instancia = instancia;
								$scope.guardar(true);
								break;
							}
						}					
					}, function() {
					});
				}
			}, function() {
				$scope.cancelar();
			});
		};
}



angular.module('moduloPrincipal').controller('AddUsuariosController',
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

angular.module('moduloPrincipal').controller('EditRoleController',
		function($uibModalInstance, parametro0) {
			var $ctrl = this;
			$ctrl.nuevorol={};
			$ctrl.largoDelArregloDeRoles=parametro0;
			
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.nuevorol);
			};
			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});

angular.module('moduloPrincipal').controller('AddRoleController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.newrol={};
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.newrol);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});

angular.module('moduloPrincipal').controller('RemoveRoleController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.roltodelete={};
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.roltodelete);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});


angular.module('moduloPrincipal').controller('RoleErrorController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.rolmessage="El usuario seleccionado ya tiene todos los roles disponibles asignados";
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.rolmessage);
			};
		});


angular.module('moduloPrincipal').controller('EditPrivilegeController',
		function($uibModalInstance, parametro0) {
			var $ctrl = this;
			$ctrl.nuevoprivilegio={};
			$ctrl.largoDelArregloDePrivilegios=parametro0;
			
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.nuevoprivilegio);
			};
			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});


angular.module('moduloPrincipal').controller('RemovePrivilegeController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.privilegetodelete={};
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.privilegetodelete);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});

angular.module('moduloPrincipal').controller('AddPrivilegeController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.newprivilege={};
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.newprivilege);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		})



//Filtros by Fabio ;)
angular.module('moduloPrincipal').filter('changeInfoInBitsUser', function ($sce) {
	return function (input) {
		if (input===0 || input===false)
			return $sce.trustAsHtml("No");
		if (input===1 || input===true)
			return $sce.trustAsHtml("Si");
	};
})
