angular.module('moduloPrincipal').controller('recoverPasswordOKController',
		[ '$scope', '$rootScope', '$uibModalInstance', RecoverPasswordOKController ]);

function RecoverPasswordOKController($scope, $rootScope, $uibModalInstance) {
	var $ctrlrecover = this;
	
	$ctrlrecover.ok = function(){
		$uibModalInstance.close();
		$uibModalInstance.dismiss();
	};
}
