angular.module('moduloPrincipal')
.controller('userRemoveFormController', ['$rootScope', '$scope', '$uibModalInstance', UserRemoveFormController]);

function UserRemoveFormController($rootScope, $scope, $uibModalInstance) {
	var $ctrluserremoveformcontroller = this;
	$ctrluserremoveformcontroller.mensajedeok="OK, ya está";
	
	$ctrluserremoveformcontroller.ok = function() {
		$uibModalInstance.close($ctrluserremoveformcontroller.mensajedeok);
		$uibModalInstance.dismiss();
	};
	
	$ctrluserremoveformcontroller.cancel = function() {
		$uibModalInstance.close();
		$uibModalInstance.dismiss();
	};
}
