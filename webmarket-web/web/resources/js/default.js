(function () {
    const user = User.CURRENT;
    const authService = new AuthService(user);
    if (!authService.isLogined()) {
        SignInFormComponent.getInstance()
            .then(value => {
                value.show();
            })
    }
})();
