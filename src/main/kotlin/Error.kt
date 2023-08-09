sealed class Error: Throwable() {
    data object ResFolderNotFound : Error()
}