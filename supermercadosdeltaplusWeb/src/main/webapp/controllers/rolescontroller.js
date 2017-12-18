angular.module('moduloPrincipal').controller('rolesController',
		[ '$scope', '$rootScope','$uibModal', 'rolesService', 'privilegiosService', RolesController ]);

function RolesController($scope, $rootScope, $uibModal, rolesService, privilegiosService) {
	$scope.data=[];
	$scope.instancia={};
	$scope.cantidadDePrivilegiosDisponibles=0;
	
	privilegiosService.list().then(
			function(respuesta){$scope.cantidadDePrivilegiosDisponibles=respuesta.data.length;},
			function(error){$scope.cantidadDePrivilegiosDisponibles=0;}
	);
	
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
	$scope.remove = function(id1) {
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/roleRemoveForm.html',
			controller : 'roleRemoveFormController',
			controllerAs : '$ctrlroleremoveformcontroller',
			size : 'lg',
		});
		modalInstance.result.then(function(instanciamodal) {
			if (instanciamodal){
				rolesService.remove(id1).then(function(r){
					$scope.data.forEach(function(o,i){
						if(o.id==id1) {
							$scope.data.splice(i,1);
							return false;
						}
					});
				});
			}
		});	
	};
	
	$scope.callModalPrivilegePerRoleAddEditRemoveError = function(){
		var modalInstance = $uibModal.open({
			animation : true,
			backdrop: false,
			ariaLabelledBy : 'modal-title',
			ariaDescribedBy : 'modal-body',
			templateUrl : 'views/privilegePerRoleAddEditRemoveFormError.html',
			controller : 'privilegePerRoleAddEditRemoveFormErrorController',
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
	
		
	$scope.administrarPrivilegioPorRol = function (r,opcion){
			$scope.editar(r);
			if(opcion==3){
				var modalInstance = $uibModal.open({
					animation : true,
					backdrop: false,
					ariaLabelledBy : 'modal-title',
					ariaDescribedBy : 'modal-body',
					templateUrl : 'views/privilegePerRoleRemoveForm.html',
					controller : 'RemovePrivilegePerRoleController',
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
								else
									if(i==$scope.instancia.privileges.length-1)
										$scope.callModalPrivilegePerRoleAddEditRemoveError();
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
				if($scope.instancia.privileges.length>=$scope.cantidadDePrivilegiosDisponibles){
					var modalInstance = $uibModal.open({
						animation : true,
						backdrop: false,
						ariaLabelledBy : 'modal-title',
						ariaDescribedBy : 'modal-body',
						templateUrl : 'views/privilegePerRoleError.html',
						controller : 'PrivilegePerRoleErrorController',
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
							templateUrl : 'views/privilegePerRoleAddForm.html',
							controller : 'AddPrivilegePerRoleController',
							controllerAs : '$ctrl',
							size : 'lg',
						});
						modalInstance.result.then(function(privilegioaagregar) {
							if (privilegioaagregar){
								privilegiosService.list().then(function(respuesta) {
									for(i=0;i<respuesta.data.length;i++){
										if(respuesta.data[i].description==privilegioaagregar){
												$scope.instancia.privileges[$scope.instancia.privileges.length]=respuesta.data[i];
												$scope.guardar(false);
												break;
										}
										if(i==respuesta.data.length-1){
											$scope.callModalPrivilegePerRoleAddEditRemoveError();							
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
							templateUrl : 'views/privilegePerRoleEditForm.html',
							controller : 'PrivilegePerRoleEditController',
							controllerAs : '$ctrl',
							size : 'lg',
							resolve : {
								parametro0 : $scope.instancia.privileges.length,
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
										if(i==respuesta.data.length-1){
											$scope.callModalPrivilegePerRoleAddEditRemoveError();								
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


angular.module('moduloPrincipal').controller('RemovePrivilegePerRoleController',
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


angular.module('moduloPrincipal').controller('PrivilegePerRoleErrorController',
		function($uibModalInstance) {
			var $ctrl = this;
			$ctrl.message="El rol seleccionado ya tiene todos los privilegios disponibles asignados";
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.message);
			};
		});

angular.module('moduloPrincipal').controller('AddPrivilegePerRoleController',
		function($uibModalInstance, privilegiosService) {
			var $ctrl = this;
			$ctrl.newprivilege="";
			$ctrl.listaDePrivilegiosDisponibles=[];
			
			privilegiosService.list().then(
				function(respuesta){
					for(i=0;i<respuesta.data.length;i++){
						$ctrl.listaDePrivilegiosDisponibles[i]=respuesta.data[i].description;
					}					
				},
				function(err){
					$ctrl.listaDePrivilegiosDisponibles=[];
				}
			);
			
			
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.newprivilege);
			};

			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		})

angular.module('moduloPrincipal').controller('PrivilegePerRoleEditController',
		function($uibModalInstance, parametro0, privilegiosService) {
			var $ctrl = this;
			$ctrl.nuevoprivilegio={};
			$ctrl.largoDelArregloDePrivilegios=parametro0;
			
			$ctrl.listaDePrivilegiosDisponibles=[];
			
			privilegiosService.list().then(
				function(respuesta){
					for(i=0;i<respuesta.data.length;i++){
						$ctrl.listaDePrivilegiosDisponibles[i]=respuesta.data[i].description;
					}					
				},
				function(err){
					$ctrl.listaDePrivilegiosQueQuedanPorAsignar=[];
				}
			);
			
			$ctrl.ok = function() {
				$uibModalInstance.close($ctrl.nuevoprivilegio);
			};
			$ctrl.cancel = function() {
				$uibModalInstance.dismiss();
			};
		});
