sealed class Error: Throwable() {
    internal data object ResFolderNotFound : Error()
}