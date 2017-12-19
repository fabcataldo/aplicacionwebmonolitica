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
	
	$scope.callModalPrivilegeAddEditRemoveError = function(){
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/privilegePerUserAddEditRemoveFormError.html',
			controller : 'privilegePerUserAddEditRemoveFormErrorController',
			controllerAs : '$ctrlprivilegeaddeditremoveformerror',
			size : 'lg',
		});
		modalInstance.result.then(function(instanciamodal) {
			if (instanciamodal){
				$scope.cancelar();
			}
		}, function(){								
		});	
	};
	
	$scope.administrarPrivilegio = function (r,opcion){
		$scope.editar(r);
		$scope.banderadealto=false;
		
		if(opcion==3){
			var modalInstance = $uibModal.open({
				animation : true,
				backdrop: false,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'views/privilegePerUserRemoveForm.html',
				controller : 'RemovePrivilegePerUserController',
				controllerAs : '$ctrl',
				size : 'lg',
			});
			modalInstance.result.then(function(privilegioaeliminar) {
				if (privilegioaeliminar){
					privilegiosService.list().then(function(respuesta) {
						for(i=0;i<$scope.instancia.privileges.length;i++){
							if($scope.instancia.privileges[i].description==privilegioaeliminar.description){
								$scope.instancia.privileges.splice(i,1);
								$scope.guardar(false);
								$scope.banderadealto=true;
								break;
							}
						}
						if($scope.banderadealto===false)
							$scope.callModalPrivilegeAddEditRemoveError();
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
					templateUrl : 'views/privilegePerUserError.html',
					controller : 'PrivilegePerUserErrorController',
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
						templateUrl : 'views/privilegePerUserAddForm.html',
						controller : 'AddPrivilegePerUserController',
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
											$scope.banderadealto=true;
											break;
									}
								}
								if($scope.banderadealto===false)
									$scope.callModalPrivilegeAddEditRemoveError();
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
						templateUrl : 'views/privilegePerUserEditForm.html',
						controller : 'EditPrivilegePerUserController',
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
											$scope.guardar(false);
											$scope.banderadealto=true;
										}
										else
											for(j=0;j<$scope.instancia.privileges.length;j++){
												if($scope.instancia.privileges[j].description==instancianuevoprivilegio.descriptionviejoprivilegio){
														$scope.instancia.privileges[j]=$scope.nuevoprivilegioencontradoenprivilegiosservice;
														$scope.guardar(false);
														$scope.banderadealto=true;
												}
											}
										break;
									}
								}
								if($scope.banderadealto==false){
									$scope.callModalRoleAddEditRemoveError();								
								}
								
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
	
	
	$scope.callModalRoleAddEditRemoveError = function(){
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/rolePerUserAddEditRemoveFormError.html',
			controller : 'rolePerUserAddEditRemoveFormErrorController',
			controllerAs : '$ctrlroleaddeditremoveformerror',
			size : 'lg',
		});
		modalInstance.result.then(function(instancianewrol) {
			if (instancianewrol){
				$scope.cancelar();
			}
		}, function(){								
		});	
	};
	
	$scope.administrarRol = function (r,opcion){
		$scope.editar(r);
		$scope.bandera=false;
		if(opcion==3){
			var modalInstance = $uibModal.open({
				animation : true,
				backdrop: false,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'views/rolePerUserRemoveForm.html',
				controller : 'RemoveRolePerUserController',
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
								$scope.guardar(false);
								$scope.bandera=true;
								break;
							}
						}
						if($scope.bandera===false)
							$scope.callModalRoleAddEditRemoveError();
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
					templateUrl : 'views/rolePerUserError.html',
					controller : 'RolePerUserErrorController',
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
						templateUrl : 'views/rolePerUserAddForm.html',
						controller : 'AddRolePerUserController',
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
											$scope.bandera=true;
											break;
									}
								}
								if($scope.bandera===false)
									$scope.callModalRoleAddEditRemoveError();	
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
						templateUrl : 'views/rolePerUserEditForm.html',
						controller : 'EditRolePerUserController',
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
											$scope.guardar(false);
											$scope.bandera=true;
										}
										else{
											for(j=0;j<$scope.instancia.roles.length;j++){
												if($scope.instancia.roles[j].description==instancianuevorol.descriptionviejorol){
													$scope.instancia.roles[j]=$scope.nuevorolencontradoenroleservice;
													$scope.guardar(false);
													$scope.bandera=true;
												}
											}
										}
										break;
									}
								}
								if($scope.bandera==false){
									$scope.callModalRoleAddEditRemoveError();								
								}
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

	$scope.acomodarDatos = function(){
		if($scope.instancia.accountEnabled==="Si" || $scope.instancia.accountEnabled==="si" ){
			$scope.instancia.accountEnabled="true";
		}
		
		else{
			if($scope.instancia.accountEnabled==="No" || $scope.instancia.accountEnabled==="no" )
				$scope.instancia.accountEnabled="false";
		}
		
		if($scope.instancia.accountExpired==="Si" || $scope.instancia.accountExpired==="si" ){
			$scope.instancia.accountExpired="true";
		}
		else{
			if($scope.instancia.accountExpired==="No" || $scope.instancia.accountExpired==="no" )
				$scope.instancia.accountExpired="false";
		}
		
		if($scope.instancia.accountLocked==="Si" || $scope.instancia.accountLocked==="si" ){
			$scope.instancia.accountLocked="true";
		}
		else{
			if($scope.instancia.accountLocked==="No" || $scope.instancia.accountLocked==="no" )
				$scope.instancia.accountLocked="false";
		}
		
		if($scope.instancia.credentialsExpired==="Si" || $scope.instancia.credentialsExpired==="si" ){
			$scope.instancia.credentialsExpired="true";
		}
		else{
			if($scope.instancia.credentialsExpired==="No" || $scope.instancia.credentialsExpired==="no" )
			$scope.instancia.credentialsExpired="false";
		}
	}

	$scope.guardar = function(nuevo) {		
		if(nuevo) {
			$scope.acomodarDatos();
			usuariosService.add($scope.instancia).then(
					function(res){
							$scope.data.push(res.data);
							$scope.instancia={};
					},
					function(err){$scope.instancia={};}
			);
		}else{
			$scope.acomodarDatos();
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
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/userRemoveForm.html',
			controller : 'userRemoveFormController',
			controllerAs : '$ctrluserremoveformcontroller',
			size : 'lg',
		});
		modalInstance.result.then(function(instanciamodal) {
			if (instanciamodal){
				usuariosService.remove(id).then(function(r){
					$scope.data.forEach(function(o,i){
						if(o.idUser==id) {
							$scope.data.splice(i,1);
							return false;
						}
					});
				});
			}
		});	
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
							if(respuesta.data[i].description==instancia.roles.description){
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

angular.module('moduloPrincipal').controller('EditRolePerUserController',
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

angular.module('moduloPrincipal').controller('AddRolePerUserController',
		function($uibModalInstance, rolesService) {
			var $ctrl = this;
			$ctrl.newrol={};
			
			$ctrl.listaDeRolesDisponibles=[];
			rolesService.list().then(
				function(respuesta){
					for(i=0;i<respuesta.data.length;i++){
						$ctrl.listaDeRolesDisponibles[i]=respuesta.data[i].description;
					}					
				},
				function(err){
					$ctrl.listaDeRolesDisponibles=[];
				}
			);
			
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.newrol);
			};
			

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});

angular.module('moduloPrincipal').controller('RemoveRolePerUserController',
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


angular.module('moduloPrincipal').controller('RolePerUserErrorController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.rolmessage="El usuario seleccionado ya tiene todos los roles disponibles asignados";
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.rolmessage);
			};
		});

angular.module('moduloPrincipal').controller('PrivilegePerUserErrorController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.rolmessage="El usuario seleccionado ya tiene todos los privilegios disponibles asignados";
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.rolmessage);
			};
		});


angular.module('moduloPrincipal').controller('EditPrivilegePerUserController',
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


angular.module('moduloPrincipal').controller('RemovePrivilegePerUserController',
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

angular.module('moduloPrincipal').controller('AddPrivilegePerUserController',
		function($uibModalInstance, privilegiosService) {
			var $ctrl = this;
			$ctrl.newprivilege={};
			
			$ctrl.listaDePrivilegiosDisponibles=[];
			privilegiosService.list().then(
				function(respuesta){
					for(i=0;i<respuesta.data.length;i++){
						$ctrl.listaDePrivilegiosDisponibles[i]=respuesta.data[i].description;
					}					
				},
				function(err){
					$ctrl.llistaDePrivilegiosDisponibles=[];
				}
			);
			
			
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
